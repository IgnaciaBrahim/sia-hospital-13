package hospital;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import Ventanas.AñadirMedicoOpcion;
import Ventanas.AsignarDoctor;
import Ventanas.AsignarHabitacion;
import Ventanas.ConsultarPorTriaje;
import Ventanas.DisponibilidadDoctores;
import Ventanas.ElimAgregMedicos;
import Ventanas.EliminarDoc;
import Ventanas.EstdaoHabitacionCama;
import Ventanas.HabitacionParticular;
import Ventanas.NewJFrame;
import Ventanas.NuevoPaciente;
import Ventanas.OpcionesMenu;
import Ventanas.RegistrarAlta;
import Ventanas.RegistrarVisita;
import Ventanas.SalirSistema;
import Ventanas.TodosDoctores;
import Ventanas.doctorParticular;
import Ventanas.mostrarTodasHabitaciones;
import Ventanas.Prueba;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase principal que maneja la interacción del usuario con el sistema
 * hospitalario. Proporciona métodos para mostrar opciones del menú,
 * validar entradas y gestionar pacientes y doctores.
 */
public class Main {
    /**
     * Muestra las opciones disponibles en el menú del sistema hospitalario,
     * proporcionando una breve descripción de cada opción.
     */
    public static void explicarOpcionesMenu() {
        System.out.println("+ - - - - - - - - - - - - - - - - - - - - - - - - - +");
        System.out.println("| Explicación de las Opciones del Menú              |");
        System.out.println("+ - - - - - - - - - - - - - - - - - - - - - - - - - +");
        System.out.println("| 1. Añadir paciente:                               |");
        System.out.println("|    Permite registrar un nuevo paciente en el      |");
        System.out.println("|    sistema con su información personal y triage.  |");
        System.out.println("| 2. Registrar alta voluntaria:                     |");
        System.out.println("|    Marca a un paciente como dado de alta y libera |");
        System.out.println("|    su cama, si corresponde.                       |");
        System.out.println("| 3. Asignar doctor a paciente:                     |");
        System.out.println("|    Asigna un doctor disponible al paciente según  |");
        System.out.println("|    su nivel de triage.                            |");
        System.out.println("| 4. Asignar habitación a paciente:                 |");
        System.out.println("|    Asigna una habitación disponible al paciente.  |");
        System.out.println("| 5. Registrar visita a paciente:                   |");
        System.out.println("|    Registra la visita de un médico al paciente    |");
        System.out.println("|    para actualizar su estado.                     |");
        System.out.println("| 6. Consultar pacientes por triage:                |");
        System.out.println("|    Muestra una lista de pacientes según su nivel  |");
        System.out.println("|    de triage.                                     |");
        System.out.println("| 7. Ver estado de habitaciones y camas:            |");
        System.out.println("|    Muestra el estado de todas las habitaciones,   |");
        System.out.println("|    indicando qué camas están ocupadas.            |");
        System.out.println("| 8. Ver disponibilidad de doctores:                |");
        System.out.println("|    Muestra la lista de doctores disponibles según |");
        System.out.println("|    su triage asignado.                            |");
        System.out.println("| 9. Gestionar Medicos por Triaje                   |");
        System.out.println("|    Se podrá eliminar o agregar un médico a un     |");
        System.out.println("|    triaje específico.                             |");
        System.out.println("| 10. Explicación de las opciones del menú:         |");
        System.out.println("|    Muestra esta pantalla de ayuda que explica     |");
        System.out.println("|    cada opción del menú.                          |");
        System.out.println("| 11. Salir del sistema:                            |");
        System.out.println("|    Cierra la aplicación.                          |");
        System.out.println("+ - - - - - - - - - - - - - - - - - - - - - - - - - +");
    }

