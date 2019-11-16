package com.amr.codes.erkeny.views.fragments;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.amr.codes.erkeny.R;
import com.amr.codes.erkeny.control.Controller;
import com.amr.codes.erkeny.model.models.requests.ClientRegisterRequest;
import com.amr.codes.erkeny.model.models.requests.CompanyRegisterRequest;
import com.amr.codes.erkeny.model.models.responses.ClientRegisterResponse;
import com.amr.codes.erkeny.model.models.responses.CompanyRegisterResponse;
import com.amr.codes.erkeny.network.RetrofitClientInstance;
import com.amr.codes.erkeny.network.ServerApis;
import com.amr.codes.erkeny.views.activities.LoginActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyRegisterationFragment extends BaseFragment  implements LocationListener {

    private View companyRegisterationView;
    private EditText name, mobile, password, confirmPassword, email, capacity, price, fromHour, toHour;
    private Button registeButton;
    private ImageView companyLogo;
    private ProgressDialog progressDoalog;
    private Map<String, String> headers;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChooseTask;
    private byte[] BYTE;
    private String fileBase64;
    private LocationManager locationManager;
    private boolean isGPSEnabled;
    private boolean isNetworkEnabled;
    private Location location;

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES =  15000; // 1 minute

    // Declaring a Location Manager
    private boolean stopped = false;


    double latitude, longitude; // longitude

    private static final int REQUEST_CODE_PERMISSION = 200;
    String locationPermission = Manifest.permission.ACCESS_FINE_LOCATION;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        companyRegisterationView = inflater.inflate(R.layout.fragment_company_registeration, container, false);

        name = (EditText) companyRegisterationView.findViewById(R.id.name_editText);
        confirmPassword = (EditText) companyRegisterationView.findViewById(R.id.confirm_password_editText);
        email = (EditText) companyRegisterationView.findViewById(R.id.email_editText);
        password = (EditText) companyRegisterationView.findViewById(R.id.password_editText);
        mobile = (EditText) companyRegisterationView.findViewById(R.id.mobile_editText);
        capacity = (EditText) companyRegisterationView.findViewById(R.id.capacity_editText);
        price = (EditText) companyRegisterationView.findViewById(R.id.hour_price_editText);
        fromHour = (EditText) companyRegisterationView.findViewById(R.id.from_hour_editText);
        toHour = (EditText) companyRegisterationView.findViewById(R.id.to_hour_editText);
        companyLogo = (ImageView) companyRegisterationView.findViewById(R.id.company_logo);
        companyLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectImage();
            }
        });
        registeButton = (Button) companyRegisterationView.findViewById(R.id.register_button);
        registeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doValidationAndContinue();
            }
        });

        headers = Controller.getInstance().getDefaultHeader();


        return companyRegisterationView;
    }

    private void doValidationAndContinue() {


        CompanyRegisterRequest companyRegisterRequest = null;
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

        } else if (capacity.getText() == null || capacity.getText().toString().equals("")) {
            capacity.setError(getString(R.string.str_enter_valid_value));
        } else if (price.getText() == null || price.getText().toString().equals("")) {
            price.setError(getString(R.string.str_enter_valid_value));
        } else if (fromHour.getText() == null || fromHour.getText().toString().equals("")) {
            fromHour.setError(getString(R.string.str_enter_valid_value));
        } else if (toHour.getText() == null || toHour.getText().toString().equals("")) {
            toHour.setError(getString(R.string.str_enter_valid_value));
        } else if(location== null) {

            getLocation();
        }else{

            companyRegisterRequest = new CompanyRegisterRequest(name.getText().toString(),
                    email.getText().toString(),
                    password.getText().toString()
                    , confirmPassword.getText().toString(),
                    mobile.getText().toString(),
                    fileBase64,
                    Float.parseFloat(capacity.getText().toString()),
                    Float.parseFloat(price.getText().toString()),
                    location.getLongitude(),
                    location.getLatitude(),
                    fromHour.getText().toString(),
                    toHour.getText().toString());
            sendRequest(companyRegisterRequest);
        }

    }

    private void sendRequest(CompanyRegisterRequest request) {

        ServerApis serverApis = RetrofitClientInstance.getRetrofitInstance().create(ServerApis.class);
        Call<CompanyRegisterResponse> clientResponse = serverApis.registerCompany(request, headers);
        clientResponse.enqueue(new Callback<CompanyRegisterResponse>() {
            @Override
            public void onResponse(Call<CompanyRegisterResponse> call, Response<CompanyRegisterResponse> response) {

                progressDoalog.dismiss();
                if (response != null && response.body() != null) {

                    if (response.body().getEmailError() != null) {
                        Toast.makeText(getActivity(), response.body().getEmailError().get(0), Toast.LENGTH_LONG).show();
                    } else if (response.body().getHours_fromError() != null) {
                        Toast.makeText(getActivity(), response.body().getHours_fromError().get(0), Toast.LENGTH_LONG).show();

                    } else if (response.body().getLat() != null) {

                        Toast.makeText(getActivity(), response.body().getLat().get(0), Toast.LENGTH_LONG).show();
                    }else if (response.body().getLng() != null) {

                        Toast.makeText(getActivity(), response.body().getLng().get(0), Toast.LENGTH_LONG).show();
                    }else if (response.body().getCapacity() != null) {

                        Toast.makeText(getActivity(), response.body().getCapacity().get(0), Toast.LENGTH_LONG).show();
                    }else if (response.body().getHour_price() != null) {

                        Toast.makeText(getActivity(), response.body().getHour_price().get(0), Toast.LENGTH_LONG).show();
                    }else if (response.body().getHours_toError() != null) {

                        Toast.makeText(getActivity(), response.body().getHours_toError().get(0), Toast.LENGTH_LONG).show();
                    } else {

                        Toast.makeText(getActivity(), getString(R.string.str_registered_successfully), Toast.LENGTH_LONG).show();

                        startActivity(new Intent(getActivity(), LoginActivity.class));
                    }


                }
            }

            @Override
            public void onFailure(Call<CompanyRegisterResponse> call, Throwable t) {

                progressDoalog.dismiss();
                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_LONG);
            }
        });

    }

    private void selectImage() {
        final CharSequence[] items = {getString(R.string.str_take_photo), getString(R.string.str_choose_from_libarary),
                getString(R.string.str_choose_photo_cancel)};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.str_add_photo));
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result;

                if (items[item].equals(getString(R.string.str_take_photo))) {
                    userChooseTask = getString(R.string.str_take_photo);
                    result = Controller.getInstance().checkCameraPermission(getActivity());

                    if (result)
                        cameraIntent();

                } else if (items[item].equals(getString(R.string.str_choose_from_libarary))) {
                    userChooseTask = getString(R.string.str_choose_from_libarary);
                    result = Controller.getInstance().checkGallaryPermission(getActivity());

                    if (result)
                        galleryIntent();

                } else if (items[item].equals(getString(R.string.str_choose_photo_cancel))) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm = null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 90, bytes);

        companyLogo.setImageBitmap(bm);
        BYTE = bytes.toByteArray();
        fileBase64 = Base64.encodeToString(BYTE, Base64.DEFAULT);


    }


    private void galleryIntent() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, getString(R.string.str_select_file)), SELECT_FILE);
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }

    }


    private void onCaptureImageResult(Intent data) {

        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        BYTE = bytes.toByteArray();
        fileBase64 = Base64.encodeToString(BYTE, Base64.DEFAULT);

        companyLogo.setImageBitmap(thumbnail);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Controller.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChooseTask.equals(getString(R.string.str_choose_from_libarary))) ;
                    galleryIntent();
                } else {
                    //code for deny
                }
                break;
            case Controller.MY_PERMISSIONS_REQUEST_OPEN_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChooseTask.equals(getString(R.string.str_take_photo))) ;
                    cameraIntent();
                } else {
                    //code for deny
                }
                break;

            case  REQUEST_CODE_PERMISSION:
                getLocation();
                break;


        }
    }


    public Location getLocation() {
        try {
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

            // getting GPS status
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {

                showSettingsAlert();
            } else {
//                this.canGetLocation = true;
                // First get location from Network Provider
                if (isGPSEnabled) {
                    if (ContextCompat.checkSelfPermission(getActivity(),
                            locationPermission)
                            == PackageManager.PERMISSION_GRANTED) {
//                        boolean mLocationPermissionGranted = true;



                        if (location == null) {
                            locationManager.requestLocationUpdates(
                                    LocationManager.GPS_PROVIDER,
                                    MIN_TIME_BW_UPDATES,
                                    MIN_DISTANCE_CHANGE_FOR_UPDATES,  this);

//                                Log.d("GPS Enabled", "GPS Enabled");

                            if (locationManager != null) {
                                location = locationManager
                                        .getLastKnownLocation(LocationManager.GPS_PROVIDER);

                            }

                        }

                    } else {
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{locationPermission},
                                REQUEST_CODE_PERMISSION);
                    }

                }

                // if GPS Enabled get lat/long using GPS Services
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }

    public void showSettingsAlert(){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

        // Setting Dialog Title
        alertDialog.setTitle(getString(R.string.title_dialog));

        // Setting Dialog Message
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");

        // On pressing Settings button
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        });

        // on pressing cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }



    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
        Toast.makeText(getActivity(),"location changed", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }


}
