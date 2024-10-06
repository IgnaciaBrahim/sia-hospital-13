package hospital;
import java.time.LocalDateTime;

public class Paciente extends Persona{
    private int edad;
    private int sexo; //(0M - 1F)
    private int triage; 
    private Doctor medico_asignado;
    private String estado_atencion;
    private String num_habitacion = null; 
    private LocalDateTime tiempoActual;

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
    
    //Setter + Getter Clase 
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
    
    //Contrato
    @Override
    public String registrarse()
    {
        return "Gracias por venir al Hospital_VIJ. Se registrará al paciente ";
    }
    
    @Override 
    public Object verAsignacion()
    {
        Object medico = (Object) medico_asignado;
        return medico;
    }
}
