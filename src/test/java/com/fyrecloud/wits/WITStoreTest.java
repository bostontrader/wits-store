package com.fyrecloud.wits;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import java.util.logging.Logger;

public class WITStoreTest {

	// In these tests we will frequently want to examine log output.
	// We do so by using the class LoggerSpy which hooks into the logger.

	// When WITStore is invoked from the command line, with no args,
	// a help screen should be produced and the program should terminate.

	// 1. First test for the help screen.  We do this by examining the log
	// and looking for a suitable message that asserts the help screen was
	// produced.  We don't examine the actual contents of the help screen
	// because that's produced by 3rd party software, presumably already tested, and it 
	// would be too tedious and of minimal value anyway.
	@Test public void testNoArgsProducesHelpScreen() {
		LoggerSpy loggerSpy = new LoggerSpy();
	    Logger logger = Logger.getLogger(WITStore.class.getName());
	    logger.addHandler(loggerSpy);

	    WITStore w = new WITStore();
	    w.start(new String[]{}, logger);
	    assertThat(loggerSpy.messages, hasItem(WITStore.DISPLAYED_HELP_SCREEN));
    }

	// 2. Now test for that invocation w/o args results in termination.
	@Test public void noArgsProducesTermination() {
		LoggerSpy loggerSpy = new LoggerSpy();
	    Logger logger = Logger.getLogger(WITStore.class.getName());
	    logger.addHandler(loggerSpy);

	    WITStore w = new WITStore();
	    w.start(new String[]{}, logger);
	    assertThat(loggerSpy.messages, hasItem(WITStore.TERMINATED));
    }
}
