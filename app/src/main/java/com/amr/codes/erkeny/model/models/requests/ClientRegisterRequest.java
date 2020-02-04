package com.amr.codes.erkeny.model.models.requests;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ClientRegisterRequest implements Serializable {

    private String name;
    private String email;
    private String password;
    private String password_confirmation;
    private String mobile;

    public ClientRegisterRequest(String name, String email, String password, String password_confirmation, String mobile) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.password_confirmation = password_confirmation;
        this.mobile = mobile;
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
}


