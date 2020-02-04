package ch.nte.wro.base;

import ch.nte.wro.threds.DetectLineColorThread;
import ch.nte.wro.threds.LightIntensityChecker;
import ch.nte.wro.threds.Timer;
import ch.nte.wro.variables.SensorValues;
import ch.nte.wro.variables.SynchedBoolean;
import ch.nte.wro.variables.SynchedFloat;
import ch.nte.wro.variables.SynchedVariables;
import lejos.hardware.Button;
import lejos.hardware.Sound;


public class Linefollower extends BasicMovment{
	
	private final int finalSpeed;
	private int speed;
	private String mode;
	private int msTime;
	private SynchedFloat sensitivity;
	private SynchedBoolean running = new SynchedBoolean();
	private Sensor sensorLeft;
	private Sensor sensorRight;
	
	public Linefollower(int speed, String mode, int msTime, SynchedFloat sensitivity, Sensor sensorLeft, Sensor sensorRight) {
		this.finalSpeed = speed;
		this.speed = speed;
		this.mode = mode;
		this.msTime = msTime;
		this.sensitivity = sensitivity;
		this.sensorLeft = sensorLeft;
		this.sensorRight = sensorRight;
		if(mode.contains("double")) {
			this.sensorLeft.setMode("Red");
			this.sensorRight.setMode("Red");
			doubleSensor();
		} else if (mode.contains("single")) {
			singleSensor();
		} else {
			System.out.println("Unknown mode!");
			Sound.buzz();
			Button.waitForAnyPress();
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
		} else if (mode.equalsIgnoreCase("double.changeLineColor")) {
			DetectLineColorThread thread = new DetectLineColorThread(running, sensorLeft, sensorRight, 0.008F);
			thread.start();
		}
		float ki = 0;
		float errI = 0;
		float errP;
		float err;
		while(running.get()) {
			if(SynchedVariables.globalSpeed.get() == 0) {
				SynchedVariables.globalSpeed.set(speed);
			}
			speed = SynchedVariables.globalSpeed.get();
			valueSensorLeft = sensorLeft.mesure()[0];
			valueSensorRight = sensorRight.mesure()[0];
			
			errP = valueSensorLeft - valueSensorRight;
			errI = errI + errP*ki;
			err = errP + errI;
			
			if(err < 0) {
				setSpeed(Math.round(speed-(err*sensitivity.get())), "right");
				setSpeed(speed, "left");
			} else {
				setSpeed(Math.round(speed+(err*sensitivity.get())), "left");
				setSpeed(speed, "right");
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
		//TODO: init sensors
		if(mode.contains("single") && mode.contains("time")) {
			if(mode.contains("left")) {
				sensorLeft.setMode("Red");
				sensor = sensorLeft;
			} else if (mode.contains("right")) {
				sensorRight.setMode("Red");
				sensor = sensorRight;
			}
			Timer thread = new Timer(msTime, running);
			thread.start();
		} else if (mode.contains("single") && mode.contains("cross")) {
			LightIntensityChecker thread = null;
			if(mode.contains("left")) {
				sensorLeft.setMode("Red");
				sensor = sensorLeft;
				thread = new LightIntensityChecker(running, sensorLeft, sensorLeft,
						SensorValues.intensityBlack, SensorValues.allowedSensorVariation);
			} else if (mode.contains("right")) {
				sensorRight.setMode("Red");
				sensor = sensorRight;
				thread = new LightIntensityChecker(running, sensorRight, sensorRight,
						SensorValues.intensityBlack, SensorValues.allowedSensorVariation);
			}
			thread.start();
		}
		float ki = 0;
		float errI = 0;
		float errP;
		float err;
		while(running.get()) {
			if(SynchedVariables.globalSpeed.get() == 0) {
				SynchedVariables.globalSpeed.set(speed);
			}
			speed = SynchedVariables.globalSpeed.get();
			valueSensor = sensor.mesure()[0];
			
			errP = valueSensor - SensorValues.targetIntensityLinefollower;
			errI = errI + errP*ki;
			err = errP + errI;
			
			if(mode.contains("left")) {
				if(err > 0) {
					setSpeed(Math.round(speed-(err*sensitivity.get())), "right");
					setSpeed(speed, "left");
				} else {
					setSpeed(Math.round(speed+(err*sensitivity.get())), "left");
					setSpeed(speed, "right");
				}
			} else if (mode.contains("right")) {
				if(err < 0) {
					setSpeed(Math.round(speed-(err*sensitivity.get())), "right");
					setSpeed(speed, "left");
				} else {
					setSpeed(Math.round(speed+(err*sensitivity.get())), "left");
					setSpeed(speed, "right");
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
