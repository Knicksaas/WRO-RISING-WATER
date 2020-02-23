package ch.nte.wro.base;

import ch.nte.wro.objects.RGBValue;

public class ValueGetter {

	public static RGBValue getRGBValue(Sensor sensor) {
		return new RGBValue(sensor.mesure());
	}
	
	public static RGBValue getAverageRGBValue(Sensor sensorLeft, Sensor sensorRight) {
		float red = (sensorLeft.mesure()[0]+sensorRight.mesure()[0])/2;
		float green = (sensorLeft.mesure()[1]+sensorRight.mesure()[1])/2;
		float blue = (sensorLeft.mesure()[2]+sensorRight.mesure()[2])/2;
		return new RGBValue(red, green, blue);
	}
	
	public static float getAverageSensorValues(Sensor sensorLeft, Sensor sensorRight) {
		float red = (sensorLeft.mesure()[0]+sensorRight.mesure()[0])/2;
		float green = (sensorLeft.mesure()[1]+sensorRight.mesure()[1])/2;
		float blue = (sensorLeft.mesure()[2]+sensorRight.mesure()[2])/2;
		return blue+green+red;
	}
	
	public static RGBValue getSensorDiff(Sensor sensorLeft, Sensor sensorRight) {
		float value;
		float red = 0;
		float green = 0;
		float blue = 0;
		for(int i = 0; i<3; i++) {
			value = sensorRight.getIndex(i)-sensorLeft.getIndex(i);
			if(value<0) {
				value *= -1;
			}
			switch (i) {
			case 0:
				red = value;
				break;
			case 1:
				green = value;
				break;
			case 2:
				blue = value;
				break;
			}
		}
		return new RGBValue(red, green, blue);
	}
}
