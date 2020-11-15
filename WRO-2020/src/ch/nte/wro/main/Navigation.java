package ch.nte.wro.main;

import ch.nte.wro.base.Robot;
import ch.nte.wro.threds.BeltCorrectionThread;
import ch.nte.wro.variables.Position;
import ch.nte.wro.variables.Status;

public class Navigation {
	
	public static void navigateTo(Robot bot, int speed, String startArea, String endArea) {
		String addition = startArea+endArea;
		if(addition.contains("green") && addition.contains("blue")) {
			MovmentBlocks.driveToOtherHouseSide(bot, speed);
		} else if (addition.contains("yellow") && addition.contains("red")) {
			MovmentBlocks.driveToOtherHouseSide(bot, speed);
		} else if (addition.contains("green") && addition.contains("yellow")) {
			MovmentBlocks.changeSide(bot, speed, "right", "left");
		} else if (addition.contains("blue") && addition.contains("red")) {
			MovmentBlocks.changeSide(bot, speed, "left", "right");
		} else if (addition.contains("green") && addition.contains("red")) {
			if(startArea.equalsIgnoreCase("green")) {
				MovmentBlocks.changeSide(bot, speed, "right", "right");
			} else {
				MovmentBlocks.changeSide(bot, speed, "left", "left");
			}
		} else if (addition.contains("blue") && addition.contains("yellow")) {
			if(startArea.equalsIgnoreCase("blue")) {
				MovmentBlocks.changeSide(bot, speed, "left", "left");
			} else {
				MovmentBlocks.changeSide(bot, speed, "right", "right");
			}
		}
	}

	public static void unloadEvacuationRequests(Robot bot, int speed, String startPoint) {
		switch (Position.getAreaOfNearestHouse(startPoint)) {
			case "green":
				MovmentBlocks.startPointToLine(bot, speed, "left", startPoint);
				break;
			case "blue":
				MovmentBlocks.startPointToLine(bot, speed, "right", startPoint);
				break;
			case "yellow":
				MovmentBlocks.startPointToLine(bot, speed, "left", startPoint);
				break;
			case "red":
				MovmentBlocks.startPointToLine(bot, speed, "right", startPoint);
				break;
		}
		MovmentBlocks.driveToHouse(bot, speed, "diini Mueter");
		navigateTo(bot, speed, Position.botPosition, Position.getAreaOfNearestHouse(Position.botPosition));
		MovmentBlocks.driveToHouse(bot, speed, "diini Mueter");
	}
	
	public static void upAndUnloadSandbags(Robot bot, int speed) {
		String firstArea = Position.getNearestSandBagUploadArea(Position.botPosition);
		navigateTo(bot, speed, Position.botPosition, firstArea);
		switch (Position.botPosition) {
		case "green":
			MovmentBlocks.pickUpSandBags(bot, speed, "right");
			break;
		case "blue":
			MovmentBlocks.pickUpSandBags(bot, speed, "left");
			break;
		case "yellow":
			MovmentBlocks.pickUpSandBags(bot, speed, "right");
			break;
		case "red":
			MovmentBlocks.pickUpSandBags(bot, speed, "left");
			break;
		}
		navigateTo(bot, speed, Position.botPosition, Position.getAreaOfHouse(Status.slot2));
		MovmentBlocks.driveToHouse(bot, speed, "sandbags");
		new BeltCorrectionThread(bot, 4).start();
		String secondArea = Position.getNearestSandBagUploadArea(Position.botPosition);
		if(secondArea.equalsIgnoreCase(firstArea)) {
			for(int i = 1; i<5; i++) {
				switch (i) {
				case 1:
					secondArea = Position.getNearestSandBagUploadArea("green");
					break;
				case 2:
					secondArea = Position.getNearestSandBagUploadArea("blue");
					break;
				case 3:
					secondArea = Position.getNearestSandBagUploadArea("yellow");
					break;
				case 4:
					secondArea = Position.getNearestSandBagUploadArea("red");
					break;
				}
				if(secondArea != null) {
					if(!secondArea.equalsIgnoreCase(firstArea)) {
						i = 6;
					}
				}
			}
		}
		navigateTo(bot, speed, Position.botPosition, secondArea);
		switch (Position.botPosition) {
		case "green":
			MovmentBlocks.pickUpSandBags(bot, speed, "right");
			break;
		case "blue":
			MovmentBlocks.pickUpSandBags(bot, speed, "left");
			break;
		case "yellow":
			MovmentBlocks.pickUpSandBags(bot, speed, "right");
			break;
		case "red":
			MovmentBlocks.pickUpSandBags(bot, speed, "left");
			break;
		}
		navigateTo(bot, speed, Position.botPosition, Position.getAreaOfHouse(Status.slot2));
		MovmentBlocks.driveToHouse(bot, speed, "sandbags");
	}
	
	public static void upAndUnloadOneSandbag(Robot bot, int speed) {
		String firstArea = Position.getNearestSandBagUploadArea(Position.botPosition);
		navigateTo(bot, speed, Position.botPosition, firstArea);
		switch (Position.botPosition) {
		case "green":
			MovmentBlocks.pickUpSandBags(bot, speed, "right");
			break;
		case "blue":
			MovmentBlocks.pickUpSandBags(bot, speed, "left");
			break;
		case "yellow":
			MovmentBlocks.pickUpSandBags(bot, speed, "right");
			break;
		case "red":
			MovmentBlocks.pickUpSandBags(bot, speed, "left");
			break;
		}
		navigateTo(bot, speed, Position.botPosition, Position.getAreaOfHouse(Status.slot2));
		MovmentBlocks.driveToHouse(bot, speed, "sandbags");
		new BeltCorrectionThread(bot, 4).start();
	}
	
	public static void driveToStartPosition(Robot bot, int speed, String startPosition) {
		switch (startPosition) {
			case "R5":
				navigateTo(bot, speed, Position.botPosition, "red");
				MovmentBlocks.driveToStartPoint(bot, speed, "right");
				break;
			case "R6":
				navigateTo(bot, speed, Position.botPosition, "blue");
				MovmentBlocks.driveToStartPoint(bot, speed, "left");
		}
	}
}
