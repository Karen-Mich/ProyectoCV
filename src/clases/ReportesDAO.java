
package clases;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReportesDAO extends Conexion {

    private Connection con = conectar();
    private PreparedStatement ps;
    private ResultSet rs;

    public List listar(String cedula, int anio, int mes) {

        String sql = "SELECT DATE(FEC_ASI) AS FECHA, JORNADA, TIME(FEC_HOR_ING) AS `HORA DE INGRESO`, ATRASO_MIN AS `MINUTOS DE ATRASO`, "
                    + "TIME(FEC_HOR_SAL) AS `HORA DE SALIDA`, DESCUENTO_MIN AS `MINUTOS DE DESCUENTO` "
                    + " FROM registros_asistencia AS r INNER JOIN jornada as j ON r.ID_ASI= j.ID_ASI_PER "
                    + " WHERE CED_EMP_ASI=? AND YEAR(r.FEC_ASI)=? AND MONTH(r.FEC_ASI)=? ";
        List<reporte> lista = new ArrayList<>();

        try {
             ps = con.prepareStatement(sql);
            ps.setString(1, cedula);
            ps.setInt(2, anio);
            ps.setInt(3, mes);
            rs = ps.executeQuery();
            while (rs.next()) {
                reporte pro = new reporte();

                pro.setFecha(rs.getString(1));
                pro.setJornada(rs.getString(2));
                pro.setHoraEntrada(rs.getString(3));
                pro.setMinutosAtraso(rs.getInt(4));
                pro.setHoraSalida(rs.getString(5));
                pro.setMinutosDescuento(rs.getInt(6));
                lista.add(pro);
            }
        } catch (Exception ex) {
            System.out.println(ex);

        }
        return lista;
    }
}
