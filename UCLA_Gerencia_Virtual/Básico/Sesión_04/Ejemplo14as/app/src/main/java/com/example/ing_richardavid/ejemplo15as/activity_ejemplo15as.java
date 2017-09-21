package com.example.ing_richardavid.ejemplo15as;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ing_richardavid.ejemplo14as.R;

import org.kxml2.io.*;
import org.kxml2.kdom.*;

import java.io.*;

public class activity_ejemplo15as extends AppCompatActivity {

    /**
     * Objects, variables and constants.
     */

    private Document documento;
    private Element raiz, fila, campo, valor;
    private File ruta;
    private String nombreArchivo = "usuarios.xml";

    /**
     * Components.
     */

    private TextView txtVTexto;
    private String textoDeSalida;

    /**
     * Functions: AppCompatActivity.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo15as);

        this.ruta = new File(this.getFilesDir().getPath() + "/Ejemplo12as");

        this.documento = new Document();

        this.raiz = this.documento.createElement("", "usuarios");

        this.documento.addChild(this.raiz.ELEMENT, this.raiz);

        //Agregando primer nodo al archivo XML.

            //Creando el nodo fila 1.
        this.fila = this.documento.createElement("", "fila");

            //Primer campo de la fila.
        this.campo = this.documento.createElement("", "campo");
        this.campo.setAttribute(null, "id", "tusuario");
        this.campo.setAttribute(null, "taborder", "1");

        this.valor = this.documento.createElement("", "valor");
        this.valor.addChild(0, Node.TEXT, "richard");

        this.campo.addChild(0, Node.ELEMENT, this.valor);

        this.fila.addChild(0, Node.ELEMENT, this.campo);

            //Segundo campo de la fila.

        this.campo = this.documento.createElement("", "campo");
        this.campo.setAttribute(null, "id", "tclave");
        this.campo.setAttribute(null, "taborder", "2");

        this.valor = this.documento.createElement("", "valor");
        this.valor.addChild(0, Node.TEXT, "123");

        this.campo.addChild(0, Node.ELEMENT, this.valor);

        this.fila.addChild(1, Node.ELEMENT, this.campo);

            //Tercer campo de la fila.

        this.campo = this.documento.createElement("", "campo");
        this.campo.setAttribute(null, "id", "tnivel");
        this.campo.setAttribute(null, "taborder", "3");

        this.valor = this.documento.createElement("", "valor");
        this.valor.addChild(0, Node.TEXT, "1");

        this.campo.addChild(0, Node.ELEMENT, this.valor);

        this.fila.addChild(2, Node.ELEMENT, this.campo);

            //Adicionando el primer elemento a la raiz.

        this.raiz.addChild(0, Node.ELEMENT, this.fila);

        this.toastConfiguration("Mensaje: Agregado el nodo 1 en memoria");

        //Agregando segundo nodo al archivo XML.

            //Creando el nodo fila 1.
        this.fila = this.documento.createElement("", "fila");

            //Primer campo de la fila.
        this.campo = this.documento.createElement("", "campo");
        this.campo.setAttribute(null, "id", "tusuario");
        this.campo.setAttribute(null, "taborder", "1");

        this.valor = this.documento.createElement("", "valor");
        this.valor.addChild(0, Node.TEXT, "maría fernanda");

        this.campo.addChild(0, Node.ELEMENT, this.valor);

        this.fila.addChild(0, Node.ELEMENT, this.campo);

            //Segundo campo de la fila.

        this.campo = this.documento.createElement("", "campo");
        this.campo.setAttribute(null, "id", "tclave");
        this.campo.setAttribute(null, "taborder", "2");

        this.valor = this.documento.createElement("", "valor");
        this.valor.addChild(0, Node.TEXT, "321");

        this.campo.addChild(0, Node.ELEMENT, this.valor);

        this.fila.addChild(1, Node.ELEMENT, this.campo);

            //Tercer campo de la fila.

        this.campo = this.documento.createElement("", "campo");
        this.campo.setAttribute(null, "id", "tnivel");
        this.campo.setAttribute(null, "taborder", "3");

        this.valor = this.documento.createElement("", "valor");
        this.valor.addChild(0, Node.TEXT, "2");

        this.campo.addChild(0, Node.ELEMENT, this.valor);

        this.fila.addChild(2, Node.ELEMENT, this.campo);

            //Adicionando el segundo elemento a la raiz.

        this.raiz.addChild(1, Node.ELEMENT, this.fila);

        this.toastConfiguration("Mensaje: Agregado el nodo 2 en memoria");

        //Una vez terminado de agregar todos los nodos adicionar la raíz al documento.

        this.documento.addChild(0, Node.ELEMENT, this.raiz);

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

        /**
         * Registering components.
         */

        this.txtVTexto = (TextView)findViewById(R.id.txtVIdTexto);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.mainmenu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                this.listarNodos();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Functions: Self.
     */

    private void toastConfiguration(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    private void listarNodos() {
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
                this.textoDeSalida = "No existen datos almacenados";
            }
        }
    }
}