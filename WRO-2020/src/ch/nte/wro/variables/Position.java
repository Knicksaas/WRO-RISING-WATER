package ch.nte.wro.variables;

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
					return "red";
				} else {
					return "blue";
				}
			}
			break;
		case "blue":
			if(Status.houseMap.get("blue").isHouse()) {
				if(Status.houseMap.get("green").isHouse()) {
					return "yellow";
				} else {
					return "green";
				}
			}
			break;
		case "yellow":
			if(Status.houseMap.get("yellow").isHouse()) {
				if(Status.houseMap.get("red").isHouse()) {
					return "green";
				} else {
					return "red";
				}
			}
			break;
		case "red":
			if(Status.houseMap.get("red").isHouse()) {
				if(Status.houseMap.get("yellow").isHouse()) {
					return "blue";
				} else {
					return "yellow";
				}
			}
			break;
		}
		return null;
	}
	
	public static String getAreaOfHouse(String color) {
		if(Status.houseMap.get("green").isHouse()) {
			if(Status.houseMap.get("green").getHouseColor().equalsIgnoreCase(color)) {
				return Status.houseMap.get("green").getHouseColor();
			}
		} else if(Status.houseMap.get("blue").isHouse()) {
			if(Status.houseMap.get("blue").getHouseColor().equalsIgnoreCase(color)) {
				return Status.houseMap.get("blue").getHouseColor();
			}
		} else if(Status.houseMap.get("yellow").isHouse()) {
			if(Status.houseMap.get("yellow").getHouseColor().equalsIgnoreCase(color)) {
				return Status.houseMap.get("yellow").getHouseColor();
			}
		} else if(Status.houseMap.get("red").isHouse()) {
			if(Status.houseMap.get("red").getHouseColor().equalsIgnoreCase(color)) {
				return Status.houseMap.get("red").getHouseColor();
			}
		} 
		return null;
	}
}
