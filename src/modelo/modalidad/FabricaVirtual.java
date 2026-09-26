package modelo.modalidad;

public class FabricaVirtual implements FabricaModalidad {
    @Override
    public Material crearMaterial() {
        return new LicenciaPlataforma();
    }

    @Override
    public Carne crearCarne() {
        return new CarneDigital();
    }
}