package com.fyrecloud.wits;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class ServerThread extends Thread {

	private Socket clientSocket;
	private final Logger logger;

	public ServerThread(Logger logger) {
		this.logger = logger;
	}

	public void run() {
		try (
			ServerSocket srvr = new ServerSocket(WITStore.WITS_DEFAULT_PORT);
			) {
			// 1. Wait for an incoming client connection.
	    	this.clientSocket = srvr.accept(); // block until client connects

	    	// 2. Obtain IO streams for the socket.
			PrintWriter pwToClient = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader brFromClient = new BufferedReader(
				new InputStreamReader(
					clientSocket.getInputStream()
				)
			);

			// KKP Example from 
			// http://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html
			String sInputFromClient, sOutputToClient;

			 // Initiate conversation with client
		    MockProtocol kkp = new MockProtocol();
		    sOutputToClient = kkp.processInput(null);
		    logger.info("Server: " + sOutputToClient);
		    pwToClient.println(sOutputToClient);

		    while ((sInputFromClient = brFromClient.readLine()) != null) {
			    logger.info("Client: " + sInputFromClient);
		    	sOutputToClient = kkp.processInput(sInputFromClient);
			    logger.info("Server: " + sOutputToClient);
		        pwToClient.println(sOutputToClient);
		        if (sOutputToClient.equals("Bye."))
		            break;
		    }

		    // 2. Read the info about the file object from the agent.
			//String s = fromClient.readLine();
			//System.out.println("rcvd from client: "+s);
			//JSONObject inputLine = new JSONObject(in.readLine());
			//JSONObject inputLine = new JSONObject()
			//String hostName       = inputLine.getString("hn");
			//String fyleSystemName = inputLine.getString("fsn");
			//String path           = inputLine.getString("p");
			//String fyleName       = inputLine.getString("fn");

			// 3. Examine the file and determine a suitable reply
			//    to the agent.
			//JSONObject jo = examineFile(hostName, fyleSystemName, path, fyleName);
			//out.println(jo);	// send output back to agent

			//out.close();
			//in.close();
			//socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The purpose of this method is to examine the given file information
	 * as well as the CMA db, and determine what to do with the file.
	 * This method is mostly interested in determining whether or not children
	 * objects of the file are accepted.
	 * 
	 * Return a JSON reply, to be returned to the WITS agent.
	 */
	//private JSONObject examineFile(String hostName, String fyleSystemName, String path, String fileName) {

		// Obtain the node for this host.  Create if necessary.
		//obtainHostNode(hostName);

		//JSONObject retVal = new JSONObject();
		//retVal.put("r", "success");
		//retVal.put("acceptChildren",true);
		//retVal.put("acceptChildrenPrefix", fileName);

		//return retVal;

	//}

	/**
	 * The purpose of this method is to obtain a node that references a 
	 * host.  This method will find that node, or create
	 * it if necessary.
	 * 
	 */
	//private void obtainHostNode(String hostName) {
	//Transaction tx = pm.currentTransaction();
	//try {
	//tx.begin();
	// None of this works.  Null pointer exception.
	//Query q = pm.newQuery(com.fyrecloud.wits.cma.Computer.class, "name == \"joybook\" ");
	//q.declareParameters("double age_limit");
	//String p = Computer.class.getName();
	//Query q = pm.newQuery("SELECT FROM "+p );
	//List results = (List)q.execute();
	//Object results = q.execute();
	// If I can't query, then do brute-force.  Get an Extent and iterate over it.
	// This is ok on my small scale.
	//Extent<Computer> e = pm.getExtent(Computer.class, true);
	//Iterator<Computer> iter=e.iterator();
	//while (iter.hasNext()) {
	//Computer c = iter.next();
	//if(c.name.equals(hostName)) {
	//System.out.println("got it!");
	//}
	//System.out.println("object not found.");
	//}
	//tx.commit();
	//} catch(Exception e) {
	//e.printStackTrace();
	//}
	//finally {
	//if(tx.isActive()) {
	//tx.rollback();
	//}
	//pm.close();
	//}
	//}

}