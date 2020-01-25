package ch.nte.wro.base;

import ch.nte.wro.threds.Timer;
import ch.nte.wro.variables.TempVariables;
import lejos.hardware.Sound;

public class Linefollower extends BasicMovment{

	private int speed;
	private String mode;
	private int msTime;
	private int sensitivity;
	private boolean running = false;
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
		float valueSensorLeft;
		float valueSensorRight;
		if(TempVariables.globalSpeed == 0) {
			TempVariables.globalSpeed = speed;
		}
		forward(TempVariables.globalSpeed);
		if(mode.equalsIgnoreCase("double.time")) {
			Timer thread = new Timer(msTime, running);
			thread.run();
		}
		while(running) {
			speed = TempVariables.globalSpeed;
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
		return running;
	}
	
	public void abort() {
		running = false;
	}
}
