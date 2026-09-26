package modelo.comprobante;

import modelo.Matricula;

public abstract class GeneradorComprobante {

    protected abstract Comprobante crearComprobante();

    public final String emitir(Matricula matricula) {
        Comprobante comprobante = crearComprobante();
        return "=== LinguaPlus - Comprobante de pago ===\n"
                + comprobante.generar(matricula)
                + "\n=== Gracias por su matricula ===";
    }
}
