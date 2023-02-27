package model;

import java.util.Date;

public class Ahorro {
    int ahorro_id;
    Date fecha_ahorro;
    int valor_ahorro;
    Usuario usuario;

    public Ahorro(Date fecha_ahorro, int valor_ahorro, Usuario usuario) {
        this.fecha_ahorro = fecha_ahorro;
        this.valor_ahorro = valor_ahorro;
        this.usuario = usuario;
    }

    public int getAhorro_id() {
        return ahorro_id;
    }

    public void setAhorro_id(int ahorro_id) {
        this.ahorro_id = ahorro_id;
    }

    public Date getFecha_ahorro() {
        return fecha_ahorro;
    }

    public void setFecha_ahorro(Date fecha_ahorro) {
        this.fecha_ahorro = fecha_ahorro;
    }

    public int getValor_ahorro() {
        return valor_ahorro;
    }

    public void setValor_ahorro(int valor_ahorro) {
        this.valor_ahorro = valor_ahorro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
