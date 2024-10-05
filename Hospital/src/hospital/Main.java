package hospital;
import java.io.*;
import java.util.*;

public class Main {
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
        System.out.println("| 9. Explicación de las opciones del menú:          |");
        System.out.println("|    Muestra esta pantalla de ayuda que explica     |");
        System.out.println("|    cada opción del menú.                          |");
        System.out.println("| 10. Salir del sistema:                            |");
        System.out.println("|    Cierra la aplicación.                          |");
        System.out.println("+ - - - - - - - - - - - - - - - - - - - - - - - - - +");
    }

    public static int esValido(BufferedReader reader, String mensaje) throws NumberFormatException, IOException {
        int numero = 0; 
        boolean valido = false; 
    
        while (!valido) {
            System.out.print(mensaje);
            String entrada = reader.readLine();
            
            if (entrada != null && entrada.matches("\\d+")) {
                numero = Integer.parseInt(entrada); 
                valido = true;
            } else {
                throw new NumberFormatException("Por favor, ingrese un número válido.");
            }
        }
        return numero; 
    }
    
    
    public static String validarRut(BufferedReader reader) throws InvalidRutException, IOException {
        String rut;
        while (true) {
            rut = reader.readLine();
            if (rut.matches("\\d{2}\\.\\d{3}\\.\\d{3}-[0-9Kk]")) {
                break; // RUT válido
            } else {
                // Aquí lanzamos la excepción
                throw new InvalidRutException("RUT inválido. Debe seguir el formato XX.XXX.XXX-X (ejemplo: 12.633.624-7).");
            }
        }
        return rut;
    }
    
    
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

    public static void main(String[] args) throws IOException
    {
        GestionTerminal terminal = new GestionTerminal();
        Hospital hospital_VIJ = new Hospital();
        hospital_VIJ.crearDoctores();
        hospital_VIJ.crear_Habitaciones();
        hospital_VIJ.crear_Pacientes();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String opcion = "";
        boolean continuar = true;
        
        while (continuar) 
        {
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
            System.out.println("| 6. Consultar pacientes por triage                 |");
            System.out.println("| 7. Ver estado de habitaciones y camas             |");
            System.out.println("| 8. Ver disponibilidad de doctores                 |");
            System.out.println("| 9. Explicación de las opciones del menú           |");
            System.out.println("| 10. Salir del sistema                             |");
            System.out.println("+ - - - - - - - - - - - - - - - - - - - - - - - - - +");
               
            System.out.print("\n\nIngrese su opción: \n");
            opcion = reader.readLine();
            String rut;
            Persona datos_paciente = null;
            switch (opcion) 
            {
                
                case "1":
                try {
                    System.out.println("Ingrese RUT del Paciente (ej. XX.XXX.XXX-X)");
                    rut = validarRut(reader); // Llamada que puede lanzar InvalidRutException
                
                    // Aquí continuaría el flujo normal de tu código si el RUT es válido:
                    System.out.println("\nIngrese Nombre del Paciente");
                    String nombre = validarNombreApellido(reader, "Nombre"); // Validar nombre
                
                    System.out.println("\nIngrese Apellido del Paciente");
                    String apellido = validarNombreApellido(reader, "Apellido"); // Validar apellido
                
                    datos_paciente = new Persona(rut, nombre, apellido);
                    
                } catch (InvalidRutException e) {
                    System.out.println("Error: " + e.getMessage()); // Mensaje en caso de RUT inválido
                } catch (IOException e) {
                    System.out.println("Error de entrada/salida: " + e.getMessage());
                }
                
                    int edad = esValido(reader, "Indique la edad: ");
                    
                    System.out.println("SEA:");
                    System.out.println("0) Hombre");
                    System.out.println("1) Mujer");
                    int sexo = esValido(reader, "Indique el Sexo del Paciente:");
                    
                    System.out.println("\nSeleccione la Condición del Paciente. Se le Asignará la Prioridad Según Corresponda:");
                    System.out.println("1) Riesgo Vital");
                    System.out.println("2) Alta Urgencia");
                    System.out.println("3) Mediana Urgencia");
                    System.out.println("4) Baja Urgencia");
                    System.out.println("5) No Urgente (Baja complejidad)");
                    int triage = esValido(reader, "\nIndique el Triaje: ");
                   
                    
                    if (triage == 5)
                    {
                        System.out.println("\nEl paciente se derivará al CESFAM correspondiente.");
                        System.out.println("\nGracias por venir hospital VIJ. Hasta Luego\n");  
                    }
                    else
                    {
                        System.out.println("\nGracias por venir hospital VIJ. Se agregará a el/la paciente " + datos_paciente.getNombre() + " \n");
                        hospital_VIJ.agregarPaciente(datos_paciente, edad, sexo, triage);
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
                            System.out.println("\nEl/La Paciente estaba hospitalizado/a. Se ha dado de alta a " + (aux_paciente.getDatos_paciente()).getNombre());
                        }
                        else
                        {
                            System.out.println("\nEl/La Paciente no estaba hospitalizado/a. Se ha dado de alta a " + (aux_paciente.getDatos_paciente()).getNombre());
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
                        if (pacienteCama.getDatos_paciente().getRut().equals(rutP)) {
                            pacienteEncontrado = pacienteCama;
                            break;
                        }
                    }
                
                    if (pacienteEncontrado != null) {
                        if (pacienteEncontrado.getNum_habitacion() != null) {
                            System.out.println("El paciente " + pacienteEncontrado.getDatos_paciente().getNombre() + " está asignado a la habitación " + pacienteEncontrado.getNum_habitacion());
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
                    // Consultar pacientes por triaje"
                    System.out.println("OPCION 6");
                    System.out.println("Ingrese una opción de triaje:");
                    System.out.println("1. Riesgo Vital");
                    System.out.println("2. Alta Urgencia");
                    System.out.println("3. Mediana Urgencia");
                    System.out.println("4. Baja Urgencia");
                    
                    int triajeBuscar = esValido(reader, "Ingrese triaje: ");
    
                    hospital_VIJ.mostrarPacientesPorTriaje(triajeBuscar);
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
                                    String mostrar_doc = hospital_VIJ.mostrarDoctor(doctor_aux);
                                    System.out.println(mostrar_doc);
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
                            
                    //La magia de la sobrecarga ojala funcione D:   
                    }
                    terminal.presioneTecla();
                    terminal.limpiarPantalla();
                    break;

                case "9":
                    // "Explicación de las opciones del menú"
                    System.out.println("OPCION 9");
                    explicarOpcionesMenu();
                    terminal.presioneTecla();
                    terminal.limpiarPantalla();
                    break;
                    
                case "10":
                    //  "Salir del sistema"
                     System.out.println("OPCION 1o");
                    continuar = false;  
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