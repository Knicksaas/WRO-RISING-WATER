package ch.nte.wro.variables;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorModes;

public class GlobalSensors {

	public static SensorModes colorSensor1 = new EV3ColorSensor(LocalEV3.get().getPort("S1"));
}
