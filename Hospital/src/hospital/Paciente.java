package hospital;

//import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class Paciente {
    Persona datos_paciente;
    private int edad;
    private int sexo; //(0M - 1F)
    private int triage; 
    Doctor medico_asignado;
    private String estado_atencion;
    private String num_habitacion = null; //debe ser un string de verdad? // Si ta bien
    private LocalDateTime tiempoActual;

    public Paciente(Persona datosPac, int edad, int sexo, int triaje, LocalDateTime tiempoActual){
        this.datos_paciente = datosPac;
        this.edad = edad;
        this.sexo = sexo;
        this.triage = triaje;
        this.medico_asignado = null;
        this.estado_atencion = null;
        this.num_habitacion = null;
        this.tiempoActual = tiempoActual;
    }
    public Paciente() {
        this.datos_paciente = null;
        this.edad = 0;
        this.sexo = 0;
        this.triage = 1;
        this.medico_asignado = null;
        this.estado_atencion = "NO";
        this.num_habitacion = null;
    }

    public Paciente(Persona datos_paciente, int edad, int sexo, int triage){
        this.datos_paciente = datos_paciente;
        this.edad = edad;
        this.sexo = sexo;
        this.triage = triage;
        this.medico_asignado = null;
        this.estado_atencion = "NO";
        this.num_habitacion = null;
    }

    public Paciente(Persona datos_paciente, int edad, int sexo, int triage, Doctor medico_asignado, String estado_atencion, String num_habitacion) {
        // Había un dato llamado num_cama, lo cambié por num_habitación pero sigue la duda de qué hacer con la cama
        this.datos_paciente = datos_paciente;
        this.edad = edad;
        this.sexo = sexo;
        this.triage = triage;
        this.medico_asignado = medico_asignado;
        this.estado_atencion = estado_atencion;
        this.num_habitacion = num_habitacion;
    }

    public Persona getDatos_paciente() {
        return datos_paciente;
    }

    public void setDatos_paciente(Persona datos_paciente) {
        this.datos_paciente = datos_paciente;
    }

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

    public void agregarPaciente(Persona datosPac, int edad, int sexo, int triaje, LinkedList<Paciente> listaPacientes)  {
        LocalDateTime tiempoActual = LocalDateTime.now();
        DateTimeFormatter formatoTiempo = DateTimeFormatter.ofPattern("HH:mm:ss");
        String tiempoFormateado = tiempoActual.format(formatoTiempo);

        Paciente pac = new Paciente(datosPac, edad, sexo, triaje, tiempoActual);
        System.out.println("Paciente ingresado a las " + tiempoFormateado);

        // Insertar el paciente en la lista de manera ordenada
        int posicionInsertar = 0;
        for (int i = 0; i < listaPacientes.size(); i++) {
            Paciente pacienteExistente = listaPacientes.get(i);
            if (pacienteExistente.getTriage() < triaje || 
               (pacienteExistente.getTriage() == triaje && 
                pacienteExistente.getTiempoActual().isBefore(tiempoActual))) {
                posicionInsertar = i + 1;
            } else {
                break;
            }
        }
        listaPacientes.add(posicionInsertar, pac);
       
    }
}