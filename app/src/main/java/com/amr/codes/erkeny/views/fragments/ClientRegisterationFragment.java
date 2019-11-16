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
import com.amr.codes.erkeny.model.models.responses.ClientRegisterResponse;
import com.amr.codes.erkeny.network.RetrofitClientInstance;
import com.amr.codes.erkeny.network.ServerApis;
import com.amr.codes.erkeny.views.activities.LoginActivity;

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
        // Inflate the layout for this fragment
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
            Call<ClientRegisterResponse> clientResponse = serverApis.registerClient(clientRegisterationRequest, headers);
            clientResponse.enqueue(new Callback<ClientRegisterResponse>() {
                @Override
                public void onResponse(Call<ClientRegisterResponse> call, Response<ClientRegisterResponse> response) {

                    progressDoalog.dismiss();
                    if (response != null && response.body() != null) {

                        if (response.body().getEmail() != null) {
                            Toast.makeText(getActivity(), response.body().getEmail().get(0), Toast.LENGTH_LONG).show();
                        } else if (response.body().getName() != null) {
                            Toast.makeText(getActivity(), response.body().getName().get(0), Toast.LENGTH_LONG).show();

                        } else if (response.body().getMobile() != null) {

                            Toast.makeText(getActivity(), response.body().getMobile().get(0), Toast.LENGTH_LONG).show();
                        } else {

                            Toast.makeText(getActivity(), getString(R.string.str_registered_successfully), Toast.LENGTH_LONG).show();

                            startActivity(new Intent(getActivity(), LoginActivity.class));
                        }


                    }
                }

                @Override
                public void onFailure(Call<ClientRegisterResponse> call, Throwable t) {

                    progressDoalog.dismiss();
                    Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_LONG);
                }
            });


        }
    }





}
