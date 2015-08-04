package com.fyrecloud.wits;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class MockClient implements Runnable{

	private final CountDownLatch doneSignal;

	MockClient(CountDownLatch doneSignal) {
		this.doneSignal = doneSignal;
	}

	public void run() {

		ArrayList<String> messages = new ArrayList<>();
		messages.add("message 1");
		messages.add("message 2");
		messages.add("Bye.");
		int idx = 0;

		try (
        	// http://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html	
        	Socket kkSocket = new Socket("localhost", WITStore.WITS_DEFAULT_PORT);
            PrintWriter pwToServer = new PrintWriter(kkSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
            	new InputStreamReader(kkSocket.getInputStream()));
            ) {
                //BufferedReader stdIn =
                    //new BufferedReader(new InputStreamReader(System.in));
                String sFromServer;
                String sFromUser;

                while ((sFromServer = in.readLine()) != null) {
                    //System.out.println("Server: " + sFromServer);
        		    //logger.info("Server: " + sFromServer);

                    if (sFromServer.equals("Bye."))
                        break;
                    
                    //sFromUser = stdIn.readLine();
                    sFromUser = messages.get(idx++);

                    if (sFromUser != null) {
                        //System.out.println("Client: " + sFromUser);
                        pwToServer.println(sFromUser);
                    }
                }
            } catch (UnknownHostException e) {
                System.err.println("Don't know about host " );
                System.exit(1);
            } catch (IOException e) {
                System.err.println("Couldn't get I/O for the connection to ");
                System.exit(1);
            }
		
        doneSignal.countDown();
	}
}