package com.example.ing_richardavid.myform.Utils;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by ing_richardavid on 17-08-17.
 */

public class Functions {

    /**
     * Toast long duration.
     */

    public static void toastLong(Activity activity, String message) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
    }

    /**
     * Toast short duration.
     */

    public static void toastShort(Activity activity, String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

}
