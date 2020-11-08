package ch.nte.wro.main;

import ch.nte.wro.base.Robot;
import ch.nte.wro.variables.SensorValues;
import lejos.utility.Delay;

public class Handling {

	public static void unloadEvacuationRequest(Robot bot, int speed) {
		bot.oneStepBelt(400, false);
		Delay.msDelay(400);
		bot.backward(speed);
		Delay.msDelay(500);
		bot.turnWithRotations(speed, "half", "right");
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
		speed = 220;
		int speedSlow = 200;
		bot.rotate(speedSlow, -0.3f);
		bot.turnWithRotations(speed, "half", "left");
		bot.rotate(speed, -0.5f, "right");
		bot.rotate(speed, -0.5f, "left");
		bot.oneStepBelt(400, true);
		Delay.msDelay(700);
		bot.rotate(speed, 0.5f, "left");
		bot.rotate(speed, 0.5f, "right");
		bot.rotate(speed, -0.5f, "left");
		bot.rotate(speed, -0.5f, "right");
		bot.oneStepBelt(400, true);
		Delay.msDelay(700);
		bot.rotate(speed, 0.5f, "right");
		bot.rotate(speed, 0.5F, "left");
		bot.rotate(speedSlow, -0.8f);
		
	}
	
	public static void unloadSandBagsInHouseFast(Robot bot, int speed) {
	
		bot.turnWithRotations(speed, "half", "right");
		bot.rotate(speed, -0.5f, "right");
		bot.oneStepBelt(400, true);
		Delay.msDelay(500);
		bot.rotate(speed, -0.7f, "left");
		bot.rotate(speed, -0.125f);
		bot.rotate(speed, -0.2f, "right");
		bot.rotate(speed, 0.2f);
		bot.rotate(speed, 0.6f, "left");
		bot.rotate(speed, 0.6f, "right");
		bot.oneStepBelt(400, true);
		Delay.msDelay(750);
		bot.rotate(speed, -0.05F, "RIGHT");
		bot.rotate(speed, -1.1f);
		bot.rotate(speed, 0.2f);
		bot.rotate(speed, 0.4f, "right");
		bot.rotate(speed, 0.4f, "left");
	
	}
	
}
