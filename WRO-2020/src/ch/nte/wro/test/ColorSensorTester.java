package ch.nte.wro.test;

import ch.nte.wro.base.Robot;
import ch.nte.wro.variables.GlobalSensors;
import ch.nte.wro.variables.MainVariables;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class ColorSensorTester {

	public static void main(String[] args) {
		Robot bot = new Robot("colorTester", MainVariables.mLeft, MainVariables.mRight);
		bot.setSensorOnPort(GlobalSensors.hiTechnicsColorSensor, 3);
		bot.getSensorOnPort(3).setMode("RGB");
		while(true) {
			LCD.drawString("Red: " + String.valueOf(bot.getSensorOnPort(3).mesure()[0]), 0, 0);
			LCD.drawString("Green: " + String.valueOf(bot.getSensorOnPort(3).mesure()[1]), 0, 1);
			LCD.drawString("Blue: " + String.valueOf(bot.getSensorOnPort(3).mesure()[2]), 0, 2);
			Delay.msDelay(100);
		}
	}
}
