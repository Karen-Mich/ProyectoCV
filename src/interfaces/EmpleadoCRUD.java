/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaces;

import clases.Conexion;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.netbeans.lib.awtextra.*;


/**
 *
 * @author DELL
 * 
 */

public class EmpleadoCRUD extends javax.swing.JFrame {
    Conexion cc = new Conexion();
    Connection cn;
    DefaultTableModel modelTable;
    
    //Estudiante1 estudiante = new Estudiante1();
    
    /**
     * Creates new form EstudianteObjeto
     */
    public EmpleadoCRUD() { 
        initComponents();
        setLocationRelativeTo(null);
        setSize(916, 700);
        cn = cc.conectar();
        cargarTabla();
        
        getContentPane().setBackground(new Color(234,234,234));
         setResizable(false);
         
         int anchoDeseado = 120; // Cambia esto al ancho deseado
int columnaEspecifica = 6; // Cambia esto al índice de la columna que deseas ajustar

tblTabla.getColumnModel().getColumn(columnaEspecifica).setPreferredWidth(anchoDeseado);

        
        jtxtCedula.addKeyListener(new KeyAdapter() {
    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (!Character.isDigit(c)) {
            e.consume(); // Ignorar el evento de tecla si no es un número
        }
    }
});
        
        jtxtTelefono.addKeyListener(new KeyAdapter() {
    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (!Character.isDigit(c)) {
            e.consume(); // Ignorar el evento de tecla si no es un número
        }
    }
});
        jtxtRol.addKeyListener(new KeyAdapter() {
    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (!Character.isDigit(c)) {
            e.consume(); // Ignorar el evento de tecla si no es un número
        }
    }
});


        tblTabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectRow = tblTabla.getSelectedRow();
                if (selectRow != -1) {
                    jtxtCedula.setText(tblTabla.getValueAt(selectRow, 0).toString());
                    jtxtNombre.setText(tblTabla.getValueAt(selectRow, 1).toString());
                    jtxtApellido.setText(tblTabla.getValueAt(selectRow, 2).toString());
                    jtxtDireccion.setText(tblTabla.getValueAt(selectRow, 3).toString());
                    jtxtTelefono.setText(tblTabla.getValueAt(selectRow, 4).toString());
                    jtxtClave.setText(tblTabla.getValueAt(selectRow, 5).toString());
                    jtxtCorreo.setText(tblTabla.getValueAt(selectRow, 6).toString());
                    jtxtToken.setText(tblTabla.getValueAt(selectRow, 7).toString());
                    jtxtRol.setText(tblTabla.getValueAt(selectRow, 8).toString());
                }
            }
        });
    }
    
    public void cargarTabla() {
        modelTable = new DefaultTableModel(new String[]{"Cédula", "Nombre", "Apellido", "Dirección", "Telefono","Clave","Correo","Token","Rol"}, 0);
        String sql = "SELECT * FROM empleados;";
        ResultSet rs = null;
        try {
            Statement s = cn.createStatement();
            rs = s.executeQuery(sql);
            while (rs.next()) {
                Empleados e = new Empleados(
                        rs.getString("ced_emp"),
                        rs.getString("nom_emp"),
                        rs.getString("ape_emp"),
                        rs.getString("dir_emp"),
                        rs.getString("tel_emp"),
                        rs.getString("pass_emp"),
                        rs.getString("cor_emp"),
                        rs.getString("token_emp"),
                        rs.getString("rol_emp"));
                modelTable.addRow(e.row());
            }
            tblTabla.setModel(modelTable);
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public boolean verificarCampos() {
    String cedula = jtxtCedula.getText().trim();
    String telefono = jtxtTelefono.getText().trim();
    String rol = jtxtRol.getText().trim();

    boolean esValido = true;
    
    if (cedula.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Debe ingresar la cédula");
    esValido = false;
} else if (cedula.length() != 10 || !cedula.matches("\\d+")) {
    JOptionPane.showMessageDialog(this, "La cédula debe contener exactamente 10 números");
    esValido = false;
}
    
if(jtxtNombre.getText().trim().isEmpty()) {
      JOptionPane.showMessageDialog(this, "Debe ingresar el nombre");
      esValido = false;
    }
    if(jtxtApellido.getText().trim().isEmpty()) {
      JOptionPane.showMessageDialog(this, "Debe ingresar el apellido");
      esValido = false;
    }

    
    if(jtxtDireccion.getText().trim().isEmpty()) {
      JOptionPane.showMessageDialog(this, "Debe ingresar la dirección");
      esValido = false;
    }


    if(telefono.isEmpty()) {
      JOptionPane.showMessageDialog(this, "Debe ingresar el teléfono");
      esValido = false; 
    }  else if (telefono.length() != 10 || !telefono.matches("\\d+")) {
    JOptionPane.showMessageDialog(this, "El teléfono debe contener exactamente 10 números");
    esValido = false;
}
    
        if(jtxtCorreo.getText().trim().isEmpty()) {
      JOptionPane.showMessageDialog(this, "Debe ingresar el correo");
      esValido = false;
    }
        
            if(rol.isEmpty()) {
      JOptionPane.showMessageDialog(this, "Debe ingresar el rol");
      esValido = false;
    } else if (rol.length() != 1 || !rol.matches("[01]")) {
    JOptionPane.showMessageDialog(this, "El rol puede ser 0 o 1.");
    esValido = false;
}

    return esValido;
}
    
    
   

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jtxtCedula = new javax.swing.JTextField();
        jtxtNombre = new javax.swing.JTextField();
        jtxtApellido = new javax.swing.JTextField();
        jtxtDireccion = new javax.swing.JTextField();
        jtxtTelefono = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        claveLbl = new javax.swing.JLabel();
        correoLbl = new javax.swing.JLabel();
        jtxtCorreo = new javax.swing.JTextField();
        jtxtClave = new javax.swing.JTextField();
        rolLbl = new javax.swing.JLabel();
        jtxtRol = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtxtToken = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTabla = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jbtnBorrar = new javax.swing.JButton();
        jbtnNuevo = new javax.swing.JButton();
        jbtnGuardar = new javax.swing.JButton();
        jbtnEditar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setFocusTraversalPolicyProvider(true);
        setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));
        jPanel1.setForeground(new java.awt.Color(204, 255, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtxtCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtCedulaActionPerformed(evt);
            }
        });
        jPanel1.add(jtxtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 179, -1));

        jtxtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtNombreActionPerformed(evt);
            }
        });
        jPanel1.add(jtxtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 179, -1));
        jPanel1.add(jtxtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 80, 179, -1));

        jtxtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtDireccionActionPerformed(evt);
            }
        });
        jPanel1.add(jtxtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 179, -1));
        jPanel1.add(jtxtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 120, 179, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        jLabel1.setText("Cédula:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 58, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        jLabel2.setText("Nombre:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 58, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        jLabel3.setText("Apellido:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 80, 58, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        jLabel4.setText("Dirección:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        jLabel5.setText("Teléfono:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 120, 58, -1));

        claveLbl.setText("Clave:");
        jPanel1.add(claveLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 160, 40, -1));

        correoLbl.setText("Correo:");
        jPanel1.add(correoLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 40, -1));
        jPanel1.add(jtxtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 180, -1));
        jPanel1.add(jtxtClave, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 150, 180, 30));

        rolLbl.setText("Rol:");
        jPanel1.add(rolLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 40, 40, 30));
        jPanel1.add(jtxtRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 40, 70, 30));

        jLabel6.setText("Token:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, -1, -1));

        jtxtToken.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtTokenActionPerformed(evt);
            }
        });
        jPanel1.add(jtxtToken, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 260, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 840, 230));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9"
            }
        ));
        jScrollPane2.setViewportView(tblTabla);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 37, 790, 142));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 840, 200));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Operaciones"));

        jbtnBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/borrar.png"))); // NOI18N
        jbtnBorrar.setText("Borrar");
        jbtnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBorrarActionPerformed(evt);
            }
        });

        jbtnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/agregar-archivo.png"))); // NOI18N
        jbtnNuevo.setText("Nuevo");
        jbtnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnNuevoActionPerformed(evt);
            }
        });

        jbtnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/disco-flexible.png"))); // NOI18N
        jbtnGuardar.setText("Guardar");
        jbtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnGuardarActionPerformed(evt);
            }
        });

        jbtnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/editar (1).png"))); // NOI18N
        jbtnEditar.setText("Editar");
        jbtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(jbtnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jbtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jbtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jbtnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(113, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 840, 80));

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/flecha-correcta.png"))); // NOI18N
        jButton2.setText("Reportes");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 20, 130, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtNombreActionPerformed
    
     
    
    private void jbtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnNuevoActionPerformed
        jtxtCedula.setText("");
        jtxtNombre.setText("");
        jtxtApellido.setText("");
        jtxtDireccion.setText("");
        jtxtTelefono.setText("");
        jtxtClave.setText("");
        jtxtToken.setText("");
        jtxtCorreo.setText("");
        jtxtRol.setText("");
    
