package Ventanas;

import hospital.Hospital;
import hospital.Paciente; // Asegúrate de importar la clase Paciente
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author evapo
 */
public class ConsultarPorTriaje extends javax.swing.JFrame {

    private static Hospital hospital;  // Instancia de Hospital

    // Constructor que recibe la instancia de Hospital
    public ConsultarPorTriaje(Hospital hospital) {
        this.hospital = hospital;  // Asignar la instancia del hospital
        initComponents();
    }

    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel6.setText("Consultar Pacientes por Triaje:");
        jLabel6.setToolTipText("");

        jLabel1.setText("Ingrese una opción:");

        jRadioButton1.setBackground(new java.awt.Color(204, 204, 255));
        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("1) Riesgo Vital");

        jRadioButton2.setBackground(new java.awt.Color(204, 204, 255));
        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("2) Alta Urgencia");

        jRadioButton3.setBackground(new java.awt.Color(204, 204, 255));
        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("3) Mediana Urgencia");

        jRadioButton4.setBackground(new java.awt.Color(204, 204, 255));
        buttonGroup1.add(jRadioButton4);
        jRadioButton4.setText("4) Baja Urgencia");

        jButton2.setBackground(new java.awt.Color(229, 229, 229));
        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jRadioButton4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    // Acción del botón "Aceptar"
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        int triajeSeleccionado = 0;

        // Obtener el valor seleccionado en los radio buttons
        if (jRadioButton1.isSelected()) {
            triajeSeleccionado = 1;
        } else if (jRadioButton2.isSelected()) {
            triajeSeleccionado = 2;
        } else if (jRadioButton3.isSelected()) {
            triajeSeleccionado = 3;
        } else if (jRadioButton4.isSelected()) {
            triajeSeleccionado = 4;
        }

        // Mostrar la lista de pacientes por triaje en un JTextArea scrolleable
        String pacientesPorTriaje = obtenerPacientesPorTriaje(triajeSeleccionado);
        if (pacientesPorTriaje.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay pacientes en este triaje.", "Pacientes por Triaje", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JTextArea textArea = new JTextArea(pacientesPorTriaje);
            textArea.setEditable(false);  // Para que no se pueda editar el texto
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new java.awt.Dimension(400, 300));  // Tamaño preferido del área de scroll
            JOptionPane.showMessageDialog(this, scrollPane, "Pacientes por Triaje", JOptionPane.INFORMATION_MESSAGE);
        }

        // Cerrar la ventana actual y volver al menú principal
        this.dispose();
        NewJFrame menuVentana = new NewJFrame(hospital);
        menuVentana.setLocationRelativeTo(null);
        menuVentana.setVisible(true);
    }

    // Acción del botón "Cancelar"
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // Cerrar la ventana actual y volver al menú principal
        this.dispose();
        NewJFrame menuVentana = new NewJFrame(hospital);
        menuVentana.setLocationRelativeTo(null);
        menuVentana.setVisible(true);
    }

    // Método que obtiene los pacientes según el triaje seleccionado y los retorna como un String
    private String obtenerPacientesPorTriaje(int triaje) {
        StringBuilder resultado = new StringBuilder();
        List<Paciente> listaPacientes = hospital.obtenerListaPacientes();

        resultado.append("Pacientes en Triaje: ").append(triaje).append("\n");
        resultado.append("- - - - - - - - - - - - - - - - - -\n");

        for (Paciente paciente : listaPacientes) {
            if (paciente.getTriage() == triaje) {
                resultado.append("- Nombre: ").append(paciente.getNombre()).append("\n");
                resultado.append("- Apellido: ").append(paciente.getApellido()).append("\n");
                resultado.append("- RUT: ").append(paciente.getRut()).append("\n");
                if (paciente.getNum_habitacion() != null) {
                    resultado.append("- Habitación: ").append(paciente.getNum_habitacion()).append("\n");
                } else {
                    resultado.append("- Sin habitación asignada.\n");
                }
                resultado.append("- - - - - - - - - - - - - - - - - -\n");
            }
        }

        return resultado.toString();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConsultarPorTriaje.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultarPorTriaje.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultarPorTriaje.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultarPorTriaje.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultarPorTriaje(hospital).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    // End of variables declaration                   
}

