package repositorio;

import java.util.*;
import java.util.function.Function;

/**
 * Implementacion generica en memoria de Repositorio<T, ID>. Sirve de
 * base para los repositorios de Estudiante, Programa, Docente,
 * ServicioAdicional y Matricula.
 */
public class RepositorioMemoria<T, ID> implements Repositorio<T, ID> {
    private final Map<ID, T> datos = new LinkedHashMap<>();
    private final Function<T, ID> extractorId;

    public RepositorioMemoria(Function<T, ID> extractorId) {
        this.extractorId = extractorId;
    }

    @Override
    public void guardar(T entidad) {
        datos.put(extractorId.apply(entidad), entidad);
    }

    @Override
    public Optional<T> buscarPorId(ID id) {
        return Optional.ofNullable(datos.get(id));
    }

    @Override
    public List<T> listarTodos() {
        return new ArrayList<>(datos.values());
    }

    @Override
    public void eliminar(ID id) {
        datos.remove(id);
    }
}
