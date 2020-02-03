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
	
	private static Float blackIntensity;
	private static Float whiteIntetsity;
	private static Float blueIntensity;
	private static Float greenIntensity;
	private static Float yellowIntensity;
	private static Float redIntensity;

	public static void main(String[] args) {
		Robot bot = new Robot("calibartor bot", MainVariables.mLeft, MainVariables.mRight);
		bot.setSensorOnPort(GlobalSensors.colorSensor1, 1);
		Sensor colorsensor =  bot.getSensorOnPort(1);
		colorsensor.setMode("Red");
		Button.waitForAnyPress();
		Sound.beep();
		blackIntensity = getNewIntensity(colorsensor);
		Sound.beep();
		Button.waitForAnyPress();
		whiteIntetsity = getNewIntensity(colorsensor);
		Sound.beep();
		Button.waitForAnyPress();
		blueIntensity = getNewIntensity(colorsensor);
		Sound.beep();
		Button.waitForAnyPress();
		greenIntensity = getNewIntensity(colorsensor);
		Sound.beep();
		Button.waitForAnyPress();
		yellowIntensity = getNewIntensity(colorsensor);
		Sound.beep();
		Button.waitForAnyPress();
		redIntensity = getNewIntensity(colorsensor);
		Sound.beep();
		LCD.drawString("black: "+blackIntensity.toString(), 0, 0);
		LCD.drawString("white: "+whiteIntetsity.toString(), 0, 1);
		LCD.drawString("blue: "+blueIntensity.toString(), 0, 2);
		LCD.drawString("green: "+greenIntensity.toString(), 0, 3);
		LCD.drawString("yellow: "+yellowIntensity.toString(), 0, 4);
		LCD.drawString("red: "+redIntensity.toString(), 0, 5);
		Button.waitForAnyPress();
	}
	
	private static float getNewIntensity(Sensor sensor) {
		List<Float> mesurements = new ArrayList<Float>();
		for(int i = 0; i<10; i++) {
			mesurements.add(sensor.mesure()[0]);
			Delay.msDelay(200);
		}
		float value = 0;
		for(int i = 0; i<mesurements.size(); i++) {
			value = value+mesurements.get(i);
		}
		return value/mesurements.size();
	}
}
