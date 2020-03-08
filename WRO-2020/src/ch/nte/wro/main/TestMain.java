package ch.nte.wro.main;

import ch.nte.wro.base.Robot;
import ch.nte.wro.variables.MainVariables;
import ch.nte.wro.variables.Position;
import ch.nte.wro.variables.SynchedVariables;
import lejos.hardware.Sound;
import lejos.utility.Delay;

public class TestMain {
	
	public static final int speed = 200;
	
	 public static void main(String[] args) {
		Robot bot = new Robot("Robot", MainVariables.mLeft, MainVariables.mRight);

		init(bot);
		
		Position.botPosition = "green";
	
		bot.oneStepBelt(400, false);
		Delay.msDelay(2500);
		Sound.beep();
		bot.oneStepBelt(400, false);
		Delay.msDelay(2500);
		bot.oneStepBelt(400, false);
		Delay.msDelay(2500);
		Sound.beep();
		bot.oneStepBelt(400, false);
		Delay.msDelay(2500);
		
		Navigation.upAndUnloadSandbags(bot, speed);

	}
	 
	private static void init(Robot bot) {
		SynchedVariables.globalSpeed.set(0);
		Initsialization.sensorInit(bot);
		Initsialization.sensitivityInit();
		Initsialization.housesInit();
		Initsialization.synchInit();
	}
}



