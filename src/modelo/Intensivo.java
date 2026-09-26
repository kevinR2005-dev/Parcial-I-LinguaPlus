package modelo;

public class Intensivo extends Programa {

    // Recargo por la mayor intensidad horaria (ajusta el valor si tu docente definió otro)
    private static final double FACTOR_INTENSIVO = 1.2;

    public Intensivo(String codigo, String nombre, String idioma, String descripcion,
                     int duracionMeses, double valorMensual, Estado estado, Modalidad modalidad) {
        super(codigo, nombre, idioma, descripcion, duracionMeses, valorMensual, estado, modalidad);
    }

    @Override
    public double calcularValorBase() {
        return getValorMensual() * getDuracionMeses() * FACTOR_INTENSIVO;
    }
}
