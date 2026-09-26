package repositorio;

import java.util.List;
import java.util.Optional;

/**
 * Abstraccion de persistencia (Dependency Inversion - SOLID): los
 * servicios dependen de esta interfaz, no de una implementacion
 * concreta de almacenamiento.
 */
public interface Repositorio<T, ID> {
    void guardar(T entidad);
    Optional<T> buscarPorId(ID id);
    List<T> listarTodos();
    void eliminar(ID id);
}
