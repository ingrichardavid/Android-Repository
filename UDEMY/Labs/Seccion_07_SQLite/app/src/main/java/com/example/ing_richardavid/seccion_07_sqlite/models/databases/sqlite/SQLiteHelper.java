package com.example.ing_richardavid.seccion_07_sqlite.models.databases.sqlite;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by ing_richardavid on 28-08-17.
 */

public class SQLiteHelper extends SQLiteAssetHelper {

    /**
     * Objects, variables and constants.
     */

    private static final String DATABASE_NAME = "products.db";
    private static final int DATABASE_VERSION = 1;

    /**
     * Builder.
     */

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

}
