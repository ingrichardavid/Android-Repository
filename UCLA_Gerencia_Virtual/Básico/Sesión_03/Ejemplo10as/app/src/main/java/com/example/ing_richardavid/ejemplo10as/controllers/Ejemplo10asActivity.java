package com.example.ing_richardavid.ejemplo10as.controllers;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ing_richardavid.ejemplo10as.R;
import com.example.ing_richardavid.ejemplo10as.models.databases.sqlite.configuration.CrearBD;
import com.example.ing_richardavid.ejemplo10as.models.databases.sqlite.entities.Usuarios;

import java.util.Vector;

public class Ejemplo10asActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_ejemplo10as);

        this.crearDB = new CrearBD(this.getApplicationContext());

        /*if (this.crearDB.insertarUsuario("richard", "12345") > 0) {
            this.toast = Toast.makeText(this, "Mensaje: Insertado el registro satisfactoriamente.", Toast.LENGTH_SHORT);
        } else {
            this.toast = Toast.makeText(this, "Error: No se pudo insertar.", Toast.LENGTH_SHORT);
        }

        this.toast.show();*/

        /*this.usuarios = new Vector();

        if (this.crearDB.buscarUsuario("richard", "12345", this.usuarios)) {
            String id = (String) this.usuarios.elementAt(0);
            String usuario = (String) this.usuarios.elementAt(1);
            String nivel = (String) this.usuarios.elementAt(3);

            this.toast = Toast.makeText(this, "Mensaje: Registro Encontrado, ID=" + id + " y USUARIO: " + usuario + " y NIVEL: " + nivel, Toast.LENGTH_SHORT);
        } else {
            this.toast = Toast.makeText(this, "Error: No se encuentra el registro", Toast.LENGTH_SHORT);
        }

        this.toast.show();*/

        String sql = "ALTER TABLE " + Usuarios.TABLA + " ADD COLUMN nivel TEXT;";
        boolean exito = this.crearDB.alterar(sql);

        if (exito) {
            this.toast = Toast.makeText(this, "Mensaje: Tabla usuarios modificada con éxito", Toast.LENGTH_SHORT);
            this.toast.show();

            ContentValues contentValues = new ContentValues();
            contentValues.put(Usuarios.NIVEL, "1");

            String condicion = "usuario = ? AND clave = ?";

            String[] parametros = new String[] {"richard","12345"};

            exito = this.crearDB.actualizarUsuario(contentValues, condicion, parametros);

            if (exito) {
                this.toast = Toast.makeText(this, "Mensaje: Nivel Actualizado", Toast.LENGTH_SHORT);
                this.toast.show();
            } else {
                this.toast = Toast.makeText(this, "Error: No se actualizo el nivel", Toast.LENGTH_SHORT);
                this.toast.show();
            }
        } else {
            this.toast = Toast.makeText(this, "Error: No se modifico la tabla usuarios", Toast.LENGTH_SHORT);
        }

        this.toast.show();
    }
}
