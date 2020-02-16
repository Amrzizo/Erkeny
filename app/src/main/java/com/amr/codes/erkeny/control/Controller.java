package com.amr.codes.erkeny.control;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.amr.codes.erkeny.R;
import com.amr.codes.erkeny.model.models.User;
import com.amr.codes.erkeny.network.RetrofitClientInstance;
import com.amr.codes.erkeny.network.ServerApis;
import com.amr.codes.erkeny.views.activities.HomeActivity;
import com.amr.codes.erkeny.views.activities.LoginActivity;
import com.amr.codes.erkeny.views.activities.base.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by amra on 5/2/2018.
 */

public class Controller {

    //    private static DBHelper dbHelper;
    private User loggedUser;
    private Activity currentActivity;
    private ProgressDialog progressDoalog;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    public static final int MY_PERMISSIONS_REQUEST_OPEN_CAMERA = 124;
    public static final int MY_PERMISSIONS_REQUEST_TELEPHONE_STATE = 125;
    public static String TOKEN = "token_key";
    private static String PREFERENCE_NAME = "prefrence_name";
    public static int REQUEST_LOCATION_CODE = 100;
    public static int MIN_TIME = 60000;
    public static int MIN_DISTANCE = 100;
    private boolean GPSEnabled;


    private static Controller instance;

    public static Controller getInstance(Context context) {
        if (instance == null) {
            instance = new Controller();
//            dbHelper = new DBHelper(context, DBHelper.DATABASE_NAME, null, DBHelper.DATABASE_VERSION);
        }
        return instance;
    }


    public static Controller getInstance() {

        return instance;
    }

    private Controller() {
    }


    public static void adjustDialogWidth(Context context, Dialog dialog) {
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        int dialogWidth = (int) (screenWidth * .9f);
        dialog.getWindow().setLayout(dialogWidth, dialog.getWindow().getAttributes().height);
    }

    public void showLogOutDialog(final Activity activity, final boolean closeActivity) {

        final Dialog innerAlertDialog = new Dialog(activity);
        innerAlertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        innerAlertDialog.setContentView(R.layout.custom_dialog_layout);
        adjustDialogWidth(activity, innerAlertDialog);
        TextView title = (TextView) innerAlertDialog
                .findViewById(R.id.custom_dialog_title_id);
        title.setText(R.string.app_name);
        TextView messageTXT = (TextView) innerAlertDialog
                .findViewById(R.id.custom_dialog_message_id);
        messageTXT.setText(activity.getString(R.string.logout_message));
        innerAlertDialog.setCancelable(false);
        Button ok_btn = (Button) innerAlertDialog
                .findViewById(R.id.custom_dialog_okBtn_id);
        ok_btn.setText(R.string.logout);

        ok_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity.startActivity(new Intent(Controller.getInstance().getCurrentActivity(), LoginActivity.class));


                if (closeActivity) {
                    activity.finish();
                }
            }

        });

        Button cancel_btn = (Button) innerAlertDialog
                .findViewById(R.id.custom_dialog_cancelBtn_id);
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                innerAlertDialog.dismiss();


            }
        });

        ok_btn.setAllCaps(true);
        cancel_btn.setAllCaps(true);

        innerAlertDialog.show();

    }


    public void showInformationDialog(final Activity activity, final boolean closeActivity, String messageString) {

        final Dialog innerAlertDialog = new Dialog(activity);
        innerAlertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        innerAlertDialog.setContentView(R.layout.custom_dialog_layout);
        adjustDialogWidth(activity, innerAlertDialog);
        TextView title =  innerAlertDialog
                .findViewById(R.id.custom_dialog_title_id);
        title.setText(R.string.app_name);
        TextView messageTXT = (TextView) innerAlertDialog
                .findViewById(R.id.custom_dialog_message_id);
        messageTXT.setText(messageString);
        innerAlertDialog.setCancelable(false);
        Button ok_btn = (Button) innerAlertDialog
                .findViewById(R.id.custom_dialog_okBtn_id);
        ok_btn.setText(R.string.btn_ok_txt);

        ok_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (closeActivity) {
                    activity.finish();
                }
                innerAlertDialog.dismiss();
            }

        });

        Button cancel_btn = (Button) innerAlertDialog
                .findViewById(R.id.custom_dialog_cancelBtn_id);
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                innerAlertDialog.dismiss();


            }
        });

        ok_btn.setAllCaps(true);
        cancel_btn.setAllCaps(true);

        innerAlertDialog.show();

    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public Activity getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(Activity currentActivity) {
        this.currentActivity = currentActivity;
    }

    public Map<String, String> getDefaultHeader() {
        Map<String, String> header = new HashMap<>();
        header.put("Accept", "application/json");
        header.put("Content-Type", "application/json");
        return header;
    }


    public static boolean checkGallaryPermission(final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle(context.getString(R.string.str_permission_necessary));
                    alertBuilder.setMessage(context.getString(R.string.str_external_storage));
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();

                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public static boolean checkCameraPermission(final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.CAMERA)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle(context.getString(R.string.str_permission_necessary));
                    alertBuilder.setMessage(context.getString(R.string.str_camera));
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_OPEN_CAMERA);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();

                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_OPEN_CAMERA);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }


    public void showProgressDialog(Context context) {
        progressDoalog = new ProgressDialog(context);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();
    }


    public void cancelProgressDialog() {
        progressDoalog.dismiss();
    }

    public ServerApis getServerApis() {
        return RetrofitClientInstance.getRetrofitInstance().create(ServerApis.class);
    }

    public void saveTokenToSharedPreferences(String token, BaseActivity activity) {

        SharedPreferences preferences = activity.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(TOKEN, token);
        editor.commit();
    }

    public String getTokenFromSharedPreferences(BaseActivity activity) {

        SharedPreferences preferences = activity.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return preferences.getString(TOKEN, "");
    }

    public void gotoGPSSetting(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Gps Settings");
        builder.setMessage("Gps Provider disabled, Click on settings to enable");
        builder.setPositiveButton("Setting", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                openGPSSettingScreen(context);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

    public void openGPSSettingScreen(Context context) {

        Intent settingIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        context.startActivity(settingIntent);

    }


    public boolean isPermissionGranted(String permissionName, BaseActivity activity, int requestCode) {
        if (ContextCompat.checkSelfPermission
                (activity, permissionName) ==
                PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            String[] permissions = {permissionName};
            ActivityCompat.requestPermissions(activity, permissions, requestCode);
            return false;
        }
    }

    @SuppressLint("MissingPermission")
    @RequiresApi(api = Build.VERSION_CODES.M)
    public Location getLocation(Context context) {

        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        GPSEnabled = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (GPSEnabled) {

            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME, MIN_DISTANCE, (LocationListener) context);

        } else {

            Controller.getInstance().gotoGPSSetting(context);
        }

        return manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    }

}
