
package Modelo;

import Entidad.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
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
}
