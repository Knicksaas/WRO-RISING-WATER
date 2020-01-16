package ch.nte.wro.test.main;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class Main {

	public static void main(String[] args) {
		Robot bot = new Robot("robi", new EV3LargeRegulatedMotor(MotorPort.A), new EV3LargeRegulatedMotor(MotorPort.B));
		
		bot.sayHi();
	}

}
