/*
 * Add Copyright
 */
package com.amiyul.demos;

public class Location {
	
	private String number;
	
	private String street;
	
	private String city;
	
	private String zipcode;
	
	public Location(String number, String street, String city, String zipcode) {
		this.number = number;
		this.street = street;
		this.city = city;
		this.zipcode = zipcode;
	}
	
	@Override
	public String toString() {
		return number + " " + street + ", " + city + ", " + zipcode;
	}
}
