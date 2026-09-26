package modelo.comprobante;

import modelo.Matricula;

public class ComprobantePdf implements Comprobante {

    @Override
    public String generar(Matricula matricula) {
        return "[PDF] " + matricula;
    }
}
