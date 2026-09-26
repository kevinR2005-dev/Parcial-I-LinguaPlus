package servicio;

import modelo.Docente;
import repositorio.Repositorio;
import java.util.List;

public class DocenteServicio {
    private final Repositorio<Docente, String> repositorio;

    public DocenteServicio(Repositorio<Docente, String> repositorio) {
        this.repositorio = repositorio;
    }

    public void registrar(Docente docente) {
        repositorio.guardar(docente);
    }

    public List<Docente> listar() {
        return repositorio.listarTodos();
    }
}
