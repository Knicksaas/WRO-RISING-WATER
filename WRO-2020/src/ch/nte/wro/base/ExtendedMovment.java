package ch.nte.wro.base;

import ch.nte.wro.threds.AccelerationThred;
import ch.nte.wro.threds.ColorIDChecker;
import ch.nte.wro.threds.ConveyorBeltThread;
import ch.nte.wro.threds.DynamicSensitivity;
import ch.nte.wro.threds.LiftingArmThread;
import ch.nte.wro.threds.LightIntensityChecker;
import ch.nte.wro.threds.RGBValueMeter;
import ch.nte.wro.variables.Status;
import ch.nte.wro.variables.MainVariables;
import ch.nte.wro.variables.Position;
import ch.nte.wro.variables.SensorValues;
import ch.nte.wro.variables.SynchedBoolean;
import ch.nte.wro.variables.SynchedFloat;
import lejos.hardware.Sound;
import lejos.utility.Delay;

public class ExtendedMovment extends BasicMovment{
	

	/*
	 * Accelerates
	 */
	public void accelerate(int speedFrom, int speedTo, int msTime) {
		AccelerationThred thread = new AccelerationThred(speedFrom, speedTo, msTime);
		thread.start();
	}
	
	public void startDynamicSensitivity(float sensitivityFrom, int sensitivityTo, int time, SynchedFloat carrier) {
		DynamicSensitivity thread = new DynamicSensitivity(sensitivityFrom, sensitivityTo, time, carrier);
		thread.start();
	}
	
	/*
	 * MODES:
	 *  - single.left: turn with one motor to the left side
	 *  - singel.right: turn with one motor to the right side
	 *  - double.left: turn with two motors to the left side
	 *  - double.right: turn with two motors to the right side
	 */
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
	
	/*
	 * MODES:
	 *  - double.time: follows the line a given time in milliseconds with two sensors
	 *  - double.cross: follows the line until it detects a black cross with two sensors
	 *  - double.changeLineColor: follows the line until it detects a line color change with two sensors
	 */
	public void followLineRGB(int speed, String mode, int msTime, float sensitivity,
			Sensor sensorLeft, Sensor sensorRight) {
		Delay.msDelay(100);
		SynchedFloat sens = new SynchedFloat();
		sens.set(sensitivity);
		new RGBLinefollower(speed, mode, msTime, sens, sensorLeft, sensorRight);
	}
	
	/*
	 * MODES:
	 *  - double.time: follows the line a given time in milliseconds with two sensors
	 *  - double.cross: follows the line until it detects a black cross with two sensors
	 *  - single.time: follows the line a given time in milliseconds with one sensor
	 *  - single.cross: follows the line until it detects a black cross with one sensor 
	 *  	KEEP IN MIND: (it will detects only branches where are on the same side as the sensor)
	 */
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
	
	public void followLineRGB(int speed, String mode, int msTime, SynchedFloat sensitivity, 
			Sensor sensorLeft, Sensor sensorRight) {
		new RGBLinefollower(speed, mode, msTime, sensitivity, sensorLeft, sensorRight);
	}
	
	public void setArmAngle(int angle, int speed) {
		LiftingArmThread thread = new LiftingArmThread(angle, speed);
		thread.start();
	}
	
	public void setArmAngle(int angle, int speed, int msDelay) {
		LiftingArmThread thread = new LiftingArmThread(angle, speed, msDelay);
		thread.start();
	}
	
