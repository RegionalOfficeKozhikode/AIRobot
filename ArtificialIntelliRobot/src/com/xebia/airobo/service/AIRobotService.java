/**
 * The program was developed in the assumption that
 * for a particular function like walk to execute
 * the program will check the batterystatus for the function to execute
 * then only robot will move.
 * if the battery is 10%  walking 5 km will not execute as robot is expecting 100% charge
 */
package com.xebia.airobo.service;

import com.xebia.airobo.model.AIRobot;
import com.xebia.airobo.model.Barcode;
import com.xebia.airobo.utilities.AIRobotUtilities;
import com.xebia.airobo.utilities.RoboMessages;

/**
 * @author Apple
 *
 */
public class AIRobotService implements AIRobotServiceInf, Scanning {

	private AIRobot robot = null;

	public AIRobotService(AIRobot robot) {
		this.robot = robot;
	}

	@Override
	public float walk(float kilometer) {
		if (loadWeight(robot.getWeightCarried()) && checkBatteryStatus() && checkMaxDistance(kilometer)) {
			if(isChargeExist(kilometer)) {
			batteryConsumption(kilometer, robot.getWeightCarried());
			float distanceCovered = robot.getDistanceCovered();
			robot.setDistanceCovered(kilometer+ distanceCovered);
			}
		}
		checkBatteryStatus();
		display();
		return robot.getBatteryStatus();
	}

	private boolean isChargeExist(float kilometer) {
		boolean isChargeExistForMove = false;
		float weight = robot.getWeightCarried();
		float extraChargeConsumed = weight * AIRobotUtilities.EXTRA_REDUCE_PKM_WGT;
		float chargeConsume = kilometer * (AIRobotUtilities.BATTERY_CONSUMPTION_PKM + extraChargeConsumed);
		float charge = robot.getBatteryStatus();
		if((charge)>= chargeConsume) {
			isChargeExistForMove = true;
		}
		return isChargeExistForMove;
	}

	private void batteryConsumption(float kilometer, float weight) {
		float charge = robot.getBatteryStatus();
		float extraChargeConsumed = weight * AIRobotUtilities.EXTRA_REDUCE_PKM_WGT;
		float chargeConsumed = kilometer * (AIRobotUtilities.BATTERY_CONSUMPTION_PKM + extraChargeConsumed);
		float remainingCharge = charge - chargeConsumed;
		robot.setBatteryStatus(remainingCharge < 0 ? 0 : remainingCharge);
	}

	@Override
	public float walkWithWeight(float kilometer, float kilogram) {
		if (loadWeight(kilogram))
			walk(kilometer);
		return robot.getBatteryStatus();
	}

	@Override
	public boolean loadWeight(float kilogram) {
		float weightCarried = robot.getWeightCarried();
		float totalWeight = weightCarried + kilogram;
		boolean isNormalWeight = true;
		if (totalWeight > 10) {
			robot.setMessage(RoboMessages.OVERWEIGHT);
			isNormalWeight = false;
		}
		robot.setWeightCarried(totalWeight);
		return isNormalWeight;
	}

	@Override
	public void chargeBattery() {
		if (robot.getBatteryStatus() == 100) {
			robot.setMessage(RoboMessages.FULL_CHARGE);
			robot.setAlert(false);
			display();
		} else {
			System.out.println(" Battery charging..");
			try {
				Thread.sleep(2512);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(" Fully charged ");
		}
		robot.setDistanceCovered(0);
		robot.setBatteryStatus(100);
	}

	@Override
	public void removeLoad() {
		if (robot.getWeightCarried() == 0) {
			robot.setMessage(RoboMessages.EMPTY_LOAD);
			robot.setAlert(false);
			display();
		}
		robot.setWeightCarried(0);
	}

	private boolean checkBatteryStatus() {
		float batteryCharge = robot.getBatteryStatus();
		if (batteryCharge == 0) {
			robot.setMessage(RoboMessages.EMPTY_BATTERY);
			robot.setAlert(true);
			return false;

		} else if (batteryCharge < 15) {
			robot.setMessage(RoboMessages.RESEVE_BATTERY);
			robot.setAlert(true);
			return false;
		}
		return true;
	}

	private boolean checkMaxDistance(float kilometer) {
		if (calcMaxMilege(kilometer) < kilometer) {
			robot.setMessage(RoboMessages.MAX_DISTANCE_EXCEED);
			return false;
		}
		return true;
	}

	private float calcMaxMilege(float kilometer) {
		float weight = robot.getWeightCarried();
		float maxMilege = 100
				/ (AIRobotUtilities.BATTERY_CONSUMPTION_PKM + (AIRobotUtilities.EXTRA_REDUCE_PKM_WGT * weight));
		return maxMilege;
	}

	private void display() {
		System.out.println("Light on Robot head : \t" + (robot.isAlert() ? "Red" : " "));
		System.out.println("Led display on Robot chest : \t" + robot.getMessage().getMessage());
		System.out.println("Battery Remaining :\t" + robot.getBatteryStatus());
	}

	@Override
	public double scan(Barcode barcode) {
		double price = 0;
		System.out.println("scanning");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (checkBarcodeImg(barcode)) {
			price = barcode.getPrice();
			System.out.println("Price display on Robot  : \t" + price);
		} else {
			robot.setMessage(RoboMessages.SCAN_FAILED);
			display();
		}
		return price;
	}

	/**
	 * There is no need for creating bar code scanning functionality. We can assume
	 * that we already have an api which does that for us.
	 * 
	 * @param barcode
	 * @return
	 */
	private boolean checkBarcodeImg(Barcode barcode) {
//		if(barcode.getImg())
		return true;
	}

}
