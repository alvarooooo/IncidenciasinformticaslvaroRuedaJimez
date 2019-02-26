package com.example.alvaro.incidenciasinformticas_lvaroruedajimez.entidades;



public class Usuario {

    private String DNI;
    private String nombre;
    private String apellidos;
    private String nombre_usuario;
    private String contrasenia;
    private String foto;
    private boolean esInformatico;

    public Usuario(String DNI, String nombre, String apellidos, String nombre_usuario, String contrasenia, String foto, boolean esInformatico) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nombre_usuario = nombre_usuario;
        this.contrasenia = contrasenia;
        this.foto = foto;
        this.esInformatico = esInformatico;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean isEsInformatico() {
        return esInformatico;
    }

    public void setEsInformatico(boolean esInformatico) {
        this.esInformatico = esInformatico;
    }
}
