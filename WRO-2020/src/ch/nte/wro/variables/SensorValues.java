package ch.nte.wro.variables;

public class SensorValues {
	
//	NONE, BLACK, BLUE, GREEN, YELLOW, RED, WHITE, BROWN: colorIDs of Ev3ColorSensor (0-7)
	
	public static float intensityBlack = 0.07F;
	public static float intensityWhite = 0.559F;
	public static float intensityBlue = 0.080F;
	public static float intensityGreen = 0.117F;
	public static float intensityYellow = 0.52F;
	public static float intensityRed = 0.484F;
	
	public static float targetIntensityLinefollower = (intensityBlack+intensityWhite)/2;
	
	public static float averageIntensityHalfCross = 0F;
	
	public static float allowedSensorVariation = 0.01F;

}
