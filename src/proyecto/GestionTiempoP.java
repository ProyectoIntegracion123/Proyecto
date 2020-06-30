/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Coarita
 */
public class GestionTiempoP extends javax.swing.JFrame {

    /**
     * Creates new form GestionTiempoP
     */
    Conexion conectar = new Conexion();
    Connection con;
    Statement st, st1;
    PreparedStatement ps, ps1;
    ResultSet rs, rs1;

    public GestionTiempoP() {
        initComponents();
        this.setTitle("Modificar el Tiempo de Prácticas");
        this.setLocationRelativeTo(GestionTiempoP.this);
        this.setResizable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        vCodigo = new javax.swing.JTextField();
        vTiempo = new javax.swing.JTextField();
        vModificar = new javax.swing.JButton();
        vAtras = new javax.swing.JButton();
        vVerificar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("CÓDIGO");

        jLabel2.setText("TIEMPO DE PRÁCTICA");

        vModificar.setText("MODIFICAR");
        vModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vModificarActionPerformed(evt);
            }
        });

        vAtras.setText("ATRÁS");
        vAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vAtrasActionPerformed(evt);
            }
        });

        vVerificar.setText("VERIFICAR");
        vVerificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vVerificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(vModificar)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(vAtras)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(vTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vVerificar))
                        .addGap(38, 38, 38))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(vCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(vVerificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(vTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vModificar)
                    .addComponent(vAtras))
                .addGap(49, 49, 49))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void vModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vModificarActionPerformed
        Connection con = null;

        try {
            con = conectar.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(GestionTiempoP.class.getName()).log(Level.SEVERE, null, ex);
        }

        String cod;
        int tiempo;
        boolean flag1 = false, flag2 = false;
        String var = "04";
        String codigo = "'1_" + var + "%'";

        cod = vCodigo.getText();
        tiempo = Integer.parseInt(vTiempo.getText());

        try {
            ps = con.prepareStatement("SELECT CODALU, TIMEPRACT FROM ALUMNO WHERE CODALU LIKE " + codigo);
            rs = ps.executeQuery();

            while (rs.next()) {

                String a = rs.getString(1);
                int b = rs.getInt(2);

                if (a.equals(cod)) {
                    flag1 = true;
                    if (b < tiempo) {
                        String cods = "'" + cod + "'";

                        ps1 = con.prepareStatement("update alumno set timepract = ? where  codalu = " + cods);
                        ps1.setInt(1, tiempo);
                        int var1 = 0;
                        var1 = ps1.executeUpdate();
                        
                        flag2 = true;
                    }
                }
            }
            
            if(flag1 == false){
                JOptionPane.showMessageDialog(this, "Código erroneo");
            }
            
            if(flag2 == true){
                JOptionPane.showMessageDialog(this, "El tiempo se modifico correctamente");
            }else{
                JOptionPane.showMessageDialog(this, "El tiempo es menor o igual al que se tiene");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GestionTiempoP.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_vModificarActionPerformed

    private void vAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vAtrasActionPerformed
        try {
            this.setVisible(false);
            GestionReportes p = new GestionReportes();
            p.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(GestionTiempoP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_vAtrasActionPerformed

    private void vVerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vVerificarActionPerformed
        Connection cn = null;

        try {
            cn = conectar.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(GestionTiempoP.class.getName()).log(Level.SEVERE, null, ex);
        }

        String cod;
        boolean flag = false;
        String var = "04";
        String codigo = "'1_" + var + "%'";

        cod = vCodigo.getText();

        try {
            ps = cn.prepareStatement("SELECT CODALU FROM ALUMNO WHERE CODALU LIKE " + codigo);
            rs = ps.executeQuery();

            while (rs.next()) {

                String a = rs.getString(1);

                if (a.equals(cod)) {
                    flag = true;
                }
            }
            
            if(flag == true){
                JOptionPane.showMessageDialog(this, "Código correcto");
            }else{
                JOptionPane.showMessageDialog(this, "Código incorrecto");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GestionTiempoP.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_vVerificarActionPerformed

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
            java.util.logging.Logger.getLogger(GestionTiempoP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionTiempoP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionTiempoP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionTiempoP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionTiempoP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton vAtras;
    private javax.swing.JTextField vCodigo;
    private javax.swing.JButton vModificar;
    private javax.swing.JTextField vTiempo;
    private javax.swing.JButton vVerificar;
    // End of variables declaration//GEN-END:variables
}
