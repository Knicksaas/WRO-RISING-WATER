package ch.nte.wro.test;

import ch.nte.wro.base.Robot;
import ch.nte.wro.main.Initsialization;
import ch.nte.wro.main.MovmentBlocks;
import ch.nte.wro.variables.MainVariables;
import ch.nte.wro.variables.Position;
import ch.nte.wro.variables.SensorValues;

public class DemoClass {

	public static void main(String[] args) {
		Robot bot = new Robot("colorTester", MainVariables.mLeft, MainVariables.mRight);
		Initsialization.sensorInit(bot);
		Initsialization.sensitivityInit();
		Initsialization.synchInit();
		Initsialization.housesInit();
		Position.botPosition = "yellow";
		sandBags(bot, 200);
	}
	
	@SuppressWarnings("unused")
	private static void evacuationRequest(Robot bot, int speed) {
		MovmentBlocks.driveToHouse(bot, speed, "lmao");
		bot.followLineRGB(speed, "double.cross", 0, SensorValues.sensitivity.get("yellow"),
				bot.getSensorOnPort(1), bot.getSensorOnPort(2));
	}
	
	@SuppressWarnings("unused")
	private static void sandBags(Robot bot, int speed) {
		MovmentBlocks.driveToHouse(bot, speed, "sandbags");
		bot.followLineRGB(speed, "double.cross", 0, SensorValues.sensitivity.get("yellow"),
				bot.getSensorOnPort(1), bot.getSensorOnPort(2));
	}
	
	@SuppressWarnings("unused")
	private static void uploadSandbags(Robot bot, int speed) {
		MovmentBlocks.pickUpSandBags(bot, speed, "left");
		bot.followLineRGB(speed, "double.cross", 0, SensorValues.sensitivity.get("yellow"),
				bot.getSensorOnPort(1), bot.getSensorOnPort(2));
	}
}
