package ch.nte.wro.base;

import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;

public class Sensor implements SensorBase {
	
	private SensorModes sensor;

	private SampleProvider sampleProvider;
	private float[] status;
			
	public Sensor(SensorModes sensor) {
		this.sensor = sensor;
	}

	@Override
	public void setMode(String mode) {
		sampleProvider = sensor.getMode(mode);
		status = new float[sampleProvider.sampleSize()];
	}

	@Override
	public void checkSenor() {
		sampleProvider.fetchSample(status, 0);
	}

	@Override
	public float[] getValue() {
		return status;
	}

	@Override
	public float[] mesure() {
		checkSenor();
		return getValue();
	}
}
