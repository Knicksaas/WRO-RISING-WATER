package ch.nte.wro.main;

import ch.nte.wro.base.Robot;
import ch.nte.wro.objects.HouseSpot;
import ch.nte.wro.variables.GlobalSensors;
import ch.nte.wro.variables.MainVariables;
import ch.nte.wro.variables.SensorValues;
import ch.nte.wro.variables.Status;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.robotics.RegulatedMotor;

public class Initsialization {

	public static void sensorInit(Robot bot) {
		bot.setSensorOnPort(GlobalSensors.colorSensor1, 1);
		bot.getSensorOnPort(1).setMode("Red");
		bot.setSensorOnPort(GlobalSensors.colorSensor2, 2);
		bot.getSensorOnPort(2).setMode("Red");
		bot.setSensorOnPort(GlobalSensors.hiTechnicsColorSensor, 3);
		bot.getSensorOnPort(3).setMode("RGB");
	}
	
	public static void sensitivityInit() {
		SensorValues.sensitivity.put("yellow", 110);
		SensorValues.sensitivity.put("blue", 50);
		SensorValues.sensitivity.put("green", 50);
		SensorValues.sensitivity.put("red", 90);
	}
	
	public static void housesInit() {
		Status.houseMap.put("green", new HouseSpot("green", false, null));
		Status.houseMap.put("blue", new HouseSpot("blue", true, "blue"));
		Status.houseMap.put("yellow", new HouseSpot("yellow", false, null));
		Status.houseMap.put("red", new HouseSpot("red", true, "green"));
		proveHouses();
	}
	
	private static void proveHouses() {
		int setted = 0;
		if(Status.houseMap.get("green").isHouse()) {
			setted++;
		}
		if(Status.houseMap.get("blue").isHouse()) {
			setted++;
		}
		if(Status.houseMap.get("yellow").isHouse()) {
			setted++;
		}
		if(Status.houseMap.get("red").isHouse()) {
			setted++;
		}
		if(setted != 2) {
			Sound.buzz();
			System.out.println("UNALLOWED HOUSE INIT: " + setted + " SPOTS ARE FILLD!");
			Button.waitForAnyPress();
		}
	}
	
	public static void synchInit() {
		MainVariables.mLeft.synchronizeWith(new RegulatedMotor[] {MainVariables.mRight});
	}
}
