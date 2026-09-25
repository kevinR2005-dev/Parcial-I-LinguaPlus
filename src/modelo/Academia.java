package modelo;

public class Academia {
    private final String nombreComercial;
    private final String nit;
    private final String direccion;
    private final String telefono;
    private final String correoElectronico;
    private final String paginaWeb;

    public Academia(String nombreComercial, String nit, String direccion,
                    String telefono, String correoElectronico, String paginaWeb) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.paginaWeb = paginaWeb;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }
    public String getNit() {
        return nit;
    }
    public String getDireccion() {
        return direccion;
    }
    public String getTelefono() {
        return telefono;
    }
    public String getCorreoElectronico() {
        return correoElectronico;
    }
    public String getPaginaWeb() {
        return paginaWeb;
    }
}