// TODO add your handling code here:
    }//GEN-LAST:event_jbtnNuevoActionPerformed

    private void jtxtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtDireccionActionPerformed

    private void jbtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditarActionPerformed
        if (!verificarCampos()) {
        JOptionPane.showMessageDialog(this, "Por favor edite los campos correctamente");
        return;
    }
        
        Empleados e = new Empleados(jtxtCedula.getText(), jtxtNombre.getText(), jtxtApellido.getText(), jtxtDireccion.getText(), jtxtTelefono.getText(),jtxtClave.getText(), jtxtCorreo.getText(),
        jtxtToken.getText(), jtxtRol.getText());
        int n = e.actualizarEstudiante();
        if (n == 1) {
            JOptionPane.showMessageDialog(this, "Editado correctamente");
            cargarTabla();
        }
    }//GEN-LAST:event_jbtnEditarActionPerformed

    private void jbtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGuardarActionPerformed
        if (!verificarCampos()) {
        JOptionPane.showMessageDialog(this, "Por favor complete todos los campos correctamente");
        return;
    }

    Empleados e = new Empleados(jtxtCedula.getText(), jtxtNombre.getText(), jtxtApellido.getText(), jtxtDireccion.getText(), jtxtTelefono.getText(),jtxtClave.getText(), jtxtToken.getText(),
        jtxtCorreo.getText(), jtxtRol.getText());
    int n = e.agregarEstudiante();

    if (n == 1) {
        JOptionPane.showMessageDialog(this, "Guardado correctamente");
        cargarTabla();
    }
    }//GEN-LAST:event_jbtnGuardarActionPerformed

    private void jbtnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnBorrarActionPerformed
        Empleados e = new Empleados(jtxtCedula.getText(), jtxtNombre.getText(), jtxtApellido.getText(), jtxtDireccion.getText(), jtxtTelefono.getText(),jtxtClave.getText(), jtxtToken.getText(),
        jtxtCorreo.getText(), jtxtRol.getText());
        int n = e.borrarEstudiante();
        if (n == 1) {
            JOptionPane.showMessageDialog(this, "Borrado correctamente");
            cargarTabla();
        }
    }//GEN-LAST:event_jbtnBorrarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        ReportesAdmin repAd = new ReportesAdmin();
        repAd.setVisible(true);

    // Cerrar o esconder el frame actual si es necesario
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jtxtTokenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtTokenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtTokenActionPerformed

    private void jtxtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtCedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtCedulaActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EmpleadoCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmpleadoCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmpleadoCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmpleadoCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmpleadoCRUD().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel claveLbl;
    private javax.swing.JLabel correoLbl;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtnBorrar;
    private javax.swing.JButton jbtnEditar;
    private javax.swing.JButton jbtnGuardar;
    private javax.swing.JButton jbtnNuevo;
    private javax.swing.JTextField jtxtApellido;
    private javax.swing.JTextField jtxtCedula;
    private javax.swing.JTextField jtxtClave;
    private javax.swing.JTextField jtxtCorreo;
    private javax.swing.JTextField jtxtDireccion;
    private javax.swing.JTextField jtxtNombre;
    private javax.swing.JTextField jtxtRol;
    private javax.swing.JTextField jtxtTelefono;
    private javax.swing.JTextField jtxtToken;
    private javax.swing.JLabel rolLbl;
    private javax.swing.JTable tblTabla;
    // End of variables declaration//GEN-END:variables
}
