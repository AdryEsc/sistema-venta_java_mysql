
package Modelo;

import Entidad.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class ProductoDao {
    Connection conn;
    Conexion conex = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    //Inserta cliente en el base de datos
    public boolean registrarProducto(Producto prod){
        String consultaSQL = "INSERT INTO productos (codigo, descripcion, precio_costo, precio_venta, cantidad, proveedor) VALUES (?,?,?,?,?,?)";
        
        try {
            conn = conex.getConnection();
            ps = conn.prepareStatement(consultaSQL);
            
            /* Pasamos los valores (?) en la consulta */ 
            //ps.setInt(0, cl.getId_cliente());  //El indice 0 es el id_cliente de la DB
            ps.setString(1, prod.getCodigo());
            ps.setString(2, prod.getDescripcion());
            ps.setDouble(3, prod.getPrecio_costo());
            ps.setDouble(4, prod.getPrecio_venta());
            ps.setInt(5, prod.getCantidad());
            ps.setString(6, prod.getProveedor());
            
            //Ejecutamos consulta
            ps.execute();
            
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException ex){
                System.out.println(ex.toString());
            }
        }
    }
    
    
    public void consultarProveedor(JComboBox cbProveedor){
        String consultaSQL = "SELECT nombre FROM proveedores";
        
        try {
            conn = conex.getConnection();
            ps = conn.prepareStatement(consultaSQL);
            rs = ps.executeQuery();
            
            while(rs.next()){
                cbProveedor.addItem(rs.getString("nombre"));
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
    }
    
    //Devuelve lista de productos de la DB
    public List listarProductos(){
        List<Producto> listaProducto = new ArrayList();
        String consultaSQL = "SELECT * FROM productos WHERE estado = 1";
        
        try {
            conn = conex.getConnection();
            ps = conn.prepareStatement(consultaSQL);
            rs = ps.executeQuery();  //devuelve un resultset
            
            //recorremos el resultset
            while(rs.next()){
                Producto prod = new Producto();
                
                //cargamos el objeto producto
                prod.setId_producto(rs.getInt("id_producto"));
                prod.setCodigo(rs.getString("codigo"));
                prod.setDescripcion(rs.getString("descripcion"));
                prod.setPrecio_costo(rs.getDouble("precio_costo"));
                prod.setPrecio_venta(rs.getDouble("precio_venta"));
                prod.setCantidad(rs.getInt("cantidad"));
                prod.setProveedor(rs.getString("proveedor"));
                
                //Agregamos cliente a la lista
                listaProducto.add(prod);
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
        return listaProducto;
    }
    
    //Baja del producto
    public boolean eliminarProducto(int id){
        String consultaSQL = "DELETE FROM productos WHERE id_producto = ?";
        //String consultaSQL = "UPDATE proveedores SET estado = 0 WHERE id_proveedor = ?";
        
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
    public boolean actualizarProducto(Producto prod){
        String consultaSQL = "UPDATE productos SET codigo = ?, descripcion = ?, precio_costo = ?, precio_venta = ?, cantidad = ?, proveedor = ? WHERE id_producto = ?";
        
        try {
            conn = conex.getConnection();
            ps = conn.prepareStatement(consultaSQL);
            ps.setString(1, prod.getCodigo());   //pasamos el valor al ? de la consulta
            ps.setString(2, prod.getDescripcion());
            ps.setDouble(3, prod.getPrecio_costo());
            ps.setDouble(4, prod.getPrecio_venta());
            ps.setInt(5, prod.getCantidad());
            ps.setString(6, prod.getProveedor());
            ps.setInt(7, prod.getId_producto());   //pasamos el id 
            
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
} //Fin clase principal
