package ch.nte.wro.main;

import ch.nte.wro.base.Robot;
import ch.nte.wro.variables.Status;
import ch.nte.wro.variables.GlobalSensors;
import ch.nte.wro.variables.MainVariables;
import ch.nte.wro.variables.SensorValues;
import ch.nte.wro.variables.SynchedVariables;

public class Main {
	
	public static final int speed = 200;
	
	 public static void main(String[] args) {
		Robot bot = new Robot("Robot", MainVariables.mLeft, MainVariables.mRight);

		init(bot);
		
		if(Status.getHouseOnSide("right").equalsIgnoreCase("right")) {
			Navigation.startPointToHouse("right", SensorValues.intensityGreen, bot, speed);
			Handling.unloadEvacuationRequest(bot, speed);
			Navigation.driveToOtherStartPlace("left", bot, speed);
		} else {
			Navigation.startPointToHouse("left", SensorValues.intensityGreen, bot, speed);
			Handling.unloadEvacuationRequest(bot, speed);
			Navigation.driveToOtherStartPlace("right", bot, speed);
		}
		
		if(Status.getColorOnSide("left").equalsIgnoreCase("left")) {
			Navigation.startPointToHouse("left", SensorValues.intensityYellow, bot, speed);
			Handling.unloadEvacuationRequest(bot, speed);
			Handling.uploadSandBags(bot, "left", 100);
			Navigation.driveToColoredLine("right", bot, speed);
		} else {
			Navigation.startPointToHouse("right", SensorValues.intensityYellow, bot, speed);
			Handling.unloadEvacuationRequest(bot, speed);
			Handling.uploadSandBags(bot, "right", 100);
			Navigation.driveToColoredLine("left", bot, speed);
		}
		
		if(Status.getColorOnSide("left").equalsIgnoreCase(Status.slot3)) {
			Navigation.driveForwardToHouse(bot, speed);
			//Unload Sandbags
		} else {
			if(Status.getHouseOnSide("right").equalsIgnoreCase("right")) {
				Navigation.driveToOtherStartPlace("left", bot, speed);
				Navigation.startPointToHouse("right", SensorValues.intensityGreen, bot, speed);
				//Unload Sandbags
			} else {
				Navigation.driveToOtherStartPlace("right", bot, speed);
				Navigation.startPointToHouse("left", SensorValues.intensityGreen, bot, speed);
				//Unload Sandbags
			}
		}
	}
	 
	private static void init(Robot bot) {
		SynchedVariables.globalSpeed.set(0);
		Status.initHouses("green", "blue", "red", "green");
		sensorInit(bot);
	}
	 
	private static void sensorInit(Robot bot) {
		bot.setSensorOnPort(GlobalSensors.colorSensor1, 1);
		bot.getSensorOnPort(1).setMode("Red");
		bot.setSensorOnPort(GlobalSensors.colorSensor2, 2);
		bot.getSensorOnPort(2).setMode("Red");
		bot.setSensorOnPort(GlobalSensors.hiTechnicsColorSensor, 3);
		bot.getSensorOnPort(3).setMode("RGB");
	}
}
