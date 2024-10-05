package hospital;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Hospital {
    
    //DOCTOR
    private final HashMap<Integer, LinkedList<Doctor>> map_doctores = new HashMap<>();
    private final ArrayList<Doctor> lista_doctores = new ArrayList<>();
    
    //PACIENTE
    private final HashMap<String, Paciente> map_paciente = new HashMap<>();
    private final LinkedList<Paciente> lista_pacientes_prioridad = new LinkedList<>();
    
    //HABITACION
    ArrayList<Habitacion> habitaciones = new ArrayList<>();
    
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
                habitacion.setOcupado(false);
                break;
            }
            else if (habitacion.getCama_2() != null && habitacion.getCama_2().equals(aux_paciente))
            {
                habitacion.setCama_2(null);
                habitacion.setOcupado(false);
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
                    System.out.println("Doctor " + doctor.obtenerDatos() + " asignado con éxito a " + paciente.obtenerDatos());
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
                    paciente.setNum_habitacion(String.valueOf(habitacion.getNum_habitacion()));
                    System.out.println("Habitación " + habitacion.getNum_habitacion() + " asignada a " + paciente.obtenerDatos());
                    return;
                } else if (habitacion.getCama_2() == null) {
                    habitacion.setCama_2(paciente);
                    habitacion.setOcupado(true);
                    paciente.setNum_habitacion(String.valueOf(habitacion.getNum_habitacion()));
                    System.out.println("Habitación " + habitacion.getNum_habitacion() + " asignada a " + paciente.obtenerDatos());
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
                System.out.println("- Nombre: " + paciente.getNombre());
                System.out.println("- Apellido: " + paciente.getApellido());
                System.out.println("- RUT: " + paciente.getRut());
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
        if (aux.getCama_1() != null && aux.getCama_2() == null)
        {
            return "Número Habitación : " + aux.getNum_habitacion() + " Ocupado: " + aux.isOcupado() + " Cama 1: " + aux.getCama_1().obtenerDatos() + " Cama 2: Nadie";
        }
        else if (aux.getCama_2() != null && aux.getCama_1() == null)
        {
            return "Número Habitación : " + aux.getNum_habitacion() + " Ocupado: " + aux.isOcupado() + " Cama 1: Nadie" + " Cama 2: " + aux.getCama_2().obtenerDatos();
        }
        else if (aux.getCama_2() != null && aux.getCama_1() != null)
        {
            return "Número Habitación : " + aux.getNum_habitacion() + " Ocupado: " + aux.isOcupado() + " Cama 1: " + aux.getCama_1().obtenerDatos() + " Cama 2: " + aux.getCama_2().obtenerDatos();
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
    }
    
    public void eliminarDoctorMapaArray(Doctor doctor_aux) 
    {
        int triage = doctor_aux.getTriage();
        LinkedList<Doctor> doctores_triaje = map_doctores.get(triage);
        doctores_triaje.remove(doctor_aux);
        lista_doctores.remove(doctor_aux);
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
        String disp;
        if (aux_doctor.isDisponible())
        {
            disp = "Si";
        }
        else
        {
            disp = "No";
        }
        return "Doctor " + aux_doctor.obtenerDatos() + " Disponible: " + disp + "\n";
    }
    
    //Sobreescritura
    public String mostrarDoctor(Doctor aux_doctor, int i)
    {
        return i + ". " + aux_doctor.obtenerDatos() + " Triaje: " + aux_doctor.getTriage();
    }

    public void crearDoctores(){
        // aca
        //ArrayList<Doctor> listaDoctores = new ArrayList<>();
        Doctor datDoct1 = new Doctor("21.669.284-5", "Lucas", "Pinto");
        Doctor datDoct2 = new Doctor("21.655.474-4", "Inti", "Liberona");
        Doctor datDoct3 = new Doctor("20.360.437-8", "Martin", "Basulto");
        Doctor datDoct4 = new Doctor("21.404.381-5", "Juan", "Mercade");
        Doctor datDoct5 = new Doctor("10.045.209-K", "Viviana", "Suazo");
        
        lista_doctores.add(datDoct1);
        lista_doctores.add(datDoct2);
        lista_doctores.add(datDoct3);
        lista_doctores.add(datDoct4);
        lista_doctores.add(datDoct5);
        
        //preparar mapa
        LinkedList<Doctor> triaje_1 = new LinkedList<>();
        LinkedList<Doctor> triaje_2 = new LinkedList<>();
        LinkedList<Doctor> triaje_3 = new LinkedList<>();
        LinkedList<Doctor> triaje_4 = new LinkedList<>();
        LinkedList<Doctor> triaje_5 = new LinkedList<>();
        
        datDoct1.setTriage(1);
        datDoct2.setTriage(2);
        datDoct3.setTriage(3);
        datDoct4.setTriage(4);
        datDoct5.setTriage(5);
        
        
        //add doctor to linked list:
        triaje_1.add(datDoct1);
        triaje_2.add(datDoct2);
        triaje_3.add(datDoct3);
        triaje_4.add(datDoct4);
        triaje_5.add(datDoct5);
        
        //añadir map doctor
        map_doctores.put(datDoct1.getTriage(), triaje_1);
        map_doctores.put(datDoct2.getTriage(), triaje_2);
        map_doctores.put(datDoct3.getTriage(), triaje_3);
        map_doctores.put(datDoct4.getTriage(), triaje_4);
        map_doctores.put(datDoct5.getTriage(), triaje_5);
    }
    
    public void crear_Habitaciones()
    {   
        for (int i = 1; i < 151; i++)
        {
            Habitacion aux = new Habitacion(i);
            habitaciones.add(aux);
            obtenerMostrarHabitacion(aux);
        } 
    }
    
        public void agregarPaciente(String rut, String nombre, String apellido, int edad, int sexo, int triaje)  {
        LocalDateTime tiempo_actual = LocalDateTime.now();
        DateTimeFormatter formatoTiempo = DateTimeFormatter.ofPattern("HH:mm:ss");
        String tiempoFormateado = tiempo_actual.format(formatoTiempo);

        Paciente pac = new Paciente(rut, nombre, apellido, edad, sexo, triaje, tiempo_actual);
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
        map_paciente.put(pac.getRut(), pac);
       
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
        map_paciente.put(pac.getRut(), pac);
       
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
        
        Paciente pac1 = new Paciente("21.275.186-3", "Ignacia", "Brahim", 21, 1, 3);
        Paciente pac2 = new Paciente("21.108.465-0", "Carlos", "Abarza", 21, 0, 5);
        Paciente pac3 = new Paciente("8.285.871-7", "Elizabeth", "Brahim", 52, 1, 1);
        Paciente pac4 = new Paciente("10.577.195-9", "Claudio", "Cubillos", 50, 0, 2);
        Paciente pac5 = new Paciente("10.273.813-6", "Laura", "Griffiths", 63, 1, 4);
        
        //Lista de prioridad
        agregarPaciente(pac1);
        agregarPaciente(pac2);
        agregarPaciente(pac3);
        agregarPaciente(pac4);
        agregarPaciente(pac5);
        
        map_paciente.put(pac1.getRut(), pac1);
        map_paciente.put(pac2.getRut(), pac2);
        map_paciente.put(pac3.getRut(), pac3);
        map_paciente.put(pac4.getRut(), pac4);
        map_paciente.put(pac5.getRut(), pac5);
    }
    
    //funciones que necesito para que funcione el main D:
    public int getListaDoctoresSize()
    {
        return lista_doctores.size();
    }
    
    public int getListaPacientePriorSize()
    {
        return lista_pacientes_prioridad.size();
    }
    
    public Paciente obtenerPacienteLista(int i)
    {
        return lista_pacientes_prioridad.get(i);
    }
}
  

