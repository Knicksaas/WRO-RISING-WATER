package ch.nte.wro.threds;

import ch.nte.wro.variables.SynchedBoolean;
import lejos.utility.Delay;

public class Timer extends Thread {
	
	private int msTime;
	private SynchedBoolean carrier;
	
	public Timer(int msTime, SynchedBoolean carrier) {
		this.msTime = msTime;
		this.carrier = carrier;
	}

	@Override
	public void run() {
		Delay.msDelay(msTime);
		carrier.set(false);
	}

}