	public void setArmAngle(int angle, int speed, boolean immediateReturn) {
		if(immediateReturn) {
			setArmAngle(angle, speed);
		} else {
			SynchedBoolean carrier = new SynchedBoolean();
			carrier.set(true);
			LiftingArmThread thread = new LiftingArmThread(angle, speed, carrier);
			thread.start();
			while (carrier.get()) {}
		}
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
		startSynchronizationOfMotors();
		MainVariables.mLeft.rotate(angle, true);
		MainVariables.mRight.rotate(angle, true);
		endSynchronizationOfMotors();
		MainVariables.mLeft.waitComplete();
		MainVariables.mRight.waitComplete();
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

	public void sandBagPickUp(Sensor sensor) {
		rotate(150, 0.58f);
		String color = ColorGetter.getColor(sensor);
		Delay.msDelay(200);
		Sound.twoBeeps();
		setArmAngle(35, 200);
		Delay.msDelay(1200);
		setArmAngle(175, 300, 230);
		rotate(150, -0.3f);
		Delay.msDelay(1500);
		setArmAngle(0, 600);
		Status.slot1 = (color);
		oneStepBelt(100, true);
		Delay.msDelay(590);
		rotate(150, 0.32f);
		setArmAngle(175, 300, false);
		accelerate(100, 200, 500);
		rotate(100, -0.53f);
		Status.slot1 = (color);
		oneStepBelt(100, true);
		setArmAngle(0, 600);
	}
	
	/*
	 * ROTATIONS ANGLES:
	 * 	- whole turn: 2.05
	 * 	- half turn: 1.025
	 * 	- quarter turn: 0.51
	 */
	public void turnWithRotations(int speed, float rotations, String side) {
		int angle = Math.round(rotations*360.0f);
		if(MainVariables.inverMotorDirections) {
			angle *= -1.0f;
		}
		Delay.msDelay(100);
		setSpeeds(speed);
		if(side.equalsIgnoreCase("right")) {
			Delay.msDelay(10);
			MainVariables.mLeft.rotate(angle, true);
			MainVariables.mRight.rotate(angle*(-1), true);
			MainVariables.mLeft.waitComplete();
			MainVariables.mRight.waitComplete();
		} else if (side.equalsIgnoreCase("left")) {
			Delay.msDelay(10);
			MainVariables.mRight.rotate(angle, true);
			MainVariables.mLeft.rotate(angle*(-1), true);
			MainVariables.mLeft.waitComplete();
			MainVariables.mRight.waitComplete();
		}
		Delay.msDelay(100);
	}
	
	public void turnWithRotationsOld(int speed, float rotations, String side) {
		int angle = Math.round(rotations*360.0f);
		if(MainVariables.inverMotorDirections) {
			angle *= -1.0f;
		}
		Delay.msDelay(100);
		setSpeeds(speed);
		if(side.equalsIgnoreCase("right")) {
			Delay.msDelay(10);
			startSynchronizationOfMotors();
			MainVariables.mLeft.rotate(angle, true);
			MainVariables.mRight.rotate(angle*(-1), true);
			endSynchronizationOfMotors();
			MainVariables.mLeft.waitComplete();
			MainVariables.mRight.waitComplete();
		} else if (side.equalsIgnoreCase("left")) {
			Delay.msDelay(10);
			startSynchronizationOfMotors();
			MainVariables.mRight.rotate(angle, true);
			MainVariables.mLeft.rotate(angle*(-1), true);
			endSynchronizationOfMotors();
			MainVariables.mLeft.waitComplete();
			MainVariables.mRight.waitComplete();
		}
		Delay.msDelay(100);
	}
	
	/*
	 * MODES:
	 * 	- quarter
	 * 	- half
	 * 	- full
	 */
	public void turnWithRotationsOld(int speed, String mode, String side) {
		
		float turn;
		if(mode.contains("quarter")) {
			if(side.contains("left")) {
				turn = 0.53f;				
			} else {
				turn = 0.545f;
			}				
			turnWithRotations(speed, turn, side);
			fixTurn(speed, side);
		} else if (mode.contains("half")) {
			if(side.contains("left")) {
				turn = 1.058f;				
			} else {
				turn = 1.065f;
			}				
			turnWithRotations(speed, turn, side);
			fixTurn(speed, side);
		} else if (mode.contains("full")) {
			turnWithRotations(speed, 2.05f, side);
			fixTurn(speed, side);
		}
		
	}
	
	public void turnWithRotations(int speed, String mode, String side) {
		if(mode.contains("quarter")) {
			turnWithRotationsOld(speed, 0.53f, side);
			fixTurn(speed, side);
		} else if (mode.contains("half")) {
			turnWithRotationsOld(speed, 1.035f, "right");
			fixTurn(speed, side);
		} else if (mode.contains("full")) {
			turnWithRotationsOld(speed, 2.05f, side);
			fixTurn(speed, side);
		}
		
	}
	
	public void driveToLineMiddle(int speed, Sensor sensorLeft, Sensor sensorRight) {
		followLineRGB(speed, "double.cross", 0, SensorValues.sensitivity.get(Position.botPosition),
				sensorLeft, sensorRight);
		accelerate(1, speed, 500);
		rotate(speed, -0.64f);
	}
	
	public void adjustBot(int speed, Sensor sensorLeft, Sensor sensorRight) {
		RGBValueMeter threadLeft = new RGBValueMeter(sensorLeft, sensorRight);
		threadLeft.start();
		turnWithRotations(speed, 0.1f, "left");
		threadLeft.end();
		float lowestValueLeft = threadLeft.getLowestValue();
		turnWithRotations(speed, 0.1f, "right");
		RGBValueMeter threadRight = new RGBValueMeter(sensorLeft, sensorRight);
		threadRight.start();
		turnWithRotations(speed, 0.1f, "right");
		threadRight.end();
		float lowestValueRight = threadRight.getLowestValue();
		turnWithRotations(speed, 0.1f, "left");
		float diff = 0.03f;
		if(lowestValueLeft<lowestValueRight) {
			Sound.beep();
			turnAtPlace(speed, "left");
			while (ValueGetter.getAverageSensorValues(sensorLeft, sensorRight)-diff>lowestValueLeft) {
			}
		} else {
			Sound.beep();
			Sound.beep();
			turnAtPlace(speed, "right");
			while (ValueGetter.getAverageSensorValues(sensorLeft, sensorRight)-diff>lowestValueRight) {
			}
		}
	}
	
	public void adjustRobot(int speed, Sensor sensorLeft, Sensor sensorRight) {
		turnAtPlace(speed, "right");
		float highestValueRight = 2;
		float hightesValueLeft = 2;
		while(highestValueRight > ValueGetter.getAverageSensorValues(sensorLeft, sensorRight)){
			highestValueRight = ValueGetter.getAverageSensorValues(sensorLeft, sensorRight);
			Delay.msDelay(50);
		}
		turnAtPlace(speed, "left");
		while (hightesValueLeft > ValueGetter.getAverageSensorValues(sensorLeft, sensorRight)) {
			hightesValueLeft = ValueGetter.getAverageSensorValues(sensorLeft, sensorRight);
			Delay.msDelay(50);
		}
		if(hightesValueLeft > highestValueRight) {
			turnAtPlace(speed, "right");
			while(highestValueRight < ValueGetter.getAverageSensorValues(sensorLeft, sensorRight)){
				Delay.msDelay(50);
			}
		}
	}
	
	public void fixTurn(int speed, String side) {
		float modifyer = 0.01f;
		turnWithRotations(speed, modifyer, side);
			
	}
	
	public void fixTurnOld(int speed, String side) {
		float modifyer = 0.01f;
		if(side.equalsIgnoreCase("right")) {
			turnWithRotations(speed, modifyer, "left");
			Sound.beep();
		} else if(side.equalsIgnoreCase("left")){
			turnWithRotations(speed, modifyer, "right");
			Sound.buzz();
		}else {
			Sound.buzz();
			Delay.msDelay(190);
			Sound.buzz();
		}
			
	}
}
