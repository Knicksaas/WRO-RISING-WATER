package ch.nte.wro.main;

import ch.nte.wro.base.Robot;
import ch.nte.wro.variables.GlobalSensors;
import ch.nte.wro.variables.SensorValues;

public class Initsialization {

	public static void sensorInit(Robot bot) {
		bot.setSensorOnPort(GlobalSensors.colorSensor1, 1);
		bot.getSensorOnPort(1).setMode("Red");
		bot.setSensorOnPort(GlobalSensors.colorSensor2, 2);
		bot.getSensorOnPort(2).setMode("Red");
		bot.setSensorOnPort(GlobalSensors.hiTechnicsColorSensor, 3);
		bot.getSensorOnPort(3).setMode("RGB");
	}
	
	public static void sensitivityInit() {
		SensorValues.sensitivity.put("yellow", 300);
		SensorValues.sensitivity.put("blue", 50);
		SensorValues.sensitivity.put("green", 50);
		SensorValues.sensitivity.put("red", 150);
	}
}
