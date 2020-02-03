package ch.nte.wro.test;

import ch.nte.wro.variables.MainVariables;
import lejos.robotics.RegulatedMotor;

public class SoundTester {

	public static void main(String[] args) {
		RegulatedMotor mLeft = MainVariables.mLeft;
		mLeft.setSpeed(100);
		mLeft.rotate(-180);
	}

}
