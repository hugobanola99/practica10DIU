
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

public class Practica10 extends javax.swing.JFrame {

    
    Tarea tarea = null;
    JFileChooser fc = new JFileChooser();
    File carpeta;
    DefaultListModel modelo = new DefaultListModel();
    final int BUFFER_SIZE=1024;
    public List<String> archivos;
    File carpetaComprimida;
    String nombreCarpeta;
    
    private class Tarea extends SwingWorker<Void,Void>{
        Practica10 frame;
        @Override
        protected Void doInBackground() throws Exception {
            
            try
            {
                // Objeto para referenciar a los archivos que queremos comprimir
                BufferedInputStream origin = null;
                // Objeto para referenciar el archivo zip de salida
                FileOutputStream dest = new FileOutputStream(carpetaComprimida+"\\"+nombreCarpeta+".zip");
                ZipOutputStream out = new ZipOutputStream(dest);
                // Buffer de transferencia para almacenar datos a comprimir
                byte[] data = new byte[BUFFER_SIZE];
                //Iterator i = archivos.iterator();
                
                for(int i = 0; i < archivos.size(); i++){
                

                    String filename = archivos.get(i);
                    FileInputStream fi = new FileInputStream(filename);
                    origin = new BufferedInputStream(fi, BUFFER_SIZE);

                    System.out.println("Comprimiendo "+filename);
                    Thread.sleep(1000);
                    

                    System.out.println(calculoBarra(i));
                    barraProgreso.setValue(calculoBarra(i));
                    
                    System.out.println((i/archivos.size())*100);
                    ZipEntry entry = new ZipEntry( filename );
                    out.putNextEntry( entry );
                    // Leemos datos desde el archivo origen y se envían al archivo destino
                    int count;
                    while((count = origin.read(data,0,BUFFER_SIZE)) > 0)
                    {
                        out.write(data, 0, count);
                    }
                    // Cerramos el archivo origen, ya enviado a comprimir
                    origin.close();
                    //out.closeEntry();
                }
                barraProgreso.setValue(100);
                // Cerramos el archivo zip
                out.close();
                System.out.println("Cerrar");
                JOptionPane.showConfirmDialog(null,"Se han comprimido los archivos con exito","Comprimir Archivos",
                     JOptionPane.DEFAULT_OPTION);
                }
            catch( Exception e )
            {
                e.printStackTrace();
            }
           
            return null;
            
        }
        
        private int calculoBarra(double i){
            double size = (double) archivos.size();
            double c = (double) i;
            double aux = (c/size)*100;
            return (int) aux;
        }
        
        @Override
        public void done(){
            System.out.println("Tarea Terminada");
            cancelar.setVisible(false);
            barraProgreso.setVisible(false);
            progreso.setVisible(false);
            
        }
        
        
    }
    
    public Practica10() {
        initComponents();
        barraProgreso.setStringPainted(true);
        listaArchivos.setModel(modelo);
        barraProgreso.setVisible(false);
        cancelar.setVisible(false);
        progreso.setVisible(false);
        nombreCarpeta = "";
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        comenzar = new javax.swing.JButton();
        terminar = new javax.swing.JButton();
        progreso = new javax.swing.JLabel();
        barraProgreso = new javax.swing.JProgressBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaArchivos = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        comprimir = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        comenzar.setText("Comenzar");
        comenzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comenzarActionPerformed(evt);
            }
        });

        terminar.setText("Terminar");
        terminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terminarActionPerformed(evt);
            }
        });

        progreso.setText("Progreso Tarea");

        jScrollPane1.setViewportView(listaArchivos);

        jLabel2.setText("Archivos");

        comprimir.setText("Comprimir Archivos");
        comprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comprimirActionPerformed(evt);
            }
        });

        cancelar.setText("Cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(comenzar)
                        .addGap(28, 28, 28)
                        .addComponent(terminar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(barraProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cancelar))
                    .addComponent(progreso))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comprimir)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel2)))
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comenzar)
                            .addComponent(terminar)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(progreso)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cancelar)
                            .addComponent(barraProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(26, 26, 26)
                .addComponent(comprimir)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comenzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comenzarActionPerformed
        // TODO add your handling code here:
        /*
        tarea = new Tarea();
        tarea.execute();
        */
        modelo.clear();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int res = fc.showOpenDialog(null);
        if(res == JFileChooser.APPROVE_OPTION){
            carpeta = fc.getSelectedFile();
            carpetaComprimida=carpeta;
            nombreCarpeta = carpeta.getName();
            File[] ficheros = carpeta.listFiles();
            for(File f : ficheros){
                modelo.addElement(f.getAbsolutePath());
                
                
            }
        }
    }//GEN-LAST:event_comenzarActionPerformed

    private void terminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terminarActionPerformed
        // TODO add your handling code here:
        tarea.cancel(true);
    }//GEN-LAST:event_terminarActionPerformed

    private void comprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comprimirActionPerformed
        // TODO add your handling code here:
        int res = fc.showSaveDialog(this);
        
        carpetaComprimida = fc.getSelectedFile();
        System.out.println(carpetaComprimida.getAbsolutePath());
        nombreCarpeta= JOptionPane.showInputDialog(this,"Introduce el nombre de la carpeta comprimida");
        archivos = listaArchivos.getSelectedValuesList();
        barraProgreso.setVisible(true);
        barraProgreso.setValue(0);
        cancelar.setVisible(true);
        progreso.setVisible(true);
        tarea = new Tarea();
        tarea.execute();
        
        
    }//GEN-LAST:event_comprimirActionPerformed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        // TODO add your handling code here:
        tarea.cancel(true);
    }//GEN-LAST:event_cancelarActionPerformed
    
    

    
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
            java.util.logging.Logger.getLogger(Practica10.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Practica10.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Practica10.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Practica10.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Practica10().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barraProgreso;
    private javax.swing.JButton cancelar;
    private javax.swing.JButton comenzar;
    private javax.swing.JButton comprimir;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listaArchivos;
    private javax.swing.JLabel progreso;
    private javax.swing.JButton terminar;
    // End of variables declaration//GEN-END:variables
}
