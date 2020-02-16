package ch.nte.wro.variables;

import ch.nte.wro.base.RGBValue;

public class SensorValues {
	
//	NONE, BLACK, BLUE, GREEN, YELLOW, RED, WHITE, BROWN: colorIDs of Ev3ColorSensor (0-7)
	
	public static final float intensityBlack = 0.07F;
	public static final float intensityWhite = 0.559F;
	public static final float intensityBlue = 0.080F;
	public static final float intensityGreen = 0.117F;
	public static final float intensityYellow = 0.52F;
	public static final float intensityRed = 0.484F;

	public static RGBValue valueBlack = new RGBValue(0.02245f, 0.025f, 0.0268f);
	
	public static float targetIntensityLinefollower = (intensityBlack+intensityWhite)/2;
	
	public static float averageIntensityHalfCross = 0F;
	
	public static final float allowedSensorVariation = 0.01F;
	public static final float allowedRGBVariation = 0f;
	public static final float otherLineColorDiff = 0.01f;

}
