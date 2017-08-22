package com.example.ing_richardavid.myapplication;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by ing_richardavid on 04-08-17.
 */

public class Test {

    public static void myToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

}
