package business;

import configurations.Conexion;
import model.Aporte;
import scripts.Scripts;

import java.sql.*;
import java.text.SimpleDateFormat;

public class AporteBusiness {

    Statement st;
    ResultSet r;
    PreparedStatement ps;

    Connection con;

    public boolean create(Aporte aporte)throws SQLException {
        con = Conexion.conectar();
        try {
            String query = Scripts.APORTE_CREATE;
            ps = con.prepareStatement(query);
            SimpleDateFormat forma=new SimpleDateFormat("yyyy-MM-dd");
            String fecha=forma.format(aporte.getFecha_aporte());

            ps.setDate(1, Date.valueOf(fecha));
            ps.setDouble(2, aporte.getValor_aporte());
            ps.setInt(3, aporte.getUsuario());
            ps.setInt(4, aporte.getTipoIngreso());
            return ps.execute();
        }catch (SQLException e){
            System.out.println("Error en guardar el aporte: "+ e.getMessage());
            return false;
        }
    }

    public ResultSet read() {
        con = Conexion.conectar();
        try {
            String query = Scripts.APORTE_READ;
            ps = con.prepareStatement(query);
            return  ps.executeQuery();
        } catch (SQLException e){
            System.out.println("No se pudo recuperar el aporte");
            return null;
        }
    }



    public boolean delete(Aporte aporte) throws SQLException {
        con = Conexion.conectar();
        try {
            String query = Scripts.APORTE_DELETE;
            ps = con.prepareStatement(query);
            ps.setInt(1, aporte.getAporte_id());
            return ps.execute();
        } catch (SQLException e){
            System.out.println("No se pudo borrar el usuario" + e.getMessage());
        }
        return false;
    }


    public boolean update(Aporte aporte) throws SQLException {
        con = Conexion.conectar();
        try {
            SimpleDateFormat forma=new SimpleDateFormat("yyyy-MM-dd");
            String fecha=forma.format(aporte.getFecha_aporte());

            String query = Scripts.APORTE_UPDATE;
            ps = con.prepareStatement(query);
            ps.setDate(1, java.sql.Date.valueOf(fecha));
            ps.setDouble(2, aporte.getValor_aporte());
            ps.setInt(3, aporte.getTipoIngreso());
            ps.setInt(4, aporte.getAporte_id());

            return ps.execute();
        } catch (SQLException e){
            System.out.println("No se puede actualizar" + e.getMessage());
        }
        return false;
    }
}



