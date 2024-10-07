package Ventanas;

import hospital.Hospital;
import hospital.Paciente;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NuevoPaciente extends JFrame {
    private static Hospital hospital;  // Añadir la instancia de Hospital aquí

    // Componentes de la interfaz gráfica
    private JTextField jTextField1, jTextField2, jTextField4;
    private JRadioButton jRadioButton1, jRadioButton2, jRadioButton3, jRadioButton4, jRadioButton5, jRadioButton6, jRadioButton7;
    private ButtonGroup buttonGroup1, buttonGroup2;
    private JButton jButton1, jButton2;

    // Constructor donde pasas la instancia de Hospital
    public NuevoPaciente(Hospital hospital) {
        this.hospital = hospital;  // Recibe la instancia del hospital
        initComponents();
    }

    private void initComponents() {
        // Inicialización de componentes
        buttonGroup1 = new ButtonGroup();
        buttonGroup2 = new ButtonGroup();
        JPanel jPanel1 = new JPanel();
        JLabel jLabel6 = new JLabel();
        JLabel jLabel1 = new JLabel();
        jTextField1 = new JTextField();
        JLabel jLabel2 = new JLabel();
        jTextField2 = new JTextField();
        JLabel jLabel3 = new JLabel();
        jTextField4 = new JTextField();
        JLabel jLabel4 = new JLabel();
        JLabel jLabel5 = new JLabel();
        jRadioButton2 = new JRadioButton();
        jRadioButton3 = new JRadioButton();
        jRadioButton1 = new JRadioButton();
        jRadioButton4 = new JRadioButton();
        jRadioButton5 = new JRadioButton();
        jRadioButton6 = new JRadioButton();
        jRadioButton7 = new JRadioButton();
        jButton1 = new JButton();
        jButton2 = new JButton();

        // Configuración de la ventana
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(new Color(204, 204, 255));

        jPanel1.setBackground(new Color(204, 204, 255));

        jLabel6.setFont(new Font("Yu Gothic UI Semibold", 1, 24));
        jLabel6.setText("Agregar Paciente:");

        jLabel1.setText("Ingrese RUT del Paciente (ej. XX.XXX.XXX-X)");

        jLabel2.setText("Ingrese Nombre y Apellido del Paciente:");

        jLabel3.setText("Indique la Edad:");

        jLabel4.setText("Indique Sexo del Paciente:");

        jLabel5.setText("Seleccione la Condición del Paciente:");

        jRadioButton2.setBackground(new Color(204, 204, 255));
        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Femenino");

        jRadioButton3.setBackground(new Color(204, 204, 255));
        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("Masculino");

        jRadioButton1.setBackground(new Color(204, 204, 255));
        buttonGroup2.add(jRadioButton1);
        jRadioButton1.setText("1) Riesgo Vital");

        jRadioButton4.setBackground(new Color(204, 204, 255));
        buttonGroup2.add(jRadioButton4);
        jRadioButton4.setText("2) Alta Urgencia");

        jRadioButton5.setBackground(new Color(204, 204, 255));
        buttonGroup2.add(jRadioButton5);
        jRadioButton5.setText("3) Mediana Urgencia");

        jRadioButton6.setBackground(new Color(204, 204, 255));
        buttonGroup2.add(jRadioButton6);
        jRadioButton6.setText("4) Baja Urgencia");

        jRadioButton7.setBackground(new Color(204, 204, 255));
        buttonGroup2.add(jRadioButton7);
        jRadioButton7.setText("5) No Urgente");

        jButton1.setText("Aceptar");
        // Aquí es donde enlazamos el evento del botón con su método
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);  // Llamada al método que será creado más abajo
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton2ActionPerformed(evt);  // Llamada al método para cancelar
            }
        });

        // Configuración del layout omitida por simplicidad
        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6)
                        .addComponent(jLabel1)
                        .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(jTextField4, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(jRadioButton2)
                        .addComponent(jRadioButton3)
                        .addComponent(jLabel5)
                        .addComponent(jRadioButton1)
                        .addComponent(jRadioButton4)
                        .addComponent(jRadioButton5)
                        .addComponent(jRadioButton6)
                        .addComponent(jRadioButton7)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jButton2)
                            .addGap(18, 18, 18)
                            .addComponent(jButton1)))
                    .addContainerGap(150, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel6)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel2)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel3)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jTextField4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel4)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jRadioButton2)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jRadioButton3)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel5)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jRadioButton1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jRadioButton4)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jRadioButton5)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jRadioButton6)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jRadioButton7)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(jButton1))
                    .addContainerGap(20, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }

    // Método que se ejecuta cuando el botón "Aceptar" es presionado
    private void jButton1ActionPerformed(ActionEvent evt) {
        // Capturar los datos desde los campos de texto
        String rut = jTextField1.getText();
        String nombreApellido = jTextField2.getText();
        String edadStr = jTextField4.getText();

        // Validar que los campos obligatorios no estén vacíos
        if (rut.isEmpty() || nombreApellido.isEmpty() || edadStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Dividir el nombre y apellido
        String[] nombreApellidoArray = nombreApellido.split(" ");
        if (nombreApellidoArray.length < 2) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese tanto el nombre como el apellido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String nombre = nombreApellidoArray[0];
        String apellido = nombreApellidoArray[1];

        // Validar la edad
        int edad;
        try {
            edad = Integer.parseInt(edadStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La edad debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar el sexo (0 = Hombre, 1 = Mujer)
        int sexo;
        if (jRadioButton2.isSelected()) {
            sexo = 1;  // Femenino
        } else if (jRadioButton3.isSelected()) {
            sexo = 0;  // Masculino
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione el sexo del paciente.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar el triaje (1 = Riesgo Vital, 2 = Alta Urgencia, etc.)
        int triaje;
        if (jRadioButton1.isSelected()) {
            triaje = 1;
        } else if (jRadioButton4.isSelected()) {
            triaje = 2;
        } else if (jRadioButton5.isSelected()) {
            triaje = 3;
        } else if (jRadioButton6.isSelected()) {
            triaje = 4;
        } else if (jRadioButton7.isSelected()) {
            triaje = 5;
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione la condición del paciente.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Lógica adicional para pacientes con triaje 5
        if (triaje == 5) {
            JOptionPane.showMessageDialog(this, "El paciente será derivado al CESFAM correspondiente.");
            this.dispose();
            NewJFrame menuVentana = new NewJFrame(hospital);
            menuVentana.setLocationRelativeTo(null);
            menuVentana.setVisible(true);
            return;
        }

        // Crear el paciente y agregarlo al hospital
        Paciente paciente = new Paciente(rut, nombre, apellido, edad, sexo, triaje);
        hospital.agregarPaciente(paciente);

        // Mostrar un mensaje de confirmación
        JOptionPane.showMessageDialog(this, "¡Se ha añadido al paciente con éxito!");

        // Cerrar esta ventana y volver al menú principal
        this.dispose();
        NewJFrame menuVentana = new NewJFrame(hospital);
        menuVentana.setLocationRelativeTo(null);
        menuVentana.setVisible(true);
    }

    // Método para el botón "Cancelar"
    private void jButton2ActionPerformed(ActionEvent evt) {
        this.dispose();  // Cerrar la ventana actual
        NewJFrame menuVentana = new NewJFrame(hospital);
        menuVentana.setLocationRelativeTo(null);
        menuVentana.setVisible(true);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NuevoPaciente ventana = new NuevoPaciente(hospital);
                ventana.setLocationRelativeTo(null);
                ventana.setVisible(true);
            }
        });
    }
}

