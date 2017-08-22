package com.example.ing_richardavid.seccion_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    /**
     * View components.
     */

    private Bundle bundle;
    private TextView txtVTitle;

    /**
     * Objects, variables and constants.
     */

    public static final String parameterGreeting = "greeting";

    /**
     * Methods AppCompatActivity.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        /**
         * Activate arrow go back.
         */

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /**
         * Components registering.
         */

        this.txtVTitle = (TextView)findViewById(R.id.secondActivityTxtVTitle);

        this.bundle = this.getIntent().getExtras();

        if (this.bundle == null) {
            Toast.makeText(SecondActivity.this, "Data not found!", Toast.LENGTH_LONG).show();
        } else {
            String greeting = this.bundle.getString(SecondActivity.parameterGreeting);

            if (greeting == null) {
                this.txtVTitle.setText("Text not found!");
            } else {
                this.txtVTitle.setText(greeting);
            }
        }
    }
}
