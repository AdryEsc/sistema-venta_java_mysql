
package Vista;

import Entidad.Cliente;
import Entidad.Proveedor;
import Entidad.Producto;
import Entidad.Venta;
import Modelo.ClienteDao;
import Modelo.ProveedorDao;
import Modelo.ProductoDao;
import Modelo.VentaDao;
import Reportes.ExcelProductos;
import Reportes.ExportarExcel;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Adry
 */
public class Sistema extends javax.swing.JFrame {
    Cliente cl = new Cliente();
    ClienteDao clDao = new ClienteDao();
    Proveedor prov = new Proveedor();
    ProveedorDao provDao = new ProveedorDao();
    Producto prod = new Producto();
    ProductoDao prodDao = new ProductoDao();
    Venta vta = new Venta();
    VentaDao vtaDao = new VentaDao();
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel modelo2 = new DefaultTableModel();
    int item = 0;
    double total = 0.00;
    
    String idProducto = "";
    String codigoAux = "";
    String descripcionAux = "";
    String precioAux = "";
    String existenciaAux = "";
    
    private static final int COLUMNA_PRECIO = 3;
    private static final int COLUMNA_CANTIDAD = 4;
    private static final int COLUMNA_SUBTOTAL = 5;
    
    
    /**
     * Creates new form Sistema
     */
    public Sistema() {
        initComponents();
        this.setLocationRelativeTo(null);
        txtIdCliente.setVisible(false);
        txtIdProveedor.setVisible(false);
        txtIdProd.setVisible(false);
        
        AutoCompleteDecorator.decorate(cmbProveedor);
        prodDao.consultarProveedor(cmbProveedor);
        
        listarProductosParaVenta();
        
    }
    
    //Metodo que al ingresar la cantidad, calcula el subtotal
    public void calcularSubtotal(){
        Double subTotal = 0.0;
        int numFila = tblVenta.getRowCount();
        for(int i = 0; i < numFila; i++){
            int cantidad = Integer.parseInt(tblVenta.getValueAt(i, COLUMNA_CANTIDAD).toString());
            double precio = Double.parseDouble(tblVenta.getValueAt(i, COLUMNA_PRECIO).toString());
            double subtotalCalculado = cantidad * precio;
            
            tblVenta.getModel().setValueAt(subtotalCalculado, i, COLUMNA_SUBTOTAL);
            subTotal += subtotalCalculado;
            tblVenta.putClientProperty("terminateEditOnFocusLost", true);
        }
        
        
    
    }
   
    
    //Metodo para cargar tabla clientes
    public void listarClientes(){
        List<Cliente> listarCl = clDao.listarClientes();    //metodo de ClientaDao
        modelo =(DefaultTableModel) tblCliente.getModel(); 
        Object[] obj = new Object[7];
        
        for(int i = 0; i < listarCl.size(); i++){
            obj[0] = listarCl.get(i).getId_cliente();
            obj[1] = listarCl.get(i).getDni_cuit();
            obj[2] = listarCl.get(i).getNombre();
            obj[3] = listarCl.get(i).getDireccion();
            obj[4] = listarCl.get(i).getTelefono();
            obj[5] = listarCl.get(i).getCorreo();
            obj[6] = listarCl.get(i).getRazon_social();
            
            modelo.addRow(obj);
        }
        
        tblCliente.setModel(modelo);
    }
    
    //Metodo para cargar tabla clientes
    public void buscarClientesPorNombre(String cadena){
        List<Cliente> listarCl = clDao.buscarClientesPorNombre(cadena);    //metodo de ClientaDao
        modelo = (DefaultTableModel) tblCliente.getModel();
        Object[] obj = new Object[7];
        
        for(int i = 0; i < listarCl.size(); i++){
            obj[0] = listarCl.get(i).getId_cliente();
            obj[1] = listarCl.get(i).getDni_cuit();
            obj[2] = listarCl.get(i).getNombre();
            obj[3] = listarCl.get(i).getDireccion();
            obj[4] = listarCl.get(i).getTelefono();
            obj[5] = listarCl.get(i).getCorreo();
            obj[6] = listarCl.get(i).getRazon_social();
            
            modelo.addRow(obj);
        }
        
        tblCliente.setModel(modelo);
    }
    
    //Metodo para cargar tabla clientes
    public void buscarClientesPorDni(String cadena){
        List<Cliente> listarCl = clDao.buscarClientesPorDni(cadena);    //metodo de ClientaDao
        modelo = (DefaultTableModel) tblCliente.getModel();
        Object[] obj = new Object[7];
        
        for(int i = 0; i < listarCl.size(); i++){
            obj[0] = listarCl.get(i).getId_cliente();
            obj[1] = listarCl.get(i).getDni_cuit();
            obj[2] = listarCl.get(i).getNombre();
            obj[3] = listarCl.get(i).getDireccion();
            obj[4] = listarCl.get(i).getTelefono();
            obj[5] = listarCl.get(i).getCorreo();
            obj[6] = listarCl.get(i).getRazon_social();
            
            modelo.addRow(obj);
        }
        
        tblCliente.setModel(modelo);
    }
    
    //Metodo para cargar tabla clientes
    public void buscarProveedoresPorNombre(String cadena){
        List<Proveedor> listarProv = provDao.buscarProveedoresPorNombre(cadena);    //metodo de ClientaDao
        modelo = (DefaultTableModel) tblProveedor.getModel();
        Object[] obj = new Object[7];
        
        for(int i = 0; i < listarProv.size(); i++){
            obj[0] = listarProv.get(i).getId_proveedor();
            obj[1] = listarProv.get(i).getDni_cuit();
            obj[2] = listarProv.get(i).getNombre();
            obj[3] = listarProv.get(i).getDireccion();
            obj[4] = listarProv.get(i).getTelefono();
            obj[5] = listarProv.get(i).getCorreo();
            obj[6] = listarProv.get(i).getRazon_social();
            
            modelo.addRow(obj);
        }
        
        tblProveedor.setModel(modelo);
    }
    
    //Metodo para cargar tabla clientes
    public void buscarProveedoresPorDni(String cadena){
        List<Proveedor> listarProv = provDao.buscarProveedoresPorDni(cadena);    //metodo de ClientaDao
        modelo = (DefaultTableModel) tblProveedor.getModel();
        Object[] obj = new Object[7];
        
        for(int i = 0; i < listarProv.size(); i++){
            obj[0] = listarProv.get(i).getId_proveedor();
            obj[1] = listarProv.get(i).getDni_cuit();
            obj[2] = listarProv.get(i).getNombre();
            obj[3] = listarProv.get(i).getDireccion();
            obj[4] = listarProv.get(i).getTelefono();
            obj[5] = listarProv.get(i).getCorreo();
            obj[6] = listarProv.get(i).getRazon_social();
            
            modelo.addRow(obj);
        }
        
        tblProveedor.setModel(modelo);
    }
    
    public void listarProveedores(){
        List<Proveedor> listarProv = provDao.listarProveedores();    //metodo de ClientaDao
        modelo = (DefaultTableModel) tblProveedor.getModel();
        Object[] obj = new Object[7];
        
        for(int i = 0; i < listarProv.size(); i++){
            obj[0] = listarProv.get(i).getId_proveedor();
            obj[1] = listarProv.get(i).getDni_cuit();
            obj[2] = listarProv.get(i).getNombre();
            obj[3] = listarProv.get(i).getDireccion();
            obj[4] = listarProv.get(i).getTelefono();
            obj[5] = listarProv.get(i).getCorreo();
            obj[6] = listarProv.get(i).getRazon_social();
            
            modelo.addRow(obj);
        }
        
        tblProveedor.setModel(modelo);
    }
    
