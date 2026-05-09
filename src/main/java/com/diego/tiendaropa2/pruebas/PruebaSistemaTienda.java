/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.diego.tiendaropa2.pruebas;

import com.diego.tiendaropa2.conexion.Conexion;
import com.diego.tiendaropa2.modelo.Cliente;
import com.diego.tiendaropa2.modelo.DetalleVenta;
import com.diego.tiendaropa2.modelo.Producto;
import com.diego.tiendaropa2.modelo.Venta;
import com.diego.tiendaropa2.servicio.ClienteServicio;
import com.diego.tiendaropa2.servicio.ProductoServicio;
import com.diego.tiendaropa2.servicio.VentaServicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Clase de prueba unitaria para validar la funcionalidad completa del sistema.
 * Al finalizar, limpia automáticamente todos los registros de prueba.
 * 
 * @author USUARIO
 */
public class PruebaSistemaTienda {
    
    private static ClienteServicio clienteServicio;
    private static ProductoServicio productoServicio;
    private static VentaServicio ventaServicio;
    
    // IDs o identificadores de los registros creados para poder limpiarlos
    private static String[] cedulasCreadas;
    private static String[] codigosCreados;
    private static Integer[] idsVentasCreadas;
    
    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════");
        System.out.println("   INICIANDO PRUEBAS DEL SISTEMA");
        System.out.println("═══════════════════════════════════════════\n");
        
        // Verificar conexión a Neon
        System.out.println("🔌 Verificando conexión a Neon...");
        try (Connection con = Conexion.getConexion()) {
            if (con != null && !con.isClosed()) {
                System.out.println("✅ ¡CONEXIÓN EXITOSA! Base de datos lista.\n");
            }
        } catch (SQLException e) {
            System.err.println("❌ ERROR de conexión: " + e.getMessage());
            return;
        }
        
        // Inicializar servicios
        clienteServicio = new ClienteServicio();
        productoServicio = new ProductoServicio();
        ventaServicio = new VentaServicio();
        
        // Arrays para almacenar los IDs de los registros creados
        cedulasCreadas = new String[3];
        codigosCreados = new String[3];
        idsVentasCreadas = new Integer[3];
        
