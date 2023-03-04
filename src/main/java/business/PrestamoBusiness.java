package business;

import configurations.Conexion;
import model.Cuota;
import model.Prestamo;
import scripts.Scripts;
import view.Prestamos;

import java.sql.*;
import java.text.SimpleDateFormat;

public class PrestamoBusiness {
    Statement st;
    ResultSet r;
    PreparedStatement ps;
    Connection con;

    public boolean createPrestamo(Prestamo prestamo) throws SQLException{
        con = Conexion.conectar();
        try {
            String query = Scripts.PRESTAMO_CREATE;
            ps = con.prepareStatement(query);
            SimpleDateFormat forma=new SimpleDateFormat("yyyy-MM-dd");
            String fecha=forma.format(prestamo.getFecha_prestamo());

            ps.setDate(1, Date.valueOf(fecha));
            ps.setInt(2, prestamo.getInteres());
            ps.setDouble(3, prestamo.getValor_prestamo());
            ps.setInt(4, prestamo.getTiempo_prestamo());
            ps.setInt(5, prestamo.getUsuario().getUsuario_id());
            return ps.execute();
        }catch (SQLException e) {
            System.out.println("Error al crear un prestamo" + e.getMessage());
            return false;
        }
    }


    public boolean createCuota (Cuota cuota) throws SQLException {
        con =Conexion.conectar();
        try {
            String query = Scripts.CUOTA_CREATE;
            ps = con.prepareStatement(query);
            SimpleDateFormat forma=new SimpleDateFormat("yyyy-MM-dd");
            String fecha=forma.format(cuota.getFecha_cuota());

            ps.setDate(1, Date.valueOf(fecha));
            ps.setDouble(2, cuota.getValor_capital());
            ps.setBoolean(3, cuota.isPago_capital());
            ps.setDouble(4, cuota.getValor_interes());
            ps.setBoolean(5, cuota.isPago_interes());
            ps.setDouble(6, cuota.getAbono_capital());
            ps.setDouble(6, cuota.getAbono_interes());
            return ps.execute();

        }catch (SQLException e) {
            System.out.println("Error al crear una cuota" + e.getMessage());
            return false;
        }
    }


    public ResultSet read(){
        con = Conexion.conectar();
        try {
            String query = Scripts.PRESTAMO_READ;
            ps = con.prepareStatement(query);
            return ps.executeQuery();
        }catch (SQLException e){
            System.out.println("No se pudo recuperar el prestamo");
            return null;
        }
    }

    public boolean updatePrestamo(Prestamo prestamo) throws SQLException{
        con = Conexion.conectar();
        try {
            String query =Scripts.PRESTAMO_UPDATE;
            ps = con.prepareStatement(query);
            ps.setDate(1, java.sql.Date.valueOf("2013-09-04"));
            ps.setInt(2, prestamo.getInteres());
            ps.setDouble(3, prestamo.getValor_prestamo());
            ps.setInt(4, prestamo.getTiempo_prestamo());
            ps.setInt(5, prestamo.getUsuario().getUsuario_id());
            return ps.execute();
        }catch (SQLException e){
            System.out.println("No se pudo actualizar el prestamo" + e.getMessage());
        }
        return false;
    }

    public boolean updateCuota(Cuota cuota) throws SQLException{
        con = Conexion.conectar();
        try {
            String query = Scripts.CUOTA_UPDATE;
            ps = con.prepareStatement(query);
            ps.setDate(1, java.sql.Date.valueOf("2013-09-04"));
            ps.setDouble(2, cuota.getValor_capital());
            ps.setBoolean(3, cuota.isPago_capital());
            ps.setDouble(4, cuota.getValor_interes());
            ps.setBoolean(5, cuota.isPago_interes());
            ps.setDouble(6, cuota.getAbono_capital());
            ps.setDouble(6, cuota.getAbono_interes());
            return ps.execute();
        } catch (SQLException e){
            System.out.println("No se puede actualizar" + e.getMessage());
        }
        return false;
    }

    public boolean deletePrestamo(Prestamo prestamo) throws SQLException {
        con = Conexion.conectar();
        try {
            String query = Scripts.PRESTAMO_DELETE;
            ps = con.prepareStatement(query);
            ps.setInt(1, prestamo.getPrestamo_id());
            return  ps.execute();
        } catch (SQLException e){
            System.out.println("No se pudo borrar el prestamo" + e.getMessage());
        }
        return false;
    }

    public boolean deleteCuota(Cuota cuota) throws SQLException {
        con = Conexion.conectar();
        try {
            String query = Scripts.CUOTA_DELETE;
            ps = con.prepareStatement(query);
            ps.setInt(1, cuota.getCuota_id());
            return  ps.execute();
        } catch (SQLException e){
            System.out.println("No se pudo borrar la cuota" + e.getMessage());
        }
        return false;
    }



}
