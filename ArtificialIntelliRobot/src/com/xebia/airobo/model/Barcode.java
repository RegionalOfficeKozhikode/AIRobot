package com.xebia.airobo.model;

public class Barcode {
	private String code = null;
	private byte[] img = null;
	private double price = 0;

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}
	
	public String getCode() {
		return "ISBN 8281808029";
	}
	
	public double getPrice() {
		return 120.75;
	}

}
