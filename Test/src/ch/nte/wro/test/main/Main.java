package ch.nte.wro.test.main;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Main {
	
	public static volatile boolean running;

	public static void main(String[] args) {
		RegulatedMotor motor = new EV3LargeRegulatedMotor(MotorPort.B);
		motor.forward();
		motor.setSpeed(2000);
		Delay.msDelay(1000);
		motor.stop();
		motor.backward();
		Delay.msDelay(2000);
		motor.stop();
		motor.close();
	}
}
