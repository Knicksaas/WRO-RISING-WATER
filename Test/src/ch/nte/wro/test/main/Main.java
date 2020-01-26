package ch.nte.wro.test.main;

public class Main {
	
	public static volatile boolean running;

	public static void main(String[] args) throws InterruptedException {
		SynchronisedClass sc = new SynchronisedClass();
		sc.set(true);
		ThreadTester thread = new ThreadTester(sc);
		thread.start();
		while(sc.get()) {
			System.out.println("running");
			Thread.sleep(100);
		}
	}
}
