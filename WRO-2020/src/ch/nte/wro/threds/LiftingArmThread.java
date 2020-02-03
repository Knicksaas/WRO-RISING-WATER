package ch.nte.wro.threds;

import ch.nte.wro.variables.MainVariables;
import ch.nte.wro.variables.SynchedVariables;

public class LiftingArmThread extends Thread {
	
	private int angle;
	private int speed;
	
	public LiftingArmThread(int angle, int speed) {
		if(SynchedVariables.isArmMoving.get()) {
			return;
		}
		if(angle>200 || angle<0) {
			return;
		}
		this.angle = angle;
		this.speed = speed;
	}

	@Override
	public void run() {
		
		if(MainVariables.armAngle < angle) {
			MainVariables.mArm.setSpeed(speed);
			int todo = angle-MainVariables.armAngle;
			MainVariables.mArm.rotate((todo/5)*6);
			MainVariables.armAngle = angle;
		} else {
			MainVariables.mArm.setSpeed(speed);
			int todo = MainVariables.armAngle-angle;
			todo *= -1;
			MainVariables.mArm.rotate((todo/5)*6);
			MainVariables.armAngle = angle;
		}
		
	}
}
