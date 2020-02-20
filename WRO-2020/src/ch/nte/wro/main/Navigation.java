package ch.nte.wro.main;

import ch.nte.wro.base.Robot;
import ch.nte.wro.variables.Position;

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
				MovmentBlocks.changeSide(bot, speed, "righ", "right");
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
	
	public static void finishFirstHouse(Robot bot, int speed) {
		
	}
}
