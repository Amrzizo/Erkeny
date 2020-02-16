package com.amr.codes.erkeny.views.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.amr.codes.erkeny.R;
import com.amr.codes.erkeny.control.Controller;
import com.amr.codes.erkeny.model.models.requests.AddBranchRequest;
import com.amr.codes.erkeny.views.activities.HomeActivity;
import com.amr.codes.erkeny.views.activities.base.BaseActivity;

@RequiresApi(api = Build.VERSION_CODES.M)
public class AddNewBranchFragment extends BaseFragment {


    private View addNewBranchFragmentView;
    private EditText capacity, price, hourFrom, hourTo;
    private Button addBranch;
    private Location currentLocation;
    private String token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        addNewBranchFragmentView = inflater.inflate(R.layout.fragment_add_new_branch, container, false);
        capacity = addNewBranchFragmentView.findViewById(R.id.capacity_editText);
        price = addNewBranchFragmentView.findViewById(R.id.hour_price_editText);
        hourFrom = addNewBranchFragmentView.findViewById(R.id.from_hour_editText);
        hourTo = addNewBranchFragmentView.findViewById(R.id.to_hour_editText);
        token = Controller.getInstance().getTokenFromSharedPreferences((BaseActivity) getActivity());

        addBranch = addNewBranchFragmentView.findViewById(R.id.add_new_branch_button);
        addBranch.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                if (Controller.getInstance().isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION, (BaseActivity) getActivity(), HomeActivity.REQUEST_LOCATION_CODE)) {

                    addNewBranch();

                } else {
                    Controller.getInstance().openGPSSettingScreen(getActivity());
                }

            }
        });


        return addNewBranchFragmentView;
    }

    private void addNewBranch() {
        currentLocation = Controller.getInstance().getLocation((BaseActivity)getActivity());
        AddBranchRequest branchRequest = new AddBranchRequest(hourFrom.getText().toString(),
                Integer.parseInt(price.getText().toString()), currentLocation.getLongitude(), hourTo.getText().toString(),
                currentLocation.getLatitude(), token, Integer.parseInt(capacity.getText().toString()));
        Controller.getInstance().getServerApis().addNewBranch(branchRequest, Controller.getInstance().getDefaultHeader());
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == Controller.REQUEST_LOCATION_CODE && grantResults[0]== PackageManager.PERMISSION_GRANTED){
            addNewBranch();
        }
    }
}
