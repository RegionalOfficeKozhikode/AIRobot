/**
 * 
 */
package com.xebia.airobo.model;

import com.xebia.airobo.utilities.RoboMessages;

/**
 * @author Apple
 *
 */
public class AIRobot {

	private boolean alert = false;

	private RoboMessages message = null;

	private float batteryStatus = 0;

	private float weightCarried = 0;

	private float distanceCovered = 0;

	public float getBatteryStatus() {
		return batteryStatus;
	}

	public void setBatteryStatus(float batteryStatus) {
		this.batteryStatus = batteryStatus;
	}

	public RoboMessages getMessage() {
		if (null == message) {
			message = RoboMessages.NILL;
		}
		return message;
	}

	public void setMessage(RoboMessages message) {
		this.message = message;
	}

	public float getWeightCarried() {
		return weightCarried;
	}

	public void setWeightCarried(float weightCarried) {
		this.weightCarried = weightCarried;
	}

	public boolean isAlert() {
		return alert;
	}

	public void setAlert(boolean alert) {
		this.alert = alert;
	}

	public float getDistanceCovered() {
		return distanceCovered;
	}

	public void setDistanceCovered(float distanceCovered) {
		this.distanceCovered = distanceCovered;
	}

}
