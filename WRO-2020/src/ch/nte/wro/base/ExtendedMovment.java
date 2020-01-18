package ch.nte.wro.base;

import ch.nte.wro.threds.AccelerationThred;

public class ExtendedMovment extends BasicMovment{

	public void accelerate(int speedFrom, int speedTo, int msTime) {
		AccelerationThred thread = new AccelerationThred(speedFrom, speedTo, msTime);
		thread.run();
	}
}
