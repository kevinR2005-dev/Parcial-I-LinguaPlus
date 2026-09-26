package modelo.periodo;

import java.util.ArrayList;
import java.util.List;

public class PeriodoAcademico {

    private String fechaInicio;
    private String fechaFin;
    private List<OfertaPeriodo> ofertas;

    public PeriodoAcademico(String fechaInicio, String fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.ofertas = new ArrayList<>();
    }

    public void agregarOferta(OfertaPeriodo oferta) {
        ofertas.add(oferta);
    }

    public PeriodoAcademico clonarOferta(String nuevaFechaInicio, String nuevaFechaFin) {
        PeriodoAcademico copia = new PeriodoAcademico(nuevaFechaInicio, nuevaFechaFin);
        for (OfertaPeriodo oferta : this.ofertas) {
            copia.ofertas.add(oferta.clone());
        }
        return copia;
    }

    public void setFechaInicio(String fechaInicio) { this.fechaInicio = fechaInicio; }
    public void setFechaFin(String fechaFin) { this.fechaFin = fechaFin; }
    public String getFechaInicio() { return fechaInicio; }
    public String getFechaFin() { return fechaFin; }
    public int cantidadOfertas() { return ofertas.size(); }
    public OfertaPeriodo getOferta(int indice) { return ofertas.get(indice); }

    public boolean compartenListaOfertas(PeriodoAcademico otro) {
        return this.ofertas == otro.ofertas;
    }
}
