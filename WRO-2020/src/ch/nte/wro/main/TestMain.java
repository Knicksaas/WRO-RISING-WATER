package ch.nte.wro.main;

import ch.nte.wro.base.Robot;
import ch.nte.wro.variables.GlobalSensors;
import ch.nte.wro.variables.MainVariables;
import ch.nte.wro.variables.Position;
import ch.nte.wro.variables.SynchedVariables;

public class TestMain {
	
	public static final int speed = 200;
	
	 public static void main(String[] args) {
		Robot bot = new Robot("Robot", MainVariables.mLeft, MainVariables.mRight);

		init(bot);
			
		Position.botPosition = "red";
		//MovmentBlocks.driveToHouse(bot, speed, "EvacuationRequest");
		//MovmentBlocks.changeSide(bot, speed, "left", "right");
		MovmentBlocks.changeSide(bot, speed, "left", "right");
		bot.followLineRGB(speed, "double.cross", 0, 20, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		
	}
	 
	private static void init(Robot bot) {
		SynchedVariables.globalSpeed.set(0);
		sensorInit(bot);
		Initsialization.sensitivityInit();
	}
	 
	private static void sensorInit(Robot bot) {
		bot.setSensorOnPort(GlobalSensors.colorSensor1, 1);
		bot.getSensorOnPort(1).setMode("Red");
		bot.setSensorOnPort(GlobalSensors.colorSensor2, 2);
		bot.getSensorOnPort(2).setMode("Red");
		bot.setSensorOnPort(GlobalSensors.hiTechnicsColorSensor, 3);
		bot.getSensorOnPort(3).setMode("ColorID");
	}
}



