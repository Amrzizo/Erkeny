package com.amr.codes.erkeny.network;

import com.amr.codes.erkeny.model.models.responses.ClientRegisterResponse;
import com.amr.codes.erkeny.model.models.responses.CompanyRegisterResponse;
import com.amr.codes.erkeny.model.models.responses.LoginResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface ServerApis {

    @POST("login")
    Call<LoginResponse> login( @Body Object loginRequestBody, @HeaderMap Map<String, String> headers);

    @GET("register/client")
    Call<ClientRegisterResponse> registerClient(@Body Object clientRegisterBody, @HeaderMap Map<String, String> headers);

    @GET("register/company")
    Call<CompanyRegisterResponse> registerCompany(@Body Object companyRegisterBody, @HeaderMap Map<String, String> headers);
}
