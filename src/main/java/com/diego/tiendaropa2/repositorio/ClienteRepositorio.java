/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diego.tiendaropa2.repositorio;

import com.diego.tiendaropa2.conexion.Conexion;
import com.diego.tiendaropa2.modelo.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class ClienteRepositorio implements IClienteRepositorio {
    @Override
    public void crear(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientes (cedula, nombre, correo) VALUES (?, ?, ?)";
        try (Connection con = Conexion.getConexion(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cliente.getCedula());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getCorreo());
            ps.executeUpdate();
        }
    }

    @Override
    public List<Cliente> listar() throws SQLException {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Cliente c = new Cliente(rs.getString("cedula"), rs.getString("nombre"), rs.getString("correo"));
                lista.add(c);
            }
        }
        return lista;
    }
    
    @Override
    public void actualizar(Cliente cliente) throws SQLException {
        String sql = "UPDATE clientes SET nombre = ?, correo = ? WHERE cedula = ?";
        try (Connection con = Conexion.getConexion(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getCorreo());
            ps.setString(3, cliente.getCedula());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(String cedula) throws SQLException {
        String sql = "DELETE FROM clientes WHERE cedula = ?";
        try (Connection con = Conexion.getConexion(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cedula);
            ps.executeUpdate();
        }
    }

    @Override
    public Cliente buscarPorCedula(String cedula) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE cedula = ?";
        try (Connection con = Conexion.getConexion(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cedula);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                        rs.getString("cedula"), 
                        rs.getString("nombre"), 
                        rs.getString("correo")
                    );
                }
            }
        }
        return null;
    }
}
