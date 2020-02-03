package ch.nte.wro.test;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class MediumMotorTester {
	
	public static void main(String[] args) {
		RegulatedMotor mArm = new EV3MediumRegulatedMotor(MotorPort.D);
		mArm.setSpeed(200);
		mArm.rotate(240);
		Delay.msDelay(1000);
		mArm.rotate(-240);
		mArm.stop();
		mArm.close();
	}

}
