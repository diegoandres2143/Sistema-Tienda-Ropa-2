/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diego.tiendaropa2.servicio;

import java.sql.SQLException;
import java.util.List;

import com.diego.tiendaropa2.modelo.Cliente;
import com.diego.tiendaropa2.repositorio.ClienteRepositorio;
import com.diego.tiendaropa2.repositorio.IClienteRepositorio;
/**
 *
 * @author USUARIO
 */
public class ClienteServicio {
    private final IClienteRepositorio clienteRepo;
    
    public ClienteServicio() {
        this.clienteRepo = new ClienteRepositorio();
    }

    public void guardarCliente(Cliente cliente) throws Exception {
        if (cliente.getCedula() == null || cliente.getCedula().trim().isEmpty()) {
            throw new Exception("La cédula no puede estar vacía.");
        }

        if (cliente.getNombre().length() < 3) {
            throw new Exception("El nombre debe tener al menos 3 caracteres.");
        }

        String email = cliente.getCorreo();
        if (email == null || !email.contains("@") ||
            !(email.endsWith(".com") || email.endsWith(".co") || email.endsWith(".edu"))) {
            throw new Exception("El correo debe ser válido, contener una '@' y terminar en .com, .edu ó .co");
        }

        // Validar que la cédula no exista ya
        if (clienteRepo.buscarPorCedula(cliente.getCedula()) != null) {
            throw new Exception("Ya existe un cliente con esa cédula.");
        }

        clienteRepo.crear(cliente);
    }

    public List<Cliente> listarClientes() throws SQLException {
        return clienteRepo.listar();
    }

    public void eliminarCliente(String cedula) throws Exception {
        if (cedula == null || cedula.trim().isEmpty()) {
            throw new Exception("La cédula no puede estar vacía.");
        }
        // Validar que el cliente no tenga ventas asociadas
        VentaServicio ventaServicio = new VentaServicio();
        boolean tieneVentas = false;
        for (var venta : ventaServicio.listarVentas()) {
            if (cedula.equals(venta.getClienteCedula())) {
                tieneVentas = true;
                break;
            }
        }
        if (tieneVentas) {
            throw new Exception("No se puede eliminar el cliente porque tiene ventas registradas.");
        }
        clienteRepo.eliminar(cedula);
    }

    public void editarCliente(Cliente cliente) throws Exception {
        if (cliente.getCedula() == null || cliente.getCedula().trim().isEmpty()) {
            throw new Exception("La cédula no puede estar vacía.");
        }
        if (cliente.getNombre().length() < 3) {
            throw new Exception("El nombre debe tener al menos 3 caracteres.");
        }
        String email = cliente.getCorreo();
        if (email == null || !email.contains("@") || !(email.endsWith(".com") || email.endsWith(".co") || email.endsWith(".edu"))) {
            throw new Exception("El correo debe ser válido, contener una '@' y terminar en .com, .edu ó .co");
        }
        clienteRepo.actualizar(cliente);
    }
}
