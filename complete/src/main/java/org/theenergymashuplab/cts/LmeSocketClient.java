/*
 * Copyright 2019-2020 The Energy Mashup Lab
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.theenergymashuplab.cts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.theenergymashuplab.cts.controller.LmeRestController;
import org.theenergymashuplab.cts.controller.payloads.EiCreateTenderPayload;
import org.theenergymashuplab.cts.controller.payloads.MarketCreateTenderPayload;

/*
 * Start by new LmeSocketClient.startConnection(("127.0.0.1",
 *	port matching server)
 *
 *	NOTE changing to non-loopback IP for docker deployment
 *	
 *	Insert into LmeRestController to receive Tender information
 *	and generate EiCreateTransaction with new TransactionId, using the
 * Tenderid passed from CtsBridge
 */

/*
	public final int LME_PORT = 39401;		// for Socket Server in LME takes CreateTransaction
 */

//	TODO run in separate thread
public class LmeSocketClient	extends Thread {

	private static final Logger logger = LogManager.getLogger(
			LmeSocketClient.class);
	
	final ObjectMapper mapper = new ObjectMapper();

	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	
    // Socket Server in LME for CreateTransaction
    public static final int LME_PORT = 39401;
    
    // Socket Server in Market for CreateTender 
	public static final int MARKET_PORT = 39402;
	private static int port = MARKET_PORT;
	
	
	// 
	/*
	 * 	TODO
	 * 	Step one - connect to IP address statically
	 * 	Step two - use a command line argument to get this IP address -
	 */
	// wtc laptop in home office is 192.168.1.77
	// assigned IP for the parity-client docker is 192.168.1.51
//	private static String ip = "192.168.1.51";
	
	// from command line or default localhost
	private String ip = null;
	


	
	//	TODO better document queues on parity and CTS side
	
	public LmeSocketClient()	{
		ip = EnergyApplication.matchingEngineIpAddress;
		System.err.println("IP Address '" + ip + "'");
	}

	
	
	@Override
	public void run() {
		EiCreateTenderPayload create;
		EiTender tender;
		MarketCreateTenderPayload toJson;
		String jsonString = null;	// for JSON string

		System.err.println("LmeSocketClient.run " + Thread.currentThread().getName() +
				" port " + port + " ip " + ip);
		
		logger.info("LmeSocketClient.run " + Thread.currentThread().getName() +
					" port " + port + " ip " + ip);

		
		try {
				clientSocket = new Socket(ip, port);
				logger.info("clientSocket is " + clientSocket.toString());
				out = new PrintWriter(clientSocket.getOutputStream(), true);
				logger.info("out constructor " + out.toString());
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
				logger.info("SocketClient IOException: " + e.getMessage());
				e.printStackTrace();
		}
		  
		while(true) {
			  logger.debug("SocketClient while loop head. queueFromLme size " +
				LmeRestController.queueFromLme.size());
			  try {
				create = LmeRestController.queueFromLme.take();
				logger.info("run() took from queueFromLme: size now " + LmeRestController.queueFromLme.size() +
						" " + create.getTender().toString());
				tender = create.getTender();
				toJson = new MarketCreateTenderPayload(
							tender.getSide(),
							tender.getQuantity(),
							tender.getPrice(),
							tender.getTenderId().value(),
							tender.getInterval(),
							tender.getExpireTime());
				
				// TODO save EiCreateTenderPayload in Map <long, EiCreateTenderPayload> for 
				// retrieval when the MarketCreateTransaction is received by CtsSocketServer			
							
				// convert to a JSON string and write to socket
				jsonString = mapper.writeValueAsString(toJson);
				
				if (jsonString == null)	{
					System.err.println("jsonString in LmeSocketClient is NULL!");
				}
				
				logger.trace("run() before send of json string " + jsonString);
				out.println(jsonString);			
				logger.trace("LME Socket Client after sending parity json string " + jsonString);
				
			} catch (InterruptedException e) {
				System.err.println("queueFromLme.take interrupted" + e.getMessage());
				e.printStackTrace();
			} catch (JsonProcessingException e1) {
				System.err.println("JsonProcessingException: Input MarketCreateTenderPayload " + e1.getMessage());
				e1.printStackTrace();
			}
		}
		  
	}

	public String sendMessage(String msg) {	// not used TODO delete
		  try {
				out.println(msg);
				System.err.println("Client sendMessage: " + msg);
				return in.readLine();
		  } catch (Exception e) {
				logger.debug("SocketClient sendMessage: " + e.getMessage());

				return null;
		  }
	}

	public void stopConnection() {	// not used TODO
		  try {
			in.close();
			out.close();
			clientSocket.close();
	  } catch (IOException e) {
			logger.debug("SocketClient stop IOException: " + e.getMessage());
	  }
	}
}
