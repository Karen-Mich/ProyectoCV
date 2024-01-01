/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Empleados {
    private String cedula;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String clave;
    private String correo;
    private String token;
    private String rol;
    
    Conexion cx = new Conexion();
    Connection cn;
    
    public Empleados(String cedula, String nombre, String apellido, String direccion, String telefono, String clave, String correo, String token, String rol) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.clave = clave;
        this.correo = correo;
        this.token = token;
        this.rol = rol;
        cn = cx.conectar();
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    
    
    public int agregarEstudiante() {
        int n = 0;
        String sql = "INSERT INTO empleados (ced_emp, nom_emp, ape_emp, dir_emp, tel_emp, pass_emp, token_emp, cor_emp, rol_emp) VALUES(?,?,?,?,?,?,?,?,?);";
        PreparedStatement ps;
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, this.cedula);
            ps.setString(2, this.nombre);
            ps.setString(3, this.apellido);
            ps.setString(4, this.direccion);
            ps.setString(5, this.telefono);
            ps.setString(6, this.clave);
            ps.setString(7, this.correo);
            ps.setString(8, this.token);
            ps.setString(9, this.rol);
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return n;
    }
    
    public int borrarEstudiante() {
        int n = 0;
        int option = JOptionPane.showConfirmDialog(null, "¿Desea eliminar este empleado?", "Eliminar", 0, 0);
        if (option != 0) {
            return n;
        }
        String sql = "DELETE FROM empleados WHERE ced_emp = ?;";
        PreparedStatement ps;
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, this.cedula);
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return n;
    }
    
    public int actualizarEstudiante() {
        int n = 0;
        int option = JOptionPane.showConfirmDialog(null, "¿Desea editar este empleado?", "Editar", 0, 0);
        if (option != 0) {
            return n;
        }
         String sql = "UPDATE empleados SET nom_emp = ?, ape_emp = ?, dir_emp = ?, tel_emp = ?, pass_emp = ?, cor_emp = ?, token_emp = ?, rol_emp = ? WHERE ced_emp = ?;";
        
        //UPDATE `empleados` SET `CED_EMP`='[value-1]',`NOM_EMP`='[value-2]',`APE_EMP`='[value-3]',`DIR_EMP`='[value-4]',`TEL_EMP`='[value-5]',`PASS_EMP`='[value-6]',`COR_EMP`='[value-7]',`TOKEN_EMP`='[value-8]',`SUE_EMP`='[value-9]',`SUE_EMP_FINAL`='[value-10]',`ROL_EMP`='[value-11]' WHERE 1
        
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, this.nombre);
        ps.setString(2, this.apellido);
        ps.setString(3, this.direccion);
        ps.setString(4, this.telefono);  
        ps.setString(5, this.clave);
        ps.setString(6, this.correo);
        ps.setString(7, this.token);
        ps.setString(8, this.rol);
        ps.setString(9, this.cedula);
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return n;
    }
    
    public Object[] row() {
        return new Object[]{this.cedula, this.nombre, this.apellido, this.direccion, this.telefono, this.clave, this.correo, this.token, this.rol};
    }

}
    
    