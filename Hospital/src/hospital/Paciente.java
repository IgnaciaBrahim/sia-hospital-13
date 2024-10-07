package hospital;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Clase que representa a un paciente en el sistema de gestión hospitalaria.
 * Hereda de la clase `Persona` y contiene atributos adicionales específicos
 * para los pacientes, como su edad, sexo, nivel de triaje, médico asignado,
 * estado de atención, número de habitación, y tiempo de registro.
 * 
 * La clase también proporciona métodos para obtener y modificar estos atributos,
 * así como sobreescribir métodos de la clase padre `Persona`.
 */
public class Paciente extends Persona{
    private int edad;
    private int sexo; //(0M - 1F)
    private int triage; 
    private Doctor medico_asignado;
    private String estado_atencion;
    private String num_habitacion = null; 
    private LocalDateTime tiempoActual;

    /**
     * Constructor que inicializa un paciente con datos completos, incluyendo su RUT,
     * nombre, apellido, edad, sexo, nivel de triaje, y la fecha y hora actuales.
     * 
     * @param rut El RUT del paciente.
     * @param nombre El nombre del paciente.
     * @param apellido El apellido del paciente.
     * @param edad La edad del paciente.
     * @param sexo El sexo del paciente (0 para masculino, 1 para femenino).
     * @param triaje El nivel de triaje asignado al paciente.
     * @param tiempoActual El tiempo actual de registro del paciente.
     */
    public Paciente(String rut, String nombre, String apellido, int edad, int sexo, int triaje, LocalDateTime tiempoActual){
        super(rut, nombre, apellido);
        this.edad = edad;
        this.sexo = sexo;
        this.triage = triaje;
        this.medico_asignado = null;
        this.estado_atencion = null;
        this.num_habitacion = null;
        this.tiempoActual = tiempoActual;
    }

     /**
     * Constructor que inicializa un paciente con solo su RUT, nombre y apellido.
     * Otros atributos como edad, sexo y triaje se inicializan con valores por defecto.
     * 
     * @param rut El RUT del paciente.
     * @param nombre El nombre del paciente.
     * @param apellido El apellido del paciente.
     */
    public Paciente(String rut, String nombre, String apellido) {
        super(rut, nombre, apellido);
        this.edad = 0;
        this.sexo = 0;
        this.triage = 1;
        this.medico_asignado = null;
        this.estado_atencion = "NO";
        this.num_habitacion = null;
        this.tiempoActual = LocalDateTime.now();
    }

     /**
     * Constructor que inicializa un paciente con datos completos excepto la asignación de un médico.
     * 
     * @param rut El RUT del paciente.
     * @param nombre El nombre del paciente.
     * @param apellido El apellido del paciente.
     * @param edad La edad del paciente.
     * @param sexo El sexo del paciente.
     * @param triaje El nivel de triaje asignado al paciente.
     */
    public Paciente(String rut, String nombre, String apellido, int edad, int sexo, int triage){
        super(rut, nombre, apellido);
        this.edad = edad;
        this.sexo = sexo;
        this.triage = triage;
        this.medico_asignado = null;
        this.estado_atencion = "NO";
        this.num_habitacion = null;
        this.tiempoActual = LocalDateTime.now();
    }

     /**
     * Constructor que inicializa un paciente con datos completos, incluyendo la asignación de un médico y habitación.
     * 
     * @param rut El RUT del paciente.
     * @param nombre El nombre del paciente.
     * @param apellido El apellido del paciente.
     * @param edad La edad del paciente.
     * @param sexo El sexo del paciente.
     * @param triage El nivel de triaje asignado al paciente.
     * @param medico_asignado El médico asignado al paciente.
     * @param estado_atencion El estado de atención del paciente.
     * @param num_habitacion El número de habitación asignado al paciente.
     */
    public Paciente(String rut, String nombre, String apellido, int edad, int sexo, int triage, Doctor medico_asignado, String estado_atencion, String num_habitacion) {
        super(rut, nombre, apellido);
        this.edad = edad;
        this.sexo = sexo;
        this.triage = triage;
        this.medico_asignado = medico_asignado;
        this.estado_atencion = estado_atencion;
        this.num_habitacion = num_habitacion;
        this.tiempoActual = LocalDateTime.now();
    }
    
    // Métodos getter y setter para los atributos 
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public int getTriage() {
        return triage;
        
    }

    public void setTriage(int triage) {
        this.triage = triage;
    }

    public Doctor getMedico_asignado() {
        return medico_asignado;
    }

    public void setMedico_asignado(Doctor medico_asignado) {
        this.medico_asignado = medico_asignado;
    }

    public String getEstado_atencion() {
        return estado_atencion;
    }

    public void setEstado_atencion(String estado_atencion) {
        this.estado_atencion = estado_atencion;
    }

    public String getNum_habitacion() {
        return num_habitacion;
    }

    public void setNum_habitacion(String num_habitacion) {
        this.num_habitacion = num_habitacion;
    }
    
    public LocalDateTime getTiempoActual(){
        return tiempoActual;
    }
    
    public void setTiempoActual(LocalDateTime tiempoActual){
        this.tiempoActual = tiempoActual;
    }
    
    // Métodos sobrescritos de la clase `Persona`

    /**
     * Método sobrescrito de la clase `Persona` que registra al paciente.
     * 
     * @return Un mensaje de agradecimiento por registrarse.
     */
    @Override
    public String registrarse()
    {
        return "Gracias por venir al Hospital_VIJ. Se registrará al paciente ";
    }
    
    /**
     * Método sobrescrito que muestra la asignación actual del paciente.
     * 
     * @return El médico asignado al paciente, o `null` si no hay médico.
     */
    @Override 
    public Object verAsignacion()
    {
        Object medico = (Object) medico_asignado;
        return medico;
    }

    /**
     * Método sobrescrito `toString()` para generar una cadena representativa
     * del estado del paciente, incluyendo su nombre, RUT, edad, sexo, nivel de triaje,
     * médico asignado, estado de atención y habitación.
     * 
     * @return Una representación en formato de texto del paciente.
     */
    @Override
    public String toString() {
    return "Paciente [RUT: " + getRut() + ", Nombre: " + getNombre() + " " + getApellido() + 
           ", Edad: " + edad + ", Sexo: " + (sexo == 0 ? "Masculino" : "Femenino") + 
           ", Triage: " + triage + ", Médico Asignado: " + 
           (medico_asignado != null ? medico_asignado.getNombre() : "Sin Doctor") + 
           ", Estado: " + estado_atencion + ", Habitación: " + 
           (num_habitacion != null ? num_habitacion : "Sin Habitación") + 
           ", Hora de Atención: " + tiempoActual.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "]";
    }

}
