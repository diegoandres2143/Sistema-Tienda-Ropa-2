/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.diego.tiendaropa2.repositorio;

import java.sql.SQLException;

import com.diego.tiendaropa2.modelo.Venta;

/**
 *
 * @author USUARIO
 */
public interface IVentaRepositorio {
    void registrarVenta(Venta venta) throws SQLException;
    void editarVenta(Venta venta) throws SQLException;
    void eliminarVenta(int id) throws SQLException;
    java.util.List<Venta> listarVentas() throws SQLException;
}