package com.example.ing_richardavid.seccion_07_sqlite.models.databases.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.ing_richardavid.seccion_07_sqlite.models.entities.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ing_richardavid on 28-08-17.
 */

public class SQLiteDatabaseConnection {

    /**
     * Objects, variables and constants.
     */

    private static SQLiteDatabaseConnection INSTANCE;
    private static Context context;
    private static SQLiteDatabase sqLiteDatabase;
    private static SQLiteHelper sqLiteHelper;

    /**
     * Builder.
     */

    private SQLiteDatabaseConnection() {
        sqLiteHelper = new SQLiteHelper(SQLiteDatabaseConnection.context);
    }

    /**
     * Functions: Self.
     */

    public static SQLiteDatabaseConnection getInstance(Context context) {
        SQLiteDatabaseConnection.context = context;

        if (INSTANCE == null) {
            INSTANCE = new SQLiteDatabaseConnection();
        }

        return INSTANCE;
    }

    public void openDB() throws SQLException {
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
    }

    public void closeDB() {
        sqLiteDatabase.close();
    }

    public SQLiteDatabase getSqLiteDatabase() {
        return sqLiteDatabase;
    }

}
