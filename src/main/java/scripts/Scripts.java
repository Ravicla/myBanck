package scripts;

public class Scripts {
    public static final String LOGIN = "select * from usuario where ci = ? and password = ?";

    public static final String USUARIO_CREATE = "insert into usuario (ci, nombre, apellido, correo, telefono, estatus, password)values(?,?,?,?,?,?,?)";

    public static final String USUARIO_READ = "select ROW_NUMBER() OVER (ORDER BY apellido, nombre) AS '#', ci AS IDETIFICACION, nombre AS NOMBRE, apellido AS APELLIDO, correo AS CORREO, telefono AS TELEFONO, estatus AS ESTATUS\n" +
            "from usuario;";

    public static final String USUARIO_UPDATE = "update usuario set ci = ?, nombre = ?, apellido = ?, correo = ?, telefono = ?, estatus = ?, password = ? where ci = ?";

    public static final String USUARIO_DELETE = "delete from usuario where ci = ?";



}