    public void listarProductos(){
        List<Producto> listarProd = prodDao.listarProductos();    //metodo de ClientaDao
        modelo = (DefaultTableModel) tblProducto.getModel();
        Object[] obj = new Object[7];
        
        for(int i = 0; i < listarProd.size(); i++){
            obj[0] = listarProd.get(i).getId_producto();
            obj[1] = listarProd.get(i).getCodigo();
            obj[2] = listarProd.get(i).getDescripcion();
            obj[3] = listarProd.get(i).getPrecio_costo();
            obj[4] = listarProd.get(i).getPrecio_venta();
            obj[5] = listarProd.get(i).getCantidad();
            obj[6] = listarProd.get(i).getProveedor();
            
            modelo.addRow(obj);
        }
        
        tblProducto.setModel(modelo);
    }
    
    public void buscarProductosPorDescripcion(String cadena){
        List<Producto> listarProd = prodDao.buscarProductosPorDescripcion(cadena);    //metodo de ClientaDao
        modelo = (DefaultTableModel) tblProducto.getModel();
        Object[] obj = new Object[7];
        
        for(int i = 0; i < listarProd.size(); i++){
            obj[0] = listarProd.get(i).getId_producto();
            obj[1] = listarProd.get(i).getCodigo();
            obj[2] = listarProd.get(i).getDescripcion();
            obj[3] = listarProd.get(i).getPrecio_costo();
            obj[4] = listarProd.get(i).getPrecio_venta();
            obj[5] = listarProd.get(i).getCantidad();
            obj[6] = listarProd.get(i).getProveedor();
            
            modelo.addRow(obj);
        }
        
        tblProducto.setModel(modelo);
    }
    
    public void buscarProductosPorCodigo(String cadena){
        List<Producto> listarProd = prodDao.buscarProductosPorCodigo(cadena);    //metodo de ClientaDao
        modelo = (DefaultTableModel) tblProducto.getModel();
        Object[] obj = new Object[7];
        
        for(int i = 0; i < listarProd.size(); i++){
            obj[0] = listarProd.get(i).getId_producto();
            obj[1] = listarProd.get(i).getCodigo();
            obj[2] = listarProd.get(i).getDescripcion();
            obj[3] = listarProd.get(i).getPrecio_costo();
            obj[4] = listarProd.get(i).getPrecio_venta();
            obj[5] = listarProd.get(i).getCantidad();
            obj[6] = listarProd.get(i).getProveedor();
            
            modelo.addRow(obj);
        }
        
        tblProducto.setModel(modelo);
    }
    
    public void listarProductosParaVenta(){
        List<Producto> listarProd = prodDao.listarProductosParaVenta();    //metodo de ClientaDao
        modelo = (DefaultTableModel) tblProductosVenta.getModel();
        Object[] obj = new Object[5];
        
        for(int i = 0; i < listarProd.size(); i++){
            obj[0] = listarProd.get(i).getId_producto();
            obj[1] = listarProd.get(i).getCodigo();
            obj[2] = listarProd.get(i).getDescripcion();
            //obj[3] = listarProd.get(i).getPrecio_costo();
            obj[3] = listarProd.get(i).getPrecio_venta();
            obj[4] = listarProd.get(i).getCantidad();
            //obj[6] = listarProd.get(i).getProveedor();
            
            modelo.addRow(obj);
        }
        
        tblProductosVenta.setModel(modelo);
    }
    
    public void listarProductosParaVentaPorDescripcion(String cadena){
        List<Producto> listarProd = prodDao.listarProductosParaVentaPorDescripcion(cadena);    //metodo de ClientaDao
        modelo = (DefaultTableModel) tblProductosVenta.getModel();
        Object[] obj = new Object[4];
        
        for(int i = 0; i < listarProd.size(); i++){
            //obj[0] = listarProd.get(i).getId_producto();
            obj[0] = listarProd.get(i).getCodigo();
            obj[1] = listarProd.get(i).getDescripcion();
            //obj[3] = listarProd.get(i).getPrecio_costo();
            obj[2] = listarProd.get(i).getPrecio_venta();
            obj[3] = listarProd.get(i).getCantidad();
            //obj[6] = listarProd.get(i).getProveedor();
            
            modelo.addRow(obj);
        }
        
        tblProductosVenta.setModel(modelo);
    }

    
    //Metodo para limpiar las tablas
    public void limpiarTabla(){
        for(int i = 0; i < modelo.getRowCount(); i++){
            modelo.removeRow(i);
            i = i - 1;
        }
    }
    
    //Metodo para limpiar la tabla clientes
    public void limpiarTablaVentas(){
        for(int i = 0; i < modelo2.getRowCount(); i++){
            modelo2.removeRow(i);
            i = i - 1;
        }
    }
    
    public void limpiarCampos(){
        txtIdCliente.setText("");
        txtDniCuitCliente.setText("");
        txtNombreCliente.setText("");
        txtDireccionCliente.setText("");
        txtTelefonoCliente.setText("");
        txtCorreoCliente.setText("");
        txtRazonCliente.setText("");
        
        txtIdProveedor.setText("");
        txtDniCuitProveedor.setText("");
        txtNombreProveedor.setText("");
        txtDireccionProveedor.setText("");
        txtTelefonoProveedor.setText("");
        txtCorreoProveedor.setText("");
        txtRazonProveedor.setText("");
        
        txtIdProducto.setText("");
        txtCodigoProd.setText("");
        txtDescripcionProd.setText("");
        txtPrecioCostoProd.setText("");
        txtPrecioVentaProd.setText("");
        txtCantidadProd.setText("");
        //AutoCompleteDecorator.decorate(cmbProveedor);
        //prodDao.consultarProveedor(cmbProveedor);

    }
    
    private void totalPagar(){
        total = 0.00;
        int numFila = tblVenta.getRowCount(); //Obtenemos la cantidad de filas de la tabla
        //recorremos la tabla
        for(int i = 0; i < numFila; i++){
            double calc = Double.parseDouble(String.valueOf(tblVenta.getModel().getValueAt(i, 5)));
            total = total + calc;
        }
        txtTotalPagar.setText(""+total);
        //txtTotalPagar.setText(String.format("%.2f", total));
        
    }
    
    
    private void registrarVenta(){
        String cliente = txtNombreClienteVenta.getText();
        String vendedor = lblVendedor.getText();
        double totalP = Double.parseDouble(txtTotalPagar.getText());
        vta.setCliente(cliente);
        vta.setVendedor(vendedor);
        vta.setTotal(totalP);
        vtaDao.registrarVenta(vta);
    }
    
