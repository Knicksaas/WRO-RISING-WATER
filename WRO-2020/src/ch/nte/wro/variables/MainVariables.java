package ch.nte.wro.variables;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

public class MainVariables {

	public static RegulatedMotor mLeft = new EV3LargeRegulatedMotor(MotorPort.B);
	public static RegulatedMotor mRight = new EV3LargeRegulatedMotor(MotorPort.C);
	public static RegulatedMotor mArm = new EV3MediumRegulatedMotor(MotorPort.D);
	public static RegulatedMotor mBelt = new EV3MediumRegulatedMotor(MotorPort.A);
	public static boolean inverMotorDirections = true;
	public static int armAngle = 0;
}	
