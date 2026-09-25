package app.controlador;

import modelo.*;
import patrones.builder.MatriculaBuilder;
import patrones.factory.ProgramaFactory;
import repositorio.Repositorio;
import repositorio.RepositorioMemoria;
import servicio.*;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Controlador unico de la vista principal. No calcula ni
 * valida nada por si mismo: delega todo a la capa `servicio`. Aqui solo
 * se conectan los eventos de la interfaz con las llamadas a los
 * servicios y se actualizan las tablas.
 */
public class MainController {

    // ---------- Servicios (con repositorios en memoria) ----------
    private final Repositorio<Estudiante, String> estudianteRepo =
            new RepositorioMemoria<>(Estudiante::getDocumentoIdentidad);
    private final Repositorio<Programa, String> programaRepo =
            new RepositorioMemoria<>(Programa::getCodigo);
    private final Repositorio<Matricula, Integer> matriculaRepo =
            new RepositorioMemoria<>(Matricula::getNumeroMatricula);

    private final EstudianteServicio estudianteServicio = new EstudianteServicio(estudianteRepo);
    private final ProgramaServicio programaServicio = new ProgramaServicio(programaRepo);
    private final MatriculaServicio matriculaServicio = new MatriculaServicio(matriculaRepo);
    private final ConsultaServicio consultaServicio = new ConsultaServicio();

    // ---------- Listas observables para las tablas ----------
    private final ObservableList<Estudiante> listaEstudiantes = FXCollections.observableArrayList();
    private final ObservableList<Programa> listaProgramas = FXCollections.observableArrayList();
    private final ObservableList<Matricula> listaMatriculas = FXCollections.observableArrayList();

    // ---------- Tab Estudiantes ----------
    @FXML private TextField txtNombreEstudiante;
    @FXML private TextField txtDocumentoEstudiante;
    @FXML private TextField txtTelefonoEstudiante;
    @FXML private TextField txtCorreoEstudiante;
    @FXML private TextField txtEdadEstudiante;
    @FXML private TableView<Estudiante> tablaEstudiantes;
    @FXML private TableColumn<Estudiante, String> colNombreEstudiante;
    @FXML private TableColumn<Estudiante, String> colDocumentoEstudiante;
    @FXML private TableColumn<Estudiante, String> colTelefonoEstudiante;
    @FXML private TableColumn<Estudiante, String> colCorreoEstudiante;

    // ---------- Tab Programas ----------
    @FXML private TextField txtCodigoPrograma;
    @FXML private TextField txtNombrePrograma;
    @FXML private TextField txtIdiomaPrograma;
    @FXML private TextField txtDuracionPrograma;
    @FXML private TextField txtValorPrograma;
    @FXML private ComboBox<ProgramaFactory.TipoPrograma> comboTipoPrograma;
    @FXML private ComboBox<Modalidad> comboModalidadPrograma;
    @FXML private TableView<Programa> tablaProgramas;
    @FXML private TableColumn<Programa, String> colCodigoPrograma;
    @FXML private TableColumn<Programa, String> colNombrePrograma;
    @FXML private TableColumn<Programa, String> colIdiomaPrograma;
    @FXML private TableColumn<Programa, String> colModalidadPrograma;
    @FXML private TableColumn<Programa, String> colValorPrograma;

    // ---------- Tab Matriculas ----------
    @FXML private ComboBox<Estudiante> comboEstudianteMatricula;
    @FXML private ComboBox<Programa> comboProgramaMatricula;
    @FXML private DatePicker fechaInicioMatricula;
    @FXML private TextField txtDescuentoMatricula;
    @FXML private TextField txtObservacionesMatricula;
    @FXML private TableView<Matricula> tablaMatriculas;
    @FXML private TableColumn<Matricula, Integer> colNumeroMatricula;
    @FXML private TableColumn<Matricula, String> colEstudianteMatricula;
    @FXML private TableColumn<Matricula, String> colProgramaMatricula;
    @FXML private TableColumn<Matricula, String> colFechaMatricula;
    @FXML private TableColumn<Matricula, String> colValorMatricula;

