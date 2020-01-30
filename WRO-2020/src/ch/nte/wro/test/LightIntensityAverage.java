package ch.nte.wro.test;

import ch.nte.wro.base.Sensor;
import ch.nte.wro.variables.GlobalSensors;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class LightIntensityAverage {

	public static void main(String[] args) {
		Sensor sensorLeft = new Sensor(GlobalSensors.colorSensor1);
		Sensor sensorRight = new Sensor(GlobalSensors.colorSensor2);
		sensorLeft.setMode("Red");
		sensorRight.setMode("Red");
		Float average = (sensorLeft.mesure()[0]+sensorRight.mesure()[0])/2;
		while(true) {
			LCD.drawString("average: " + average.toString(), 0, 0);
			Delay.msDelay(200);
		}
	}

}
