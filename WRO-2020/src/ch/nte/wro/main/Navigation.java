package ch.nte.wro.main;

import ch.nte.wro.base.Robot;
import ch.nte.wro.variables.SensorValues;
import lejos.hardware.Sound;
import lejos.utility.Delay;

public class Navigation {
	
	public static void startPointToHouse(String side, Robot bot, int speed) {
		bot.accelerate(100, speed, 100);
		bot.forwardUntil(speed, bot.getSensorOnPort(1), bot.getSensorOnPort(2),
				SensorValues.intensityWhite, SensorValues.allowedSensorVariation*4);
		Sound.beep();
		bot.forwardUntil(speed, bot.getSensorOnPort(1), bot.getSensorOnPort(2),
				SensorValues.intensityGreen, SensorValues.allowedSensorVariation*6);
		Sound.beep();
		bot.forwardUntil(speed, bot.getSensorOnPort(1), bot.getSensorOnPort(2),
				SensorValues.intensityWhite, SensorValues.allowedSensorVariation*6);
		Sound.beep();
		bot.setSpeeds(0);
		if(side.equalsIgnoreCase("right")) {
			bot.rotate(speed, 1F, "left");
			Sound.beep();
		} else if (side.equalsIgnoreCase("left")) {
			bot.rotate(speed, 1F, "right");
			Sound.beep();
		}
		Delay.msDelay(100);
		bot.followLine(speed/3, "double.cross", 0, 80, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
	}
}
