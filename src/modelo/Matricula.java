package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Matricula {
    private int numeroMatricula;                 // obligatorio, consecutivo, único
    private Estudiante estudiante;                // obligatorio
    private Programa programa;                    // obligatorio
    private LocalDate fechaInicio;                 // obligatorio
    private Docente docenteTutor;                  // opcional (solo Personalizado)
    private List<ServicioAdicional> serviciosAdicionales = new ArrayList<>(); // opcional
    private double descuentoPorcentaje;            // opcional, máx. 30%
    private String observaciones;                  // opcional

    public Matricula(int numeroMatricula, Estudiante estudiante, Programa programa,
                     LocalDate fechaInicio) {
        if (programa == null) {
            throw new IllegalArgumentException("No puede existir una matricula sin programa");
        }
        this.numeroMatricula = numeroMatricula;
        this.estudiante = estudiante;
        this.programa = programa;
        this.fechaInicio = fechaInicio;
    }

    public int getNumeroMatricula() {
        return numeroMatricula;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Docente getDocenteTutor() {
        return docenteTutor;
    }

    public void setDocenteTutor(Docente docenteTutor) {
        this.docenteTutor = docenteTutor;
    }

    public List<ServicioAdicional> getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    public void agregarServicioAdicional(ServicioAdicional servicio) {
        this.serviciosAdicionales.add(servicio);
    }

    public double getDescuentoPorcentaje() {
        return descuentoPorcentaje;
    }

    public void setDescuentoPorcentaje(double descuentoPorcentaje) {
        if (descuentoPorcentaje > 30) {
            throw new IllegalArgumentException("El descuento no puede superar el 30%");
        }
        this.descuentoPorcentaje = descuentoPorcentaje;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * Valor del programa + servicios adicionales, menos el descuento.
     */
    public double calcularValorFinal() {
        double valorServicios = serviciosAdicionales.stream()
                .mapToDouble(ServicioAdicional::getPrecio)
                .sum();
        double subtotal = programa.calcularValorBase() + valorServicios;
        return subtotal - (subtotal * descuentoPorcentaje / 100);
    }

    /**
     * Builder: Matricula tiene 4 campos obligatorios (numero, estudiante,
     * programa, fecha de inicio) y 4 opcionales (docente, servicios,
     * descuento, observaciones). El builder evita un constructor con
     * demasiados parametros y centraliza las validaciones.
     */
    public static class MatriculaBuilder {
        private Estudiante estudiante;
        private Programa programa;
        private LocalDate fechaInicio;
        private Docente docenteTutor;
        private double descuentoPorcentaje = 0;
        private String observaciones = "";
        private final List<ServicioAdicional> servicios = new ArrayList<>();

        public MatriculaBuilder estudiante(Estudiante estudiante) {
            this.estudiante = estudiante;
            return this;
        }

        public MatriculaBuilder programa(Programa programa) {
            this.programa = programa;
            return this;
        }

        public MatriculaBuilder fechaInicio(LocalDate fecha) {
            this.fechaInicio = fecha;
            return this;
        }

        public MatriculaBuilder docenteTutor(Docente docente) {
            this.docenteTutor = docente;
            return this;
        }

        public MatriculaBuilder servicioAdicional(ServicioAdicional servicio) {
            this.servicios.add(servicio);
            return this;
        }

        public MatriculaBuilder descuentoPorcentaje(double descuento) {
            this.descuentoPorcentaje = descuento;
            return this;
        }

        public MatriculaBuilder observaciones(String observaciones) {
            this.observaciones = observaciones;
            return this;
        }

        public Matricula build() {
            if (programa == null) {
                throw new IllegalStateException("No puede existir una matricula sin programa");
            }
            int numero = ContadorMatricula.getInstancia().siguiente();
            Matricula matricula = new Matricula(numero, estudiante, programa, fechaInicio);
            matricula.setDocenteTutor(docenteTutor);
            matricula.setDescuentoPorcentaje(descuentoPorcentaje);
            matricula.setObservaciones(observaciones);
            servicios.forEach(matricula::agregarServicioAdicional);
            return matricula;
        }
    }
}

    /**
     * Singleton: garantiza un unico consecutivo de numero de matricula en
     * toda la academia, sin importar desde que sede o computador se registre.
     */

