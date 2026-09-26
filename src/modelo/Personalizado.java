package modelo;

public class Personalizado extends Programa {
    private int cantidadSesionesTutor;
    private String nivelRequerido;      // p. ej. "A1".."C2"
    private String objetivosEstudiante;
    private double tarifaPorSesionTutor; // se toma del Docente asignado

    public Personalizado(String codigo, String nombre, String idioma, String descripcion,
                         int duracionMeses, double valorMensual, Estado estado, Modalidad modalidad,
                         int cantidadSesionesTutor, String nivelRequerido, String objetivosEstudiante) {
        super(codigo, nombre, idioma, descripcion, duracionMeses, valorMensual, estado, modalidad);
        this.cantidadSesionesTutor = cantidadSesionesTutor;
        this.nivelRequerido = nivelRequerido;
        this.objetivosEstudiante = objetivosEstudiante;
    }

    public int getCantidadSesionesTutor() { return cantidadSesionesTutor; }
    public void setCantidadSesionesTutor(int cantidadSesionesTutor) { this.cantidadSesionesTutor = cantidadSesionesTutor; }

    public String getNivelRequerido() { return nivelRequerido; }
    public void setNivelRequerido(String nivelRequerido) { this.nivelRequerido = nivelRequerido; }

    public String getObjetivosEstudiante() { return objetivosEstudiante; }
    public void setObjetivosEstudiante(String objetivosEstudiante) { this.objetivosEstudiante = objetivosEstudiante; }

    public double getTarifaPorSesionTutor() { return tarifaPorSesionTutor; }
    public void setTarifaPorSesionTutor(double tarifaPorSesionTutor) { this.tarifaPorSesionTutor = tarifaPorSesionTutor; }

    @Override
    public double calcularValorBase() {
        double base = getValorMensual() * getDuracionMeses();
        double costoTutoria = cantidadSesionesTutor * tarifaPorSesionTutor;
        return base + costoTutoria;
    }

    public static class PersonalizadoBuilder {
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

        public PersonalizadoBuilder datosBasicos(String codigo, String nombre, String idioma,
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

        public PersonalizadoBuilder cantidadSesionesTutor(int cantidad) {
            this.cantidadSesionesTutor = cantidad;
            return this;
        }

        public PersonalizadoBuilder nivelRequerido(String nivel) {
            this.nivelRequerido = nivel;
            return this;
        }

        public PersonalizadoBuilder objetivosEstudiante(String objetivos) {
            this.objetivosEstudiante = objetivos;
            return this;
        }

        public Personalizado build() {
            return new Personalizado(codigo, nombre, idioma, descripcion, duracionMeses,
                    valorMensual, estado, modalidad, cantidadSesionesTutor,
                    nivelRequerido, objetivosEstudiante);
        }
    }
}