        try {
            // ========== PRUEBA 1: CREAR CLIENTES ==========
            System.out.println("📝 PRUEBA 1: Creando 3 clientes...");
            Cliente[] clientes = new Cliente[] {
                new Cliente("9999999991", "Prueba Uno", "prueba1@correo.com"),
                new Cliente("9999999992", "Prueba Dos", "prueba2@correo.com"),
                new Cliente("9999999993", "Prueba Tres", "prueba3@correo.com")
            };
            
            for (int i = 0; i < clientes.length; i++) {
                clienteServicio.guardarCliente(clientes[i]);
                cedulasCreadas[i] = clientes[i].getCedula();
                System.out.println("   ✅ Cliente creado: " + clientes[i].getNombre() + " (Cédula: " + clientes[i].getCedula() + ")");
            }
            
            // Listar clientes
            System.out.println("\n📋 Listado de clientes actual:");
            for (Cliente c : clienteServicio.listarClientes()) {
                System.out.println("   - " + c);
            }
            
            // ========== PRUEBA 2: CREAR PRODUCTOS ==========
            System.out.println("\n📝 PRUEBA 2: Creando 3 productos...");
            Producto[] productos = new Producto[] {
                new Producto("TEST-001", "Producto Prueba 1", "M", "Rojo", 15000.0, 50),
                new Producto("TEST-002", "Producto Prueba 2", "L", "Azul", 25000.0, 30),
                new Producto("TEST-003", "Producto Prueba 3", "S", "Verde", 35000.0, 20)
            };
            
            for (int i = 0; i < productos.length; i++) {
                productoServicio.guardarProducto(productos[i]);
                codigosCreados[i] = productos[i].getCodigo();
                System.out.println("   ✅ Producto creado: " + productos[i].getNombre() + " (Código: " + productos[i].getCodigo() + ")");
            }
            
            // Listar productos
            System.out.println("\n📋 Listado de productos actual:");
            for (Producto p : productoServicio.listarProductos()) {
                System.out.println("   - " + p);
            }
            
            // ========== PRUEBA 3: CREAR VENTAS ==========
            System.out.println("\n📝 PRUEBA 3: Creando 3 ventas...");
            for (int i = 0; i < 3; i++) {
                Venta venta = new Venta();
                venta.setClienteCedula(clientes[i].getCedula());
                venta.setFecha(new java.util.Date());
                
                java.util.List<DetalleVenta> detalles = new java.util.ArrayList<>();
                double totalVenta = 0;
                
                // Cada venta tendrá 2 detalles
                for (int j = 0; j < 2; j++) {
                    DetalleVenta det = new DetalleVenta();
                    Producto prod = productos[(i + j) % 3];
                    det.setProductoCodigo(prod.getCodigo());
                    det.setCantidad(1 + j);
                    double subtotal = prod.getPrecio() * (1 + j);
                    det.setSubtotal(subtotal);
                    totalVenta += subtotal;
                    detalles.add(det);
                }
                
                venta.setTotal(totalVenta);
                ventaServicio.guardarVenta(venta, detalles);
                System.out.println("   ✅ Venta creada para cliente: " + clientes[i].getNombre() + " - Total: $" + totalVenta);
            }
            
            // Listar ventas
            System.out.println("\n📋 Listado de ventas actual:");
            java.util.List<Venta> ventas = ventaServicio.listarVentas();
            for (int i = 0; i < ventas.size(); i++) {
                Venta v = ventas.get(i);
                idsVentasCreadas[i] = v.getId();
                System.out.println("   - Venta ID: " + v.getId() + " | Cliente: " + v.getClienteCedula() + " | Total: $" + v.getTotal());
            }
            
            // ========== PRUEBA 4: EDITAR CLIENTE ==========
            System.out.println("\n📝 PRUEBA 4: Editando un cliente...");
            Cliente clienteEditar = clientes[1];
            clienteEditar.setNombre("Prueba Dos Modificado");
            clienteEditar.setCorreo("modificado@correo.com");
            clienteServicio.editarCliente(clienteEditar);
            System.out.println("   ✅ Cliente editado: " + clienteEditar);
            
            // ========== PRUEBA 5: EDITAR PRODUCTO ==========
            System.out.println("\n📝 PRUEBA 5: Editando un producto...");
            Producto productoEditar = productos[1];
            productoEditar.setPrecio(27500.0);
            productoEditar.setStock(25);
            productoServicio.editarProducto(productoEditar);
            System.out.println("   ✅ Producto editado: " + productoEditar);
            
            // ========== PRUEBA 6: ELIMINAR VENTA ==========
            System.out.println("\n📝 PRUEBA 6: Eliminando la primera venta...");
            if (ventas.size() > 0) {
                int idEliminar = ventas.get(0).getId();
                ventaServicio.eliminarVenta(idEliminar);
                System.out.println("   ✅ Venta con ID " + idEliminar + " eliminada correctamente.");
                idsVentasCreadas[0] = null; // Marcar como eliminada
            }
            
            // ========== PRUEBA 7: ELIMINAR CLIENTE ==========
            System.out.println("\n📝 PRUEBA 7: Eliminando el primer cliente...");
            clienteServicio.eliminarCliente(clientes[0].getCedula());
            System.out.println("   ✅ Cliente " + clientes[0].getNombre() + " eliminado correctamente.");
            cedulasCreadas[0] = null; // Marcar como eliminada
            
            // ========== PRUEBA 8: ELIMINAR PRODUCTO ==========
            System.out.println("\n📝 PRUEBA 8: Eliminando el primer producto...");
            productoServicio.eliminarProducto(productos[0].getCodigo());
            System.out.println("   ✅ Producto " + productos[0].getNombre() + " eliminado correctamente.");
            codigosCreados[0] = null; // Marcar como eliminado
            
            // ========== RESULTADO FINAL ==========
            System.out.println("\n═══════════════════════════════════════════");
            System.out.println("   📊 ESTADO FINAL DEL SISTEMA");
            System.out.println("═══════════════════════════════════════════");
            
            System.out.println("\n📋 Clientes en la base de datos:");
            for (Cliente c : clienteServicio.listarClientes()) {
                System.out.println("   - " + c);
            }
            
            System.out.println("\n📋 Productos en la base de datos:");
            for (Producto p : productoServicio.listarProductos()) {
                System.out.println("   - " + p);
            }
            
            System.out.println("\n📋 Ventas en la base de datos:");
            for (Venta v : ventaServicio.listarVentas()) {
                System.out.println("   - Venta ID: " + v.getId() + " | Cliente: " + v.getClienteCedula() + " | Total: $" + v.getTotal());
            }
            
            System.out.println("\n✅ ¡TODAS LAS PRUEBAS COMPLETADAS CON ÉXITO!");
            
        } catch (Exception e) {
            System.err.println("\n❌ ERROR en las pruebas: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // ========== LIMPIEZA AUTOMÁTICA ==========
            System.out.println("\n═══════════════════════════════════════════");
            System.out.println("   🧹 LIMPIANDO REGISTROS DE PRUEBA");
            System.out.println("═══════════════════════════════════════════");
            limpiarRegistrosPrueba();
        }
    }
    
