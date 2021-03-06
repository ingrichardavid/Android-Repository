package com.example.ing_richardavid.ejemplo07as.models.databases.sqlite.configuration;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.widget.Toast;
import android.util.Log;

import com.example.ing_richardavid.ejemplo07as.models.databases.sqlite.entities.Usuarios;

/**
 * Created by ing_richardavid on 14-09-17.
 */

public class CrearBD extends SQLiteOpenHelper {

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

    public CrearBD(Context context) {
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
}
