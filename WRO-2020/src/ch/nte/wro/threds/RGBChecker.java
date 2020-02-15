package ch.nte.wro.threds;

import ch.nte.wro.base.RGBValue;
import ch.nte.wro.base.Sensor;
import ch.nte.wro.variables.SynchedBoolean;

public class RGBChecker extends Thread {
	
	private SynchedBoolean carrier;
	private Sensor sensorLeft;
	private Sensor sensorRight;
	private RGBValue value;
	private float diff;

	public RGBChecker(SynchedBoolean carrier, Sensor sensorLeft, Sensor sensorRight, RGBValue value, float diff) {
		this.carrier = carrier;
		this.sensorLeft = sensorLeft;
		this.sensorRight = sensorRight;
		this.value = value;
		this.diff = diff;
	}
	
	@Override
	public void run() {
		sensorLeft.setMode("RGB");
		sensorRight.setMode("RGB");
		RGBValue mesurementLeft;
		RGBValue mesurementRight;
		while (true) {
			mesurementLeft = new RGBValue(sensorLeft.mesure()[0], sensorLeft.mesure()[1], sensorLeft.mesure()[2]);
			mesurementRight = new RGBValue(sensorRight.mesure()[0], sensorRight.mesure()[1], sensorRight.mesure()[2]);
			if(mesurementLeft.isEqual(value, diff) && mesurementRight.isEqual(value, diff)) {
				carrier.set(false);
				return;
			}
		}
	}
}
