package ch.nte.wro.base;

import ch.nte.wro.main.Initsialization;
import ch.nte.wro.variables.MainVariables;
import lejos.utility.Delay;

public class BasicMovment implements MovmentBase {

	@Override
	public void forward(int speed) {
		if(MainVariables.inverMotorDirections) {
			Delay.msDelay(10);
			startSynchronizationOfMotors();
			MainVariables.mLeft.backward();
			MainVariables.mRight.backward();
			endSynchronizationOfMotors();
			Delay.msDelay(10);
			setSpeeds(speed);
		} else {
			Delay.msDelay(10);
			startSynchronizationOfMotors();
			MainVariables.mLeft.forward();
			MainVariables.mRight.forward();
			startSynchronizationOfMotors();
			Delay.msDelay(10);
			setSpeeds(speed);
		}
	}

	@Override
	public void backward(int speed) {
		if(!MainVariables.inverMotorDirections) {
			Delay.msDelay(10);
			startSynchronizationOfMotors();
			MainVariables.mLeft.backward();
			MainVariables.mRight.backward();
			endSynchronizationOfMotors();
			Delay.msDelay(10);
			setSpeeds(speed);
			
		} else {
			Delay.msDelay(10);
			startSynchronizationOfMotors();
			MainVariables.mLeft.forward();
			MainVariables.mRight.forward();
			endSynchronizationOfMotors();
			Delay.msDelay(10);
			setSpeeds(speed);
		}
	}

	@Override
	public void turnAtPlace(int speed, String side) {
		if(side.equalsIgnoreCase("left")) {
			if(MainVariables.inverMotorDirections) {
				setSpeeds(speed);
				startSynchronizationOfMotors();
				MainVariables.mLeft.forward();
				MainVariables.mRight.backward();
				endSynchronizationOfMotors();
				setSpeeds(speed);
			} else {
				setSpeeds(speed);
				startSynchronizationOfMotors();
				MainVariables.mLeft.backward();
				MainVariables.mRight.forward();
				endSynchronizationOfMotors();
				setSpeeds(speed);
			}
		} else {
			if(!MainVariables.inverMotorDirections) {
				setSpeeds(speed);
				startSynchronizationOfMotors();
				MainVariables.mLeft.forward();
				MainVariables.mRight.backward();
				endSynchronizationOfMotors();
				setSpeeds(speed);
			} else {
				setSpeeds(speed);
				startSynchronizationOfMotors();
				MainVariables.mLeft.backward();
				MainVariables.mRight.forward();
				endSynchronizationOfMotors();
				setSpeeds(speed);
			}
		}
	}

	@Override
	public void motorOn(int speed, String side, boolean forward) {
		if(forward) {
			if(side.contains("left")) {
				if(MainVariables.inverMotorDirections) {
					MainVariables.mLeft.setSpeed(speed);
					MainVariables.mLeft.backward();
					MainVariables.mLeft.setSpeed(speed);
				} else {
					MainVariables.mLeft.setSpeed(speed);
					MainVariables.mLeft.forward();
					MainVariables.mLeft.setSpeed(speed);
				}
			} else if (side.contains("right")) {
				if(MainVariables.inverMotorDirections) {
					MainVariables.mRight.setSpeed(speed);
					MainVariables.mRight.backward();
					MainVariables.mRight.setSpeed(speed);
				} else {
					MainVariables.mRight.setSpeed(speed);
					MainVariables.mRight.forward();
					MainVariables.mRight.setSpeed(speed);
				}
			}
		} else {
			if(side.contains("left")) {
				if(!MainVariables.inverMotorDirections) {
					MainVariables.mLeft.setSpeed(speed);
					MainVariables.mLeft.backward();
					MainVariables.mLeft.setSpeed(speed);
				} else {
					MainVariables.mLeft.setSpeed(speed);
					MainVariables.mLeft.forward();
					MainVariables.mLeft.setSpeed(speed);
				}
			} else if (side.contains("right")) {
				if(!MainVariables.inverMotorDirections) {
					MainVariables.mRight.setSpeed(speed);
					MainVariables.mRight.backward();
					MainVariables.mRight.setSpeed(speed);
				} else {
					MainVariables.mRight.setSpeed(speed);
					MainVariables.mRight.forward();
					MainVariables.mRight.setSpeed(speed);
				}
			}
		}
	}

	@Override
	public void setSpeeds(int speed) {
		startSynchronizationOfMotors();
		MainVariables.mLeft.setSpeed(speed);
		MainVariables.mRight.setSpeed(speed);
		endSynchronizationOfMotors();
	}

	@Override
	public void motorsOff() {
		setSpeeds(0);
		startSynchronizationOfMotors();
		MainVariables.mLeft.stop();
		MainVariables.mRight.stop();
		endSynchronizationOfMotors();
		Delay.msDelay(50);
		setSpeeds(100);
	}

	@Override
	public void setSpeed(int speed, String side) {
		if(side.equalsIgnoreCase("left")) {
			MainVariables.mLeft.setSpeed(speed);
		} else if (side.equalsIgnoreCase("right")) {
			MainVariables.mRight.setSpeed(speed);
		}
	}
	
	public void stop() {
		motorsOff();
	}
	
	public void initSynchronistation() {
		Initsialization.synchInit();
	}
	
	public void startSynchronizationOfMotors() {
		MainVariables.mLeft.startSynchronization();
	}
	
	public void endSynchronizationOfMotors() {
		MainVariables.mLeft.endSynchronization();
	}

}
