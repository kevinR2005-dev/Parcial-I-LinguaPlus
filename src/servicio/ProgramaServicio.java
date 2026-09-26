package servicio;

import modelo.Programa;

import modelo.Programa;
import repositorio.Repositorio;
import java.util.List;

public class ProgramaServicio {
    private final Repositorio<Programa, String> repositorio;

    public ProgramaServicio(Repositorio<Programa, String> repositorio) {
        this.repositorio = repositorio;
    }

    public void registrar(Programa programa) {
        repositorio.guardar(programa);
    }

    public List<Programa> listar() {
        return repositorio.listarTodos();
    }
}
