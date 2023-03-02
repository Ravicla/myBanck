package business;

import configurations.Conexion;
import model.Aporte;
import model.Usuario;
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
            ps.setDouble(2, (int) aporte.getValor_aporte());
            ps.setInt(3, aporte.getUsuario());
            ps.setInt(4, aporte.getTipoIngreso());
            return ps.execute();
        }catch (SQLException e){
            System.out.println("Error en insertar: "+ e.getMessage());
            return false;
        }
    }



}



