package ch.nte.wro.variables;

public class SynchedFloat {

	private volatile float carrier = 0;
	
	public synchronized void set(float value) {
		this.carrier = value;
	}
	
	public synchronized float get() {
		return carrier;
	}
	
	public synchronized float setGet(float value) {
		this.carrier = value;
		return value;
	}
}
