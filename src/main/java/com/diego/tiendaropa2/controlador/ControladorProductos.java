/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diego.tiendaropa2.controlador;

import java.util.List;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.diego.tiendaropa2.modelo.Producto;
import com.diego.tiendaropa2.servicio.ProductoServicio;

/**
 *
 * @author USUARIO
 */
public class ControladorProductos {
    private final ProductoServicio productoServicio;
    private final JTable tblProductos;
    private final JTextField txtCodigo;
    private final JTextField txtNombre;
    private final JTextField txtTalla;
    private final JTextField txtColor;
    private final JTextField txtPrecio;
    private final JTextField txtStock;
    private final JTextArea txtAvisos;

    public ControladorProductos(JTable tblProductos, 
                                 JTextField txtCodigo,
                                 JTextField txtNombre,
                                 JTextField txtTalla,
                                 JTextField txtColor,
                                 JTextField txtPrecio,
                                 JTextField txtStock,
                                 JTextArea txtAvisos) {
        this.productoServicio = new ProductoServicio();
        this.tblProductos = tblProductos;
        this.txtCodigo = txtCodigo;
        this.txtNombre = txtNombre;
        this.txtTalla = txtTalla;
        this.txtColor = txtColor;
        this.txtPrecio = txtPrecio;
        this.txtStock = txtStock;
        this.txtAvisos = txtAvisos;
    }

    public void cargarTablaProductos() {
        try {
            List<Producto> productos = productoServicio.listarProductos();
            DefaultTableModel model = (DefaultTableModel) tblProductos.getModel();
            model.setRowCount(0);
            for (Producto p : productos) {
                model.addRow(new Object[]{
                    p.getCodigo(), 
                    p.getNombre(), 
                    p.getTalla(), 
                    p.getColor(), 
                    p.getPrecio(), 
                    p.getStock()
                });
            }
        } catch (Exception ex) {
            mostrarAviso("Error al cargar productos: " + ex.getMessage());
        }
    }

    public void agregarProducto() {
        try {
            Producto p = new Producto();
            p.setCodigo(txtCodigo.getText().trim());
            p.setNombre(txtNombre.getText().trim());
            p.setTalla(txtTalla.getText().trim());
            p.setColor(txtColor.getText().trim());
            p.setPrecio(Double.parseDouble(txtPrecio.getText().trim()));
            p.setStock(Integer.parseInt(txtStock.getText().trim()));
            
            productoServicio.guardarProducto(p);
            mostrarAviso("Producto agregado correctamente.");
            cargarTablaProductos();
            limpiarCampos();
        } catch (NumberFormatException ex) {
            mostrarAviso("Error: Precio y Stock deben ser números válidos.");
        } catch (Exception ex) {
            mostrarAviso("Error: " + ex.getMessage());
        }
    }

    public void editarProducto() {
        int fila = tblProductos.getSelectedRow();
        if (fila >= 0) {
            try {
                String codigo = tblProductos.getValueAt(fila, 0).toString();
                Producto p = new Producto();
                p.setCodigo(codigo);
                p.setNombre(txtNombre.getText().trim());
                p.setTalla(txtTalla.getText().trim());
                p.setColor(txtColor.getText().trim());
                p.setPrecio(Double.parseDouble(txtPrecio.getText().trim()));
                p.setStock(Integer.parseInt(txtStock.getText().trim()));
                
                productoServicio.editarProducto(p);
                mostrarAviso("Producto editado correctamente.");
                cargarTablaProductos();
                limpiarCampos();
            } catch (NumberFormatException ex) {
                mostrarAviso("Error: Precio y Stock deben ser números válidos.");
            } catch (Exception ex) {
                mostrarAviso("Error: " + ex.getMessage());
            }
        } else {
            mostrarAviso("Selecciona un producto para editar.");
        }
    }

    public void eliminarProducto() {
        int fila = tblProductos.getSelectedRow();
        if (fila >= 0) {
            String codigo = tblProductos.getValueAt(fila, 0).toString();
            try {
                productoServicio.eliminarProducto(codigo);
                mostrarAviso("Producto eliminado correctamente.");
                cargarTablaProductos();
                limpiarCampos();
            } catch (Exception ex) {
                mostrarAviso("Error: " + ex.getMessage());
            }
        } else {
            mostrarAviso("Selecciona un producto para eliminar.");
        }
    }

    public void cargarDatosProductoSeleccionado() {
        int fila = tblProductos.getSelectedRow();
        if (fila >= 0) {
            txtCodigo.setText(tblProductos.getValueAt(fila, 0).toString());
            txtNombre.setText(tblProductos.getValueAt(fila, 1).toString());
            txtTalla.setText(tblProductos.getValueAt(fila, 2).toString());
            txtColor.setText(tblProductos.getValueAt(fila, 3).toString());
            txtPrecio.setText(tblProductos.getValueAt(fila, 4).toString());
            txtStock.setText(tblProductos.getValueAt(fila, 5).toString());
        }
    }

    public void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtTalla.setText("");
        txtColor.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
    }

    public void mostrarAviso(String msg) {
        txtAvisos.setText(msg);
    }
}
