package ch.nte.wro.variables;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;

public class Position {

	public static String botPosition;
	public static String botDirection;
	
	public static String getAreaOfNearestHouse(String actualArea) {
		switch (actualArea) {
		case "green":
			if(Status.houseMap.get("blue").isHouse()) {
				return "blue";
			} else if(Status.houseMap.get("yellow").isHouse()) {
				return "yellow";
			} else if(Status.houseMap.get("red").isHouse()) {
				return "red";
			}
			break;
		case "blue":
			if(Status.houseMap.get("green").isHouse()) {
				return "green";
			} else if(Status.houseMap.get("yellow").isHouse()) {
				return "yellow";
			} else if(Status.houseMap.get("red").isHouse()) {
				return "red";
			}
			break;
		case "yellow":
			if(Status.houseMap.get("blue").isHouse()) {
				return "blue";
			} else if(Status.houseMap.get("green").isHouse()) {
				return "green";
			} else if(Status.houseMap.get("red").isHouse()) {
				return "red";
			}
			break;
		case "red":
			if(Status.houseMap.get("blue").isHouse()) {
				return "blue";
			} else if(Status.houseMap.get("yellow").isHouse()) {
				return "yellow";
			} else if(Status.houseMap.get("green").isHouse()) {
				return "green";
			}
			break;
		case "R5":
			if(Status.houseMap.get("yellow").isHouse()) {
				return "yellow";
			} else if(Status.houseMap.get("red").isHouse()) {
				return "red";
			} else if(Status.houseMap.get("green").isHouse()) {
				return "green";
			} else if(Status.houseMap.get("blue").isHouse()) {
					return "blue";
				}
			break;	
		case "R6":
			if(Status.houseMap.get("green").isHouse()) {
				return "green";
			} else if(Status.houseMap.get("blue").isHouse()) {
				return "blue";
			} else if(Status.houseMap.get("yellow").isHouse()) {
				return "yellow";
			} else if(Status.houseMap.get("red").isHouse()) {
					return "red";
				}
			break;	
		}
		return null;
	}
	
	public static String getNearestSandBagUploadArea(String actualArea) {
		switch (actualArea) {
		case "green":
			if(Status.houseMap.get("green").isHouse()) {
				if(Status.houseMap.get("blue").isHouse()) {
					if(Status.houseMap.get("red").isHouse()) {
						return "yellow";
					} else {
						return "red";
					}
				} else {
					return "blue";
				}
			} else {
				return "green";
			}
		case "blue":
			if(Status.houseMap.get("blue").isHouse()) {
				if(Status.houseMap.get("green").isHouse()) {
					if(Status.houseMap.get("yellow").isHouse()) {
						return "red";
					} else {
						return "yellow";
					}
				} else {
					return "green";
				}
			} else {
				return "blue";
			}
		case "yellow":
			if(Status.houseMap.get("yellow").isHouse()) {
				if(Status.houseMap.get("red").isHouse()) {
					if(Status.houseMap.get("green").isHouse()) {
						return "blue";
					} else {
						return "green";
					}
				} else {
					return "red";
				}
			} else {
				return "yellow";
			}
		case "red":
			if(Status.houseMap.get("red").isHouse()) {
				if(Status.houseMap.get("yellow").isHouse()) {
					if(Status.houseMap.get("blue").isHouse()) {
						return "green";
					} else {
						return "blue";
					}
				} else {
					return "yellow";
				}
			} else {
				return "red";
			}
		}
		return null;
	}
	
	public static String getAreaOfHouse(String color) {
		if(Status.houseMap.get("green").isHouse()) {
			if(Status.houseMap.get("green").getHouseColor().equalsIgnoreCase(color)) {
				return Status.houseMap.get("green").getArea();
			}
		} 
		if(Status.houseMap.get("blue").isHouse()) {
			if(Status.houseMap.get("blue").getHouseColor().equalsIgnoreCase(color)) {
				return Status.houseMap.get("blue").getArea();
			}
		} 
		if(Status.houseMap.get("yellow").isHouse()) {
			if(Status.houseMap.get("yellow").getHouseColor().equalsIgnoreCase(color)) {
				return Status.houseMap.get("yellow").getArea();
			}
		} 
		if(Status.houseMap.get("red").isHouse()) {
			if(Status.houseMap.get("red").getHouseColor().equalsIgnoreCase(color)) {
				return Status.houseMap.get("red").getArea();
			}
		} 
		LCD.clear();
		LCD.drawString("Wrong or no color given!", 0, 0);
		Sound.buzz();
		Button.waitForAnyPress();
		return null;
	}
}
