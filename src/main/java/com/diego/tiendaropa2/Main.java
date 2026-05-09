/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.diego.tiendaropa2;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.diego.tiendaropa2.conexion.Conexion;
import com.diego.tiendaropa2.vista.VistaPrincipal;

/**
 * Clase principal del sistema de gestión de tienda.
 * Inicia la aplicación Swing y verifica la conexión a la base de datos.
 * 
 * @author USUARIO
 */
public class Main {
    
    private static final java.util.logging.Logger logger = 
        java.util.logging.Logger.getLogger(Main.class.getName());
    
    public static void main(String[] args) {
        // Configurar Look and Feel para mejor apariencia
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            logger.warning("No se pudo establecer el Look and Feel del sistema: " + e.getMessage());
        }
        
        // Verificar conexión a la base de datos antes de iniciar
        System.out.println("Iniciando Sistema de Gestión de Tienda...");
        System.out.println("Verificando conexión a Neon Database...");
        
        try (Connection con = Conexion.getConexion()) {
            if (con != null && !con.isClosed()) {
                System.out.println("Conexión exitosa a Neon Database");
                System.out.println("Iniciando interfaz gráfica...");
                
                // Iniciar la aplicación Swing
                java.awt.EventQueue.invokeLater(() -> {
                    new VistaPrincipal().setVisible(true);
                });
            } else {
                throw new SQLException("La conexión devolvió null");
            }
        } catch (SQLException e) {
            System.err.println("ERROR DE CONEXIÓN: " + e.getMessage());
            logger.severe("No se pudo conectar a la base de datos: " + e.getMessage());
            
            // Mostrar mensaje de error al usuario
            JOptionPane.showMessageDialog(
                null,
                "No se pudo conectar a la base de datos.\n\n" +
                "Verifica:\n" +
                "1. Tu conexión a Internet\n" +
                "2. Las credenciales de Neon en el archivo de configuración\n" +
                "3. Que la base de datos esté activa\n\n" +
                "Error técnico: " + e.getMessage(),
                "Error de Conexión",
                JOptionPane.ERROR_MESSAGE
            );
            
            // Salir con código de error
            System.exit(1);
        }
    }
}