
package Modelo;

import Entidad.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class ProveedorDao {
    Connection conn;
    Conexion conex = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean registrarProveedor(Proveedor prov){
        String consultaSQL = "INSERT INTO proveedores (dni_cuit, nombre, direccion, telefono, correo, razon_social) VALUES (?,?,?,?,?,?)";
    
        try {
            conn = conex.getConnection();
            ps = conn.prepareStatement(consultaSQL);
            
            /* Pasamos los valores (?) en la consulta */ 
            //ps.setInt(0, cl.getId_cliente());  //El indice 0 es el id_cliente de la DB
            ps.setLong(1, prov.getDni_cuit());
            ps.setString(2, prov.getNombre());
            ps.setString(3, prov.getDireccion());
            ps.setLong(4, prov.getTelefono());
            ps.setString(5, prov.getCorreo());
            ps.setString(6, prov.getRazon_social());
            
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
    
    //Devuelve lista de proveedores de la DB
    public List listarProveedores(){
        List<Proveedor> listaProveedor = new ArrayList();
        String consultaSQL = "SELECT * FROM proveedores WHERE estado = 1 order by nombre";
        
        try {
            conn = conex.getConnection();
            ps = conn.prepareStatement(consultaSQL);
            rs = ps.executeQuery();  //devuelve un resultset
            
            //recorremos el resultset
            while(rs.next()){
                Proveedor proveedor = new Proveedor();
                
                //cargamos el objeto proveedor
                proveedor.setId_proveedor(rs.getInt("id_proveedor"));
                proveedor.setDni_cuit(rs.getLong("dni_cuit"));
                proveedor.setNombre(rs.getString("nombre"));
                proveedor.setDireccion(rs.getString("direccion"));
                proveedor.setTelefono(rs.getLong("telefono"));
                proveedor.setCorreo(rs.getString("correo"));
                proveedor.setRazon_social(rs.getString("razon_social"));
                
                //Agregamos proveedor a la lista
                listaProveedor.add(proveedor);
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
        return listaProveedor;
    }
    
    //Baja logica del proveedor, cambiando su estado a 0
    public boolean eliminarProveedor(int id){
        //String consultaSQL = "DELETE clientes WHERE id_cliente = ?";
        String consultaSQL = "UPDATE proveedores SET estado = 0 WHERE id_proveedor = ?";
        
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
    public boolean actualizarProveedor(Proveedor prov){
        String consultaSQL = "UPDATE proveedores SET dni_cuit = ?, nombre = ?, direccion = ?, telefono = ?, correo = ?, razon_social = ? WHERE id_proveedor = ?";
        
        try {
            conn = conex.getConnection();
            ps = conn.prepareStatement(consultaSQL);
            ps.setLong(1, prov.getDni_cuit());   //pasamos el valor al ? de la consulta
            ps.setString(2, prov.getNombre());
            ps.setString(3, prov.getDireccion());
            ps.setLong(4, prov.getTelefono());
            ps.setString(5, prov.getCorreo());
            ps.setString(6, prov.getRazon_social());
            ps.setInt(7, prov.getId_proveedor());   //pasamos el id 
            
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
    public List buscarProveedoresPorNombre(String cadena){
        List<Proveedor> listaProveedor = new ArrayList();
        String consultaSQL = "SELECT * FROM proveedores WHERE estado = 1 AND nombre like '%" + cadena + "%' order by nombre";
        
        try {
            conn = conex.getConnection();
            ps = conn.prepareStatement(consultaSQL);
            rs = ps.executeQuery();  //devuelve un resultset
            
            //recorremos el resultset
            while(rs.next()){
                Proveedor proveedor = new Proveedor();
                
                //cargamos el objeto proveedor
                proveedor.setId_proveedor(rs.getInt("id_proveedor"));
                proveedor.setDni_cuit(rs.getLong("dni_cuit"));
                proveedor.setNombre(rs.getString("nombre"));
                proveedor.setDireccion(rs.getString("direccion"));
                proveedor.setTelefono(rs.getLong("telefono"));
                proveedor.setCorreo(rs.getString("correo"));
                proveedor.setRazon_social(rs.getString("razon_social"));
                
                //Agregamos proveedor a la lista
                listaProveedor.add(proveedor);
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
        return listaProveedor;
    }
    
    //Devuelve lista de clientes de la DB
    public List buscarProveedoresPorDni(String cadena){
        List<Proveedor> listaProveedor = new ArrayList();
        String consultaSQL = "SELECT * FROM proveedores WHERE estado = 1 AND dni_cuit like '%" + cadena + "%' order by nombre";
        
        try {
            conn = conex.getConnection();
            ps = conn.prepareStatement(consultaSQL);
            rs = ps.executeQuery();  //devuelve un resultset
            
            //recorremos el resultset
            while(rs.next()){
                Proveedor proveedor = new Proveedor();
                
                //cargamos el objeto proveedor
                proveedor.setId_proveedor(rs.getInt("id_proveedor"));
                proveedor.setDni_cuit(rs.getLong("dni_cuit"));
                proveedor.setNombre(rs.getString("nombre"));
                proveedor.setDireccion(rs.getString("direccion"));
                proveedor.setTelefono(rs.getLong("telefono"));
                proveedor.setCorreo(rs.getString("correo"));
                proveedor.setRazon_social(rs.getString("razon_social"));
                
                //Agregamos proveedor a la lista
                listaProveedor.add(proveedor);
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
        return listaProveedor;
    }
}
