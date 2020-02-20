package ch.nte.wro.objects;

public class HouseSpot {

	private String area;
	private boolean house;
	private String houseColor;
	
	public HouseSpot(String area, boolean house, String houseColor) {
		this.setArea(area);
		this.setHouse(house);
		this.setHouseColor(houseColor);
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public boolean isHouse() {
		return house;
	}

	public void setHouse(boolean house) {
		this.house = house;
	}

	public String getHouseColor() {
		if(!house) {
			return null;
		}
		return houseColor;
	}

	public void setHouseColor(String houseColor) {
		if(!house) {
			return;
		}
		this.houseColor = houseColor;
	}
}
