package com.example.ing_richardavid.ejemplo16as;

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

import com.example.ing_richardavid.ejemplo14as.R;

import org.kxml2.io.*;
import org.kxml2.kdom.*;

import java.io.*;

public class activity_ejemplo16as extends AppCompatActivity implements View.OnClickListener {

    /**
     * Objects, variables and constants.
     */

    private Document documento;
    private Element raiz, fila, campo, valor;
    private File ruta;
    private String nombreArchivo = "usuarios.xml";
    private int nivel = 1;

    /**
     * Components.
     */

    private EditText editTxtUsuario;
    private EditText editTxtClave;
    private RadioButton rBtnNivel1;
    private RadioButton rBtnNivel2;
    private RadioButton rBtnNivel3;
    private TextView txtVTexto;
    private String textoDeSalida;

    /**
     * Functions: AppCompatActivity.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo16as);

        this.crearArchivoXML();

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
                        this.removerUsuario(this.editTxtUsuario.getText().toString());
                        this.crearUsuario(this.editTxtUsuario.getText().toString(), this.editTxtClave.getText().toString());
                        this.listarUsuarios();
                        this.limpiarCampos();
                    } else {
                        this.toastConfiguration("El usuario no se encuentra registrado.");
                    }
                }

                return true;
            case R.id.itemRemoverUsuario:
                if (this.validarUsuario()) {
                    if (this.buscarUsuario(this.editTxtUsuario.getText().toString())) {
                        this.removerUsuario(this.editTxtUsuario.getText().toString());
                        this.listarUsuarios();
                        this.limpiarCampos();
                    } else {
                        this.toastConfiguration("El usuario no se encuentra registrado.");
                    }
                }
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

    private void toastConfiguration(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
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

    private void crearArchivoXML() {
        this.ruta = new File(this.getFilesDir().getPath() + "/Ejemplo16as");

        this.documento = new Document();

        this.raiz = this.documento.createElement("", "usuarios");

        this.documento.addChild(this.raiz.ELEMENT, this.raiz);

        //Crear un directorio particular dentro de lo permitidos en Android

        if (!this.ruta.exists()) {
            if (!this.ruta.mkdir()){
                Log.i("Mensaje","Error al crear archivo");
            }
        }

        //Archivo físico xml

        File file = new File(this.ruta, this.nombreArchivo);
        DataOutputStream dataOutputStream = null;

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException io) {
                Log.e("IOException",io.toString());
            }
        }

        //Stream de memoria donde se vaciara el archivo xml o viceversa

        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            Log.e("FileNotFoundException",e.toString());
        }

        try {
            KXmlSerializer kXmlSerializer = new KXmlSerializer();

            //Para identar el documento usuarios.xml, equivale a pretty en JDOM y PHP
            kXmlSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);

            kXmlSerializer.setOutput(fileOutputStream, "UTF-8");

            kXmlSerializer.flush();

            this.documento.write(kXmlSerializer);

            fileOutputStream.close();

            this.toastConfiguration("Mensaje: El directorio es " + this.ruta);
            this.toastConfiguration("Mensaje: usuario.xml creado");

            Log.i("RUTA DE ARCHIVO", "Mensaje: El directorio es " + this.ruta);
        } catch (Exception e) {
            Log.e("FileNotFoundException", e.toString());
        }
    }

    private void crearUsuario(String usuario, String clave) {
        //Archivo físico XML.
        File file = new File(this.ruta, this.nombreArchivo);

        FileInputStream fileInputStream = null;

        this.documento = new Document();

        if (file.exists()) {
            //Generar stream de memoria donde se abrira el archivo XML.

            try {
                fileInputStream = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                Log.e("FileNotFoundException", e.toString());
            }

            try {
                KXmlParser kXmlParser = new KXmlParser();
                kXmlParser.setInput(fileInputStream, "UTF-8");

                this.documento.parse(kXmlParser);
            } catch (Exception e) {
                Log.e("Exception", e.toString());
            }

            //Stream de memoria donde se vaciara el archivo xml o viceversa

            FileOutputStream fileOutputStream = null;

            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                Log.e("FileNotFoundException",e.toString());
            }

            //Leemos el nodo raíz.

            this.raiz = this.documento.getRootElement();

            //Creando el nodo.
            this.fila = this.documento.createElement("", "fila");

            //Primer campo de la fila.
            this.campo = this.documento.createElement("", "campo");
            this.campo.setAttribute(null, "id", "tusuario");
            this.campo.setAttribute(null, "taborder", "1");

            this.valor = this.documento.createElement("", "valor");
            this.valor.addChild(0, Node.TEXT, usuario);

            this.campo.addChild(0, Node.ELEMENT, this.valor);

            this.fila.addChild(0, Node.ELEMENT, this.campo);

            //Segundo campo de la fila.

            this.campo = this.documento.createElement("", "campo");
            this.campo.setAttribute(null, "id", "tclave");
            this.campo.setAttribute(null, "taborder", "2");

            this.valor = this.documento.createElement("", "valor");
            this.valor.addChild(0, Node.TEXT, clave);

            this.campo.addChild(0, Node.ELEMENT, this.valor);

            this.fila.addChild(1, Node.ELEMENT, this.campo);

            //Tercer campo de la fila.

            this.campo = this.documento.createElement("", "campo");
            this.campo.setAttribute(null, "id", "tnivel");
            this.campo.setAttribute(null, "taborder", "3");

            this.valor = this.documento.createElement("", "valor");
            this.valor.addChild(0, Node.TEXT, String.valueOf(this.nivel));

            this.campo.addChild(0, Node.ELEMENT, this.valor);

            this.fila.addChild(2, Node.ELEMENT, this.campo);

            //Adicionando elemento a la raíz.

            this.raiz.addChild(0, Node.ELEMENT, this.fila);

            this.documento.addChild(0, Node.ELEMENT, this.raiz);

            try {
                KXmlSerializer kXmlSerializer = new KXmlSerializer();

                //Para identar el documento usuarios.xml, equivale a pretty en JDOM y PHP
                kXmlSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);

                kXmlSerializer.setOutput(fileOutputStream, "UTF-8");

                kXmlSerializer.flush();

                this.documento.write(kXmlSerializer);

                fileOutputStream.close();
            } catch (Exception e) {
                Log.e("FileNotFoundException", e.toString());
            }
        } else {
            this.toastConfiguration("Mensaje: El archivo XML no existe.");
        }
    }

    private void actualizarUsuario(String usuario, String clave) {
        int m = 0;

        //Archivo físico XML.
        File file = new File(this.ruta, this.nombreArchivo);

        FileInputStream fileInputStream = null;

        this.documento = new Document();

        if (file.exists()) {
            //Generar stream de memoria donde se abrira el archivo XML.

            try {
                fileInputStream = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                Log.e("FileNotFoundException", e.toString());
            }

            try {
                KXmlParser kXmlParser = new KXmlParser();
                kXmlParser.setInput(fileInputStream, "UTF-8");

                this.documento.parse(kXmlParser);
            } catch (Exception e) {
                Log.e("Exception", e.toString());
            }

            //Leemos el nodo raíz.

            this.raiz = this.documento.getRootElement();

            //Contamos los nodos hijos.

            int nodos_hijos = this.raiz.getChildCount();
            boolean indicador = true;
            String nuevoUsuario = "";
            String nuevaClave = "";
            int nuevoNivel = 0;
            int nodoEliminar = -1;

            if (nodos_hijos > 0) {
                for (int i=0; i < nodos_hijos; i++) {
                    if (this.raiz.getType(i) != Node.ELEMENT) {
                        continue;
                    }

                    this.fila = this.raiz.getElement(i);

                    if (!this.fila.getName().equals("fila")) {
                        continue;
                    }

                    //Contamos los hijos del nodo.

                    int hijos_del_nodo = this.fila.getChildCount();

                    for (int j=0; j < hijos_del_nodo; j++) {
                        if (this.fila.getType(j) != Node.ELEMENT) {
                            continue;
                        }

                        this.campo = this.fila.getElement(j);

                        //Contamos si el hijo tiene campos.

                        int campos = this.campo.getChildCount();

                        for (int k=0; k < campos; k++) {
                            if (this.campo.getType(k) != Node.ELEMENT) {
                                continue;
                            }

                            this.valor = this.campo.getElement(k);

                            if (m == 0 && indicador) {
                                if (this.valor.getText(0).equals(usuario)) {
                                    nuevoUsuario = usuario;
                                    nodoEliminar = i;

                                    indicador = false;
                                } else {
                                    indicador = true;
                                }
                            }

                            if (m == 1 && !indicador) {
                                nuevaClave = clave;
                            }

                            if (m == 2 && !indicador) {
                                nuevoNivel = this.nivel;
                            }

                            m++;

                            this.valor = null;
                        }

                        this.campo = null;
                    }

                    m = 0;
                }

                Log.i("NODO A ELIMINAR", String.valueOf(nodoEliminar));
                Log.i("NUEVO USUARIO", nuevoUsuario);
                Log.i("NUEVA CLAVE", nuevaClave);
                Log.i("NUEVO NIVEL", String.valueOf(nuevoNivel));

                try {
                    fileInputStream.close();
                } catch (Exception e) {
                    Log.e("Exception", e.toString());
                }
            } else {
                this.textoDeSalida = "No existen datos almacenados";
            }
        }
    }

    private void removerUsuario(String usuario) {
        int m = 0;

        //Archivo físico XML.
        File file = new File(this.ruta, this.nombreArchivo);

        FileInputStream fileInputStream = null;

        this.documento = new Document();

        if (file.exists()) {
            //Generar stream de memoria donde se abrira el archivo XML.

            try {
                fileInputStream = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                Log.e("FileNotFoundException", e.toString());
            }

            try {
                KXmlParser kXmlParser = new KXmlParser();
                kXmlParser.setInput(fileInputStream, "UTF-8");

                this.documento.parse(kXmlParser);
            } catch (Exception e) {
                Log.e("Exception", e.toString());
            }

            //Leemos el nodo raíz.

            this.raiz = this.documento.getRootElement();

            //Contamos los nodos hijos.

            int nodos_hijos = this.raiz.getChildCount();

            int nodoEliminar = -1;

            if (nodos_hijos > 0) {
                for (int i=0; i < nodos_hijos; i++) {
                    if (this.raiz.getType(i) != Node.ELEMENT) {
                        continue;
                    }

                    this.fila = this.raiz.getElement(i);

                    if (!this.fila.getName().equals("fila")) {
                        continue;
                    }

                    //Contamos los hijos del nodo.

                    int hijos_del_nodo = this.fila.getChildCount();

                    for (int j=0; j < hijos_del_nodo; j++) {
                        if (this.fila.getType(j) != Node.ELEMENT) {
                            continue;
                        }

                        this.campo = this.fila.getElement(j);

                        //Contamos si el hijo tiene campos.

                        int campos = this.campo.getChildCount();

                        for (int k=0; k < campos; k++) {
                            if (this.campo.getType(k) != Node.ELEMENT) {
                                continue;
                            }

                            this.valor = this.campo.getElement(k);

                            if (m == 0) {
                                if (this.valor.getText(0).equals(usuario)) {
                                    nodoEliminar = i;
                                }
                            }

                            m++;

                            this.valor = null;
                        }

                        this.campo = null;
                    }

                    m = 0;

                    this.fila = null;
                }

                this.raiz.removeChild(nodoEliminar);

                //Stream de memoria donde se vaciara el archivo xml o viceversa

                FileOutputStream fileOutputStream = null;

                try {
                    fileOutputStream = new FileOutputStream(file);
                } catch (FileNotFoundException e) {
                    Log.e("FileNotFoundException",e.toString());
                }

                try{
                    KXmlSerializer kXmlSerializer = new KXmlSerializer();

                    //Para identar el documento usuarios.xml, equivale a pretty en JDOM y PHP
                    kXmlSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);

                    kXmlSerializer.setOutput(fileOutputStream, "UTF-8");

                    kXmlSerializer.flush();

                    this.documento.write(kXmlSerializer);

                    fileInputStream.close();
                } catch (Exception e) {
                    Log.e("Exception", e.toString());
                }
            } else {
                this.textoDeSalida = "No existen datos almacenados";
            }
        }
    }

    private boolean buscarUsuario(String usuario) {
        int m = 0;

        boolean usuarioEncontrado = false;

        //Archivo físico XML.
        File file = new File(this.ruta, this.nombreArchivo);

        FileInputStream fileInputStream = null;

        this.documento = new Document();

        if (file.exists()) {
            //Generar stream de memoria donde se abrira el archivo XML.

            try {
                fileInputStream = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                Log.e("FileNotFoundException", e.toString());
            }

            try {
                KXmlParser kXmlParser = new KXmlParser();
                kXmlParser.setInput(fileInputStream, "UTF-8");

                this.documento.parse(kXmlParser);
            } catch (Exception e) {
                Log.e("Exception", e.toString());
            }

            //Leemos el nodo raíz.

            this.raiz = this.documento.getRootElement();

            //Contamos los nodos hijos.

            int nodos_hijos = this.raiz.getChildCount();

            if (nodos_hijos > 0) {
                for (int i=0; i < nodos_hijos; i++) {
                    if (this.raiz.getType(i) != Node.ELEMENT) {
                        continue;
                    }

                    this.fila = this.raiz.getElement(i);

                    if (!this.fila.getName().equals("fila")) {
                        continue;
                    }

                    //Contamos los hijos del nodo.

                    int hijos_del_nodo = this.fila.getChildCount();

                    for (int j=0; j < hijos_del_nodo; j++) {
                        if (this.fila.getType(j) != Node.ELEMENT) {
                            continue;
                        }

                        this.campo = this.fila.getElement(j);

                        //Contamos si el hijo tiene campos.

                        int campos = this.campo.getChildCount();

                        for (int k=0; k < campos; k++) {
                            if (this.campo.getType(k) != Node.ELEMENT) {
                                continue;
                            }

                            this.valor = this.campo.getElement(k);

                            if (m == 0) {
                                if (this.valor.getText(0).equals(usuario)) {
                                    usuarioEncontrado = true;
                                }
                            }

                            m++;

                            this.valor = null;
                        }

                        this.campo = null;
                    }

                    m = 0;

                    this.fila = null;
                }

                try{
                    fileInputStream.close();
                } catch (Exception e) {
                    Log.e("Exception", e.toString());
                }
            } else {
                this.textoDeSalida = "No existen datos almacenados";
            }
        }

        return usuarioEncontrado;
    }

    private void listarUsuarios() {
        int m = 0;

        //Archivo físico XML.
        File file = new File(this.ruta, this.nombreArchivo);

        FileInputStream fileInputStream = null;

        this.documento = new Document();

        if (file.exists()) {
            //Generar stream de memoria donde se abrira el archivo XML.

            try {
                fileInputStream = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                Log.e("FileNotFoundException", e.toString());
            }

            try {
                KXmlParser kXmlParser = new KXmlParser();
                kXmlParser.setInput(fileInputStream, "UTF-8");

                this.documento.parse(kXmlParser);
            } catch (Exception e) {
                Log.e("Exception", e.toString());
            }

            //Leemos el nodo raíz.

            this.raiz = this.documento.getRootElement();

            //Contamos los nodos hijos.

            int nodos_hijos = this.raiz.getChildCount();

            if (nodos_hijos > 0) {
                this.textoDeSalida = "------------[ Lista de Usuarios ]-----------\n";

                for (int i=0; i < nodos_hijos; i++) {
                    if (this.raiz.getType(i) != Node.ELEMENT) {
                        continue;
                    }

                    this.fila = this.raiz.getElement(i);

                    if (!this.fila.getName().equals("fila")) {
                        continue;
                    }

                    //Contamos los hijos del nodo.

                    int hijos_del_nodo = this.fila.getChildCount();

                    for (int j=0; j < hijos_del_nodo; j++) {
                        if (this.fila.getType(j) != Node.ELEMENT) {
                            continue;
                        }

                        this.campo = this.fila.getElement(j);

                        //Contamos si el hijo tiene campos.

                        int campos = this.campo.getChildCount();

                        for (int k=0; k < campos; k++) {
                            if (this.campo.getType(k) != Node.ELEMENT) {
                                continue;
                            }

                            this.valor = this.campo.getElement(k);

                            if (m == 0) {
                                this.textoDeSalida = this.textoDeSalida  + "Usuario: " + this.valor.getText(0) + "\n";
                            }

                            if (m == 1) {
                                this.textoDeSalida = this.textoDeSalida  + "Clave: " + this.valor.getText(0) + "\n";
                            }

                            if (m == 2) {
                                this.textoDeSalida = this.textoDeSalida  + "Nivel: " + this.valor.getText(0) + "\n";
                            }

                            m++;

                            this.valor = null;
                        }

                        this.campo = null;
                    }

                    m = 0;

                    this.textoDeSalida = this.textoDeSalida + "-----------------------------------------------------\n";

                    this.fila = null;

                    this.txtVTexto.setText(this.textoDeSalida);
                }

                try{
                    fileInputStream.close();
                } catch (Exception e) {
                    Log.e("Exception", e.toString());
                }
            } else {
                this.txtVTexto.setText("No existen datos almacenados");
            }
        }
    }

}