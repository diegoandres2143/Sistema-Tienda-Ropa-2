/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.diego.tiendaropa2.repositorio;

import com.diego.tiendaropa2.modelo.Producto;
import java.util.List;
import java.sql.SQLException;
/**
 *
 * @author USUARIO
 */
public interface IProductoRepositorio {
    void crear(Producto producto) throws SQLException;
    List<Producto> listar() throws SQLException;
    void actualizar(Producto producto) throws SQLException;
    void eliminar(String codigo) throws SQLException;
    Producto buscarPorCodigo(String codigo) throws SQLException;
}
