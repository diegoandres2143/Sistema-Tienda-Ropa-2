/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diego.tiendaropa2.controlador;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.diego.tiendaropa2.modelo.Cliente;
import com.diego.tiendaropa2.modelo.DetalleVenta;
import com.diego.tiendaropa2.modelo.Producto;
import com.diego.tiendaropa2.modelo.Venta;
import com.diego.tiendaropa2.servicio.ClienteServicio;
import com.diego.tiendaropa2.servicio.ProductoServicio;
import com.diego.tiendaropa2.servicio.VentaServicio;
/**
 *
 * @author USUARIO
 */
public class ControladorVentas {
    private final VentaServicio ventaServicio;
    private final ClienteServicio clienteServicio;
    private final ProductoServicio productoServicio;
    
    private final JComboBox<String> comboCliente;
    private final JComboBox<String> comboProducto;
    private final JTextField txtCantidad;
    private final JTextField txtFecha;
    private final JTextField txtTotal;
    private final JTable tblDetalles;
    private final JTextArea txtAvisos;
    
    private List<DetalleVenta> carrito;
    private double totalVenta;

    public ControladorVentas(JComboBox<String> comboCliente,
                              JComboBox<String> comboProducto,
                              JTextField txtCantidad,
                              JTextField txtFecha,
                              JTextField txtTotal,
                              JTable tblDetalles,
                              JTextArea txtAvisos) {
        this.ventaServicio = new VentaServicio();
        this.clienteServicio = new ClienteServicio();
        this.productoServicio = new ProductoServicio();
        this.comboCliente = comboCliente;
        this.comboProducto = comboProducto;
        this.txtCantidad = txtCantidad;
        this.txtFecha = txtFecha;
        this.txtTotal = txtTotal;
        this.tblDetalles = tblDetalles;
        this.txtAvisos = txtAvisos;
        this.carrito = new ArrayList<>();
        this.totalVenta = 0.0;
        
        // Mostrar fecha actual
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        txtFecha.setText(sdf.format(new Date()));
    }

    public void cargarClientesEnCombo() {
        try {
            comboCliente.removeAllItems();
            List<Cliente> clientes = clienteServicio.listarClientes();
            for (Cliente c : clientes) {
                comboCliente.addItem(c.getCedula() + " - " + c.getNombre());
            }
        } catch (Exception ex) {
            mostrarAviso("Error al cargar clientes: " + ex.getMessage());
        }
    }

    public void cargarProductosEnCombo() {
        try {
            comboProducto.removeAllItems();
            List<Producto> productos = productoServicio.listarProductos();
            for (Producto p : productos) {
                comboProducto.addItem(p.getCodigo() + " - " + p.getNombre() + " (Stock: " + p.getStock() + ")");
            }
        } catch (Exception ex) {
            mostrarAviso("Error al cargar productos: " + ex.getMessage());
        }
    }

    public void agregarProductoAlCarrito() {
        try {
            String selected = (String) comboProducto.getSelectedItem();
            if (selected == null) {
                mostrarAviso("Selecciona un producto.");
                return;
            }
            
            String codigo = selected.split(" - ")[0];
            int cantidad = Integer.parseInt(txtCantidad.getText().trim());
            
            if (cantidad <= 0) {
                mostrarAviso("La cantidad debe ser mayor a cero.");
                return;
            }
            
            // Buscar producto completo
            Producto producto = productoServicio.listarProductos().stream()
                .filter(p -> p.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
            
            if (producto == null) {
                mostrarAviso("Producto no encontrado.");
                return;
            }
            
            if (producto.getStock() < cantidad) {
                mostrarAviso("Stock insuficiente. Stock disponible: " + producto.getStock());
                return;
            }
            
            // Crear detalle
            double subtotal = producto.getPrecio() * cantidad;
            DetalleVenta detalle = new DetalleVenta(codigo, cantidad, subtotal);
            carrito.add(detalle);
            totalVenta += subtotal;
            
            actualizarTablaCarrito();
            actualizarTotal();
            txtCantidad.setText("");
            mostrarAviso("Producto agregado al carrito.");
            
        } catch (NumberFormatException ex) {
            mostrarAviso("Error: Cantidad debe ser un número válido.");
        } catch (Exception ex) {
            mostrarAviso("Error: " + ex.getMessage());
        }
    }

    public void quitarProductoDelCarrito() {
        int fila = tblDetalles.getSelectedRow();
        if (fila >= 0) {
            DetalleVenta detalle = carrito.get(fila);
            totalVenta -= detalle.getSubtotal();
            carrito.remove(fila);
            actualizarTablaCarrito();
            actualizarTotal();
            mostrarAviso("Producto eliminado del carrito.");
        } else {
            mostrarAviso("Selecciona un producto para quitar.");
        }
    }

    public void registrarVenta() {
        try {
            if (carrito.isEmpty()) {
                mostrarAviso("Agrega al menos un producto a la venta.");
                return;
            }
            
            String selectedCliente = (String) comboCliente.getSelectedItem();
            if (selectedCliente == null) {
                mostrarAviso("Selecciona un cliente.");
                return;
            }
            
            String cedulaCliente = selectedCliente.split(" - ")[0];
            
            Venta venta = new Venta();
            venta.setClienteCedula(cedulaCliente);
            venta.setFecha(new Date());
            venta.setTotal(totalVenta);
            venta.setDetalles(new ArrayList<>(carrito));
            
            ventaServicio.registrarVenta(venta);
            mostrarAviso("Venta registrada exitosamente.");
            
            // Limpiar carrito y total
            carrito.clear();
            totalVenta = 0.0;
            actualizarTablaCarrito();
            actualizarTotal();
            
            // Recargar productos (para actualizar stock en combo)
            cargarProductosEnCombo();
            
        } catch (Exception ex) {
            mostrarAviso("Error al registrar venta: " + ex.getMessage());
        }
    }

    public void limpiarCarrito() {
        carrito.clear();
        totalVenta = 0.0;
        actualizarTablaCarrito();
        actualizarTotal();
        mostrarAviso("Carrito limpiado.");
    }

    private void actualizarTablaCarrito() {
        DefaultTableModel model = (DefaultTableModel) tblDetalles.getModel();
        model.setRowCount(0);
        
        for (DetalleVenta det : carrito) {
            try {
                // Obtener nombre del producto
                Producto p = productoServicio.listarProductos().stream()
                    .filter(prod -> prod.getCodigo().equals(det.getProductoCodigo()))
                    .findFirst()
                    .orElse(null);
                String nombre = (p != null) ? p.getNombre() : "Desconocido";
                double precio = (p != null) ? p.getPrecio() : 0.0;
                
                model.addRow(new Object[]{
                    det.getProductoCodigo(),
                    nombre,
                    det.getCantidad(),
                    precio,
                    det.getSubtotal()
                });
            } catch (Exception ex) {
                // Si hay error, mostrar básico
                model.addRow(new Object[]{
                    det.getProductoCodigo(),
                    "?",
                    det.getCantidad(),
                    0.0,
                    det.getSubtotal()
                });
            }
        }
    }

    private void actualizarTotal() {
        txtTotal.setText(String.format("$%.2f", totalVenta));
    }

    private void mostrarAviso(String msg) {
        txtAvisos.setText(msg);
    }
}
