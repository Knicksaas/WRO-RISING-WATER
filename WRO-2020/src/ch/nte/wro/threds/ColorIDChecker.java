package ch.nte.wro.threds;

import ch.nte.wro.base.Sensor;
import ch.nte.wro.variables.SynchedBoolean;

public class ColorIDChecker extends Thread {
	
	private SynchedBoolean carrier;
	private Sensor sensor;
	private int colorID;

	public ColorIDChecker(SynchedBoolean carier, Sensor sensor, int colorID) {
		this.carrier = carier;
		this.sensor = sensor;
	}
	@Override
	public void run() {
		sensor.setMode("ColorID");
		float value = sensor.mesure()[0];
		while(true) {
			if(value == colorID) {
				carrier.set(false);
				return;
			}
		}
	}

}
