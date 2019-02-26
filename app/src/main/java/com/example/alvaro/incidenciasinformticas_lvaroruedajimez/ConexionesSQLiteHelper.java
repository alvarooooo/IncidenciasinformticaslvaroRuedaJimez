package com.example.alvaro.incidenciasinformticas_lvaroruedajimez;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.alvaro.incidenciasinformticas_lvaroruedajimez.Activities.MainActivity;
import com.example.alvaro.incidenciasinformticas_lvaroruedajimez.Utilidades.Utilidades;

public class ConexionesSQLiteHelper extends SQLiteOpenHelper {

    public ConexionesSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA_USUARIOS);
        db.execSQL(Utilidades.CREAR_TABLA_INCIDENDIAS);
        String insertUsuarioPorDefecto = "INSERT INTO "+ Utilidades.TABLA_USUARIOS+"("+Utilidades.CAMPO_USUARIOS_DNI+","+Utilidades.CAMPO_USUARIOS_NOMBRE+","
                +Utilidades.CAMPO_USUARIOS_APELLIDOS+","+Utilidades.CAMPO_USUARIOS_NOMBRE_USUARIO+","+Utilidades.CAMPO_USUARIOS_CONTRASENIA+","
                +Utilidades.CAMPO_USUARIOS_FOTO+","+Utilidades.CAMPO_USUARIOS_ES_INFORMATICO+") VALUES " +
                "('10862942L','Administrador','Por Defecto','admin','1234ASdf','','true')";
        db.execSQL(insertUsuarioPorDefecto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        db.execSQL("DROP TABLE IF EXISTS incidencias");
        onCreate(db);
    }
}
