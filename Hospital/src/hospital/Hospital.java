package hospital;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Clase que representa un hospital, gestionando doctores, pacientes y habitaciones.
 * Proporciona funcionalidades para asignar doctores a pacientes, habitaciones a pacientes,
 * eliminar pacientes y cargar datos de doctores y pacientes desde archivos CSV.
 */
public class Hospital {
    
    // Mapa de doctores, organizado por nivel de triage
    private final HashMap<Integer, LinkedList<Doctor>> map_doctores = new HashMap<>();
    // Lista de todos los doctores
    private final ArrayList<Doctor> lista_doctores = new ArrayList<>();
    
    // Mapa de pacientes, con el RUT como clave
    private final HashMap<String, Paciente> map_paciente = new HashMap<>();
    // Lista de pacientes priorizados por triage
    private final LinkedList<Paciente> lista_pacientes_prioridad = new LinkedList<>();
    
    // Lista de habitaciones en el hospital
    ArrayList<Habitacion> habitaciones = new ArrayList<>();
    
    /**
     * Busca un paciente por su RUT.
     * @param rut RUT del paciente.
     * @return Paciente si existe, null si no se encuentra.
     */
    public Paciente buscarPacienteRut(String rut)
    {
        Paciente aux_paciente = map_paciente.get(rut);
        return aux_paciente;
    }
    
     /**
     * Borra la referencia del paciente en la cama de la habitación que ocupaba.
     * @param aux_paciente El paciente a eliminar.
     */
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

     /**
     * Elimina un paciente del sistema y libera su habitación si corresponde.
     * @param rut RUT del paciente.
     * @param aux_paciente El paciente a eliminar.
     */
    public void borrarPaciente(String rut, Paciente aux_paciente)
    {
        map_paciente.remove(rut);
        lista_pacientes_prioridad.remove(aux_paciente);
        if (aux_paciente.getNum_habitacion() != null)
        {
            borrarPacienteCama(aux_paciente);
        }
    }

    /**
     * Asigna un doctor disponible a un paciente en función de su triage.
     * @param paciente Paciente al que se le va a asignar un doctor.
     */
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

    /**
     * Asigna una habitación disponible a un paciente.
     * @param paciente Paciente al que se le va a asignar una habitación.
     */
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

    /**
     * Muestra los pacientes por nivel de triage.
     * @param triaje El nivel de triage a filtrar.
     */
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
    
    /**
    * Devuelve una habitación específica basada en su número.
    * 
    * Este método busca una habitación en la lista de habitaciones según el número dado.
    * Si el número está fuera del rango válido, se muestra un mensaje de error y retorna `null`.
    * 
    * @param num El número de la habitación que se desea obtener.
    * @return La habitación correspondiente al número, o `null` si el número es inválido.
    */
    public Habitacion obtenerHabitacion(int num)
    {
        if (num >= 1 && num <= habitaciones.size()) {
            return habitaciones.get(num - 1);
        } else {
            System.out.println("Número de habitación no válido.");
            return null;
        }
    }
    
    /**
    * Devuelve una cadena que muestra el estado de ocupación de la habitación.
    * 
    * Este método retorna una descripción detallada del estado de la habitación, 
    * incluyendo el número de habitación, si está ocupada y los datos de los pacientes 
    * en las camas disponibles. Si alguna cama está vacía, indica "Nadie".
    * 
    * @param aux La habitación que se desea mostrar.
    * @return Una cadena con los detalles de la habitación y sus camas.
     */
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
    
    /**
    * Añade un doctor a la lista y al mapa de doctores por triage.
    * 
    * Este método agrega un doctor a la lista global de doctores y lo asigna 
    * al mapa de doctores basado en su triage. Si el triage del doctor no existe en el mapa, 
    * crea una nueva entrada.
    * 
    * @param doctor_aux El doctor que se desea añadir al sistema.
    */
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
    
    /**
    * Elimina un doctor de la lista y del mapa de doctores por triage.
    * 
    * Este método elimina al doctor de la lista global de doctores y del mapa de doctores 
    * basado en su triage.
    * 
    * @param doctor_aux El doctor que se desea eliminar del sistema.
    */
    public void eliminarDoctorMapaArray(Doctor doctor_aux) 
    {
        int triage = doctor_aux.getTriage();
        LinkedList<Doctor> doctores_triaje = map_doctores.get(triage);
        doctores_triaje.remove(doctor_aux);
        lista_doctores.remove(doctor_aux);
    }
    
