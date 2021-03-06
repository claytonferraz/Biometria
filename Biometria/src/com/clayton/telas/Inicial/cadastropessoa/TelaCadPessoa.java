/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clayton.telas.Inicial.cadastropessoa;

import com.clayton.metodos.CapturaDigital;
import com.clayton.model.CadPessoa;
import com.clayton.telas.cadastrardigital.CadastrodeDigital;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JTextField;

/**
 *
 * @author Clayton Ferraz
 */
public class TelaCadPessoa extends javax.swing.JFrame {

    /**
     * Creates new form TelaCadPessoa
     */
    public TelaCadPessoa() {
        
        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
        initComponents();
        setLocation((tela.width-this.getSize().width)/2, (tela.height-this.getSize().height)/2);
        
    }
    
    public TelaCadPessoa(CadPessoa pe) {
        this.pe=pe;
        initComponents();
//        nomepessoa.setText(pe.getLogin());
        
    }

    private CapturaDigital me;
    private CadPessoa pe;

    public CapturaDigital getMe() {
        return me;
    }

    public void setMe(CapturaDigital me) {
        this.me = me;
    }

    public CadPessoa getPe() {
        return pe;
    }

    public JTextField getNomepessoa() {
        return nomepessoa;
    }

    public void setNomepessoa(JTextField nomepessoa) {
        this.nomepessoa = nomepessoa;
    }
    
    

    public void setPe(CadPessoa pe) {
        this.pe = pe;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        nomepessoa = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setText("Nome:");

        nomepessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomepessoaActionPerformed(evt);
            }
        });

        jButton2.setText("Capturar Digitais");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nomepessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addComponent(jButton2)))
                .addContainerGap(157, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nomepessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(jButton2)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nomepessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomepessoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomepessoaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

//  Botão que chama a tela de cadastro de digital.
        CadastrodeDigital te = new CadastrodeDigital();
        CadPessoa cape = new CadPessoa();
        cape.setLogin(nomepessoa.getText());
        System.out.println(cape.getLogin());
        te.setPe(cape);
        te.setAlterar(false);
        System.out.println(te.isAlterar()+"Telacadpessoa");
        te.setVisible(true);        
        te.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.dispose();


    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCadPessoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadPessoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadPessoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadPessoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadPessoa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField nomepessoa;
    // End of variables declaration//GEN-END:variables
}
