package modelo.periodo;

import modelo.Docente;
import modelo.Programa;

public class OfertaPeriodo implements Cloneable{

    private final Programa programa;
    private final String horario;
    private final Docente docente;
    private final String salon;
    private final int cuposTotales;
    private int cuposDisponibles;

    public OfertaPeriodo(Programa programa, String horario, Docente docente,
                         String salon, int cuposTotales) {
        this.programa = programa;
        this.horario = horario;
        this.docente = docente;
        this.salon = salon;
        this.cuposTotales = cuposTotales;
        this.cuposDisponibles = cuposTotales;
    }

    public boolean matricular() {
        if (cuposDisponibles == 0) {
            throw new IllegalStateException("No hay cupos disponibles para " + programa.getNombre());
        }
        cuposDisponibles--;
        return true;
    }

    public Programa getPrograma() { return programa; }
    public String getHorario() { return horario; }
    public Docente getDocente() { return docente; }
    public String getSalon() { return salon; }
    public int getCuposTotales() { return cuposTotales; }
    public int getCuposDisponibles() { return cuposDisponibles; }

    @Override
    public OfertaPeriodo clone() {
        return new OfertaPeriodo(this.programa, this.horario, this.docente,
                this.salon, this.cuposTotales);
    }

    @Override
    public String toString() {
        return programa.getNombre() + " | " + horario + " | cupos: "
                + cuposDisponibles + "/" + cuposTotales;
    }
}
