package ch.nte.wro.threds;

import java.util.ArrayList;
import java.util.List;

import ch.nte.wro.base.RGBValue;
import ch.nte.wro.base.Sensor;
import ch.nte.wro.variables.SynchedBoolean;
import lejos.hardware.Sound;
import lejos.utility.Delay;

public class DetectLineColorChange extends Thread {

	private SynchedBoolean carrier;
	private Sensor sensorLeft;
	private Sensor sensorRight;
	private float diff;
	
	public DetectLineColorChange(SynchedBoolean carrier, Sensor sensorLeft, Sensor sensorRight, float diff) {
		this.carrier = carrier;
		this.sensorLeft = sensorLeft;
		this.sensorRight = sensorRight;
		this.diff = diff;
	}
	
	@Override
	public void run() {
		RGBValue basicValue = getAverageRGBValue(sensorLeft, sensorRight);
		Sound.beep();
		RGBValue actualValue = new RGBValue(0, 0, 0);
		while (true) {
			actualValue = new RGBValue(sensorLeft.mesure()).getAverage(new RGBValue(sensorRight.mesure()));
			if(!actualValue.isEqual(basicValue, diff)) {
				carrier.set(false);
				Sound.beep();
				return;
			}
		}
	}
	
	public RGBValue getAverageRGBValue(Sensor sensorLeft, Sensor sensorRight) {
		List<RGBValue> values = new ArrayList<>();
		float red = 0;
		float green = 0;
		float blue = 0;
		for(int i = 0; i<10; i++) {
			red = (sensorLeft.mesure()[0]+sensorRight.mesure()[0])/2;
			green = (sensorLeft.mesure()[1]+sensorRight.mesure()[1])/2;
			blue = (sensorLeft.mesure()[2]+sensorRight.mesure()[2])/2;
			values.add(new RGBValue(red, green, blue));
			Delay.msDelay(30);
		}
		float returnRed = 0;
		float returnGreen = 0;
		float returnBlue = 0;
		for(int i = 0; i<values.size(); i++) {
			returnRed = returnRed+values.get(i).getRed();
			returnGreen = returnGreen+values.get(i).getGreen();
			returnBlue = returnBlue+values.get(i).getBlue();
		}
		return new RGBValue(returnRed/values.size(), returnGreen/values.size(), returnBlue/values.size());
	}
}