    /**
    * Valida la entrada del usuario para diferentes tipos de datos: edad, sexo y triage.
    *
    * @param reader BufferedReader utilizado para leer la entrada del usuario.
    * @param mensaje Mensaje que se muestra al usuario al solicitar la entrada.
    * @param tipo Tipo de dato a validar (edad, sexo, triage).
     * @return Un número entero válido según el tipo especificado.
     * @throws IOException Si ocurre un error al leer la entrada.
    */
    public static int esValido(BufferedReader reader, String mensaje, String tipo) throws IOException {
        int numero = 0;
        boolean valido = false;
    
        while (!valido) {
            System.out.print(mensaje);
            String entrada = reader.readLine();
    
            try {
                numero = Integer.parseInt(entrada);
                switch (tipo) {
                    case "edad":
                        if (numero >= 0 && numero <= 150) {
                            valido = true; 
                        } else {
                            throw new EdadInvalidaException("Edad fuera del rango permitido (0-150).");
                        }
                        break;
                    case "sexo":
                        if (numero == 0 || numero == 1) {
                            valido = true; 
                        } else {
                            throw new SexoInvalidoException("Sexo no válido. Ingrese 0 para Hombre o 1 para Mujer.");
                        }
                        break;
                    case "triaje":
                        if (numero >= 1 && numero <= 5) {
                            valido = true;  
                        } else {
                            throw new TriageInvalidoException("Triage no válido. Ingrese un valor entre 1 y 5.");
                        }
                        break;
                    default:
                        System.out.println("Tipo de dato no reconocido.");
                        valido = true; 
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: no se pudo convertir a número. Inténtelo nuevamente.");
            } catch (EdadInvalidaException | SexoInvalidoException | TriageInvalidoException e) {
                System.out.println("Error: " + e.getMessage());  // mje excepción
            }
        }
        return numero;
    }   

    /**
    * Valida el formato del RUT ingresado por el usuario.
    *
    * @param reader BufferedReader utilizado para leer la entrada del usuario.
    * @return El RUT válido ingresado por el usuario.
    * @throws IOException Si ocurre un error al leer la entrada.
    */
    public static String validarRut(BufferedReader reader) throws IOException {
        String rut = "";
        boolean valido = false;
        
        while (!valido) {
            System.out.println("Ingrese RUT (Formato XX.XXX.XXX-X): ");
            rut = reader.readLine();
            if (rut.matches("\\d{2}\\.\\d{3}\\.\\d{3}-[0-9Kk]")) {
                valido = true; // Si el RUT es válido, salimos del ciclo
            } else {
                System.out.println("RUT inválido. Debe seguir el formato XX.XXX.XXX-X (ejemplo: 12.633.624-7). Intente nuevamente.");
            }
        }
        return rut;
    }
    
    /**
    * Valida que la entrada del usuario para nombre o apellido contenga solo letras.
     *
     * @param reader BufferedReader utilizado para leer la entrada del usuario.
     * @param tipo Tipo de entrada a validar (nombre o apellido).
    * @return La entrada válida ingresada por el usuario.
    * @throws IOException Si ocurre un error al leer la entrada.
    */
    public static String validarNombreApellido(BufferedReader reader, String tipo) throws IOException {
        String entrada;
        while (true) {
            entrada = reader.readLine();
            if (entrada.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                break; // Nombre o apellido válido
            } else {
                System.out.println(tipo + " inválido. Debe contener solo letras. Ingrese nuevamente: ");
            }
        }
        return entrada;
    }

    /**
     * Método principal que inicia la ejecución de la aplicación.
     * 
     * @param args Argumentos de línea de comandos (no utilizados).
     * @throws IOException Si ocurre un error al leer o escribir archivos.
     */
    public static void main(String[] args) throws IOException
    {

        GestionTerminal terminal = new GestionTerminal();
        Hospital hospital_VIJ = new Hospital();
        String baseDir = System.getProperty("user.dir");  // Obtiene el directorio actual
        String doctoresPath = baseDir + "/doctores.csv";  // Construye la ruta completa
        String pacientesPath = baseDir + "/pacientes.csv";  // Construye la ruta completa
        hospital_VIJ.cargarDoctoresCSV("doctores.csv");
        hospital_VIJ.cargarPacientesCSV("pacientes.csv");
        hospital_VIJ.crear_Habitaciones(); // Crea habitaciones para el hospital.
        
<<<<<<< HEAD
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Create and open the Prueba window (Main window)
                Prueba ventana = new Prueba(hospital_VIJ);
                
                // Center the window
                ventana.setLocationRelativeTo(null);
                
                // Make it visible
                ventana.setVisible(true);
            }
        });
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String opcion = "";
        boolean continuar = true;
