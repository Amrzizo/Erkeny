package com.amr.codes.erkeny.network;

import com.amr.codes.erkeny.model.models.responses.ClientRegisterSuccess;
import com.amr.codes.erkeny.model.models.responses.LoginResponse;
import com.google.gson.JsonElement;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ServerApis {

    @POST("login")
    Call<LoginResponse> login( @Body Object loginRequestBody, @HeaderMap Map<String, String> headers);

    @POST("register/client")
    Call<JsonElement> registerClient(@Body Object clientRegisterBody, @HeaderMap Map<String, String> headers);

    @POST("register/company")
    Call<JsonElement> registerCompany(@Body Object companyRegisterBody, @HeaderMap Map<String, String> headers);

    @GET("companies")
    Call<JsonElement> listofcompanies(@Body Object companyRegisterBody, @HeaderMap Map<String, String> headers);

    @PUT("change_capacity")
    Call<JsonElement> changecapacity(@Body Object companyRegisterBody, @HeaderMap Map<String, String> headers);

    @PUT("change-price")
    Call<JsonElement> changeprice(@Body Object companyRegisterBody, @HeaderMap Map<String, String> headers);

    @PUT("change-hours")
    Call<JsonElement> changehours(@Body Object companyRegisterBody, @HeaderMap Map<String, String> headers);

    @POST("add-branch")
    Call<LoginResponse> addbranch( @Body Object loginRequestBody, @HeaderMap Map<String, String> headers);

    @GET("branches")
    Call<LoginResponse> branches( @Body Object loginRequestBody, @HeaderMap Map<String, String> headers);


}
