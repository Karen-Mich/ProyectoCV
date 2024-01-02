
package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class Conexion {
    Connection connect = null;
    
    public Connection conectar (){
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/registro_empleados","root","1805162433");//cambie de nombre
            //JOptionPane.showMessageDialog(null, "CONECTADO");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return connect;
    }
}
