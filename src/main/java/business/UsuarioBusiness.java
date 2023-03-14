package business;

import configurations.Conexion;
import model.TipoIngreso;
import model.Usuario;
import scripts.Scripts;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioBusiness {
    Statement st;
    ResultSet r;
    PreparedStatement ps;

    Connection con;
    public Usuario login(String usuario, String password) {
        con = Conexion.conectar();
        try {
            Usuario usuarioResult = new Usuario();
            String query = Scripts.LOGIN;
            ps = con.prepareStatement(query);
            ps.setString(1, usuario);
            ps.setString(2, password);
            r = ps.executeQuery();
            while (r.next()){
                usuarioResult.setCi(r.getString("ci"));
                usuarioResult.setNombre(r.getString("nombre"));
                usuarioResult.setApellido(r.getString("apellido"));
                usuarioResult.setCorreo(r.getString("correo"));
                usuarioResult.setTelefono(r.getString("telefono"));
                usuarioResult.setEstatus(r.getString("estatus"));
                usuarioResult.setPassword(r.getString("password"));
                return usuarioResult;
            }
            return null;
        } catch (SQLException e){
            System.out.println("no hay esos datos");
            return null;
        }
    }

    public boolean create(Usuario usuario) throws SQLException {
        con = Conexion.conectar();
        try {
            String query = Scripts.USUARIO_CREATE;
            ps = con.prepareStatement(query);
            ps.setString(1, usuario.getCi());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getApellido());
            ps.setString(4, usuario.getCorreo());
            ps.setString(5, usuario.getTelefono());
            ps.setString(6, usuario.getEstatus());
            ps.setString(7, usuario.getPassword());
            return ps.execute();
        } catch (SQLException e){
            System.out.println("Error al crear nuevo usuario: "+ e.getMessage());
            return false;
        }
    }


    public List<Usuario> readNoUsar() {
        con = Conexion.conectar();
        try {
            List<Usuario> listaUsuarioResult = new ArrayList<Usuario>();
            String query = Scripts.USUARIO_READ;
            ps = con.prepareStatement(query);
            r = ps.executeQuery();
            while (r.next()){
                Usuario usuario=new Usuario();
                usuario.setCi(r.getString("ci"));
                usuario.setNombre(r.getString("nombre"));
                usuario.setApellido(r.getString("apellido"));
                usuario.setCorreo(r.getString("correo"));
                usuario.setTelefono(r.getString("telefono"));
                usuario.setEstatus(r.getString("estatus"));
                //usuario.setPassword(r.getString("password"));
                listaUsuarioResult.add(usuario);
            }
            return listaUsuarioResult;
        } catch (SQLException e){
            System.out.println("No se pudo recuperar el usuario");
            return null;
        }
    }

    public ResultSet read() {
        con = Conexion.conectar();
        try {
            String query = Scripts.USUARIO_READ;
            ps = con.prepareStatement(query);
            return  ps.executeQuery();
        } catch (SQLException e){
            System.out.println("No se pudo recuperar el usuario");
            return null;
        }
    }


    public boolean update(Usuario usuario) throws SQLException {
        con = Conexion.conectar();
        try {
            String query = Scripts.USUARIO_UPDATE;
            ps = con.prepareStatement(query);
            ps.setString(1, usuario.getCi());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getApellido());
            ps.setString(4, usuario.getCorreo());
            ps.setString(5, usuario.getTelefono());
            ps.setString(6, usuario.getEstatus());
            ps.setString(7, usuario.getCi());
            ps.setString(8, usuario.getCi());
            return ps.execute();
        } catch (SQLException e){
            System.out.println("No se puede actualizar" + e.getMessage());
        }
        return false;
    }


    public boolean delete(Usuario usuario) throws SQLException {
        con = Conexion.conectar();
        try {
            String query = Scripts.USUARIO_DELETE;
            ps = con.prepareStatement(query);
            ps.setString(1, usuario.getCi());
            return ps.execute();
        } catch (SQLException e){
            System.out.println("No se pudo borrar el usuario" + e.getMessage());
        }
        return false;
    }

    public ArrayList<Usuario> getUsuario() {
        con = Conexion.conectar();
        ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
        try {
            st = con.createStatement();
            r = st.executeQuery("select * from usuario");
            while(r.next()){
                Usuario usuario = new Usuario();
                usuario.setUsuario_id(r.getInt("usuario_id"));
                usuario.setCi(r.getString("ci"));
                usuario.setNombre(r.getString("nombre"));
                usuario.setApellido(r.getString("apellido"));
                usuario.setCorreo(r.getString("correo"));
                usuario.setTelefono(r.getString("telefono"));
                usuario.setEstatus(r.getString("estatus"));
                usuario.setPassword(r.getString("password"));
                listaUsuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("No se pudo recuperar el usuario");
        }
        return listaUsuarios;
    }

    public ArrayList<TipoIngreso>getTipoIngreso(){
        con = Conexion.conectar();
        ArrayList<TipoIngreso>listaTipoIngreso = new ArrayList<TipoIngreso>();
        try {
            st = con.createStatement();
            r = st.executeQuery("select * from tipoingreso");
            while (r.next()){
                TipoIngreso tipoIngreso = new TipoIngreso();
                tipoIngreso.setTipoIngreso_id(r.getInt("tipoIngreso_id"));
                tipoIngreso.setDescripcion(r.getString("descripcion"));
                listaTipoIngreso.add(tipoIngreso);
            }
        } catch (SQLException e) {
            System.out.println("no hay ese tipo");
            //throw new RuntimeException(e);
        }
        return listaTipoIngreso;
   }


}
