package com.amr.codes.erkeny.control;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.amr.codes.erkeny.R;
import com.amr.codes.erkeny.model.models.User;
import com.amr.codes.erkeny.views.activities.LoginActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by amra on 5/2/2018.
 */

public class Controller {

//    private static DBHelper dbHelper;
    private User loggedUser;
    private Activity currentActivity;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    public static final int MY_PERMISSIONS_REQUEST_OPEN_CAMERA = 124;
    public static final int MY_PERMISSIONS_REQUEST_TELEPHONE_STATE = 125;


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
}
