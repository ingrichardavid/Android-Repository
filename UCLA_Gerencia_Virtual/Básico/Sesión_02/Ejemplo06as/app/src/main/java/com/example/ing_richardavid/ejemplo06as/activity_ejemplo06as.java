package com.example.ing_richardavid.ejemplo06as;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class activity_ejemplo06as extends AppCompatActivity {

    /**
     * Components.
     */

    private Button btnInicio;
    private Button btnAcceso;
    private Button btnContacto;

    /**
     * Funcitons: AppCompatActivity.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo06as);

        this.btnInicio = (Button)findViewById(R.id.btnIdInicio);
        this.btnAcceso = (Button)findViewById(R.id.btnIdAcceso);
        this.btnContacto = (Button)findViewById(R.id.btnIdContacto);

        this.btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastConfiguration("Mensaje: Presionó Inicio", 3000);
            }
        });

        this.btnAcceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastConfiguration("Mensaje: Presionó Acceso", 3000);
            }
        });

        this.btnContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastConfiguration("Mensaje: Presionó Contacto", 3000);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.mainmenu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemIdInicio:
                this.toastConfiguration("Menu Contextual: Presionó Inicio", Toast.LENGTH_SHORT);

                return true;
            case R.id.itemIdAcceso:
                this.toastConfiguration("Menu Contextual: Presionó Acceso", Toast.LENGTH_SHORT);

                return true;
            case R.id.itemIdContacto:
                this.toastConfiguration("Menu Contextual: Presionó Contacto", Toast.LENGTH_SHORT);

                return true;

            case R.id.itemIdSalir:
                finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Functions: Self.
     */

    private void toastConfiguration(String message, int duration) {
        Toast.makeText(this, message, duration).show();
    }
}
