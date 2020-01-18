package ch.nte.wro.test;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class MediumMotorTester {
	
	public static void main(String[] args) {
		RegulatedMotor m = new EV3MediumRegulatedMotor(MotorPort.A);
		m.forward();
		m.setSpeed(250);
		Delay.msDelay(10000);
		m.close();
	}

}
