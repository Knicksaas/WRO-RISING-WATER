package ch.nte.wro.main;

import ch.nte.wro.base.Robot;
import ch.nte.wro.variables.GlobalSensors;
import ch.nte.wro.variables.MainVariables;
import ch.nte.wro.variables.SynchedVariables;
import lejos.hardware.Sound;
import lejos.utility.Delay;

public class TestMain {
	
	public static final int speed = 200;
	
	 public static void main(String[] args) {
		Robot bot = new Robot("Robot", MainVariables.mLeft, MainVariables.mRight);

		init(bot);
			
		Delay.msDelay(5000);
		Sound.beep();
		Delay.msDelay(2500);
		Sound.beep();
		Delay.msDelay(1250);
		Sound.beep();
		Delay.msDelay(512);
		Sound.beep();
		Delay.msDelay(512);
		Sound.twoBeeps();

		bot.followLine(speed, "double.cross", 0, 60, bot.getSensorOnPort(1), bot.getSensorOnPort(2));
		Handling.unloadSandBagsInHouse(bot, speed);
		bot.oneStepBelt(speed, false);
		Delay.msDelay(2000);
		bot.oneStepBelt(speed, false);
		Delay.msDelay(2000);
							
		
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



