package ch.nte.wro.test;

import ch.nte.wro.base.Robot;
import ch.nte.wro.main.Initsialization;
import ch.nte.wro.variables.MainVariables;
import ch.nte.wro.variables.Position;
import ch.nte.wro.variables.SynchedVariables;
import lejos.utility.Delay;

public class BeltTester {
	
public static final int speed = 250;
	
	public static void main(String[] args) {
		Robot bot = new Robot("Robot", MainVariables.mLeft, MainVariables.mRight);

		init(bot);
		
		Position.botPosition = "red";
		boolean direction = true;
		for(int i = 0;i<10;i++) {
			if(i == 5) {
				direction = !direction;
			}
			bot.oneStepBelt(300, direction);
			Delay.msDelay(1500);
		}
		
	}
	
	
	private static void init(Robot bot) {
		SynchedVariables.globalSpeed.set(0);
		Initsialization.sensorInit(bot);
		Initsialization.sensitivityInit();
		Initsialization.housesInit();
	}
}
