package ch.nte.wro.base;

import ch.nte.wro.threds.RGBChecker;
import ch.nte.wro.threds.Timer;
import ch.nte.wro.variables.SensorValues;
import ch.nte.wro.variables.SynchedBoolean;
import ch.nte.wro.variables.SynchedFloat;
import ch.nte.wro.variables.SynchedVariables;
import lejos.hardware.Button;
import lejos.hardware.Sound;


public class RGBLinefollower extends BasicMovment{
	
	private final int finalSpeed;
	private int speed;
	private String mode;
	private int msTime;
	private SynchedFloat sensitivity;
	private SynchedBoolean running = new SynchedBoolean();
	private Sensor sensorLeft;
	private Sensor sensorRight;
	
	public RGBLinefollower(int speed, String mode, int msTime, SynchedFloat sensitivity, Sensor sensorLeft, Sensor sensorRight) {
		this.finalSpeed = speed;
		this.speed = speed;
		this.mode = mode;
		this.msTime = msTime;
		this.sensitivity = sensitivity;
		this.sensorLeft = sensorLeft;
		this.sensorRight = sensorRight;
		if(mode.contains("double")) {
			this.sensorLeft.setMode("RGB");
			this.sensorRight.setMode("RGB");
			doubleSensor();
		} else {
			System.out.println("Unknown mode!");
			Sound.buzz();
			Button.waitForAnyPress();
		}
	}
	
	private void doubleSensor() {
		running.set(true);
		float valueLeft;
		float valueRight;
		float err;
		if(SynchedVariables.globalSpeed.get() == 0) {
			SynchedVariables.globalSpeed.set(finalSpeed);
		}
		forward(SynchedVariables.globalSpeed.get());
		
//		Modes
		if(mode.equalsIgnoreCase("double.time")) {
			if(msTime < 1) {
				return;
			}
			Timer thread = new Timer(msTime, running);
			thread.start();
		} else if (mode.equalsIgnoreCase("double.cross")) {
			RGBChecker thread = new RGBChecker(running, sensorLeft, sensorRight, SensorValues.valueBlack, SensorValues.allowedRGBVariation);
			thread.start();
		}
		
		while(running.get()) {
			if(SynchedVariables.globalSpeed.get() == 0) {
				SynchedVariables.globalSpeed.set(speed);
			}
			speed = SynchedVariables.globalSpeed.get();
			
//			Error calculation
			valueLeft = sensorLeft.mesure()[0] + sensorLeft.mesure()[1] + sensorLeft.mesure()[2];
			valueRight = sensorRight.mesure()[0] + sensorRight.mesure()[1] + sensorRight.mesure()[2];
			
			err = valueLeft-valueRight;
			
			if(err < 0) {
				setSpeed(Math.round(speed-(err*sensitivity.get())), "right");
				setSpeed(speed, "left");
			} else {
				setSpeed(Math.round(speed+(err*sensitivity.get())), "left");
				setSpeed(speed, "right");
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
