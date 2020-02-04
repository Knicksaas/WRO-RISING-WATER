package ch.nte.wro.main;

import ch.nte.wro.base.Robot;
import lejos.utility.Delay;

public class Handling {

	public static void unloadEvacuationRequest(Robot bot, int speed) {
		Delay.msDelay(100);
		bot.rotate(speed/2, -0.1F);
		bot.oneStepBelt(300, false);
		Delay.msDelay(1500);
		bot.rotate(speed, -0.3F);
		Delay.msDelay(100);
		bot.turnWithRotations(speed/2, 1F, "left");
		Delay.msDelay(200);
	}
	
	public static void uploadSandBags(Robot bot, String side, int speed) {
		bot.followLine(speed, "double.cross", 0, 80, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		bot.rotate(speed, 0.5F);
		if(side.equalsIgnoreCase("left")) {
			bot.rotate(speed, 0.5f, "right");
		} else if (side.equalsIgnoreCase("right")) {
			bot.rotate(speed, 0.5f, "left");
		}
		bot.followLine(speed, "double.cross", 0, 60, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		bot.sandBagPickUp(speed, null);
	}
}
