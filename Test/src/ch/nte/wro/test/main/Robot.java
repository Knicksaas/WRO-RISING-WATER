package ch.nte.wro.test.main;

import java.util.HashMap;
import java.util.Map;

import lejos.hardware.sensor.SensorModes;
import lejos.robotics.RegulatedMotor;

public class Robot extends Sensors implements RobotBase {
	
	private String name;
	private RegulatedMotor mLeft;
	private RegulatedMotor mRight;
	
	private Map<Integer, SensorModes> sensors = new HashMap<>();
	private Map<Integer, RegulatedMotor> motors = new HashMap<>();
	
	public Robot(String name, RegulatedMotor mLeft, RegulatedMotor mRight) {
		this.name = name;
		this.mLeft = mLeft;
		this.mRight = mRight;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setMotorLeft(RegulatedMotor mLeft) {
		this.mLeft = mLeft;
	}

	@Override
	public RegulatedMotor getMotorLeft() {
		return mLeft;
	}

	@Override
	public void setMotorRight(RegulatedMotor mRight) {
		this.mRight = mRight;
	}

	@Override
	public RegulatedMotor getMotorRight() {
		return mRight;
	}

	@Override
	public void setSensorOnPort(SensorModes sensor, int port) {
		sensors.put(port, sensor);
	}

	@Override
	public SensorModes getSensorOnPort(int port) {
		return sensors.get(port);
	}

	@Override
	public void setMotorOnPort(RegulatedMotor motor, int port) {
		motors.put(port, motor);
	}

	@Override
	public RegulatedMotor getMotorOnPort(int port) {
		return motors.get(port);
	}

}
