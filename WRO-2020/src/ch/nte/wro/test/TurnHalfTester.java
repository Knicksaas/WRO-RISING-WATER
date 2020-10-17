package ch.nte.wro.test;

import ch.nte.wro.base.Robot;
import ch.nte.wro.main.Initsialization;
import ch.nte.wro.variables.MainVariables;
import ch.nte.wro.variables.Position;
import ch.nte.wro.variables.SensorValues;
import ch.nte.wro.variables.SynchedVariables;

public class TurnHalfTester {

public static final int speed = 200;
	
	public static void main(String[] args) {
		Robot bot = new Robot("Robot", MainVariables.mLeft, MainVariables.mRight);

		init(bot);
		
		Position.botPosition = "blue";
		
		for(int l = 0; l < 4; l++) {
			bot.turnWithRotations(speed, "half", "left");
		}
		
		for(int r = 0; r < 4; r++) {
			bot.turnWithRotations(speed, "half", "right");
		}
		
		for(int i = 0;i<8;i++) {
			bot.followLineRGB(speed, "double.cross", 0, SensorValues.sensitivity.get(Position.botPosition),
					bot.getSensorOnPort(1), bot.getSensorOnPort(2));
			bot.turnWithRotations(speed, "half", "right");
		}
	}
	
	private static void init(Robot bot) {
		SynchedVariables.globalSpeed.set(0);
		Initsialization.sensorInit(bot);
		Initsialization.sensitivityInit();
		Initsialization.housesInit();
	}
}
