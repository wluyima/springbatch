/*
 * Add Copyright
 */
package com.amiyul.demos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.adapter.ItemWriterAdapter;

public class ConsoleLocationWriter extends ItemWriterAdapter<Location> {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public ConsoleLocationWriter() {
		setTargetObject(this);
		setTargetMethod("write");
	}
	
	public void write(Location location) {
		logger.debug("Writing: " + location);
	}
	
}
