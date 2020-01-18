package ch.nte.wro.base;

import ch.nte.wro.variables.MainVariables;

public class BasicMovment implements MovmentBase {

	@Override
	public void forward(int speed) {
		if(MainVariables.inverMotorDirections) {
			MainVariables.mLeft.backward();
			MainVariables.mRight.backward();
			setSpeed(speed);
		} else {
			MainVariables.mLeft.forward();
			MainVariables.mRight.forward();
			setSpeed(speed);
		}
	}

	@Override
	public void backward(int speed) {
		if(!MainVariables.inverMotorDirections) {
			MainVariables.mLeft.backward();
			MainVariables.mRight.backward();
			setSpeed(speed);
		} else {
			MainVariables.mLeft.forward();
			MainVariables.mRight.forward();
			setSpeed(speed);
		}
	}

	@Override
	public void turnAtPlace(int speed, String side) {
		if(side.equalsIgnoreCase("left")) {
			if(!MainVariables.inverMotorDirections) {
				MainVariables.mLeft.forward();
				MainVariables.mRight.backward();
				setSpeed(speed);
			} else {
				MainVariables.mLeft.backward();
				MainVariables.mRight.forward();
				setSpeed(speed);
			}
		} else {
			if(MainVariables.inverMotorDirections) {
				MainVariables.mLeft.forward();
				MainVariables.mRight.backward();
				setSpeed(speed);
			} else {
				MainVariables.mLeft.backward();
				MainVariables.mRight.forward();
				setSpeed(speed);
			}
		}
	}

	@Override
	public void motorOn(int speed, String side) {
		if(side.equalsIgnoreCase("left")) {
			if(!MainVariables.inverMotorDirections) {
				MainVariables.mLeft.forward();
				MainVariables.mLeft.setSpeed(speed);
			} else {
				MainVariables.mLeft.backward();
				MainVariables.mLeft.setSpeed(speed);
			}
		} else {
			if(!MainVariables.inverMotorDirections) {
				MainVariables.mRight.forward();
				MainVariables.mRight.setSpeed(speed);
			} else {
				MainVariables.mRight.backward();
				MainVariables.mRight.setSpeed(speed);
			}
		}
	}

	@Override
	public void setSpeed(int speed) {
		MainVariables.mLeft.setSpeed(speed);
		MainVariables.mRight.setSpeed(speed);
	}

	@Override
	public void motorsOff() {
		MainVariables.mLeft.setSpeed(0);
		MainVariables.mRight.setSpeed(0);
		MainVariables.mLeft.stop();
		MainVariables.mRight.stop();
	}
	
	

}
