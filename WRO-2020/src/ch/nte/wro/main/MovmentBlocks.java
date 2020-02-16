package ch.nte.wro.main;

import ch.nte.wro.base.Robot;
import ch.nte.wro.threds.BeepThread;
import ch.nte.wro.variables.Position;
import ch.nte.wro.variables.SensorValues;
import lejos.hardware.Sound;
import lejos.utility.Delay;

public class MovmentBlocks {

	public static void changeSide(Robot bot, int speed, String side1, String side2) {
		bot.followLineRGB(speed, "double.changeLineColor", 0, SensorValues.sensitivity.get(Position.botPosition),
				bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		bot.rotate(speed, 0.5F);
		Delay.msDelay(50);
		if(side1.equalsIgnoreCase("left")) {
			bot.turnWithRotations(speed/2, 0.5F, "right");
		} else if (side1.equalsIgnoreCase("right")) {
			bot.turnWithRotations(speed/2, 0.5f, "left");
		}
		bot.accelerate(speed, speed*2, 2000);
		bot.forwardUntil(speed, bot.getSensorOnPort(1), bot.getSensorOnPort(2),
				SensorValues.intensityBlack, SensorValues.allowedSensorVariation*4);
		Sound.beep();
		bot.forwardUntil(speed*2, bot.getSensorOnPort(1), bot.getSensorOnPort(2),
				SensorValues.intensityBlack, SensorValues.allowedSensorVariation*4);
		Delay.msDelay(100);
		bot.accelerate(speed*2, speed, 1000);
		bot.rotate(speed*2, 1F);
		startPointToLine(bot, speed, side2);
		if(Position.botPosition.equalsIgnoreCase("red") || Position.botPosition.equalsIgnoreCase("yellow")) {
			if(side2.equalsIgnoreCase("right")) {
				Position.botPosition = "blue";
			} else {
				Position.botPosition = "green";
			}
		} else {
			if(side2.equalsIgnoreCase("right")) {
				Position.botPosition = "red";
			} else {
				Position.botPosition = "yellow";
			}
		}
	}
	
	public static void pickUpSandBags(Robot bot, int speed, String side) {
		bot.followLineRGB(speed, "double.cross", 0, SensorValues.sensitivity.get(Position.botPosition),
				bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		bot.rotate(speed, 0.3F);
		if(side.equalsIgnoreCase("left")) {
			bot.rotate(speed*2, 1f, "right");
		} else if (side.equalsIgnoreCase("right")) {
			bot.rotate(speed*2, 1f, "left");
		}
		bot.followLineRGB(speed, "double.cross", 0, 40, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		bot.sandBagPickUp(speed, bot.getSensorOnPort(3));
		bot.turnWithRotations(speed, 1f, side);
	}
	
	public static void startPointToLine(Robot bot, int speed, String side) {
		bot.forwardUntil(speed, bot.getSensorOnPort(1), bot.getSensorOnPort(2),
				SensorValues.intensityWhite, SensorValues.allowedSensorVariation*3);
		bot.rotate(speed, 0.5f);
		if(side.equalsIgnoreCase("left")) {
			bot.turnToLine(speed, "double.left", bot.getSensorOnPort(1), SensorValues.intensityWhite, SensorValues.allowedSensorVariation*2);
			new BeepThread().start();
			bot.turnWithRotations(speed, 0.2f, "left");
		} else {
			bot.turnToLine(speed, "double.right", bot.getSensorOnPort(2), SensorValues.intensityWhite, SensorValues.allowedSensorVariation*2);
			new BeepThread().start();
			bot.turnWithRotations(speed, 0.2f, "right");
		}
	}
	
	public static void driveToHouse(Robot bot, int speed, String mode) {
		bot.followLineRGB(speed, "double.cross", 0, SensorValues.sensitivity.get(Position.botPosition), bot.getSensorOnPort(1), bot.getSensorOnPort(2));	
		bot.followLineRGB(speed, "double.cross", 0, SensorValues.sensitivity.get(Position.botPosition), bot.getSensorOnPort(1), bot.getSensorOnPort(2));	
		if (mode.equalsIgnoreCase("sandbags")){
			Handling.unloadSandBagsInHouse(bot, speed);
		} else {
			Handling.unloadEvacuationRequest(bot, speed);
		}
	}
}

