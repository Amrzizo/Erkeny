package com.amr.codes.erkeny.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amr.codes.erkeny.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePriceFragment extends Fragment {


    public ChangePriceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_price, container, false);
    }

}