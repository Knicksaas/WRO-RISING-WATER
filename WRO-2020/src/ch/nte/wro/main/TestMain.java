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
	
		Navigation.upAndUnloadSandbags(bot, speed);
		Sound.beep();
		bot.oneStepBelt(400, true);
		Delay.msDelay(2000);
		Sound.beep();
		bot.oneStepBelt(400, true);
		Delay.msDelay(2000);

	}
	 
	private static void init(Robot bot) {
		SynchedVariables.globalSpeed.set(0);
		Initsialization.sensorInit(bot);
		Initsialization.sensitivityInit();
		Initsialization.housesInit();
		Initsialization.synchInit();
	}
}



