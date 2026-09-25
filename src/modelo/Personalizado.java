package modelo;

public class Personalizado {

    private final int cantidadSesionesTutor;
    private final String nivelIdiomaRequerido;
    private final String objetivosEstudiante;

    public Personalizado(String codigo, String nombre, String idioma, String descripcion,
                         int duracionMeses, double valorMensual, String estado,
                         int cantidadSesionesTutor, String nivelIdiomaRequerido,
                         String objetivosEstudiante) {
        super(codigo, nombre, idioma, descripcion, duracionMeses, valorMensual, estado);
        this.cantidadSesionesTutor = cantidadSesionesTutor;
        this.nivelIdiomaRequerido = nivelIdiomaRequerido;
        this.objetivosEstudiante = objetivosEstudiante;
    }

    public int getCantidadSesionesTutor() { return cantidadSesionesTutor; }
    public String getNivelIdiomaRequerido() { return nivelIdiomaRequerido; }
    public String getObjetivosEstudiante() { return objetivosEstudiante; }

    @Override
    public double calcularValor() {
        double base = getValorMensual() * getDuracionMeses();
        double recargoTutorias = cantidadSesionesTutor * 20000;
        return base + recargoTutorias;
    }
}