    /**
    * Devuelve un doctor de la lista basado en su índice.
    * 
    * Este método obtiene un doctor de la lista global de doctores utilizando el índice proporcionado.
    * 
    * @param i El índice del doctor que se desea obtener.
    * @return El doctor correspondiente al índice.
    */
    public Doctor obtenerDoctor(int i)
    {
        Doctor aux_doctor = lista_doctores.get(i);
        return aux_doctor;
    }
    
    /**
    * Devuelve una lista de doctores disponibles para un triaje específico.
    * 
    * Este método busca en el mapa de doctores el triaje dado y retorna una lista 
    * de doctores disponibles para ese triaje.
    * 
    * @param triaje_aux El triaje en formato de cadena.
    * @return Una lista de doctores disponibles para el triaje especificado.
    */
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
    
    /**
    * Muestra los detalles de un doctor.
    * 
    * Este método devuelve una cadena que muestra los datos de un doctor 
    * junto con su disponibilidad.
    * 
    * @param aux_doctor El doctor cuyos datos se desean mostrar.
    * @return Una cadena con los datos del doctor y su disponibilidad.
    */
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


    /**
    * Sobrecarga del método mostrarDoctor.
    * 
    * Este método sobrecargado muestra los datos del doctor junto con su índice y su triaje.
    * 
    * @param aux_doctor El doctor cuyos datos se desean mostrar.
    * @param i El índice del doctor en la lista.
    * @return Una cadena con el índice, los datos del doctor y su triaje.
    */
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

    /**
    * Crea las habitaciones del hospital y las agrega a la lista de habitaciones.
    * 
    * Este método genera un conjunto de 150 habitaciones numeradas del 1 al 150. 
    * Por cada habitación creada, se añade a la lista de habitaciones del hospital 
    * y se muestra la información de la habitación en pantalla a través del método 
    * obtenerMostrarHabitacion().
    * 
    * No recibe parámetros.
    * 
    * No retorna valores.
    */
    public void crear_Habitaciones()
    {   
        for (int i = 1; i < 151; i++)
        {
            Habitacion aux = new Habitacion(i);
            habitaciones.add(aux);
            obtenerMostrarHabitacion(aux);
        } 
    }
    
