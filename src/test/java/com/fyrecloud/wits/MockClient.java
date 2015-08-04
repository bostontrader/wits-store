package com.fyrecloud.wits;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.CountDownLatch;

public class MockClient implements Runnable{

	private final CountDownLatch doneSignal;
	MockClient(CountDownLatch doneSignal) {
		this.doneSignal = doneSignal;
	}

	public void run() {

        try (
        	// http://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html	
        	Socket kkSocket = new Socket("localhost", WITStore.WITS_DEFAULT_PORT);
            PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
            	new InputStreamReader(kkSocket.getInputStream()));
            ) {
                BufferedReader stdIn =
                    new BufferedReader(new InputStreamReader(System.in));
                String fromServer;
                String fromUser;

                while ((fromServer = in.readLine()) != null) {
                    System.out.println("Server: " + fromServer);
                    if (fromServer.equals("Bye."))
                        break;
                    
                    fromUser = stdIn.readLine();
                    if (fromUser != null) {
                        System.out.println("Client: " + fromUser);
                        out.println(fromUser);
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