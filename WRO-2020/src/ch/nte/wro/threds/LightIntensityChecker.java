package ch.nte.wro.threds;

import ch.nte.wro.base.Sensor;

public class LightIntensityChecker implements Runnable{
	
	private boolean carrier;
	private Sensor sensorLeft;
	private Sensor sensorRight;
	private float averageIntensity;
	private float diff;

	public LightIntensityChecker(boolean carier, Sensor sensorLeft, Sensor sensorRight, float averageIntensity, float diff) {
		this.carrier = carier;
		this.sensorLeft = sensorLeft;
		this.sensorRight = sensorRight;
		this.averageIntensity = averageIntensity;
		this.diff = diff;
	}
	@Override
	public void run() {
		float intensity1 = 0;
		float intensity2 = 0;
		while(averageIntensity-diff < (intensity1+intensity2)/2 && (intensity1+intensity2)/2 < averageIntensity+diff) {
			intensity1 = sensorLeft.mesure()[0];
			intensity2 = sensorRight.mesure()[0];
		}
		if(carrier) {
			carrier = false;
		}
	}

}
