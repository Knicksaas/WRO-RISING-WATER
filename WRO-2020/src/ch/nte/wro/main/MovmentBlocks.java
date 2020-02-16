package ch.nte.wro.main;

import ch.nte.wro.base.Robot;

public class MovmentBlocks {

	public static void changeSide(int speed, String side1, String side2) {
		
	}
	public static void driveToHouse(Robot bot, int speed, String mode) {
	float sensitivity2 = 0;	
	float sensitivity1 = 0;
	//if for sensitivity 
	bot.followLineRGB(speed, "double.cross", 0, sensitivity1, bot.getSensorOnPort(1), bot.getSensorOnPort(2));	
	bot.followLineRGB(speed, "double.cross", 0, sensitivity2, bot.getSensorOnPort(1), bot.getSensorOnPort(2));	
	//if for mode
	}
}

