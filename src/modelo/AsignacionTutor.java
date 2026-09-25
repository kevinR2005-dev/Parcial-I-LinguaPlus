package modelo;

public class AsignacionTutor {
    private final Estudiante estudiante;
    private final Programa programa;
    private final Docente docente;
    private final String fechaAsignacion;

    public AsignacionTutor(Estudiante estudiante, Programa programa, Docente docente,
                           String fechaAsignacion) {
        this.estudiante = estudiante;
        this.programa = programa;
        this.docente = docente;
        this.fechaAsignacion = fechaAsignacion;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }
    public Programa getPrograma() {
        return programa;
    }
    public Docente getDocente() {
        return docente;
    }
    public String getFechaAsignacion() {
        return fechaAsignacion;
    }
}
