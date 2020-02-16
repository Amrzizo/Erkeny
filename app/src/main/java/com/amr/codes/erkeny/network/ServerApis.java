package com.amr.codes.erkeny.network;

import com.amr.codes.erkeny.model.models.requests.AddBranchRequest;
import com.amr.codes.erkeny.model.models.responses.AddBranchResponse;
import com.amr.codes.erkeny.model.models.responses.BranchesItem;
import com.amr.codes.erkeny.model.models.responses.CompanyDataResponse;
import com.amr.codes.erkeny.model.models.responses.LoginResponse;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ServerApis {

    @POST("login")
    Call<LoginResponse> login( @Body Object loginRequestBody, @HeaderMap Map<String, String> headers);

    @POST("register/client")
    Call<JsonElement> registerClient(@Body Object clientRegisterBody, @HeaderMap Map<String, String> headers);

    @POST("register/company")
    Call<JsonElement> registerCompany(@Body Object companyRegisterBody, @HeaderMap Map<String, String> headers);

    @GET("companies")
    Call<CompanyDataResponse> getCompanyData(@Query("token") String token);

    @GET("branches")
    Call<ArrayList<BranchesItem>> getNearBranches(@Query("token") String token, @Query("lng") String lng,
                                                  @Query("lat") String lat, @Query("currentTime") String currentTime,
                                                  @HeaderMap Map<String, String> headers);

//    @PUT("change-capacity")
//    changeCapacity();

    @POST("add-branch")
    Call<AddBranchResponse> addNewBranch(@Body AddBranchRequest request,@HeaderMap Map<String, String> headers );



}
