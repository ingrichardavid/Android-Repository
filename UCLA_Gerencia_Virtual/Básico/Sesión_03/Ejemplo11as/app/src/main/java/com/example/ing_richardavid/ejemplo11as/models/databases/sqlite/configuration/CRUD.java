package com.example.ing_richardavid.ejemplo11as.models.databases.sqlite.configuration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.widget.Toast;
import android.util.Log;

import com.example.ing_richardavid.ejemplo11as.models.databases.sqlite.entities.Usuarios;

import java.util.Vector;

/**
 * Created by ing_richardavid on 14-09-17.
 */

public class CRUD extends SQLiteOpenHelper {

    /**
     * Objects, variables and constants.
     */

    private Context context;
    private static final String NOMBRE_BASEDATOS = "miproyecto.db";
    private static final int VERSION_BASEDATOS = 2;
    private SQLiteDatabase sqLiteDatabase;

    /**
     * Builder.
     * @param context
     */

    public CRUD(Context context) {
        super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);

        this.context = context;
        this.sqLiteDatabase = getWritableDatabase();
    }

    /**
     * Functions SQLiteOpenHelper.
     */

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + Usuarios.TABLA + " (" + Usuarios.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + Usuarios.USUARIO + " TEXT," + Usuarios.CLAVE + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        String OldVersion = Integer.toString(oldVersion);
        String NewVersion = Integer.toString(newVersion);

        Toast toast = Toast.makeText(this.context, "Mensaje: Actualizando la Base de Datos de la version "+ OldVersion + " a " + NewVersion + ", lo cual destruira los datos anteriores", Toast.LENGTH_SHORT);
        toast.show();

        Log.w("Mensaje: ", "Actualizando la Base de Datos de la version "+ OldVersion + " a " + NewVersion + ", lo cual destruira los datos anteriores");

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Usuarios.TABLA);

        this.onCreate(sqLiteDatabase);
    }

    /**
     * Functions: Self.
     */

    public long insertarUsuario(String usuario, String clave) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Usuarios.USUARIO, usuario);
        contentValues.put(Usuarios.CLAVE, clave);

        return this.sqLiteDatabase.insert(Usuarios.TABLA, Usuarios.TABLA, contentValues);
    }

    public boolean buscarUsuario(String usuario, String clave, Vector Usuario) {
        boolean encontrado = false;
        String[] parametros = new String[] {usuario, clave};
        String[] columnas = new String[] {Usuarios.ID, Usuarios.USUARIO, Usuarios.CLAVE, Usuarios.NIVEL};

        try {
            Cursor cursor = this.sqLiteDatabase.query(Usuarios.TABLA, columnas, "usuario = ? AND clave = ?", parametros, null, null, null);

            while(cursor.moveToNext()){
                encontrado = true;

                Usuario.add((String) cursor.getString(0));
                Usuario.add((String) cursor.getString(1));
                Usuario.add((String) cursor.getString(2));
                Usuario.add((String) cursor.getString(3));
            }
        } catch (Exception e) {
            Log.w("Error: ", e.getMessage());
        }

        return encontrado;
    }

    public boolean alterar(String sql) {
        boolean exito = false;

        try {
            this.sqLiteDatabase.execSQL(sql);

            exito = true;
        } catch (Exception e) {
            Log.w("Error: ", e.getMessage());
        }

        return exito;
    }

    public boolean actualizarUsuario(ContentValues contentValues, String condicion, String[] parametros) {
        boolean exito = false;

        try {
            this.sqLiteDatabase.update(Usuarios.TABLA, contentValues, condicion, parametros);

            exito = true;
        } catch (Exception e) {
            Log.w("Error: ", e.getMessage());
        }

        return exito;
    }

    public boolean eliminarUsuario(String condicion, String[] parametros) {
        boolean exito = false;

        try {
            if (this.sqLiteDatabase.delete(Usuarios.TABLA, condicion, parametros) > 0) {
                exito = true;
            }
        } catch (Exception e) {
            Log.w("Error: ", e.getMessage());
        }

        return exito;
    }

}