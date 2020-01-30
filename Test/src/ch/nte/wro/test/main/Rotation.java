package ch.nte.wro.test.main;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

public class Rotation {

	public static void main(String[] args) {
		RegulatedMotor mLeft = new EV3LargeRegulatedMotor(MotorPort.A);
		mLeft.rotate(180);
		mLeft.close();
	}
}
