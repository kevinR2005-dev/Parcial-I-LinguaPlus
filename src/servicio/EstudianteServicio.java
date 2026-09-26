package servicio;

import modelo.Estudiante;
import repositorio.Repositorio;
import java.util.List;
import java.util.Optional;

public class EstudianteServicio {
    private final Repositorio<Estudiante, String> repositorio;

    public EstudianteServicio(Repositorio<Estudiante, String> repositorio) {
        this.repositorio = repositorio;
    }

    public void registrar(Estudiante estudiante) {
        repositorio.guardar(estudiante);
    }

    public List<Estudiante> listar() {
        return repositorio.listarTodos();
    }

    /** Busca un estudiante por telefono, como pide el enunciado. */
    public Optional<Estudiante> buscarPorTelefono(String telefono) {
        return repositorio.listarTodos().stream()
                .filter(e -> e.getTelefono() != null && e.getTelefono().equals(telefono))
                .findFirst();
    }
}
