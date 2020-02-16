package ch.nte.wro.test;

import ch.nte.wro.base.Robot;
import ch.nte.wro.variables.GlobalSensors;
import ch.nte.wro.variables.MainVariables;

public class LinefollowerTester {

	public static void main(String[] args) {
		Robot bot = new Robot("tester", MainVariables.mLeft, MainVariables.mRight);
		bot.setSensorOnPort(GlobalSensors.colorSensor1, 1);
		bot.setSensorOnPort(GlobalSensors.colorSensor2, 2);
		bot.followLineRGB(400, "double.cross", 0, 50, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		bot.followLineRGB(400, "double.cross", 0, 50, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		bot.turnWithRotations(100, 1, "right");
		bot.followLineRGB(400, "double.cross", 0, 50, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		bot.followLineRGB(400, "double.cross", 0, 50, bot.getSensorOnPort(1), bot.getSensorOnPort(2));bot.turnWithRotations(100, 1, "right");
		bot.followLineRGB(400, "double.cross", 0, 50, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		bot.followLineRGB(400, "double.cross", 0, 50, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
	}
}
