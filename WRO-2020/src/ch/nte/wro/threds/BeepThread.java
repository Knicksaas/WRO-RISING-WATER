package ch.nte.wro.threds;

import lejos.hardware.Sound;

public class BeepThread extends Thread {

	@Override
	public void run() {
		Sound.beep();
	}
}