=======

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); // Para leer entradas del usuario.
        String opcion = ""; 
        boolean continuar = true; // Bandera para controlar el bucle del menú.
>>>>>>> ce21c379b9822cd2ae0477e9fdbd77b73a413481
    
        while (continuar) 
        {
            // Menú principal

            // Se muestra las opciones del menú al usuario.
            System.out.println("+ - - - - - - - - - - - - - - - - - - - - - - - - - +");
            System.out.println("| Bienvenido/a al sistema de Atención Hospitalaria. |");
            System.out.println("|                                                   |");
            System.out.println("| Por  favor,  seleccione  una  de  las  siguientes |");
            System.out.println("| opciones  ingresando  el  numero  correspondiente:|");
            System.out.println("+ - - - - - - - - - - - - - - - - - - - - - - - - - +");
            System.out.println("| 1. Añadir paciente                                |");
            System.out.println("| 2. Registrar alta voluntaria                      |");
            System.out.println("| 3. Asignar doctor a paciente                      |");
            System.out.println("| 4. Asignar habitación a paciente                  |");
            System.out.println("| 5. Registrar visita a paciente                    |");
            System.out.println("| 6. Consultar pacientes por triaje                 |");
            System.out.println("| 7. Ver estado de habitaciones y camas             |");
            System.out.println("| 8. Ver disponibilidad de doctores                 |");
            System.out.println("| 9. Gestionar Medicos por Triaje                   |");
            System.out.println("| 10. Explicación de las opciones del menú          |");
            System.out.println("| 11. Salir del sistema                             |");
            System.out.println("+ - - - - - - - - - - - - - - - - - - - - - - - - - +");
               
            System.out.print("\n\nIngrese su opción: \n");
            opcion = reader.readLine();// Lee la opción seleccionada por el usuario.
            String rut;
            Persona datos_paciente = null;

            switch (opcion) 
            {
                
                case "1":
                // Opción para añadir un paciente.
                    try {
                        // Validar el RUT hasta que sea válido
                        System.out.println("Ingrese RUT del Paciente (ej. XX.XXX.XXX-X)");
                        rut = validarRut(reader);  // pedir el RUT hasta que sea válido

                        // nombre y apellido
                        System.out.println("\nIngrese Nombre del Paciente");
                        String nombre = validarNombreApellido(reader, "Nombre");

                        System.out.println("\nIngrese Apellido del Paciente");
                        String apellido = validarNombreApellido(reader, "Apellido");

                        // edad hasta que se ingrese una válida
                        int edad = esValido(reader, "Indique la edad: ", "edad");

                        System.out.println("SEA:");
                        System.out.println("0) Hombre");
                        System.out.println("1) Mujer");
                        int sexo = esValido(reader, "Indique el Sexo del Paciente: ", "sexo");

                        System.out.println("\nSeleccione la Condición del Paciente. Se le Asignará la Prioridad Según Corresponda:");
                        System.out.println("1) Riesgo Vital");
                        System.out.println("2) Alta Urgencia");
                        System.out.println("3) Mediana Urgencia");
                        System.out.println("4) Baja Urgencia");
                        System.out.println("5) No Urgente (Baja complejidad)");
                        int triaje = esValido(reader, "\nIndique el Triaje: ", "triaje");

                        if (triaje == 5) {
                            System.out.println("\nEl paciente se derivará al CESFAM correspondiente.");
                            System.out.println("\nGracias por venir hospital VIJ. Hasta Luego\n");
                        } else {
                            Paciente pac = new Paciente(rut, nombre, apellido, edad, sexo, triaje);
                            System.out.println(pac.registrarse());
                            hospital_VIJ.agregarPaciente(pac);
                        }

                    } catch (IOException e) {
                        System.out.println("Error de entrada/salida: " + e.getMessage());
                    }
                    terminal.presioneTecla();
                    terminal.limpiarPantalla(); 
                    break;

                case "2":
                    // "Registrar alta voluntaria"
                    System.out.println("OPCION 2\n\n");
                    System.out.println("Ingrese el RUT del paciente");
                    rut =  reader.readLine();
                    Paciente aux_paciente = hospital_VIJ.buscarPacienteRut(rut);
                    if (aux_paciente == null)
                    {
                        System.out.println("\nNo existe tal paciente. Saliendo al menu principal.\n");
                    }
                    else if (aux_paciente.getEdad() < 18)
                    {
                        System.out.println("\nEl/La Paciente es menor de edad. No se pudo registrar el alta.\n");
                    }
                    else if (aux_paciente.getTriage() < 3)
                    {
                        System.out.println("\nEl/La Paciente está en estado grave. Por ley, no se registra el alta.\n\n");
                    }
                    else
                    {
                        System.out.println("\nBuscando si el Paciente está Hospitalizado...");
                        hospital_VIJ.borrarPaciente(rut, aux_paciente);
                        if (aux_paciente.getNum_habitacion() != null)
                        {
                            System.out.println("\nEl/La Paciente estaba hospitalizado/a. Se ha dado de alta a " + aux_paciente.obtenerDatos());
                        }
                        else
                        {
                            System.out.println("\nEl/La Paciente no estaba hospitalizado/a. Se ha dado de alta a " + aux_paciente.obtenerDatos());
                        }
                  
                        
                    }
                    terminal.presioneTecla();
                    terminal.limpiarPantalla(); 
                    break;

                case "3":
                    // "Asignar doctor a paciente"
                    System.out.println("OPCION 3");
                    System.out.println("Ingrese RUT del Paciente (ej. XX.XXX.XXX-X): ");
                    rut = reader.readLine();
                    Paciente paciente = hospital_VIJ.buscarPacienteRut(rut);
                    if (paciente != null) {
                        hospital_VIJ.asignarDoctorAPaciente(paciente);
                    } else {
                        System.out.println("Paciente no encontrado.");
                    }
                    terminal.presioneTecla();
                    terminal.limpiarPantalla();
                    break;

                case "4":
                    // "Asignar habitación a paciente"
                    System.out.println("OPCION 4");
                    System.out.println("Ingrese RUT del Paciente (ej. XX.XXX.XXX-X): ");
                    rut = reader.readLine();
                    paciente = hospital_VIJ.buscarPacienteRut(rut);
                    if (paciente != null) {
                        hospital_VIJ.asignarHabitacionAPaciente(paciente);
                    } else {
                        System.out.println("Paciente no encontrado.");
                    }
                    terminal.presioneTecla();
                    terminal.limpiarPantalla();
                    break;

                case "5":
                    // "Registrar visita a paciente"
                    System.out.print("Ingrese el RUT del paciente a buscar: ");
                    String rutP = reader.readLine();
                    Paciente pacienteEncontrado = null;
                    // Busca el paciente por RUT
                    for (int i = 0; i < hospital_VIJ.getListaPacientePriorSize(); i++) {
                        Paciente pacienteCama = hospital_VIJ.obtenerPacienteLista(i);
                        if (pacienteCama.getRut().equals(rutP)) {
                            pacienteEncontrado = pacienteCama;
                            break;
                        }
                    }
                
                    if (pacienteEncontrado != null) {
                        if (pacienteEncontrado.getNum_habitacion() != null) {
                            System.out.println("El paciente " + pacienteEncontrado.obtenerDatos() + " está asignado a la habitación " + pacienteEncontrado.getNum_habitacion());
                        } else {
                            System.out.println("El paciente con RUT " + rutP + " no tiene una cama asignada.");
                        }
                    } else {
                        System.out.println("No se encontró ningún paciente con el RUT ingresado.");
                    }

                    terminal.presioneTecla();
                    terminal.limpiarPantalla();
                    break;

                case "6":
                    System.out.println("OPCION 6");
                    System.out.println("Ingrese una opción de triaje:");
                    System.out.println("1. Riesgo Vital");
                    System.out.println("2. Alta Urgencia");
                    System.out.println("3. Mediana Urgencia");
                    System.out.println("4. Baja Urgencia");
                    try {
                        int triajeBuscar = esValido(reader, "Ingrese triaje: ", "triaje");
                        hospital_VIJ.mostrarPacientesPorTriaje(triajeBuscar);
                    } catch (IOException e) {
                        System.out.println("Error de entrada/salida: " + e.getMessage());
                    }

                    terminal.presioneTecla();
                    terminal.limpiarPantalla();
                    break;

                case "7":
                    // "Ver estado de habitaciones y camas"
                    System.out.println("OPCION 7");
                    System.out.println("Desea ver el estado de: \n");
                    System.out.println("1) Todas las habitaciones.");
                    System.out.println("2) Habitación en particular.\n");
                    String menu_7_opcion = reader.readLine();
                    
                    switch (menu_7_opcion)
                    {
                        case "1":
                            System.out.println("MOSTRANDO ESTADO DEL HOSPITAL...\n");
                            for (int i = 1; i < 151 ; i++)
                            {
                                System.out.println("==================================================================================================");
                                Habitacion aux = hospital_VIJ.obtenerHabitacion(i);
                                String cadena_mostrar = hospital_VIJ.obtenerMostrarHabitacion(aux);
                                System.out.println(cadena_mostrar);
                            }
                            break;
                            
                        case "2":
                            System.out.println("Ingrese el n° de habitación.\n");
                            int num_habi = Integer.parseInt(reader.readLine());
                            Habitacion aux = hospital_VIJ.obtenerHabitacion(num_habi);
                            String cadena_mostrar = hospital_VIJ.obtenerMostrarHabitacion(aux);
                            System.out.println("==================================================================================================");
                            System.out.println(cadena_mostrar);
                            System.out.println("==================================================================================================");
                            
                            break;
                        default:
                            System.out.println("\nOpción inválida. Volviendo al menú principal...");
                            break;
                    }
                    
                    terminal.presioneTecla();
                    terminal.limpiarPantalla(); 
                    break;
                    //end caso 7
                    
                case "8":
                    // "Ver disponibilidad de doctores"
                    System.out.println("OPCION 8");
                    System.out.println("Desea ver disponibilidad de: \n");
                    System.out.println("1) Todos los doctores.");
                    System.out.println("2) Triaje en específico .\n");
                    String menu_8_opcion = reader.readLine();
                    
                    switch(menu_8_opcion)
                    {
                        case "1":
                            System.out.println("\nMOSTRANDO DOCTORES DISPONIBLES...\n");
                            int cont = 0;
                            for (int i = 0 ; i < hospital_VIJ.getListaDoctoresSize(); i++)
                            {
                                Doctor doctor_aux = hospital_VIJ.obtenerDoctor(i);
                                if (doctor_aux != null && (doctor_aux.isDisponible() == true))
                                {
                                    cont++;
                                    System.out.println(doctor_aux.obtenerDatos());
                                }
                            }
                            if (cont == 0)
                            {
                                System.out.println("No hay doctores disponibles...\n");
                            }
                            break;
                        
                        case "2":
                            System.out.println("\nQue triaje deseas mirar?\n");
                            System.out.println("1) Riesgo Vital");
                            System.out.println("2) Alta Urgencia");
                            System.out.println("3) Mediana Urgencia");
                            System.out.println("4) Baja Urgencia");
                            System.out.println("5) No Urgente (Baja complejidad)");
                            String triaje_aux = reader.readLine();
                            
                            if (!triaje_aux.matches("[1-5]"))
                            {
                                System.out.println("\nEl triaje que usted ingresó no es válido.");
                                System.out.println("Volviendo al menú principal...\n");
                            }
                            else
                            {
                                System.out.println("\nMOSTRANDO LOS DOCTORES DEL TRIAJE " + triaje_aux + " ...\n");
                                //Solo hay 1 doctor por triaje actualmente pero esta maniobra soporta cambios.
                                ArrayList<Doctor> doctor_disponible_triaje = hospital_VIJ.obtenerDoctor(triaje_aux);
                                if (doctor_disponible_triaje == null || doctor_disponible_triaje.isEmpty())
                                {
                                    System.out.println("No hay doctores disponibles en el triaje señalado.\n");
                                    
                                }
                                else
                                {
                                    for (int i = 0; i < doctor_disponible_triaje.size(); i++)
                                    {
                                        String mostrar_doc = hospital_VIJ.mostrarDoctor(doctor_disponible_triaje.get(i));
                                        System.out.println(mostrar_doc);
                                        //reutilizan2
                                    } 
                                }  
                            }  
                            break;
                        default:
                            System.out.println("\nOpción inválida. Volviendo al menú principal...");
                            break;
                            
                    }
                    terminal.presioneTecla();
                    terminal.limpiarPantalla();
                    break;
                    
                case "9":
                    // "Gestion Triaje"
                    System.out.println("OPCION 9");
                    System.out.println("¿Desea añadir o Eliminar un Médico del Hospital?\n");
                    System.out.println("1. Añadir");
                    System.out.println("2. Eliminar\n");
                    
                    String op9 = reader.readLine();
                    switch (op9)
                    {
                        case "1":
                            System.out.println("Ingrese los datos del Médico que desea añadir:\n");
                            System.out.println("1. RUT \n");
                            String medRut = validarRut(reader);
                            System.out.println("2. Nombre \n");
                            String medNombre = validarNombreApellido(reader, "nombre");
                            System.out.println("3. Apellido \n");
                            String medApellido = validarNombreApellido(reader, "apellido");
                            System.out.println("Ingrese el triaje (1 - 5) al que desea añadir a " + medNombre + ": \n");
                            String medTriaje = reader.readLine();
                            
                            if (!medTriaje.matches("[1-5]"))
                            {
                                System.out.println("\nEl triaje que usted ingresó no es válido.");
                                System.out.println("Volviendo al menú principal...\n");
                            }
                            else
                            {
                                Doctor house = new Doctor(medRut, medNombre, medApellido, Integer.parseInt(medTriaje));
                                System.out.println(house.registrarse());
                                hospital_VIJ.añadirDoctorMapaArray(house);
                                System.out.println("Se añadió a " + house.obtenerDatos() + "al Triaje " + medTriaje + ".");
                            }   
                            break;
   
                        case "2":
                            System.out.println("Ingrese el Nombre y Apellido del Médico que desea eliminar:\n");
                            
                            for (int i = 0 ; i < hospital_VIJ.getListaDoctoresSize(); i++)
                            {
                                Doctor aux_doctor = hospital_VIJ.obtenerDoctor(i);
                                String printDoc = hospital_VIJ.mostrarDoctor(aux_doctor, i + 1);
                                System.out.println(printDoc);
                            }
                            
                            System.out.println("1. Nombre \n");
                            medNombre = validarNombreApellido(reader, "nombre");
                            System.out.println("2. Apellido \n");
                            medApellido = validarNombreApellido(reader, "apellido");

                            int encontrado = 0;
                            for (int i = 0 ; i < hospital_VIJ.getListaDoctoresSize(); i++)
                            {
                                Doctor aux_doctor = hospital_VIJ.obtenerDoctor(i);
                                if (aux_doctor.getNombre().equals(medNombre) && aux_doctor.getApellido().equals(medApellido))
                                {
                                    encontrado = 1;
                                    System.out.println("Se eliminará al doctor " + aux_doctor.obtenerDatos() + "del Triaje " + aux_doctor.getTriage() + ".");
                                    hospital_VIJ.eliminarDoctorMapaArray(aux_doctor);
                                }
                            }
                            if (encontrado == 0)
                            {
                                System.out.println("No se encontró tal doctor. Volviendo al menú principal...");
                            }
                            break;
                    }
                    terminal.presioneTecla();
                    terminal.limpiarPantalla();
                    break;

                case "10":
                    // "Explicación de las opciones del menú"
                    System.out.println("OPCION 10");
                    explicarOpcionesMenu();
                    terminal.presioneTecla();
                    terminal.limpiarPantalla();
                    break;
                    
                case "11":
                    //  "Salir del sistema"
                     System.out.println("OPCION 11");
                    continuar = false; // Sale del sistema
                    hospital_VIJ.generarReporteTXT("reporte.txt"); // Genera un reporte en TXT.
                    hospital_VIJ.guardarDoctoresCSV("doctores.csv");// Guarda doctores en un archivo CSV.   
                    hospital_VIJ.guardarPacientesCSV("pacientes.csv"); // Guarda pacientes en un archivo CSV.
                    //terminal.presioneTecla();
                    terminal.limpiarPantalla();
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese un número del 1 al 10.");

                    break;
            }
    }
        System.out.println("Gracias por usar el sistema de Atención Hospitalaria. ¡Hasta luego!");
    }
}

