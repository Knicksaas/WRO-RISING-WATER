package ch.nte.wro.test;

import ch.nte.wro.base.Robot;
import ch.nte.wro.main.Initsialization;
import ch.nte.wro.variables.MainVariables;
import ch.nte.wro.variables.SynchedVariables;
import lejos.utility.Delay;

public class AcceleratorTest {
	public static final int speed = 200;
	
	 public static void main(String[] args) {
		Robot bot = new Robot("Robot", MainVariables.mLeft, MainVariables.mRight);

		init(bot);
		
		MainVariables.mLeft.forward();
		MainVariables.mRight.forward();
		MainVariables.mLeft.setSpeed(100);
		MainVariables.mRight.setSpeed(100);
		Delay.msDelay(5000);
		
		MainVariables.mLeft.forward();
		MainVariables.mRight.forward();
		MainVariables.mLeft.setSpeed(200);
		MainVariables.mRight.setSpeed(200);
		Delay.msDelay(5000);
		
		MainVariables.mLeft.forward();
		MainVariables.mRight.forward();
		MainVariables.mLeft.setSpeed(300);
		MainVariables.mRight.setSpeed(300);
		Delay.msDelay(5000);
		
		MainVariables.mLeft.forward();
		MainVariables.mRight.forward();
		MainVariables.mLeft.setSpeed(400);
		MainVariables.mRight.setSpeed(400);
		Delay.msDelay(5000);
		
		MainVariables.mLeft.forward();
		MainVariables.mRight.forward();
		MainVariables.mLeft.setSpeed(500);
		MainVariables.mRight.setSpeed(500);
		Delay.msDelay(5000);
		
		MainVariables.mLeft.forward();
		MainVariables.mRight.forward();
		MainVariables.mLeft.setSpeed(600);
		MainVariables.mRight.setSpeed(600);
		Delay.msDelay(5000);
		
		MainVariables.mLeft.forward();
		MainVariables.mRight.forward();
		MainVariables.mLeft.setSpeed(700);
		MainVariables.mRight.setSpeed(700);
		Delay.msDelay(5000);
		
		MainVariables.mLeft.forward();
		MainVariables.mRight.forward();
		MainVariables.mLeft.setSpeed(800);
		MainVariables.mRight.setSpeed(800);
		Delay.msDelay(5000);
	}
	
	
	private static void init(Robot bot) {
		SynchedVariables.globalSpeed.set(0);
		Initsialization.sensorInit(bot);
		Initsialization.sensitivityInit();
		Initsialization.housesInit();
	}
}
