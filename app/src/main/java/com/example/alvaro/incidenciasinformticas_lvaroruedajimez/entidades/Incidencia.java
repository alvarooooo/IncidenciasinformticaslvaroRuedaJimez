package com.example.alvaro.incidenciasinformticas_lvaroruedajimez.entidades;

import java.util.Date;

public class Incidencia {

    private String DNI;
    private String fecha_incidencia;
    private String observaciones;
    private String DNI_informatico;
    private String estado_incidencia;
    private String fecha_resolucion_incidencia;
    private String observaciones_informatico;

    public Incidencia(String DNI, String fecha_incidencia, String observaciones, String DNI_informatico, String estado_incidencia, String fecha_resolucion_incidencia, String observaciones_informatico) {
        this.DNI = DNI;
        this.fecha_incidencia = fecha_incidencia;
        this.observaciones = observaciones;
        this.DNI_informatico = DNI_informatico;
        this.estado_incidencia = estado_incidencia;
        this.fecha_resolucion_incidencia = fecha_resolucion_incidencia;
        this.observaciones_informatico = observaciones_informatico;
    }

    public String getDni() {
        return DNI;
    }

    public void setDni(String DNI) {
        this.DNI = DNI;
    }

    public String getFecha_incidencia() {
        return fecha_incidencia;
    }

    public void setFecha_incidencia(String fecha_incidencia) {
        this.fecha_incidencia = fecha_incidencia;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getDni_informatico() {
        return DNI_informatico;
    }

    public void setDni_informatico(String DNI_informatico) {
        this.DNI_informatico = DNI_informatico;
    }

    public String getEstado_incidencia() {
        return estado_incidencia;
    }

    public void setEstado_incidencia(String estado_incidencia) {
        this.estado_incidencia = estado_incidencia;
    }

    public String getFecha_resolucion_incidencia() {
        return fecha_resolucion_incidencia;
    }

    public void setFecha_resolucion_incidencia(String fecha_resolucion_incidencia) {
        this.fecha_resolucion_incidencia = fecha_resolucion_incidencia;
    }

    public String getObservaciones_informatico() {
        return observaciones_informatico;
    }

    public void setObservaciones_informatico(String observaciones_informatico) {
        this.observaciones_informatico = observaciones_informatico;
    }
}