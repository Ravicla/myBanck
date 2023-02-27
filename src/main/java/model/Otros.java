package model;

public class Otros {
    int otros_id;
    double cantidad_recaudada;
    int id_generio;
    TipoIngreso tipoIngreso;

    public Otros(double cantidad_recaudada, int id_generio, TipoIngreso tipoIngreso) {
        this.cantidad_recaudada = cantidad_recaudada;
        this.id_generio = id_generio;
        this.tipoIngreso = tipoIngreso;
    }

    public int getOtros_id() {
        return otros_id;
    }

    public void setOtros_id(int otros_id) {
        this.otros_id = otros_id;
    }

    public double getCantidad_recaudada() {
        return cantidad_recaudada;
    }

    public void setCantidad_recaudada(double cantidad_recaudada) {
        this.cantidad_recaudada = cantidad_recaudada;
    }

    public int getId_generio() {
        return id_generio;
    }

    public void setId_generio(int id_generio) {
        this.id_generio = id_generio;
    }

    public TipoIngreso getTipoIngreso() {
        return tipoIngreso;
    }

    public void setTipoIngreso(TipoIngreso tipoIngreso) {
        this.tipoIngreso = tipoIngreso;
    }
}
