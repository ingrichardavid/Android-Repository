package com.example.ing_richardavid.listview.utils;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by ing_richardavid on 18-08-17.
 */

public class Functions {

    /**
     * Toast long.
     */

    public static void toastLong(Activity activity, String message) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
    }

    /**
     * Toast short.
     */

    public static void toastShort(Activity activity, String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

}
