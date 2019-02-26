package com.example.alvaro.incidenciasinformticas_lvaroruedajimez.Utilidades;

public class Utilidades {

    //Campos de la tabla usiarios
    public static String TABLA_USUARIOS="usuarios";
    public static String CAMPO_USUARIOS_DNI="dni";
    public static String CAMPO_USUARIOS_NOMBRE="nombre";
    public static String CAMPO_USUARIOS_APELLIDOS="apellidos";
    public static String CAMPO_USUARIOS_NOMBRE_USUARIO="nombre_usuario";
    public static String CAMPO_USUARIOS_CONTRASENIA="contrasenya";
    public static String CAMPO_USUARIOS_FOTO="foto";
    public static String CAMPO_USUARIOS_ES_INFORMATICO="es_informatico";

    public static final String CREAR_TABLA_USUARIOS="CREATE TABLE " + TABLA_USUARIOS + " ("
            + CAMPO_USUARIOS_DNI+" TEXT, "
            + CAMPO_USUARIOS_NOMBRE + " TEXT, "
            + CAMPO_USUARIOS_APELLIDOS + " TEXT, "
            + CAMPO_USUARIOS_NOMBRE_USUARIO + " TEXT, "
            + CAMPO_USUARIOS_CONTRASENIA + " TEXT, "
            + CAMPO_USUARIOS_FOTO + " TEXT, "
            + CAMPO_USUARIOS_ES_INFORMATICO + " BOOLEAN)";

    //Campos de la tabla usiarios
    public static String TABLA_INCIDENCIAS="incidencias";
    public static String CAMPO_INCIDENCIAS_DNI="dni";
    public static String CAMPO_INCIDENCIAS_FECHA_INCIDENCIA="fecha_incidencia";
    public static String CAMPO_INCIDENCIAS_OBSERVACIONES="observaciones";
    public static String CAMPO_INCIDENCIAS_DNI_INFORMATICO="dni_informatico";
    public static String CAMPO_INCIDENCIAS_ESTADO_INCIDENCIAS="estado_incidencia";
    public static String CAMPO_INCIDENCIAS_FECHA_RESOLUCION_INCIDENCIA="fecha_resolucion_incidencia";
    public static String CAMPO_INCIDENCIAS_OBSERVACIONES_INFORMATICO="observaciones_informatico";

    public static final String CREAR_TABLA_INCIDENDIAS="CREATE TABLE " + TABLA_INCIDENCIAS + " ("
            + CAMPO_INCIDENCIAS_DNI + " TEXT, "
            + CAMPO_INCIDENCIAS_FECHA_INCIDENCIA + " TEXT, "
            + CAMPO_INCIDENCIAS_OBSERVACIONES + " TEXT, "
            + CAMPO_INCIDENCIAS_DNI_INFORMATICO + " TEXT, "
            + CAMPO_INCIDENCIAS_ESTADO_INCIDENCIAS + " TEXT, "
            + CAMPO_INCIDENCIAS_FECHA_RESOLUCION_INCIDENCIA + " TEXT, "
            + CAMPO_INCIDENCIAS_OBSERVACIONES_INFORMATICO + " TEXT)";
}
