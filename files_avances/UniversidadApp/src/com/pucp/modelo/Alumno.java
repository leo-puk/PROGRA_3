package com.pucp.modelo;

import java.sql.Date;

public class Alumno {
    private int id;
    private String nombre;
    private Date fechaNacimiento;
    private double craest;
    private boolean activo;
    private TIPO_ALUMNO tipoAlumno;

    public Alumno() {
    }

    public Alumno(int id, String nombre, Date fechaNacimiento, double craest, boolean activo, TIPO_ALUMNO tipoAlumno) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.craest = craest;
        this.activo = activo;
        this.tipoAlumno = tipoAlumno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public double getCraest() {
        return craest;
    }

    public void setCraest(double craest) {
        this.craest = craest;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public TIPO_ALUMNO getTipoAlumno() {
        return tipoAlumno;
    }

    public void setTipoAlumno(TIPO_ALUMNO tipoAlumno) {
        this.tipoAlumno = tipoAlumno;
    }

    @Override
    public String toString() {
        return "Alumno{" + "id=" + id + ", nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento + ", craest=" + craest + ", activo=" + activo + ", tipoAlumno=" + tipoAlumno + '}';
    }   
    
}
