package ch.nte.wro.threds;

import ch.nte.wro.base.Sensor;
import ch.nte.wro.variables.SynchedBoolean;

public class LightIntensityChecker extends Thread {
	
	private SynchedBoolean carrier;
	private Sensor sensorLeft;
	private Sensor sensorRight;
	private float averageIntensity;
	private float diff;

	public LightIntensityChecker(SynchedBoolean carier, Sensor sensorLeft, Sensor sensorRight, float averageIntensity, float diff) {
		this.carrier = carier;
		this.sensorLeft = sensorLeft;
		this.sensorRight = sensorRight;
		this.averageIntensity = averageIntensity;
		this.diff = diff;
	}
	@Override
	public void run() {
		sensorLeft.setMode("Red");
		sensorRight.setMode("Red");
		float intensity1 = sensorLeft.mesure()[0];
		float intensity2 = sensorRight.mesure()[0];
		while(true) {
			intensity1 = sensorLeft.mesure()[0];
			intensity2 = sensorRight.mesure()[0];
			if(averageIntensity-diff < (intensity1+intensity2)/2 && (intensity1+intensity2)/2 < averageIntensity+diff) {
				carrier.set(false);
				return;
			}
		}
	}

}
