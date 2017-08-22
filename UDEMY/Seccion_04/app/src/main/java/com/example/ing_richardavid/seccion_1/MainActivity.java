package com.example.ing_richardavid.seccion_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * View components.
     */

    private Button btnGoToActivityOne;
    private Button btnGoToActivityThree;
    private Intent intent;

    /**
     * Objects, variables and constants.
     */

    private final String greeting = "Hello from the other side!";

    /**
     * Methods AppCompatActivity.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Force and change icon in action bar.
         */

        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.getSupportActionBar().setIcon(R.mipmap.ic_myicon);

        /**
         * Components registering.
         */

        this.btnGoToActivityOne = (Button)findViewById(R.id.activityMainBtnGoToActivityTwo);
        this.btnGoToActivityOne.setOnClickListener(this);

        this.btnGoToActivityThree = (Button)findViewById(R.id.activityMainBtnGoToActivityThree);
        this.btnGoToActivityThree.setOnClickListener(this);
    }

    /**
     * Events registering OnClickListener.
     */

    @Override
    public void onClick(View view) {
        if (this.btnGoToActivityOne.equals(view)) {
            this.intent = new Intent(MainActivity.this, SecondActivity.class);
            this.intent.putExtra(SecondActivity.parameterGreeting, this.greeting);

            this.startActivity(this.intent);
        } else if (this.btnGoToActivityThree.equals(view)) {
            this.intent = new Intent(MainActivity.this, ThirdActivity.class);

            this.startActivity(this.intent);
        }
    }
}
