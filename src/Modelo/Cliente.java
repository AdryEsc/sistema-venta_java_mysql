
package Modelo;

//import java.util.Date;

public class Cliente {
    private int id_cliente;
    private long dni_cuit;
    private String nombre;
    private String direccion;
    private long telefono;
    private String correo;
    private String razon_social;
    //private int estado;
    //private Date fecha_alta;

    public Cliente() {
    }

    public Cliente(int id_cliente, long dni_cuit, String nombre, String direccion, long telefono, String correo, String razon_social/*, int estado, Date fecha_alta*/) {
        this.id_cliente = id_cliente;
        this.dni_cuit = dni_cuit;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.razon_social = razon_social;
        /*this.estado = estado;
        this.fecha_alta = fecha_alta;*/
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public long getDni_cuit() {
        return dni_cuit;
    }

    public void setDni_cuit(long dni_cuit) {
        this.dni_cuit = dni_cuit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    /*
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(Date fecha_alta) {
        this.fecha_alta = fecha_alta;
    }
    */
    
    
}
