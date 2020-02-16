package com.amr.codes.erkeny.views.fragments;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.amr.codes.erkeny.R;
import com.amr.codes.erkeny.views.activities.HomeActivity;


public class CompanyFragment extends BaseFragment {

    private View companyFragmentView;
    private ImageView addBranch, changePrice, changeCapacity, changeHours;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        companyFragmentView =  inflater.inflate(R.layout.fragment_company, container, false);
        addBranch = companyFragmentView.findViewById(R.id.id_add_new_branch);
        changePrice = companyFragmentView.findViewById(R.id.id_change_price);
        changeCapacity = companyFragmentView.findViewById(R.id.id_change_capacity);
        changeHours = companyFragmentView.findViewById(R.id.id_change_hours);


        addBranch.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                ((HomeActivity)getActivity()).addNewBranchFragmentToActivity();
            }
        });
        changePrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        changeCapacity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        changeHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return companyFragmentView;
    }

}
