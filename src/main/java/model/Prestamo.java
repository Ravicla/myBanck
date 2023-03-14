package model;

import java.util.Date;
import java.util.UUID;

public class Prestamo {
    int prestamo_id;
    Date fecha_prestamo;
    int interes;
    double valor_prestamo;
    int tiempo_prestamo;
    int usuario;

    public Prestamo() {
        this.fecha_prestamo = fecha_prestamo;
        this.valor_prestamo = valor_prestamo;

    }



    public int getPrestamo_id() {
        return prestamo_id;
    }

    public void setPrestamo_id(int prestamo_id) {
        this.prestamo_id = prestamo_id;
    }

    public Date getFecha_prestamo() {
        return fecha_prestamo;
    }

    public void setFecha_prestamo(Date fecha_prestamo) {
        this.fecha_prestamo = fecha_prestamo;
    }

    public int getInteres() {
        return interes;
    }

    public void setInteres(int interes) {
        this.interes = interes;
    }

    public double getValor_prestamo() {
        return valor_prestamo;
    }

    public void setValor_prestamo(double valor_prestamo) {
        this.valor_prestamo = valor_prestamo;
    }

    public int getTiempo_prestamo() {
        return tiempo_prestamo;
    }

    public void setTiempo_prestamo(int tiempo_prestamo) {
        this.tiempo_prestamo = tiempo_prestamo;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }
}
