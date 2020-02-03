package ch.nte.wro.threds;

import ch.nte.wro.variables.SynchedFloat;
import ch.nte.wro.variables.SynchedVariables;
import lejos.utility.Delay;

public class DynamicSensitivity extends Thread {
	
	private float sensitivityFrom;
	private float sensitivityTo;
	private int time;
	private SynchedFloat carrier;
	
	public DynamicSensitivity(float sensitivityFrom, int sensitivityTo, int time, SynchedFloat carrier) {
		this.sensitivityFrom = sensitivityFrom;
		this.sensitivityTo = sensitivityTo;
		this.time = time;
		this.carrier = carrier;
	}

	@Override
	public void run() {
		float sensitivity = sensitivityFrom;
		carrier.set(sensitivityFrom);
		if(sensitivityFrom < sensitivityTo) {
			int intervallTime = Math.round(((time)/(sensitivityTo-sensitivityFrom))*5);
			while(sensitivity < sensitivityTo) {
				Delay.msDelay(intervallTime);
				sensitivity = sensitivity + 5;
				carrier.set(sensitivity);
			}
		} else {
			int intervallTime = Math.round(((time)/(sensitivityFrom-sensitivityTo))*5);
			while(sensitivity > sensitivityTo) {
				Delay.msDelay(intervallTime);
				sensitivity = sensitivity - 5;
				carrier.set(sensitivity);
			}
		}
		SynchedVariables.globalSpeed.set(0);
	}
}
