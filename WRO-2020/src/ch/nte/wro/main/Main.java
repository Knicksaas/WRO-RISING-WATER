package ch.nte.wro.main;

import ch.nte.wro.base.Robot;
import ch.nte.wro.variables.MainVariables;
import ch.nte.wro.variables.SynchedVariables;
import lejos.utility.Delay;

public class Main {
	
	public static final int speed = 200;
	
	 public static void main(String[] args) {
		Robot bot = new Robot("Robot", MainVariables.mLeft, MainVariables.mRight);

		init(bot);
		
		Navigation.unloadEvacuationRequests(bot, speed, "R6");
		Navigation.upAndUnloadSandbags(bot, speed);
		Navigation.driveToStartPosition(bot, speed, "R6");
		bot.oneStepBelt(400, false);
		Delay.msDelay(1000);
		bot.oneStepBelt(400, false);
	}
	
	
	private static void init(Robot bot) {
		SynchedVariables.globalSpeed.set(0);
		Initsialization.sensorInit(bot);
		Initsialization.sensitivityInit();
		Initsialization.housesInit();
	}
}
