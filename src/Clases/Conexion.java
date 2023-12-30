/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Kiwar
 */
public class Conexion {

    String nombrebd = "proyectocv";
    String usuario = "root";
    String contraseña = "root";
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public Connection conexion() {

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + nombrebd, usuario, contraseña);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return con;
    }

    public String obtenerHoraBD() throws SQLException {

        String hora = "";
        try {
            String sql = "SELECT CURRENT_TIME();";
            Connection conn = this.conexion();
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                hora = rs.getString(1);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return hora;
    }
    
        public String obtenerFechaBD() throws SQLException {

        String fecha = "";
        try {
            String sql = "SELECT CURRENT_DATE();";
            Connection conn = this.conexion();
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                fecha = rs.getString(1);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return fecha;
    }
public String obtenerFechaHoraBD() throws SQLException {

        String fecha = "";
        try {
            String sql = "SELECT CURRENT_DATE();";
            Connection conn = this.conexion();
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                fecha = rs.getString(1);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return fecha;
    }
}
