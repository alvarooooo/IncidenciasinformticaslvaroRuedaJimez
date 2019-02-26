package com.example.alvaro.incidenciasinformticas_lvaroruedajimez.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.alvaro.incidenciasinformticas_lvaroruedajimez.Fragments.CrearIncidencias;
import com.example.alvaro.incidenciasinformticas_lvaroruedajimez.Fragments.Inicio;
import com.example.alvaro.incidenciasinformticas_lvaroruedajimez.Fragments.CrearUsuarioFragment;
import com.example.alvaro.incidenciasinformticas_lvaroruedajimez.Fragments.ListadoIncidencias;
import com.example.alvaro.incidenciasinformticas_lvaroruedajimez.Fragments.ListadoUsuarios;
import com.example.alvaro.incidenciasinformticas_lvaroruedajimez.R;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Boolean esAdmin;

    @SuppressLint("WrongViewCast")
    @Override
    public void onCreate(Bundle seavedInstanceState) {
        super.onCreate(seavedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navview);

        Intent intent = getIntent();
        esAdmin = intent.getExtras().getBoolean("esAdministrador");
        if (esAdmin) {
            navigationView.getMenu().findItem(R.id.crear_usuario).setVisible(true);
            navigationView.getMenu().findItem(R.id.crear_usuario).setEnabled(true);
            navigationView.getMenu().findItem(R.id.lista_usuarios).setVisible(true);
            navigationView.getMenu().findItem(R.id.lista_usuarios).setEnabled(true);
            navigationView.getMenu().findItem(R.id.lista_incidencias).setVisible(true);
            navigationView.getMenu().findItem(R.id.lista_incidencias).setEnabled(true);
        } else {
            navigationView.getMenu().findItem(R.id.crear_usuario).setVisible(false);
            navigationView.getMenu().findItem(R.id.crear_usuario).setEnabled(false);
            navigationView.getMenu().findItem(R.id.lista_usuarios).setVisible(false);
            navigationView.getMenu().findItem(R.id.lista_usuarios).setEnabled(false);
            navigationView.getMenu().findItem(R.id.lista_incidencias).setVisible(false);
            navigationView.getMenu().findItem(R.id.lista_incidencias).setEnabled(false);
        }


        setFragmentByDefault();

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

            }

            @Override
            public void onDrawerOpened(@NonNull View view) {
                //Toast.makeText(MainActivity.this, "Open", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(@NonNull View view) {
                //Toast.makeText(MainActivity.this, "Close", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                boolean fragmentTransaction = false;
                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.inicio:
                        fragment = new Inicio();
                        fragmentTransaction = true;
                        break;
                    case R.id.crear_usuario:
                        fragment = new CrearUsuarioFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.crear_incidencia:
                        fragment = new CrearIncidencias();
                        fragmentTransaction = true;
                        break;
                    case R.id.lista_usuarios:
                        fragment = new ListadoUsuarios();
                        fragmentTransaction = true;
                        break;
                    case R.id.lista_incidencias:
                        fragment = new ListadoIncidencias();
                        fragmentTransaction = true;
                        break;
                    /*case R.id.menu_opcion_1:
                        Toast.makeText(MainActivity.this, "Has pulsado en la opción 1", Toast.LENGTH_SHORT).show();
                        break;*/
                }

                if (fragmentTransaction) {
                    changeFragment(fragment, item);
                    drawerLayout.closeDrawers();
                }

                return true;
            }
        });
    }

    private void setToolbar() {
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setFragmentByDefault() {
        changeFragment(new Inicio(), navigationView.getMenu().getItem(0));
    }

    private void changeFragment(Fragment fragment, MenuItem item) {
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
        item.setChecked(true);
        getSupportActionBar().setTitle(item.getTitle());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Abrir el menú lateral
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
