package ch.nte.wro.variables;

public class SynchedBoolean {

	private volatile boolean carrier = false;
	
	public synchronized void set(boolean value) {
		this.carrier = value;
	}
	
	public synchronized boolean get() {
		return carrier;
	}
}
