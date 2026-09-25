package modelo;

import java.time.LocalDate;

public class Estuadiante {

    private final String documentoIdentidad;
    private final String nombreCompleto;
    private final String telefono;
    private final String correoElectronico;
    private final int edad;
    private final LocalDate fechaRegistro;

    public Estuadiante(String documentoIdentidad, String nombreCompleto, String telefono, String correoElectronico, int edad, LocalDate fechaRegistro) {
        this.documentoIdentidad = documentoIdentidad;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.edad = edad;
        this.fechaRegistro = fechaRegistro;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public int getEdad() {
        return edad;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    @Override
    public String toString() {
        return nombreCompleto + "(" + documentoIdentidad + ")";
    }
}
