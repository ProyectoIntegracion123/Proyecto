package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    static Connection con;

    public Conexion() {
    }

    public Connection getConnection() throws SQLException {
        con = null;

        String driver = "com.mysql.jdbc.Driver";
        String urlDB = "jdbc:mysql://us-cdbr-east-05.cleardb.net:3306/heroku_743c051ba58d699?zeroDateTimeBehavior=convertToNull";
        String user = "be703576092fb9";
        String pass = "56e316e3";

        try {

            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(urlDB, user, pass);
        } catch (SQLException e) {
            throw e;
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encontro el driver de la BD.");
        } catch (Exception e) {
            throw new SQLException("No se puede establecer "
                    + "conexión de la BD.");
        }
        return con;
    }

}
