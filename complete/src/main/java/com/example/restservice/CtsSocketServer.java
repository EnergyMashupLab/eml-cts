package com.example.restservice;


import java.net.*;
import java.io.*;

//	import org.apache.logging.log4j.LogManager;
//  import org.apache.logging.log4j.Logger;

/*
 * Communicates with CtsSocketClient called from LmeRestController in CTS
 *
 *	This socket server in parity-cts receives and responds to MarketCreateTransaction
 * message and replies with a MarketCreatedTransaction message.
 */


/*
 * Global constants

public final int LME_PORT = 39401;		// for Socket Server in LME takes CreateTransaction
public final int MARKET_PORT = 39402;	// for Socket Server in Market takes CreateTender 
 */

//	TODO run in separate thread

public class CtsSocketServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
//	private static final Logger logger = LogManager.getLogger(CtsSocketServer.class);
    private BufferedReader in;
    int port = 0;

    public void start(int port) {
    	// System.err.println("CtsSocketServer port: " + port);
    	
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String greeting = in.readLine();
            if ("hello server".equals(greeting))
                out.println("hello client");
            else	{
                out.println("unrecognised greeting");
            	System.err.println("CtsSocketServer unrecognized greeting " + greeting);
            }
        } catch (IOException e) {
        	
            //	LOG.debug(e.getMessage());
        	// ignore
        }
    }

    public void stop() {
        try {
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
        	// System.err.println("CtsSocketServer stop: " + e.getMessage());
//    		logger.info("CtsSocketServer: " + e.getMessage());
        }
    }
    
    public CtsSocketServer()	{
    }
    
    public CtsSocketServer(int port)	{
    	// System.err.println("CtsSocketServer constructor Port: " + port);
    	this.port = port;
    	CtsSocketServer server = new CtsSocketServer();
        server.start(this.port);
    }
}