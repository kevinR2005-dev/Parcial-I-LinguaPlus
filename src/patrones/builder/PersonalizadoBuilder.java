package patrones.builder;

import modelo.*;

public class PersonalizadoBuilder {
    private String codigo;
    private String nombre;
    private String idioma;
    private String descripcion;
    private int duracionMeses;
    private double valorMensual;
    private Estado estado;
    private Modalidad modalidad;
    private int cantidadSesionesTutor;
    private String nivelRequerido;
    private String objetivosEstudiante;

    public Personalizado.PersonalizadoBuilder datosBasicos(String codigo, String nombre, String idioma,
                                             String descripcion, int duracionMeses,
                                             double valorMensual, Estado estado, Modalidad modalidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.idioma = idioma;
        this.descripcion = descripcion;
        this.duracionMeses = duracionMeses;
        this.valorMensual = valorMensual;
        this.estado = estado;
        this.modalidad = modalidad;
        return this;
    }

    public Personalizado.PersonalizadoBuilder cantidadSesionesTutor(int cantidad) {
        this.cantidadSesionesTutor = cantidad;
        return this;
    }

    public Personalizado.PersonalizadoBuilder nivelRequerido(String nivel) {
        this.nivelRequerido = nivel;
        return this;
    }

    public Personalizado.PersonalizadoBuilder objetivosEstudiante(String objetivos) {
        this.objetivosEstudiante = objetivos;
        return this;
    }

    public Personalizado build() {
        return new Personalizado(codigo, nombre, idioma, descripcion, duracionMeses,
                valorMensual, estado, modalidad, cantidadSesionesTutor,
                nivelRequerido, objetivosEstudiante);
    }
}
