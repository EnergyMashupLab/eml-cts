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

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.ByteBuffer;

import org.agrona.concurrent.UnsafeBuffer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.theenergymashuplab.cts.controller.LmeRestController;
import org.theenergymashuplab.cts.controller.payloads.EiCreateTenderPayload;
import org.theenergymashuplab.cts.controller.payloads.MarketCreateTenderPayload;
import org.theenergymashuplab.cts.sbe.SBEEncoderDecoder_EML;

import com.fasterxml.jackson.databind.ObjectMapper;

import baseline.MarketCreateTenderPayloadEncoder;
import baseline.MessageHeaderEncoder;
import baseline.SideType;

/*
 * Start by new LmeSocketClient.startConnection(("127.0.0.1",
 *	port matching server)
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
	BufferedOutputStream bos;
	
    // Socket Server in LME for CreateTransaction
    public static final int LME_PORT = 39401;
    
    // Socket Server in Market for CreateTender 
	public static final int MARKET_PORT = 39402;
	private static int port = MARKET_PORT;
	private static String ip = "127.0.0.1";
	
	 private static final MessageHeaderEncoder messageHeaderEncoder = new MessageHeaderEncoder();
	 private static final MarketCreateTenderPayloadEncoder marketCreateTenderPayloadEncoder = new MarketCreateTenderPayloadEncoder();
	 ByteBuffer bbf = ByteBuffer.allocate(4096);
	 UnsafeBuffer buffer = new UnsafeBuffer(bbf);
    
    private static final String ENCODING_FILENAME = "sbe.encoding.filename";
    
	//	TODO better document queues on parity and CTS side
	
	public LmeSocketClient()	{
	}

	
	
	@Override
	public void run() {
		EiCreateTenderPayload create;
		EiTender tender;
		MarketCreateTenderPayload toJson;
		String jsonString = null;	// for JSON string

		logger.trace("LmeSocketClient.run " + Thread.currentThread().getName() +
					" port " + port + " ip " + ip);
		
		try {
				clientSocket = new Socket("localhost", port);
				logger.debug("clientSocket is " + clientSocket.toString());
				out = new PrintWriter(clientSocket.getOutputStream(), true);
				logger.debug("out constructor " + out.toString());
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
				logger.debug("SocketClient start IOException: " + e.getMessage());
				e.printStackTrace();
		}
		  
		while(true) {
			  logger.debug("SocketClient while loop head. queueFromLme size " +
				LmeRestController.queueFromLme.size());
			  try {
				create = LmeRestController.queueFromLme.take();
				logger.debug("run() took from queueFromLme: size now " + LmeRestController.queueFromLme.size() +
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
				//jsonString = mapper.writeValueAsString(toJson);
				
				//final MarketCreateTenderPayloadEncoder marketCreateTenderPayloadEncoder = SBEEncoderDecoder.encode(buffer,toJson);
				
				
				
				/*byte[] data = new byte[marketCreateTenderPayloadEncoder.limit()];
				  marketCreateTenderPayloadEncoder.buffer().getBytes(0, data); */
				
				//DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
			//ObjectOutputStream dos = new ObjectOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
				
				bos = new BufferedOutputStream(clientSocket.getOutputStream());
				
				int encodingLengthPlusHeader = encode(buffer,toJson, marketCreateTenderPayloadEncoder);
				
				bos.write(buffer.byteArray(), 0, encodingLengthPlusHeader);
				
				bos.flush();
				
				logger.trace("run() before send of json string " + jsonString);
				//fos.write(data);
				//dos.writeObject(marketCreateTenderPayloadEncoder);
				//out.println(jsonString);			
				logger.trace("LME Socket Client after sending parity json string " + jsonString);
				
			} catch (InterruptedException | IOException e) {
				System.err.println("queueFromLme.take interrupted" + e.getMessage());
				e.printStackTrace();
			} /*
			  catch (JsonProcessingException e1) {
				System.err.println("JsonProcessingException: Input MarketCreateTenderPayload " + e1.getMessage());
				e1.printStackTrace();
			}*/
		}
		  
	}
	
	public static int encode(UnsafeBuffer directBuffer, MarketCreateTenderPayload marketCreateTenderPayload, MarketCreateTenderPayloadEncoder marketCreateTenderPayloadEncoder)
    {
    	marketCreateTenderPayloadEncoder.wrapAndApplyHeader(directBuffer, 0, messageHeaderEncoder)
                .quantity(marketCreateTenderPayload.getQuantity())
                .price(marketCreateTenderPayload.getPrice())
                .ctsTenderId(marketCreateTenderPayload.getCtsTenderId())
                .side(SideType.S);
    	marketCreateTenderPayloadEncoder.expireTime()
                .length(5)
                .varDataMaxValue();
    	marketCreateTenderPayloadEncoder.bridgeInterval()
                .durationInMinutes(30)
                .length(5)
                .varDataMaxValue();
    	return MessageHeaderEncoder.ENCODED_LENGTH + marketCreateTenderPayloadEncoder.encodedLength();
        //return marketCreateTenderPayloadEncoder;

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
