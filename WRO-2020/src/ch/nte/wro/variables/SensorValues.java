package ch.nte.wro.variables;

public class SensorValues {
	
//	NONE, BLACK, BLUE, GREEN, YELLOW, RED, WHITE, BROWN: colorIDs of Ev3ColorSensor (0-7)
	
	public static float intensityBlack = 0.069F;
	public static float intensityWhite = 0.533F;
	public static float intensityBlue = 0.079F;
	public static float intensityGreen = 0.114F;
	public static float intensityYellow = 0.521F;
	public static float intensityRed = 0.475F;
	
	public static float targetIntensityLinefollower = (intensityBlack+intensityWhite)/2;
	
	public static float averageIntensityHalfCross = 0F;
	
	public static float allowedSensorVariation = 0.01F;

}
