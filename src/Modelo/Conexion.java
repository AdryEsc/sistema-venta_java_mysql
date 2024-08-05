
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    //Variable para guardar y retornar la conexion
    Connection conn;
    
    //Metodo para realizar la conexion
    public Connection getConnection(){
        try {
            String myDB = "jdbc:mysql://localhost:3306/fenix?serverTimezone=UTC";   // Driver://ipservidorDB:puerto/nombreDB/horario
            //conn = DriverManager.getConnection(myDB, "root", "");  // StringConexion/usuario/contraseña
            conn = DriverManager.getConnection(myDB, "root", "Adry-49686");  // StringConexion/usuario/contraseña
            
            //String myDB = "jdbc:mysql://sql210.byethost31.com:3306/b31_36977157_fenix?serverTimezone=UTC";   // Driver://ipservidorDB:puerto/nombreDB/horario
            //conn = DriverManager.getConnection(myDB, "b31_36977157", "08ftd92h");  // StringConexion/usuario/contraseña
            
            return conn;
        } catch (SQLException e){
            System.out.println(e.toString()); 
        }
        return null;
    }
}
