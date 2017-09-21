package com.example.ing_richardavid.ejemplo12as;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.kxml2.io.*;
import org.kxml2.kdom.*;

import java.io.*;

public class activity_ejemplo12as extends AppCompatActivity {

    /**
     * Objects, variables and constants.
     */

    private Document document;
    private Element root, row, field, value;
    private File path;
    private String nombreArchivo = "usuarios.xml";

    /**
     * Functions: AppCompatActivity.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo12as);

        this.path = new File(this.getFilesDir().getPath() + "/Ejemplo12as");

        this.document = new Document();

        this.root = this.document.createElement("", "usuarios");

        this.document.addChild(this.root.ELEMENT, this.root);

        //Crear un directorio particular dentro de lo permitidos en Android

        if (!this.path.exists()) {
            if (!this.path.mkdir()){
                Log.i("Mensaje","Error al crear archivo");
            }
        }

        //Archivo fisico xml

        File file = new File(this.path, this.nombreArchivo);
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

            this.document.write(kXmlSerializer);

            fileOutputStream.close();

            this.toastConfiguration("Mensaje: El directorio es " + this.path);
            this.toastConfiguration("Mensaje: usuario.xml creado");
        } catch (Exception e) {
            Log.e("FileNotFoundException", e.toString());
        }

    }

    /**
     * Functions: Self.
     */

    private void toastConfiguration(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}