package ch.nte.wro.base;

public interface MovmentBase {

	public void forward(int speed);
	public void backward(int speed);
	public void turnAtPlace(int speed, String side);
	public void motorOn(int speed, String side);
	public void setSpeed(int speed);
	public void motorsOff();
}
