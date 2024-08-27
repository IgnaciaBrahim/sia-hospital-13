package hospital;
import hospital.Paciente;
import java.io.*;
import java.util.*;

public class Hospital 
{
    //HashMap doctores =
    //KEY = "Traumatología"
    //VALUE = "Doctor1, Doctor2..."
    int capacidad = 300;
    HashMap<Integer, LinkedList<Doctor>> map_doctores = new HashMap<>();
    LinkedList<Paciente> lista_pacientes_prioridad = new LinkedList<>();
    ArrayList<Habitacion> habitaciones = new ArrayList<>();

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

    public LinkedList<Paciente> getLista_pacientes_prioridad() {
        return lista_pacientes_prioridad;
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
    
   
}
