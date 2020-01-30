package ch.nte.wro.test;

import ch.nte.wro.base.Robot;
import ch.nte.wro.base.Sensor;
import ch.nte.wro.variables.GlobalSensors;
import ch.nte.wro.variables.MainVariables;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class LightIntensityTester {

	public static void main(String[] args) {
		
		Robot bot = new Robot("testbot", MainVariables.mLeft, MainVariables.mRight);
		bot.setSensorOnPort(GlobalSensors.colorSensor1, 1);
		bot.setSensorOnPort(GlobalSensors.colorSensor2, 2);
		
		Sensor sensorLeft; 
		Sensor sensorRight;
//		sensorLeft = new Sensor(GlobalSensors.colorSensor1);
//		sensorRight = new Sensor(GlobalSensors.colorSensor2);
		sensorLeft = bot.getSensorOnPort(1);
		sensorRight = bot.getSensorOnPort(2);
		sensorLeft.setMode("Red");
		sensorRight.setMode("Red");
		Float value1;
		Float value2;
		while(true) {
			value1 = sensorLeft.mesure()[0];
			value2 = sensorRight.mesure()[0];
			LCD.drawString("left: " + value1.toString(), 0, 0);
			LCD.drawString("right: " + value2.toString(), 0, 1);
			Delay.msDelay(200);
		}
	}

}
