package com.amr.codes.erkeny.views.activities;

import android.os.Bundle;

import com.amr.codes.erkeny.R;
import com.amr.codes.erkeny.views.activities.base.BaseActivity;
import com.amr.codes.erkeny.views.fragments.LoginFragment;

public class LoginActivity extends BaseActivity {

    private LoginFragment loginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setHeaderName(getString(R.string.str_login_header_txt));
        loginFragment = new LoginFragment();
        addFragmentToView(loginFragment);
    }


    @Override
    protected boolean isHeaderEnabled() {
        return false;
    }

    @Override
    protected boolean isBackEnabled() {
        return false;
    }

    @Override
    protected boolean isMenuButtonEnabled() {
        return false;
    }


}
