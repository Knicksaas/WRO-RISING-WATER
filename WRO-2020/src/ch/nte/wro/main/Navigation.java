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
		bot.followLine(speed/2, "double.cross", 0, 80, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		bot.followLine(speed/2, "double.time", 1000, 80, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		bot.followLine(speed/3, "double.cross", 0, 80, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
	}
	
	public static void driveToOtherStartPlace(String side, Robot bot, int speed) {
		Delay.msDelay(100);
		bot.followLine(speed/2, "double.changeLineColor", 0, 60, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
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
}
