package scripts;

public class Scripts {
    //USUARIOS
    public static final String LOGIN = "select * from usuario where ci = ? and password = ?";
    public static final String USUARIO_CREATE = "insert into usuario (ci, nombre, apellido, correo, telefono, estatus, password)values(?,?,?,?,?,?,?)";
    public static final String USUARIO_READ = "select ROW_NUMBER() OVER (ORDER BY apellido, nombre) AS '#', ci AS IDETIFICACION, nombre AS NOMBRE, apellido AS APELLIDO, correo AS CORREO, telefono AS TELEFONO, estatus AS ESTATUS\n" + "from usuario;";
    public static final String USUARIO_UPDATE = "update usuario set ci = ?, nombre = ?, apellido = ?, correo = ?, telefono = ?, estatus = ?, password = ? where ci = ?";
    public static final String USUARIO_DELETE = "delete from usuario where ci = ?";
    public static final String CBX_USUARIO = "select * from usuario";

    //APORTES
    public static final String APORTE_CREATE = "insert into aporte (fecha_aporte, valor_aporte, usuario_id, tipoIngreso_id)values(?, ?, ?, ?)";
    public static final String APORTE_READ = "select * from aporte";
    public static final String APORTE_UPDATE = "update aporte set fecha_ahorro = ?, valor_ahorro = ?, usuario_id = ?, tipoIngreso_id= ? where aporte_id = ?";
    public static final String APORTE_DELETE = "delete from aporte where ahorro_id = ?";











}
