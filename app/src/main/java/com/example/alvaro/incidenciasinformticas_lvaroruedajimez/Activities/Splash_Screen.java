package com.example.alvaro.incidenciasinformticas_lvaroruedajimez.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.alvaro.incidenciasinformticas_lvaroruedajimez.R;

public class Splash_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Splash_Screen.this,IniciarSesion.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}
