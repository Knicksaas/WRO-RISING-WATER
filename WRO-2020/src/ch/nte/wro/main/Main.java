package ch.nte.wro.main;

import ch.nte.wro.base.Robot;
import ch.nte.wro.variables.GlobalSensors;
import ch.nte.wro.variables.MainVariables;
import ch.nte.wro.variables.SensorValues;
import ch.nte.wro.variables.SynchedVariables;


public class Main {
	
	public static final int speed = 200;
	
	 public static void main(String[] args) {
		Robot bot = new Robot("Robot", MainVariables.mLeft, MainVariables.mRight);

		init(bot);
		
		Navigation.startPointToHouse("right", SensorValues.intensityGreen, bot, speed);
		Handling.unloadEvacuationRequest(bot, speed);
		Navigation.driveToOtherStartPlace("left", bot, speed);
		Navigation.startPointToHouse("left", SensorValues.intensityYellow, bot, speed);
		Handling.unloadEvacuationRequest(bot, speed);
		Handling.uploadSandBags(bot, "left", 100);
		
		
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
	}
}
