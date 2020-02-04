package ch.nte.wro.threds;

import java.util.ArrayList;
import java.util.List;

import ch.nte.wro.base.Sensor;
import ch.nte.wro.variables.SynchedBoolean;
import lejos.hardware.Sound;
import lejos.utility.Delay;

public class DetectLineColorThread extends Thread {

	private SynchedBoolean carrier;
	private Sensor sensorLeft;
	private Sensor sensorRight;
	private float diff;
	
	public DetectLineColorThread(SynchedBoolean carrier, Sensor sensorLeft, Sensor sensorRight, float diff) {
		this.carrier = carrier;
		this.sensorLeft = sensorLeft;
		this.sensorRight = sensorRight;
		this.diff = diff;
	}
	
	@Override
	public void run() {
		float basicValue = getAverageIntensity(sensorLeft, sensorRight);
		Sound.beep();
		float actualValue;
		while(true) {
			actualValue = getAverageIntensity(sensorLeft, sensorRight);
			if(actualValue < basicValue-diff || actualValue > basicValue+diff) {
				carrier.set(false);
				Sound.beep();
				return;
			}
		}
	}
	
	public float getAverageIntensity(Sensor sensorLeft, Sensor sensorRight) {
		List<Float> values = new ArrayList<Float>();
		float average = 0;
		for(int i = 0; i<10; i++) {
			average = (sensorLeft.mesure()[0]+sensorRight.mesure()[0])/2;
			values.add(average);
			Delay.msDelay(50);
		}
		float returnValue = 0;
		for(int i = 0; i<values.size(); i++) {
			returnValue = returnValue+values.get(i);
		}
		return returnValue/values.size();
	}
}
