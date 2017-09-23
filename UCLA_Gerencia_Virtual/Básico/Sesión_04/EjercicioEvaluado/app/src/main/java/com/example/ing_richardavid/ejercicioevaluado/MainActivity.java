package com.example.ing_richardavid.ejercicioevaluado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * Objects, variables and constants.
     */

    private Button btnAcceso;

    /**
     * Functions: AppCompatActivity.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Add icon and title view.
         */

        this.getSupportActionBar().setIcon(R.mipmap.ic_myicon);
        this.getSupportActionBar().setDisplayUseLogoEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.getSupportActionBar().setTitle(R.string.titleViewInicio);

        /**
         * Components registering.
         */

        this.btnAcceso = (Button)findViewById(R.id.btnIdAcceso);
        this.btnAcceso.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_inicio, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemIdInicio:
                return true;
            case R.id.itemIdAcceso:
                this.iniciarAccesoActivity();

                return true;
            case R.id.itemIdContacto:
                return true;
            case R.id.itemIdSalir:
                finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Functions: OnClickListener.
     */

    @Override
    public void onClick(View view) {
        if (this.btnAcceso.equals(view)) {
            this.iniciarAccesoActivity();
        }
    }

    /**
     * Functions: Self.
     */

    private void iniciarAccesoActivity() {
        this.startActivity(new Intent(this, AccesoActivity.class));
    }
}
