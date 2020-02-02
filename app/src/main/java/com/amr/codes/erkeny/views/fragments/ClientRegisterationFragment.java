package com.amr.codes.erkeny.views.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amr.codes.erkeny.R;
import com.amr.codes.erkeny.control.Controller;
import com.amr.codes.erkeny.model.models.requests.ClientRegisterRequest;
import com.amr.codes.erkeny.model.models.responses.ClientRegisterFailure;
import com.amr.codes.erkeny.model.models.responses.ClientRegisterSuccess;
import com.amr.codes.erkeny.model.models.responses.CompanyRegisterResponseFailure;
import com.amr.codes.erkeny.model.models.responses.CompanyRegisterResponseSuccess;
import com.amr.codes.erkeny.network.RetrofitClientInstance;
import com.amr.codes.erkeny.network.ServerApis;
import com.amr.codes.erkeny.views.activities.LoginActivity;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ClientRegisterationFragment extends BaseFragment {

    private View registerView;
    private EditText name;
    private EditText mobile;
    private EditText password, confirmPassword;
    private EditText email;
    private Button registeButton;
    private ProgressDialog progressDoalog;
    private Map<String, String> headers;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment_map
        registerView = inflater.inflate(R.layout.fragment_client_register,
                container, false);

        name = (EditText) registerView.findViewById(R.id.name_editText);
        confirmPassword = (EditText) registerView.findViewById(R.id.confirm_password_editText);
        email = (EditText) registerView.findViewById(R.id.email_editText);
        password = (EditText) registerView.findViewById(R.id.password_editText);
        mobile = (EditText) registerView.findViewById(R.id.hour_price_editText);
        registeButton = (Button) registerView.findViewById(R.id.register_button);
        registeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doValidationAndContinue();
            }
        });

        headers = Controller.getInstance().getDefaultHeader();

        return registerView;
    }


    private void doValidationAndContinue() {
        ClientRegisterRequest clientRegisterationRequest = null;
        if (name.getText() == null || name.getText().toString().equals("")) {

            name.setError(getString(R.string.str_enter_valid_value));

        } else if (email.getText() == null || email.getText().toString().equals("")) {

            email.setError(getString(R.string.str_enter_valid_value));
        } else if (password.getText() == null || password.getText().toString().equals("")) {

            password.setError(getString(R.string.str_enter_valid_value));


        } else if (confirmPassword.getText() == null || confirmPassword.getText().toString().equals("")) {

            confirmPassword.setError(getString(R.string.str_enter_valid_value));


        } else if (mobile.getText() == null || mobile.getText().toString().equals("")) {

            mobile.setError(getString(R.string.str_enter_valid_value));

        } else {

            progressDoalog = new ProgressDialog(getActivity());
            progressDoalog.setMessage("Loading....");
            progressDoalog.show();

            clientRegisterationRequest = new
                    ClientRegisterRequest(name.getText().toString(),
                    email.getText().toString(),
                    password.getText().toString(),
                    confirmPassword.getText().toString(),
                    mobile.getText().toString());

            ServerApis serverApis = RetrofitClientInstance.getRetrofitInstance().create(ServerApis.class);
            Call<JsonElement> clientResponse = serverApis.registerClient(clientRegisterationRequest, headers);
            clientResponse.enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                    progressDoalog.dismiss();
                    if (response != null && response.body() != null) {

                        String result = response.body().toString();
                        ClientRegisterSuccess responseSuccess = null;
                        ClientRegisterFailure responseFailure = null;

                        if (result.contains("created_at")) {
                            responseSuccess = new Gson().fromJson(result, ClientRegisterSuccess.class);
                        } else {

                            responseFailure = new Gson().fromJson(result, ClientRegisterFailure.class);

                        }


                        if(responseFailure != null){


                        if (responseFailure.getEmail() != null) {
                            Toast.makeText(getActivity(), responseFailure.getEmail().get(0), Toast.LENGTH_LONG).show();
                        } else if (responseFailure.getName() != null) {
                            Toast.makeText(getActivity(), responseFailure.getName().get(0), Toast.LENGTH_LONG).show();

                        } else if (responseFailure.getMobile() != null) {

                            Toast.makeText(getActivity(), responseFailure.getMobile().get(0), Toast.LENGTH_LONG).show();
                        }   } else {

                            Toast.makeText(getActivity(), getString(R.string.str_registered_successfully), Toast.LENGTH_LONG).show();

                            startActivity(new Intent(getActivity(), LoginActivity.class));
                        }


                    }
                }

                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {

                    progressDoalog.dismiss();
                    Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_LONG);
                }
            });


        }
    }





}
