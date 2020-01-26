package ch.nte.wro.variables;

public class SynchedInt {

	private volatile int carrier = 0;
	
	public synchronized void set(int value) {
		this.carrier = value;
	}
	
	public synchronized int get() {
		return carrier;
	}
}
