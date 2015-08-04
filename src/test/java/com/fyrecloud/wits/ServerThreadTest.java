package com.fyrecloud.wits;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

public class ServerThreadTest {

	@Test public void test() {

		// We'll need to spy on the log for this test.
		LoggerSpy loggerSpy = new LoggerSpy();
	    Logger logger = Logger.getLogger(ServerThread.class.getName());
	    logger.addHandler(loggerSpy);

	    ServerThread serverThread;

	    try {
			// 1. Create, but don't yet start, a server thread.  When started, the thread
			// will listen on the port and block until something connects. The server 
			// will then enter a loop whereby it reads from the client and sends a reply,
			// until it receives the special shut-down code word. 
			serverThread = new ServerThread(logger);

			// 2. Create a mock client thread. When started, it will immediately connect
			// to the server and enter a similar loop whereby the client will send to the
			// server and read a reply from the server, until the client decides to shutdown.
			// We will use a CountDownLatch to make the JUnit thread wait
			// until the client is finished.
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

		// Now examine the log in order to verify expected behavior.
	    // This test as-is does not check message order.  This is a very 
	    // temporary arragnement so don't fret about it.
	    assertThat(loggerSpy.messages, hasItem("Server: " + MockProtocol.HELLO));
	    assertThat(loggerSpy.messages, hasItem("Client: " + "message 1"));
	    assertThat(loggerSpy.messages, hasItem("Server: " + "keep talkin'"));
	    assertThat(loggerSpy.messages, hasItem("Client: " + "message 2"));
	    assertThat(loggerSpy.messages, hasItem("Server: " + "keep talkin'"));
	    assertThat(loggerSpy.messages, hasItem("Client: " + "Bye."));
	    assertThat(loggerSpy.messages, hasItem("Server: " + "Bye."));
	}

}
