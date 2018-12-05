/*
 * Add Copyright
 */
package com.amiyul.demos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class LocationFieldSetMapper implements FieldSetMapper<Location> {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public Location mapFieldSet(FieldSet fieldSet) throws BindException {
		return new Location(fieldSet.readString(2), fieldSet.readString(3), fieldSet.readString(5), fieldSet.readString(8));
	}
}
