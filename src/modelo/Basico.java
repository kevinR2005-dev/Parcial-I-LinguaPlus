package modelo;

public class Basico extends Programa {

    public Basico(String codigo, String nombre, String idioma, String descripcion,
                  int duracionMeses, double valorMensual, Estado estado, Modalidad modalidad) {
        super(codigo, nombre, idioma, descripcion, duracionMeses, valorMensual, estado, modalidad);
    }

    @Override
    public double calcularValorBase() {
        return getValorMensual() * getDuracionMeses();
    }
}
