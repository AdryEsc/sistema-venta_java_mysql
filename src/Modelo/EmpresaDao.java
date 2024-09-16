
package Modelo;

import Entidad.Cliente;
import Entidad.Empresa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDao {
    Connection conn;
    Conexion conex = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    //Inserta empresa en el base de datos
    public boolean registrarCliente(Empresa empresa){
        String consultaSQL = "INSERT INTO empresa (cuit, nombre, direccion, telefono, correo, razon_social) VALUES (?,?,?,?,?,?)";
        
        try {
            conn = conex.getConnection();
            ps = conn.prepareStatement(consultaSQL);
            
            /* Pasamos los valores (?) en la consulta */ 
            //ps.setInt(0, empresa.getId_cliente());  //El indice 0 es el id_empresa de la DB
            ps.setLong(1, empresa.getCuit());
            ps.setString(2, empresa.getNombre());
            ps.setString(3, empresa.getDireccion());
            ps.setLong(4, empresa.getTelefono());
            ps.setString(5, empresa.getCorreo());
            ps.setString(6, empresa.getRazon_social());
            
            //Ejecutamos consulta
            ps.execute();
            
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException ex){
                System.out.println(ex.toString());
            }
        }
    }
    
    
    public Empresa buscarEmpresa(){
        Empresa emp = new Empresa();
        String consultaSQL = "SELECT * FROM empresa";
        
        try {
            conn = conex.getConnection();
            ps = conn.prepareStatement(consultaSQL);
            
            rs = ps.executeQuery();
            
            if(rs.next()){
                emp.setCuit(rs.getLong("cuit"));
                emp.setNombre(rs.getString("nombre"));
                emp.setDireccion(rs.getString("direccion"));
                emp.setTelefono(rs.getLong("telefono"));
                emp.setCorreo(rs.getString("correo"));
                emp.setRazon_social(rs.getString("razon_social"));
            }
            
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                conn.close();
            } catch (SQLException ex){
                System.out.println(ex.toString());
            }
        }
        return emp;
    }
    
    
}//Fin de la clase
