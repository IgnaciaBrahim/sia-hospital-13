package Ventanas;

import hospital.Hospital;
import hospital.Habitacion;

/**
 *
 * @author evapo
 */
public class mostrarTodasHabitaciones extends javax.swing.JFrame {

    private static Hospital hospital;  // Instancia de Hospital

    // Constructor que recibe la instancia de Hospital
    public mostrarTodasHabitaciones(Hospital hospital) {
        this.hospital = hospital;  // Guardar la instancia del hospital
        initComponents();
        mostrarEstadoHabitaciones();  // Llamar a la función para mostrar las habitaciones
    }

    // Inicialización de componentes
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel6.setText("Lista de Habitaciones");
        jLabel6.setToolTipText("");

        // Configuración del JTextArea para mostrar el estado de las habitaciones
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        // Botón "Continuar"
        jButton1.setText("Continuar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        // Layout del contenido
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }

    // Método que muestra el estado de todas las habitaciones en el JTextArea
    private void mostrarEstadoHabitaciones() {
        StringBuilder sb = new StringBuilder();

        // Iterar sobre las habitaciones del hospital y obtener su estado
        for (int i = 1; i <= 150; i++) {
            sb.append("==================================================================================================\n");
            Habitacion aux = hospital.obtenerHabitacion(i);
            String cadena_mostrar = hospital.obtenerMostrarHabitacion(aux);
            sb.append(cadena_mostrar).append("\n");
        }

        // Mostrar el estado de todas las habitaciones en el JTextArea
        jTextArea1.setText(sb.toString());
    }

    // Acción del botón "Continuar"
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        this.dispose();  // Cierra la ventana actual

        // Volver al menú principal
        NewJFrame menuVentana = new NewJFrame(hospital);
        menuVentana.setLocationRelativeTo(null);
        menuVentana.setVisible(true);
    }                                        

    // Método principal para lanzar la ventana
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Aquí debes pasar una instancia de Hospital
                new mostrarTodasHabitaciones(hospital).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration                   
}