    private boolean verificarPrecioSubtotalCero(){
        boolean verif = false;
        int numFila = tblVenta.getRowCount(); //Obtenemos la cantidad de filas de la tabla
        //recorremos la tabla
        for(int i = 0; i < numFila; i++){
            double calc = Double.parseDouble(String.valueOf(tblVenta.getModel().getValueAt(i, 5)));
            if(calc == 0){
                verif = true;
            }  
        }
        return verif;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnNuevaVenta = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnProveedores = new javax.swing.JButton();
        btnProductos = new javax.swing.JButton();
        btnVentas = new javax.swing.JButton();
        btnConfig = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lblVendedor = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        btnEmininarVenta = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVenta = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txtDniCuitVenta = new javax.swing.JTextField();
        txtNombreClienteVenta = new javax.swing.JTextField();
        btnGenerarVenta = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        lblTotalPagar = new javax.swing.JLabel();
        txtTelefonoClienteVenta = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtDireccionClienteVenta = new javax.swing.JTextField();
        txtCorreoClienteVenta = new javax.swing.JTextField();
        txtIdProducto = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblProductosVenta = new javax.swing.JTable();
        txtBuscarProductoPorDesc = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        btnAgregarProdParaVenta = new javax.swing.JButton();
        txtTotalPagar = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtDniCuitCliente = new javax.swing.JTextField();
        txtNombreCliente = new javax.swing.JTextField();
        txtDireccionCliente = new javax.swing.JTextField();
        txtTelefonoCliente = new javax.swing.JTextField();
        txtCorreoCliente = new javax.swing.JTextField();
        txtRazonCliente = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCliente = new javax.swing.JTable();
        btnGuardarCliente = new javax.swing.JButton();
        btnActualizarCliente = new javax.swing.JButton();
        btnEliminarCliente = new javax.swing.JButton();
        btnNuevoCliente = new javax.swing.JButton();
        txtIdCliente = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        txtBuscarClientePorDni = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        txtBuscarCliente = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtDniCuitProveedor = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtNombreProveedor = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtDireccionProveedor = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtTelefonoProveedor = new javax.swing.JTextField();
        txtCorreoProveedor = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtRazonProveedor = new javax.swing.JTextField();
        btnNuevoProveedor = new javax.swing.JButton();
        btnEliminarProveedor = new javax.swing.JButton();
        btnActualizarProveedor = new javax.swing.JButton();
        btnGuardarProveedor = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblProveedor = new javax.swing.JTable();
        txtIdProveedor = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        txtBuscarProvPorDni = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        txtBuscarProvPorNombre = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        txtCodigoProd = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtDescripcionProd = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtPrecioCostoProd = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtPrecioVentaProd = new javax.swing.JTextField();
        txtCantidadProd = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        btnImprimirExcel = new javax.swing.JButton();
        btnEliminarProd = new javax.swing.JButton();
        btnActualizarProd = new javax.swing.JButton();
        btnGuardarProd = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblProducto = new javax.swing.JTable();
        cmbProveedor = new javax.swing.JComboBox<>();
        btnNuevoProd = new javax.swing.JButton();
        txtIdProd = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        txtBuscarProdPorDescripcion = new javax.swing.JTextField();
        txtBuscarProdPorCodigo = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblVentas = new javax.swing.JTable();
        btnPdfVentas = new javax.swing.JButton();
        txtIdVenta = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtCuitEmpresa = new javax.swing.JTextField();
        txtNombreEmpresa = new javax.swing.JTextField();
        txtDireccionEmpresa = new javax.swing.JTextField();
        txtTelefonoEmpresa = new javax.swing.JTextField();
        txtCorreoEmpresa = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txtRazonEmpresa = new javax.swing.JTextField();
        btnActualizarEmpresa = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));

        btnNuevaVenta.setBackground(new java.awt.Color(255, 255, 255));
        btnNuevaVenta.setForeground(new java.awt.Color(0, 0, 0));
        btnNuevaVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Nventa.png"))); // NOI18N
        btnNuevaVenta.setText("NUEVA VENTA");
        btnNuevaVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevaVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaVentaActionPerformed(evt);
            }
        });

        btnClientes.setBackground(new java.awt.Color(255, 255, 255));
        btnClientes.setForeground(new java.awt.Color(0, 0, 0));
        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Clientes.png"))); // NOI18N
        btnClientes.setText("CLIENTES");
        btnClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });

        btnProveedores.setBackground(new java.awt.Color(255, 255, 255));
        btnProveedores.setForeground(new java.awt.Color(0, 0, 0));
        btnProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/proveedor.png"))); // NOI18N
        btnProveedores.setText("PROVEEDORES");
        btnProveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedoresActionPerformed(evt);
            }
        });

        btnProductos.setBackground(new java.awt.Color(255, 255, 255));
        btnProductos.setForeground(new java.awt.Color(0, 0, 0));
        btnProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/producto.png"))); // NOI18N
        btnProductos.setText("PRODUCTOS");
        btnProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosActionPerformed(evt);
            }
        });

        btnVentas.setBackground(new java.awt.Color(255, 255, 255));
        btnVentas.setForeground(new java.awt.Color(0, 0, 0));
        btnVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/compras.png"))); // NOI18N
        btnVentas.setText("VENTAS");
        btnVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnConfig.setBackground(new java.awt.Color(255, 255, 255));
        btnConfig.setForeground(new java.awt.Color(0, 0, 0));
        btnConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/config.png"))); // NOI18N
        btnConfig.setText("CONFIG.");
        btnConfig.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/logo_espectro.png"))); // NOI18N

        jButton1.setText("USUARIOS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblVendedor.setText("Vendedor");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnNuevaVenta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                    .addComponent(btnClientes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnProveedores, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnProductos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVentas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnConfig, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 3, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jButton1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblVendedor)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblVendedor)
                .addGap(10, 10, 10)
                .addComponent(btnNuevaVenta)
                .addGap(32, 32, 32)
                .addComponent(btnClientes)
                .addGap(25, 25, 25)
                .addComponent(btnProveedores)
                .addGap(29, 29, 29)
                .addComponent(btnProductos)
                .addGap(28, 28, 28)
                .addComponent(btnVentas)
                .addGap(40, 40, 40)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(btnConfig)
                .addGap(37, 37, 37))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 670));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/encabezado.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 910, 140));

        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        btnEmininarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btnEmininarVenta.setText("Eliminar");
        btnEmininarVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEmininarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmininarVentaActionPerformed(evt);
            }
        });

        tblVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID PRODUCTO", "CODIGO", "DESCRIPCION", "PRECIO", "CANTIDAD", "SUBTOTAL"
            }
        ));
        tblVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVentaMouseClicked(evt);
            }
        });
        tblVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblVentaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblVentaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblVenta);
        if (tblVenta.getColumnModel().getColumnCount() > 0) {
            tblVenta.getColumnModel().getColumn(0).setPreferredWidth(30);
            tblVenta.getColumnModel().getColumn(1).setPreferredWidth(50);
            tblVenta.getColumnModel().getColumn(2).setPreferredWidth(150);
            tblVenta.getColumnModel().getColumn(3).setPreferredWidth(50);
            tblVenta.getColumnModel().getColumn(4).setPreferredWidth(50);
            tblVenta.getColumnModel().getColumn(5).setPreferredWidth(50);
        }

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel9.setText("Buscar:");

        txtDniCuitVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtDniCuitVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDniCuitVentaKeyPressed(evt);
            }
        });

        txtNombreClienteVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnGenerarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/print.png"))); // NOI18N
        btnGenerarVenta.setText("GENERAR VENTA");
        btnGenerarVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGenerarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarVentaActionPerformed(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/money.png"))); // NOI18N
        jLabel10.setText("TOTAL A PAGAR $:");

        lblTotalPagar.setText("-------");
        lblTotalPagar.setEnabled(false);

        txtTelefonoClienteVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoClienteVentaActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel11.setText("DNI / CUIT");

        tblProductosVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID PRODUCTO", "CODIGO", "DESCRIPCION", "PRECIO", "EXISTENCIA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProductosVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosVentaMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblProductosVenta);
        if (tblProductosVenta.getColumnModel().getColumnCount() > 0) {
            tblProductosVenta.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblProductosVenta.getColumnModel().getColumn(1).setPreferredWidth(40);
            tblProductosVenta.getColumnModel().getColumn(2).setPreferredWidth(150);
            tblProductosVenta.getColumnModel().getColumn(3).setPreferredWidth(30);
            tblProductosVenta.getColumnModel().getColumn(4).setPreferredWidth(40);
        }

        txtBuscarProductoPorDesc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarProductoPorDescKeyReleased(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel43.setText("Nombre del cliente");

        btnAgregarProdParaVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nuevo.png"))); // NOI18N
        btnAgregarProdParaVenta.setText("Agregar");
        btnAgregarProdParaVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarProdParaVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProdParaVentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(txtDniCuitVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtNombreClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtCorreoClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtTelefonoClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)
                                .addComponent(jLabel11)
                                .addGap(101, 101, 101)
                                .addComponent(jLabel43))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(txtDireccionClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGenerarVenta)
                        .addGap(39, 39, 39)
                        .addComponent(jLabel10)
                        .addGap(3, 3, 3)
                        .addComponent(txtTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 858, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(559, 559, 559)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAgregarProdParaVenta)
                                    .addComponent(txtBuscarProductoPorDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEmininarVenta)
                                .addGap(8, 8, 8)))))
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBuscarProductoPorDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addComponent(btnAgregarProdParaVenta)
                        .addGap(24, 24, 24)
                        .addComponent(btnEmininarVenta)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel43)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtDniCuitVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNombreClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtTelefonoClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDireccionClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCorreoClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnGenerarVenta)
                                    .addComponent(jLabel10)
                                    .addComponent(txtTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lblTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79))))
        );

        jTabbedPane1.addTab("NUEVA VENTA", jPanel2);

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel12.setText("DNI / CUIT");

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel13.setText("Nombre");

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel14.setText("Dirección");

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel15.setText("Telefono");

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel16.setText("Correo electronico");

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel17.setText("Razon social");

        txtDniCuitCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtNombreCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtDireccionCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtTelefonoCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtCorreoCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtRazonCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_CLIENTE", "DNI / CUIT", "NOMBRE", "DIRECCION", "TELEFONO", "CORREO", "RAZON SOCIAL"
            }
        ));
        tblCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClienteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCliente);
        if (tblCliente.getColumnModel().getColumnCount() > 0) {
            tblCliente.getColumnModel().getColumn(0).setPreferredWidth(40);
            tblCliente.getColumnModel().getColumn(1).setPreferredWidth(50);
            tblCliente.getColumnModel().getColumn(2).setPreferredWidth(120);
            tblCliente.getColumnModel().getColumn(3).setPreferredWidth(120);
            tblCliente.getColumnModel().getColumn(4).setPreferredWidth(40);
            tblCliente.getColumnModel().getColumn(5).setPreferredWidth(150);
            tblCliente.getColumnModel().getColumn(6).setPreferredWidth(80);
        }

        btnGuardarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/GuardarTodo.png"))); // NOI18N
        btnGuardarCliente.setText("GUARDAR");
        btnGuardarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarClienteActionPerformed(evt);
            }
        });

        btnActualizarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Actualizar (2).png"))); // NOI18N
        btnActualizarCliente.setText("ACTUALIZAR");
        btnActualizarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarClienteActionPerformed(evt);
            }
        });

        btnEliminarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btnEliminarCliente.setText("ELIMINAR");
        btnEliminarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarClienteActionPerformed(evt);
            }
        });

        btnNuevoCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nuevo.png"))); // NOI18N
        btnNuevoCliente.setText("NUEVO");
        btnNuevoCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoClienteActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel37.setText("Buscar por Nombre:");

        txtBuscarClientePorDni.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtBuscarClientePorDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarClientePorDniKeyReleased(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel38.setText("Buscar por DNI:");

        txtBuscarCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtBuscarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarClienteKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel12)
                        .addGap(95, 95, 95)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14)
                        .addGap(88, 88, 88)
                        .addComponent(jLabel15)
                        .addGap(63, 63, 63)
                        .addComponent(jLabel16)
                        .addGap(57, 57, 57)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 885, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(txtDniCuitCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnGuardarCliente)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel38)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtBuscarClientePorDni, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDireccionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnActualizarCliente))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(txtTelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtCorreoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtRazonCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(btnEliminarCliente)
                                        .addGap(44, 44, 44)
                                        .addComponent(btnNuevoCliente))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(239, 239, 239))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel12)
                            .addComponent(jLabel17)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDniCuitCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDireccionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCorreoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRazonCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarCliente)
                    .addComponent(btnActualizarCliente)
                    .addComponent(btnEliminarCliente)
                    .addComponent(btnNuevoCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(txtBuscarClientePorDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38)
                    .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("CLIENTES", jPanel3);

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel18.setText("DNI / CUIT");

        txtDniCuitProveedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel19.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel19.setText("Nombre");

        txtNombreProveedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel20.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel20.setText("Dirección");

        txtDireccionProveedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel21.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel21.setText("Telefono");

        txtTelefonoProveedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtCorreoProveedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel22.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel22.setText("Correo electronico");

        jLabel23.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel23.setText("Razon social");

        txtRazonProveedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnNuevoProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nuevo.png"))); // NOI18N
        btnNuevoProveedor.setText("NUEVO");
        btnNuevoProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevoProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProveedorActionPerformed(evt);
            }
        });

        btnEliminarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btnEliminarProveedor.setText("ELIMINAR");
        btnEliminarProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProveedorActionPerformed(evt);
            }
        });

        btnActualizarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Actualizar (2).png"))); // NOI18N
        btnActualizarProveedor.setText("ACTUALIZAR");
        btnActualizarProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarProveedorActionPerformed(evt);
            }
        });

        btnGuardarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/GuardarTodo.png"))); // NOI18N
        btnGuardarProveedor.setText("GUARDAR");
        btnGuardarProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarProveedorActionPerformed(evt);
            }
        });

        tblProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_PROVEEDOR", "DNI / CUIT", "NOMBRE", "DIRECCION", "TELEFONO", "CORREO", "RAZON SOCIAL"
            }
        ));
        tblProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProveedorMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblProveedor);
        if (tblProveedor.getColumnModel().getColumnCount() > 0) {
            tblProveedor.getColumnModel().getColumn(0).setPreferredWidth(40);
            tblProveedor.getColumnModel().getColumn(1).setPreferredWidth(50);
            tblProveedor.getColumnModel().getColumn(2).setPreferredWidth(120);
            tblProveedor.getColumnModel().getColumn(3).setPreferredWidth(120);
            tblProveedor.getColumnModel().getColumn(4).setPreferredWidth(40);
            tblProveedor.getColumnModel().getColumn(5).setPreferredWidth(150);
            tblProveedor.getColumnModel().getColumn(6).setPreferredWidth(80);
        }

        jLabel39.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel39.setText("Buscar por DNI:");

        txtBuscarProvPorDni.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtBuscarProvPorDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarProvPorDniKeyReleased(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel40.setText("Buscar por Nombre:");

        txtBuscarProvPorNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtBuscarProvPorNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarProvPorNombreKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(txtDniCuitProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtNombreProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtDireccionProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTelefonoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtCorreoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtRazonProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel18)
                        .addGap(81, 81, 81)
                        .addComponent(jLabel19)
                        .addGap(118, 118, 118)
                        .addComponent(jLabel20)
                        .addGap(101, 101, 101)
                        .addComponent(jLabel21)
                        .addGap(70, 70, 70)
                        .addComponent(jLabel22)
                        .addGap(62, 62, 62)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(txtIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(239, 239, 239))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 885, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(174, 174, 174)
                        .addComponent(btnGuardarProveedor)
                        .addGap(70, 70, 70)
                        .addComponent(btnActualizarProveedor)
                        .addGap(58, 58, 58)
                        .addComponent(btnEliminarProveedor)
                        .addGap(52, 52, 52)
                        .addComponent(btnNuevoProveedor))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jLabel39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscarProvPorDni, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(121, 121, 121)
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscarProvPorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22)
                            .addComponent(jLabel18)
                            .addComponent(jLabel23)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDniCuitProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDireccionProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefonoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCorreoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRazonProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarProveedor)
                    .addComponent(btnActualizarProveedor)
                    .addComponent(btnEliminarProveedor)
                    .addComponent(btnNuevoProveedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(txtBuscarProvPorDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40)
                    .addComponent(txtBuscarProvPorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("PROVEEDORES", jPanel4);

        jLabel24.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel24.setText("Codigo");

        txtCodigoProd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCodigoProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoProdActionPerformed(evt);
            }
        });
        txtCodigoProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoProdKeyPressed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel25.setText("Descripcion");

        txtDescripcionProd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtDescripcionProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionProdActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel26.setText("Precio costo");

        txtPrecioCostoProd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel27.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel27.setText("Precio venta");

        txtPrecioVentaProd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtCantidadProd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel28.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel28.setText("Cantidad");

        jLabel29.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel29.setText("Proveedor");

        btnImprimirExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/excel.png"))); // NOI18N
        btnImprimirExcel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnImprimirExcel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnImprimirExcelMouseClicked(evt);
            }
        });
        btnImprimirExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirExcelActionPerformed(evt);
            }
        });

        btnEliminarProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btnEliminarProd.setText("ELIMINAR");
        btnEliminarProd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProdActionPerformed(evt);
            }
        });

        btnActualizarProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Actualizar (2).png"))); // NOI18N
        btnActualizarProd.setText("ACTUALIZAR");
        btnActualizarProd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarProdActionPerformed(evt);
            }
        });

        btnGuardarProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/GuardarTodo.png"))); // NOI18N
        btnGuardarProd.setText("GUARDAR");
        btnGuardarProd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarProdActionPerformed(evt);
            }
        });

        tblProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_PRODUCTO", "CODIGO", "DESCRIPCION", "COSTO", "VENTA", "CANTIDAD", "PROVEEDOR"
            }
        ));
        tblProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblProducto);
        if (tblProducto.getColumnModel().getColumnCount() > 0) {
            tblProducto.getColumnModel().getColumn(0).setPreferredWidth(60);
            tblProducto.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblProducto.getColumnModel().getColumn(2).setPreferredWidth(150);
            tblProducto.getColumnModel().getColumn(3).setPreferredWidth(30);
            tblProducto.getColumnModel().getColumn(4).setPreferredWidth(30);
            tblProducto.getColumnModel().getColumn(5).setPreferredWidth(30);
            tblProducto.getColumnModel().getColumn(6).setPreferredWidth(120);
        }

        cmbProveedor.setEditable(true);
        cmbProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProveedorActionPerformed(evt);
            }
        });

        btnNuevoProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nuevo.png"))); // NOI18N
        btnNuevoProd.setText("NUEVO");
        btnNuevoProd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevoProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProdActionPerformed(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel41.setText("Buscar por Descripcion:");

        txtBuscarProdPorDescripcion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtBuscarProdPorDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarProdPorDescripcionKeyReleased(evt);
            }
        });

        txtBuscarProdPorCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtBuscarProdPorCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarProdPorCodigoKeyReleased(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel42.setText("Buscar por Codigo:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel24)
                        .addGap(110, 110, 110)
                        .addComponent(jLabel25)
                        .addGap(110, 110, 110)
                        .addComponent(jLabel26)
                        .addGap(50, 50, 50)
                        .addComponent(jLabel27)
                        .addGap(50, 50, 50)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(txtIdProd, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 885, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtCodigoProd, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(txtDescripcionProd, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtPrecioCostoProd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(btnGuardarProd)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnActualizarProd)
                                        .addGap(54, 54, 54)))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(btnEliminarProd)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnNuevoProd)
                                        .addGap(183, 183, 183))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(txtPrecioVentaProd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtCantidadProd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addComponent(jLabel42)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBuscarProdPorCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(121, 121, 121)
                                .addComponent(jLabel41)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBuscarProdPorDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(139, 139, 139)
                                .addComponent(btnImprimirExcel)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel25)
                                .addComponent(jLabel26)
                                .addComponent(jLabel27)
                                .addComponent(jLabel28))
                            .addComponent(txtIdProd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(1, 1, 1)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigoProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcionProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecioCostoProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecioVentaProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantidadProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevoProd)
                    .addComponent(btnEliminarProd)
                    .addComponent(btnActualizarProd)
                    .addComponent(btnGuardarProd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnImprimirExcel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel42)
                        .addComponent(txtBuscarProdPorCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel41)
                        .addComponent(txtBuscarProdPorDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("PRODUCTOS", jPanel5);

        tblVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CLIENTE", "VENDEDOR", "TOTAL", "FECHA"
            }
        ));
        jScrollPane5.setViewportView(tblVentas);
        if (tblVentas.getColumnModel().getColumnCount() > 0) {
            tblVentas.getColumnModel().getColumn(0).setPreferredWidth(30);
            tblVentas.getColumnModel().getColumn(1).setPreferredWidth(150);
            tblVentas.getColumnModel().getColumn(2).setPreferredWidth(150);
            tblVentas.getColumnModel().getColumn(3).setPreferredWidth(50);
            tblVentas.getColumnModel().getColumn(4).setPreferredWidth(50);
        }

        btnPdfVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/pdf.png"))); // NOI18N
        btnPdfVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPdfVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPdfVentasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 881, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(btnPdfVentas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtIdVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPdfVentas)
                    .addComponent(txtIdVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("TODAS LAS VENTAS", jPanel6);

        jLabel30.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel30.setText("CUIT");

        jLabel31.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel31.setText("Telefono");

        jLabel32.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel32.setText("Nombre");

        jLabel33.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel33.setText("Correo");

        jLabel34.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel34.setText("Direccion");

        txtCuitEmpresa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtNombreEmpresa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtDireccionEmpresa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtTelefonoEmpresa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtCorreoEmpresa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel35.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel35.setText("Razon social");

        txtRazonEmpresa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtRazonEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRazonEmpresaActionPerformed(evt);
            }
        });

        btnActualizarEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Actualizar (2).png"))); // NOI18N
        btnActualizarEmpresa.setText("ACTUALIZAR");
        btnActualizarEmpresa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel36.setFont(new java.awt.Font("Dubai", 1, 24)); // NOI18N
        jLabel36.setText("DATOS DE LA EMPRESA");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel32)
                        .addGap(260, 260, 260)
                        .addComponent(jLabel34)
                        .addGap(60, 60, 60))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(txtCuitEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(142, 142, 142)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNombreEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCorreoEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtTelefonoEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRazonEmpresa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDireccionEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel31)
                        .addGap(223, 223, 223)
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel35)
                        .addGap(41, 41, 41)))
                .addGap(129, 129, 129))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(287, 287, 287)
                        .addComponent(jLabel36))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(337, 337, 337)
                        .addComponent(btnActualizarEmpresa)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel36)
                .addGap(71, 71, 71)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jLabel32)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCuitEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDireccionEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jLabel35)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefonoEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCorreoEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRazonEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addComponent(btnActualizarEmpresa)
                .addContainerGap(133, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("CONFIGURACION", jPanel7);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 910, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtRazonEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRazonEmpresaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRazonEmpresaActionPerformed

    private void btnPdfVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPdfVentasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPdfVentasActionPerformed

    private void btnGuardarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarClienteActionPerformed
        if(!"".equals(txtDniCuitCliente.getText()) || !"".equals(txtNombreCliente.getText()) || !"".equals(txtDireccionCliente.getText()) || !"".equals(txtTelefonoCliente.getText()) || !"".equals(txtCorreoCliente.getText()) || !"".equals(txtRazonCliente.getText())){
            try{
            cl.setDni_cuit(Long.parseLong(txtDniCuitCliente.getText()));  //Convertimos a long
            cl.setNombre(txtNombreCliente.getText());
            cl.setDireccion(txtDireccionCliente.getText());
            cl.setTelefono(Long.parseLong(txtTelefonoCliente.getText()));
            cl.setCorreo(txtCorreoCliente.getText());
            cl.setRazon_social(txtRazonCliente.getText());
            
            clDao.registrarCliente(cl);
             JOptionPane.showMessageDialog(null, "Cliente registrado con exito","CLIENTES",JOptionPane.PLAIN_MESSAGE);
             limpiarTabla();
             listarClientes();
             limpiarCampos();
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, e.toString());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor completa todos los campos para el registro","CLIENTES",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarClienteActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        limpiarTabla();
        listarClientes();
        jTabbedPane1.setSelectedIndex(1);   //muestra el tab de cliente
    }//GEN-LAST:event_btnClientesActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
       
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void tblClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClienteMouseClicked
        int fila = tblCliente.rowAtPoint(evt.getPoint());
        
        txtIdCliente.setText(tblCliente.getValueAt(fila, 0).toString());
        txtDniCuitCliente.setText(tblCliente.getValueAt(fila, 1).toString());
        txtNombreCliente.setText(tblCliente.getValueAt(fila, 2).toString());
        txtDireccionCliente.setText(tblCliente.getValueAt(fila, 3).toString());
        txtTelefonoCliente.setText(tblCliente.getValueAt(fila, 4).toString());
        txtCorreoCliente.setText(tblCliente.getValueAt(fila, 5).toString());
        txtRazonCliente.setText(tblCliente.getValueAt(fila, 6).toString());
    }//GEN-LAST:event_tblClienteMouseClicked

    private void btnEliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarClienteActionPerformed
        if(!"".equals(txtIdCliente.getText())){ //Si el txtId es distinto de vacio
            int pregunta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar el cliente?");
            if(pregunta == 0){
                int idCliente = Integer.parseInt(txtIdCliente.getText());
                clDao.eliminarCliente(idCliente);
                JOptionPane.showMessageDialog(null, "Cliente eliminado con exito","CLIENTES",JOptionPane.PLAIN_MESSAGE);
                limpiarTabla();
                listarClientes();
                limpiarCampos();
            }
        }
    }//GEN-LAST:event_btnEliminarClienteActionPerformed

    private void btnActualizarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarClienteActionPerformed
        if("".equals(txtIdCliente.getText())){
            JOptionPane.showMessageDialog(null, "Seleccione en la tabla el cliente a actualizar");
        } else{
            if(!"".equals(txtDniCuitCliente.getText()) || !"".equals(txtNombreCliente.getText()) || !"".equals(txtDireccionCliente.getText()) || !"".equals(txtTelefonoCliente.getText()) || !"".equals(txtCorreoCliente.getText()) || !"".equals(txtRazonCliente.getText())){
                cl.setDni_cuit(Long.parseLong(txtDniCuitCliente.getText()));  //Convertimos a long
                cl.setNombre(txtNombreCliente.getText());
                cl.setDireccion(txtDireccionCliente.getText());
                cl.setTelefono(Long.parseLong(txtTelefonoCliente.getText()));
                cl.setCorreo(txtCorreoCliente.getText());
                cl.setRazon_social(txtRazonCliente.getText());
                cl.setId_cliente(Integer.parseInt(txtIdCliente.getText())); //le pasamos el id para la consulta
                
                clDao.actualizarCliente(cl);
                JOptionPane.showMessageDialog(null, "Cliente actualizado con exito");
                limpiarTabla();
                listarClientes();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(null, "Para actualizar deben estar todos los campos cargados");
            }
        }
    }//GEN-LAST:event_btnActualizarClienteActionPerformed

    private void btnNuevoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoClienteActionPerformed
        limpiarTabla();
        listarClientes();
        limpiarCampos();
    }//GEN-LAST:event_btnNuevoClienteActionPerformed

    private void btnGuardarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarProveedorActionPerformed
        if(!"".equals(txtDniCuitProveedor.getText()) || !"".equals(txtNombreProveedor.getText()) || !"".equals(txtDireccionProveedor.getText()) || !"".equals(txtTelefonoProveedor.getText()) || !"".equals(txtCorreoProveedor.getText()) || !"".equals(txtRazonProveedor.getText())){
            try{
            prov.setDni_cuit(Long.parseLong(txtDniCuitProveedor.getText()));  //Convertimos a long
            prov.setNombre(txtNombreProveedor.getText());
            prov.setDireccion(txtDireccionProveedor.getText());
            prov.setTelefono(Long.parseLong(txtTelefonoProveedor.getText()));
            prov.setCorreo(txtCorreoProveedor.getText());
            prov.setRazon_social(txtRazonProveedor.getText());
            
            provDao.registrarProveedor(prov);
             JOptionPane.showMessageDialog(null, "Proveedor registrado con exito");
             limpiarTabla();
             listarProveedores();
             limpiarCampos();
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, e.toString());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor completa todos los campos para el registro");
        }

    }//GEN-LAST:event_btnGuardarProveedorActionPerformed

    private void btnProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedoresActionPerformed
        limpiarTabla();
        listarProveedores();
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_btnProveedoresActionPerformed

    private void btnNuevoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProveedorActionPerformed
        limpiarTabla();
        listarProveedores();
        limpiarCampos();
    }//GEN-LAST:event_btnNuevoProveedorActionPerformed

    private void btnEliminarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProveedorActionPerformed
        if(!"".equals(txtIdProveedor.getText())){ //Si el txtId es distinto de vacio
            int pregunta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar el proveedor?");
            if(pregunta == 0){
                int idProveedor = Integer.parseInt(txtIdProveedor.getText());
                provDao.eliminarProveedor(idProveedor);
                JOptionPane.showMessageDialog(null, "Proveedor eliminado con exito");
                limpiarTabla();
                listarProveedores();
                limpiarCampos();
            }
        }
    }//GEN-LAST:event_btnEliminarProveedorActionPerformed

    private void tblProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProveedorMouseClicked
        int fila = tblProveedor.rowAtPoint(evt.getPoint());
        
        txtIdProveedor.setText(tblProveedor.getValueAt(fila, 0).toString());
        txtDniCuitProveedor.setText(tblProveedor.getValueAt(fila, 1).toString());
        txtNombreProveedor.setText(tblProveedor.getValueAt(fila, 2).toString());
        txtDireccionProveedor.setText(tblProveedor.getValueAt(fila, 3).toString());
        txtTelefonoProveedor.setText(tblProveedor.getValueAt(fila, 4).toString());
        txtCorreoProveedor.setText(tblProveedor.getValueAt(fila, 5).toString());
        txtRazonProveedor.setText(tblProveedor.getValueAt(fila, 6).toString());
    }//GEN-LAST:event_tblProveedorMouseClicked

    private void btnActualizarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarProveedorActionPerformed
        if("".equals(txtIdProveedor.getText())){
            JOptionPane.showMessageDialog(null, "Seleccione en la tabla el proveedor a actualizar");
        } else{
            if(!"".equals(txtDniCuitProveedor.getText()) || !"".equals(txtNombreProveedor.getText()) || !"".equals(txtDireccionProveedor.getText()) || !"".equals(txtTelefonoProveedor.getText()) || !"".equals(txtCorreoProveedor.getText()) || !"".equals(txtRazonProveedor.getText())){
                prov.setDni_cuit(Long.parseLong(txtDniCuitProveedor.getText()));  //Convertimos a long
                prov.setNombre(txtNombreProveedor.getText());
                prov.setDireccion(txtDireccionProveedor.getText());
                prov.setTelefono(Long.parseLong(txtTelefonoProveedor.getText()));
                prov.setCorreo(txtCorreoProveedor.getText());
                prov.setRazon_social(txtRazonProveedor.getText());
                prov.setId_proveedor(Integer.parseInt(txtIdProveedor.getText())); //le pasamos el id para la consulta
                
                provDao.actualizarProveedor(prov);
                JOptionPane.showMessageDialog(null, "Proveedor actualizado con exito");
                limpiarTabla();
                listarProveedores();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(null, "Para actualizar deben estar todos los campos cargados");
            }
        }
    }//GEN-LAST:event_btnActualizarProveedorActionPerformed

    private void txtCodigoProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoProdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoProdActionPerformed

    private void btnGuardarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarProdActionPerformed
        boolean verificarComaDecimal;
        if(!"".equals(txtCodigoProd.getText()) || !"".equals(txtDescripcionProd.getText()) || !"".equals(txtPrecioCostoProd.getText()) || !"".equals(txtPrecioVentaProd.getText()) || !"".equals(txtCantidadProd.getText()) /*|| !"".equals(cmbProveedor.getSelectedItem())*/){
            if(txtPrecioCostoProd.getText().contains(",") || txtPrecioVentaProd.getText().contains(",")){ //Verificamos si se agrego la coma en los precios
                JOptionPane.showMessageDialog(null, "Por favor, para la parte decimal del precio utilice el punto ( . )");
                } else {
                    try{
                    prod.setCodigo(txtCodigoProd.getText());
                    prod.setDescripcion(txtDescripcionProd.getText());
                    prod.setPrecio_costo(Double.parseDouble(txtPrecioCostoProd.getText()));
                    prod.setPrecio_venta(Double.parseDouble(txtPrecioVentaProd.getText()));
                    prod.setCantidad(Integer.parseInt(txtCantidadProd.getText()));
                    prod.setProveedor(cmbProveedor.getSelectedItem().toString());
            
                    prodDao.registrarProducto(prod);
                    JOptionPane.showMessageDialog(null, "Producto registrado con exito");
                    limpiarTabla();
                    listarProductos();
                    limpiarCampos();
            
                    } catch (Exception e){
                    JOptionPane.showMessageDialog(null, e.toString());
                }
            }
           
        } else {
            JOptionPane.showMessageDialog(null, "Por favor completa todos los campos para el registro");
        }
    }//GEN-LAST:event_btnGuardarProdActionPerformed

    private void cmbProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbProveedorActionPerformed

    private void btnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosActionPerformed
        limpiarTabla();
        listarProductos();
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_btnProductosActionPerformed

    private void btnNuevoProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProdActionPerformed
        limpiarTabla();
        listarProductos();
        limpiarCampos();
    }//GEN-LAST:event_btnNuevoProdActionPerformed

    private void tblProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductoMouseClicked
        int fila = tblProducto.rowAtPoint(evt.getPoint());
        
        txtIdProducto.setText(tblProducto.getValueAt(fila, 0).toString());
        txtCodigoProd.setText(tblProducto.getValueAt(fila, 1).toString());
        txtDescripcionProd.setText(tblProducto.getValueAt(fila, 2).toString());
        txtPrecioCostoProd.setText(tblProducto.getValueAt(fila, 3).toString());
        txtPrecioVentaProd.setText(tblProducto.getValueAt(fila, 4).toString());
        txtCantidadProd.setText(tblProducto.getValueAt(fila, 5).toString());
        cmbProveedor.setSelectedItem(tblProducto.getValueAt(fila, 6).toString());
        
    }//GEN-LAST:event_tblProductoMouseClicked

    private void btnEliminarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProdActionPerformed
        if(!"".equals(txtIdProducto.getText())){ //Si el txtId es distinto de vacio
            int pregunta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar el producto?");
            if(pregunta == 0){
                int idProducto = Integer.parseInt(txtIdProducto.getText());
                prodDao.eliminarProducto(idProducto);
                JOptionPane.showMessageDialog(null, "Producto eliminado con exito");
                limpiarTabla();
                listarProductos();
                limpiarCampos();
               
            }
        }
    }//GEN-LAST:event_btnEliminarProdActionPerformed

    private void btnActualizarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarProdActionPerformed
        if("".equals(txtIdProducto.getText())){
            JOptionPane.showMessageDialog(null, "Seleccione en la tabla el producto a actualizar");
        } else{
            if(!"".equals(txtCodigoProd.getText()) || !"".equals(txtDescripcionProd.getText()) || !"".equals(txtPrecioCostoProd.getText()) || !"".equals(txtPrecioVentaProd.getText()) || !"".equals(txtCantidadProd.getText())){
                if(txtPrecioCostoProd.getText().contains(",") || txtPrecioVentaProd.getText().contains(",")){ //Verificamos si se agrego la coma en los precios
                JOptionPane.showMessageDialog(null, "Por favor, para la parte decimal del precio utilice el punto ( . )");
                } else {
                prod.setCodigo(txtCodigoProd.getText());  //Convertimos a long
                prod.setDescripcion(txtDescripcionProd.getText());
                prod.setPrecio_costo(Double.parseDouble(txtPrecioCostoProd.getText()));
                prod.setPrecio_venta(Double.parseDouble(txtPrecioVentaProd.getText()));
                prod.setCantidad(Integer.parseInt(txtCantidadProd.getText()));
                prod.setProveedor(cmbProveedor.getSelectedItem().toString());
                prod.setId_producto(Integer.parseInt(txtIdProducto.getText())); //le pasamos el id para la consulta
                
                prodDao.actualizarProducto(prod);
                JOptionPane.showMessageDialog(null, "Producto actualizado con exito");
                limpiarTabla();
                listarProductos();
                limpiarCampos();
                        }
            } else {
                JOptionPane.showMessageDialog(null, "Para actualizar deben estar todos los campos cargados");
            }
        }
    }//GEN-LAST:event_btnActualizarProdActionPerformed

    private void btnImprimirExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirExcelActionPerformed
        
        ExportarExcel obj;

        try {
            obj = new ExportarExcel();
            obj.exportarExcel(tblProducto);
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
        
        //ExcelProductos.reporte();
    }//GEN-LAST:event_btnImprimirExcelActionPerformed

    private void btnImprimirExcelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImprimirExcelMouseClicked
      /*
        ExportarExcel obj;

        try {
            obj = new ExportarExcel();
            obj.exportarExcel(tblProducto);
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
        */
    }//GEN-LAST:event_btnImprimirExcelMouseClicked

    private void txtCodigoProdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProdKeyPressed
        
    }//GEN-LAST:event_txtCodigoProdKeyPressed

    private void txtDescripcionProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionProdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionProdActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtTelefonoClienteVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoClienteVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoClienteVentaActionPerformed

    private void txtBuscarClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarClienteKeyReleased
        limpiarTabla();
        buscarClientesPorNombre(txtBuscarCliente.getText());
    }//GEN-LAST:event_txtBuscarClienteKeyReleased

    private void txtBuscarClientePorDniKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarClientePorDniKeyReleased
        limpiarTabla();
        buscarClientesPorDni(txtBuscarClientePorDni.getText());
    }//GEN-LAST:event_txtBuscarClientePorDniKeyReleased

    private void btnNuevaVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaVentaActionPerformed
        limpiarTabla();
        listarProductosParaVenta();
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_btnNuevaVentaActionPerformed

    private void txtBuscarProductoPorDescKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProductoPorDescKeyReleased
        limpiarTabla();
        listarProductosParaVentaPorDescripcion(txtBuscarProductoPorDesc.getText());
    }//GEN-LAST:event_txtBuscarProductoPorDescKeyReleased

    private void txtBuscarProvPorDniKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProvPorDniKeyReleased
        limpiarTabla();
        buscarProveedoresPorDni(txtBuscarProvPorDni.getText());
    }//GEN-LAST:event_txtBuscarProvPorDniKeyReleased

    private void txtBuscarProvPorNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProvPorNombreKeyReleased
        limpiarTabla();
        buscarProveedoresPorNombre(txtBuscarProvPorNombre.getText());
    }//GEN-LAST:event_txtBuscarProvPorNombreKeyReleased

    private void txtBuscarProdPorCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProdPorCodigoKeyReleased
        limpiarTabla();
        buscarProductosPorCodigo(txtBuscarProdPorCodigo.getText());
    }//GEN-LAST:event_txtBuscarProdPorCodigoKeyReleased

    private void txtBuscarProdPorDescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProdPorDescripcionKeyReleased
        limpiarTabla();
        buscarProductosPorDescripcion(txtBuscarProdPorDescripcion.getText());
    }//GEN-LAST:event_txtBuscarProdPorDescripcionKeyReleased

    private void tblVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVentaMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tblVentaMouseClicked

    private void tblProductosVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosVentaMouseClicked
        int fila = tblProductosVenta.rowAtPoint(evt.getPoint());
        
        idProducto = tblProductosVenta.getValueAt(fila, 0).toString();
        codigoAux = tblProductosVenta.getValueAt(fila, 1).toString();
        descripcionAux = tblProductosVenta.getValueAt(fila, 2).toString();
        precioAux = tblProductosVenta.getValueAt(fila, 3).toString();
        existenciaAux = tblProductosVenta.getValueAt(fila, 4).toString();
    }//GEN-LAST:event_tblProductosVentaMouseClicked

    private void btnAgregarProdParaVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProdParaVentaActionPerformed
         if("".equals(descripcionAux)){
            JOptionPane.showMessageDialog(null, "Seleccione un producto de la tabla");
         } else{
             int idP = Integer.parseInt(idProducto);
             String codigo = codigoAux;
             String descripcion = descripcionAux;
             Double precio = Double.parseDouble(precioAux);
             int existencia = Integer.parseInt(existenciaAux);
             
             item = item + 1;
             modelo2 = (DefaultTableModel) tblVenta.getModel();
             
             ArrayList lista = new ArrayList();
             lista.add(item);
             lista.add(idP);
             lista.add(codigo);
             lista.add(descripcion);
             lista.add(precio);
             lista.add(existencia);
             
             Object[] obj = new Object[6];
             obj[0] = lista.get(1);
             obj[1] = lista.get(2);
             obj[2] = lista.get(3);
             obj[3] = lista.get(4);
             obj[4] = 0;
             obj[5] = 0;
             modelo2.addRow(obj);
             tblVenta.setModel(modelo2);
             
             codigoAux = "";
             descripcionAux = "";
             precioAux = "";
             existenciaAux = "";
             
             txtBuscarProductoPorDesc.requestFocus();
         }
         
    }//GEN-LAST:event_btnAgregarProdParaVentaActionPerformed

    private void tblVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblVentaKeyPressed
        calcularSubtotal();
        totalPagar();
    }//GEN-LAST:event_tblVentaKeyPressed

    private void tblVentaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblVentaKeyReleased
        calcularSubtotal();
        totalPagar();
    }//GEN-LAST:event_tblVentaKeyReleased

    private void btnEmininarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmininarVentaActionPerformed
        modelo2 = (DefaultTableModel) tblVenta.getModel();
        modelo2.removeRow(tblVenta.getSelectedRow());
        totalPagar();
        txtBuscarProductoPorDesc.requestFocus();
    }//GEN-LAST:event_btnEmininarVentaActionPerformed

    private void txtDniCuitVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniCuitVentaKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){   //Verificamos que la tecla enter fue seleccionada
            if(!"".equals(txtDniCuitVenta.getText())){   //Verificamos que el campo dni no este vacio
                long dni_cuit = Long.parseLong(txtDniCuitVenta.getText());
                cl = clDao.buscarClienteParaVenta(dni_cuit);
                if(cl.getNombre() != null){   //Verificamos si trae resultado
                    txtNombreClienteVenta.setText(""+cl.getNombre());
                    txtTelefonoClienteVenta.setText(""+cl.getTelefono());
                    txtDireccionClienteVenta.setText(""+cl.getDireccion());
                    txtCorreoClienteVenta.setText(""+cl.getCorreo());
                } else {
                    JOptionPane.showMessageDialog(null, "El cliente no existe");
                    txtDniCuitVenta.setText("");
                }
            }
        }
    }//GEN-LAST:event_txtDniCuitVentaKeyPressed

    private void btnGenerarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarVentaActionPerformed
        //Verificamos que no haya subtotales en cero en la tabla venta
        boolean verificarSubtotal = verificarPrecioSubtotalCero();
        if(verificarSubtotal == true){
            JOptionPane.showMessageDialog(null, "No puede haber subtotales en 0 (cero), por favor cargue la cantidad a vender", "VENTAS", JOptionPane.WARNING_MESSAGE);
        }else{
            registrarVenta();
            JOptionPane.showMessageDialog(null, "Venta realizada con exito", "VENTAS", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_btnGenerarVentaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sistema().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarCliente;
    private javax.swing.JButton btnActualizarEmpresa;
    private javax.swing.JButton btnActualizarProd;
    private javax.swing.JButton btnActualizarProveedor;
    private javax.swing.JButton btnAgregarProdParaVenta;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnConfig;
    private javax.swing.JButton btnEliminarCliente;
    private javax.swing.JButton btnEliminarProd;
    private javax.swing.JButton btnEliminarProveedor;
    private javax.swing.JButton btnEmininarVenta;
    private javax.swing.JButton btnGenerarVenta;
    private javax.swing.JButton btnGuardarCliente;
    private javax.swing.JButton btnGuardarProd;
    private javax.swing.JButton btnGuardarProveedor;
    private javax.swing.JButton btnImprimirExcel;
    private javax.swing.JButton btnNuevaVenta;
    private javax.swing.JButton btnNuevoCliente;
    private javax.swing.JButton btnNuevoProd;
    private javax.swing.JButton btnNuevoProveedor;
    private javax.swing.JButton btnPdfVentas;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnVentas;
    private javax.swing.JComboBox<String> cmbProveedor;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblTotalPagar;
    private javax.swing.JLabel lblVendedor;
    private javax.swing.JTable tblCliente;
    private javax.swing.JTable tblProducto;
    private javax.swing.JTable tblProductosVenta;
    private javax.swing.JTable tblProveedor;
    private javax.swing.JTable tblVenta;
    private javax.swing.JTable tblVentas;
    private javax.swing.JTextField txtBuscarCliente;
    private javax.swing.JTextField txtBuscarClientePorDni;
    private javax.swing.JTextField txtBuscarProdPorCodigo;
    private javax.swing.JTextField txtBuscarProdPorDescripcion;
    private javax.swing.JTextField txtBuscarProductoPorDesc;
    private javax.swing.JTextField txtBuscarProvPorDni;
    private javax.swing.JTextField txtBuscarProvPorNombre;
    private javax.swing.JTextField txtCantidadProd;
    private javax.swing.JTextField txtCodigoProd;
    private javax.swing.JTextField txtCorreoCliente;
    private javax.swing.JTextField txtCorreoClienteVenta;
    private javax.swing.JTextField txtCorreoEmpresa;
    private javax.swing.JTextField txtCorreoProveedor;
    private javax.swing.JTextField txtCuitEmpresa;
    private javax.swing.JTextField txtDescripcionProd;
    private javax.swing.JTextField txtDireccionCliente;
    private javax.swing.JTextField txtDireccionClienteVenta;
    private javax.swing.JTextField txtDireccionEmpresa;
    private javax.swing.JTextField txtDireccionProveedor;
    private javax.swing.JTextField txtDniCuitCliente;
    private javax.swing.JTextField txtDniCuitProveedor;
    private javax.swing.JTextField txtDniCuitVenta;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtIdProd;
    private javax.swing.JTextField txtIdProducto;
    private javax.swing.JTextField txtIdProveedor;
    private javax.swing.JTextField txtIdVenta;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNombreClienteVenta;
    private javax.swing.JTextField txtNombreEmpresa;
    private javax.swing.JTextField txtNombreProveedor;
    private javax.swing.JTextField txtPrecioCostoProd;
    private javax.swing.JTextField txtPrecioVentaProd;
    private javax.swing.JTextField txtRazonCliente;
    private javax.swing.JTextField txtRazonEmpresa;
    private javax.swing.JTextField txtRazonProveedor;
    private javax.swing.JTextField txtTelefonoCliente;
    private javax.swing.JTextField txtTelefonoClienteVenta;
    private javax.swing.JTextField txtTelefonoEmpresa;
    private javax.swing.JTextField txtTelefonoProveedor;
    private javax.swing.JTextField txtTotalPagar;
    // End of variables declaration//GEN-END:variables
}
