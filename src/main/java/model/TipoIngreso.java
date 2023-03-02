package model;

public class TipoIngreso {
    int tipoIngreso_id;
    String descripcion;
    Usuario usuario;

 public TipoIngreso(){

 }

    public String getDescripcion() {
        return descripcion;
    }


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTipoIngreso_id() {
        return tipoIngreso_id;
    }

    public void setTipoIngreso_id(int tipoIngreso_id) {
        this.tipoIngreso_id = tipoIngreso_id;
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


}
