package ch.nte.wro.variables;

import java.util.HashMap;
import java.util.Map;

import ch.nte.wro.objects.RGBValue;

public class SensorValues {
	
//	NONE, BLACK, BLUE, GREEN, YELLOW, RED, WHITE, BROWN: colorIDs of Ev3ColorSensor (0-7)
	
	public static final float intensityBlack = 0.07F;
	public static final float intensityWhite = 0.581F;
	public static final float intensityBlue = 0.090F;
	public static final float intensityGreen = 0.119F;
	public static final float intensityYellow = 0.560F;
	public static final float intensityRed = 0.528F;

	public static RGBValue valueBlack = new RGBValue(0.0214f, 0.0238f, 0.0236f);
	
	public static float targetIntensityLinefollower = (intensityBlack+intensityWhite)/2;
	
	public static float averageIntensityHalfCross = 0F;
	
	public static final float allowedSensorVariation = 0.01F;
	public static final float allowedRGBVariation = 0.03f;
	
	public static final Map<String, Integer> sensitivity = new HashMap<String, Integer>();

	public static final float otherLineColorDiff = 0.03f;

}
