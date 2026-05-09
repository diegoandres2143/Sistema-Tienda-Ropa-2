/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diego.tiendaropa2.modelo;

/**
 *
 * @author USUARIO
 */
public class DetalleVenta {
    private String productoCodigo;
    private int cantidad;
    private double subtotal;

    public DetalleVenta() {}

    public DetalleVenta(String productoCodigo, int cantidad, double subtotal) {
        this.productoCodigo = productoCodigo;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public String getProductoCodigo() {
        return productoCodigo;
    }

    public void setProductoCodigo(String productoCodigo) {
        this.productoCodigo = productoCodigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "DetalleVenta{" + "productoCodigo=" + productoCodigo + ", cantidad=" + cantidad + ", subtotal=" + subtotal + '}';
    }
    
}
