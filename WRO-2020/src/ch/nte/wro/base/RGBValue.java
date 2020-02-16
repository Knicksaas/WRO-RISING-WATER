package ch.nte.wro.base;

public class RGBValue {

	private float red;
	private float green;
	private float blue;
	
	public RGBValue(float red, float green, float blue) {
		this.setBlue(blue);
		this.setGreen(green);
		this.setRed(red);
	}
	
	public RGBValue(float[] values) {
		this.setRed(values[0]);
		this.setGreen(values[1]);
		this.setBlue(values[2]);
	}

	public float getRed() {
		return red;
	}

	public void setRed(float red) {
		this.red = red;
	}

	public float getGreen() {
		return green;
	}

	public void setGreen(float green) {
		this.green = green;
	}

	public float getBlue() {
		return blue;
	}

	public void setBlue(float blue) {
		this.blue = blue;
	}
	
	public RGBValue getAverage(RGBValue value) {
		return new RGBValue((value.getRed()+red)/2, (value.getGreen()+green)/2, (value.getBlue()+blue)/2);
	}
	
	public boolean isEqual(RGBValue value) {
		if(value.getRed() == red && value.getGreen() == green && value.getBlue() == blue) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isEqual(float red, float green, float blue) {
		if(this.red == red && this.green == green && this.blue == blue) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isEqual(RGBValue value, float diff) {
		if(value.getRed()-diff < red && value.getRed()+diff > red) {
			if(value.getBlue()-diff < blue && value.getBlue()+diff > blue) {
				if(value.getGreen()-diff < green && value.getGreen()+diff > green) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public boolean isEqual(float red, float green, float blue, float diff) {
		if(this.red-diff < red && this.red+diff > red) {
			if(this.blue-diff < blue && this.blue+diff > blue) {
				if(this.green-diff < green && this.green+diff > green) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
