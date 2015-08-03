package com.fyrecloud.wits;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import java.util.logging.Logger;

public class WITStoreTest {

	// When WITStore is invoked from the command line, with no args,
	// a help screen should be produced and the program should terminate.

	// We test these two concerns by examining the log
	// and looking for suitable messages that assert that said
	// events occurred.
	// We don't examine the actual contents of the help screen
	// because that's produced by 3rd party software, presumably already tested, and it 
	// would be too tedious and of minimal value anyway.
	@Test public void testNoArgsProducesHelpScreenAndTerminates() {
		LoggerSpy loggerSpy = new LoggerSpy();
	    Logger logger = Logger.getLogger(WITStore.class.getName());
	    logger.addHandler(loggerSpy);

	    WITStore w = new WITStore();
	    w.start(new String[]{}, logger);
	    assertThat(loggerSpy.messages, hasItem(WITStore.DISPLAYED_HELP_SCREEN));
	    assertThat(loggerSpy.messages, hasItem(WITStore.TERMINATED));
    }

	// If we invoke the program with an unknown option, display the help screen
	// and terminate.
	@Test public void testBadArgsProduceHelpScreenAndTerminates() {
		LoggerSpy loggerSpy = new LoggerSpy();
	    Logger logger = Logger.getLogger(WITStore.class.getName());
	    logger.addHandler(loggerSpy);

	    WITStore w = new WITStore();
	    w.start(new String[]{"-catfood"}, logger);
	    assertThat(loggerSpy.messages, hasItem(WITStore.DISPLAYED_HELP_SCREEN));
	    assertThat(loggerSpy.messages, hasItem(WITStore.TERMINATED));
    }

	// If we invoke the program with args that cannot be parsed, display the help screen
	// and terminate.  However, I cannot test this because I cannot determine
	// specific args that will break the parser.
	@Test public void testUnparseableArgsProduceHelpScreenAndTerminates() {
		LoggerSpy loggerSpy = new LoggerSpy();
	    Logger logger = Logger.getLogger(WITStore.class.getName());
	    logger.addHandler(loggerSpy);

	    WITStore w = new WITStore();
	    w.start(new String[]{"<?!"}, logger);
	    //assertThat(loggerSpy.messages, hasItem(WITStore.DISPLAYED_HELP_SCREEN));
	    //assertThat(loggerSpy.messages, hasItem(WITStore.TERMINATED));
    }
	
}
