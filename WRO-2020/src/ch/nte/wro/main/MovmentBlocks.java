package ch.nte.wro.main;

import ch.nte.wro.base.Robot;
import ch.nte.wro.threds.BeepThread;
import ch.nte.wro.variables.Position;
import ch.nte.wro.variables.SensorValues;
import ch.nte.wro.variables.SynchedVariables;
import lejos.hardware.Sound;
import lejos.utility.Delay;

public class MovmentBlocks {
	
	public static void changeSide(Robot bot, int speed, String side1, String side2) {
		int speedSlow = 200;
		speed = 200;
		bot.driveToLineMiddle(speed, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		Delay.msDelay(50);
		bot.turnWithRotations(speed, "quarter", side1);
		bot.forward(speed*2);
		Delay.msDelay(100);
		bot.forwardUntil(speedSlow*3, bot.getSensorOnPort(1), bot.getSensorOnPort(2),
				SensorValues.intensityBlack, SensorValues.allowedSensorVariation*4);
		Sound.beep();
		bot.forwardUntil(speedSlow*3, bot.getSensorOnPort(1), bot.getSensorOnPort(2),
				SensorValues.intensityBlack, SensorValues.allowedSensorVariation*4);
		Delay.msDelay(100);
		bot.rotate(speed, 1F);
		if(Position.botPosition.equalsIgnoreCase("red") || Position.botPosition.equalsIgnoreCase("yellow")) {
			startPointToLine(bot, speed, side2, "R6");
		} else {
			startPointToLine(bot, speed, side2, "R5");
		}
	}

	//IDEA: give more time to accelerate form speed to speed*4 
	// 		and start directly form 200 and do not accelerate at the beginning that fast
	public static void changeSideOld(Robot bot, int speed, String side1, String side2) {
		bot.driveToLineMiddle(speed, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		bot.fixTurn(speed, side1);
		Delay.msDelay(50);
		bot.turnWithRotations(speed, "quarter", side1);
		Delay.msDelay(100);
		bot.forward(speed);
		Delay.msDelay(100);
		bot.accelerate(speed, speed*4, 700);
		bot.forwardUntil(speed, bot.getSensorOnPort(1), bot.getSensorOnPort(2),
				SensorValues.intensityBlack, SensorValues.allowedSensorVariation*4);
		Sound.beep();
		bot.forwardUntil(speed*4, bot.getSensorOnPort(1), bot.getSensorOnPort(2),
				SensorValues.intensityBlack, SensorValues.allowedSensorVariation*4);
		Delay.msDelay(100);
		bot.accelerate(speed*4, speed, 200);
		bot.rotate(speed, 1F);
		if(Position.botPosition.equalsIgnoreCase("red") || Position.botPosition.equalsIgnoreCase("yellow")) {
			startPointToLine(bot, speed, side2, "R6");
		} else {
			startPointToLine(bot, speed, side2, "R5");
		}
	}
	
	public static void pickUpSandBags(Robot bot, int speed, String side) {
		int speedNew = speed;
		speed = 200;
		Delay.msDelay(50);
		bot.followLineRGB(speedNew, "double.cross", 0, SensorValues.sensitivity.get(Position.botPosition),
				bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		bot.setSpeeds(speed);
		SynchedVariables.globalSpeed.set(speed);
		bot.followLineRGB(speed, "double.cross", 0, SensorValues.sensitivity.get(Position.botPosition),
				bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		bot.rotate(speed, -0.2F);
		bot.turnWithRotations(speed, "quarter", side);
		bot.followLine(speed, "double.cross", 0, 70, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		bot.sandBagPickUp(bot.getSensorOnPort(3));
		bot.rotate(speed, -0.54f);
		bot.turnWithRotations(speed, "quarter", side);
	}
	
	public static void startPointToLine(Robot bot, int speed, String side, String startPoint) {
		bot.forwardUntil(speed, bot.getSensorOnPort(1), bot.getSensorOnPort(2),
				SensorValues.intensityWhite, SensorValues.allowedSensorVariation*8);
		new BeepThread().start();
		bot.rotate(speed, 0.73f);
		bot.turnWithRotations(speed, "quarter", side);
		if(startPoint.equalsIgnoreCase("R6")) {
			if(side.equalsIgnoreCase("right")) {
				Position.botPosition = "blue";
			} else {
				Position.botPosition = "green";
			}
		} else {
			if(startPoint.equalsIgnoreCase("R5")) {
				if(side.equalsIgnoreCase("right")) {
					Position.botPosition = "red";
				} else {
					Position.botPosition = "yellow";
				}
			}
		}
	}
	
	public static void driveToOtherHouseSide(Robot bot, int speed) {
		switch (Position.botPosition) {
		case "green":
			Position.botPosition = "blue";
			break;
		case "blue":
			Position.botPosition = "green";
			break;
		case "yellow":
			Position.botPosition = "red";
			break;
		case "red":
			Position.botPosition = "yellow";
			break;
		}
	}
	
	public static void driveToHouse(Robot bot, int speed, String mode) {
		int speedNew = speed;
		speed = 200;
		bot.followLineRGB(speedNew, "double.cross", 0, SensorValues.sensitivity.get(Position.botPosition),
				bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		SynchedVariables.globalSpeed.set(speed);
		bot.followLineRGB(speed, "double.cross", 0, SensorValues.sensitivity.get(Position.botPosition),
				bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		if (mode.equalsIgnoreCase("sandbags")){
			bot.rotate(speed, -0.1f);
			Handling.unloadSandBagsInHouse(bot, speedNew);
		}else if (mode.equalsIgnoreCase("sandbagsfast")) {
			bot.rotate(speed, -0.1f);
			Handling.unloadSandBagsInHouseFast(bot, speed);
		} else {
			bot.rotate(speed, -0.1f);
			Handling.unloadEvacuationRequest(bot, speed);
		}
	}
	
	public static void driveToStartPoint(Robot bot, int speed, String side) {
		bot.driveToLineMiddle(speed, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		Delay.msDelay(50);
		bot.turnWithRotations(speed, "quarter", side);
		bot.rotate(speed, 1);
	}
}

