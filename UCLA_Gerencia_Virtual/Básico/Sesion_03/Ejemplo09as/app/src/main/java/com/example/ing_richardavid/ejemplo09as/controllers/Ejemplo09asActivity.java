package com.example.ing_richardavid.ejemplo09as.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ing_richardavid.ejemplo09as.R;
import com.example.ing_richardavid.ejemplo09as.models.databases.sqlite.configuration.CrearBD;

import java.util.Vector;

public class Ejemplo09asActivity extends AppCompatActivity {

    /**
     * Objects, variables and constants.
     */

    private CrearBD crearDB;
    private Vector usuarios;

    /**
     * Components.
     */

    private Toast toast;

    /**
     * Functions: AppCompatActivity.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo09as);

        this.crearDB = new CrearBD(this.getApplicationContext());

        /*if (this.crearDB.InsertarUsuario("richard", "12345") > 0) {
            this.toast = Toast.makeText(this, "Mensaje: Insertado el registro satisfactoriamente.", Toast.LENGTH_SHORT);
        } else {
            this.toast = Toast.makeText(this, "Error: No se pudo insertar.", Toast.LENGTH_SHORT);
        }

        this.toast.show();*/

        this.usuarios = new Vector();

        if (this.crearDB.BuscarUsuario("richard", "123456", this.usuarios)) {
            String id = (String) this.usuarios.elementAt(0);
            String usuario = (String) this.usuarios.elementAt(1);

            this.toast = Toast.makeText(this, "Mensaje: Registro Encontrado, ID=" + id + " y USUARIO: " + usuario, Toast.LENGTH_SHORT);
        } else {
            this.toast = Toast.makeText(this, "Error: No se encuentra el registro", Toast.LENGTH_SHORT);
        }

        toast.show();
    }
}
