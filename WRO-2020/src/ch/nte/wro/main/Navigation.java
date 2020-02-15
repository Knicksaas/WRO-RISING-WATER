package ch.nte.wro.main;

import ch.nte.wro.base.Robot;
import ch.nte.wro.variables.SensorValues;
import lejos.hardware.Sound;
import lejos.utility.Delay;

public class Navigation {
	
	public static void startPointToHouse(String side, float lineIntensity, Robot bot, int speed) {
		Delay.msDelay(100);
		bot.accelerate(100, speed, 100);
		bot.forwardUntil(speed, bot.getSensorOnPort(1), bot.getSensorOnPort(2),
				SensorValues.intensityWhite, SensorValues.allowedSensorVariation*5);
		Sound.beep();
		bot.forwardUntil(speed, bot.getSensorOnPort(1), bot.getSensorOnPort(2),
				lineIntensity, SensorValues.allowedSensorVariation*10);
		Sound.beep();
		bot.forwardUntil(speed, bot.getSensorOnPort(1), bot.getSensorOnPort(2),
				SensorValues.intensityWhite, SensorValues.allowedSensorVariation*10);
		Sound.beep();
		bot.setSpeeds(0);
		if(side.equalsIgnoreCase("right")) {
			bot.rotate(speed, 1F, "left");
			Sound.beep();
		} else if (side.equalsIgnoreCase("left")) {
			bot.rotate(speed, 1F, "right");
			Sound.beep();
		}
		bot.motorsOff();
		Delay.msDelay(200);
		if(lineIntensity == SensorValues.intensityYellow) {
			bot.followLine(speed/2, "double.cross", 0, 200, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
			bot.followLine(speed/2, "double.time", 500, 200, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
			bot.followLine(speed/3, "double.cross", 0, 200, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		} else {
			bot.followLine(speed/2, "double.cross", 0, 100, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
			bot.followLine(speed/2, "double.time", 500, 100, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
			bot.followLine(speed/3, "double.cross", 0, 100, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		}
	}
	
	public static void driveToOtherStartPlace(String side, Robot bot, float lineIntenstiy, int speed) {
		Delay.msDelay(100);
		if(lineIntenstiy == SensorValues.intensityYellow) {
			bot.followLine(speed/2, "double.changeLineColor", 0, 200, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		} else {
			bot.followLine(speed/2, "double.changeLineColor", 0, 80, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		}
		Delay.msDelay(100);
		bot.rotate(speed, 0.5F);
		Delay.msDelay(50);
		if(side.equalsIgnoreCase("left")) {
			bot.turnWithRotations(speed/2, 0.5F, "right");
		} else if (side.equalsIgnoreCase("right")) {
			bot.turnWithRotations(speed/2, 0.5f, "left");
		}
		Delay.msDelay(50);
		bot.accelerate(speed, speed*2, 2000);
		bot.forwardUntil(speed, bot.getSensorOnPort(1), bot.getSensorOnPort(2),
				SensorValues.intensityBlack, SensorValues.allowedSensorVariation*3);
		Sound.beep();
		bot.forwardUntil(speed*2, bot.getSensorOnPort(1), bot.getSensorOnPort(2),
				SensorValues.intensityBlack, SensorValues.allowedSensorVariation*3);
		Delay.msDelay(100);
		bot.rotate(speed, 1F);
		Delay.msDelay(100);
		bot.stop();
		Delay.msDelay(100);
		
	}
	
	public static void driveForwardToHouse(Robot bot, int speed) {
		bot.followLine(speed, "double.cross", 0, 80, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		bot.followLine(speed, "double.time", 500, 80, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		bot.followLine(speed/2, "double.cross", 0, 80, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
	}
	
	public static void driveToColoredLine(String side, Robot bot, int speed) {
		Delay.msDelay(100);
		bot.turnWithRotations(speed/2, 1f, side);
		bot.rotate(speed, 0.4f);
		bot.turnWithRotations(speed/2, 0.5f, side);
	}
	
	public static void driveFromStartPointToColoredLine(Robot bot, int speed, float lineIntensity, String side) {
		Delay.msDelay(100);
		bot.accelerate(100, speed, 100);
		bot.forwardUntil(speed, bot.getSensorOnPort(1), bot.getSensorOnPort(2),
				SensorValues.intensityWhite, SensorValues.allowedSensorVariation*5);
		Sound.beep();
		bot.forwardUntil(speed, bot.getSensorOnPort(1), bot.getSensorOnPort(2),
				lineIntensity, SensorValues.allowedSensorVariation*10);
		Sound.beep();
		bot.forwardUntil(speed, bot.getSensorOnPort(1), bot.getSensorOnPort(2),
				SensorValues.intensityWhite, SensorValues.allowedSensorVariation*10);
		Sound.beep();
		bot.setSpeeds(0);
		if(side.equalsIgnoreCase("right")) {
			bot.rotate(speed, 1F, "left");
			Sound.beep();
		} else if (side.equalsIgnoreCase("left")) {
			bot.rotate(speed, 1F, "right");
			Sound.beep();
		}
	}
}
