package modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Programa {
    private String codigo;
    private String nombre;
    private String idioma;
    private String descripcion;
    private int duracionMeses;
    private double valorMensual;
    private Estado estado;
    private Modalidad modalidad;
    private List<String> beneficios = new ArrayList<>();

    public Programa(String codigo, String nombre, String idioma, String descripcion,
                    int duracionMeses, double valorMensual, Estado estado, Modalidad modalidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.idioma = idioma;
        this.descripcion = descripcion;
        this.duracionMeses = duracionMeses;
        this.valorMensual = valorMensual;
        this.estado = estado;
        this.modalidad = modalidad;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getDuracionMeses() { return duracionMeses; }
    public void setDuracionMeses(int duracionMeses) { this.duracionMeses = duracionMeses; }

    public double getValorMensual() { return valorMensual; }
    public void setValorMensual(double valorMensual) { this.valorMensual = valorMensual; }

    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }

    public Modalidad getModalidad() { return modalidad; }
    public void setModalidad(Modalidad modalidad) { this.modalidad = modalidad; }

    public List<String> getBeneficios() { return beneficios; }
    public void agregarBeneficio(String beneficio) { this.beneficios.add(beneficio); }

    /** Cada subtipo concreto define cómo calcula su valor base. */
    public abstract double calcularValorBase();

    /** Para que los combos de los programas se vean como: (nombre de programa + código + idioma) */
    @Override
    public String toString() {
        return nombre + " (" + codigo + ") - " + idioma;
    }

    /**
     * Factory Method: centraliza la creacion de cada tipo de programa
     * para que el resto del sistema no dependa de sus constructores
     * concretos (Basico, Intensivo, Personalizado).
     */
    public static class ProgramaFactory {

        public enum TipoPrograma { BASICO, INTENSIVO, PERSONALIZADO }

        public static Programa crear(TipoPrograma tipo, String codigo, String nombre,
                                     String idioma, String descripcion, int duracionMeses,
                                     double valorMensual, Estado estado, Modalidad modalidad) {
            return switch (tipo) {
                case BASICO -> new Basico(codigo, nombre, idioma, descripcion,
                        duracionMeses, valorMensual, estado, modalidad);
                case INTENSIVO -> new Intensivo(codigo, nombre, idioma, descripcion,
                        duracionMeses, valorMensual, estado, modalidad);
                case PERSONALIZADO -> throw new IllegalArgumentException(
                        "Personalizado requiere datos adicionales: usa PersonalizadoBuilder");
            };
        }
    }
}
