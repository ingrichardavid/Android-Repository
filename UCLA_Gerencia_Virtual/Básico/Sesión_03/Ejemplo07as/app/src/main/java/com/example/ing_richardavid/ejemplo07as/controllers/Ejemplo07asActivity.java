package com.example.ing_richardavid.ejemplo07as.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ing_richardavid.ejemplo07as.R;
import com.example.ing_richardavid.ejemplo07as.models.databases.sqlite.configuration.CrearBD;

public class Ejemplo07asActivity extends AppCompatActivity {

    /**
     * Objects, variables and constants.
     */

    private CrearBD crearDB;

    /**
     * Functions: AppCompatActivity.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo07as);

        this.crearDB = new CrearBD(this.getApplicationContext());
    }
}
