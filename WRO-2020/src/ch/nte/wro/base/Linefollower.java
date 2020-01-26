package ch.nte.wro.base;

import ch.nte.wro.threds.LightIntensityChecker;
import ch.nte.wro.threds.Timer;
import ch.nte.wro.variables.SensorValues;
import ch.nte.wro.variables.SynchedBoolean;
import ch.nte.wro.variables.SynchedVariables;
import lejos.hardware.Sound;

public class Linefollower extends BasicMovment{

	private int speed;
	private String mode;
	private int msTime;
	private int sensitivity;
	private SynchedBoolean running;
	private Sensor sensorLeft;
	private Sensor sensorRight;
	
	public Linefollower(int speed, String mode, int msTime, int sensitivity, Sensor sensorLeft, Sensor sensorRight) {
		this.speed = speed;
		this.mode = mode;
		this.msTime = msTime;
		this.sensitivity = sensitivity;
		this.sensorLeft = sensorLeft;
		this.sensorRight = sensorRight;
		if(mode.contains("double")) {
			this.sensorLeft.setMode("red");
			this.sensorRight.setMode("red");
			doubleSensor();
		} else if (mode.contains("singel")) {
			singleSensor();
		} else {
			System.out.println("Unknown mode!");
			Sound.buzz();
		}
	}
	
	private void doubleSensor() {
		running.set(true);
		float valueSensorLeft;
		float valueSensorRight;
		if(SynchedVariables.globalSpeed.get() == 0) {
			SynchedVariables.globalSpeed.set(speed);
		}
		forward(SynchedVariables.globalSpeed.get());
		if(mode.equalsIgnoreCase("double.time")) {
			if(msTime < 1) {
				return;
			}
			Timer thread = new Timer(msTime, running);
			thread.run();
		} else if (mode.equalsIgnoreCase("double.cross")) {
			LightIntensityChecker thread = new LightIntensityChecker(running, sensorLeft, sensorRight, 
					SensorValues.intensityBlack, SensorValues.allowedSensorVariation);
			thread.run();
		}
		while(running.get()) {
			speed = SynchedVariables.globalSpeed.get();
			valueSensorLeft = sensorLeft.mesure()[0];
			valueSensorRight = sensorRight.mesure()[0];
			if(valueSensorLeft < valueSensorRight) {
				setSpeed(speed, "left");
				setSpeed(Math.round((valueSensorRight - valueSensorLeft)*sensitivity+speed), "right");
			} else if(valueSensorLeft > valueSensorRight) {
				setSpeed(speed, "right");
				setSpeed(Math.round((valueSensorRight - valueSensorLeft)*sensitivity+speed), "left");
			}
		}
	}
	
	private void singleSensor() {
		//TODO: init sensors
	}
	
	public boolean isRunning() {
		return running.get();
	}
	
	public void abort() {
		running.set(false);
	}
}
