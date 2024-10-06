package hospital;
import java.util.ArrayList;
public class Doctor extends Persona 
{
    private int triage;
    private int pacientes_max = 6;
    private int pacientes_actual = 0;
    private final ArrayList<Persona> lista_pacientes_atencion;
    private boolean disponible;

    public Doctor(String rut, String nombre, String apellido, int triaje) 
    {
        super(rut, nombre, apellido);
        this.triage = triaje;
        this.disponible = true;
        lista_pacientes_atencion = new ArrayList<>();
    }
    
        public Doctor(Persona pp, int triaje) 
    {
        
        this.triage = triaje;
        this.disponible = true;
        lista_pacientes_atencion = new ArrayList<>();
    }
        
    public Doctor(String rut, String nombre, String apellido) 
    {
        super(rut, nombre, apellido);
        this.triage = 0;
        this.disponible = true;
        lista_pacientes_atencion = new ArrayList<>();
    }
    
    //Propios
    public int getTriage() {
        return triage;
    }

    public void setTriage(int triage) {
        this.triage = triage;
    }

    public int getPacientes_max() {
        return pacientes_max;
    }

    public void setPacientes_max(int pacientes_max) {
        this.pacientes_max = pacientes_max;
    }

    public int getPacientes_actual() {
        return pacientes_actual;
    }

    public void setPacientes_actual(int pacientes_actual) {
        this.pacientes_actual = pacientes_actual;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    
    
    //Contrato Abstracta   
    @Override
    public String registrarse()
    {
        return "Gracias por trabajar con nosotros! Se añadirá el Doctor... ";
    }
    
    @Override
    public Object verAsignacion()
    {
        Object copy_pac = (Object) new ArrayList<>(lista_pacientes_atencion);
        return copy_pac;
    }

    @Override
    public String toString() {
    return "Doctor [RUT: " + getRut() + ", Nombre: " + getNombre() + " " + getApellido() + 
           ", Triage: " + triage + ", Pacientes Actual: " + pacientes_actual + 
           ", Pacientes Máx: " + pacientes_max + ", Disponible: " + disponible + "]";
    }

}