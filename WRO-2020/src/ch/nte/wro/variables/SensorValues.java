package ch.nte.wro.variables;

import ch.nte.wro.base.RGBValue;

public class SensorValues {
	
//	NONE, BLACK, BLUE, GREEN, YELLOW, RED, WHITE, BROWN: colorIDs of Ev3ColorSensor (0-7)
	
	public static float intensityBlack = 0.061F;
	public static float intensityWhite = 0.542F;
	public static float intensityBlue = 0.087F;
	public static float intensityGreen = 0.114F;
	public static float intensityYellow = 0.525F;
	public static float intensityRed = 0.455F;
	
	public static RGBValue valueBlack = new RGBValue(0.02245f, 0.025f, 0.0268f);
	
	public static float targetIntensityLinefollower = (intensityBlack+intensityWhite)/2;
	
	public static float averageIntensityHalfCross = 0F;
	
	public static float allowedSensorVariation = 0.01F;
	public static float allowedRGBVariation = 0.03f;

}
