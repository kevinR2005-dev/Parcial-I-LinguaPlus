package modelo.comprobante;

import modelo.Matricula;

public class ComprobanteExcel implements Comprobante{

    @Override
    public String generar(Matricula matricula) {
        return "matricula;estudiante;programa;valor\n"
                + matricula.getNumeroMatricula() + ";"
                + matricula.getEstudiante().getNombreCompleto() + ";"
                + matricula.getPrograma().getNombre() + ";"
                + matricula.calcularValorFinal();
    }
}
