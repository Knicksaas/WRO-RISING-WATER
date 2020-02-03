package ch.nte.wro.threds;

import ch.nte.wro.variables.MainVariables;
import ch.nte.wro.variables.SynchedVariables;

public class ConveyorBeltThread extends Thread {

	private int speed;
	private boolean forward;
	
	public ConveyorBeltThread(int speed, boolean forward) {
		this.speed = speed;
		this.forward = forward;
	}
	
	@Override
	public void run() {
		SynchedVariables.isBeltMoving.set(true);
		MainVariables.mBelt.setSpeed(speed);
		if(forward) {
			MainVariables.mBelt.rotate(144);
		} else {
			MainVariables.mBelt.rotate(-144);
		}
		SynchedVariables.isBeltMoving.set(false);
	}
}
