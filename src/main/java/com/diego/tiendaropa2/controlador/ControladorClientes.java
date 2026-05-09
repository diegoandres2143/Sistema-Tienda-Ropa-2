package com.diego.tiendaropa2.controlador;

import java.util.List;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.diego.tiendaropa2.modelo.Cliente;
import com.diego.tiendaropa2.servicio.ClienteServicio;

public class ControladorClientes {
        public void editarCliente() {
            int fila = tblClientes.getSelectedRow();
            if (fila >= 0) {
                String cedula = tblClientes.getValueAt(fila, 0).toString();
                String nombre = txtNombre.getText().trim();
                String correo = txtCorreo.getText().trim();
                try {
                    Cliente cliente = new Cliente();
                    cliente.setCedula(cedula);
                    cliente.setNombre(nombre);
                    cliente.setCorreo(correo);
                    clienteServicio.editarCliente(cliente);
                    mostrarAviso("Cliente editado correctamente.");
                    cargarTablaClientes();
                    limpiarCampos();
                } catch (Exception ex) {
                    mostrarAviso("Error: " + ex.getMessage());
                }
            } else {
                mostrarAviso("Selecciona un cliente para editar.");
            }
        }
    private final ClienteServicio clienteServicio;
    private final JTable tblClientes;
    private final JTextField txtCedula;
    private final JTextField txtNombre;
    private final JTextField txtCorreo;
    private final JTextArea txtAvisos;

    public ControladorClientes(JTable tblClientes, JTextField txtCedula, JTextField txtNombre, JTextField txtCorreo, JTextArea txtAvisos) {
        this.clienteServicio = new ClienteServicio();
        this.tblClientes = tblClientes;
        this.txtCedula = txtCedula;
        this.txtNombre = txtNombre;
        this.txtCorreo = txtCorreo;
        this.txtAvisos = txtAvisos;
    }

    public void cargarTablaClientes() {
        try {
            List<Cliente> clientes = clienteServicio.listarClientes();
            DefaultTableModel model = (DefaultTableModel) tblClientes.getModel();
            model.setRowCount(0);
            for (Cliente c : clientes) {
                model.addRow(new Object[]{c.getCedula(), c.getNombre(), c.getCorreo()});
            }
        } catch (Exception ex) {
            mostrarAviso("Error al cargar clientes: " + ex.getMessage());
        }
    }

    public void cargarDatosClienteSeleccionado() {
        int fila = tblClientes.getSelectedRow();
        if (fila >= 0) {
            txtCedula.setText(tblClientes.getValueAt(fila, 0).toString());
            txtNombre.setText(tblClientes.getValueAt(fila, 1).toString());
            txtCorreo.setText(tblClientes.getValueAt(fila, 2).toString());
        }
    }

    public void agregarCliente() {
        String cedula = txtCedula.getText().trim();
        String nombre = txtNombre.getText().trim();
        String correo = txtCorreo.getText().trim();
        try {
            Cliente cliente = new Cliente();
            cliente.setCedula(cedula);
            cliente.setNombre(nombre);
            cliente.setCorreo(correo);
            clienteServicio.guardarCliente(cliente);
            mostrarAviso("Cliente agregado correctamente.");
            cargarTablaClientes();
            limpiarCampos();
        } catch (Exception ex) {
            mostrarAviso("Error: " + ex.getMessage());
        }
    }

    public void eliminarCliente() {
        int fila = tblClientes.getSelectedRow();
        if (fila >= 0) {
            String cedula = tblClientes.getValueAt(fila, 0).toString();
            try {
                clienteServicio.eliminarCliente(cedula);
                mostrarAviso("Cliente eliminado correctamente.");
                cargarTablaClientes();
                limpiarCampos();
            } catch (Exception ex) {
                mostrarAviso("Error: " + ex.getMessage());
            }
        } else {
            mostrarAviso("Selecciona un cliente para eliminar.");
        }
    }

    public void limpiarCampos() {
        txtCedula.setText("");
        txtNombre.setText("");
        txtCorreo.setText("");
    }

    public void mostrarAviso(String msg) {
        txtAvisos.setText(msg);
    }
}
