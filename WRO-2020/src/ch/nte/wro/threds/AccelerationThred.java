package ch.nte.wro.threds;

import ch.nte.wro.variables.MainVariables;
import ch.nte.wro.variables.SynchedVariables;
import lejos.utility.Delay;

public class AccelerationThred extends Thread {
	
	private int speedFrom;
	private int speedTo;
	private int time;
	
	public AccelerationThred(int speedFrom, int speedTo, int time) {
		this.speedFrom = speedFrom;
		this.speedTo = speedTo;
		this.time = time;
	}

	@Override
	public void run() {
		int speed = speedFrom;
		MainVariables.mLeft.setSpeed(speed);
		MainVariables.mRight.setSpeed(speed);
		SynchedVariables.globalSpeed.set(speed);
		int intervallTime = Math.round(((time)/(speedTo-speedFrom))*5);
		while(speed < speedTo) {
			MainVariables.mLeft.setSpeed(speed);
			MainVariables.mRight.setSpeed(speed);
			SynchedVariables.globalSpeed.set(speed);
			Delay.msDelay(intervallTime);
			speed = speed + 5;
		}
		SynchedVariables.globalSpeed.set(0);
	}
}
