package ch.nte.wro.test;

import ch.nte.wro.base.Robot;
import ch.nte.wro.main.Initsialization;
import ch.nte.wro.main.MovmentBlocks;
import ch.nte.wro.variables.MainVariables;
import ch.nte.wro.variables.Position;
import ch.nte.wro.variables.SensorValues;
import ch.nte.wro.variables.SynchedVariables;

public class TestChangeSide {
	public static final int speed = 200;
	
	public static void main(String[] args) {
		Robot bot = new Robot("Robot", MainVariables.mLeft, MainVariables.mRight);

		init(bot);
		
		Position.botPosition = "blue";
		
		doCycle(bot, 3);
	}
	
	private static void doCycle(Robot bot, int repeats) {
		for(int i = 0;i<repeats;i++) {
			MovmentBlocks.changeSide(bot, speed, "right", "right");
			bot.followLineRGB(speed, "double.cross", 0, SensorValues.sensitivity.get(Position.botPosition),
					bot.getSensorOnPort(1), bot.getSensorOnPort(2));
			bot.turnWithRotations(speed, "half", "right");
			MovmentBlocks.changeSide(bot, speed, "left", "left");
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
