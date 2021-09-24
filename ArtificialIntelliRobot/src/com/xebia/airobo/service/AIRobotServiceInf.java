package com.xebia.airobo.service;

public interface AIRobotServiceInf {
	
	public float walk(float kilometer);
	
	public float walkWithWeight(float kilometer, float kilogram);
	
	public boolean loadWeight(float kilogram);
	
	
	public void chargeBattery();
	
	public void removeLoad();
	

}
