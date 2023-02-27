package model;

import java.util.Date;

public class Rifa {
    int rifa_id;
    Date fecha_rifa;
    int valor_rifa;
    Usuario usuario;

    public Rifa(Date fecha_rifa, int valor_rifa) {
        this.fecha_rifa = fecha_rifa;
        this.valor_rifa = valor_rifa;
    }

    public int getRifa_id() {
        return rifa_id;
    }

    public void setRifa_id(int rifa_id) {
        this.rifa_id = rifa_id;
    }

    public Date getFecha_rifa() {
        return fecha_rifa;
    }

    public void setFecha_rifa(Date fecha_rifa) {
        this.fecha_rifa = fecha_rifa;
    }

    public int getValor_rifa() {
        return valor_rifa;
    }

    public void setValor_rifa(int valor_rifa) {
        this.valor_rifa = valor_rifa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
