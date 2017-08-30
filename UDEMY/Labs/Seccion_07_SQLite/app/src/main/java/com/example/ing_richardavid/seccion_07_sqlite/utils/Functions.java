package com.example.ing_richardavid.seccion_07_sqlite.utils;

import android.content.Context;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ing_richardavid on 29-08-17.
 */

public class Functions {

    /**
     * Function for get date
     * @return Date transform to string.
     */

    public static String getDateDayMonthYear() {
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }

    /**
     * Toast short.
     */

    public static void toastShort(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Toast long.
     */

    public static void toastLong(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

}
