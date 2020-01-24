package ch.nte.wro.variables;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

public class MainVariables {

	public static RegulatedMotor mLeft = new EV3LargeRegulatedMotor(MotorPort.A);
	public static RegulatedMotor mRight = new EV3LargeRegulatedMotor(MotorPort.B);
	public static boolean inverMotorDirections = false;
}