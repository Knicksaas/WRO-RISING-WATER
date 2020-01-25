package ch.nte.wro.main;

import java.util.ArrayList;
import java.util.List;

import ch.nte.wro.base.Robot;
import ch.nte.wro.base.Sensor;
import ch.nte.wro.variables.GlobalSensors;
import ch.nte.wro.variables.MainVariables;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class Calibrator {
	
	private static String blackIntensity;
	private static String whiteIntetsity;
	private static List<Float> value1 = new ArrayList<Float>();
	private static List<Float> value2 = new ArrayList<Float>();

	public static void main(String[] args) {
		Robot bot = new Robot("calibartor bot", MainVariables.mLeft, MainVariables.mRight);
		bot.setSensorOnPort(GlobalSensors.colorSensor1, 1);
		Sensor colorsensor =  bot.getSensorOnPort(1);
		colorsensor.setMode("red");
		Button.waitForAnyPress();
		Sound.beep();
		for(int i = 0; i < 10; i++) {
			value1.add(mesure(colorsensor));
			Delay.msDelay(500);
		}
		float valueSum1 = 0;
		for(int x = 0; x<value1.size(); x++) {
			valueSum1 = valueSum1 + value1.get(x);
		}
		blackIntensity = String.valueOf(valueSum1/value1.size());
		Sound.beep();
		Delay.msDelay(200);
		Sound.beep();
		Button.waitForAnyPress();
		Sound.beep();
		for(int i = 0; i < 10; i++) {
			value2.add(mesure(colorsensor));
			Delay.msDelay(500);
		}
		float valueSum2 = 0;
		for(int x = 0; x<value1.size(); x++) {
			valueSum2 = valueSum2 + value1.get(x);
		}
		whiteIntetsity = String.valueOf(valueSum2/value2.size());
		Sound.beep();
		Delay.msDelay(200);
		Sound.beep();
		
		LCD.drawString("Black: "+blackIntensity, 0, 0);
		LCD.drawString("White: "+whiteIntetsity, 0, 1);
	}
	
	private static float mesure(Sensor sensor) {
		float[] values = sensor.mesure();
		float worth = values[0];
		return worth;
	}
}
