package ch.nte.wro.threds;

import ch.nte.wro.variables.MainVariables;
import ch.nte.wro.variables.SynchedVariables;
import lejos.utility.Delay;

public class LiftingArmThread extends Thread {
	
	private int angle;
	
	public LiftingArmThread(int angle) {
		if(SynchedVariables.isArmMoving.get()) {
			return;
		}
		if(angle>180 || angle<0) {
			return;
		}
		this.angle = angle;
	}

	@Override
	public void run() {
		int speed = 2000;
		if(MainVariables.armAngle < angle) {
			int todo = angle-MainVariables.armAngle;
			MainVariables.mArm.forward();
			MainVariables.mArm.setSpeed(speed);
			Delay.msDelay(Math.round(todo*28.33333));
			MainVariables.mArm.stop();
			MainVariables.armAngle = angle;
		} else {
			int todo = angle-MainVariables.armAngle;
			todo = todo-todo*2;
			MainVariables.mArm.backward();
			MainVariables.mArm.setSpeed(speed);
			Delay.msDelay(Math.round(todo*28.33333));
			MainVariables.mArm.stop();
			MainVariables.armAngle = angle;
		}
		
	}
}
