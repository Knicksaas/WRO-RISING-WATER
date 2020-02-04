package ch.nte.wro.base;

import lejos.hardware.Sound;

public class ColorGetter {
	
	public static String getColor(Sensor sensor) {
		sensor.setMode("RGB");
		float valueGreen = sensor.mesure()[1];
		float valueBlue = sensor.mesure()[2];
		if(valueGreen < valueBlue) {
			Sound.beep();
			return "Blue";
		} else {
			Sound.buzz();
			return "Green";
			
		}
	}
}
