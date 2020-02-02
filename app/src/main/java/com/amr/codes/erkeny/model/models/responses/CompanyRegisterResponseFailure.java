package com.amr.codes.erkeny.model.models.responses;

import java.util.List;

public class CompanyRegisterResponseFailure {
	private List<String> hourPrice;
	private List<String> hoursFrom;
	private List<String> image;
	private List<String> lng;
	private List<String> name;
	private List<String> mobile;
	private List<String> hoursTo;
	private List<String> email;
	private List<String> lat;
	private List<String> capacity;

	public void setHourPrice(List<String> hourPrice){
		this.hourPrice = hourPrice;
	}

	public List<String> getHourPrice(){
		return hourPrice;
	}

	public void setHoursFrom(List<String> hoursFrom){
		this.hoursFrom = hoursFrom;
	}

	public List<String> getHoursFrom(){
		return hoursFrom;
	}

	public void setImage(List<String> image){
		this.image = image;
	}

	public List<String> getImage(){
		return image;
	}

	public void setLng(List<String> lng){
		this.lng = lng;
	}

	public List<String> getLng(){
		return lng;
	}

	public void setName(List<String> name){
		this.name = name;
	}

	public List<String> getName(){
		return name;
	}

	public void setMobile(List<String> mobile){
		this.mobile = mobile;
	}

	public List<String> getMobile(){
		return mobile;
	}

	public void setHoursTo(List<String> hoursTo){
		this.hoursTo = hoursTo;
	}

	public List<String> getHoursTo(){
		return hoursTo;
	}

	public void setEmail(List<String> email){
		this.email = email;
	}

	public List<String> getEmail(){
		return email;
	}

	public void setLat(List<String> lat){
		this.lat = lat;
	}

	public List<String> getLat(){
		return lat;
	}

	public void setCapacity(List<String> capacity){
		this.capacity = capacity;
	}

	public List<String> getCapacity(){
		return capacity;
	}

	@Override
 	public String toString(){
		return 
			"CompanyRegisterResponseFailure{" +
			"hour_price = '" + hourPrice + '\'' + 
			",hours_from = '" + hoursFrom + '\'' + 
			",image = '" + image + '\'' + 
			",lng = '" + lng + '\'' + 
			",name = '" + name + '\'' + 
			",mobile = '" + mobile + '\'' + 
			",hours_to = '" + hoursTo + '\'' + 
			",email = '" + email + '\'' + 
			",lat = '" + lat + '\'' + 
			",capacity = '" + capacity + '\'' + 
			"}";
		}
}