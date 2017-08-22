package com.example.ing_richardavid.seccion_1;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * View components.
     */

    private EditText editTxtPhone;
    private EditText editTxtWeb;
    private EditText editTxtContacts;
    private EditText editTxtSendEmailFast;
    private EditText editTxtSendEmailComplete;
    private EditText editTxtQuickCall;
    private ImageButton imgBtnPhone;
    private ImageButton imgBtnWeb;
    private ImageButton imgBtnCamera;
    private ImageButton imgBtnContacts;
    private ImageButton imgBtnSendEmailFast;
    private ImageButton imgBtnSendEmailComplete;
    private ImageButton imgBtnQuickCall;
    private Intent intent;

    /**
     * Objects, variables and constants.
     */

    private final int PHONE_CALL_CODE = 100;
    private final int PICTURE_FROM_CAMERA = 50;

    /**
     * Methods AppCompatActivity.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        /**
         * Activate arrow go back.
         */

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /**
         *  Components registering.
         */

        this.editTxtPhone = (EditText)findViewById(R.id.activityThirdEditTxtPhone);

        this.editTxtWeb = (EditText)findViewById(R.id.activityThirdEditTxtWeb);

        this.editTxtContacts = (EditText)findViewById(R.id.activityThirdEditTxtContacts);

        this.editTxtSendEmailFast = (EditText)findViewById(R.id.activityThirdEditTxtSendEmailFast);

        this.editTxtSendEmailComplete = (EditText)findViewById(R.id.activityThirdEditTxtSendEmailComplete);

        this.editTxtQuickCall = (EditText)findViewById(R.id.activityThirdEditTxtQuickCall);

        this.imgBtnPhone = (ImageButton)findViewById(R.id.activityThirdImgBtnPhone);
        this.imgBtnPhone.setOnClickListener(this);

        this.imgBtnWeb = (ImageButton)findViewById(R.id.activityThirdImgBtnWeb);
        this.imgBtnWeb.setOnClickListener(this);

        this.imgBtnCamera = (ImageButton)findViewById(R.id.activityThirdImgBtnCamera);
        this.imgBtnCamera.setOnClickListener(this);

        this.imgBtnContacts = (ImageButton)findViewById(R.id.activityThirdImgBtnContacts);
        this.imgBtnContacts.setOnClickListener(this);

        this.imgBtnSendEmailFast = (ImageButton)findViewById(R.id.activityThirdImgBtnSendEmailFast);
        this.imgBtnSendEmailFast.setOnClickListener(this);

        this.imgBtnSendEmailComplete = (ImageButton)findViewById(R.id.activityThirdImgBtnSendEmailComplete);
        this.imgBtnSendEmailComplete.setOnClickListener(this);

        this.imgBtnQuickCall = (ImageButton)findViewById(R.id.activityThirdImgBtnQuickCall);
        this.imgBtnQuickCall.setOnClickListener(this);
    }

    /**
     * Events registering OnClickListener.
     */

    @Override
    public void onClick(View view) {
        if (this.imgBtnPhone.equals(view)) {
            if (this.editTxtPhone.getText().toString().isEmpty()) {
                Toast.makeText(ThirdActivity.this, "Enter phone number!", Toast.LENGTH_SHORT).show();
            } else {
                //Checks if current version is major or equals at version 6 of android.

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    //Check if the user has accepted, not accepted or has never been asked.

                    if (this.checkPermission(Manifest.permission.CALL_PHONE)) {
                        this.intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + this.editTxtPhone.getText().toString()));

                        this.startActivity(this.intent);
                    } else {
                        //Check if the user not accepted or has never been asked.

                        if (!this.shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                            this.requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, this.PHONE_CALL_CODE);
                        } else {
                            Toast.makeText(ThirdActivity.this, "Please, enable the request permission!", Toast.LENGTH_SHORT).show();

                            this.intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            this.intent.addCategory(Intent.CATEGORY_DEFAULT);
                            this.intent.setData(Uri.parse("package:" + this.getPackageName()));
                            this.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            this.intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            this.intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

                            this.startActivity(this.intent);
                        }
                    }
                } else {
                    this.olderVersions(this.editTxtPhone.getText().toString());
                }
            }
        } else if (this.imgBtnWeb.equals(view)) {
            if (this.editTxtWeb.getText().toString().isEmpty()) {
                Toast.makeText(ThirdActivity.this, "Enter URL!", Toast.LENGTH_SHORT).show();
            } else {
                this.intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + this.editTxtWeb.getText().toString()));

                this.startActivity(this.intent);
            }
        } else if (this.imgBtnContacts.equals(view)) {
            if (this.editTxtContacts.getText().toString().isEmpty()) {
                Toast.makeText(ThirdActivity.this, "Enter contact name!", Toast.LENGTH_SHORT).show();
            } else {
                this.intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people"));

                this.startActivity(this.intent);
            }
        } else if (this.imgBtnSendEmailFast.equals(view)) {
            if (this.editTxtSendEmailFast.getText().toString().isEmpty()) {
                Toast.makeText(ThirdActivity.this, "Enter email!", Toast.LENGTH_SHORT).show();
            } else {
                this.intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + this.editTxtSendEmailFast.getText().toString()));

                this.startActivity(this.intent);
            }
        } else if (this.imgBtnSendEmailComplete.equals(view)) {
            if (this.editTxtSendEmailComplete.getText().toString().isEmpty()) {
                Toast.makeText(ThirdActivity.this, "Enter email!", Toast.LENGTH_SHORT).show();
            } else {
                this.intent = new Intent(Intent.ACTION_SEND, Uri.parse(this.editTxtSendEmailComplete.getText().toString()));
                this.intent.setType("plain/text");
                this.intent.putExtra(Intent.EXTRA_SUBJECT, "Mail's title");
                this.intent.putExtra(Intent.EXTRA_TEXT, "Hi, how are you?");
                this.intent.putExtra(Intent.EXTRA_EMAIL, new String[]{this.editTxtSendEmailComplete.getText().toString()});

                this.startActivity(Intent.createChooser(this.intent, "Elige el cliente de correo:"));
            }
        } else if (this.imgBtnQuickCall.equals(view)) {
            if (this.editTxtQuickCall.getText().toString().isEmpty()) {
                Toast.makeText(ThirdActivity.this, "Enter phone number!", Toast.LENGTH_SHORT).show();
            } else {
                //This form allows you to make a call without requesting permissions.

                this.intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + this.editTxtQuickCall.getText().toString()));

                this.startActivity(this.intent);
            }
        } else if (this.imgBtnCamera.equals(view)) {
            this.intent = new Intent("android.media.action.IMAGE_CAPTURE");

            this.startActivityForResult(this.intent, PICTURE_FROM_CAMERA);
        }
    }

    /**
     *  Functions: Self.
     */

    /***
     * Checks if the application has permissions to open other applications.
     * @param permission
     * @return
     */
    private boolean checkPermission(String permission) {
        return this.checkCallingOrSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Checks if the application has permissions to open other applications (Older versions).
     * @param phoneNumber
     */
    private void olderVersions(String phoneNumber) {
        if (this.checkPermission(Manifest.permission.CALL_PHONE)) {
            this.intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));

            this.startActivity(this.intent);
        } else {
            Toast.makeText(ThirdActivity.this, "You declined the access!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Function handling permission request response to open other applications.
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PHONE_CALL_CODE:
                String permission = permissions[0];
                int result = grantResults[0];

                if (permission.equals(Manifest.permission.CALL_PHONE)) {
                    //Check if the user has accepted or denied the request for permission.

                    if (result == PackageManager.PERMISSION_GRANTED) {
                        this.intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + this.editTxtPhone.getText().toString()));

                        this.startActivity(this.intent);
                    } else {
                        Toast.makeText(ThirdActivity.this, "You declined the access!", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);

                break;
        }
    }

    /**
     * Function that handles the result of data captured by external activities.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PICTURE_FROM_CAMERA:
                if (resultCode == Activity.RESULT_OK) {
                    String result = data.toUri(0);

                    Toast.makeText(this, "Result: " + result, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "There was an error with the picture, try again.", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);

                break;
        }
    }
}