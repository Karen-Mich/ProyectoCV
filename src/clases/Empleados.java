/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author ramir
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Empleados {
     private String cedula;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String password;
    private String correo;
    private String token;
    private double sueldo;
    private double sueldoFinal;
    private int rol;
    DefaultTableModel modelo;

    public Empleados() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public double getSueldoFinal() {
        return sueldoFinal;
    }

    public void setSueldoFinal(double sueldoFinal) {
        this.sueldoFinal = sueldoFinal;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }
    
    Conexion cn = new Conexion();
    
    public void cargarTabla(JTable jtblEstudiante){
        String titulos[]= {"Cedula","Nombre","Apellido","Direccion","Telefono"};
        String registro[]=new String[5];
        try {
      
            Connection cc=cn.conectar();
            String sql ="SELECT CED_EMP, NOM_EMP,APE_EMP,DIR_EMP,TEL_EMP FROM empleados";
            Statement psd = cc.createStatement();
            ResultSet rs =psd.executeQuery(sql);
            modelo=new DefaultTableModel(null,titulos);
            while(rs.next()){
                registro[0]=rs.getString("CED_EMP");
                registro[1]=rs.getString("NOM_EMP");
                registro[2]=rs.getString("APE_EMP");
                registro[3]=rs.getString("DIR_EMP");
                registro[4]=rs.getString("TEL_EMP");
                
                modelo.addRow(registro);
                
            }
            jtblEstudiante.setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error:"+ ex);
        }
    }
    
    public void guardarDatosEmpleados (JTextField jtxtCedula){
            this.setCedula(jtxtCedula.getText());
            
    }
    
    public void tomarValor(JTable jtblEstudiante, JTextField jtxtCedula){
    jtblEstudiante.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if(jtblEstudiante.getSelectedRow()!=-1){
                    System.out.println(jtblEstudiante.getSelectedRow());
                    guardarDatosEmpleados(jtxtCedula);
                    int fila = jtblEstudiante.getSelectedRow();
                jtxtCedula.setText(jtblEstudiante.getValueAt(fila,0).toString());
                
                }
            }
        });
    

    }
    
}
