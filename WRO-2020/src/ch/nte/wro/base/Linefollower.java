package ch.nte.wro.base;

import ch.nte.wro.threds.LightIntensityChecker;
import ch.nte.wro.threds.Timer;
import ch.nte.wro.variables.SensorValues;
import ch.nte.wro.variables.SynchedBoolean;
import ch.nte.wro.variables.SynchedVariables;
import lejos.hardware.Sound;

public class Linefollower extends BasicMovment{
	
	private final int finalSpeed;
	private int speed;
	private String mode;
	private int msTime;
	private int sensitivity;
	private SynchedBoolean running;
	private Sensor sensorLeft;
	private Sensor sensorRight;
	
	public Linefollower(int speed, String mode, int msTime, int sensitivity, Sensor sensorLeft, Sensor sensorRight) {
		this.finalSpeed = speed;
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
		} else if (mode.contains("single")) {
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
			SynchedVariables.globalSpeed.set(finalSpeed);
		}
		forward(SynchedVariables.globalSpeed.get());
		if(mode.equalsIgnoreCase("double.time")) {
			if(msTime < 1) {
				return;
			}
			Timer thread = new Timer(msTime, running);
			thread.start();
		} else if (mode.equalsIgnoreCase("double.cross")) {
			LightIntensityChecker thread = new LightIntensityChecker(running, sensorLeft, sensorRight, 
					SensorValues.intensityBlack, SensorValues.allowedSensorVariation);
			thread.start();
		} else if (mode.equalsIgnoreCase("double.halfcross")) {
			LightIntensityChecker thread = new LightIntensityChecker(running, sensorLeft, sensorRight,
					SensorValues.averageIntensityHalfCross, SensorValues.allowedSensorVariation);
			thread.start();
		}
		while(running.get()) {
			if(SynchedVariables.globalSpeed.get() == 0) {
				SynchedVariables.globalSpeed.set(speed);
			}
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
		running.set(true);
		if(SynchedVariables.globalSpeed.get() == 0) {
			SynchedVariables.globalSpeed.set(finalSpeed);
		}
		float valueSensor;
		Sensor sensor = null;
		float targetIntensity = (SensorValues.intensityBlack+SensorValues.intensityWhite)/2;
		//TODO: init sensors
		if(mode.contains("single") && mode.contains("time")) {
			if(mode.contains("left")) {
				sensorLeft.setMode("red");
				sensor = sensorLeft;
			} else if (mode.contains("right")) {
				sensorRight.setMode("red");
				sensor = sensorRight;
			}
			Timer thread = new Timer(msTime, running);
			thread.start();
		} else if (mode.contains("single") && mode.contains("cross")) {
			LightIntensityChecker thread = null;
			if(mode.contains("left")) {
				sensorLeft.setMode("red");
				sensor = sensorLeft;
				thread = new LightIntensityChecker(running, sensorLeft, sensorLeft,
						SensorValues.intensityBlack, SensorValues.allowedSensorVariation);
			} else if (mode.contains("right")) {
				sensorRight.setMode("red");
				sensor = sensorRight;
				thread = new LightIntensityChecker(running, sensorRight, sensorRight,
						SensorValues.intensityBlack, SensorValues.allowedSensorVariation);
			}
			thread.start();
		}
		while(running.get()) {
			if(SynchedVariables.globalSpeed.get() == 0) {
				SynchedVariables.globalSpeed.set(finalSpeed);
			}
			speed = SynchedVariables.globalSpeed.get();
			valueSensor = sensor.mesure()[0];
			if(valueSensor < targetIntensity) {
				if(mode.contains("left")){
					setSpeed(speed, "left");
					setSpeed((int) Math.round(0.1*sensitivity), "right");
				} else if (mode.contains("right")) {
					setSpeed(speed, "right");
					setSpeed((int) Math.round(0.1*sensitivity), "left");
				}
			} else if (valueSensor > targetIntensity) {
				if(mode.contains("right")){
					setSpeed(speed, "left");
					setSpeed((int) Math.round(0.1*sensitivity), "right");
				} else if (mode.contains("left")) {
					setSpeed(speed, "right");
					setSpeed((int) Math.round(0.1*sensitivity), "left");
				}
			}
		}
	}
	
	public boolean isRunning() {
		return running.get();
	}
	
	public void abort() {
		running.set(false);
	}
}
