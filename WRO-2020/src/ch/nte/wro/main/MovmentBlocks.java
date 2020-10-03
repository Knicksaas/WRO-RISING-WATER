package ch.nte.wro.main;

import ch.nte.wro.base.Robot;
import ch.nte.wro.threds.BeepThread;
import ch.nte.wro.variables.Position;
import ch.nte.wro.variables.SensorValues;
import lejos.hardware.Sound;
import lejos.utility.Delay;

public class MovmentBlocks {

	public static void changeSide(Robot bot, int speed, String side1, String side2) {
		speed = 175;
		bot.driveToLineMiddle(speed, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		bot.fixTurn(speed, side1);
		Delay.msDelay(50);
		bot.turnWithRotations(speed, "quarter", side1);
		Delay.msDelay(100);
		bot.accelerate(1, speed, 150);
		bot.forward(1);
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
		Delay.msDelay(50);
		bot.followLineRGB(speed, "double.cross", 0, SensorValues.sensitivity.get(Position.botPosition),
				bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		bot.followLineRGB(speed, "double.cross", 0, SensorValues.sensitivity.get(Position.botPosition),
				bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		bot.rotate(speed, -0.195F);
		bot.turnWithRotations(speed, "quarter", side);
		bot.rotate(speed, -0.4f);
		bot.followLine(speed, "double.cross", 0, 70, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		bot.sandBagPickUp(bot.getSensorOnPort(3));
		bot.rotate(speed, -0.55f);
		bot.turnWithRotations(speed, "quarter", side);
	}
	
	public static void startPointToLine(Robot bot, int speed, String side, String startPoint) {
		bot.forwardUntil(speed, bot.getSensorOnPort(1), bot.getSensorOnPort(2),
				SensorValues.intensityWhite, SensorValues.allowedSensorVariation*8);
		new BeepThread().start();
		bot.rotate(speed, 0.72f);
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
		bot.followLineRGB(speed, "double.cross", 0, SensorValues.sensitivity.get(Position.botPosition),
				bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		bot.followLineRGB(speed, "double.cross", 0, SensorValues.sensitivity.get(Position.botPosition),
				bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		if (mode.equalsIgnoreCase("sandbags")){
			bot.rotate(speed, -0.1f);
			Handling.unloadSandBagsInHouse(bot, speed);
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

