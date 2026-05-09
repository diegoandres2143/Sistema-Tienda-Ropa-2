/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diego.tiendaropa2.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.diego.tiendaropa2.conexion.Conexion;
import com.diego.tiendaropa2.modelo.DetalleVenta;
import com.diego.tiendaropa2.modelo.Venta;

public class VentaRepositorio implements IVentaRepositorio {
    @Override
    public void registrarVenta(Venta venta) throws SQLException {
        String sqlVenta = "INSERT INTO ventas (cliente_cedula, total) VALUES (?, ?) RETURNING id";
        String sqlDetalle = "INSERT INTO detalle_ventas (venta_id, producto_codigo, cantidad, subtotal) VALUES (?, ?, ?, ?)";
        
        Connection con = null;
        try {
            con = Conexion.getConexion();
            con.setAutoCommit(false);

            int ventaId = 0;
            try (PreparedStatement psV = con.prepareStatement(sqlVenta)) {
                psV.setString(1, venta.getClienteCedula());
                psV.setDouble(2, venta.getTotal());
                ResultSet rs = psV.executeQuery();
                if (rs.next()) ventaId = rs.getInt(1);
            }

            try (PreparedStatement psD = con.prepareStatement(sqlDetalle)) {
                for (DetalleVenta det : venta.getDetalles()) {
                    psD.setInt(1, ventaId);
                    psD.setString(2, det.getProductoCodigo());
                    psD.setInt(3, det.getCantidad());
                    psD.setDouble(4, det.getSubtotal());
                    psD.addBatch();
                }
                psD.executeBatch();
            }

            con.commit(); 
        } catch (SQLException e) {
            if (con != null) con.rollback(); 
            throw e;
        } finally {
            if (con != null) con.close();
        }
    }
    @Override
    public void editarVenta(Venta venta) throws SQLException {
        // Solo permite editar el total y el cliente, no los detalles ni la fecha
        String sql = "UPDATE ventas SET cliente_cedula = ?, total = ? WHERE id = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, venta.getClienteCedula());
            ps.setDouble(2, venta.getTotal());
            ps.setInt(3, venta.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminarVenta(int id) throws SQLException {
        // Eliminar detalles primero por FK
        String sqlDetalle = "DELETE FROM detalle_ventas WHERE venta_id = ?";
        String sqlVenta = "DELETE FROM ventas WHERE id = ?";
        try (Connection con = Conexion.getConexion()) {
            try (PreparedStatement psD = con.prepareStatement(sqlDetalle)) {
                psD.setInt(1, id);
                psD.executeUpdate();
            }
            try (PreparedStatement psV = con.prepareStatement(sqlVenta)) {
                psV.setInt(1, id);
                psV.executeUpdate();
            }
        }
    }

    @Override
    public List<Venta> listarVentas() throws SQLException {
        List<Venta> lista = new ArrayList<>();
        String sql = "SELECT id, cliente_cedula, fecha, total FROM ventas";
        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Venta v = new Venta();
                v.setId(rs.getInt("id"));
                v.setClienteCedula(rs.getString("cliente_cedula"));
                v.setFecha(rs.getTimestamp("fecha"));
                v.setTotal(rs.getDouble("total"));
                // No se cargan detalles aquí para simplificar
                lista.add(v);
            }
        }
        return lista;
    }
}
