package ch.nte.wro.test;

import ch.nte.wro.base.Robot;
import ch.nte.wro.variables.MainVariables;
import lejos.utility.Delay;

public class MediumMotorTester {
	
	public static void main(String[] args) {
		Robot bot = new Robot("tEst boii", MainVariables.mLeft, MainVariables.mRight);
		bot.oneStepBelt(300, true);
		Delay.msDelay(2000);
		bot.oneStepBelt(300, true);
		Delay.msDelay(2000);
		bot.oneStepBelt(300, false);
		Delay.msDelay(2000);
		bot.oneStepBelt(300, false);
		
	}

}
