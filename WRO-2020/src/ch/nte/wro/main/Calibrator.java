package ch.nte.wro.main;

import java.util.ArrayList;
import java.util.List;

import ch.nte.wro.base.RGBValue;
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
	private static RGBValue valueBlack;

	public static void main(String[] args) {
		Robot bot = new Robot("calibartor bot", MainVariables.mLeft, MainVariables.mRight);
		bot.setSensorOnPort(GlobalSensors.colorSensor1, 1);
		Sensor colorsensor =  bot.getSensorOnPort(1);
		Button.waitForAnyPress();
		Sound.beep();
		blackIntensity = getNewIntensity(colorsensor);
		Sound.beep();
		Sound.beep();
		valueBlack = getNewRGBValue(colorsensor);
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
		LCD.drawString("black: "+blackIntensity.toString(), 0, 0);
		LCD.drawString(getMsg(valueBlack), 0, 1);
		LCD.drawString("white: "+whiteIntetsity.toString(), 0, 2);
		LCD.drawString("blue: "+blueIntensity.toString(), 0, 3);
		LCD.drawString("green: "+greenIntensity.toString(), 0, 4);
		LCD.drawString("yellow: "+yellowIntensity.toString(), 0, 5);
		LCD.drawString("red: "+redIntensity.toString(), 0, 6);
		Button.waitForAnyPress();
	}
	
	private static float getNewIntensity(Sensor sensor) {
		sensor.setMode("Red");
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
	
	private static RGBValue getNewRGBValue(Sensor sensor) {
		sensor.setMode("RGB");
		List<RGBValue> mesurements = new ArrayList<>();
		for(int i = 0; i<10; i++) {
			mesurements.add(new RGBValue(sensor.mesure()));
			Delay.msDelay(200);
		}
		float valueRed = 0;
		float valueGreen = 0;
		float valueBlue = 0;
		for(int x = 0; x<mesurements.size(); x++) {
			valueRed = valueRed+mesurements.get(x).getRed();
			valueGreen = valueRed+mesurements.get(x).getGreen();
			valueBlue = valueRed+mesurements.get(x).getBlue();
		}
		return new RGBValue(valueRed/mesurements.size(), valueGreen/mesurements.size(), valueBlue/mesurements.size());
	}
	
	private static String getMsg(RGBValue value) {
		return "RGB: " + String.valueOf(value.getRed()) + " " + String.valueOf(value.getGreen()) + " " + String.valueOf(value.getBlue());
	}
}