    /**
     * Limpia todos los registros creados durante la prueba
     */
    private static void limpiarRegistrosPrueba() {
        try (Connection con = Conexion.getConexion()) {
            con.setAutoCommit(false);
            
            // 1. Eliminar ventas de prueba
            if (idsVentasCreadas != null) {
                for (Integer id : idsVentasCreadas) {
                    if (id != null) {
                        // Primero eliminar detalles
                        String sqlDetalle = "DELETE FROM detalle_ventas WHERE venta_id = ?";
                        try (PreparedStatement ps = con.prepareStatement(sqlDetalle)) {
                            ps.setInt(1, id);
                            ps.executeUpdate();
                        }
                        // Luego eliminar venta
                        String sqlVenta = "DELETE FROM ventas WHERE id = ?";
                        try (PreparedStatement ps = con.prepareStatement(sqlVenta)) {
                            ps.setInt(1, id);
                            ps.executeUpdate();
                        }
                        System.out.println("   🗑️ Venta ID " + id + " eliminada (limpieza)");
                    }
                }
            }
            
            // 2. Eliminar productos de prueba
            if (codigosCreados != null) {
                for (String codigo : codigosCreados) {
                    if (codigo != null) {
                        String sql = "DELETE FROM productos WHERE codigo = ?";
                        try (PreparedStatement ps = con.prepareStatement(sql)) {
                            ps.setString(1, codigo);
                            int filas = ps.executeUpdate();
                            if (filas > 0) {
                                System.out.println("   🗑️ Producto " + codigo + " eliminado (limpieza)");
                            }
                        }
                    }
                }
            }
            
            // 3. Eliminar clientes de prueba
            if (cedulasCreadas != null) {
                for (String cedula : cedulasCreadas) {
                    if (cedula != null) {
                        String sql = "DELETE FROM clientes WHERE cedula = ?";
                        try (PreparedStatement ps = con.prepareStatement(sql)) {
                            ps.setString(1, cedula);
                            int filas = ps.executeUpdate();
                            if (filas > 0) {
                                System.out.println("   🗑️ Cliente " + cedula + " eliminado (limpieza)");
                            }
                        }
                    }
                }
            }
            
            con.commit();
            System.out.println("\n✅ LIMPIEZA COMPLETADA - Base de datos sin registros de prueba");
            
        } catch (SQLException e) {
            System.err.println("⚠️ Error durante la limpieza: " + e.getMessage());
            try (Connection con = Conexion.getConexion()) {
                con.rollback();
            } catch (SQLException ex) {
                System.err.println("⚠️ Error al hacer rollback: " + ex.getMessage());
            }
        }
    }
}