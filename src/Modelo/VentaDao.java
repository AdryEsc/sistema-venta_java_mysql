
package Modelo;

import Entidad.Venta;
import Entidad.VentaDetalle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class VentaDao {
    Connection conn;
    Conexion conex = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int resp;
    
    //Inserta venta en el base de datos
    public int registrarVenta(Venta vta){
        String consultaSQL = "INSERT INTO ventas (cliente, vendedor, total) VALUES (?,?,?)";
        
        
        try {
            conn = conex.getConnection();
            ps = conn.prepareStatement(consultaSQL);
            
            /* Pasamos los valores (?) en la consulta */ 
            //ps.setInt(0, cl.getId_cliente());  //El indice 0 es el id_venta de la DB
            ps.setString(1, vta.getCliente());
            ps.setString(2, vta.getVendedor());
            ps.setDouble(3, vta.getTotal());
            
            //Ejecutamos consulta
            ps.execute();
            
            
        } catch (SQLException e) {
            System.out.println(e.toString());
            
        } finally {
            try {
                conn.close();
            } catch (SQLException ex){
                System.out.println(ex.toString());
            }
        }
        return resp;
    }
    
    //Inserta detalle de venta en la base de datos
    public int registrarVentaDetalle(VentaDetalle vtaDetalle){
        String consultaSQL = "INSERT INTO venta_detalle (id_producto, cantidad, subTotal, id_venta) VALUES (?,?,?,?)";
        
        
        try {
            conn = conex.getConnection();
            ps = conn.prepareStatement(consultaSQL);
            
            /* Pasamos los valores (?) en la consulta */ 
            //ps.setInt(0, cl.getId_cliente());  //El indice 0 es el id_venta de la DB
            ps.setInt(1, vtaDetalle.getId_producto());
            ps.setInt(2, vtaDetalle.getCantidad());
            ps.setDouble(3, vtaDetalle.getSubTotal());
            ps.setInt(4, vtaDetalle.getId_venta());
            
            //Ejecutamos consulta
            ps.execute();
            
            
        } catch (SQLException e) {
            System.out.println(e.toString());
            
        } finally {
            try {
                conn.close();
            } catch (SQLException ex){
                System.out.println(ex.toString());
            }
        }
        return resp;
    }
    
} //Fin clase pricipal
