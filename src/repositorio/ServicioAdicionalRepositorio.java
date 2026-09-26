package repositorio;

import java.util.*;
import java.util.function.Function;


public class ServicioAdicionalRepositorio<T, ID> implements Repositorio<T, ID> {
    private final Map<ID, T> datos = new LinkedHashMap<>();
    private final Function<T, ID> extractorId;

    public ServicioAdicionalRepositorio(Function<T, ID> extractorId) {
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