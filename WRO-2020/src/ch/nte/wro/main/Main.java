package ch.nte.wro.main;

import ch.nte.wro.base.Robot;
import ch.nte.wro.variables.MainVariables;
import ch.nte.wro.variables.SynchedVariables;

public class Main {
	
	public static final int speed = 200;
	public static final String startPoint = "K6";
	
	 public static void main(String[] args) {
		Robot bot = new Robot("Robot", MainVariables.mLeft, MainVariables.mRight);

		init(bot);
		
		Navigation.unloadEvacuationRequests(bot, speed, startPoint);
		Navigation.upAndUnloadSandbags(bot, speed);
		Navigation.driveToStartPosition(bot, speed, startPoint);
	}
	
	
	private static void init(Robot bot) {
		SynchedVariables.globalSpeed.set(0);
		Initsialization.sensorInit(bot);
		Initsialization.sensitivityInit();
		Initsialization.housesInit();
	}
}
