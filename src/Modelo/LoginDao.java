
package Modelo;

import Entidad.Login;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDao {
    //Variables para la conexion y captura de datos
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    //Instanciamos objeto de tipo Conexion
    Conexion conex = new Conexion();
    
    //Metodo que devuelve objeto de clase Login
    public Login logueo(String correo, String contrasena){
        Login log = new Login();
        String consultaSQL = "SELECT * FROM usuarios WHERE correo = ? AND contrasena = ?";
        
        try {
            conn = conex.getConnection();
            //Capturamos los datos
            ps = conn.prepareStatement(consultaSQL);
            ps.setString(1, correo);
            ps.setString(2, contrasena);
            rs = ps.executeQuery(); //Devuelve un ResultSet
            
            if(rs.next()){
                log.setId_usuario(rs.getInt("id_usuario"));
                log.setNombre(rs.getString("nombre"));
                log.setCorreo(rs.getString("correo"));
                log.setContrasena(rs.getString("contrasena"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return log;
    }
}
