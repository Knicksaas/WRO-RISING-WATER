package ch.nte.wro.threds;

import ch.nte.wro.variables.MainVariables;
import ch.nte.wro.variables.SynchedVariables;
import lejos.utility.Delay;

public class AccelerationThred extends Thread {
	
	private int speedFrom;
	private int speedTo;
	private int time;
	private boolean wasLeft = false;
	
	public AccelerationThred(int speedFrom, int speedTo, int time) {
		this.speedFrom = speedFrom;
		this.speedTo = speedTo;
		this.time = time;
	}
	
	private void updateSpeed(int speed) {
		if(wasLeft) {
			MainVariables.mRight.setSpeed(speed);
			MainVariables.mLeft.setSpeed(speed);
		} else {
			MainVariables.mLeft.setSpeed(speed);
			MainVariables.mRight.setSpeed(speed);
		}
		wasLeft = !wasLeft;
	}
	
	@Override
	public void run() {
		int speed = speedFrom;
		updateSpeed(speed);
		SynchedVariables.globalSpeed.set(speed);
		if(speed < speedTo) {
			int intervallTime = Math.round(((time)/(speedTo-speedFrom))*5);
			while(speed < speedTo) {
				updateSpeed(speed);
				SynchedVariables.globalSpeed.set(speed);
				Delay.msDelay(intervallTime);
				speed = speed + 5;
			}
		} else {
			int intervallTime = Math.round(((time)/(speedFrom-speedTo))*5);
			while(speed > speedTo) {
				updateSpeed(speed);
				SynchedVariables.globalSpeed.set(speed);
				Delay.msDelay(intervallTime);
				speed = speed - 5;
			}
		}
		
		SynchedVariables.globalSpeed.set(0);
		
	}
}
