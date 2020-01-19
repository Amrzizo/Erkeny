package com.amr.codes.erkeny.views.activities;

import android.os.Bundle;

import com.amr.codes.erkeny.R;
import com.amr.codes.erkeny.views.activities.base.BaseActivity;
import com.amr.codes.erkeny.views.fragments.BaseRegisterFragment;

public class RegisterActiviy extends BaseActivity {

    private BaseRegisterFragment registerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setHeaderName(getString(R.string.str_registeration_header_txt));
        registerFragment = new BaseRegisterFragment();
        addFragmentToView(registerFragment);
    }



    @Override
    protected boolean isMenuButtonEnabled() {
        return false;
    }
}
