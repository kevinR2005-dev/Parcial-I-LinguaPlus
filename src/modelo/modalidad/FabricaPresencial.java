package modelo.modalidad;

public class FabricaPresencial implements FabricaModalidad {
    @Override
    public Material crearMaterial() {
        return new MaterialImpreso();
    }

    @Override
    public Carne crearCarne() {
        return new CarneFisico();
    }
}