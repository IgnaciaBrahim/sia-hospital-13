package hospital;

import java.util.*;

public class Hospital {

    
    private HashMap<Integer, LinkedList<Doctor>> mapDoctores;
    
    private LinkedList<Paciente> listaPacientesPrioridad;

    private ArrayList<Habitacion> habitaciones;

    public Hospital() {
        this.mapDoctores = new HashMap<>();
        this.listaPacientesPrioridad = new LinkedList<>();
        this.habitaciones = new ArrayList<>();

        for (int i = 1; i <= 150; i++) {
            habitaciones.add(new Habitacion(i));
        }
    }


    public void añadirDoctor(Doctor doctor) {
        int triage = doctor.getTriage();
        if (!mapDoctores.containsKey(triage)) {
            mapDoctores.put(triage, new LinkedList<>());
        }
        mapDoctores.get(triage).add(doctor);
        System.out.println("Doctor añadido con éxito.");
    }

    public void asignarDoctor(Paciente paciente) {
        int triage = paciente.getTriage();
        LinkedList<Doctor> doctoresDisponibles = mapDoctores.get(triage);
        if (doctoresDisponibles != null && !doctoresDisponibles.isEmpty()) {
            for (Doctor doctor : doctoresDisponibles) {
                if (doctor.isDisponible()) {
                    doctor.setPacientes_actual(doctor.getPacientes_actual() + 1);
                    paciente.setMedico_asignado(doctor);
                    doctor.setDisponible(doctor.getPacientes_actual() < doctor.getPacientes_max());
                    System.out.println("Doctor asignado con éxito.");
                    return;
                }
            }
            System.out.println("No hay doctores disponibles en este momento.");
        } else {
            System.out.println("No hay doctores para el triaje solicitado.");
        }
    }


    public List<Paciente> consultarPacientesPorTriage(int triage) {
        List<Paciente> pacientes = new ArrayList<>();
        for (Paciente p : listaPacientesPrioridad) {
            if (p.getTriage() == triage) {
                pacientes.add(p);
            }
        }
        return pacientes;
    }

    public void verDisponibilidadDoctores() {
        for (Map.Entry<Integer, LinkedList<Doctor>> entry : mapDoctores.entrySet()) {
            int triage = entry.getKey();
            LinkedList<Doctor> doctores = entry.getValue();
            System.out.println("Triage: " + triage);
            for (Doctor doctor : doctores) {
                System.out.println("Doctor: " + doctor.getDatos_doctor().getNombre() + " - Disponible: " + doctor.isDisponible());
            }
        }
    }

    public void verEstadoHabitaciones() {
        for (Habitacion habitacion : habitaciones) {
            System.out.println("Habitación #" + habitacion.getNum_habitacion() + " - Ocupado: " + habitacion.isOcupado());
            if (habitacion.getCama_1() != null) {
                System.out.println("\tCama 1: " + habitacion.getCama_1().getDatos_paciente().getNombre());
            }
            if (habitacion.getCama_2() != null) {
                System.out.println("\tCama 2: " + habitacion.getCama_2().getDatos_paciente().getNombre());
            }
        }
    }

    public void registrarVisita(String rutPaciente) {
        Paciente paciente = buscarPacientePorRut(rutPaciente);
        if (paciente != null) {
            System.out.println("Visita registrada para el paciente: " + paciente.getDatos_paciente().getNombre());
        } else {
            System.out.println("Paciente no encontrado.");
        }
    }


    private Paciente buscarPacientePorRut(String rut) {
        for (Paciente p : listaPacientesPrioridad) {
            if (p.getDatos_paciente().getRut().equals(rut)) {
                return p;
            }
        }
        return null;
    }
}