package com.amr.codes.erkeny.views.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amr.codes.erkeny.R;
import com.amr.codes.erkeny.control.Controller;
import com.amr.codes.erkeny.model.models.User;
import com.amr.codes.erkeny.model.models.requests.LoginRequest;
import com.amr.codes.erkeny.model.models.responses.LoginResponse;
import com.amr.codes.erkeny.network.RetrofitClientInstance;
import com.amr.codes.erkeny.network.ServerApis;
import com.amr.codes.erkeny.views.activities.RegisterActiviy;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginFragment extends BaseFragment {

    public  static  String TOKEN = "token_key";
    private View loginFragmentView;
    private EditText userName;
    private EditText password;
    private TextView register;
    private Button loginButton;
    private ProgressDialog progressDoalog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        loginFragmentView = inflater.inflate(R.layout.fragment_login, container, false);


        userName = (EditText) loginFragmentView.findViewById(R.id.user_name_edit_txt);
        password = (EditText) loginFragmentView.findViewById(R.id.password_edit_text);
        loginButton = (Button) loginFragmentView.findViewById(R.id.login_button);
        register = (TextView) loginFragmentView.findViewById(R.id.register_txt);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), RegisterActiviy.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                doValidationAndContinue();
            }
        });


        return loginFragmentView;
    }

    private void doValidationAndContinue() {

        LoginRequest loginRequest = null;
        if (userName.getText() == null || userName.
                getText().toString().equals("")) {

            userName.setError(getString(R.string.str_enter_valid_value));

        } else if (password.getText() == null ||
                password.getText().toString().equals("")) {

            password.setError(getString(R.string.str_enter_valid_value));

        } else {
            loginRequest = new LoginRequest();
            loginRequest.setEmail(userName.getText().toString());
            loginRequest.setPassword(password.getText().toString());

//            loginRequest = Controller.getInstance(getActivity()).login(userName.getText().toString(), password.getText().toString());
            if (loginRequest != null) {

            sendRequest(loginRequest);

            } else {

                Toast.makeText(getActivity(), getString(R.string.str_invalid_login_message), Toast.LENGTH_LONG).show();
            }
        }


    }

    private void sendRequest(LoginRequest loginRequest) {

        progressDoalog = new ProgressDialog(getActivity());
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        ServerApis service = RetrofitClientInstance.getRetrofitInstance().create(ServerApis.class);
        Call<LoginResponse> call = service.login(loginRequest, Controller.getInstance().getDefaultHeader());
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                progressDoalog.dismiss();

               if( response != null && response.body()!=null ) {

                   String t = response.body().getToken();
                   saveTokenToSharedPreferences(t);
               }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_LONG);
                progressDoalog.dismiss();
            }
        });

    }

    public void saveTokenToSharedPreferences(String token){

        SharedPreferences preferences = getActivity().getPreferences(Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(TOKEN, token);

        editor.commit();


    }


}
