package ch.nte.wro.test;

import ch.nte.wro.base.Robot;
import ch.nte.wro.variables.GlobalSensors;
import ch.nte.wro.variables.MainVariables;

public class SoundTester {

	public static void main(String[] args) {
		Robot bot = new Robot("tester", MainVariables.mLeft, MainVariables.mRight);
		bot.setSensorOnPort(GlobalSensors.colorSensor1, 1);
		bot.setSensorOnPort(GlobalSensors.colorSensor2, 2);
		bot.followLine(150, "double.time", 10000, 600, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
	}
}
