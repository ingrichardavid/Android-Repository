package com.example.ing_richardavid.ejemplo17as;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import rpc.json.me.JSONArray;
import rpc.json.me.JSONObject;

public class activity_ejemplo17as extends AppCompatActivity implements View.OnClickListener {

    /**
     * Objects, variables and constants.
     */

    private File rutaDeArchivo;
    private String nombreDeArchivo = "usuarios.json";
    private String campoNombreUsuario = "nombre";
    private String campoClave = "clave";
    private String campoNivel = "nivel";
    private int nivel = 1;
    private String textoDeSalida;

    /**
     * Components.
     */

    private EditText editTxtUsuario;
    private EditText editTxtClave;
    private RadioButton rBtnNivel1;
    private RadioButton rBtnNivel2;
    private RadioButton rBtnNivel3;
    private TextView txtVTexto;

    /**
     * Functions: AppCompatActivity.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo17as);

        this.crearArchivo();

        /**
         * Registering components.
         */

        this.editTxtUsuario = (EditText)findViewById(R.id.editTxtIdUsuario);
        this.editTxtClave = (EditText)findViewById(R.id.editTxtIdClave);
        this.rBtnNivel1 = (RadioButton)findViewById(R.id.rBtnNivel1);
        this.rBtnNivel1.setOnClickListener(this);
        this.rBtnNivel2 = (RadioButton)findViewById(R.id.rBtnNivel2);
        this.rBtnNivel2.setOnClickListener(this);
        this.rBtnNivel3 = (RadioButton)findViewById(R.id.rBtnNivel3);
        this.rBtnNivel3.setOnClickListener(this);
        this.txtVTexto = (TextView)findViewById(R.id.txtVIdTexto);

        this.listarUsuarios();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.mainmenu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemCrearUsuario:
                if (this.validarCampos()) {
                    if (!this.buscarUsuario(this.editTxtUsuario.getText().toString())) {
                        this.crearUsuario(this.editTxtUsuario.getText().toString(), this.editTxtClave.getText().toString());
                        this.listarUsuarios();
                        this.limpiarCampos();
                    } else {
                        this.toastConfiguration("El usuario ya se encuentra registrado.");
                    }
                }

                return true;
            case R.id.itemActualizarUsuario:
                if (this.validarCampos()) {
                    if (this.buscarUsuario(this.editTxtUsuario.getText().toString())) {
                        this.actualizarUsuario(this.editTxtUsuario.getText().toString(), this.editTxtClave.getText().toString());
                        this.listarUsuarios();
                        this.limpiarCampos();
                    } else {
                        this.toastConfiguration("El usuario ha modificar no existe.");
                    }
                }

                return true;
            case R.id.itemRemoverUsuario:
                if (this.validarUsuario()) {
                    if (this.buscarUsuario(this.editTxtUsuario.getText().toString())) {
                        this.eliminarUsuario(this.editTxtUsuario.getText().toString());
                        this.listarUsuarios();
                        this.limpiarCampos();
                    } else {
                        this.toastConfiguration("El usuario ha eliminar no existe.");
                    }
                }

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
        if (this.rBtnNivel1.equals(view)) {
            this.nivel = 1;
        } else if (this.rBtnNivel2.equals(view)) {
            this.nivel = 2;
        } else if (this.rBtnNivel3.equals(view)) {
            this.nivel = 3;
        }
    }

    /**
     * Functions: Self.
     */

    private void crearArchivo() {
        this.rutaDeArchivo = new File(this.getFilesDir().getPath() + "/Ejemplo16as");

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

                this.toastConfiguration("Mensaje: El directorio es " + this.rutaDeArchivo);
                this.toastConfiguration("Mensaje: " + this.nombreDeArchivo + " creado");
            } catch (IOException e) {
                Log.e("IOException", e.getMessage());
            }
        }
    }

    private void limpiarCampos() {
        this.editTxtUsuario.setText("");
        this.editTxtUsuario.requestFocus();
        this.editTxtClave.setText("");
        this.rBtnNivel1.setChecked(true);
        this.nivel = 1;
    }

    private boolean validarCampos() {
        if (this.editTxtUsuario.getText().toString().isEmpty()) {
            this.toastConfiguration("Introduzca el nombre de usuario.");

            return false;
        } else if (this.editTxtClave.getText().toString().isEmpty()) {
            this.toastConfiguration("Introduzca la clave.");

            return false;
        }

        return true;
    }

    private boolean validarUsuario() {
        if (this.editTxtUsuario.getText().toString().isEmpty()) {
            this.toastConfiguration("Introduzca el nombre de usuario.");

            return false;
        }

        return true;
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

    private void actualizarUsuario(String usuario, String clave) {
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
                JSONArray newJsonArray = new JSONArray();

                JSONObject jsonObject;

                JSONObject newJsonObject = new JSONObject();
                newJsonObject.put(this.campoNombreUsuario, usuario);
                newJsonObject.put(this.campoClave, clave);
                newJsonObject.put(this.campoNivel, String.valueOf(this.nivel));

                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = new JSONObject(jsonArray.getString(i));

                    if (!jsonObject.getString(this.campoNombreUsuario).toLowerCase().equals(usuario.toLowerCase())) {
                        newJsonArray.put(jsonObject);
                    } else {
                        newJsonArray.put(newJsonObject);
                    }
                }

                fileOutputStream = new FileOutputStream(archivoFisico);

                fileOutputStream.write(newJsonArray.toString().getBytes());
                fileOutputStream.close();

                fileInputStream.close();
            } catch (Exception e) {
                Log.e("JSONException", e.getMessage());
            }
        }
    }

    private void eliminarUsuario(String usuario) {
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
                JSONArray newJsonArray = new JSONArray();

                JSONObject jsonObject = new JSONObject();

                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = new JSONObject(jsonArray.getString(i));

                    if (!jsonObject.getString(this.campoNombreUsuario).toLowerCase().equals(usuario.toLowerCase())) {
                        newJsonArray.put(jsonObject);
                    }
                }

                fileOutputStream = new FileOutputStream(archivoFisico);

                fileOutputStream.write(newJsonArray.toString().getBytes());
                fileOutputStream.close();

                fileInputStream.close();
            } catch (Exception e) {
                Log.e("JSONException", e.getMessage());
            }
        }
    }

    private boolean buscarUsuario(String usuario) {
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

                    if (jsonObject.getString(this.campoNombreUsuario).toLowerCase().equals(usuario.toLowerCase())) {
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

    private void listarUsuarios() {
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

                this.textoDeSalida = "--------------[ Detalles de los usuarios ]------------\n";

                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = new JSONObject(jsonArray.getString(i));

                    this.textoDeSalida = this.textoDeSalida + "\nUsuario: " + jsonObject.getString(this.campoNombreUsuario) + "\n";
                    this.textoDeSalida = this.textoDeSalida + "Clave: " + jsonObject.getString(this.campoClave) + "\n";
                    this.textoDeSalida = this.textoDeSalida + "Nivel: " + jsonObject.getString(this.campoNivel) + "\n";
                }

                this.textoDeSalida = this.textoDeSalida + "\n------------------------------------------------------------------\n";

                this.txtVTexto.setText(this.textoDeSalida);

                fileInputStream.close();
            } catch (Exception e) {
                Log.e("JSONException", e.getMessage());
            }
        } else {

        }
    }

    private void toastConfiguration(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}
