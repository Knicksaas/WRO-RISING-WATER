package ch.nte.wro.test.main;

public class ThreadTester extends Thread {

	private SynchronisedClass carrier;
	
	public ThreadTester(SynchronisedClass carrier) {
		this.carrier = carrier;
	}
	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		carrier.set(false);
		System.out.println("Setted to false");
	}

}
