
package Entidad;

public class Proveedor {
    private int id_proveedor;
    private long dni_cuit;
    private String nombre;
    private String direccion;
    private long telefono;
    private String correo;
    private String razon_social;
    //private int estado;
    //private Date fecha_alta

    public Proveedor() {
    }

    public Proveedor(int id_proveedor, long dni_cuit, String nombre, String direccion, long telefono, String correo, String razon_social) {
        this.id_proveedor = id_proveedor;
        this.dni_cuit = dni_cuit;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.razon_social = razon_social;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
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
    
    

}
