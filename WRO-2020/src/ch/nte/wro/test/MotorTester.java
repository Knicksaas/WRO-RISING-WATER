package ch.nte.wro.test;

import ch.nte.wro.base.Robot;
import ch.nte.wro.main.Initsialization;
import ch.nte.wro.main.MovmentBlocks;
import ch.nte.wro.variables.MainVariables;
import ch.nte.wro.variables.Position;

public class MotorTester {

	public static void main(String[] args) {
		Robot bot = new Robot("ae", MainVariables.mLeft, MainVariables.mRight);
		Initsialization.sensitivityInit();
		Initsialization.sensorInit(bot);
		Initsialization.synchInit();
		
		Position.botPosition = "green";
		
		MovmentBlocks.changeSide(bot, 200, "right", "left");
	}
}
