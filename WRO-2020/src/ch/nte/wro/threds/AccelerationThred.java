package ch.nte.wro.threds;

import ch.nte.wro.variables.MainVariables;
import ch.nte.wro.variables.TempVariables;
import lejos.utility.Delay;

public class AccelerationThred implements Runnable {
	
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
		MainVariables.mLeft.setSpeed(speed);
		TempVariables.globalSpeed = speed;
		int intervallTime = Math.round((time)/(speedTo-speedFrom));
		while(speed < speedTo) {
			MainVariables.mLeft.setSpeed(speed);
			MainVariables.mLeft.setSpeed(speed);
			TempVariables.globalSpeed = speed;
			Delay.msDelay(intervallTime);
			speed++;
		}
		TempVariables.globalSpeed = 0;
	}
}
