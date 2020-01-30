package ch.nte.wro.main;

import ch.nte.wro.base.Robot;
import ch.nte.wro.variables.GlobalSensors;
import ch.nte.wro.variables.MainVariables;
import ch.nte.wro.variables.SynchedVariables;

public class Main {
	
	 public static void main(String[] args) {
		Robot bot = new Robot("Robot", MainVariables.mLeft, MainVariables.mRight);
		init(bot);
		
		bot.followLine(150, "double.time", 30000, 60F, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		
	}
	 
	private static void init(Robot bot) {
		SynchedVariables.globalSpeed.set(0);
		sensorInit(bot);
	}
	 
	private static void sensorInit(Robot bot) {
		bot.setSensorOnPort(GlobalSensors.colorSensor1, 1);
		bot.setSensorOnPort(GlobalSensors.colorSensor2, 2);
	}
}
