package ch.nte.wro.test;

import ch.nte.wro.base.Robot;
import ch.nte.wro.variables.MainVariables;
import lejos.utility.Delay;

public class MediumMotorTester {
	
	public static void main(String[] args) {
		Robot bot = new Robot("tEst boii", MainVariables.mLeft, MainVariables.mRight);
		bot.setArmAngle(100, 130);
		Delay.msDelay(2000);
		bot.setArmAngle(170, 150);
		Delay.msDelay(2000);
		bot.setArmAngle(0, 200);
	}

}
