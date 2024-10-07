package hospital;

/**
 * La clase Habitacion representa una habitación de un hospital, la cual puede contener dos camas
 * para pacientes. Esta clase se encarga de gestionar la ocupación de la habitación y los pacientes
 * asignados a cada cama.
 */
public class Habitacion {

    /** Número de la habitación, que va del #1 al #150 en el hospital */
    private int num_habitacion;

    /** Paciente asignado a la cama 1 */
    private Paciente cama_1;

    /** Paciente asignado a la cama 2 */
    private Paciente cama_2;

    /** Estado de ocupación de la habitación (true si está ocupada, false si está disponible) */
    private boolean ocupado;

    /**
     * Constructor que inicializa la habitación con un número dado.
     * La habitación comienza sin pacientes asignados y está disponible (no ocupada).
     *
     * @param num_habitacion Número de la habitación en el hospital.
     */
    public Habitacion(int num_habitacion) {
        this.num_habitacion = num_habitacion;
        this.cama_1 = null;
        this.cama_2 = null;
        this.ocupado = false;
    }

    /**
     * Obtiene el número de la habitación.
     *
     * @return Número de la habitación.
     */
    public int getNum_habitacion() {
        return num_habitacion;
    }

    /**
     * Establece el número de la habitación.
     *
     * @param num_habitacion Número a asignar a la habitación.
     */
    public void setNum_habitacion(int num_habitacion) {
        this.num_habitacion = num_habitacion;
    }

    /**
     * Obtiene el paciente asignado a la cama 1.
     *
     * @return Paciente asignado a la cama 1 o null si la cama está vacía.
     */
    public Paciente getCama_1() {
        return cama_1;
    }

    /**
     * Asigna un paciente a la cama 1.
     *
     * @param cama_1 Paciente a asignar a la cama 1.
     */
    public void setCama_1(Paciente cama_1) {
        this.cama_1 = cama_1;
    }

    /**
     * Obtiene el paciente asignado a la cama 2.
     *
     * @return Paciente asignado a la cama 2 o null si la cama está vacía.
     */
    public Paciente getCama_2() {
        return cama_2;
    }

    /**
     * Asigna un paciente a la cama 2.
     *
     * @param cama_2 Paciente a asignar a la cama 2.
     */
    public void setCama_2(Paciente cama_2) {
        this.cama_2 = cama_2;
    }

    /**
     * Verifica si la habitación está ocupada.
     *
     * @return true si la habitación está ocupada, false si está disponible.
     */
    public boolean isOcupado() {
        return ocupado;
    }

    /**
     * Establece el estado de ocupación de la habitación.
     *
     * @param ocupado true para marcar la habitación como ocupada, false para marcarla como disponible.
     */
    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
}
