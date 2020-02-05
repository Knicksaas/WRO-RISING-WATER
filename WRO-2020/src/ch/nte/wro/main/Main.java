package ch.nte.wro.main;

import ch.nte.wro.base.Robot;
import ch.nte.wro.variables.ConveyorbeltStatus;
import ch.nte.wro.variables.GlobalSensors;
import ch.nte.wro.variables.MainVariables;
import ch.nte.wro.variables.SensorValues;
import ch.nte.wro.variables.SynchedVariables;

public class Main {
	
	public static final int speed = 200;
	
	 public static void main(String[] args) {
		Robot bot = new Robot("Robot", MainVariables.mLeft, MainVariables.mRight);

		init(bot);
		
		if(MainVariables.greenSlot.contains("house")) {
			Navigation.startPointToHouse("right", SensorValues.intensityGreen, bot, speed);
			Handling.unloadEvacuationRequest(bot, speed);
			Navigation.driveToOtherStartPlace("left", bot, speed);
		} else {
			Navigation.startPointToHouse("left", SensorValues.intensityGreen, bot, speed);
			Handling.unloadEvacuationRequest(bot, speed);
			Navigation.driveToOtherStartPlace("right", bot, speed);
		}
		
		if(MainVariables.redSlot.contains("house")) {
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
		
		String onlyColorOfSecondHouseString;
		if(MainVariables.redSlot.contains("house")) {
			onlyColorOfSecondHouseString = MainVariables.redSlot.toLowerCase().replace("house", "");
			if(onlyColorOfSecondHouseString.equalsIgnoreCase(ConveyorbeltStatus.slot3.toLowerCase().replace("sandbag", ""))) {
				Navigation.driveForwardToHouse(bot, speed);
			} else {
				Navigation.driveToOtherStartPlace("left", bot, speed);
				if(MainVariables.greenSlot.contains("house")) {
					Navigation.startPointToHouse("right", SensorValues.intensityGreen, bot, speed);
				} else {
					Navigation.startPointToHouse("left", SensorValues.intensityGreen, bot, speed);
				}
			}
		} else {
			onlyColorOfSecondHouseString = MainVariables.yellowSlot.toLowerCase().replace("house", "");
			//TODO: understand if-else and finish it
		}

	}
	 
	private static void init(Robot bot) {
		SynchedVariables.globalSpeed.set(0);
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
