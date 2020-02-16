package com.amr.codes.erkeny.model.models.requests;


import com.google.gson.annotations.SerializedName;


public class AddBranchRequest{

	@SerializedName("hours_from")
	private String hoursFrom;

	@SerializedName("hour_price")
	private int hourPrice;

	@SerializedName("lng")
	private double lng;

	@SerializedName("hours_to")
	private String hoursTo;

	@SerializedName("lat")
	private double lat;

	@SerializedName("token")
	private String token;

	@SerializedName("capacity")
	private int capacity;

	public AddBranchRequest(String hoursFrom, int hourPrice, double lng, String hoursTo, double lat, String token, int capacity) {
		this.hoursFrom = hoursFrom;
		this.hourPrice = hourPrice;
		this.lng = lng;
		this.hoursTo = hoursTo;
		this.lat = lat;
		this.token = token;
		this.capacity = capacity;
	}

	public void setHoursFrom(String hoursFrom){
		this.hoursFrom = hoursFrom;
	}

	public String getHoursFrom(){
		return hoursFrom;
	}

	public void setHourPrice(int hourPrice){
		this.hourPrice = hourPrice;
	}

	public int getHourPrice(){
		return hourPrice;
	}

	public void setLng(double lng){
		this.lng = lng;
	}

	public double getLng(){
		return lng;
	}

	public void setHoursTo(String hoursTo){
		this.hoursTo = hoursTo;
	}

	public String getHoursTo(){
		return hoursTo;
	}

	public void setLat(double lat){
		this.lat = lat;
	}

	public double getLat(){
		return lat;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}

	public void setCapacity(int capacity){
		this.capacity = capacity;
	}

	public int getCapacity(){
		return capacity;
	}

	@Override
 	public String toString(){
		return 
			"AddBranchRequest{" + 
			"hours_from = '" + hoursFrom + '\'' + 
			",hour_price = '" + hourPrice + '\'' + 
			",lng = '" + lng + '\'' + 
			",hours_to = '" + hoursTo + '\'' + 
			",lat = '" + lat + '\'' + 
			",token = '" + token + '\'' + 
			",capacity = '" + capacity + '\'' + 
			"}";
		}
}