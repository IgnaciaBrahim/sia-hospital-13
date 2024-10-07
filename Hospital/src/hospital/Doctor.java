package hospital;

import java.util.ArrayList;

/**
 * La clase Doctor representa un doctor que trabaja en el hospital y está encargado de atender pacientes.
 * Cada doctor tiene un nivel de triaje asignado, el cual indica la gravedad de los casos que puede atender.
 * Los doctores también tienen un límite máximo de pacientes que pueden atender simultáneamente.
 * La clase extiende de Persona, heredando atributos como el RUT, nombre y apellido.
 * Además, implementa métodos que permiten manejar la disponibilidad del doctor y la asignación de pacientes.
 */
public class Doctor extends Persona 
{
    // Nivel de triaje que el doctor está capacitado para atender (1 a 5).
    private int triage;

    // Máximo de pacientes que el doctor puede atender simultáneamente.
    private int pacientes_max = 6;

    // Número de pacientes que el doctor está atendiendo actualmente.
    private int pacientes_actual = 0;

    // Lista de personas (pacientes) asignadas al doctor para su atención.
    private final ArrayList<Persona> lista_pacientes_atencion;

    // Indica si el doctor está disponible para atender nuevos pacientes.
    private boolean disponible;

    /**
     * Constructor que inicializa los atributos del doctor, asignando el nivel de triaje, el RUT, nombre y apellido.
     * @param rut El RUT del doctor.
     * @param nombre El nombre del doctor.
     * @param apellido El apellido del doctor.
     * @param triaje El nivel de triaje asignado al doctor.
     */
    public Doctor(String rut, String nombre, String apellido, int triaje) {
        super(rut, nombre, apellido);
        this.triage = triaje;
        this.disponible = true;
        lista_pacientes_atencion = new ArrayList<>();
    }
    
    /**
     * Constructor que recibe una instancia de Persona para asignar un nivel de triaje.
     * @param pp Instancia de la clase Persona.
     * @param triaje Nivel de triaje asignado al doctor.
     */
    public Doctor(Persona pp, int triaje) {
        this.triage = triaje;
        this.disponible = true;
        lista_pacientes_atencion = new ArrayList<>();
    }

    /**
     * Constructor que inicializa un doctor sin un nivel de triaje especificado.
     * @param rut El RUT del doctor.
     * @param nombre El nombre del doctor.
     * @param apellido El apellido del doctor.
     */
    public Doctor(String rut, String nombre, String apellido) {
        super(rut, nombre, apellido);
        this.triage = 0;
        this.disponible = true;
        lista_pacientes_atencion = new ArrayList<>();
    }

    // Métodos Getters y Setters

    /**
     * Obtiene el nivel de triaje del doctor.
     * @return Nivel de triaje del doctor.
     */
    public int getTriage() {
        return triage;
    }

    /**
     * Establece el nivel de triaje del doctor.
     * @param triage El nivel de triaje a asignar.
     */
    public void setTriage(int triage) {
        this.triage = triage;
    }

    /**
     * Obtiene el número máximo de pacientes que el doctor puede atender.
     * @return Máximo número de pacientes.
     */
    public int getPacientes_max() {
        return pacientes_max;
    }

    /**
     * Establece el número máximo de pacientes que el doctor puede atender.
     * @param pacientes_max Número máximo de pacientes.
     */
    public void setPacientes_max(int pacientes_max) {
        this.pacientes_max = pacientes_max;
    }

    /**
     * Obtiene el número actual de pacientes que el doctor está atendiendo.
     * @return Número actual de pacientes.
     */
    public int getPacientes_actual() {
        return pacientes_actual;
    }

    /**
     * Establece el número de pacientes que el doctor está atendiendo actualmente.
     * @param pacientes_actual Número de pacientes actuales.
     */
    public void setPacientes_actual(int pacientes_actual) {
        this.pacientes_actual = pacientes_actual;
    }

    /**
     * Verifica si el doctor está disponible para atender nuevos pacientes.
     * @return true si el doctor está disponible, false si no lo está.
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * Establece si el doctor está disponible para atender nuevos pacientes.
     * @param disponible Estado de disponibilidad del doctor.
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    // Métodos sobreescritos de la clase abstracta Persona

    /**
     * Registra al doctor en el sistema del hospital.
     * @return Un mensaje de registro exitoso.
     */
    @Override
    public String registrarse() {
        return "Gracias por trabajar con nosotros! Se añadirá el Doctor... ";
    }

    /**
     * Retorna una copia de la lista de pacientes que el doctor está atendiendo actualmente.
     * @return Una lista de pacientes asignados al doctor.
     */
    @Override
    public Object verAsignacion() {
        Object copy_pac = (Object) new ArrayList<>(lista_pacientes_atencion);
        return copy_pac;
    }

    /**
     * Representación en formato de cadena de texto del doctor, mostrando su RUT, nombre, apellido,
     * nivel de triaje, número actual y máximo de pacientes, y disponibilidad.
     * @return Cadena con la información del doctor.
     */
    @Override
    public String toString() {
        return "Doctor [RUT: " + getRut() + ", Nombre: " + getNombre() + " " + getApellido() + 
               ", Triage: " + triage + ", Pacientes Actual: " + pacientes_actual + 
               ", Pacientes Máx: " + pacientes_max + ", Disponible: " + disponible + "]";
    }
}
