package interfaces;

import clases.Empleados;
import clases.ReportesDAO;
import clases.reporte;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ReportesAdmin extends javax.swing.JFrame {

    private ReportesDAO rd = new ReportesDAO();
    private reporte r;
    private List<reporte> lr;
    private Empleados e;
    private final int sueldoInicial = 2000;
    private double sueldoFinal;
    private double cantMinAtr;
    private double cantMinDesc;

    Empleados em = new Empleados();

    public ReportesAdmin() {
        initComponents();
        em.cargarTabla(jtblEmpleados);
        em.tomarValor(jtblEmpleados, jtxtCedula);
        r = new reporte();
        lr = new ArrayList<>();
        e = new Empleados();
        sueldoFinal = 0;
        cantMinAtr = 0;
        cantMinDesc = 0;
    }

    private void openpdf(String file) {

        try {
            SwingController control = new SwingController();
            SwingViewBuilder factry = new SwingViewBuilder(control);
            JPanel veiwerCompntpnl = factry.buildViewerPanel();
            ComponentKeyBinding.install(control, veiwerCompntpnl);
            control.getDocumentViewController().setAnnotationCallback(
                    new org.icepdf.ri.common.MyAnnotationCallback(
                            control.getDocumentViewController()));
            control.openDocument(file);
            jScrollPane2.setViewportView(veiwerCompntpnl);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Cannot Load Pdf");
        }
    }

    private void formatoDocumento() {

        Document d = new Document();
        try {
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(d, new FileOutputStream(ruta + "/Desktop/reporte" + this.jtxtCedula.getText() + ".pdf"));
            //////
            Image headerL = Image.getInstance("src/images/logoFISEI.png");
            Image headerR = Image.getInstance("src/images/utaSmallLogo.png");
            headerL.scaleToFit(450, 50);
            headerR.scaleToFit(50, 50);
            headerL.setAlignment(Chunk.ALIGN_LEFT);
            headerR.setAlignment(Chunk.ALIGN_RIGHT);
            /////
            PdfPCell cell1 = new PdfPCell();
            PdfPCell cell2 = new PdfPCell();

            cell1.addElement(headerL);
            cell2.addElement(headerR);

            cell1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
            cell2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);

            Paragraph pa = new Paragraph();

            pa.add("\n\n");
            pa.add("UNIVERSIDAD TECNICA DE AMBATO\n\n");
            pa.add("REPORTE DEL MENSUAL\n\n");
            pa.setAlignment(pa.ALIGN_CENTER);
            //pa.setFont(FontFactory.getFont("TAHOMA",25,Font.BOLD, BaseColor.DARK_GRAY));

            Paragraph pa2 = new Paragraph();

            pa2.add("Cedula: " + e.getCedula() + "\n");
            pa2.add("Nombre: " + e.getNombre() + "\n");
            pa2.add("Apellido: " + e.getApellido() + "\n");
            pa2.add("Direccion: " + e.getDireccion() + "\n");
            pa2.add("Celular: " + e.getTelefono() + "\n\n\n");
            pa2.setAlignment(pa.ALIGN_LEFT);
            d.open();

            PdfPTable c = new PdfPTable(2);
            c.setWidthPercentage(100);
            c.getDefaultCell().setBorder(Rectangle.NO_BORDER);

            c.addCell(cell1);
            c.addCell(cell2);

            PdfPTable t = new PdfPTable(6);
            t.addCell("FECHA");
            t.addCell("JORNADA");
            t.addCell("HORA ENTRADA");
            t.addCell("MIN ATRASO");
            t.addCell("HORA SALIDA");
            t.addCell("MIN DESCUENTO");

            for (reporte p : lr) {
                t.addCell(p.getFecha());
                t.addCell(p.getJornada());
                t.addCell(p.getHoraEntrada());
                t.addCell(String.valueOf(p.getMinutosAtraso()));
                cantMinAtr += p.getMinutosAtraso();
                t.addCell(p.getHoraSalida());
                t.addCell(String.valueOf(p.getMinutosDescuento()));
                cantMinDesc += p.getMinutosDescuento();

            }
            sueldoFinal = sueldoInicial - cantMinAtr * 0.25 - cantMinDesc * 0.25;
            Paragraph pa3 = new Paragraph();
            pa3.add("\n\n");
            pa3.add("Cantidad Total de Minutos de Atraso: " + cantMinAtr + "\n");
            pa3.add("Cantidad Total de Minutos de Descuento: " + cantMinDesc + "\n");
            pa3.add("Minutos de Atraso x 0.25: " + cantMinAtr * 0.25 + "\n");
            pa3.add("Minutos de Descuento x 0.25: " + cantMinDesc * 0.25 + "\n");
            pa3.add("Sueldo Inicial: " + sueldoInicial + "$\n\n");
            pa3.add("Sueldo Final: " + sueldoFinal + "$\n");

            d.add(c);
            d.add(pa);
            d.add(pa2);
            d.add(t);
            d.add(pa3);
            d.close();

            System.out.println("Se creo?");

            openpdf(ruta + "/Desktop/reporte" + this.jtxtCedula.getText() + ".pdf");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtxtCedula = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jmesChooser = new com.toedter.calendar.JMonthChooser();
        jyearChooser = new com.toedter.calendar.JYearChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblEmpleados = new javax.swing.JTable();
        jbtnReporte = new javax.swing.JButton();
        jbtnRegresar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(234, 234, 234));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Empleado"));
        jPanel2.setForeground(new java.awt.Color(187, 187, 187));

        jLabel1.setText("Cédula:");

        jLabel2.setText("Fecha:");

        jtblEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jtblEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblEmpleadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtblEmpleados);

        jbtnReporte.setText("Generar Reporte");
        jbtnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnReporteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jmesChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jyearChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jtxtCedula))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 197, Short.MAX_VALUE)
                        .addComponent(jbtnReporte)
                        .addGap(117, 117, 117))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jtxtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jmesChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jyearChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jbtnReporte)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                .addContainerGap())
        );

        jbtnRegresar.setText("Regresar");
        jbtnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnRegresar)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jbtnRegresar)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnReporteActionPerformed
        // TODO add your handling code here:
        if (!this.jtxtCedula.getText().isEmpty()) {
            if (jyearChooser.getYear() > 2000) {
                if (jmesChooser.getMonth() >= 0) {
                    String cedula = this.jtxtCedula.getText();
                    int anio = jyearChooser.getYear();
                    int mes = jmesChooser.getMonth() + 1;
                    lr = rd.listar(cedula, anio, mes);
                    if (lr.size() > 0) {
                        formatoDocumento();
                    } else {
                        JOptionPane.showMessageDialog(this, "No hay Datos que mostrar");
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione un Mes");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ingrese un año");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Escoja una cedula");
        }
//          String mesS;
//          if(mes<10){
//          mesS="0"+String.valueOf(mes);
//          }else {
//          mesS=String.valueOf(mes);}
//          // Usa este dato para la fecha, y la formateas como puedas: 
//          String fecha = year+"-"+mesS; // seria 2024-02 etc
//          System.out.println(fecha);
//          
    }//GEN-LAST:event_jbtnReporteActionPerformed

    private void jbtnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnRegresarActionPerformed
        // TODO add your handling code here:
        EmpleadoCRUD EmpCru = new EmpleadoCRUD();
        EmpCru.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jbtnRegresarActionPerformed

    private void jtblEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblEmpleadosMouseClicked
        int fila = this.jtblEmpleados.rowAtPoint(evt.getPoint());
        e.setCedula(this.jtblEmpleados.getValueAt(fila, 0).toString());
        e.setNombre(this.jtblEmpleados.getValueAt(fila, 1).toString());
        e.setApellido(this.jtblEmpleados.getValueAt(fila, 2).toString());
        e.setDireccion(this.jtblEmpleados.getValueAt(fila, 3).toString());
        e.setTelefono(this.jtblEmpleados.getValueAt(fila, 4).toString());
    }//GEN-LAST:event_jtblEmpleadosMouseClicked

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
            java.util.logging.Logger.getLogger(ReportesAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReportesAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReportesAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReportesAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReportesAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jbtnRegresar;
    private javax.swing.JButton jbtnReporte;
    private com.toedter.calendar.JMonthChooser jmesChooser;
    private javax.swing.JTable jtblEmpleados;
    private javax.swing.JTextField jtxtCedula;
    private com.toedter.calendar.JYearChooser jyearChooser;
    // End of variables declaration//GEN-END:variables
}
