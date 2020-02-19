package ch.nte.wro.main;

import ch.nte.wro.base.Robot;
import ch.nte.wro.variables.SensorValues;
import lejos.utility.Delay;

public class Handling {

	public static void unloadEvacuationRequest(Robot bot, int speed) {
		bot.oneStepBelt(300, false);
		Delay.msDelay(1500);
		bot.rotate(speed, -0.3F);
		Delay.msDelay(100);
		bot.turnWithRotations(speed, 1.02f, "left");
		Delay.msDelay(200);
	}
	
	public static void uploadSandBags(Robot bot, String side, float lineintensity, int speed) {
		if(lineintensity == SensorValues.intensityYellow) {
			bot.followLine(speed, "double.cross", 0, 200, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		} else {
			bot.followLine(speed, "double.cross", 0, 100, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		}
		bot.rotate(speed, 0.3F);
		if(side.equalsIgnoreCase("left")) {
			bot.rotate(speed, 1f, "right");
		} else if (side.equalsIgnoreCase("right")) {
			bot.rotate(speed, 1f, "left");
		}
		bot.followLine(speed, "double.cross", 0, 60, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		bot.sandBagPickUp(bot.getSensorOnPort(3));
	}
	
	public static void unloadSandBagsInHouse(Robot bot, int speed) {
		bot.rotate(speed, -0.4f);
		bot.turnWithRotations(speed, 1.04f, "left");
		bot.rotate(speed, -0.5f, "right");
		bot.rotate(speed, -0.5f, "left");
		bot.oneStepBelt(400, true);
		Delay.msDelay(1500);
		bot.rotate(speed, 0.5f, "left");
		bot.rotate(speed, 0.5f, "right");
		bot.rotate(speed, -0.5f, "left");
		bot.rotate(speed, -0.5f, "right");
		bot.oneStepBelt(400, true);
		Delay.msDelay(1500);
		bot.rotate(speed, 0.5f, "right");
		bot.rotate(speed, 0.5F, "left");
		bot.rotate(speed, -0.9f);
		
	}
}
