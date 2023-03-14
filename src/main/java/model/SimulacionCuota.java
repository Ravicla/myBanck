package model;

import java.util.Date;

public class SimulacionCuota {
    String descripcion;
    double cuotaCapital;
    double interesCapital;
    double totalCuota;
    String fecha;
    int interes;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCuotaCapital() {
        return cuotaCapital;
    }

    public void setCuotaCapital(double cuotaCapital) {
        this.cuotaCapital = cuotaCapital;
    }

    public double getInteresCapital() {
        return interesCapital;
    }

    public void setInteresCapital(double interesCapital) {
        this.interesCapital = interesCapital;
    }

    public double getTotalCuota() {
        return totalCuota;
    }

    public void setTotalCuota(double totalCuota) {
        this.totalCuota = totalCuota;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getInteres() {
        return interes;
    }

    public void setInteres(int interes) {
        this.interes = interes;
    }
}
