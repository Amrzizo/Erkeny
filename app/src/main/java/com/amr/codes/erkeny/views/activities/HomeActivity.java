package com.amr.codes.erkeny.views.activities;

import android.os.Bundle;

import com.amr.codes.erkeny.R;
import com.amr.codes.erkeny.views.activities.base.BaseActivity;
import com.amr.codes.erkeny.views.fragments.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;

public class HomeActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHeaderName(getString(R.string.str_home_header_txt));

        SupportMapFragment mapFragment = new SupportMapFragment();

        addFragmentToView(mapFragment);
    }


}
