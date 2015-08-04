package com.fyrecloud.wits;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import static org.junit.Assert.fail;

import java.util.concurrent.CountDownLatch;

public class ServerThreadTest {


	@Test public void test() {

		ServerThread serverThread;
		try {
			// 1. Create a server thread.  When started, it will listen on the port
			// and block until something connects. The server will then read from the client,
			// send a reply, then shutdown.  But don't start yet.
			serverThread = new ServerThread();

			// 2. Create a mock client thread. When started, it will immediately connect
			// to the server, send to the server, read a reply from the server, and then shutdown.
			// We will use a CountDownLatch to make the JUnit thread wait
			// until the client is finished.

			// If the client wants to do more communication then it needs to 
			// start another connection.

			CountDownLatch doneSignal = new CountDownLatch(1);
			MockClient mockClient = new MockClient(doneSignal);

			// 3. Now start the server and client threads.
			Thread clientThread = new Thread(mockClient);
			serverThread.start();
			clientThread.start();

			// 4. Wait until the client is finished before terminating this test.
			doneSignal.await();

		} catch(Exception e) {
			fail();
		}
		
	}

}