    /**
     * Agrega un nuevo paciente a la lista de pacientes con prioridad, ordenándolos 
    * según su triaje y tiempo de ingreso.
    * 
    * Este método recibe los datos del paciente, crea una nueva instancia de la clase Paciente, 
    * y lo inserta en la lista de pacientes de acuerdo a su prioridad (triaje). 
    * Si dos pacientes tienen el mismo nivel de triaje, se ordenan según el tiempo de llegada. 
    * Además, el paciente se almacena en un mapa para acceso rápido por su RUT.
    * 
    * @param rut       RUT del paciente.
    * @param nombre    Nombre del paciente.
    * @param apellido  Apellido del paciente.
    * @param edad      Edad del paciente.
    * @param sexo      Sexo del paciente (0: masculino, 1: femenino).
    * @param triaje    Nivel de triaje del paciente (entre 1 y 5, siendo 1 la mayor prioridad).
    */
        public void agregarPaciente(String rut, String nombre, String apellido, int edad, int sexo, int triaje)  {
        LocalDateTime tiempo_actual = LocalDateTime.now();
        DateTimeFormatter formatoTiempo = DateTimeFormatter.ofPattern("HH:mm:ss");
        String tiempoFormateado = tiempo_actual.format(formatoTiempo);

        Paciente pac = new Paciente(rut, nombre, apellido, edad, sexo, triaje, tiempo_actual);
        //System.out.println("Paciente ingresado a las " + tiempoFormateado);

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

    /**
    * Busca un doctor en el mapa de doctores utilizando su nombre completo.
    * 
    * Este método recorre la lista de doctores almacenados en el mapa (map_doctores) 
    * y compara el nombre completo (nombre + apellido) del doctor con el nombre 
    * proporcionado. Si encuentra un doctor con el nombre completo exacto, 
    * lo retorna. Si no se encuentra o si el nombre ingresado es nulo o vacío, 
    * retorna null.
    * 
    * @param nombreCompleto El nombre completo del doctor a buscar.
    * @return El doctor encontrado, o null si no se encuentra ningún doctor con ese nombre.
    */
    public Doctor buscarDoctorPorNombre(String nombreCompleto) {
        // Verificar si el nombre completo es null o está vacío
        if (nombreCompleto == null || nombreCompleto.isEmpty()) {
            return null;  // No hay doctor asignado
        }
    
        // Recorrer todos los doctores en el mapa para buscar el nombre
        for (LinkedList<Doctor> listaDoctores : map_doctores.values()) {
            for (Doctor doctor : listaDoctores) {
                String nombreDoctor = doctor.getNombre() + " " + doctor.getApellido();
                if (nombreDoctor.equalsIgnoreCase(nombreCompleto)) {
                    return doctor;
                }
            }
        }
    
        return null; // Si no se en
    }    
 
    /**
    * Agrega un paciente ya creado a la lista de pacientes con prioridad, ordenándolos 
    * según su triaje y tiempo de ingreso.
    * 
     * Este método recibe una instancia de la clase Paciente, la cual ya ha sido creada, 
     * y lo inserta en la lista de pacientes de acuerdo a su prioridad (triaje). 
    * Si dos pacientes tienen el mismo nivel de triaje, se ordenan según el tiempo de llegada. 
    * Además, el paciente se almacena en un mapa para acceso rápido por su RUT.
    * 
    * @param pac El paciente a agregar a la lista de pacientes con prioridad.
    */
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
    /**
    * Busca un paciente asignado a una habitación específica.
    * 
    * Este método recorre la lista de pacientes con prioridad y verifica si alguno 
     * de ellos está asignado a la habitación con el número proporcionado. Si encuentra 
     * un paciente que esté en la habitación indicada, lo retorna. Si no se encuentra 
     * ningún paciente en esa habitación, retorna null.
     * 
     * @param numHabitacion El número de la habitación a buscar.
     * @return El paciente asignado a la habitación, o null si no se encuentra ninguno.
     */
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

    /**
     * Carga los datos de doctores desde un archivo CSV.
     * @param archivo Ruta del archivo CSV.
     */
    public void cargarDoctoresCSV(String archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
    
            // Saltar la primera línea si es el encabezado
            linea = br.readLine(); // Lee la primera línea (encabezado) y no la procesa
    
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String rut = datos[0];
                String nombre = datos[1];
                String apellido = datos[2];
                int triage = Integer.parseInt(datos[3]);  // Aquí ya no habrá problema
                int pacientesMax = Integer.parseInt(datos[4]);
                int pacientesActual = Integer.parseInt(datos[5]);
                boolean disponible = Boolean.parseBoolean(datos[6]);
    
                Doctor doctor = new Doctor(rut, nombre, apellido, triage);
                doctor.setPacientes_max(pacientesMax);
                doctor.setPacientes_actual(pacientesActual);
                doctor.setDisponible(disponible);
                lista_doctores.add(doctor);
    
                // Agregar al mapa por nivel de Triage
                map_doctores.computeIfAbsent(triage, k -> new LinkedList<>()).add(doctor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

      /**
     * Carga los datos de pacientes desde un archivo CSV.
     * @param archivo Ruta del archivo CSV.
     */
     public void cargarPacientesCSV(String archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

            // Saltar la primera línea si es el encabezado
            linea = br.readLine(); // Lee la primera línea (encabezado) y no la procesa
            
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");

                 // Verificar que la línea tenga al menos 10 campos antes de procesarla
                if (datos.length < 10) {
                    System.out.println("Línea incompleta encontrada: " + linea);
                    continue;  // Saltar la línea incompleta
                }
                
                String rut = datos[0];
                String nombre = datos[1];
                String apellido = datos[2];
                int edad = Integer.parseInt(datos[3]);
                int sexo = Integer.parseInt(datos[4]);
                int triage = Integer.parseInt(datos[5]);
                String medicoAsignado = datos[6].isEmpty() ? null : datos[6];
                String estadoAtencion = datos[7];
                String numHabitacion = datos[8].isEmpty() ? null : datos[8]; 
                String horaAtencionStr = datos[9];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm:ss");
                LocalTime horaAtencion = LocalTime.parse(horaAtencionStr, formatter);

                // Combinar con una fecha predeterminada, por ejemplo, el día actual
                LocalDateTime fechaHoraAtencion = LocalDateTime.of(LocalDate.now(), horaAtencion);


                Paciente paciente = new Paciente(rut, nombre, apellido, edad, sexo, triage, fechaHoraAtencion);
                
                paciente.setEstado_atencion(estadoAtencion);
                paciente.setNum_habitacion(numHabitacion);

                // Buscar el doctor por su nombre
                Doctor doctorAsignado = buscarDoctorPorNombre(medicoAsignado);

                // Si se encuentra el doctor, asignarlo al paciente
                if (doctorAsignado != null) {
                    paciente.setMedico_asignado(doctorAsignado);
                } else {
                    System.out.println("Doctor no encontrado: " + medicoAsignado);
                }

                paciente.setMedico_asignado(doctorAsignado);

                map_paciente.put(rut, paciente);
                lista_pacientes_prioridad.add(paciente);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
    * Guarda los datos de los doctores en un archivo CSV.
    * 
    * Este método recorre la lista de doctores y escribe sus datos en un archivo CSV. 
    * Si la lista de doctores está vacía, no se realiza ninguna acción. 
    * El archivo se abre en modo "append" para agregar los datos al final del archivo 
     * sin sobrescribir su contenido.
     * 
     * El formato de cada línea en el archivo es: 
     * "RUT, Nombre, Apellido, Triage, Pacientes Max, Pacientes Actual, Disponible".
     * 
     * @param archivo El nombre del archivo CSV donde se guardarán los datos de los doctores.
    */
    public void guardarDoctoresCSV(String archivo) {
        if (lista_doctores.isEmpty()) {
            System.out.println("No hay doctores para guardar.");
            return;  // No guardar si la lista está vacía
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {  // true para modo append
            for (Doctor doctor : lista_doctores) {
                String linea = String.format("%s,%s,%s,%d,%d,%d,%b\n",
                        doctor.getRut(),
                        doctor.getNombre(),
                        doctor.getApellido(),
                        doctor.getTriage(),
                        doctor.getPacientes_max(),
                        doctor.getPacientes_actual(),
                        doctor.isDisponible());
                bw.write(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   


    /**
    * Guarda los datos de los pacientes en un archivo CSV.
    * 
    * Este método recorre la lista de pacientes priorizados y escribe sus datos en un archivo CSV. 
    * Si la lista de pacientes está vacía, no se realiza ninguna acción. 
    * El archivo se abre en modo "append" para agregar los datos al final del archivo 
    * sin sobrescribir su contenido.
     * 
    * El formato de cada línea en el archivo es: 
    * "RUT, Nombre, Apellido, Edad, Sexo, Triage, Doctor Asignado, Estado de Atención, 
    * Número de Habitación, Tiempo de Ingreso".
    * 
    * @param archivo El nombre del archivo CSV donde se guardarán los datos de los pacientes.
    */
    public void guardarPacientesCSV(String archivo) {
        if (lista_pacientes_prioridad.isEmpty()) {
            System.out.println("No hay pacientes para guardar.");
            return;  // No guardar si la lista está vacía
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {  // true para modo append
            for (Paciente paciente : lista_pacientes_prioridad) {
                String linea = String.format("%s,%s,%s,%d,%s,%d,%s,%s,%s,%s\n",
                        paciente.getRut(),
                        paciente.getNombre(),
                        paciente.getApellido(),
                        paciente.getEdad(),
                        paciente.getSexo(),
                        paciente.getTriage(),
                        (paciente.getMedico_asignado() != null ? paciente.getMedico_asignado().getNombre() : "Sin Doctor"),
                        paciente.getEstado_atencion(),
                        (paciente.getNum_habitacion() != null ? paciente.getNum_habitacion() : "Sin Habitación"),
                        paciente.getTiempoActual().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                bw.write(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


      /**
    * Genera un reporte en formato TXT con los datos de los doctores y pacientes.
    * 
    * Este método crea un archivo de texto que contiene un reporte con la información 
    * de todos los doctores y pacientes registrados. El reporte incluye un bloque para 
    * los doctores y otro para los pacientes, con cada uno de ellos representado en una línea 
    * utilizando su método toString().
    * 
    * @param archivo El nombre del archivo TXT donde se guardará el reporte.
    */
      public void generarReporteTXT(String archivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            bw.write("Reporte de Doctores:\n");
            for (Doctor doctor : lista_doctores) {
                bw.write(doctor.toString() + "\n");
            }

            bw.write("\nReporte de Pacientes:\n");
            for (Paciente paciente : lista_pacientes_prioridad) {
                bw.write(paciente.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

  

