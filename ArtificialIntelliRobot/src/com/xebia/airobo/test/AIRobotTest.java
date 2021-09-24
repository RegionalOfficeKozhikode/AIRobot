package com.xebia.airobo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.xebia.airobo.model.AIRobot;
import com.xebia.airobo.model.Barcode;
import com.xebia.airobo.service.AIRobotService;
import com.xebia.airobo.service.AIRobotServiceInf;
import com.xebia.airobo.service.Scanning;

public class AIRobotTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
//		System.setOut("");
	}

	// Robot carries 12 KG
	@Test
	public void OverweightTest() {
		AIRobot robot = new AIRobot();
		AIRobotServiceInf tester = new AIRobotService(robot);
		assertEquals(false, tester.loadWeight(12));
	}

	@Test
	public void NormalTest() {
		AIRobot robot = new AIRobot();
		AIRobotServiceInf tester = new AIRobotService(robot);
		assertTrue(tester.loadWeight(9.5f));
	}

	// Robot walks 3.5 kilometers
	@Test
	public void WalkBatteryValidTest() {
		AIRobot robot = new AIRobot();
		AIRobotServiceInf tester = new AIRobotService(robot);
		tester.chargeBattery();
		assertEquals(30.0, tester.walk(3.5f), 0.0001);
	}

	@Test
	public void WalkWithoutCharingTest() {
		AIRobot robot = new AIRobot();
		AIRobotServiceInf tester = new AIRobotService(robot);
		assertEquals(00.0, tester.walk(3.5f), 0.0001);
	}

	// Robot walks for 2Km carrying 3 kg
	@Test
	public void WalkWithWeighValidTest() {
		AIRobot robot = new AIRobot();
		AIRobotServiceInf tester = new AIRobotService(robot);
		tester.chargeBattery();
		assertEquals(36.0, tester.walkWithWeight(2, 3), 0.0001);
	}
	
	@Test
	public void robotScanTest() {
		Barcode barcode = new Barcode();
		AIRobot robot = new AIRobot();
		Scanning tester = new AIRobotService(robot);
		assertEquals(120.75, tester.scan(barcode), 0.0001);
	}
	
	@Test
	public void successiveWalkTest() {
		AIRobot robot = new AIRobot();
		AIRobotServiceInf tester = new AIRobotService(robot);
		tester.chargeBattery();
		tester.walk(3);
		assertEquals(0.0, tester.walk(2), 0.0001);
	}
	
	@Test
	public void walkAboveMaxDistanceTest() {
		AIRobot robot = new AIRobot();
		AIRobotServiceInf tester = new AIRobotService(robot);
		tester.chargeBattery();
		assertEquals(100.0, tester.walk(6), 0.0001);
	}

}
