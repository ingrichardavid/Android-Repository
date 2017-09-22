package com.example.ing_richardavid.ejemplo16as;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import rpc.json.me.*;

public class activity_ejemplo16as extends AppCompatActivity {

    /**
     * Objects, variables and constants.
     */

    private File rutaDeArchivo;
    private String nombreDeArchivo = "usuarios.json";

    /**
     * Functions: AppCompatActivity.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo16as);

        this.crearArchivo();
        this.agregarDatos();
    }

    /**
     * Functions: Self.
     */

    private void crearArchivo() {
        this.rutaDeArchivo = new File(this.getFilesDir().getPath() + "/Ejemplo16as");

        if (!this.rutaDeArchivo.exists()) {
            this.rutaDeArchivo.mkdir();
        }

        File archivoFisico = new File(this.rutaDeArchivo, this.nombreDeArchivo);

        if (!archivoFisico.exists()) {
            try {
                archivoFisico.createNewFile();

                this.toastConfiguration("Mensaje: El directorio es " + this.rutaDeArchivo);
                this.toastConfiguration("Mensaje: " + this.nombreDeArchivo + " creado");
            } catch (IOException e) {
                Log.e("IOException", e.getMessage());
            }
        }
    }

    private void agregarDatos() {
        String campoUsuario = "usuario";
        String campoClave = "clave";
        String campoNivel = "nivel";

        File archivoFisico = new File(this.rutaDeArchivo, this.nombreDeArchivo);

        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(archivoFisico);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put(campoUsuario, "Richard");
            jsonObject.put(campoClave, "12345");
            jsonObject.put(campoNivel, "1");


            fileOutputStream.write(jsonObject.toString().getBytes());
            fileOutputStream.close();

            this.toastConfiguration("Mensaje: Datos almacenados.");
        } catch (Exception e) {
            Log.e("JSONException", e.getMessage());
        }

    }

    private void toastConfiguration(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}
