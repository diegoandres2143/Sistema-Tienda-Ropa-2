/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.diego.tiendaropa2;

import com.diego.tiendaropa2.conexion.Conexion;
/**
 *
 * @author USUARIO
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Intentando conectar a Neon...");
        try (java.sql.Connection con = Conexion.getConexion()) {
            if (con != null && !con.isClosed()) {
                System.out.println("✅ ¡CONEXIÓN EXITOSA! Tu base de datos en Neon está lista.");
            }
        } catch (java.sql.SQLException e) {
            System.err.println("❌ ERROR de conexión: " + e.getMessage());
        }
    }
}
