package ch.nte.wro.test;

import ch.nte.wro.base.Robot;
import ch.nte.wro.main.Initsialization;
import ch.nte.wro.variables.MainVariables;
import lejos.utility.Delay;

public class ArmTester {

	public static void main(String[] args) {
		Robot bot = new Robot("tEst boii", MainVariables.mLeft, MainVariables.mRight);
		Initsialization.synchInit();
		Initsialization.sensitivityInit();
		Initsialization.sensorInit(bot);
		
		while (true) {
			bot.setArmAngle(150, 300, false);
			bot.setArmAngle(200, 500, false);
			Delay.msDelay(1000);
			bot.setArmAngle(0, 400, false);
			Delay.msDelay(1000);
		}
	}
}
