package model;

public class Cuota {
    int cuota_id;
    String fecha_cuota;
    double valor_capital;
    boolean pago_capital;
    double valor_interes;
    boolean pago_interes;
    double abono_capital;
    double abono_interes;
    int prestamo;

    public Cuota(double valor_capital, double valor_interes) {
        this.valor_capital = valor_capital;
        this.valor_interes = valor_interes;
    }
    public Cuota(){

    }

    public int getCuota_id() {
        return cuota_id;
    }

    public void setCuota_id(int cuota_id) {
        this.cuota_id = cuota_id;
    }

    public String getFecha_cuota() {
        return fecha_cuota;
    }

    public void setFecha_cuota(String fecha_cuota) {
        this.fecha_cuota = fecha_cuota;
    }

    public double getValor_capital() {
        return valor_capital;
    }

    public void setValor_capital(double valor_capital) {
        this.valor_capital = valor_capital;
    }

    public boolean isPago_capital() {
        return pago_capital;
    }

    public void setPago_capital(boolean pago_capital) {
        this.pago_capital = pago_capital;
    }

    public double getValor_interes() {
        return valor_interes;
    }

    public void setValor_interes(double valor_interes) {
        this.valor_interes = valor_interes;
    }

    public boolean isPago_interes() {
        return pago_interes;
    }

    public void setPago_interes(boolean pago_interes) {
        this.pago_interes = pago_interes;
    }

    public double getAbono_capital() {
        return abono_capital;
    }

    public void setAbono_capital(double abono_capital) {
        this.abono_capital = abono_capital;
    }

    public double getAbono_interes() {
        return abono_interes;
    }

    public void setAbono_interes(double abono_interes) {
        this.abono_interes = abono_interes;
    }

    public int getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(int prestamo) {
        this.prestamo = prestamo;
    }

}




