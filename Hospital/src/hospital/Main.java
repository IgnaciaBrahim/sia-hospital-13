package hospital;
import java.io.*;
import java.util.*;
//import java.time.*;
//import hospital.GestionTerminal;
//import hospital.Hospital;
//import hospital.Persona;

//Instancia el hospital pero la clase que realmente
//gestiona el hospital es gestor hospital.
public class Main {
    public static void main(String[] args) throws IOException
    {
        GestionTerminal terminal = new GestionTerminal();
        Hospital hospital_VIJ = new Hospital();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String opcion = "";
        boolean continuar = true;
        LinkedList<Paciente> pacientes = hospital_VIJ.getLista_pacientes_prioridad();

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
            
            switch (opcion) 
            {
                case "1":
                    System.out.println("OPCION 1\n\n");
                    System.out.println("Ingrese RUT del Paciente (ej. XX.XXX.XXX-X)");
                    String rut = reader.readLine();
                    
                    System.out.println("\nIngrese Nombre del Paciente");
                    String nombre = reader.readLine();
                    
                    System.out.println("\nIngrese Apellido del Paciente");
                    String apellido = reader.readLine();
                    
                    Persona datos_paciente = new Persona(rut, nombre, apellido);
                    
                    System.out.println("\nIndique la edad:");
                    int edad = Integer.parseInt(reader.readLine());
                    
                    System.out.println("\nIndique el Sexo del Paciente:");
                    System.out.println("0) Hombre");
                    System.out.println("1) Mujer");
                    int sexo = Integer.parseInt(reader.readLine());
                    
                    System.out.println("\nSeleccione la Condición del Paciente. Se le Asignará la Prioridad Según Corresponda:");
                    System.out.println("1) Riesgo Vital");
                    System.out.println("2) Alta Urgencia");
                    System.out.println("3) Mediana Urgencia");
                    System.out.println("4) Baja Urgencia");
                    System.out.println("5) No Urgente (Baja complejidad)");
                    
                    int triage = Integer.parseInt(reader.readLine());
                    
                    if (triage == 5)
                    {
                        System.out.println("\nEl paciente se derivará al CESFAM correspondiente.");
                        System.out.println("\nGracias por venir hospital VIJ. Hasta Luego\n");  
                    }
                    else
                    {
                        System.out.println("\nGracias por venir hospital VIJ. Se agregará a el/la paciente " + datos_paciente.getNombre() + " \n");  
                        Paciente paciente = new Paciente();
                        paciente.agregarPaciente(datos_paciente, edad, sexo, triage, pacientes);

                    }
                    
                    terminal.presioneTecla();
                    terminal.limpiarPantalla();
                    break;
                case "2":
                    // "Registrar alta voluntaria"
                     System.out.println("OPCION 2");
                    break;
                case "3":
                    // "Asignar doctor a paciente"
                     System.out.println("OPCION 3");
                    break;
                case "4":
                    // "Asignar habitación a paciente"
                     System.out.println("OPCION 4");
                    break;
                case "5":
                    // "Registrar visita a paciente"
                     System.out.println("OPCION 5");
                    break;
                case "6":
                    // Consultar pacientes por triaje"
                     System.out.println("OPCION 6");
                    break;
                case "7":
                    // "Ver estado de habitaciones y camas"
                     System.out.println("OPCION 7");
                    break;
                case "8":
                    // "Ver disponibilidad de doctores"
                     System.out.println("OPCION 8");
                    break;
                case "9":
                    // "Explicación de las opciones del menú"
                     System.out.println("OPCION 9");
                    break;
                case "10":
                    //  "Salir del sistema"
                     System.out.println("OPCION 1o");
                    continuar = false;  
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese un número del 1 al 10.");
                    break;
            }
    }
        System.out.println("Gracias por usar el sistema de Atención Hospitalaria. ¡Hasta luego!");
    }
}