/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diego.tiendaropa2.repositorio;

import com.diego.tiendaropa2.conexion.Conexion;
import com.diego.tiendaropa2.modelo.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author USUARIO
 */
public class ProductoRepositorio implements IProductoRepositorio {
    @Override
    public void crear(Producto p) throws SQLException {
        String sql = "INSERT INTO productos (codigo, nombre, talla, color, precio, stock) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getCodigo());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getTalla());
            ps.setString(4, p.getColor());
            ps.setDouble(5, p.getPrecio());
            ps.setInt(6, p.getStock());
            ps.executeUpdate();
        }
    }

    @Override
    public Producto buscarPorCodigo(String codigo) throws SQLException {
        String sql = "SELECT * FROM productos WHERE codigo = ?";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Producto(rs.getString("codigo"), rs.getString("nombre"), 
                                        rs.getString("talla"), rs.getString("color"), 
                                        rs.getDouble("precio"), rs.getInt("stock"));
                }
            }
        }
        return null;
    }
    
    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Producto p = new Producto(
                    rs.getString("codigo"),
                    rs.getString("nombre"),
                    rs.getString("talla"),
                    rs.getString("color"),
                    rs.getDouble("precio"),
                    rs.getInt("stock")
                );
                lista.add(p);
            }
        }
        return lista;
    }

    @Override
    public void actualizar(Producto p) throws SQLException {
        String sql = "UPDATE productos SET nombre = ?, talla = ?, color = ?, precio = ?, stock = ? WHERE codigo = ?";
        try (Connection con = Conexion.getConexion(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getTalla());
            ps.setString(3, p.getColor());
            ps.setDouble(4, p.getPrecio());
            ps.setInt(5, p.getStock());
            ps.setString(6, p.getCodigo());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(String codigo) throws SQLException {
        String sql = "DELETE FROM productos WHERE codigo = ?";
        try (Connection con = Conexion.getConexion(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codigo);
            ps.executeUpdate();
        }
    }
}
