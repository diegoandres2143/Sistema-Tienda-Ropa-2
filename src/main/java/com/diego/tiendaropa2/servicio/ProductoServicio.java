/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diego.tiendaropa2.servicio;

import java.sql.SQLException;
import java.util.List;

import com.diego.tiendaropa2.modelo.DetalleVenta;
import com.diego.tiendaropa2.modelo.Producto;
import com.diego.tiendaropa2.repositorio.IProductoRepositorio;
import com.diego.tiendaropa2.repositorio.ProductoRepositorio;
/**
 *
 * @author USUARIO
 */
public class ProductoServicio {
    private final IProductoRepositorio productoRepo;

    public ProductoServicio() {
        this.productoRepo = new ProductoRepositorio();
    }


    public void guardarProducto(Producto p) throws Exception {
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

    public void editarProducto(Producto p) throws Exception {
        if (p.getCodigo() == null || p.getCodigo().trim().isEmpty()) {
            throw new Exception("El código no puede estar vacío.");
        }
        if (p.getPrecio() <= 0) {
            throw new Exception("El precio debe ser un valor positivo.");
        }
        if (p.getStock() < 0) {
            throw new Exception("No puedes tener stock negativo.");
        }
        productoRepo.actualizar(p);
    }

    public void eliminarProducto(String codigo) throws Exception {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new Exception("El código no puede estar vacío.");
        }
        // Validar que el producto no tenga ventas asociadas
        VentaServicio ventaServicio = new VentaServicio();
        boolean tieneVentas = false;
        for (var venta : ventaServicio.listarVentas()) {
            List<DetalleVenta> detalles = venta.getDetalles();
            if (detalles == null) detalles = java.util.Collections.emptyList();
            for (DetalleVenta det : detalles) {
                if (codigo.equals(det.getProductoCodigo())) {
                    tieneVentas = true;
                    break;
                }
            }
            if (tieneVentas) break;
        }
        if (tieneVentas) {
            throw new Exception("No se puede eliminar el producto porque está asociado a ventas registradas.");
        }
        productoRepo.eliminar(codigo);
    }

    public List<Producto> listarProductos() throws SQLException {
        return productoRepo.listar();
    }
}
