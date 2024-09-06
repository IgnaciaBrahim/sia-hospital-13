package hospital;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public void mostrarPacientesPorTriaje(int triaje){
        if (triaje >=5){
            System.out.println("Ingreso de triaje incorrecto.");
            return;
        }
        System.out.println("Pacientes en Triaje: " + triaje);
        System.out.println("- - - - - - - - - - - - - - - - - -");
        for (Paciente paciente : lista_pacientes_prioridad) {
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
        if (num >= 1 && num <= habitaciones.size()) {
            return habitaciones.get(num - 1);
        } else {
            System.out.println("Número de habitación no válido.");
            return null;
        }
    }
    
    public String obtenerMostrarHabitacion(Habitacion aux)
    {
        if (aux.getCama_1() != null && aux.getCama_2() != null)
        {
            return "Número Habitación : " + aux.getNum_habitacion() + " Ocupado: " + aux.isOcupado() + " Cama 1: " + aux.getCama_1().getDatos_paciente().getNombre() + " Cama 2: " + aux.getCama_2().getDatos_paciente().getNombre();
        }
        return "Número Habitación : " + aux.getNum_habitacion() + " Ocupado: " + aux.isOcupado() + " Cama 1: Nadie"  + " Cama 2: Nadie";
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
        if (aux_doctor.isDisponible())
        {
            disp = "Si";
        }
        else
        {
            disp = "No";
        }
        return "Doctor " + datos.getNombre() + " " + datos.getApellido() + " Disponible: " + disp + "\n";
    }

    public void crearDoctores(){
        // aca
        //ArrayList<Doctor> listaDoctores = new ArrayList<>();
        Persona datDoct1 = new Persona("21.669.284-5", "Lucas", "Pinto");
        Persona datDoct2 = new Persona("21.655.474-4", "Inti", "Liberona");
        Persona datDoct3 = new Persona("20.360.437-8", "Martin", "Basulto");
        Persona datDoct4 = new Persona("21.404.381-5", "Juan", "Mercade");
        
        Doctor dc1 = new Doctor(datDoct1, 1);
        Doctor dc2 = new Doctor(datDoct2, 2);
        Doctor dc3 = new Doctor(datDoct3, 3);
        Doctor dc4 = new Doctor(datDoct4, 4);
        lista_doctores.add(dc1);
        lista_doctores.add(dc2);
        lista_doctores.add(dc3);
        lista_doctores.add(dc4);
        
        //preparar mapa
        LinkedList<Doctor> triaje_1 = new LinkedList<>();
        LinkedList<Doctor> triaje_2 = new LinkedList<>();
        LinkedList<Doctor> triaje_3 = new LinkedList<>();
        LinkedList<Doctor> triaje_4 = new LinkedList<>();
        
        //add doctor to linked list:
        triaje_1.add(dc1);
        triaje_2.add(dc2);
        triaje_3.add(dc3);
        triaje_4.add(dc4);
        
        //añadir map doctor
        map_doctores.put(dc1.getTriage(), triaje_1);
        map_doctores.put(dc2.getTriage(), triaje_2);
        map_doctores.put(dc3.getTriage(), triaje_3);
        map_doctores.put(dc4.getTriage(), triaje_4);
    }
    
    public void crear_Habitaciones()
    {
        //solo hay arrlist:
        //ArrayList<Habitacion> habitaciones = new ArrayList<>();
        /*
            private int num_habitacion; //#1 a #150
            private Paciente cama_1;
            private Paciente cama_2;
            private boolean ocupado;
        
        constructor:
        public Habitacion(int num_habitacion) 
        {
        this.num_habitacion = num_habitacion;
        this.cama_1 = null;
        this.cama_2 = null;
        this.ocupado = false;
        }
        */
        
        for (int i = 1; i < 151; i++)
        {
            Habitacion aux = new Habitacion(i);
            habitaciones.add(aux);
            obtenerMostrarHabitacion(aux);
        } 
    }
    
        public void agregarPaciente(Persona datosPac, int edad, int sexo, int triaje)  {
        LocalDateTime tiempo_actual = LocalDateTime.now();
        DateTimeFormatter formatoTiempo = DateTimeFormatter.ofPattern("HH:mm:ss");
        String tiempoFormateado = tiempo_actual.format(formatoTiempo);

        Paciente pac = new Paciente(datosPac, edad, sexo, triaje, tiempo_actual);
        System.out.println("Paciente ingresado a las " + tiempoFormateado);

        // Insertar el paciente en la lista de manera ordenada
        int posicionInsertar = 0;
        for (int i = 0; i < lista_pacientes_prioridad.size(); i++) {
            Paciente pacienteExistente = lista_pacientes_prioridad.get(i);
            if (pacienteExistente.getTriage() < triaje || 
               (pacienteExistente.getTriage() == triaje && 
                pacienteExistente.getTiempoActual().isBefore(tiempo_actual))) {
                posicionInsertar = i + 1;
            } else {
                break;
            }
        }
        //Implementación de insersion ordenada en arrlist.
        lista_pacientes_prioridad.add(posicionInsertar, pac);
        map_paciente.put(pac.getDatos_paciente().getRut(), pac);
       
    }
        
    //sobreescribi
    public void agregarPaciente(Paciente pac)  {
        LocalDateTime tiempo_actual = LocalDateTime.now();
        DateTimeFormatter formatoTiempo = DateTimeFormatter.ofPattern("HH:mm:ss");
        String tiempoFormateado = tiempo_actual.format(formatoTiempo);

        System.out.println("Paciente ingresado a las " + tiempoFormateado);

        // Insertar el paciente en la lista de manera ordenada
        int posicionInsertar = 0;
        for (int i = 0; i < lista_pacientes_prioridad.size(); i++) {
            Paciente pacienteExistente = lista_pacientes_prioridad.get(i);
            if (pacienteExistente.getTriage() < pac.getTriage() || 
               (pacienteExistente.getTriage() == pac.getTriage() && 
                pacienteExistente.getTiempoActual().isBefore(tiempo_actual))) {
                posicionInsertar = i + 1;
            } else {
                break;
            }
        }
        //Implementación de insersion ordenada en arrlist.
        lista_pacientes_prioridad.add(posicionInsertar, pac);
        map_paciente.put(pac.getDatos_paciente().getRut(), pac);
       
    }

    public Paciente buscarPacientePorCama(String numHabitacion) {
        for (Paciente paciente : lista_pacientes_prioridad) {
            if (paciente.getNum_habitacion() != null && paciente.getNum_habitacion().equals(numHabitacion)) {
                return paciente;
            }
        }
        return null; // Si no se encuentra
    }
    
    public void crear_Pacientes()
    {
        //ficticios:
        
        Persona p1 = new Persona("21.275.186-3", "Ignacia", "Brahim");
        Persona p2 = new Persona("21.108.465-0", "Carlos", "Abarza");
        Persona p3 = new Persona("8.285.871-7", "Elizabeth", "Brahim");
        Persona p4 = new Persona("10.577.195-9", "Claudio", "Cubillos");
        Persona p5 = new Persona("10.273.813-6", "Laura", "Griffiths");
        
        Paciente pac1 = new Paciente(p1, 21, 1, 3);
        Paciente pac2 = new Paciente(p2, 21, 0, 5);
        Paciente pac3 = new Paciente(p3, 52, 1, 1);
        Paciente pac4 = new Paciente(p4, 50, 0, 2);
        Paciente pac5 = new Paciente(p5, 63, 1, 4);
        
        agregarPaciente(pac1);
        agregarPaciente(pac2);
        agregarPaciente(pac3);
        agregarPaciente(pac4);
        agregarPaciente(pac5);
        map_paciente.put(pac1.getDatos_paciente().getRut(), pac1);
        map_paciente.put(pac2.getDatos_paciente().getRut(), pac2);
        map_paciente.put(pac3.getDatos_paciente().getRut(), pac3);
        map_paciente.put(pac4.getDatos_paciente().getRut(), pac4);
        map_paciente.put(pac5.getDatos_paciente().getRut(), pac5);
    }
}
  

