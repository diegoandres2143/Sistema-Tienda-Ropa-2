/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diego.tiendaropa2.repositorio;

import com.diego.tiendaropa2.conexion.Conexion;
import com.diego.tiendaropa2.modelo.Venta;
import com.diego.tiendaropa2.modelo.DetalleVenta;
import java.sql.*;
/**
 *
 * @author USUARIO
 */
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
}
