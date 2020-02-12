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
		
		part1(bot, speed);
		part2(bot, speed);
		part3(bot, speed);
	}
	
	/* PART 1
	 * - Unload the first Evacuation-Request on the right side of the Playground
	 */
	private static void part1(Robot bot, int speed) {
		if(Status.getHouseOnSide("right").equalsIgnoreCase("right")) {
			Navigation.startPointToHouse("right", SensorValues.intensityGreen, bot, speed);
			Handling.unloadEvacuationRequest(bot, speed);
			Navigation.driveToOtherStartPlace("left", bot, speed);
		} else {
			Navigation.startPointToHouse("left", SensorValues.intensityGreen, bot, speed);
			Handling.unloadEvacuationRequest(bot, speed);
			Navigation.driveToOtherStartPlace("right", bot, speed);
		}
	}
	
	/* PART 2
	 * - Unload the second Evacuation-Request on the left side of the Playground 
	 * - Upload the sandbags on also the left side
	 */
	private static void part2(Robot bot, int speed) {
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
	}
	
	/* PART 3
	 * - Check if the same colored house as the sandbags are, is on the left side - if not drive to the other side
	 * - Execute part 4 with the specific start variables
	 */
	private static void part3(Robot bot, int speed) {
		if(Status.getColorOnSide("left").equalsIgnoreCase(Status.slot3)) {
			Navigation.driveForwardToHouse(bot, speed);
//			TODO: Unload Sandbags
			if(Status.getColorOnSide("left").equalsIgnoreCase("left")) {
				part4(bot, speed, "left", "left");
			} else {
				part4(bot, speed, "left", "right");
			}
			
		} else {
			if(Status.getHouseOnSide("right").equalsIgnoreCase("right")) {
				Navigation.driveToOtherStartPlace("left", bot, speed);
				Navigation.startPointToHouse("right", SensorValues.intensityGreen, bot, speed);
//				TODO :Unload Sandbags
				part4(bot, speed, "right", "right");
			} else {
				Navigation.driveToOtherStartPlace("right", bot, speed);
				Navigation.startPointToHouse("left", SensorValues.intensityGreen, bot, speed);
//				TODO: Unload Sandbags
				part4(bot, speed, "right", "left");
			}
		}
	}
	
	/* PART 4
	 * - Pick up the remaining sandbags and unload them in the correct houses
	 */
	private static void part4(Robot bot, int speed, String playgroundSide, String houseSide) {
		if(playgroundSide.equalsIgnoreCase("right")) {
			Handling.uploadSandBags(bot, houseSide, speed);
		} else {
			if(houseSide.equalsIgnoreCase("right")) {
				Navigation.driveToOtherStartPlace("left", bot, speed);
			} else {
				Navigation.driveToOtherStartPlace("right", bot, speed);
			}
			if(Status.getHouseOnSide("left").equalsIgnoreCase("left")) {
				Navigation.driveFromStartPointToColoredLine(bot, speed, SensorValues.intensityYellow, "right");
				Handling.uploadSandBags(bot, "left", speed);
				Navigation.driveToColoredLine("right", bot, speed);
			} else {
				Navigation.driveFromStartPointToColoredLine(bot, speed, SensorValues.intensityYellow, "left");
				Handling.uploadSandBags(bot, "right", speed);
				Navigation.driveToColoredLine("left", bot, speed);
			}
		}
		if(playgroundSide.equalsIgnoreCase("right")) {
			if(Status.getHouseOnSide("left").equalsIgnoreCase("left")) {
				Navigation.driveToOtherStartPlace("left", bot, speed);
			} else {
				Navigation.driveToOtherStartPlace("right", bot, speed);
			}
			if(Status.getHouseOnSide("right").equalsIgnoreCase("right")) {
				Navigation.startPointToHouse("right", SensorValues.intensityGreen, bot, speed);
//				TODO: Unload sandbags
			} else {
				Navigation.startPointToHouse("left", SensorValues.intensityGreen, bot, speed);
//				TODO: Unload sandbags
			}
		} else {
			Navigation.driveForwardToHouse(bot, speed);
//			TODO: Unload sandbags
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
