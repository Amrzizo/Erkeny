package com.amr.codes.erkeny.model.models.responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ClientRegisterResponse implements Serializable {
    private String type;
    private String updated_at;
    private String created_at;
    private float id;
    private String name ;
    private String email ;
    private String mobile ;
    @SerializedName("name")
    ArrayList<String> nameError = new ArrayList<>();
    @SerializedName("email")
    ArrayList<String> emailError = new ArrayList<>();
    @SerializedName("mobile")
    ArrayList<String> mobileError = new ArrayList<>();
    ArrayList<String> password = new ArrayList<>();


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

    public ArrayList<String> getName() {
        return nameError;
    }

    public void setName(ArrayList<String> name) {
        this.nameError = name;
    }

    public ArrayList<String> getEmail() {
        return emailError;
    }

    public void setEmail(ArrayList<String> email) {
        this.emailError = email;
    }

    public ArrayList<String> getMobile() {
        return mobileError;
    }

    public void setMobile(ArrayList<String> mobile) {
        this.mobileError = mobile;
    }


    public String getName(int zero) {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail( int zero) {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile(int zero) {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public ArrayList<String> getPassword() {
        return password;
    }

    public void setPassword(ArrayList<String> password) {
        this.password = password;
    }
}
