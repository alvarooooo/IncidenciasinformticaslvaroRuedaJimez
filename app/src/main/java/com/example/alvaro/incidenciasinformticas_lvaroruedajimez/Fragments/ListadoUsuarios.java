package com.example.alvaro.incidenciasinformticas_lvaroruedajimez.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alvaro.incidenciasinformticas_lvaroruedajimez.ConexionesSQLiteHelper;
import com.example.alvaro.incidenciasinformticas_lvaroruedajimez.R;
import com.example.alvaro.incidenciasinformticas_lvaroruedajimez.Utilidades.Utilidades;
import com.example.alvaro.incidenciasinformticas_lvaroruedajimez.entidades.Usuario;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListadoUsuarios.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListadoUsuarios#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListadoUsuarios extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    ////////////////// MIS VARIABLES ///////////////////////
    ListView listaPersonas;
    ArrayList<String> listaInformacion;
    ArrayList<Usuario> listaUsuarios;
    ConexionesSQLiteHelper conn;



    public ListadoUsuarios() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListadoUsuarios.
     */
    // TODO: Rename and change types and number of parameters
    public static ListadoUsuarios newInstance(String param1, String param2) {
        ListadoUsuarios fragment = new ListadoUsuarios();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

  /*  @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_listado_usuarios, container, false);

        conn = new ConexionesSQLiteHelper(view.getContext(), "DB_IncidenciasInformaticas", null, 1);
        listaPersonas = (ListView) view.findViewById(R.id.listViewPersonas);

        consultarListaPersonas();

        AdaptadorUsuarios adaptador = new AdaptadorUsuarios((AppCompatActivity) view.getContext());
        listaPersonas.setAdapter(adaptador);

        listaPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                String informacion = "Nombre: " + listaUsuarios.get(pos).getNombre() + "\n";
                informacion += "Apellidos: " + listaUsuarios.get(pos).getApellidos() + "\n";
                informacion += "DNI: " + listaUsuarios.get(pos).getDNI() + "\n";
                informacion += "Nombre de Usuario: " + listaUsuarios.get(pos).getNombre_usuario() + "\n";
                informacion += "Contraseña: " + listaUsuarios.get(pos).getContrasenia() + "\n";
                if (listaUsuarios.get(pos).isEsInformatico()) {
                    informacion += "Tipo de usuario: Administrador";
                } else {
                    informacion += "Tipo de usuario: Usuario";
                }

                Toast.makeText(view.getContext(), informacion, Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    private void consultarListaPersonas() {
        SQLiteDatabase db = conn.getReadableDatabase();

        Usuario usuario = null;
        listaUsuarios = new ArrayList<Usuario>();
        Cursor cursor = db.rawQuery("Select * FROM "+Utilidades.TABLA_USUARIOS, null);

        while (cursor.moveToNext()) {
            usuario = new Usuario(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    Boolean.valueOf(cursor.getString(6))
            );
            listaUsuarios.add(usuario);
        }
    }

    class AdaptadorUsuarios extends ArrayAdapter<Usuario> {

        AppCompatActivity appCompatActivity;

        AdaptadorUsuarios(AppCompatActivity context) {
            super(context, R.layout.item_listview_usuario, listaUsuarios);
            appCompatActivity = context;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.item_listview_usuario, null);

            TextView textView1 = (TextView) item.findViewById(R.id.descripcion_usuario);
            textView1.setText("Nombre: " + listaUsuarios.get(position).getNombre() + "\n"
                    + "Apellidos: " + listaUsuarios.get(position).getApellidos() + "\n"
                    + "DNI: " + listaUsuarios.get(position).getDNI());

            ImageView imagenUsuario = (ImageView) item.findViewById(R.id.img_usuario);
            if (listaUsuarios.get(position).getFoto() == null || listaUsuarios.get(position).getFoto().equals("")) {
                imagenUsuario.setImageResource(R.drawable.persona_default);
            } else {
                File imagen = new File(listaUsuarios.get(position).getFoto());
                Uri miPath = Uri.fromFile(imagen);
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), miPath);
                    bitmap = redimensionarImagen(bitmap, 300, 300);
                    imagenUsuario.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return (item);
        }

        private Bitmap redimensionarImagen(Bitmap bitmap, float anchoNuevo, float altoNuevo) {
            int ancho = bitmap.getWidth();
            int alto = bitmap.getHeight();

            if (ancho>anchoNuevo || alto>altoNuevo) {
                float escalaAncho = anchoNuevo/ancho;
                float escalaAlto = altoNuevo/alto;

                Matrix matrix = new Matrix();
                matrix.postScale(escalaAncho, escalaAlto);

                return Bitmap.createBitmap(bitmap, 0, 0, ancho, alto, matrix, false);
            } else {
                return bitmap;
            }
        }
    }
}
