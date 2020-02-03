package ch.nte.wro.threds;

import ch.nte.wro.variables.ConveyorbeltStatus;
import ch.nte.wro.variables.MainVariables;
import ch.nte.wro.variables.SynchedVariables;

public class ConveyorBeltThread extends Thread {

	private int speed;
	private boolean forward;
	
	public ConveyorBeltThread(int speed, boolean forward) {
		this.speed = speed;
		this.forward = forward;
		
		if(SynchedVariables.isBeltMoving.get()) {
			return;
		}
	}
	
	@Override
	public void run() {
		SynchedVariables.isBeltMoving.set(true);
		MainVariables.mBelt.setSpeed(speed);
		if(forward) {
			MainVariables.mBelt.rotate(144);
			ConveyorbeltStatus.slot3 = ConveyorbeltStatus.slot2;
			ConveyorbeltStatus.slot2 = ConveyorbeltStatus.slot1;
			ConveyorbeltStatus.slot1 = null;
			
		} else {
			MainVariables.mBelt.rotate(-144);
			ConveyorbeltStatus.slot1 = ConveyorbeltStatus.slot2;
			ConveyorbeltStatus.slot2 = ConveyorbeltStatus.slot3;
			ConveyorbeltStatus.slot3 = null;

			

		}
		SynchedVariables.isBeltMoving.set(false);
	}
}
