package hospital;

import java.util.*;

public class Hospital {
    int capacidad = 300;
    
    //DOCTOR
    HashMap<Integer, LinkedList<Doctor>> map_doctores = new HashMap<>();
    ArrayList<Doctor> lista_doctores = new ArrayList<>();
    
    //PACIENTE
    HashMap<String, Paciente> map_paciente = new HashMap<>();
    LinkedList<Paciente> lista_pacientes_prioridad = new LinkedList<>();
    
    //HABITACION
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

    public void mostrarPacientesPorTriaje(LinkedList<Paciente> pacientitos, int triaje){
        if (triaje >=5){
            System.out.println("Ingreso de triaje incorrecto.");
            return;
        }
        System.out.println("Pacientes en Triaje: " + triaje);
        System.out.println("- - - - - - - - - - - - - - - - - -");
        for (Paciente paciente : pacientitos) {
            if (paciente.getTriage() == triaje){
                System.out.println("- Nombre: " + paciente.getDatos_paciente().getNombre());
                System.out.println("- Apellido: " + paciente.getDatos_paciente().getApellido());
                System.out.println("- RUT: " + paciente.getDatos_paciente().getRut());
                if (paciente.getNum_habitacion() != null) {
                    System.out.println("- Habitación: " + paciente.getNum_habitacion());
                }
                else{
                    System.out.println("- Sin habitación asignada.");
                }
                System.out.println("- - - - - - - - - - - - - - - - - -");
            }
        }
    }
    
       public Habitacion obtenerHabitacion(int num)
    {
        Habitacion habitacion_aux = habitaciones.get(num);
        return habitacion_aux;
    }
    
    public String obtenerMostrarHabitacion(Habitacion aux)
    {
        return "Número Habitación : " + aux.getNum_habitacion() + " Ocupado: " + aux.isOcupado() + " Cama 1: " + aux.getCama_1() + " Cama 2: " + aux.getCama_2();
    }
    
    public void añadirDoctorMapaArray(Doctor doctor_aux) 
    {
        int triage = doctor_aux.getTriage();

        // Si el triage no está en el mapa se crea la lista enlazada.
        if (!map_doctores.containsKey(triage)) 
        {
            map_doctores.put(triage, new LinkedList<>());
        }

        // Se añade el doctor a la lista enlazada de todas formas.
        map_doctores.get(triage).add(doctor_aux);
        
        lista_doctores.add(doctor_aux);
        
        System.out.println("Doctor añadido con éxito.");
    }
    
    public Doctor obtenerDoctor(int i)
    {
        Doctor aux_doctor = lista_doctores.get(i);
        return aux_doctor;
    }
    
    public ArrayList<Doctor> obtenerDoctor(String triaje_aux)
    {
        int triaje = Integer.parseInt(triaje_aux);
        ArrayList<Doctor> doctor_disponible_triaje = new ArrayList<>();
        
        LinkedList<Doctor> aux_triaje_lista = map_doctores.get(triaje);
        
        for (int i = 0; i < aux_triaje_lista.size(); i++)
        {
            Doctor doc_aux = aux_triaje_lista.get(i);
            if (doc_aux.isDisponible())
            {
                doctor_disponible_triaje.add(doc_aux);
            }
        }
        return doctor_disponible_triaje; 
    }
    
    public String mostrarDoctor(Doctor aux_doctor)
    {
        Persona datos = aux_doctor.getDatos_doctor();
        String disp;
        if (!aux_doctor.isDisponible())
        {
            disp = "Si";
        }
        else
        {
            disp = "No";
        }
        return "Doctor " + datos.getNombre() + " " + datos.getApellido() + " Disponible: " + disp + "\n";
    }

    public ArrayList<Doctor> crearDoctores(){
        // aca
        ArrayList<Doctor> listaDoctores = new ArrayList<>();
        Persona datDoct1 = new Persona("21.669.284-5", "Lucas", "Pinto");
        Persona datDoct2 = new Persona("21.655.474-4", "Inti", "Liberona");
        Persona datDoct3 = new Persona("20.360.437-8", "Martin", "Basulto");
        Persona datDoct4 = new Persona("21.404.381-5", "Juan", "Mercade");
        
        Doctor dc1 = new Doctor(datDoct1, 1);
        Doctor dc2 = new Doctor(datDoct2, 2);
        Doctor dc3 = new Doctor(datDoct3, 3);
        Doctor dc4 = new Doctor(datDoct4, 4);
        listaDoctores.add(dc1);
        listaDoctores.add(dc2);
        listaDoctores.add(dc3);
        listaDoctores.add(dc4);
        return listaDoctores;
    }

}
  

