package ch.nte.wro.test;

import ch.nte.wro.base.Robot;
import ch.nte.wro.variables.GlobalSensors;
import ch.nte.wro.variables.MainVariables;

public class Speedtest {
	
	public static int speed = 200;
	
	public static void main(String[] args) {
		Robot bot = new Robot("test", MainVariables.mLeft, MainVariables.mRight);
		init(bot);
		bot.rotate(300, 1, "right");
	}

	private static void init(Robot bot) {
		bot.setSensorOnPort(GlobalSensors.colorSensor1, 1);
		bot.getSensorOnPort(1).setMode("Red");
		bot.setSensorOnPort(GlobalSensors.colorSensor2, 2);
		bot.getSensorOnPort(2).setMode("Red");
	}
}