package business;

import configurations.Conexion;
import model.Cuota;
import model.Prestamo;
import model.Simulacion;
import model.SimulacionCuota;
import scripts.Scripts;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
            SimpleDateFormat forma = new SimpleDateFormat("yyyy-MM-dd");
            String fecha = forma.format(prestamo.getFecha_prestamo());

            ps.setDate(1, Date.valueOf(fecha));
            ps.setInt(2, prestamo.getInteres());
            ps.setDouble(3, prestamo.getValor_prestamo());
            ps.setInt(4, prestamo.getTiempo_prestamo());
            ps.setInt(5, prestamo.getUsuario());
            return ps.execute();
        }catch (SQLException e) {
            System.out.println("Error al crear un prestamo" + e.getMessage());
            return false;
        }
    }

    public boolean createCuota (Cuota cuota) {
        con = Conexion.conectar();
        try {
            String query = Scripts.CUOTA_CREATE;
            ps = con.prepareStatement(query);

            String nuevaFechaFormateada = cuota.getFecha_cuota().replace('/', '-');
            java.util.Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(nuevaFechaFormateada);
            java.sql.Date sqlDate = new java.sql.Date(date1.getTime());

            ps.setDate(1, Date.valueOf(sqlDate.toLocalDate()));
            ps.setDouble(2, cuota.getValor_capital());
            ps.setBoolean(3, cuota.isPago_capital());
            ps.setDouble(4, cuota.getValor_interes());
            ps.setBoolean(5, cuota.isPago_interes());
            ps.setDouble(6, cuota.getAbono_capital());
            ps.setDouble(7, cuota.getAbono_interes());
            ps.setInt(8, cuota.getPrestamo());
            return ps.execute();

        } catch (SQLException e) {
            System.out.println("Error al crear una cuota" + e.getMessage());
            return false;
        } catch (ParseException e) {
            throw new RuntimeException(e);
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
            SimpleDateFormat forma = new SimpleDateFormat("yyyy-MM-dd");
            String fechaPrestamo = forma.format(prestamo.getFecha_prestamo());

            String query =Scripts.PRESTAMO_UPDATE;
            ps = con.prepareStatement(query);
            ps.setDate(1, java.sql.Date.valueOf(fechaPrestamo));
            ps.setInt(2, prestamo.getInteres());
            ps.setDouble(3, prestamo.getValor_prestamo());
            ps.setInt(4, prestamo.getTiempo_prestamo());
            ps.setInt(5, prestamo.getUsuario());
            return ps.execute();
        }catch (SQLException e){
            System.out.println("No se pudo actualizar el prestamo" + e.getMessage());
        }
        return false;
    }

    /*public boolean updateCuota(Cuota cuota) throws SQLException{
        con = Conexion.conectar();
        try {
            SimpleDateFormat forma = new SimpleDateFormat("yyyy-MM-dd");
            String fechaCuota = forma.format(cuota.getFecha_cuota());

            String query = Scripts.CUOTA_UPDATE;
            ps = con.prepareStatement(query);
            ps.setDate(1, java.sql.Date.valueOf(fechaCuota));
            ps.setDouble(2, cuota.getValor_capital());
            ps.setBoolean(3, cuota.isPago_capital());
            ps.setDouble(4, cuota.getValor_interes());
            ps.setBoolean(5, cuota.isPago_interes());
            ps.setDouble(6, cuota.getAbono_capital());
            ps.setDouble(7, cuota.getAbono_interes());
            return ps.execute();
        } catch (SQLException e){
            System.out.println("No se puede actualizar" + e.getMessage());
        }
        return false;
    }*/

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

    public List<SimulacionCuota> calcularSimulacion(Simulacion simulacion){
        double capitalporinteres;
        double valorTotal;
        double tcuota;
        double icuota;
        double ccuota;
        double interes = 0;
        //(Math.ceil(a+b))
        if (simulacion.getValor() <= 100 && simulacion.getTiempo() <= 3){
            interes = 5;
        }
        if ((simulacion.getValor() > 100 && simulacion.getValor() <= 300) && simulacion.getTiempo() <= 3){
            interes = 6;
        }
        if ((simulacion.getValor() > 100 && simulacion.getValor() <= 300) && simulacion.getTiempo() > 3){
            interes = 7;
        }
        if (simulacion.getValor() > 300 && simulacion.getTiempo() <= 3){
            interes = 8;
        }
        if (simulacion.getValor() > 300 && simulacion.getTiempo() > 3){
            interes = 9;
        }
        capitalporinteres = Math.round(interes * simulacion.getValor() / 100.0 * 100.0) / 100.0;
        valorTotal = Math.round((capitalporinteres + simulacion.getValor()) * 100.0) / 100.0;
        tcuota = Math.round(valorTotal / simulacion.getTiempo() * 100.0) / 100.0;
        icuota = Math.round(capitalporinteres / simulacion.getTiempo() * 100.0) / 100.0;
        ccuota = Math.round(simulacion.getValor() / simulacion.getTiempo() * 100.0) / 100.0;
        System.out.println(interes);


        LocalDate fechaInicial = simulacion.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int duracionPrestamo = simulacion.getTiempo();

        List<String> fechas = new ArrayList<>();
        for (int i = 0; i < duracionPrestamo; i++) {
            LocalDate fechaFinal = fechaInicial.plusMonths(1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            //System.out.println(fechaInicial.format(formatter));
            //System.out.println(fechaFinal.format(formatter));
            fechas.add(fechaFinal.format(formatter));
            fechaInicial = fechaFinal;
        }

        List<SimulacionCuota> lista = new ArrayList<>();

        int contador = 1;
        for (String fecha : fechas) {
            SimulacionCuota simulacionCuota = new SimulacionCuota();
            simulacionCuota.setDescripcion("cuota " + contador);
            simulacionCuota.setCuotaCapital(ccuota);
            simulacionCuota.setInteresCapital(icuota);
            simulacionCuota.setTotalCuota(tcuota);
            simulacionCuota.setFecha(fecha);
            simulacionCuota.setInteres((int) interes);

            lista.add(simulacionCuota);
            contador++;
        }
        return lista;
    }

    public int obtenerIdPrestamo(Prestamo prestamo) {
        con = Conexion.conectar();
        try {
            String sql = "SELECT prestamo_id FROM prestamo WHERE usuario_id = ? AND valor_prestamo = ? AND fecha_prestamo = ?";
            ps = con.prepareStatement(sql);
            SimpleDateFormat forma = new SimpleDateFormat("yyyy-MM-dd");
            String fecha=forma.format(prestamo.getFecha_prestamo());

            ps.setInt(1, prestamo.getUsuario());
            ps.setDouble(2, prestamo.getValor_prestamo());
            ps.setDate(3, Date.valueOf(fecha));

            r = ps.executeQuery();
            if (r.next()) {
                return r.getInt("prestamo_id");
            }
        } catch (SQLException e) {
            System.out.println("No se pudo recuperar el prestamo");
        }
        return 0;
    }

    public List<Prestamo> obtenerPrestamoByUsuarioId(int usuarioId) {
        con = Conexion.conectar();
        List<Prestamo> prestamos = new ArrayList<>();
        try {
            String sql = "SELECT prestamo_id, fecha_prestamo, interes, valor_prestamo, tiempo_prestamo FROM prestamo WHERE usuario_id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, usuarioId);
            r = ps.executeQuery();
            while (r.next()) {
                Prestamo prestamo = new Prestamo();
                prestamo.setPrestamo_id(r.getInt("prestamo_id"));
                prestamo.setFecha_prestamo(r.getDate("fecha_prestamo"));
                prestamo.setInteres((int) r.getDouble("interes"));
                prestamo.setValor_prestamo(r.getDouble("valor_prestamo"));
                prestamo.setTiempo_prestamo(r.getInt("tiempo_prestamo"));
                prestamos.add(prestamo);
            }
        } catch (SQLException e) {
            System.out.println("No se pudo recuperar el prestamo");
        }
        return prestamos;
    }


    public List<Cuota> obtenerCuotaByIdPrestamo(int prestamoId){
        con = Conexion.conectar();
        List<Cuota> cuotas = new ArrayList<>();
        try {
            String sql = "select cuota_id, fecha_cuota, valor_capital, pago_capital, valor_interes, pago_interes, abono_capital, abono_interes from cuota where prestamo_id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, prestamoId);
            r = ps.executeQuery();
            while (r.next()){
                Cuota cuota = new Cuota();
                cuota.setCuota_id(r.getInt("cuota_id"));
                cuota.setFecha_cuota(r.getString("fecha_cuota"));
                cuota.setValor_capital(r.getDouble("valor_capital"));
                cuota.setPago_capital(r.getBoolean("pago_capital"));
                cuota.setValor_interes(r.getDouble("valor_interes"));
                cuota.setPago_interes(r.getBoolean("pago_interes"));
                cuota.setAbono_capital(r.getDouble("abono_capital"));
                cuota.setAbono_interes(r.getDouble("abono_interes"));
                cuotas.add(cuota);
            }
        }catch (SQLException e) {
            System.out.println("No se pudo recuperar la cuota");
        }
        return cuotas;
    }

    public boolean updateCuotaByIdCuota(Cuota cuota){
        con = Conexion.conectar();
        try {
            String sql = "UPDATE cuota SET pago_capital = ?, pago_interes = ?, abono_capital = ?, abono_interes = ? WHERE cuota_id = ?";
            ps = con.prepareStatement(sql);

            ps.setBoolean(1, cuota.isPago_capital());
            ps.setBoolean(2, cuota.isPago_interes());
            ps.setDouble(3, cuota.getAbono_capital());
            ps.setDouble(4, cuota.getAbono_interes());
            ps.setInt(5, cuota.getCuota_id());

            return ps.execute();
        } catch (SQLException e) {
            System.out.println("No se pudo actualizar la cuota" + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateAbonoByIdCuota(Cuota cuota){
        con = Conexion.conectar();
        try {
            String sql = "update cuota set abono_capital = ?, abono_interes = ? where cuota_id = ?";
            ps = con.prepareStatement(sql);

            ps.setDouble(1, cuota.getAbono_capital());
            ps.setDouble(2, cuota.getAbono_interes());
            ps.setInt(3, cuota.getCuota_id());
            return ps.execute();
        } catch (SQLException e) {
            System.out.println("No se pudo actualizar el abono" + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}

















