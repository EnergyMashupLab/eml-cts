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
	
    // Socket Server in LME for CreateTransaction
    public static final int LME_PORT = 39401;
    
    // Socket Server in Market for CreateTender 
	public static final int MARKET_PORT = 39402;
	private static int port = MARKET_PORT;
	private static String ip = "127.0.0.1";
	
	// queueToMarket is for processed MarketCreateTenderPayload objects
	
	// queueFromLme in LME for EiCreateTender payloads
	// in LmeRestController.queueFromLme
	//	private static BlockingQueue<EiCreateTenderPayload> queueFromLme = new ArrayBlockingQueue(20);
		
	public LmeSocketClient()	{
    	System.err.println("LmeSocketClient: 0 param constructor port " +
    			port + " ip " + ip + " " + Thread.currentThread().getName());
	}

	
	
	@Override
	public void run() {
		EiCreateTenderPayload create;
		EiTender tender;
		MarketCreateTenderPayload toJson;
		String jsonString = null;	// for JSON string
		
//		logger.info("LmeSocketClient.run " + Thread.currentThread().getName() + " port " + port + " ip " + ip);
// 		System.err.println("LmeSocketClient.run() port " + port + " ip " + ip +
// 				" '" + Thread.currentThread().getName() + "'");
		
		try {
				clientSocket = new Socket(ip, port);
				logger.debug("clientSocket is " + clientSocket.toString());
				out = new PrintWriter(clientSocket.getOutputStream(), true);
				logger.debug("out constructor " + out.toString());
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
				logger.debug("SocketClient start IOException: " + e.getMessage());
				e.printStackTrace();
		}
		  
		while(true) {
//			  logger.debug("SocketClient while loop head. queueFromLme size " + LmeRestController.queueFromLme.size());
			  try {
				create = LmeRestController.queueFromLme.take();
//				logger.debug("run() took from queueFromLme: size now " + LmeRestController.queueFromLme.size() +
//						" " + create.getTender().toString());
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
//				logger.debug("run() before send of json string " + jsonString);
				out.println(jsonString);			
//				logger.info("LME Socket Client after sending parity json string " + jsonString);
				
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
