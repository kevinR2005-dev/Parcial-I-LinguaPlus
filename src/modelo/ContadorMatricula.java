package modelo;

public class ContadorMatricula {

    private static final ContadorMatricula INSTANCIA = new ContadorMatricula();

    private int ultimo = 0;

    private ContadorMatricula() { }

    public static ContadorMatricula getInstancia() {
        return INSTANCIA;
    }

    public synchronized int siguiente() {
        return ++ultimo;
    }
}
