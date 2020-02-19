package ch.nte.wro.base;

import ch.nte.wro.variables.MainVariables;
import lejos.utility.Delay;

public class BasicMovment implements MovmentBase {

	@Override
	public void forward(int speed) {
		if(MainVariables.inverMotorDirections) {
			setSpeeds(speed);
			Delay.msDelay(10);
			MainVariables.mLeft.backward();
			MainVariables.mRight.backward();
			Delay.msDelay(10);
			setSpeeds(speed);
		} else {
			setSpeeds(speed);
			Delay.msDelay(10);
			MainVariables.mLeft.forward();
			MainVariables.mRight.forward();
			Delay.msDelay(10);
			setSpeeds(speed);
		}
	}

	@Override
	public void backward(int speed) {
		if(!MainVariables.inverMotorDirections) {
			MainVariables.mLeft.backward();
			MainVariables.mRight.backward();
			setSpeeds(speed);
		} else {
			MainVariables.mLeft.forward();
			MainVariables.mRight.forward();
			setSpeeds(speed);
		}
	}

	@Override
	public void turnAtPlace(int speed, String side) {
		if(side.equalsIgnoreCase("left")) {
			if(MainVariables.inverMotorDirections) {
				setSpeeds(speed);
				MainVariables.mLeft.forward();
				MainVariables.mRight.backward();
				setSpeeds(speed);
			} else {
				setSpeeds(speed);
				MainVariables.mLeft.backward();
				MainVariables.mRight.forward();
				setSpeeds(speed);
			}
		} else {
			if(!MainVariables.inverMotorDirections) {
				setSpeeds(speed);
				MainVariables.mLeft.forward();
				MainVariables.mRight.backward();
				setSpeeds(speed);
			} else {
				setSpeeds(speed);
				MainVariables.mLeft.backward();
				MainVariables.mRight.forward();
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
		MainVariables.mLeft.setSpeed(speed);
		MainVariables.mRight.setSpeed(speed);
	}

	@Override
	public void motorsOff() {
		setSpeeds(0);
		MainVariables.mLeft.stop();
		MainVariables.mRight.stop();
		Delay.msDelay(50);
		setSpeeds(100);
		Delay.msDelay(50);
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

}
