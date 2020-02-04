package com.amr.codes.erkeny.model.models.responses;

import com.google.gson.annotations.SerializedName;

public class BranchesItem{

	@SerializedName("hour_price")
	private int hourPrice;

	@SerializedName("hours_from")
	private String hoursFrom;

	@SerializedName("company_id")
	private int companyId;

	@SerializedName("lng")
	private double lng;

	@SerializedName("hours_to")
	private String hoursTo;

	@SerializedName("id")
	private int id;

	@SerializedName("lat")
	private double lat;

	@SerializedName("capacity")
	private int capacity;

	public void setHourPrice(int hourPrice){
		this.hourPrice = hourPrice;
	}

	public int getHourPrice(){
		return hourPrice;
	}

	public void setHoursFrom(String hoursFrom){
		this.hoursFrom = hoursFrom;
	}

	public String getHoursFrom(){
		return hoursFrom;
	}

	public void setCompanyId(int companyId){
		this.companyId = companyId;
	}

	public int getCompanyId(){
		return companyId;
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

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setLat(double lat){
		this.lat = lat;
	}

	public double getLat(){
		return lat;
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
			"BranchesItem{" + 
			"hour_price = '" + hourPrice + '\'' + 
			",hours_from = '" + hoursFrom + '\'' + 
			",company_id = '" + companyId + '\'' + 
			",lng = '" + lng + '\'' + 
			",hours_to = '" + hoursTo + '\'' + 
			",id = '" + id + '\'' + 
			",lat = '" + lat + '\'' + 
			",capacity = '" + capacity + '\'' + 
			"}";
		}
}