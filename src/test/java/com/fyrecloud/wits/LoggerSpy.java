package com.fyrecloud.wits;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

// In these tests we will frequently want to examine log output.
// We do so by using this class which hooks into the logger.

public class LoggerSpy extends Handler {
	
	public Set<String> messages = new HashSet<>();

	@Override
	public void publish(LogRecord lr) {
		messages.add(lr.getMessage());
	}

	@Override
	public void close() throws SecurityException {}

	@Override
	public void flush() {}
}
