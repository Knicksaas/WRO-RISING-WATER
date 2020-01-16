package ch.nte.wro.test.main;

import lejos.hardware.sensor.SensorModes;
import lejos.robotics.RegulatedMotor;

public interface RobotBase {

	public void setName(String name);
	public String getName();
	
	public void setMotorLeft(RegulatedMotor mLeft);
	public RegulatedMotor getMotorLeft();
	
	public void setMotorRight(RegulatedMotor mRight);
	public RegulatedMotor getMotorRight();
	
	public void setSensorOnPort(SensorModes sensor, int port);
	public SensorModes getSensorOnPort(int port);
	
	public void setMotorOnPort(RegulatedMotor motor, int port);
	public RegulatedMotor getMotorOnPort(int port);
}
