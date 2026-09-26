package servicio;

import modelo.Matricula;
import repositorio.Repositorio;
import java.time.LocalDate;
import java.util.List;

public class MatriculaServicio {
    private final Repositorio<Matricula, Integer> repositorio;

    public MatriculaServicio(Repositorio<Matricula, Integer> repositorio) {
        this.repositorio = repositorio;
    }

    public void registrar(Matricula matricula) {
        // El numero ya viene asignado por GeneradorMatricula dentro de MatriculaBuilder.
        repositorio.guardar(matricula);
    }

    public List<Matricula> listar() {
        return repositorio.listarTodos();
    }

    /**
     * Ingresos generados por los programas adquiridos dentro de un
     * periodo dado: recorre las matriculas, filtra por fecha de inicio
     * dentro del rango y acumula el valor final de cada una.
     */
    public double calcularIngresosPorPeriodo(LocalDate desde, LocalDate hasta) {
        double total = 0;
        for (Matricula m : repositorio.listarTodos()) {
            LocalDate fecha = m.getFechaInicio();
            boolean dentroDelRango = !fecha.isBefore(desde) && !fecha.isAfter(hasta);
            if (dentroDelRango) {
                total += m.calcularValorFinal();
            }
        }
        return total;
    }
}
