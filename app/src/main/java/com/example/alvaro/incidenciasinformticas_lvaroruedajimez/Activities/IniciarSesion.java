package com.example.alvaro.incidenciasinformticas_lvaroruedajimez.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alvaro.incidenciasinformticas_lvaroruedajimez.ConexionesSQLiteHelper;
import com.example.alvaro.incidenciasinformticas_lvaroruedajimez.R;
import com.example.alvaro.incidenciasinformticas_lvaroruedajimez.Utilidades.Utilidades;

public class IniciarSesion extends AppCompatActivity {

    private EditText usuario, contrasenia;
    private Button iniciarSesion;
    private int intentos = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*Creación de base datos*/
        final ConexionesSQLiteHelper conexion =
                new ConexionesSQLiteHelper(this, "DB_IncidenciasInformaticas", null, 1);

        iniciarSesion = (Button) findViewById(R.id.boton_inicio_sesion);
        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentos--;
                usuario = findViewById(R.id.usuario);
                contrasenia = findViewById(R.id.contrasenia);

                //Abrimos la base de datos 'DBUsuarios' en modo lectura
                SQLiteDatabase db = conexion.getReadableDatabase();

                //Ejecutamos la selección de los campos
                String query = "SELECT * FROM "+ Utilidades.TABLA_USUARIOS+" WHERE "
                        +Utilidades.CAMPO_USUARIOS_NOMBRE_USUARIO+"='"+usuario.getText().toString()+"' AND "
                        +Utilidades.CAMPO_USUARIOS_CONTRASENIA+"='"+contrasenia.getText().toString()+"'";
                Cursor cursor = db.rawQuery(query, null);

                //Preguntamos si se ha seleccionado algun campo
                if (cursor.getCount() > 0) {
                    Intent intent=new Intent(IniciarSesion.this,MainActivity.class);

                    //Verificamos si el usuario es administrdor o no
                    query = "SELECT "+Utilidades.CAMPO_USUARIOS_ES_INFORMATICO+" FROM "+ Utilidades.TABLA_USUARIOS+" WHERE "
                            +Utilidades.CAMPO_USUARIOS_NOMBRE_USUARIO+"='"+usuario.getText().toString()+"' AND "
                            +Utilidades.CAMPO_USUARIOS_CONTRASENIA+"='"+contrasenia.getText().toString()+"'";
                    Cursor cursor2 = db.rawQuery(query, null);
                    cursor2.moveToNext();

                    intent.putExtra("esAdministrador", Boolean.valueOf(cursor2.getString(0)));
                    startActivity(intent);
                    finish();
                } else {
                    if (intentos>0) {
                        Toast.makeText(getBaseContext(), "Usuario y/o contraseña incorrectos.\nLe quedan "+intentos+" intentos.", Toast.LENGTH_LONG).show();
                    } else {
                        iniciarSesion.setEnabled(false);
                        Toast.makeText(getBaseContext(), "Le quedan 0 intentos.\nSe ha bloqueado el inicio de sesión.", Toast.LENGTH_LONG).show();
                    }
                }
                db.close();
            }
        });
    }

}
