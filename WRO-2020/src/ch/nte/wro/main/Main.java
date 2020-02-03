package ch.nte.wro.main;

import ch.nte.wro.base.Robot;
import ch.nte.wro.variables.GlobalSensors;
import ch.nte.wro.variables.MainVariables;
import ch.nte.wro.variables.SynchedVariables;
import lejos.utility.Delay;

public class Main {
	
	public static final int speed = 200;
	
	 public static void main(String[] args) {
		Robot bot = new Robot("Robot", MainVariables.mLeft, MainVariables.mRight);
<<<<<<< HEAD
//		init(bot);
=======
		init(bot);
		
		Navigation.startPointToHouse("right", bot, speed);
		Handling.unloadEvacuationRequest(bot, speed);
		Navigation.driveToOtherStartPlace("left", bot, speed);
>>>>>>> 40448bdd805dfc485c0943760ab465846b06c6d3
		
		bot.setArmAngle(200, 50);
		Delay.msDelay(8000);
		bot.setArmAngle(0, 50);
		/*bot.sandBagPickUp(100, null);
		Delay.msDelay(5000);
		bot.setArmAngle(0, 50);*/
		Delay.msDelay(5000);
	}
	 
	private static void init(Robot bot) {
		SynchedVariables.globalSpeed.set(0);
		sensorInit(bot);
	}
	 
	private static void sensorInit(Robot bot) {
		bot.setSensorOnPort(GlobalSensors.colorSensor1, 1);
		bot.getSensorOnPort(1).setMode("Red");
		bot.setSensorOnPort(GlobalSensors.colorSensor2, 2);
		bot.getSensorOnPort(2).setMode("Red");
	}
}
