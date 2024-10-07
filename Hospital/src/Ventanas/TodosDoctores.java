package Ventanas;

import hospital.Hospital;
import hospital.Doctor;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 *
 * @author evapo
 */
public class TodosDoctores extends javax.swing.JFrame {

    private static Hospital hospital;  // Instancia de Hospital

    // Constructor que recibe la instancia de Hospital
    public TodosDoctores(Hospital hospital) {
        this.hospital = hospital;  // Asignar la instancia del hospital
        initComponents();
        mostrarDoctoresDisponibles();  // Mostrar los doctores disponibles
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel6.setText("Disponibilidad de Doctores");

        jList1.setBackground(new java.awt.Color(204, 204, 255));
        jScrollPane1.setViewportView(jList1);

        jButton2.setBackground(new java.awt.Color(229, 229, 229));
        jButton2.setText("Continuar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    // Método para mostrar los doctores disponibles
    private void mostrarDoctoresDisponibles() {
        DefaultListModel<String> model = new DefaultListModel<>(); // Modelo para la lista

        // Obtener el tamaño de la lista de doctores
        int size = hospital.getListaDoctoresSize();
        int cont = 0;

        // Recorrer la lista de doctores
        for (int i = 0; i < size; i++) {
            Doctor doctor_aux = hospital.obtenerDoctor(i);
            if (doctor_aux != null && doctor_aux.isDisponible()) {
                cont++;
                model.addElement(doctor_aux.obtenerDatos());  // Agregar el doctor disponible al modelo
            }
        }

        if (cont == 0) {
            model.addElement("No hay doctores disponibles...");
        }

        jList1.setModel(model);  // Asignar el modelo a la lista
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // Cerrar la ventana y volver al menú principal
        this.dispose();  // Cierra la ventana actual
        NewJFrame menuVentana = new NewJFrame(hospital);  // Pasar la instancia de hospital
        menuVentana.setLocationRelativeTo(null);
        menuVentana.setVisible(true);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Pasar una instancia de Hospital cuando se crea la ventana
                new TodosDoctores(hospital).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration                   
}