    // ---------- Tab Consultas ----------
    @FXML private TextField txtTelefonoConsulta;
    @FXML private Label lblResultadoTelefono;
    @FXML private DatePicker fechaDesdeIngresos;
    @FXML private DatePicker fechaHastaIngresos;
    @FXML private Label lblResultadoIngresos;

    @FXML
    public void initialize() {
        // --- Tabla estudiantes ---
        colNombreEstudiante.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        colDocumentoEstudiante.setCellValueFactory(new PropertyValueFactory<>("documentoIdentidad"));
        colTelefonoEstudiante.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colCorreoEstudiante.setCellValueFactory(new PropertyValueFactory<>("correoElectronico"));
        tablaEstudiantes.setItems(listaEstudiantes);

        // --- Tabla programas ---
        colCodigoPrograma.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombrePrograma.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colIdiomaPrograma.setCellValueFactory(new PropertyValueFactory<>("idioma"));
        colModalidadPrograma.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getModalidad().toString()));
        colValorMatricula.setCellValueFactory(cell ->
                new SimpleStringProperty(formatearMoneda(cell.getValue().calcularValorFinal())));
        tablaProgramas.setItems(listaProgramas);

        comboTipoPrograma.setItems(FXCollections.observableArrayList(ProgramaFactory.TipoPrograma.values()));
        comboModalidadPrograma.setItems(FXCollections.observableArrayList(Modalidad.values()));

        // --- Tabla matriculas ---
        colNumeroMatricula.setCellValueFactory(new PropertyValueFactory<>("numeroMatricula"));
        colEstudianteMatricula.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getEstudiante().getNombreCompleto()));
        colProgramaMatricula.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getPrograma().getNombre()));
        colFechaMatricula.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getFechaInicio().toString()));
        colValorPrograma.setCellValueFactory(cell ->
                new SimpleStringProperty(formatearMoneda(cell.getValue().calcularValorBase())));
        tablaMatriculas.setItems(listaMatriculas);

        comboEstudianteMatricula.setItems(listaEstudiantes);
        comboProgramaMatricula.setItems(listaProgramas);
    }

    // ================= ESTUDIANTES =================
    @FXML
    private void registrarEstudiante() {
        try {
            Estudiante estudiante = new Estudiante(
                    txtNombreEstudiante.getText(),
                    txtDocumentoEstudiante.getText(),
                    txtTelefonoEstudiante.getText(),
                    txtCorreoEstudiante.getText(),
                    Integer.parseInt(txtEdadEstudiante.getText()),
                    LocalDate.now()
            );
            estudianteServicio.registrar(estudiante);
            listaEstudiantes.add(estudiante);
            limpiarCamposEstudiante();
        } catch (Exception e) {
            mostrarError("No se pudo registrar el estudiante: " + e.getMessage());
        }
    }

    private void limpiarCamposEstudiante() {
        txtNombreEstudiante.clear();
        txtDocumentoEstudiante.clear();
        txtTelefonoEstudiante.clear();
        txtCorreoEstudiante.clear();
        txtEdadEstudiante.clear();
    }

    // ================= PROGRAMAS =================
    @FXML
    private void registrarPrograma() {
        try {
            ProgramaFactory.TipoPrograma tipo = comboTipoPrograma.getValue();
            Modalidad modalidad = comboModalidadPrograma.getValue();
            if (tipo == null || modalidad == null) {
                mostrarError("Selecciona tipo y modalidad");
                return;
            }
            if (tipo == ProgramaFactory.TipoPrograma.PERSONALIZADO) {
                mostrarError("Los programas Personalizados requieren datos adicionales; "
                        + "usa el formulario de Personalizado (pendiente) o el PersonalizadoBuilder.");
                return;
            }
            // Factory Method: Basico o Intensivo.
            Programa programa = ProgramaFactory.crear(
                    tipo,
                    txtCodigoPrograma.getText(),
                    txtNombrePrograma.getText(),
                    txtIdiomaPrograma.getText(),
                    "",
                    Integer.parseInt(txtDuracionPrograma.getText()),
                    Double.parseDouble(txtValorPrograma.getText()),
                    Estado.ACTIVO,
                    modalidad
            );
            programaServicio.registrar(programa);
            listaProgramas.add(programa);
            limpiarCamposPrograma();
        } catch (Exception e) {
            mostrarError("No se pudo registrar el programa: " + e.getMessage());
        }
    }

    private void limpiarCamposPrograma() {
        txtCodigoPrograma.clear();
        txtNombrePrograma.clear();
        txtIdiomaPrograma.clear();
        txtDuracionPrograma.clear();
        txtValorPrograma.clear();
    }

    // ================= MATRICULAS =================
    @FXML
    private void registrarMatricula() {
        try {
            Estudiante estudiante = comboEstudianteMatricula.getValue();
            Programa programa = comboProgramaMatricula.getValue();
            LocalDate fecha = fechaInicioMatricula.getValue();
            if (estudiante == null || programa == null || fecha == null) {
                mostrarError("Estudiante, programa y fecha de inicio son obligatorios");
                return;
            }
            double descuento = txtDescuentoMatricula.getText().isBlank()
                    ? 0 : Double.parseDouble(txtDescuentoMatricula.getText());

            // Builder: valida programa obligatorio y descuento <= 30%,
            // y usa el Singleton GeneradorMatricula para el consecutivo.
            Matricula matricula = new MatriculaBuilder()
                    .estudiante(estudiante)
                    .programa(programa)
                    .fechaInicio(fecha)
                    .descuentoPorcentaje(descuento)
                    .observaciones(txtObservacionesMatricula.getText())
                    .build();

            matriculaServicio.registrar(matricula);
            listaMatriculas.add(matricula);
            limpiarCamposMatricula();
        } catch (Exception e) {
            mostrarError("No se pudo registrar la matricula: " + e.getMessage());
        }
    }

    private void limpiarCamposMatricula() {
        txtDescuentoMatricula.clear();
        txtObservacionesMatricula.clear();
        fechaInicioMatricula.setValue(null);
    }

    // ================= CONSULTAS =================
    @FXML
    private void consultarPorTelefono() {
        String telefono = txtTelefonoConsulta.getText();
        Optional<Estudiante> estudiante = estudianteServicio.buscarPorTelefono(telefono);
        if (estudiante.isEmpty()) {
            lblResultadoTelefono.setText("No se encontro un estudiante con ese telefono.");
            return;
        }
        try {
            boolean esPerfecto = consultaServicio.esNumeroPerfecto(Long.parseLong(telefono));
            lblResultadoTelefono.setText(
                    "Estudiante: " + estudiante.get().getNombreCompleto()
                            + " | ¿" + telefono + " es numero perfecto? "
                            + (esPerfecto ? "Si" : "No"));
        } catch (NumberFormatException e) {
            lblResultadoTelefono.setText(
                    "Estudiante: " + estudiante.get().getNombreCompleto()
                            + " (el telefono tiene caracteres no numericos, no se puede evaluar como numero perfecto)");
        }
    }

    @FXML
    private void calcularIngresos() {
        LocalDate desde = fechaDesdeIngresos.getValue();
        LocalDate hasta = fechaHastaIngresos.getValue();
        if (desde == null || hasta == null) {
            lblResultadoIngresos.setText("Selecciona ambas fechas.");
            return;
        }
        double total = matriculaServicio.calcularIngresosPorPeriodo(desde, hasta);
        lblResultadoIngresos.setText("Ingresos entre " + desde + " y " + hasta + ": $" + total);
    }

    // ================= UTIL =================
    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR, mensaje);
        alert.showAndWait();
    }

    private String formatearMoneda(double valor) {
        return String.format("$%,.0f", valor);
    }
}