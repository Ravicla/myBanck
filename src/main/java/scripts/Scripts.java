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
    public static final String APORTE_READ = "SELECT  concat_ws  (' ', u.nombre,  u.apellido) as NOMBRES, a.fecha_aporte as FECHA, a.valor_aporte as VALOR, t.descripcion as TIPO, a.aporte_id as CODE\n" +
            "  FROM usuario as u INNER JOIN aporte as a ON u.usuario_id = a.usuario_id\n" +
            "  INNER JOIN tipoingreso as t ON a.tipoIngreso_id = t.tipoingreso_id;";
    public static final String APORTE_UPDATE = "update aporte set fecha_aporte = ?, valor_aporte = ?, tipoIngreso_id= ? where aporte_id = ?";
    public static final String APORTE_DELETE = "delete from aporte where aporte_id = ?";

    //PRESTAMO
    public static final String PRESTAMO_CREATE = "insert into prestamo (fecha_prestamo, interes, valor_prestamo, tiempo_prestamo, usuario_id)values(?, ?, ?, ?, ?)";
    public static final String CUOTA_CREATE = "insert into cuota (fecha_cuota, valor_capital, pago_capital, valor_interes, pago_interes, abono_capital, abono_interes, prestamo_id)values(?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String PRESTAMO_READ = "SELECT  concat_ws  (' ', u.nombre,  u.apellido) as NOMBRES, \n" +
            "p.fecha_prestamo as FECHA_PRESTAMO, p.interes as INTERES_PRESTAMO, p.valor_prestamo as VALOR_PRESTAMO, p.tiempo_prestamo as TIEMPO_PRESTAMO,\n" +
            "c.fecha_cuota as FECHA_CUOTA, c.valor_capital as VALOR_CAPITAL, c.pago_capital as PAGO_CAPITAL, c.valor_interes as VALOR_INTERES, c.pago_interes as PAGO_INTERES, \n" +
            "c.abono_capital as ABONO_CAPITAL, c.abono_interes as ABONO_INTERES\n" +
            "FROM usuario as u INNER JOIN prestamo as p ON u.usuario_id = p.usuario_id\n" +
            "INNER JOIN cuota as c ON p.prestamo_id = c.prestamo_id";
    public static final String PRESTAMO_UPDATE = "update prestamo set fecha_prestamo = ?, interes = ?, valor_prestamo = ?, tiempo_prestamo = ? where usuario_id = ?";
    public static final String CUOTA_UPDATE = "update cuota set fecha_cuota = ?, valor_capital = ?, pago_capital = ?, valor_interes = ?, pago_interes = ?, abono_capital = ?, abono_interes= ? where prestamo_id = ?";

    public static final String PRESTAMO_DELETE = "delete from prestamo where prestamo_id = ?";
    public static final String CUOTA_DELETE = "delete from cuota where cuota_id = ?";










}
