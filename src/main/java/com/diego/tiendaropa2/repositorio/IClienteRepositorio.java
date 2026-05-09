/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.diego.tiendaropa2.repositorio;

import com.diego.tiendaropa2.modelo.Cliente;
import java.util.List;
import java.sql.SQLException;
/**
 *
 * @author USUARIO
 */
public interface IClienteRepositorio {
    void crear(Cliente cliente) throws SQLException;
    List<Cliente> listar() throws SQLException;
    void actualizar(Cliente cliente) throws SQLException;
    void eliminar(String cedula) throws SQLException;
    Cliente buscarPorCedula(String cedula) throws SQLException;
}
