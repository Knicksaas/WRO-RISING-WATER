package ch.nte.wro.threds;

import lejos.utility.Delay;

public class Timer implements Runnable{
	
	private int msTime;
	private boolean carier;
	
	public Timer(int msTime, boolean carier) {
		this.msTime = msTime;
		this.carier = carier;
	}

	@Override
	public void run() {
		Delay.msDelay(msTime);
		if(carier == true) {
			carier = false;
		}
	}

}
