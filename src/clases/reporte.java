
package clases;

public class reporte {
    
    private String fecha;
    private String jornada;
    private String horaEntrada;
    private int minutosAtraso;
    private String horaSalida;
    private int minutosDescuento;

    public reporte(String fecha, String jornada, String horaEntrada, int minutosAtraso, String horaSalida, int minutosDescuento) {
        this.fecha = fecha;
        this.jornada = jornada;
        this.horaEntrada = horaEntrada;
        this.minutosAtraso = minutosAtraso;
        this.horaSalida = horaSalida;
        this.minutosDescuento = minutosDescuento;
    }

    public reporte() {
    }


    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public int getMinutosAtraso() {
        return minutosAtraso;
    }

    public void setMinutosAtraso(int minutosAtraso) {
        this.minutosAtraso = minutosAtraso;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public int getMinutosDescuento() {
        return minutosDescuento;
    }

    public void setMinutosDescuento(int minutosDescuento) {
        this.minutosDescuento = minutosDescuento;
    }
    
    
    
}
