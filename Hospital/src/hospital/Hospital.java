package hospital;

import java.util.*;

public class Hospital {
    //HashMap doctores =
    //KEY = "Traumatología"
    //VALUE = "Doctor1, Doctor2..."
    int capacidad = 300;
    HashMap<Integer, LinkedList<Doctor>> map_doctores = new HashMap<>();
    HashMap<String, Paciente> map_paciente = new HashMap<>();
    LinkedList<Paciente> lista_pacientes_prioridad = new LinkedList<>();
    ArrayList<Habitacion> habitaciones = new ArrayList<>();

    public HashMap<String, Paciente> getMap_paciente() {
        return map_paciente;
    }

    public void setMap_paciente(HashMap<String, Paciente> map_paciente) {
        this.map_paciente = map_paciente;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public HashMap<Integer, LinkedList<Doctor>> getMap_doctores() {
        return map_doctores;
    }

    public void setMap_doctores(HashMap<Integer, LinkedList<Doctor>> map_doctores) {
        this.map_doctores = map_doctores;
    }

    public LinkedList<Paciente> getLista_pacientes_prioridad() 
    {
        //copia
        return new LinkedList<>(lista_pacientes_prioridad);
    }

    public void setLista_pacientes_prioridad(LinkedList<Paciente> lista_pacientes_prioridad) {
        this.lista_pacientes_prioridad = lista_pacientes_prioridad;
    }

    public ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(ArrayList<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }
    
    public Paciente buscarPacienteRut(String rut)
    {
        Paciente aux_paciente = map_paciente.get(rut);
        return aux_paciente;
    }
    
    public void borrarPacienteCama(Paciente aux_paciente)
    {
        for (int i = 0; i < habitaciones.size(); i++) 
        {
            Habitacion habitacion = habitaciones.get(i);
            if (habitacion.getCama_1() != null && habitacion.getCama_1().equals(aux_paciente))
            {
                habitacion.setCama_1(null);
                break;
            }
            else if (habitacion.getCama_2() != null && habitacion.getCama_2().equals(aux_paciente))
            {
                habitacion.setCama_2(null);
                break;
            }
        }
    }

    public void borrarPaciente(String rut, Paciente aux_paciente)
    {
        map_paciente.remove(rut);
        lista_pacientes_prioridad.remove(aux_paciente);
        if (aux_paciente.getNum_habitacion() != null)
        {
            borrarPacienteCama(aux_paciente);
        }
    }


public void asignarDoctorAPaciente(Paciente paciente) {
    int triage = paciente.getTriage();
    LinkedList<Doctor> doctoresDisponibles = map_doctores.get(triage);

    if (doctoresDisponibles != null && !doctoresDisponibles.isEmpty()) {
        for (Doctor doctor : doctoresDisponibles) {
            if (doctor.isDisponible()) {
                doctor.setPacientes_actual(doctor.getPacientes_actual() + 1);
                paciente.setMedico_asignado(doctor);
                doctor.setDisponible(doctor.getPacientes_actual() < doctor.getPacientes_max());
                System.out.println("Doctor asignado con éxito a " + paciente.getDatos_paciente().getNombre());
                return;
            }
        }
        System.out.println("No hay doctores disponibles en este momento.");
    } else {
        System.out.println("No hay doctores para el triaje solicitado.");
    }
}


    public void asignarHabitacionAPaciente(Paciente paciente) {
        for (Habitacion habitacion : habitaciones) {
            if (!habitacion.isOcupado()) {
                if (habitacion.getCama_1() == null) {
                    habitacion.setCama_1(paciente);
                    habitacion.setOcupado(true);
                    paciente.setNum_habitacion(String.valueOf(habitacion.getNum_habitacion()));
                    System.out.println("Habitación " + habitacion.getNum_habitacion() + " asignada a " + paciente.getDatos_paciente().getNombre());
                    return;
                } else if (habitacion.getCama_2() == null) {
                    habitacion.setCama_2(paciente);
                    habitacion.setOcupado(true);
                    paciente.setNum_habitacion(String.valueOf(habitacion.getNum_habitacion()));
                    System.out.println("Habitación " + habitacion.getNum_habitacion() + " asignada a " + paciente.getDatos_paciente().getNombre());
                    return;
                }
            }
        }
        System.out.println("No hay habitaciones disponibles.");
}

}
   
    



/*
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
*/
