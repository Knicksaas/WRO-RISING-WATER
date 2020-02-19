package ch.nte.wro.test;

import ch.nte.wro.base.Robot;
//import ch.nte.wro.variables.GlobalSensors;
import ch.nte.wro.variables.MainVariables;
import lejos.utility.Delay;

public class Speedtest {
	
	public static int speed = 200;
	
	public static void main(String[] args) {
		Robot bot = new Robot("test", MainVariables.mLeft, MainVariables.mRight);
//		init(bot);
		bot.oneStepBelt(200, false);
		Delay.msDelay(1500);
		bot.oneStepBelt(200, false);
		Delay.msDelay(1500);
		bot.oneStepBelt(200, false);
		Delay.msDelay(1500);
		bot.oneStepBelt(200, false);
		Delay.msDelay(1500);
		bot.oneStepBelt(200, false);
		Delay.msDelay(1500);
		bot.oneStepBelt(200, false);
		Delay.msDelay(1500);
		bot.oneStepBelt(200, false);
		Delay.msDelay(1500);
//		bot.oneStepBelt(300, false);
		
	}
//
//	private static void init(Robot bot) {
//		bot.setSensorOnPort(GlobalSensors.colorSensor1, 1);
//		bot.getSensorOnPort(1).setMode("Red");
//		bot.setSensorOnPort(GlobalSensors.colorSensor2, 2);
//		bot.getSensorOnPort(2).setMode("Red");
//	}
}