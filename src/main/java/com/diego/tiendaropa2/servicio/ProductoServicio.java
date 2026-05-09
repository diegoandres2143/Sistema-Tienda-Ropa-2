/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diego.tiendaropa2.servicio;

import com.diego.tiendaropa2.modelo.Producto;
import com.diego.tiendaropa2.repositorio.IProductoRepositorio;
import com.diego.tiendaropa2.repositorio.ProductoRepositorio;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author USUARIO
 */
public class ProductoServicio {
    private final IProductoRepositorio productoRepo;

    public ProductoServicio() {
        this.productoRepo = new ProductoRepositorio();
    }

    public void registrarProducto(Producto p) throws Exception {
        // VALIDACIÓN: Precio lógico
        if (p.getPrecio() <= 0) {
            throw new Exception("El precio debe ser un valor positivo.");
        }

        // VALIDACIÓN: Stock no negativo
        if (p.getStock() < 0) {
            throw new Exception("No puedes iniciar con stock negativo.");
        }

        productoRepo.crear(p);
    }

    public List<Producto> listarTodo() throws SQLException {
        return productoRepo.listar();
    }
}
