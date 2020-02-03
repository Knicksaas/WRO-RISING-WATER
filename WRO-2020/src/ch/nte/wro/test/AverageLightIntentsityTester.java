package ch.nte.wro.test;

import ch.nte.wro.base.Robot;
import ch.nte.wro.variables.GlobalSensors;
import ch.nte.wro.variables.MainVariables;
import ch.nte.wro.variables.SensorValues;
import lejos.hardware.Sound;

public class AverageLightIntentsityTester {

	public static void main(String[] args) {
		Robot bot = new Robot("test", MainVariables.mLeft, MainVariables.mRight);
		bot.setSensorOnPort(GlobalSensors.colorSensor1, 1);
		bot.setSensorOnPort(GlobalSensors.colorSensor2, 2);
		bot.forwardUntil(300, bot.getSensorOnPort(1), bot.getSensorOnPort(2),
				SensorValues.intensityWhite, SensorValues.allowedSensorVariation*20);
		Sound.beep();
	}
}
