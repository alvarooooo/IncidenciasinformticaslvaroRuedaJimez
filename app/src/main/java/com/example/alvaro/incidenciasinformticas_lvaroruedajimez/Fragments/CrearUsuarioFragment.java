package com.example.alvaro.incidenciasinformticas_lvaroruedajimez.Fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.alvaro.incidenciasinformticas_lvaroruedajimez.ConexionesSQLiteHelper;
import com.example.alvaro.incidenciasinformticas_lvaroruedajimez.R;
import com.example.alvaro.incidenciasinformticas_lvaroruedajimez.Utilidades.Utilidades;

import java.io.File;
import java.io.IOException;

import static java.lang.Boolean.TRUE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CrearUsuarioFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CrearUsuarioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CrearUsuarioFragment extends Fragment {
    private final CharSequence[] opciones = {"Tomar foto", "Elegir de la Galería", "Cancelar"};
    private AlertDialog.Builder builder = null;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private OnFragmentInteractionListener mListener;

    ////////////////// MIS VARIABLES ///////////////////////
    private static final String CARPETA_PRINCIPAL="imagenesApp/";
    private static final String CARPETA_IMAGEN="imagenesUsuarios/";
    private static final String DIRECTORIO_IMAGEN=CARPETA_PRINCIPAL+CARPETA_IMAGEN;
    File ficheroImagen;
    Bitmap bitmap;

    private static final int COD_SELECCIONA=10;
    private static final int COD_FOTO=20;
    ImageView imgFoto;
    ProgressDialog progreso;
    String rutaImagen = "";
    String rutaImagenAntigua;
    EditText campoNombre, campoApellidos, campoDni, campoNombre_usuario, campoContrasenia;
    Switch es_informatico;
    Button btnFoto, btnUsuario;
    Drawable imagenAntigua;

    public CrearUsuarioFragment() {
        // Required empty public constructor
    }

    public static CrearUsuarioFragment newInstance(String param1, String param2) {
        CrearUsuarioFragment fragment = new CrearUsuarioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crear_usuario, container, false);
    }*/

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*@Override
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

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ConexionesSQLiteHelper conn=new ConexionesSQLiteHelper(this, "bd_usuarios", null, 1);


    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_crear_usuario, container, false);

        campoNombre = (EditText) view.findViewById(R.id.nombre);
        campoApellidos = (EditText)  view.findViewById(R.id.apellidos);
        campoDni = (EditText) view.findViewById(R.id.dni);
        campoNombre_usuario = (EditText) view.findViewById(R.id.nombre_usuario);
        campoContrasenia = (EditText) view.findViewById(R.id.contrasenia);
        imgFoto = (ImageView) view.findViewById(R.id.imagen_usuario);
        es_informatico = (Switch) view.findViewById(R.id.es_informatico);

        btnFoto = (Button) view.findViewById(R.id.boton_foto);
        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoOpciones();
            }
        });

        btnUsuario = (Button) view.findViewById(R.id.boton_agregar_usuario);
        btnUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConexionesSQLiteHelper conn=new ConexionesSQLiteHelper(view.getContext(), "DB_IncidenciasInformaticas", null, 1);
                SQLiteDatabase db = conn.getWritableDatabase();

                //Uri rutaIimgFoto = Uri.parse("android.resource://com.example.alvaro.incidenciasinformticas_lvaroruedajimez/" + imgFoto);



                String insert="INSERT INTO "+ Utilidades.TABLA_USUARIOS+"("+Utilidades.CAMPO_USUARIOS_DNI+","+Utilidades.CAMPO_USUARIOS_NOMBRE+","
                        +Utilidades.CAMPO_USUARIOS_APELLIDOS+","+Utilidades.CAMPO_USUARIOS_NOMBRE_USUARIO+","+Utilidades.CAMPO_USUARIOS_CONTRASENIA+","
                        +Utilidades.CAMPO_USUARIOS_FOTO+","+Utilidades.CAMPO_USUARIOS_ES_INFORMATICO+") VALUES ( '"
                        +campoDni.getText().toString()+"','"+campoNombre.getText().toString()+"','"+campoApellidos.getText().toString()+"','"
                        +campoNombre_usuario.getText().toString()+"','"+campoContrasenia.getText().toString()+"','"+rutaImagen+"','"
                        +es_informatico.isChecked()+"' ) ";

                db.execSQL(insert);
                db.close();

                Toast.makeText(view.getContext(), "Usuario agregado correctamente", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void mostrarDialogoOpciones() {
        builder = new AlertDialog.Builder(  this.getContext());
        builder.setTitle("Elige una opción");

        imagenAntigua = imgFoto.getDrawable();
        rutaImagenAntigua = rutaImagen;

        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
           public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                        abrirCamera();
                        break;
                    case 1:
                        /*Intent intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/");
                        startActivityForResult(intent.createChooser(intent, "Seleccione"), COD_SELECCIONA);*/

                        String directoryPath = Environment.getExternalStorageDirectory() + "/" + CARPETA_IMAGEN;
                        rutaImagen = directoryPath+Long.toHexString(System.currentTimeMillis())+".jpg";
                        File directory = new File(directoryPath);
                        if (!directory.exists()) {
                            directory.mkdirs();
                        }
                        Intent intent=new Intent(Intent.ACTION_GET_CONTENT,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.putExtra( MediaStore.EXTRA_OUTPUT, Uri.fromFile( new File(rutaImagen) ) );
                        intent.setType("image/");
                        startActivityForResult(intent.createChooser(intent,"Seleccione"), COD_SELECCIONA);
                        break;
                    default:
                        dialogInterface.dismiss();
                        break;
                }
            }
        });
        builder.show();
    }

    private void abrirCamera() {
        File miFile=new File(Environment.getExternalStorageDirectory(),DIRECTORIO_IMAGEN);
        boolean isCreada=miFile.exists();

        if (!isCreada) {
            miFile.mkdirs();
            isCreada=miFile.exists();
        }

        if (isCreada) {
            Long consecutivo = System.currentTimeMillis()/1000;
            String nombre = consecutivo.toString()+".jpg";

            rutaImagen = Environment.getExternalStorageDirectory()+File.separator+DIRECTORIO_IMAGEN
                    +File.separator+nombre;

            ficheroImagen = new File(rutaImagen);

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(ficheroImagen));

            startActivityForResult(intent, COD_FOTO);
        }
    }

    public void onActivityResult(int requestCode, int resulCode, Intent data) {
        super.onActivityResult(requestCode, resulCode, data);

        if (resulCode != 0) {
            switch (requestCode) {
                case COD_SELECCIONA:
                    Uri miPath = data.getData();
                    imgFoto.setImageURI(miPath);
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), miPath);
                        bitmap = redimensionarImagen(bitmap, 300, 300);
                        imgFoto.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case COD_FOTO:
                    MediaScannerConnection.scanFile(getContext(), new String[]{rutaImagen}, null, new MediaScannerConnection.OnScanCompletedListener() {
                        @Override
                        public void onScanCompleted(String path, Uri uri) {
                            Log.i("Path", "" + rutaImagen);
                        }
                    });
                    bitmap = BitmapFactory.decodeFile(rutaImagen);
                    bitmap = redimensionarImagen(bitmap, 300, 300);
                    imgFoto.setImageBitmap(bitmap);
                    break;
            }
            //bitmap = redimensionarImagen(bitmap, 600, 800);
        } else if (imgFoto.getDrawable() == imagenAntigua) {
            rutaImagen = rutaImagenAntigua;
        }
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
