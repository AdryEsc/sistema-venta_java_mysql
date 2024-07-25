
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClienteDao {
    Connection conn;
    Conexion conex = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean registrarCliente(Cliente cl){
        String consultaSQL = "INSERT INTO clientes (dni_cuit, nombre, direccion, telefono, correo, razon_social) VALUES (?,?,?,?,?,?)";
        
        try {
            conn = conex.getConnection();
            ps = conn.prepareStatement(consultaSQL);
            
            /* Pasamos los valores (?) en la consulta */ 
            //ps.setInt(0, cl.getId_cliente());  //El indice 0 es el id_cliente de la DB
            ps.setLong(1, cl.getDni_cuit());
            ps.setString(2, cl.getNombre());
            ps.setString(3, cl.getDireccion());
            ps.setLong(4, cl.getTelefono());
            ps.setString(5, cl.getCorreo());
            ps.setString(6, cl.getRazon_social());
            
            //Ejecutamos consulta
            ps.execute();
            
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException e){
                System.out.println(e.toString());
            }
        }
    }
    
    public List listarClientes(){
        List<Cliente> listaCliente = new ArrayList();
        String consultaSQL = "SELECT * FROM clientes";
        
        try {
            conn = conex.getConnection();
            ps = conn.prepareStatement(consultaSQL);
            rs = ps.executeQuery();  //devuelve un resultset
            
            //recorremos el resultset
            while(rs.next()){
                Cliente cliente = new Cliente();
                
                //cargamos el objeto cliente
                cliente.setId_cliente(rs.getInt("id_cliente"));
                cliente.setDni_cuit(rs.getLong("dni_cuit"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setTelefono(rs.getLong("telefono"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setRazon_social(rs.getString("razon_social"));
                
                //Agregamos cliente a la lista
                listaCliente.add(cliente);
            }
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return listaCliente;
    }   
    
}
