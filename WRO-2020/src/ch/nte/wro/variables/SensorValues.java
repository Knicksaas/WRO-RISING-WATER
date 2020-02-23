package ch.nte.wro.variables;

import java.util.HashMap;
import java.util.Map;

import ch.nte.wro.objects.RGBValue;

public class SensorValues {
	
//	NONE, BLACK, BLUE, GREEN, YELLOW, RED, WHITE, BROWN: colorIDs of Ev3ColorSensor (0-7)
	
	public static final float intensityBlack = 0.066F;
	public static final float intensityWhite = 0.561F;
	public static final float intensityBlue = 0.090F;
	public static final float intensityGreen = 0.123F;
	public static final float intensityYellow = 0.534F;
	public static final float intensityRed = 0.505F;

	public static RGBValue valueBlack = new RGBValue(0.0201f, 0.0228f, 0.0220f);
	
	public static float targetIntensityLinefollower = (intensityBlack+intensityWhite)/2;
	
	public static float averageIntensityHalfCross = 0F;
	
	public static final float allowedSensorVariation = 0.01F;
	public static final float allowedRGBVariation = 0.03f;
	
	public static final Map<String, Integer> sensitivity = new HashMap<String, Integer>();

	public static final float otherLineColorDiff = 0.03f;

}
