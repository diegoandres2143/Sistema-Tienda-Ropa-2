/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.diego.tiendaropa2.vista;

import javax.swing.table.DefaultTableCellRenderer;

import com.diego.tiendaropa2.controlador.ControladorClientes;
import com.diego.tiendaropa2.controlador.ControladorProductos;
import com.diego.tiendaropa2.controlador.ControladorVentas;

/**
 *
 * @author USUARIO
 */
public class VistaPrincipal extends javax.swing.JFrame {
        // Métodos de eventos requeridos por referencias en addActionListener
        public void txtProductoColorActionPerformed(java.awt.event.ActionEvent evt) {
            // No hace nada por ahora
        }

        public void comboVentaClienteActionPerformed(java.awt.event.ActionEvent evt) {
            // No hace nada por ahora
        }

        public void comboVentaProductoActionPerformed(java.awt.event.ActionEvent evt) {
            // No hace nada por ahora
        }

        public void txtVentaFechaActionPerformed(java.awt.event.ActionEvent evt) {
            // No hace nada por ahora
        }
    private ControladorClientes controladorClientes;
    private ControladorProductos controladorProductos;
    private ControladorVentas controladorVentas;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VistaPrincipal.class.getName());

    /**
     * Creates new form VistaPrincipal
     */
    public VistaPrincipal() {
        initComponents();
        
        // ------------------ CONFIGURACIÓN DE LA TABLA HISTÓRICA ------------------
    tblHistoricoVentaDetalles.setModel(new javax.swing.table.DefaultTableModel(
        new Object[][]{},
        new String[]{"ID", "Cliente", "Cédula", "Fecha", "Total"}
    ) {
        Class<?>[] types = new Class<?>[]{Integer.class, String.class, String.class, String.class, String.class};
        boolean[] canEdit = new boolean[]{false, false, false, false, false};

        @Override
        public Class getColumnClass(int columnIndex) {
            return types[columnIndex];
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit[columnIndex];
        }
    });

    // Alinear columna Total a la derecha
    DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
    rightRenderer.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    tblHistoricoVentaDetalles.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);

    // Ajustar anchos preferidos
    tblHistoricoVentaDetalles.getColumnModel().getColumn(0).setPreferredWidth(50);
    tblHistoricoVentaDetalles.getColumnModel().getColumn(1).setPreferredWidth(150);
    tblHistoricoVentaDetalles.getColumnModel().getColumn(2).setPreferredWidth(100);
    tblHistoricoVentaDetalles.getColumnModel().getColumn(3).setPreferredWidth(130);
    tblHistoricoVentaDetalles.getColumnModel().getColumn(4).setPreferredWidth(100);

    // Configurar campo de total acumulado
    txtHistoricoVentaTotal.setEditable(false);
    txtHistoricoVentaTotal.setBackground(new java.awt.Color(240, 240, 240));
    txtHistoricoVentaTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
    txtHistoricoVentaTotal.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
    txtHistoricoVentaTotal.setPreferredSize(new java.awt.Dimension(120, 28));
        // Forzar tamaño del campo cantidad
        txtVentaCantidad.setColumns(10);  // Esto le da un ancho basado en caracteres
        txtVentaCantidad.setMinimumSize(new java.awt.Dimension(80, 28));
        txtVentaCantidad.setPreferredSize(new java.awt.Dimension(100, 28));
        // Inicializar el controlador de clientes con los componentes de la vista
        controladorClientes = new ControladorClientes(
            tblClientes,
            txtClienteCedula,
            txtClienteNombre,
            txtClienteCorreo,
            txtClienteAvisos
        );
        controladorClientes.cargarTablaClientes();
        
        // Inicializar el controlador de productos
        controladorProductos = new ControladorProductos(
            tblProductos,
            txtProductoCodigo,
            txtProductoNombre,
            txtProductoTalla,
            txtProductoColor,
            txtProductoPrecio,
            txtProductoStock,
            txtProductoAvisos
        );
        controladorProductos.cargarTablaProductos();
        
                // Inicializar el controlador de ventas
        controladorVentas = new ControladorVentas(
            comboVentaCliente,
            comboVentaProducto,
            txtVentaCantidad,
            txtVentaFecha,
            txtVentaTotal,
            tblVentaDetalles,
            tblHistoricoVentaDetalles,   // nuevo parámetro
            txtHistoricoVentaTotal,      // nuevo parámetro
            txtVentaAvisos
        );
        controladorVentas.cargarClientesEnCombo();
        controladorVentas.cargarProductosEnCombo();
        controladorVentas.cargarHistorialVentas();  // carga inicial del historial


    // Métodos de eventos requeridos por referencias en addActionListener
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        tabMenuNavegacion = new javax.swing.JTabbedPane();
        tabCanal = new javax.swing.JPanel();
        btnClienteAgregar = new javax.swing.JButton();
        btnClienteLimpiar = new javax.swing.JButton();
        btnClienteEliminar = new javax.swing.JButton();
        txtClienteCedula = new javax.swing.JTextField();
        lblClienteCedula = new javax.swing.JLabel();
        txtClienteNombre = new javax.swing.JTextField();
        lblClienteNombre = new javax.swing.JLabel();
        lblClienteCorreo = new javax.swing.JLabel();
        txtClienteCorreo = new javax.swing.JTextField();
        lblClientes = new javax.swing.JLabel();
        lblListadoClientes = new javax.swing.JLabel();
        btnClienteEditar = new javax.swing.JButton();
        lblClienteAvisosTitulo = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtClienteAvisos = new javax.swing.JTextArea();
        jScrollPane11 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        tabProductos = new javax.swing.JPanel();
        txtProductoPrecio = new javax.swing.JTextField();
        lblProductoPrecio = new javax.swing.JLabel();
        lblVistaProductosTitulo = new javax.swing.JLabel();
        txtProductoCodigo = new javax.swing.JTextField();
        lblProductoCodigo = new javax.swing.JLabel();
        txtProductoNombre = new javax.swing.JTextField();
        lblProductoNombre = new javax.swing.JLabel();
        lblProductoTalla = new javax.swing.JLabel();
        txtProductoTalla = new javax.swing.JTextField();
        lblProductoCantidad = new javax.swing.JLabel();
        txtProductoStock = new javax.swing.JTextField();
        lblListadoProductos = new javax.swing.JLabel();
        lblProductoColor = new javax.swing.JLabel();
        txtProductoColor = new javax.swing.JTextField();
        btnProductoAgregar = new javax.swing.JButton();
        btnProductoEditar = new javax.swing.JButton();
        btnProductoEliminar = new javax.swing.JButton();
        btnProductoLimpiar = new javax.swing.JButton();
        lblProductoAvisosTitulo = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtProductoAvisos = new javax.swing.JTextArea();
        jScrollPane10 = new javax.swing.JScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        tabVentas = new javax.swing.JPanel();
        lblVistaVentasTitulo = new javax.swing.JLabel();
        lblVentaCliente = new javax.swing.JLabel();
        lblFechaVenta = new javax.swing.JLabel();
        lblVentaProducto = new javax.swing.JLabel();
        lblVentaCantidad = new javax.swing.JLabel();
        btnVentaAgregar = new javax.swing.JButton();
        comboVentaCliente = new javax.swing.JComboBox<>();
        lblVentaFecha = new javax.swing.JLabel();
        comboVentaProducto = new javax.swing.JComboBox<>();
        txtVentaFecha = new javax.swing.JTextField();
        txtVentaCantidad = new javax.swing.JTextField();
        btnVentaQuitar = new javax.swing.JButton();
        btnVentaRegistrar = new javax.swing.JButton();
        btnVentaLimpiar = new javax.swing.JButton();
        lblListadoVentas = new javax.swing.JLabel();
        lblVentaTotal = new javax.swing.JLabel();
        txtVentaTotal = new javax.swing.JTextField();
        lblVentaAvisosTitulo = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtVentaAvisos = new javax.swing.JTextArea();
        lblListadoHistoricoVentas = new javax.swing.JLabel();
        lblHistoricoVentaTotal = new javax.swing.JLabel();
        txtHistoricoVentaTotal = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblVentaDetalles = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblHistoricoVentaDetalles = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabMenuNavegacion.setToolTipText("");
        tabMenuNavegacion.setName(""); // NOI18N

        btnClienteAgregar.setText("Agregar");
        btnClienteAgregar.addActionListener(this::btnClienteAgregarActionPerformed);

        btnClienteLimpiar.setText("Limpiar");
        btnClienteLimpiar.addActionListener(this::btnClienteLimpiarActionPerformed);

        btnClienteEliminar.setText("Eliminar");
        btnClienteEliminar.addActionListener(this::btnClienteEliminarActionPerformed);

        lblClienteCedula.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblClienteCedula.setText("Cédula:");

        lblClienteNombre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblClienteNombre.setText("Nombre:");

        lblClienteCorreo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblClienteCorreo.setText("Correo:");

        lblClientes.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        lblClientes.setText("Gestion de Clientes");

        lblListadoClientes.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        lblListadoClientes.setText("Listado Clientes");

        btnClienteEditar.setText("Editar");

        lblClienteAvisosTitulo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblClienteAvisosTitulo.setText("Avisos:");

        txtClienteAvisos.setEditable(false);
        txtClienteAvisos.setColumns(20);
        txtClienteAvisos.setRows(5);
        jScrollPane4.setViewportView(txtClienteAvisos);

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Cédula", "Nombre", "Correo"
            }
        ));
        tblClientes.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(tblClientes);
        tblClientes.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jScrollPane11.setViewportView(jScrollPane1);

        javax.swing.GroupLayout tabCanalLayout = new javax.swing.GroupLayout(tabCanal);
        tabCanal.setLayout(tabCanalLayout);
        tabCanalLayout.setHorizontalGroup(
            tabCanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabCanalLayout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addComponent(lblClientes)
                .addContainerGap(328, Short.MAX_VALUE))
            .addGroup(tabCanalLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(tabCanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(tabCanalLayout.createSequentialGroup()
                        .addGroup(tabCanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblClienteCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblClienteNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblClienteCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tabCanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtClienteNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtClienteCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtClienteCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(70, 70, 70)
                        .addGroup(tabCanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnClienteAgregar)
                            .addComponent(btnClienteEditar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tabCanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnClienteEliminar)
                            .addComponent(btnClienteLimpiar)))
                    .addComponent(lblListadoClientes)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tabCanalLayout.createSequentialGroup()
                        .addComponent(lblClienteAvisosTitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        tabCanalLayout.setVerticalGroup(
            tabCanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabCanalLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblClientes)
                .addGap(36, 36, 36)
                .addGroup(tabCanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtClienteCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblClienteCedula)
                    .addComponent(btnClienteAgregar)
                    .addComponent(btnClienteEliminar))
                .addGroup(tabCanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabCanalLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabCanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtClienteNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblClienteNombre))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabCanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtClienteCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblClienteCorreo)))
                    .addGroup(tabCanalLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(tabCanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnClienteLimpiar)
                            .addComponent(btnClienteEditar))))
                .addGap(51, 51, 51)
                .addComponent(lblListadoClientes)
                .addGap(34, 34, 34)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addGroup(tabCanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblClienteAvisosTitulo)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
        );

        tabMenuNavegacion.addTab("Clientes", tabCanal);

        lblProductoPrecio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblProductoPrecio.setText("Precio:");

        lblVistaProductosTitulo.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        lblVistaProductosTitulo.setText("Gestion de Productos");

        lblProductoCodigo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblProductoCodigo.setText("Código:");

        lblProductoNombre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblProductoNombre.setText("Nombre:");

        lblProductoTalla.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblProductoTalla.setText("Talla:");

        lblProductoCantidad.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblProductoCantidad.setText("Stock:");

        lblListadoProductos.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        lblListadoProductos.setText("Listado de Productos");

        lblProductoColor.setText("Color:");

        txtProductoColor.addActionListener(this::txtProductoColorActionPerformed);

        btnProductoAgregar.setText("Agregar");
        btnProductoAgregar.addActionListener(this::btnProductoAgregarActionPerformed);

        btnProductoEditar.setText("Editar");

        btnProductoEliminar.setText("Eliminar");
        btnProductoEliminar.addActionListener(this::btnProductoEliminarActionPerformed);

        btnProductoLimpiar.setText("Limpiar");
        btnProductoLimpiar.addActionListener(this::btnProductoLimpiarActionPerformed);

        lblProductoAvisosTitulo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblProductoAvisosTitulo.setText("Avisos:");

        txtProductoAvisos.setEditable(false);
        txtProductoAvisos.setColumns(20);
        txtProductoAvisos.setRows(5);
        jScrollPane2.setViewportView(txtProductoAvisos);

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Nombre", "Talla", "Color", "Precio", "Stock"
            }
        ));
        tblProductos.setColumnSelectionAllowed(true);
        jScrollPane3.setViewportView(tblProductos);
        tblProductos.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jScrollPane10.setViewportView(jScrollPane3);

        javax.swing.GroupLayout tabProductosLayout = new javax.swing.GroupLayout(tabProductos);
        tabProductos.setLayout(tabProductosLayout);
        tabProductosLayout.setHorizontalGroup(
            tabProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabProductosLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblVistaProductosTitulo)
                .addGap(237, 237, 237))
            .addGroup(tabProductosLayout.createSequentialGroup()
                .addGroup(tabProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabProductosLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblListadoProductos))
                    .addGroup(tabProductosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(tabProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblProductoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblProductoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(tabProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblProductoColor)
                                .addComponent(lblProductoTalla, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblProductoPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblProductoCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tabProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(tabProductosLayout.createSequentialGroup()
                                .addComponent(txtProductoStock, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(tabProductosLayout.createSequentialGroup()
                                .addGroup(tabProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtProductoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtProductoColor, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtProductoPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtProductoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtProductoTalla, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                                .addGroup(tabProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnProductoAgregar)
                                    .addComponent(btnProductoEliminar))
                                .addGap(28, 28, 28)
                                .addGroup(tabProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnProductoEditar)
                                    .addComponent(btnProductoLimpiar))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(tabProductosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabProductosLayout.createSequentialGroup()
                        .addComponent(jScrollPane10)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabProductosLayout.createSequentialGroup()
                        .addGap(0, 11, Short.MAX_VALUE)
                        .addComponent(lblProductoAvisosTitulo)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))))
        );
        tabProductosLayout.setVerticalGroup(
            tabProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabProductosLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(lblVistaProductosTitulo)
                .addGroup(tabProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabProductosLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(tabProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtProductoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblProductoCodigo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtProductoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblProductoNombre))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtProductoTalla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblProductoTalla))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtProductoColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblProductoColor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtProductoPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblProductoPrecio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtProductoStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblProductoCantidad)))
                    .addGroup(tabProductosLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(tabProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnProductoAgregar)
                            .addComponent(btnProductoEditar))
                        .addGap(18, 18, 18)
                        .addGroup(tabProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnProductoEliminar)
                            .addComponent(btnProductoLimpiar))))
                .addGap(18, 18, 18)
                .addComponent(lblListadoProductos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addGroup(tabProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProductoAvisosTitulo))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        tabMenuNavegacion.addTab("Productos", tabProductos);

        lblVistaVentasTitulo.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        lblVistaVentasTitulo.setText("Gestion de Ventas");

        lblVentaCliente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblVentaCliente.setText("Cliente:");

        lblVentaProducto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblVentaProducto.setText("Producto:");

        lblVentaCantidad.setText("Cantidad:");

        btnVentaAgregar.setText("Agregar");
        btnVentaAgregar.addActionListener(this::btnVentaAgregarActionPerformed);

        comboVentaCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboVentaCliente.addActionListener(this::comboVentaClienteActionPerformed);

        lblVentaFecha.setText("Fecha:");

        comboVentaProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboVentaProducto.addActionListener(this::comboVentaProductoActionPerformed);

        txtVentaFecha.setEditable(false);
        txtVentaFecha.addActionListener(this::txtVentaFechaActionPerformed);

        txtVentaCantidad.setColumns(1);

        btnVentaQuitar.setText("Quitar");
        btnVentaQuitar.addActionListener(this::btnVentaQuitarActionPerformed);

        btnVentaRegistrar.setText("Registrar");
        btnVentaRegistrar.addActionListener(this::btnVentaRegistrarActionPerformed);

        btnVentaLimpiar.setText("Limpiar");
        btnVentaLimpiar.addActionListener(this::btnVentaLimpiarActionPerformed);

        lblListadoVentas.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblListadoVentas.setText("Carrito de Compras");

        lblVentaTotal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblVentaTotal.setText("Total:");

        txtVentaTotal.setEditable(false);
        txtVentaTotal.setText("$0.00");
        txtVentaTotal.addActionListener(this::txtVentaTotalActionPerformed);

        lblVentaAvisosTitulo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblVentaAvisosTitulo.setText("Avisos:");

        txtVentaAvisos.setEditable(false);
        txtVentaAvisos.setColumns(20);
        txtVentaAvisos.setRows(5);
        jScrollPane7.setViewportView(txtVentaAvisos);

        lblListadoHistoricoVentas.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblListadoHistoricoVentas.setText("Listado Histórico de Ventas");

        lblHistoricoVentaTotal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblHistoricoVentaTotal.setText("Total Acumulado:");

        txtHistoricoVentaTotal.setEditable(false);
        txtHistoricoVentaTotal.setText("$0.00");
        txtHistoricoVentaTotal.addActionListener(this::txtHistoricoVentaTotalActionPerformed);

        tblVentaDetalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código", "Nombre", "Cantidad", "Precio", "Subtotal"
            }
        ));
        tblVentaDetalles.setColumnSelectionAllowed(true);
        jScrollPane5.setViewportView(tblVentaDetalles);
        tblVentaDetalles.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jScrollPane6.setViewportView(jScrollPane5);

        tblHistoricoVentaDetalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Cliente", "Cédula", "Fecha", "Subtotal"
            }
        ));
        jScrollPane8.setViewportView(tblHistoricoVentaDetalles);
        tblHistoricoVentaDetalles.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jScrollPane9.setViewportView(jScrollPane8);

        javax.swing.GroupLayout tabVentasLayout = new javax.swing.GroupLayout(tabVentas);
        tabVentas.setLayout(tabVentasLayout);
        tabVentasLayout.setHorizontalGroup(
            tabVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabVentasLayout.createSequentialGroup()
                .addGroup(tabVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tabVentasLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblVistaVentasTitulo)
                        .addGap(255, 255, 255))
                    .addGroup(tabVentasLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(lblVentaAvisosTitulo)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane7))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tabVentasLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(tabVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane9)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(tabVentasLayout.createSequentialGroup()
                                .addGroup(tabVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(tabVentasLayout.createSequentialGroup()
                                        .addComponent(lblVentaCliente)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(comboVentaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(tabVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(tabVentasLayout.createSequentialGroup()
                                                .addGap(124, 124, 124)
                                                .addComponent(lblFechaVenta))
                                            .addGroup(tabVentasLayout.createSequentialGroup()
                                                .addGap(24, 24, 24)
                                                .addComponent(lblVentaProducto)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(comboVentaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(tabVentasLayout.createSequentialGroup()
                                        .addComponent(lblVentaCantidad)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtVentaCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblVentaFecha)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtVentaFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                                .addGroup(tabVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnVentaAgregar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnVentaRegistrar, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(tabVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnVentaLimpiar, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnVentaQuitar, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(tabVentasLayout.createSequentialGroup()
                                .addGroup(tabVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblListadoVentas)
                                    .addComponent(lblListadoHistoricoVentas))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(24, 24, 24))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabVentasLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(tabVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabVentasLayout.createSequentialGroup()
                        .addComponent(lblHistoricoVentaTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtHistoricoVentaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabVentasLayout.createSequentialGroup()
                        .addComponent(lblVentaTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtVentaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))))
        );
        tabVentasLayout.setVerticalGroup(
            tabVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabVentasLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblVistaVentasTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblFechaVenta)
                .addGap(16, 16, 16)
                .addGroup(tabVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVentaCliente)
                    .addComponent(comboVentaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVentaProducto)
                    .addComponent(comboVentaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVentaAgregar)
                    .addComponent(btnVentaQuitar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tabVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVentaCantidad)
                    .addComponent(txtVentaCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVentaFecha)
                    .addComponent(txtVentaFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVentaRegistrar)
                    .addComponent(btnVentaLimpiar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(lblListadoVentas)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(tabVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVentaTotal)
                    .addComponent(txtVentaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(lblListadoHistoricoVentas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(tabVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHistoricoVentaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHistoricoVentaTotal))
                .addGap(45, 45, 45)
                .addGroup(tabVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVentaAvisosTitulo))
                .addGap(27, 27, 27))
        );

        tabMenuNavegacion.addTab("Ventas", tabVentas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabMenuNavegacion)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabMenuNavegacion)
        );

        pack();
    }// </editor-fold>                        

    private void txtHistoricoVentaTotalActionPerformed(java.awt.event.ActionEvent evt) {                                                       
        // TODO add your handling code here:
    }                                                                                                      

    private void txtVentaTotalActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    // ==================== EVENTOS CLIENTES ====================
    private void btnClienteAgregarActionPerformed(java.awt.event.ActionEvent evt) {
        controladorClientes.agregarCliente();
    }

    private void btnClienteEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        controladorClientes.eliminarCliente();
    }

    private void btnClienteLimpiarActionPerformed(java.awt.event.ActionEvent evt) {
        controladorClientes.limpiarCampos();
        controladorClientes.mostrarAviso("");
    }

    private void btnClienteEditarActionPerformed(java.awt.event.ActionEvent evt) {
        controladorClientes.editarCliente();
    }

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {
        controladorClientes.cargarDatosClienteSeleccionado();
    }

    // ==================== EVENTOS PRODUCTOS ====================
    private void btnProductoAgregarActionPerformed(java.awt.event.ActionEvent evt) {
        controladorProductos.agregarProducto();
    }

    private void btnProductoEditarActionPerformed(java.awt.event.ActionEvent evt) {
        controladorProductos.editarProducto();
    }

    private void btnProductoEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        controladorProductos.eliminarProducto();
    }

    private void btnProductoLimpiarActionPerformed(java.awt.event.ActionEvent evt) {
        controladorProductos.limpiarCampos();
        controladorProductos.mostrarAviso("");
    }

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {
        controladorProductos.cargarDatosProductoSeleccionado();
    }

    // ==================== EVENTOS VENTAS ====================
    private void btnVentaAgregarActionPerformed(java.awt.event.ActionEvent evt) {
        controladorVentas.agregarProductoAlCarrito();
    }

    private void btnVentaQuitarActionPerformed(java.awt.event.ActionEvent evt) {
        controladorVentas.quitarProductoDelCarrito();
    }

    private void btnVentaRegistrarActionPerformed(java.awt.event.ActionEvent evt) {
        controladorVentas.registrarVenta();
    }

    private void btnVentaLimpiarActionPerformed(java.awt.event.ActionEvent evt) {
        controladorVentas.limpiarCarrito();
    }

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new VistaPrincipal().setVisible(true));
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnClienteAgregar;
    private javax.swing.JButton btnClienteEditar;
    private javax.swing.JButton btnClienteEliminar;
    private javax.swing.JButton btnClienteLimpiar;
    private javax.swing.JButton btnProductoAgregar;
    private javax.swing.JButton btnProductoEditar;
    private javax.swing.JButton btnProductoEliminar;
    private javax.swing.JButton btnProductoLimpiar;
    private javax.swing.JButton btnVentaAgregar;
    private javax.swing.JButton btnVentaLimpiar;
    private javax.swing.JButton btnVentaQuitar;
    private javax.swing.JButton btnVentaRegistrar;
    private javax.swing.JComboBox<String> comboVentaCliente;
    private javax.swing.JComboBox<String> comboVentaProducto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblClienteAvisosTitulo;
    private javax.swing.JLabel lblClienteCedula;
    private javax.swing.JLabel lblClienteCorreo;
    private javax.swing.JLabel lblClienteNombre;
    private javax.swing.JLabel lblClientes;
    private javax.swing.JLabel lblFechaVenta;
    private javax.swing.JLabel lblHistoricoVentaTotal;
    private javax.swing.JLabel lblListadoClientes;
    private javax.swing.JLabel lblListadoHistoricoVentas;
    private javax.swing.JLabel lblListadoProductos;
    private javax.swing.JLabel lblListadoVentas;
    private javax.swing.JLabel lblProductoAvisosTitulo;
    private javax.swing.JLabel lblProductoCantidad;
    private javax.swing.JLabel lblProductoCodigo;
    private javax.swing.JLabel lblProductoColor;
    private javax.swing.JLabel lblProductoNombre;
    private javax.swing.JLabel lblProductoPrecio;
    private javax.swing.JLabel lblProductoTalla;
    private javax.swing.JLabel lblVentaAvisosTitulo;
    private javax.swing.JLabel lblVentaCantidad;
    private javax.swing.JLabel lblVentaCliente;
    private javax.swing.JLabel lblVentaFecha;
    private javax.swing.JLabel lblVentaProducto;
    private javax.swing.JLabel lblVentaTotal;
    private javax.swing.JLabel lblVistaProductosTitulo;
    private javax.swing.JLabel lblVistaVentasTitulo;
    private javax.swing.JPanel tabCanal;
    private javax.swing.JTabbedPane tabMenuNavegacion;
    private javax.swing.JPanel tabProductos;
    private javax.swing.JPanel tabVentas;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTable tblHistoricoVentaDetalles;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTable tblVentaDetalles;
    private javax.swing.JTextArea txtClienteAvisos;
    private javax.swing.JTextField txtClienteCedula;
    private javax.swing.JTextField txtClienteCorreo;
    private javax.swing.JTextField txtClienteNombre;
    private javax.swing.JTextField txtHistoricoVentaTotal;
    private javax.swing.JTextArea txtProductoAvisos;
    private javax.swing.JTextField txtProductoCodigo;
    private javax.swing.JTextField txtProductoColor;
    private javax.swing.JTextField txtProductoNombre;
    private javax.swing.JTextField txtProductoPrecio;
    private javax.swing.JTextField txtProductoStock;
    private javax.swing.JTextField txtProductoTalla;
    private javax.swing.JTextArea txtVentaAvisos;
    private javax.swing.JTextField txtVentaCantidad;
    private javax.swing.JTextField txtVentaFecha;
    private javax.swing.JTextField txtVentaTotal;
    // End of variables declaration                   
}