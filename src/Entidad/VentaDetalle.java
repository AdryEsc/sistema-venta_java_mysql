
package Entidad;

public class VentaDetalle {
    private int id_venta_detalle;
    private int id_producto;
    private int cantidad;
    private double subTotal;
    private int id_venta;

    public VentaDetalle() {
    }

    public VentaDetalle(int id_venta_detalle, int id_producto, int cantidad, double subTotal, int id_venta) {
        this.id_venta_detalle = id_venta_detalle;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
        this.id_venta = id_venta;
    }

    public int getId_venta_detalle() {
        return id_venta_detalle;
    }

    public void setId_venta_detalle(int id_venta_detalle) {
        this.id_venta_detalle = id_venta_detalle;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }
    
    
    
}
