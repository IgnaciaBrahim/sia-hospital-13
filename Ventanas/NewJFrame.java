/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Ventanas;

/**
 *
 * @author evapo
 */
public class NewJFrame extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public NewJFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    //@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jList1 = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 255));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setText("Escoja una opción:");

        jList1.setBackground(new java.awt.Color(204, 204, 255));
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Añadir Paciente", "Registrar Alta Voluntaria", "Asignar Doctor a Paciente", "Asignar Habitación a Paciente", "Registrar Visita a Paciente", "Consultar Pacientes por Triaje", "Ver Estado de Habitaciones y Camas", "Ver Disponibilidad de Doctores", "Gestionar Medicos por Triaje", "Explicación de las Opciones del Menú", "Salir del Sistema." };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jList1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jList1)
                .addContainerGap(28, Short.MAX_VALUE))
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
                .addGap(24, 24, 24)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {                                    
        // Verificar que la opción seleccionada es "Añadir Paciente"
    String seleccion = jList1.getSelectedValue().toString();

    if (seleccion.equals("Añadir Paciente")) {
  
        NuevoPaciente nuevoPacienteVentana = new NuevoPaciente();
        nuevoPacienteVentana.setLocationRelativeTo(null);
        nuevoPacienteVentana.setVisible(true);

        // Cerrar la ventana actual
        this.dispose();  
    }
    if (seleccion.equals("Registrar Alta Voluntaria")) {
    
        RegistrarAlta altaVol = new RegistrarAlta();
        altaVol.setLocationRelativeTo(null);
        altaVol.setVisible(true);

        // Cerrar la ventana actual
        this.dispose(); 
    }
    if (seleccion.equals("Asignar Doctor a Paciente")) {

        AsignarDoctor asignarDoct = new AsignarDoctor();
        asignarDoct.setLocationRelativeTo(null);
        asignarDoct.setVisible(true);

        // Cerrar la ventana actual
        this.dispose(); 
    }
    if (seleccion.equals("Asignar Habitación a Paciente")) {
    
        AsignarHabitacion asignarHab = new AsignarHabitacion();
        asignarHab.setLocationRelativeTo(null);
        asignarHab.setVisible(true);

        // Cerrar la ventana actual
        this.dispose(); 
    }
    if (seleccion.equals("Registrar Visita a Paciente")) {

        RegistrarVisita visita = new RegistrarVisita();
        visita.setLocationRelativeTo(null);
        visita.setVisible(true);

        // Cerrar la ventana actual
        this.dispose(); 
    }
    if (seleccion.equals("Consultar Pacientes por Triaje")) {
        
        ConsultarPorTriaje triajeBusc = new ConsultarPorTriaje();
        triajeBusc.setLocationRelativeTo(null);
        triajeBusc.setVisible(true);

        // Cerrar la ventana actual
        this.dispose();  
    }
    if (seleccion.equals("Ver Estado de Habitaciones y Camas")) {
       
        EstdaoHabitacionCama habCam = new EstdaoHabitacionCama();
        habCam.setLocationRelativeTo(null);
        habCam.setVisible(true);

        // Cerrar la ventana actual
        this.dispose(); 
    }
    if (seleccion.equals("Ver Disponibilidad de Doctores")) {
   
        DisponibilidadDoctores doctoresVt = new DisponibilidadDoctores();
        doctoresVt.setLocationRelativeTo(null);
        doctoresVt.setVisible(true);

        // Cerrar la ventana actual
        this.dispose(); 
    }
    if (seleccion.equals("Explicación de las Opciones del Menú")) {
        // Crear y mostrar la ventana NuevoPaciente
        OpcionesMEnu menuu = new OpcionesMEnu();
        menuu.setLocationRelativeTo(null);
        menuu.setVisible(true);
        // Cerrar la ventana actual
        this.dispose(); 
    }
    if (seleccion.equals("Salir del Sistema.")) {
        // Crear y mostrar la ventana NuevoPaciente
        SalirSistema salida = new SalirSistema();
        salida.setLocationRelativeTo(null);
        salida.setVisible(true);
        // Cerrar la ventana actual
        this.dispose();
    }

    if (seleccion.equals("Gestionar Medicos por Triaje")) { 
        // Crear y mostrar la ventana ElimAgregMedicos
        ElimAgregMedicos elimAgreg = new ElimAgregMedicos();
        elimAgreg.setLocationRelativeTo(null);
        elimAgreg.setVisible(true);
    
        // Cerrar la ventana actual
        this.dispose();  // Esto cierra NewJFrame
    }
    }                             

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    try {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (ClassNotFoundException ex) {
        java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            NewJFrame ventana = new NewJFrame();  // Crear la ventana
            
            ventana.setLocationRelativeTo(null);  // Centramos la ventana en la pantalla
            ventana.setVisible(true);  // Hacer visible la ventana
        }
    });
}


    // Variables declaration - do not modify                     
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration                   
}
