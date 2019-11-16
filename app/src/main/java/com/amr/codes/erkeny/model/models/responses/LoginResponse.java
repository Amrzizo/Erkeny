package com.amr.codes.erkeny.model.models.responses;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    private String token;


    // Getter Methods

    public String getToken() {
        return token;
    }

    // Setter Methods

    public void setToken(String token) {
        this.token = token;
    }
}
