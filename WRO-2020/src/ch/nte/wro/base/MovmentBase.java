package ch.nte.wro.base;

public interface MovmentBase {

	public void forward(int speed);
	public void backward(int speed);
	public void turnAtPlace(int speed, String side);
	public void motorOn(int speed, String side, boolean forward);
	public void setSpeeds(int speed);
	public void motorsOff();
	public void setSpeed(int speed, String side);
}
