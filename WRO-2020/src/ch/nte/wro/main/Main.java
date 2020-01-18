package ch.nte.wro.main;

import ch.nte.wro.base.Robot;
import ch.nte.wro.variables.GlobalSensors;
import ch.nte.wro.variables.MainVariables;
import lejos.hardware.motor.EV3LargeRegulatedMotor;

public class Main {
	
	 public static void main(String[] args) {
		Robot bot = new Robot("Robot", new EV3LargeRegulatedMotor(MainVariables.portMotorLeft), new EV3LargeRegulatedMotor(MainVariables.portMotorRight));
		sensorInit(bot);
	}
	 
	private static void sensorInit(Robot bot) {
		bot.setSensorOnPort(GlobalSensors.colorSensor1, 1);
		bot.getSensorOnPort(1).setMode("red");
		bot.getSensorOnPort(1).checkSenor();
		bot.getSensorOnPort(1).getValue();
	}
}
