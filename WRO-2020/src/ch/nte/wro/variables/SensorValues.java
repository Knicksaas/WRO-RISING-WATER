package ch.nte.wro.variables;

public class SensorValues {
	
	public static float intensityBlack = 0F;
	public static float intensityWhite = 0F;
	
	public static float targetIntensityLinefollower = (intensityBlack+intensityWhite)/2;
	
	public static float averageIntensityHalfCross = 0F;
	
	public static float allowedSensorVariation = 0.02F;

}
