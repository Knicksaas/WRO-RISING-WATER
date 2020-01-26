package ch.nte.wro.base;

import ch.nte.wro.threds.AccelerationThred;
import ch.nte.wro.variables.SensorValues;

public class ExtendedMovment extends BasicMovment{

	public void accelerate(int speedFrom, int speedTo, int msTime) {
		AccelerationThred thread = new AccelerationThred(speedFrom, speedTo, msTime);
		thread.start();
	}
	
	public void turnToLine(int speed, String mode, Sensor sensor) {
		if(mode.contains("single")) {
			if(mode.contains("right")) {
				motorOn(speed, "right");
			} else if (mode.contains("left")) {
				motorOn(speed, "left");
			}
		} else if (mode.contains("double")) {
			if(mode.contains("right")) {
				turnAtPlace(speed, "right");
			} else if (mode.contains("left")) {
				turnAtPlace(speed, "left");
			}
		}
		sensor.setMode("red");
		float value = sensor.mesure()[0];
		while(value > SensorValues.intensityBlack-SensorValues.allowedSensorVariation &&
				value < SensorValues.intensityBlack+SensorValues.allowedSensorVariation) {
			value = sensor.mesure()[0];
		}
	}
}
