package modelo.comprobante;

public class GeneradorPdf extends GeneradorComprobante{

    @Override
    protected Comprobante crearComprobante() {
        return new ComprobantePdf();
    }
}
