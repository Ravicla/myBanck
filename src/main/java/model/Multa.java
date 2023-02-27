package model;

import java.util.Date;

public class Multa {
    int multa_id;
    Date fecha_multa;
    int valor_multa;
    Usuario usuario;

    public Multa(Date fecha_multa, int valor_multa, Usuario usuario) {
        this.fecha_multa = fecha_multa;
        this.valor_multa = valor_multa;
        this.usuario = usuario;
    }

    public int getMulta_id() {
        return multa_id;
    }

    public void setMulta_id(int multa_id) {
        this.multa_id = multa_id;
    }

    public Date getFecha_multa() {
        return fecha_multa;
    }

    public void setFecha_multa(Date fecha_multa) {
        this.fecha_multa = fecha_multa;
    }

    public int getValor_multa() {
        return valor_multa;
    }

    public void setValor_multa(int valor_multa) {
        this.valor_multa = valor_multa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
