package ch.nte.wro.test;

import ch.nte.wro.base.Robot;
import ch.nte.wro.main.Initsialization;
import ch.nte.wro.main.MovmentBlocks;
import ch.nte.wro.variables.MainVariables;
import ch.nte.wro.variables.Position;
import ch.nte.wro.variables.SensorValues;
import lejos.utility.Delay;

public class UnloadHouseTester {

	public static void main(String[] args) {
		Robot bot = new Robot("house test bot", MainVariables.mLeft, MainVariables.mRight);
		Initsialization.synchInit();
		Initsialization.sensitivityInit();
		Initsialization.sensorInit(bot);
		
		Position.botPosition = "green";
		
		MovmentBlocks.driveToHouse(bot, 250, "sandbags");
		
		bot.followLineRGB(200, "double.cross", 0, SensorValues.sensitivity.get(Position.botPosition),
				bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		bot.stop();
		bot.oneStepBelt(200, false);
		Delay.msDelay(2000);
		bot.oneStepBelt(200, false);
	}

}
