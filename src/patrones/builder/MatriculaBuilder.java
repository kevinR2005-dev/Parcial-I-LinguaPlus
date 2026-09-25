package patrones.builder;

import modelo.*;
import patrones.singleton.GeneradorMatricula;
import java.time.LocalDate;

/**
 * Builder: Matricula tiene 4 campos obligatorios (numero, estudiante,
 * programa, fecha de inicio) y 4 opcionales (docente, servicios,
 * descuento, observaciones). El builder evita un constructor con
 * demasiados parametros y centraliza las validaciones.
 */
public class MatriculaBuilder {
    private Estudiante estudiante;
    private Programa programa;
    private LocalDate fechaInicio;
    private Docente docenteTutor;
    private double descuentoPorcentaje = 0;
    private String observaciones = "";
    private final java.util.List<ServicioAdicional> servicios = new java.util.ArrayList<>();

    public Matricula.MatriculaBuilder estudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
        return this;
    }

    public Matricula.MatriculaBuilder programa(Programa programa) {
        this.programa = programa;
        return this;
    }

    public Matricula.MatriculaBuilder fechaInicio(LocalDate fecha) {
        this.fechaInicio = fecha;
        return this;
    }

    public Matricula.MatriculaBuilder docenteTutor(Docente docente) {
        this.docenteTutor = docente;
        return this;
    }

    public Matricula.MatriculaBuilder servicioAdicional(ServicioAdicional servicio) {
        this.servicios.add(servicio);
        return this;
    }

    public Matricula.MatriculaBuilder descuentoPorcentaje(double descuento) {
        this.descuentoPorcentaje = descuento;
        return this;
    }

    public Matricula.MatriculaBuilder observaciones(String observaciones) {
        this.observaciones = observaciones;
        return this;
    }

    public Matricula build() {
        if (programa == null) {
            throw new IllegalStateException("No puede existir una matricula sin programa");
        }
        int numero = GeneradorMatricula.getInstancia().siguienteNumero();
        Matricula matricula = new Matricula(numero, estudiante, programa, fechaInicio);
        matricula.setDocenteTutor(docenteTutor);
        matricula.setDescuentoPorcentaje(descuentoPorcentaje);
        matricula.setObservaciones(observaciones);
        servicios.forEach(matricula::agregarServicioAdicional);
        return matricula;
    }
}
