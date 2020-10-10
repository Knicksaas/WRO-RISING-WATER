package ch.nte.wro.test;

import ch.nte.wro.base.Robot;
import ch.nte.wro.main.Initsialization;
import ch.nte.wro.variables.MainVariables;
import ch.nte.wro.variables.Position;
import ch.nte.wro.variables.SynchedVariables;
import lejos.hardware.Sound;
import lejos.utility.Delay;

public class TurnTester {

public static final int speed = 200;
	
	public static void main(String[] args) {
		Robot bot = new Robot("Robot", MainVariables.mLeft, MainVariables.mRight);

		init(bot);
		
		Position.botPosition = "blue";
		/*
		for(int y = 0; y < 8; y++) {
			bot.turnWithRotations(speed, "quarter", "left");
		}
		Sound.beep();
		Delay.msDelay(3000);
		Sound.buzz(); 
		
		for(int y = 0; y < 8; y++) {
			bot.turnWithRotations(speed, "quarter", "right");
		}
		Sound.beep();
		Delay.msDelay(3000);
		Sound.buzz();
		
		*/
		/*
		for(int y = 0; y < 8; y++) {
			bot.turnWithRotations(speed, "half", "left");
		}
		Sound.beep();
		Delay.msDelay(3000);
		Sound.buzz(); 
		*/
		for(int y = 0; y < 8; y++) {
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
