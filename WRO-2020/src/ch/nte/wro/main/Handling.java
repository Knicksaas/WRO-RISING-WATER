package ch.nte.wro.main;

import ch.nte.wro.base.Robot;
import ch.nte.wro.variables.SensorValues;

public class Handling {

	public static void unloadEvacuationRequest(Robot bot, int speed, float lineIntensity) {
		bot.turnToLine(speed, "double.right", bot.getSensorOnPort(2), lineIntensity, SensorValues.allowedSensorVariation);
		bot.backward(speed);
	}
}
