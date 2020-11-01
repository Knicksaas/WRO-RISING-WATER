package ch.nte.wro.demo;

import ch.nte.wro.base.Robot;
import ch.nte.wro.main.Initsialization;
import ch.nte.wro.variables.MainVariables;
import ch.nte.wro.variables.Position;
import ch.nte.wro.variables.SynchedVariables;

public class DemoGrabConcreteBlock {

public static final int speed = 200;
	
	public static void main(String[] args) {
		Robot bot = new Robot("Robot", MainVariables.mLeft, MainVariables.mRight);

		init(bot);
		
		Position.botPosition = "blue";
		
		// Grab
		bot.setArmAngle(100, 200);
		bot.rotate(speed, 1f);
		bot.setArmAngle(40, 200);
		bot.rotate(speed, -1f);
		bot.turnWithRotations(speed, "quarter", "left");
		
		// Unload
		bot.rotate(speed, 1f);
		bot.setArmAngle(100, 200);
		bot.rotate(speed, -1f);
		bot.setArmAngle(0, 200);
		
	}
	
	
	
	
	private static void init(Robot bot) {
		SynchedVariables.globalSpeed.set(0);
		Initsialization.sensorInit(bot);
		Initsialization.sensitivityInit();
		Initsialization.housesInit();
	}

}
