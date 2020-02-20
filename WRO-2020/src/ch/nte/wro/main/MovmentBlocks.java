package ch.nte.wro.main;

import ch.nte.wro.base.Robot;
import ch.nte.wro.threds.BeepThread;
import ch.nte.wro.variables.Position;
import ch.nte.wro.variables.SensorValues;
import lejos.hardware.Sound;
import lejos.utility.Delay;

public class MovmentBlocks {

	public static void changeSide(Robot bot, int speed, String side1, String side2) {
		bot.driveToLineMiddle(speed, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		Delay.msDelay(50);
		bot.turnWithRotations(speed, 0.5f, side1);
		bot.accelerate(speed, speed*3, 1000);
		bot.forwardUntil(speed, bot.getSensorOnPort(1), bot.getSensorOnPort(2),
				SensorValues.intensityBlack, SensorValues.allowedSensorVariation*4);
		Sound.beep();
		bot.forwardUntil(speed*2, bot.getSensorOnPort(1), bot.getSensorOnPort(2),
				SensorValues.intensityBlack, SensorValues.allowedSensorVariation*4);
		Delay.msDelay(100);
		bot.accelerate(speed*3, speed, 700);
		bot.rotate(speed*3, 1F);
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
		bot.rotate(speed, 0.54F);
		bot.turnWithRotations(speed, 0.5f, side);
		bot.followLine(speed, "double.cross", 0, 70, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		bot.sandBagPickUp(bot.getSensorOnPort(3));
		bot.turnWithRotations(speed, 1.03f, side);
		bot.followLine(speed, "double.time", speed*500/200, 80,
				bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		bot.setSpeeds(1);
		Delay.msDelay(100);
		if(side.equalsIgnoreCase("right")) {
			bot.turnWithRotations(speed, 0.5f, "left");
		} else {
			bot.turnWithRotations(speed, 0.5f, "right");
		}
	}
	
	public static void startPointToLine(Robot bot, int speed, String side, String startPoint) {
		bot.forwardUntil(speed, bot.getSensorOnPort(1), bot.getSensorOnPort(2),
				SensorValues.intensityWhite, SensorValues.allowedSensorVariation*3);
		new BeepThread().start();
		bot.rotate(speed, 0.72f);
		bot.turnWithRotations(speed, 0.5f, side);
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
	
	public static void driveToHouse(Robot bot, int speed, String mode) {
		bot.followLineRGB(speed, "double.cross", 0, SensorValues.sensitivity.get(Position.botPosition),
				bot.getSensorOnPort(1), bot.getSensorOnPort(2));	
		bot.followLineRGB(speed, "double.cross", 0, SensorValues.sensitivity.get(Position.botPosition),
				bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		bot.rotate(speed, -0.1f);
		if (mode.equalsIgnoreCase("sandbags")){
			Handling.unloadSandBagsInHouse(bot, speed);
		}else if (mode.equalsIgnoreCase("sandbagsfast")) {
			Handling.unloadSandBagsInHouseFast(bot, speed);
		} else {
			Handling.unloadEvacuationRequest(bot, speed);
		}
	}
}

