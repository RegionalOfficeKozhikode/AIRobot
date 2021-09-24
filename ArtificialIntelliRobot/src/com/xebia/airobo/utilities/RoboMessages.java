/**
 * 
 */
package com.xebia.airobo.utilities;

/**
 * @author Apple
 *
 */
public enum RoboMessages {
	
	OVERWEIGHT("OVERWEIGHT"),
	FULL_CHARGE("BATTERY FULL CHARGED"),
	EMPTY_LOAD("LOAD IS EMPTY"),
	EMPTY_BATTERY("BATTERY IS EMPTY"),
	MAX_DISTANCE_EXCEED("MAXIMUM DISTANCE EXCEEDED"), 
	RESEVE_BATTERY("BATTERY IN RESERVE"),
	NILL(""), SCAN_FAILED("SCAN FAILURE");

	private String message;

	public String getMessage() {
		return this.message;
	}

	private RoboMessages(String message) {
		this.message = message;
	}

}
