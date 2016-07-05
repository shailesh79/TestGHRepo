package com.duetche.model;

public class Shop  {
	
	private String shopName;
	private String shopAddress;
	private String postCode;
	private String latitude;
	private String longitude;
	 
	public String getLatitude() {
		return latitude;
	}
	
	public void setLatitude(String latitue) {
		this.latitude = latitue;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

}
