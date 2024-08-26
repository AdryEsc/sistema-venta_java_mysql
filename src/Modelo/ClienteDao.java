
package Modelo;

import Entidad.Cliente;
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
    
    //Inserta cliente en el base de datos
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
    
    //Devuelve lista de clientes de la DB
    public List listarClientes(){
        List<Cliente> listaCliente = new ArrayList();
        String consultaSQL = "SELECT * FROM clientes WHERE estado = 1 order by nombre";
        
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
        } finally {
            try {
                conn.close();
            } catch (SQLException ex){
                System.out.println(ex.toString());
            }
        }
        return listaCliente;
    }
    
    //Baja logica del cliente, cambiando su estado a 0
    public boolean eliminarCliente(int id){
        //String consultaSQL = "DELETE clientes WHERE id_cliente = ?";
        String consultaSQL = "UPDATE clientes SET estado = 0 WHERE id_cliente = ?";
        
        try {
            conn = conex.getConnection();
            ps = conn.prepareStatement(consultaSQL);
            ps.setInt(1, id);   //pasamos el valor al ? de la consulta
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
    
    //Actualiza cliente
    public boolean actualizarCliente(Cliente cl){
        String consultaSQL = "UPDATE clientes SET dni_cuit = ?, nombre = ?, direccion = ?, telefono = ?, correo = ?, razon_social = ? WHERE id_cliente = ?";
        
        try {
            conn = conex.getConnection();
            ps = conn.prepareStatement(consultaSQL);
            ps.setLong(1, cl.getDni_cuit());   //pasamos el valor al ? de la consulta
            ps.setString(2, cl.getNombre());
            ps.setString(3, cl.getDireccion());
            ps.setLong(4, cl.getTelefono());
            ps.setString(5, cl.getCorreo());
            ps.setString(6, cl.getRazon_social());
            ps.setInt(7, cl.getId_cliente());   //pasamos el id 
            
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
    
    //Devuelve lista de clientes de la DB
    public List buscarClientesPorNombre(String cadena){
        List<Cliente> listaCliente = new ArrayList();
        String consultaSQL = "SELECT * FROM clientes WHERE estado = 1 AND nombre like '%" + cadena + "%' order by nombre";
        
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
        } finally {
            try {
                conn.close();
            } catch (SQLException ex){
                System.out.println(ex.toString());
            }
        }
        return listaCliente;
    }
    
    //Devuelve lista de clientes de la DB
    public List buscarClientesPorDni(String cadena){
        List<Cliente> listaCliente = new ArrayList();
        String consultaSQL = "SELECT * FROM clientes WHERE estado = 1 AND dni_cuit like '%" + cadena + "%' order by nombre";
        
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
        } finally {
            try {
                conn.close();
            } catch (SQLException ex){
                System.out.println(ex.toString());
            }
        }
        return listaCliente;
    }
    
    
}//Fin clase
