package ch.nte.wro.variables;

import lejos.hardware.Button;
import lejos.hardware.Sound;

public class Status {
	
	public static String slot1 = "EvacuationRequest";
	public static String slot2 = "EvacuationRequest";
	public static String slot3;

	private static String areaGreen = null;
	private static String areaYellow = null;

	public static void initHouses(String houseRightArea, String colorHouseRight, String houseLeftArea, String colorHouseLeft) {
		if(houseRightArea.equalsIgnoreCase("green")) {
			areaGreen = colorHouseRight;
			
		} else if (houseRightArea.equalsIgnoreCase("blue")) {
			areaGreen = null;
		} else {
			Sound.buzz();
			System.out.println("Wrong house init!");
			Button.waitForAnyPress();
		}
		if(houseLeftArea.equalsIgnoreCase("yellow")) {
			areaYellow = colorHouseLeft;
		} else if (houseLeftArea.equalsIgnoreCase("red")) {
			areaYellow = null;
		} else {
			Sound.buzz();
			System.out.println("Wrong house init!");
			Button.waitForAnyPress();
		}
	}
	
	public static String getHouseOnSide(String side) {
		if(side.equalsIgnoreCase("right")) {
			if(areaGreen == null) {
				return "left";
			} else {
				return "right";
			}
		} else if (side.equalsIgnoreCase("left")) {
			if(areaYellow == null) {
				return "left";
			} else {
				return "right";
			}
		} else {
			Sound.buzz();
			System.out.println("Unknown Side! ERROR!");
			Button.waitForAnyPress();
			return null;
		}
	}
	
	public static String getColorOnSide(String side) {
		if(side.equalsIgnoreCase("right")) {
			if(areaGreen == null) {
				return "blue";
			} else {
				return "green";
			}
		} else if (side.equalsIgnoreCase("left")) {
			if(areaYellow == null) {
				return "red";
			} else {
				return "yellow";
			}
		} else {
			Sound.buzz();
			System.out.println("Unknown Side! ERROR!");
			Button.waitForAnyPress();
		}
		return null;
	}
}
