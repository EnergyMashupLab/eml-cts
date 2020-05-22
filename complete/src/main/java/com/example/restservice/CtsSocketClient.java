package com.example.restservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
 * Start by new CtsSocketClient.startConnection(("127.0.0.1",
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
public class CtsSocketClient	extends Thread {

	private static final Logger logger = LogManager.getLogger(
			CtsSocketClient.class);
	
	final ObjectMapper mapper = new ObjectMapper();

	private Socket clientSocket;
	private PrintWriter out;
	private static InputStreamReader inStream;
	private BufferedReader in;
	
	public static final int MARKET_PORT = 39402;
	private static int port = MARKET_PORT;
	private static String ip = "127.0.0.1";
	
	// queueToMarket is for processed MarketCreateTenderPayload objects
	private static BlockingQueue<String> queueToMarket = new ArrayBlockingQueue(20);
	
	// queueFromLme in LME for EiCreateTender payloads
	// in LmeRestController.queueFromLme
	//	private static BlockingQueue<EiCreateTenderPayload> queueFromLme = new ArrayBlockingQueue(20);
	
	private static String driverLine;	// input line to drive to Market - json encoding
	private static String s;
	public static Thread drainQ;	// possible future use
	private static int ITERATIONS = 27;
	
	public CtsSocketClient()	{
	}

	@Override
	public void run() {
		EiCreateTenderPayload create;
		EiTender tender;
		MarketCreateTenderPayload toJson;
		String jsonString = null;	// for JSON string
		
		logger.info("Thread name" + Thread.currentThread().getName());
		
		  try {
				clientSocket = new Socket(ip, port);
				out = new PrintWriter(clientSocket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		  } catch (IOException e) {
				logger.debug("SocketClient start IOException: " + e.getMessage());
		  }
		  
		  while(true) {
			  logger.info("SocketClient while loop head");
			  try {
				create = LmeRestController.queueFromLme.take();
				logger.info("SocketClient take head of queueFromLme: size " + LmeRestController.queueFromLme.size() +
						create.getTender().toString());
				tender = create.getTender();
				toJson = new MarketCreateTenderPayload(
							tender.getSide(),
							tender.getQuantity(),
							tender.getPrice(),
							tender.getTenderId().value(),
							tender.getInterval(),
							tender.getExpireTime());
							
				// and convert to a JSON string and write to socket
				jsonString = mapper.writeValueAsString(toJson);
				out.println(jsonString);			
				logger.info("SocketClient after println of json " + jsonString);
				
			} catch (InterruptedException e) {
				System.err.println("queueFromLme.take interrupted");
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				System.err.println("JsonProcessingException: Input MarketCreateTenderPayload " + e);
				e.printStackTrace();
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