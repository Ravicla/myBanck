package configurations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static Connection conectar() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banquito", "root", "sale92");
            System.out.println("conectado");
            return connection;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
