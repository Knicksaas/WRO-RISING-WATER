package ch.nte.wro.test;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class WheelBalancer {

	public static void main(String[] args) {
		RegulatedMotor mLeft = new EV3LargeRegulatedMotor(MotorPort.A);
		mLeft.forward();
		mLeft.setSpeed(2000);
		Delay.msDelay(10000);
		mLeft.stop();
		mLeft.close();
	}
}
