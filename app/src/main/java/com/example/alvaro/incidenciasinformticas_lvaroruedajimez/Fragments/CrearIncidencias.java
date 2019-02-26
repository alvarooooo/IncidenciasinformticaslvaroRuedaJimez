package com.example.alvaro.incidenciasinformticas_lvaroruedajimez.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.alvaro.incidenciasinformticas_lvaroruedajimez.Activities.MainActivity;
import com.example.alvaro.incidenciasinformticas_lvaroruedajimez.ConexionesSQLiteHelper;
import com.example.alvaro.incidenciasinformticas_lvaroruedajimez.R;
import com.example.alvaro.incidenciasinformticas_lvaroruedajimez.Utilidades.Utilidades;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CrearIncidencias.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CrearIncidencias#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CrearIncidencias extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    ////////////////// MIS VARIABLES ///////////////////////
    EditText dni, fecha_incidencia, observaciones, dni_informatico, fecha_resolucion, observaciones_informatico;
    Button aniadeIncidencia;
    Spinner estado_incidencia;



    public CrearIncidencias() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CrearIncidencias.
     */
    // TODO: Rename and change types and number of parameters
    public static CrearIncidencias newInstance(String param1, String param2) {
        CrearIncidencias fragment = new CrearIncidencias();
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

   /* @Override
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
        final View view = inflater.inflate(R.layout.fragment_crear_incidencias, container, false);

        estado_incidencia = (Spinner) view.findViewById(R.id.estado_incidencia);
        String[] items = new String[]{"Creada", "Aprobada", "Resuelta"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(), R.layout.support_simple_spinner_dropdown_item, items);
        estado_incidencia.setAdapter(adapter);


        dni = (EditText) view.findViewById(R.id.dni_usuario);
        observaciones = (EditText) view.findViewById(R.id.observaciones_usuario);
        fecha_incidencia = (EditText)  view.findViewById(R.id.fecha_incidencia);
        dni_informatico = (EditText) view.findViewById(R.id.dni_informatico);
        fecha_resolucion = (EditText) view.findViewById(R.id.fecha_resolucion);
        observaciones_informatico = (EditText) view.findViewById(R.id.observaciones_informatico);


        aniadeIncidencia = (Button) view.findViewById(R.id.aniade_incidencia);
        aniadeIncidencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConexionesSQLiteHelper conn=new ConexionesSQLiteHelper(view.getContext(), "DB_IncidenciasInformaticas", null, 1);
                SQLiteDatabase db = conn.getWritableDatabase();

                String insert="INSERT INTO "+ Utilidades.TABLA_INCIDENCIAS+"("+Utilidades.CAMPO_INCIDENCIAS_DNI+","+Utilidades.CAMPO_INCIDENCIAS_FECHA_INCIDENCIA+","
                        +Utilidades.CAMPO_INCIDENCIAS_OBSERVACIONES+","+Utilidades.CAMPO_INCIDENCIAS_DNI_INFORMATICO+","+Utilidades.CAMPO_INCIDENCIAS_ESTADO_INCIDENCIAS+","
                        +Utilidades.CAMPO_INCIDENCIAS_FECHA_RESOLUCION_INCIDENCIA+","+Utilidades.CAMPO_INCIDENCIAS_OBSERVACIONES_INFORMATICO+") VALUES ( '"
                        +dni.getText().toString()+"','"+fecha_incidencia.getText().toString()+"','"+observaciones.getText().toString()+"','"
                        +dni_informatico.getText().toString()+"','"+estado_incidencia.getSelectedItem().toString()+"','"+fecha_resolucion.getText().toString()+"','"
                        +observaciones_informatico.getText().toString()+"' ) ";

                db.execSQL(insert);
                db.close();

                Toast.makeText(view.getContext(), "Incidencia registrada correctamente", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
