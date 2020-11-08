package ch.nte.wro.test;

import ch.nte.wro.base.Robot;
import ch.nte.wro.main.Initsialization;
import ch.nte.wro.main.MovmentBlocks;
import ch.nte.wro.variables.MainVariables;
import ch.nte.wro.variables.Position;
import ch.nte.wro.variables.SensorValues;
import ch.nte.wro.variables.SynchedVariables;
import lejos.utility.Delay;

public class UploadSandbagsTester {
	
public static final int speed = 250;
	
	public static void main(String[] args) {
		Robot bot = new Robot("Robot", MainVariables.mLeft, MainVariables.mRight);

		init(bot);
		
		Position.botPosition = "yellow";
		MovmentBlocks.pickUpSandBags(bot, speed, "right");
		bot.followLineRGB(speed, "double.cross", 0, SensorValues.sensitivity.get(Position.botPosition),
				bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		bot.stop();
		bot.oneStepBelt(400, false);
		Delay.msDelay(2000);
		bot.oneStepBelt(400, false);
		Delay.msDelay(2000);
		bot.oneStepBelt(400, false);
	}
	
	
	private static void init(Robot bot) {
		SynchedVariables.globalSpeed.set(0);
		Initsialization.sensorInit(bot);
		Initsialization.sensitivityInit();
		Initsialization.housesInit();
	}
}
