package ch.nte.wro.threds;

import ch.nte.wro.base.Robot;
import lejos.utility.Delay;

public class BeltCorrectionThread extends Thread {

	private Robot bot;
	private int intervalls;
	
	public BeltCorrectionThread(Robot bot, int intervalls) {
		this.intervalls = intervalls;
		this.bot = bot;
	}
	
	@Override
	public void run() {
		for(int i = 0; i<intervalls; i++) {
			bot.oneStepBelt(400, false);
			Delay.msDelay(2000);
		}
	}
}
