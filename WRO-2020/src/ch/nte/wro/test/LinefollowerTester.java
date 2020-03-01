package ch.nte.wro.test;

import ch.nte.wro.base.Robot;
import ch.nte.wro.main.Initsialization;
import ch.nte.wro.variables.MainVariables;
import ch.nte.wro.variables.Position;
import ch.nte.wro.variables.SensorValues;

public class LinefollowerTester {
	
	public static int speed = 200;

	public static void main(String[] args) {
		Robot bot = new Robot("tester", MainVariables.mLeft, MainVariables.mRight);
		Initsialization.sensorInit(bot);
		Initsialization.sensitivityInit();
		Initsialization.synchInit();
		Position.botPosition = "green";
		while (true) {
			bot.followLineRGB(speed, "double.cross", 0, SensorValues.sensitivity.get(Position.botPosition),
					bot.getSensorOnPort(1), bot.getSensorOnPort(2));
			bot.turnWithRotations(speed, "half", "right");
			bot.fixTurn(speed, "right");
		}
	}
}
