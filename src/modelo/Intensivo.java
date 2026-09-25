package modelo;

public class Intensivo {

    public Intensivo(String codigo, String nombre, String idioma, String descripcion,
                     int duracionMeses, double valorMensual, String estado) {
        super(codigo, nombre, idioma, descripcion, duracionMeses, valorMensual, estado);
    }

    @Override
    public double calcularValor() {
        return getValorMensual() * getDuracionMeses();
    }
}
