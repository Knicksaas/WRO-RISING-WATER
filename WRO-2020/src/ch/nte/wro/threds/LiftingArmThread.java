package ch.nte.wro.threds;

import ch.nte.wro.variables.MainVariables;
import ch.nte.wro.variables.SynchedBoolean;
import ch.nte.wro.variables.SynchedVariables;
import lejos.utility.Delay;

public class LiftingArmThread extends Thread {
	
	private int angle;
	private int speed;
	private int delay = 0;
	SynchedBoolean immediateReturn = new SynchedBoolean();
	
	public LiftingArmThread(int angle, int speed) {
		if(SynchedVariables.isArmMoving.get()) {
			return;
		}
		if(angle>201 || angle<-1) {
			return;
		}
		this.angle = angle;
		this.speed = speed;
		immediateReturn.set(true);
	}
	
	public LiftingArmThread(int angle, int speed, int msDelay) {
		if(SynchedVariables.isArmMoving.get()) {
			return;
		}
		if(angle>201 || angle<-1) {
			return;
		}
		this.angle = angle;
		this.speed = speed;
		this.delay = msDelay;
		immediateReturn.set(true);
	}
	
	public LiftingArmThread(int angle, int speed, SynchedBoolean carrier) {
		if(SynchedVariables.isArmMoving.get()) {
			return;
		}
		if(angle>201 || angle<-1) {
			return;
		}
		this.angle = angle;
		this.speed = speed;
		this.immediateReturn = carrier;
	}

	@Override
	public void run() {
		Delay.msDelay(delay);
		if(MainVariables.armAngle < angle) {
			MainVariables.mArm.setSpeed(speed);
			int todo = angle-MainVariables.armAngle;
			MainVariables.mArm.rotate((int) Math.round(todo*10/3));
			MainVariables.armAngle = angle;
		} else {
			MainVariables.mArm.setSpeed(speed);
			int todo = MainVariables.armAngle-angle;
			todo *= -1;
			MainVariables.mArm.rotate((int) Math.round(todo*10/3));
			MainVariables.armAngle = angle;
		}
		immediateReturn.set(false);
	}
}
