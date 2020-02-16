package com.amr.codes.erkeny.views.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.amr.codes.erkeny.R;
import com.amr.codes.erkeny.control.Controller;
import com.amr.codes.erkeny.model.models.responses.BranchesItem;
import com.amr.codes.erkeny.model.models.responses.CompanyDataResponse;
import com.amr.codes.erkeny.views.activities.base.BaseActivity;
import com.amr.codes.erkeny.views.fragments.AddNewBranchFragment;
import com.amr.codes.erkeny.views.fragments.BaseFragment;
import com.amr.codes.erkeny.views.fragments.CompanyFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends BaseActivity implements OnMapReadyCallback, LocationListener {

    private CompanyDataResponse companyDataResponse;
    private GoogleMap mMap;
    private String locationPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    public static int REQUEST_LOCATION_CODE = 100;
    public static int MIN_TIME = 60000;
    public static int MIN_DISTANCE = 100;
    private boolean GPSEnabled;
    private Location currentLocation;
    private String token = null;
    private AddNewBranchFragment addNewBranchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHeaderName(getString(R.string.str_home_header_txt));

          token = Controller.getInstance().getTokenFromSharedPreferences(this);
        Controller.getInstance().showProgressDialog(this);
        checkLoginTypeAndContinue(token);

    }

    private void checkLoginTypeAndContinue(final String token) {

        Call<CompanyDataResponse> companiesCall = Controller.getInstance().getServerApis().getCompanyData(token);

        companiesCall.enqueue(new Callback<CompanyDataResponse>() {
            @Override
            public void onResponse(Call<CompanyDataResponse> call, Response<CompanyDataResponse> response) {

                if (response != null && response.body() != null) {

                    Controller.getInstance().cancelProgressDialog();
                    // if there is response it mean that this is company else it is Client
                    if (response.body().getUserId() >0) {

                        CompanyFragment companyFragment = new CompanyFragment();
                        addFragmentToView(companyFragment);


                    } else {

                        openMapFragment();

                    }
                }else if(response.body()== null){

                    openMapFragment();

                } else if(response.message()!= null){

                    Controller.getInstance().cancelProgressDialog();
                    Controller.getInstance().showInformationDialog(HomeActivity.this, false, response.message());
                }
            }

            @Override
            public void onFailure(Call<CompanyDataResponse> call, Throwable t) {

                Controller.getInstance().cancelProgressDialog();
                Controller.getInstance().showInformationDialog(HomeActivity.this, false, t.getLocalizedMessage());


            }
        });
    }

    private void openMapFragment() {
        SupportMapFragment mapFragment = new SupportMapFragment();
        addFragmentToView(mapFragment);
        mapFragment.getMapAsync(HomeActivity.this);
    }

    @SuppressLint("NewApi")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (Controller.getInstance().isPermissionGranted(locationPermission, this, REQUEST_LOCATION_CODE)) {

            currentLocation = getLocation();

            if(currentLocation!= null){
                getNearBranches();
            }

        }else{
            Controller.getInstance().cancelProgressDialog();
        }


    }

    @SuppressLint("MissingPermission")
    @RequiresApi(api = Build.VERSION_CODES.M)
    private Location getLocation() {

        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        GPSEnabled = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (GPSEnabled) {


                manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME, MIN_DISTANCE, this);
                currentLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);


        } else {

            Controller.getInstance().gotoGPSSetting(this);
        }

        return currentLocation;
    }

    private void getNearBranches( ) {

        Controller.getInstance().showProgressDialog(this);
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        String currentDateTimeString = sdf.format(d);
        Call<ArrayList<BranchesItem>> branchesCall = Controller.getInstance().getServerApis().getNearBranches(token,
                String.valueOf(currentLocation.getLongitude()), String.valueOf(currentLocation.getLatitude()),
                currentDateTimeString, Controller.getInstance().getDefaultHeader());
        branchesCall.enqueue(new Callback<ArrayList<BranchesItem>>() {
            @Override
            public void onResponse(Call<ArrayList<BranchesItem>> call, Response<ArrayList<BranchesItem>> response) {

                if(response!=null && response.body().size()>0){

                    drawAllGaragesLocationsOnMaps(response.body());
                    Controller.getInstance().cancelProgressDialog();
                }else{
                    Controller.getInstance().cancelProgressDialog();
                    Controller.getInstance().showInformationDialog(HomeActivity.this, false, getString(R.string.str_no_near_garages));

                }



            }

            @Override
            public void onFailure(Call<ArrayList<BranchesItem>> call, Throwable t) {
                Controller.getInstance().cancelProgressDialog();
                Controller.getInstance().showInformationDialog(HomeActivity.this, false, t.getLocalizedMessage());

            }
        });

    }

    private void drawAllGaragesLocationsOnMaps(ArrayList<BranchesItem> branchesItems) {

        for(BranchesItem item :branchesItems){
            LatLng garage = new LatLng(item.getLat(), item.getLng()); //.icon( BitmapDescriptorFactory.fromResource(R.drawable.ic_location_on))
            mMap.addMarker(new MarkerOptions().position(garage).title(item.getHoursFrom()
                    +" "+item.getHoursTo() +"\n"+item.getHourPrice()+" LE. "+item.getCapacity()+" Car"));
        }

    }


    @Override
    public void onLocationChanged(Location location) {
        if(location!= null){
            getNearBranches();
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(getCurrentFragment() instanceof AddNewBranchFragment){

            addNewBranchFragment.onRequestPermissionsResult(requestCode,permissions,grantResults);

        }else{
            if(requestCode == REQUEST_LOCATION_CODE && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                getLocation();
                if(currentLocation!= null){
                    getNearBranches();
                }
            }
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void addNewBranchFragmentToActivity(){

        addNewBranchFragment = new AddNewBranchFragment();
        addFragmentToView(addNewBranchFragment);

    }

    @SuppressLint("RestrictedApi")
    public void showFloatingListViewButton(){
        listViewButton.setVisibility(View.VISIBLE);
    }
}
