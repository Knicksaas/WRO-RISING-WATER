package ch.nte.wro.test;

import ch.nte.wro.base.Robot;
import ch.nte.wro.main.Initsialization;
import ch.nte.wro.variables.MainVariables;
import ch.nte.wro.variables.Position;
import ch.nte.wro.variables.SynchedVariables;
import lejos.hardware.Sound;
import lejos.utility.Delay;

public class HardwareTest {
	
	public static void main(String[] args) {
		Robot bot = new Robot("Robot", MainVariables.mLeft, MainVariables.mRight);

		init(bot);
		
		Position.botPosition = "blue";
		
		testMotors();
		testArm(bot);
		testBelt(bot);
	}
	
	private static void testMotors() {
		Sound.beep();
		MainVariables.mLeft.forward();
		MainVariables.mLeft.setSpeed(200);
		Delay.msDelay(1000);
		MainVariables.mLeft.setSpeed(0);
		MainVariables.mLeft.stop();
		
		Delay.msDelay(1000);
		
		Sound.beep();
		MainVariables.mRight.forward();
		MainVariables.mRight.setSpeed(200);
		Delay.msDelay(1000);
		MainVariables.mRight.setSpeed(0);
		MainVariables.mRight.stop();
		
		Sound.beepSequence();
	}
	
	private static void testArm(Robot bot) {
		Sound.beep();
		bot.setArmAngle(0, 200, false);
		Sound.beep();
		bot.setArmAngle(100, 200, false);
		Sound.beep();
		bot.setArmAngle(175, 200, false);
		Sound.beep();
		bot.setArmAngle(50, 200, false);
		Sound.beep();
		bot.setArmAngle(0, 200, false);
		Sound.beepSequence();
	}
	
	private static void testBelt(Robot bot) {
		Sound.beep();
		bot.oneStepBelt(200, true);
		bot.oneStepBelt(200, true);
		bot.oneStepBelt(200, true);
		
		Sound.beep();
		bot.oneStepBelt(200, false);
		bot.oneStepBelt(200, false);
		bot.oneStepBelt(200, false); 
		
		Sound.beepSequence();
	}

	private static void init(Robot bot) {
		SynchedVariables.globalSpeed.set(0);
		Initsialization.sensorInit(bot);
		Initsialization.sensitivityInit();
		Initsialization.housesInit();
	}
}
