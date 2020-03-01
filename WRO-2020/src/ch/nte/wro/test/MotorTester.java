package ch.nte.wro.test;

import ch.nte.wro.base.Robot;
import ch.nte.wro.variables.MainVariables;
import lejos.utility.Delay;

public class MotorTester {

	public static void main(String[] args) {
		Robot bot = new Robot("ae", MainVariables.mLeft, MainVariables.mRight);
		while (true) {
			bot.forward(200);
			Delay.msDelay(2000);
			bot.stop();
			Delay.msDelay(1000);
		}
	}
}
