package com.amr.codes.erkeny.model.models.requests;

import java.io.Serializable;

public class CompanyRegisterRequest implements Serializable {

    private String name;
    private String email;
    private String password;
    private String password_confirmation;
    private String mobile;
    private String image;
    private double capacity;
    private double hour_price;
    private double lng;
    private double lat;
    private String hours_from;
    private String hours_to;

    public CompanyRegisterRequest(String name, String email, String password, String password_confirmation, String mobile, String image, double capacity, double hour_price, double lng, double lat, String hours_from, String hours_to) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.password_confirmation = password_confirmation;
        this.mobile = mobile;
        this.image = image;
        this.capacity = capacity;
        this.hour_price = hour_price;
        this.lng = lng;
        this.lat = lat;
        this.hours_from = hours_from;
        this.hours_to = hours_to;
    }


    // Getter Methods

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPassword_confirmation() {
        return password_confirmation;
    }

    public String getMobile() {
        return mobile;
    }

    public String getImage() {
        return image;
    }

    public double getCapacity() {
        return capacity;
    }

    public double getHour_price() {
        return hour_price;
    }

    public double getLng() {
        return lng;
    }

    public double getLat() {
        return lat;
    }

    public String getHours_from() {
        return hours_from;
    }

    public String getHours_to() {
        return hours_to;
    }

    // Setter Methods

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPassword_confirmation(String password_confirmation) {
        this.password_confirmation = password_confirmation;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public void setHour_price(double hour_price) {
        this.hour_price = hour_price;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setHours_from(String hours_from) {
        this.hours_from = hours_from;
    }

    public void setHours_to(String hours_to) {
        this.hours_to = hours_to;
    }
}
