package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Aporte {
    int aporte_id;
    Date fecha_aporte;
    double valor_aporte;
    int tipoIngreso;
    int usuario;
    SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");

    public Aporte() {
        this.fecha_aporte = fecha_aporte;
        this.valor_aporte = valor_aporte;
    }

    public int getAporte_id() {
        return aporte_id;
    }

    public void setAporte_id(int aporte_id) {
        this.aporte_id = aporte_id;
    }

    public Date getFecha_aporte(String DATE) {
        return fecha_aporte;
    }
    public Date getFecha_aporte() {
        return fecha_aporte;
    }

    public void setFecha_aporte(Date fecha_aporte) {
        this.fecha_aporte = fecha_aporte;
    }

    public double getValor_aporte() {
        return valor_aporte;
    }

    public void setValor_aporte(double valor_aporte) {
        this.valor_aporte = valor_aporte;
    }

    public int getTipoIngreso() {
        return tipoIngreso;
    }

    public void setTipoIngreso(int tipoIngreso) {
        this.tipoIngreso = tipoIngreso;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }
}
