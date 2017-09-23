package com.example.ing_richardavid.ejercicioevaluado;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import rpc.json.me.JSONArray;
import rpc.json.me.JSONObject;

public class AccesoActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * Objects, variables and constants.
     */

    private File rutaDeArchivo;
    private String nombreDeArchivo = "usuarios.json";
    private String campoNombreUsuario = "nombre";
    private String campoClave = "clave";
    private String campoNivel = "nivel";
    private int nivel = 1;

    /**
     * Components.
     */

    private EditText editTxtUsuario;
    private EditText editTxtClave;
    private Button btnAceptar;
    private Button btnLimpiar;
    private Button btnSalir;

    /**
     *  Functions: AppCompatActivity.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso);

        /**
         * Components registering.
         */

        this.editTxtUsuario = (EditText)findViewById(R.id.editTxtIdUsuario);
        this.editTxtClave = (EditText)findViewById(R.id.editTxtIdClave);
        this.btnAceptar = (Button)findViewById(R.id.btnIdAceptar);
        this.btnAceptar.setOnClickListener(this);
        this.btnLimpiar = (Button)findViewById(R.id.btnIdLimpiar);
        this.btnLimpiar.setOnClickListener(this);
        this.btnSalir = (Button)findViewById(R.id.btnIdSalir);
        this.btnSalir.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_acceso, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemIdGenerar:
                this.crearArchivoJSON();

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
        if (this.btnAceptar.equals(view)) {
            if (this.validarCampos()) {
                if (this.buscarUsuario(this.editTxtUsuario.getText().toString(),this.editTxtClave.getText().toString())) {
                    this.toastConfiguration("Mensaje: Usuario existe.");
                } else {
                    this.toastConfiguration("Mensaje: El usaurio no existe.");
                }
            }
        } else if (this.btnLimpiar.equals(view)) {
            this.limpiarCampos();
        } else if (this.btnSalir.equals(view)) {
            this.finish();
        }
    }

    /**
     * Functions: Self.
     */

    private void limpiarCampos() {
        this.editTxtUsuario.setText("");
        this.editTxtUsuario.requestFocus();
        this.editTxtClave.setText("");
    }

    private boolean validarCampos() {
        if (this.editTxtUsuario.getText().toString().isEmpty()) {
            this.toastConfiguration("Introduzca el nombre de usuario.");

            return false;
        } else if (this.editTxtClave.getText().toString().isEmpty()) {
            this.toastConfiguration("Introduzca la clave de usuario.");

            return false;
        }

        return true;
    }

    private void toastConfiguration(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    private void crearArchivoJSON() {
        this.rutaDeArchivo = new File(this.getFilesDir().getPath() + "/EjercicioEvaluado");

        FileOutputStream fileOutputStream = null;


        if (!this.rutaDeArchivo.exists()) {
            this.rutaDeArchivo.mkdir();
        }

        File archivoFisico = new File(this.rutaDeArchivo, this.nombreDeArchivo);

        if (!archivoFisico.exists()) {
            try {
                archivoFisico.createNewFile();

                fileOutputStream = new FileOutputStream(archivoFisico);

                JSONArray jsonArray = new JSONArray();

                fileOutputStream.write(jsonArray.toString().getBytes());
                fileOutputStream.close();

                this.crearUsuario("admin","admin");

                this.toastConfiguration("Archivo JSON generado.");
            } catch (IOException e) {
                Log.e("IOException", e.getMessage());
            }
        }
    }

    private void crearUsuario(String usuario, String clave) {
        File archivoFisico = new File(this.rutaDeArchivo, this.nombreDeArchivo);

        FileInputStream fileInputStream = null;
        StringBuffer stringBuffer = null;
        char[] buffer = null;
        InputStreamReader inputStreamReader = null;
        int count = 0;
        String json = null;
        FileOutputStream fileOutputStream = null;

        if (archivoFisico.exists()) {
            try {
                fileInputStream = new FileInputStream(archivoFisico);

                inputStreamReader = new InputStreamReader(fileInputStream);

                buffer = new char[32];

                stringBuffer = new StringBuffer();

                while ((count = inputStreamReader.read(buffer, 0, buffer.length)) > -1) {
                    stringBuffer.append(buffer,0,count);
                }

                inputStreamReader.close();

                json = stringBuffer.toString();

                JSONArray jsonArray = new JSONArray(json);

                fileOutputStream = new FileOutputStream(archivoFisico);

                JSONObject jsonObject = new JSONObject();
                jsonObject.put(this.campoNombreUsuario, usuario);
                jsonObject.put(this.campoClave, clave);
                jsonObject.put(this.campoNivel, String.valueOf(this.nivel));

                jsonArray.put(jsonObject);

                fileOutputStream.write(jsonArray.toString().getBytes());
                fileOutputStream.close();

                fileInputStream.close();

                this.toastConfiguration("Mensaje: Datos almacenados.");
            } catch (Exception e) {
                Log.e("JSONException", e.getMessage());
            }
        }
    }

    private boolean buscarUsuario(String usuario, String clave) {
        boolean usuarioExiste = false;

        File archivoFisico = new File(this.rutaDeArchivo, this.nombreDeArchivo);

        FileInputStream fileInputStream = null;
        StringBuffer stringBuffer = null;
        char[] buffer = null;
        InputStreamReader inputStreamReader = null;
        int count = 0;
        String json = null;

        if (archivoFisico.exists()) {
            try {
                fileInputStream = new FileInputStream(archivoFisico);

                inputStreamReader = new InputStreamReader(fileInputStream);

                buffer = new char[32];

                stringBuffer = new StringBuffer();

                while ((count = inputStreamReader.read(buffer, 0, buffer.length)) > -1) {
                    stringBuffer.append(buffer,0,count);
                }

                inputStreamReader.close();

                json = stringBuffer.toString();

                JSONArray jsonArray = new JSONArray(json);

                JSONObject jsonObject = new JSONObject();

                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = new JSONObject(jsonArray.getString(i));

                    if (jsonObject.getString(this.campoNombreUsuario).toLowerCase().equals(usuario.toLowerCase()) && jsonObject.getString(this.campoClave).toLowerCase().equals(clave.toLowerCase())) {
                        usuarioExiste = true;

                        break;
                    }
                }

                fileInputStream.close();
            } catch (Exception e) {
                Log.e("JSONException", e.getMessage());
            }
        }

        return usuarioExiste;
    }
}
