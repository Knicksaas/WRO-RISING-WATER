package ch.nte.wro.base;

import ch.nte.wro.threds.AccelerationThred;
import ch.nte.wro.threds.ColorIDChecker;
import ch.nte.wro.threds.ConveyorBeltThread;
import ch.nte.wro.threds.DynamicSensitivity;
import ch.nte.wro.threds.LiftingArmThread;
import ch.nte.wro.threds.LightIntensityChecker;
import ch.nte.wro.variables.ConveyorbeltStatus;
import ch.nte.wro.variables.MainVariables;
import ch.nte.wro.variables.SynchedBoolean;
import ch.nte.wro.variables.SynchedFloat;
import lejos.utility.Delay;

public class ExtendedMovment extends BasicMovment{

	public void accelerate(int speedFrom, int speedTo, int msTime) {
		AccelerationThred thread = new AccelerationThred(speedFrom, speedTo, msTime);
		thread.start();
	}
	
	public void startDynamicSensitivity(float sensitivityFrom, int sensitivityTo, int time, SynchedFloat carrier) {
		DynamicSensitivity thread = new DynamicSensitivity(sensitivityFrom, sensitivityTo, time, carrier);
		thread.start();
	}
	
	public void turnToLine(int speed, String mode, Sensor sensor, float lineIntensity, float diff) {
		if(mode.contains("single")) {
			if(mode.contains("right")) {
				motorOn(speed, "left", true);
			} else if (mode.contains("left")) {
				motorOn(speed, "right", true);
			}
		} else if (mode.contains("double")) {
			if(mode.contains("right")) {
				turnAtPlace(speed, "left");
			} else if (mode.contains("left")) {
				turnAtPlace(speed, "right");
			}
		}
		sensor.setMode("Red");
		float value = sensor.mesure()[0];
		while(true) {
			value = sensor.mesure()[0];
			if(value > lineIntensity-diff &&
				value < lineIntensity+diff) {
				return;
			}
		}
	}
	
	public void followLine(int speed, String mode, int msTime, float sensitivity, 
		Sensor sensorLeft, Sensor sensorRight) {
		Delay.msDelay(100);
		SynchedFloat sens = new SynchedFloat();
		sens.set(sensitivity);
		new Linefollower(speed, mode, msTime, sens, sensorLeft, sensorRight);
	}
	
	public void followLine(int speed, String mode, int msTime, SynchedFloat sensitivity, 
			Sensor sensorLeft, Sensor sensorRight) {
		new Linefollower(speed, mode, msTime, sensitivity, sensorLeft, sensorRight);
	}
	
	public void setArmAngle(int angle, int speed) {
		LiftingArmThread thread = new LiftingArmThread(angle, speed);
		thread.start();
	}

		
	
	
	public int getArmAngle() {
		return MainVariables.armAngle;
	}
	
	public void oneStepBelt(int speed, boolean forward) {
		ConveyorBeltThread thread = new ConveyorBeltThread(speed, forward);
		thread.start();
	}
	
	public void rotate(int speed, float rotations, String side) {
		int angle = Math.round(rotations*360);
		if(side.equalsIgnoreCase("left")) {
			if(MainVariables.inverMotorDirections) {
				angle *= -1;
			}
			setSpeed(speed, "left");
			Delay.msDelay(50);
			MainVariables.mLeft.rotate(angle);
			Delay.msDelay(50);
		} else if (side.equalsIgnoreCase("right")) {
			if(MainVariables.inverMotorDirections) {
				angle *= -1;
			}
			setSpeed(speed, "right");
			Delay.msDelay(50);
			MainVariables.mRight.rotate(angle);
			Delay.msDelay(50);
		}
	}
	public void rotate(int speed, float rotations) {
		int angle = Math.round(rotations*360);
		if(MainVariables.inverMotorDirections) {
			angle *= -1;
		}
		setSpeeds(speed);
		Delay.msDelay(10);
		MainVariables.mLeft.rotate(angle, true);
		Delay.msDelay(10);
		MainVariables.mRight.rotate(angle);
		Delay.msDelay(10);
	}
	
	public void forwardUntil(int speed, Sensor sensorLeft, Sensor sensorRight, float targetValue, float diff) {
		SynchedBoolean running = new SynchedBoolean();
		running.set(true);
		forward(speed);
		LightIntensityChecker thread = new LightIntensityChecker(running, sensorLeft, sensorRight,
				targetValue, diff);
		thread.start();
		while (true) {
			if(!running.get()) {
				return;
			}
		}
	}
	public void forwardUntil(int speed, Sensor sensor, int colorID, float diff) {
		SynchedBoolean running = new SynchedBoolean();
		running.set(true);
		forward(speed);
		ColorIDChecker thread = new ColorIDChecker(running, sensor, colorID);
		thread.start();
		while (true) {
			if(!running.get()) {
				return;
			}
		}
	}

	public void sandBagPickUp(int speed, Sensor sensor) {
		
		rotate(200, 0.52f);
		String color = ColorGetter.getColor(sensor);
		setArmAngle(25, 50);
		Delay.msDelay(1500);
		setArmAngle(200, 100);
		rotate(150, -0.3f);
		Delay.msDelay(2000);
		setArmAngle(0, 200);
		ConveyorbeltStatus.slot1 = (color + "SandBag");
		oneStepBelt(100, true);
		Delay.msDelay(590);
		rotate(speed, 0.3f);
		setArmAngle(200, 100);
		Delay.msDelay(3000);
		ConveyorbeltStatus.slot1 = (color + "SandBag");
		setArmAngle(0, 300);
		rotate(200, -0.5f); 
		
		
		
		
		return;
	}
	public void turnWithRotations(int speed, float rotations, String side) {
		int angle = Math.round(rotations*360);
		if(MainVariables.inverMotorDirections) {
			angle *= -1;
		}
		Delay.msDelay(100);
		setSpeeds(speed);
		if(side.equalsIgnoreCase("left")) {
			Delay.msDelay(10);
			MainVariables.mLeft.rotate(angle, true);
			Delay.msDelay(10);
			MainVariables.mRight.rotate(-angle);
			Delay.msDelay(10);
		} else if (side.equalsIgnoreCase("right")) {
			Delay.msDelay(10);
			MainVariables.mRight.rotate(angle, true);
			Delay.msDelay(10);
			MainVariables.mLeft.rotate(-angle);
			Delay.msDelay(10);
		}
		Delay.msDelay(100);
	}
}
