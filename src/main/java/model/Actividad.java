package model;

import java.util.Date;

public class Actividad {
    int actividad_id;
    Date fecha_actividad;
    int valor_actividad;
    Usuario usuario;

    public Actividad(Date fecha_actividad, int valor_actividad, Usuario usuario) {
        this.fecha_actividad = fecha_actividad;
        this.valor_actividad = valor_actividad;
        this.usuario = usuario;
    }

    public int getActividad_id() {
        return actividad_id;
    }

    public void setActividad_id(int actividad_id) {
        this.actividad_id = actividad_id;
    }

    public Date getFecha_actividad() {
        return fecha_actividad;
    }

    public void setFecha_actividad(Date fecha_actividad) {
        this.fecha_actividad = fecha_actividad;
    }

    public int getValor_actividad() {
        return valor_actividad;
    }

    public void setValor_actividad(int valor_actividad) {
        this.valor_actividad = valor_actividad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
