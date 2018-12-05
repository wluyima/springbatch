/*
 * Add Copyright
 */
package com.amiyul.demos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.adapter.ItemProcessorAdapter;

public class LocationProcessor extends ItemProcessorAdapter {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public LocationProcessor() {
		setTargetObject(this);
		setTargetMethod("process");
	}
	
	public Location process(Location location) {
		//logger.debug(" Processing: " + location);
		return location;
	}
	
}
