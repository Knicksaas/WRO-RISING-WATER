package ch.nte.wro.main;

import ch.nte.wro.base.Robot;
import ch.nte.wro.variables.ConveyorbeltStatus;
import ch.nte.wro.variables.GlobalSensors;
import ch.nte.wro.variables.MainVariables;
import ch.nte.wro.variables.SynchedVariables;
import lejos.hardware.Sound;

public class TestMain {
	
	public static final int speed = 200;
	
	 public static void main(String[] args) {
		Robot bot = new Robot("Robot", MainVariables.mLeft, MainVariables.mRight);

		init(bot);
		bot.followLine(speed, "double.cross", 0, 60, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		bot.sandBagPickUp(100, bot.getSensorOnPort(3));
		if(ConveyorbeltStatus.slot2.equalsIgnoreCase("blueSandbag")) {
			Sound.beep();
		}else {
			Sound.buzz();
		}
		
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
		bot.setSensorOnPort(GlobalSensors.hiTechnicsColorSensor, 3);
		bot.getSensorOnPort(3).setMode("ColorID");
	}
}



