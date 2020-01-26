package ch.nte.wro.test.main;

public class SynchronisedClass {

	private volatile boolean carrier = false;
	
	public synchronized void set(boolean value) {
		this.carrier = value;
	}
	
	public synchronized boolean get() {
		return carrier;
	}
}
