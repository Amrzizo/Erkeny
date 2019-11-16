package com.amr.codes.erkeny.model.models.responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class CompanyRegisterResponse implements Serializable {

    private String name;
    private String type;
    private String updated_at;
    private String created_at;
    private float id;
    @SerializedName("email")
    ArrayList<String> emailError = new ArrayList<>();
    @SerializedName("mobile")
    ArrayList<String> mobileError = new ArrayList<>();
    @SerializedName("password")
    ArrayList<String> passwordError = new ArrayList<>();
    @SerializedName("image")
    ArrayList<String> imageError = new ArrayList<>();
    @SerializedName("hours_from")
    ArrayList<String> hours_fromError = new ArrayList<>();
    @SerializedName("hours_to")
    ArrayList<String> hours_toError = new ArrayList<>();
    ArrayList < String > capacity = new ArrayList <  > ();
    ArrayList < String > hour_price = new ArrayList <  > ();
    ArrayList < String > lng = new ArrayList <  > ();
    ArrayList < String > lat = new ArrayList <  > ();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
    }

    public ArrayList<String> getEmailError() {
        return emailError;
    }

    public void setEmailError(ArrayList<String> emailError) {
        this.emailError = emailError;
    }

    public ArrayList<String> getMobileError() {
        return mobileError;
    }

    public void setMobileError(ArrayList<String> mobileError) {
        this.mobileError = mobileError;
    }

    public ArrayList<String> getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(ArrayList<String> passwordError) {
        this.passwordError = passwordError;
    }

    public ArrayList<String> getImageError() {
        return imageError;
    }

    public void setImageError(ArrayList<String> imageError) {
        this.imageError = imageError;
    }

    public ArrayList<String> getHours_fromError() {
        return hours_fromError;
    }

    public void setHours_fromError(ArrayList<String> hours_fromError) {
        this.hours_fromError = hours_fromError;
    }

    public ArrayList<String> getHours_toError() {
        return hours_toError;
    }

    public void setHours_toError(ArrayList<String> hours_toError) {
        this.hours_toError = hours_toError;
    }

    public ArrayList<String> getCapacity() {
        return capacity;
    }

    public void setCapacity(ArrayList<String> capacity) {
        this.capacity = capacity;
    }

    public ArrayList<String> getHour_price() {
        return hour_price;
    }

    public void setHour_price(ArrayList<String> hour_price) {
        this.hour_price = hour_price;
    }

    public ArrayList<String> getLng() {
        return lng;
    }

    public void setLng(ArrayList<String> lng) {
        this.lng = lng;
    }

    public ArrayList<String> getLat() {
        return lat;
    }

    public void setLat(ArrayList<String> lat) {
        this.lat = lat;
    }
}
