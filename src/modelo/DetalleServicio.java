package modelo;

public class DetalleServicio {

    private final Matricula matricula;
    private final ServicioAdicional servicio;
    private final double precioAplicado;

    public DetalleServicio(Matricula matricula, ServicioAdicional servicio, double precioAplicado) {
        this.matricula = matricula;
        this.servicio = servicio;
        this.precioAplicado = precioAplicado;
    }

    public Matricula getMatricula() {
        return matricula;
    }
    public ServicioAdicional getServicio() {
        return servicio;
    }
    public double getPrecioAplicado() {
        return precioAplicado;
    }
}
