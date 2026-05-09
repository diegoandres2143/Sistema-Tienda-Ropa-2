/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diego.tiendaropa2.servicio;

import java.util.List;

import com.diego.tiendaropa2.modelo.Producto;
import com.diego.tiendaropa2.modelo.Venta;
import com.diego.tiendaropa2.repositorio.ProductoRepositorio;
import com.diego.tiendaropa2.repositorio.VentaRepositorio;

public class VentaServicio {
    private final VentaRepositorio ventaRepo;
    private final ProductoRepositorio productoRepo;

    public VentaServicio() {
        this.ventaRepo = new VentaRepositorio();
        this.productoRepo = new ProductoRepositorio();
    }
    
    public void guardarVenta(Venta venta, List<com.diego.tiendaropa2.modelo.DetalleVenta> detalles) throws Exception {
        // Asignar detalles a la venta
        venta.setDetalles(detalles);
        registrarVenta(venta);
    }

    public void registrarVenta(Venta venta) throws Exception {
        // 1. Validar existencia y stock de todos los productos
        double total = 0.0;
        for (var detalle : venta.getDetalles()) {
            Producto producto = productoRepo.buscarPorCodigo(detalle.getProductoCodigo());
            if (producto == null) {
                throw new Exception("El producto con código " + detalle.getProductoCodigo() + " no existe.");
            }
            if (producto.getStock() < detalle.getCantidad()) {
                throw new Exception("Stock insuficiente para el producto " + producto.getNombre() + ". Solo quedan: " + producto.getStock());
            }
            // Calcular subtotal y sumarlo al total
            double subtotal = producto.getPrecio() * detalle.getCantidad();
            detalle.setSubtotal(subtotal);
            total += subtotal;
        }
        venta.setTotal(total);

        // 2. Guardar la venta y sus detalles
        ventaRepo.registrarVenta(venta);

        // 3. Actualizar stock de todos los productos vendidos
        for (var detalle : venta.getDetalles()) {
            Producto producto = productoRepo.buscarPorCodigo(detalle.getProductoCodigo());
            int nuevoStock = producto.getStock() - detalle.getCantidad();
            producto.setStock(nuevoStock);
            productoRepo.actualizar(producto);
        }
    }
    public void editarVenta(Venta venta) throws Exception {
        if (venta.getId() <= 0) {
            throw new Exception("ID de venta inválido para editar.");
        }
        ventaRepo.editarVenta(venta);
    }

    public void eliminarVenta(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID de venta inválido para eliminar.");
        }
        ventaRepo.eliminarVenta(id);
    }

    public List<Venta> listarVentas() throws Exception {
        return ventaRepo.listarVentas();
    }
}
