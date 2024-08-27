package hospital;

import java.util.LinkedList;

public class GestorPaciente {

    private LinkedList<Paciente> listaPacientes;

    public GestorPaciente() {
        this.listaPacientes = new LinkedList<>();
    }


    public void añadirPaciente(Paciente paciente) {
        int i = 0;
        while (i < listaPacientes.size() && listaPacientes.get(i).getTriage() <= paciente.getTriage()) {
            i++;
        }
        listaPacientes.add(i, paciente);
        System.out.println("Paciente añadido con éxito.");
    }

    public void registrarAltaVoluntaria(String rut) {
        Paciente paciente = buscarPacientePorRut(rut);
        if (paciente != null) {
            if (paciente.getTriage() == 1) {
                System.out.println("No se puede dar de alta a pacientes con triaje máximo.");
                return;
            }

            listaPacientes.remove(paciente);
            System.out.println("Paciente dado de alta con éxito.");

        } else {
            System.out.println("Paciente no encontrado.");
        }
    }


    public void cambiarTriage(String rut, int nuevoTriage) {
        Paciente paciente = buscarPacientePorRut(rut);
        if (paciente != null) {
            listaPacientes.remove(paciente);
            paciente.setTriage(nuevoTriage);
            añadirPaciente(paciente);
            System.out.println("Triaje actualizado con éxito.");
        } else {
            System.out.println("Paciente no encontrado.");
        }
    }

    public void asignarCama(String rut, int numHabitacion, int cama) {
        Paciente paciente = buscarPacientePorRut(rut);
        if (paciente != null) {
            
            Habitacion habitacion = Hospital.habitaciones.get(numHabitacion - 1);
            if (!habitacion.isOcupado()) {
                if (cama == 1 && habitacion.getCama_1() == null) {
                    habitacion.setCama_1(paciente);
                    paciente.setNum_habitacion(String.valueOf(numHabitacion));
                    habitacion.setOcupado(habitacion.getCama_2() != null);
                    System.out.println("Cama asignada con éxito.");
                } else if (cama == 2 && habitacion.getCama_2() == null) {
                    habitacion.setCama_2(paciente);
                    paciente.setNum_habitacion(String.valueOf(numHabitacion));
                    habitacion.setOcupado(habitacion.getCama_1() != null);
                    System.out.println("Cama asignada con éxito.");
                } else {
                    System.out.println("Cama no disponible.");
                }
            } else {
                System.out.println("Habitación ocupada.");
            }
        } else {
            System.out.println("Paciente no encontrado.");
        }
    }

    public void liberarCama(String rut) {
        Paciente paciente = buscarPacientePorRut(rut);
        if (paciente != null) {
            int numHabitacion = Integer.parseInt(paciente.getNum_habitacion());
            Habitacion habitacion = Hospital.habitaciones.get(numHabitacion - 1);

            if (habitacion.getCama_1() == paciente) {
                habitacion.setCama_1(null);
            } else if (habitacion.getCama_2() == paciente) {
                habitacion.setCama_2(null);
            }
            habitacion.setOcupado(habitacion.getCama_1() != null || habitacion.getCama_2() != null);
            paciente.setNum_habitacion(null);
            System.out.println("Cama liberada con éxito.");
        } else {
            System.out.println("Paciente no encontrado.");
        }
    }

    private Paciente buscarPacientePorRut(String rut) {
        for (Paciente p : listaPacientes) {
            if (p.getDatos_paciente().getRut().equals(rut)) {
                return p;
            }
        }
        return null;
    }
