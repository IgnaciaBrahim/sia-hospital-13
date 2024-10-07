package Ventanas;
import hospital.Hospital;
import javax.swing.*;

/**
 *
 * @author evapo
 */
public class OpcionesMenu extends javax.swing.JFrame {
    private static Hospital hospital;
    private JTextArea jTextArea1;

    public OpcionesMenu(Hospital hospital) {
        this.hospital = hospital;
        initComponents();
        explicarOpcionesMenu(); // Mostrar la explicación del menú al cargar la ventana
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setText("Explicación de las Opciones del Menú");

        jButton2.setBackground(new java.awt.Color(229, 229, 229));
        jButton2.setText("Continuar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        // Crear un JTextArea donde se mostrará la explicación del menú
        jTextArea1.setColumns(20);
        jTextArea1.setRows(15);
        jTextArea1.setEditable(false);  // Hacer que el texto no sea editable
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(40, 40, 40))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(90, 90, 90))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(34, 34, 34))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();  // Cierra la ventana actual

        // Abrir la ventana del menú (prueba.java)
        NewJFrame menuVentana = new NewJFrame(hospital);
        menuVentana.setLocationRelativeTo(null);
        menuVentana.setVisible(true);
    }

    // Método para explicar las opciones del menú, mostrando el texto en el JTextArea
    public void explicarOpcionesMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("+ - - - - - - - - - - - - - - - - - - - - - - - - - +\n");
        sb.append("| Explicación de las Opciones del Menú              |\n");
        sb.append("+ - - - - - - - - - - - - - - - - - - - - - - - - - +\n");
        sb.append("| 1. Añadir paciente:                               |\n");
        sb.append("|    Permite registrar un nuevo paciente en el      |\n");
        sb.append("|    sistema con su información personal y triage.  |\n");
        sb.append("| 2. Registrar alta voluntaria:                     |\n");
        sb.append("|    Marca a un paciente como dado de alta y libera |\n");
        sb.append("|    su cama, si corresponde.                       |\n");
        sb.append("| 3. Asignar doctor a paciente:                     |\n");
        sb.append("|    Asigna un doctor disponible al paciente según  |\n");
        sb.append("|    su nivel de triage.                            |\n");
        sb.append("| 4. Asignar habitación a paciente:                 |\n");
        sb.append("|    Asigna una habitación disponible al paciente.  |\n");
        sb.append("| 5. Registrar visita a paciente:                   |\n");
        sb.append("|    Registra la visita de un médico al paciente    |\n");
        sb.append("|    para actualizar su estado.                     |\n");
        sb.append("| 6. Consultar pacientes por triage:                |\n");
        sb.append("|    Muestra una lista de pacientes según su nivel  |\n");
        sb.append("|    de triage.                                     |\n");
        sb.append("| 7. Ver estado de habitaciones y camas:            |\n");
        sb.append("|    Muestra el estado de todas las habitaciones,   |\n");
        sb.append("|    indicando qué camas están ocupadas.            |\n");
        sb.append("| 8. Ver disponibilidad de doctores:                |\n");
        sb.append("|    Muestra la lista de doctores disponibles según |\n");
        sb.append("|    su triage asignado.                            |\n");
        sb.append("| 9. Gestionar Medicos por Triaje                   |\n");
        sb.append("|    Se podrá eliminar o agregar un médico a un     |\n");
        sb.append("|    triaje específico.                             |\n");
        sb.append("| 10. Explicación de las opciones del menú:         |\n");
        sb.append("|    Muestra esta pantalla de ayuda que explica     |\n");
        sb.append("|    cada opción del menú.                          |\n");
        sb.append("| 11. Salir del sistema:                            |\n");
        sb.append("|    Cierra la aplicación.                          |\n");
        sb.append("+ - - - - - - - - - - - - - - - - - - - - - - - - - +");

        // Mostrar el texto en el JTextArea
        jTextArea1.setText(sb.toString());
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
            java.util.logging.Logger.getLogger(OpcionesMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OpcionesMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OpcionesMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OpcionesMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OpcionesMenu(hospital).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration                   
}
