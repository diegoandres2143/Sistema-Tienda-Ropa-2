/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.diego.tiendaropa2.repositorio;

import com.diego.tiendaropa2.modelo.Venta;
import java.sql.SQLException;

/**
 *
 * @author USUARIO
 */
public interface IVentaRepositorio {
    void registrarVenta(Venta venta) throws SQLException;
}