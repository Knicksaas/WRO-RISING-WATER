package ch.nte.wro.threds;

import ch.nte.wro.base.Sensor;
import ch.nte.wro.base.ValueGetter;

public class RGBValueMeter extends Thread {
	
	private float lowestValue = 100;
	private Sensor sensorLeft;
	private Sensor sensorRight;
	private boolean running = true;
	
	public RGBValueMeter(Sensor sensorLeft, Sensor sensorRight) {
		this.sensorLeft = sensorLeft;
		this.sensorRight = sensorRight;
	}

	@Override
	public void run() {
		float actualValue;
		while (running) {
			actualValue = ValueGetter.getAverageSensorValues(sensorLeft, sensorRight);
			if(actualValue<lowestValue) {
				lowestValue = actualValue;
			}
		}
	}
	
	public void end() {
		running = false;
	}
	
	public float getLowestValue() {
		return lowestValue;
	}
}
