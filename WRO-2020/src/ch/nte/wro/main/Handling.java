package ch.nte.wro.main;

import ch.nte.wro.base.Robot;
import lejos.utility.Delay;

public class Handling {

	public static void unloadEvacuationRequest(Robot bot, int speed) {
		bot.rotate(speed/2, -0.1F);
		bot.oneStepBelt(300, false);
		Delay.msDelay(1500);
		bot.rotate(speed, -0.3F);
		bot.turnWithRotations(speed/2, 1F, "left");
		bot.followLine(speed, "double.changeLineColor", 0, 60, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
	}
}
