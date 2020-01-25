package ch.nte.wro.base;

import ch.nte.wro.variables.MainVariables;
import lejos.utility.Delay;

public class BasicMovment implements MovmentBase {

	@Override
	public void forward(int speed) {
		if(MainVariables.inverMotorDirections) {
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
			if(!MainVariables.inverMotorDirections) {
				MainVariables.mLeft.forward();
				MainVariables.mRight.backward();
				setSpeeds(speed);
			} else {
				MainVariables.mLeft.backward();
				MainVariables.mRight.forward();
				setSpeeds(speed);
			}
		} else {
			if(MainVariables.inverMotorDirections) {
				MainVariables.mLeft.forward();
				MainVariables.mRight.backward();
				setSpeeds(speed);
			} else {
				MainVariables.mLeft.backward();
				MainVariables.mRight.forward();
				setSpeeds(speed);
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
	public void setSpeeds(int speed) {
		MainVariables.mLeft.setSpeed(speed);
		MainVariables.mRight.setSpeed(speed);
	}

	@Override
	public void motorsOff() {
		Delay.msDelay(10);
		MainVariables.mLeft.setSpeed(0);
		MainVariables.mRight.setSpeed(0);
		MainVariables.mLeft.stop();
		MainVariables.mRight.stop();
	}

	@Override
	public void setSpeed(int speed, String side) {
		if(side.equalsIgnoreCase("left")) {
			MainVariables.mLeft.setSpeed(speed);
		} else if (side.equalsIgnoreCase("right")) {
			MainVariables.mRight.setSpeed(speed);
		}
	}
	
	

}
