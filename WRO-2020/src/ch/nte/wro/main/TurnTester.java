package ch.nte.wro.main;

import ch.nte.wro.base.Robot;
import ch.nte.wro.variables.MainVariables;
import ch.nte.wro.variables.Position;
import ch.nte.wro.variables.SynchedVariables;

public class TurnTester {

public static final int speed = 175;
	
	public static void main(String[] args) {
		Robot bot = new Robot("Robot", MainVariables.mLeft, MainVariables.mRight);

		init(bot);
		
		Position.botPosition = "blue";
		
		bot.turnWithRotations(speed, "quarter", "right");
		bot.turnWithRotations(speed, "quarter", "left");
		
		bot.turnWithRotations(speed, "quarter", "left");
		bot.turnWithRotations(speed, "quarter", "right");
	}
	
	
	private static void init(Robot bot) {
		SynchedVariables.globalSpeed.set(0);
		Initsialization.sensorInit(bot);
		Initsialization.sensitivityInit();
		Initsialization.housesInit();
	}
}
