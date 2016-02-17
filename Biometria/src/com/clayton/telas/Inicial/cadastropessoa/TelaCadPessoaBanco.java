/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clayton.telas.Inicial.cadastropessoa;

import com.clayton.metodos.CapturaDigital;
import com.clayton.metodos.DigitalPersistencia;
import com.clayton.model.CadDigital;
import com.clayton.model.CadPessoa;
import com.clayton.telas.Inicial.NovaTela;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Clayton Ferraz
 */
public class TelaCadPessoaBanco extends javax.swing.JFrame {

    /**
     * Creates new form TelaCadPessoa
     */
    public TelaCadPessoaBanco() {

        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
        initComponents();
        setLocation((tela.width - this.getSize().width) / 2, (tela.height - this.getSize().height) / 2);

    }

    public TelaCadPessoaBanco(CadPessoa pe, boolean x, CadDigital cad) {

        initComponents();
        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
        if (x == true) {
            salvaralterar.setVisible(true);
            salvar.setVisible(false);
            System.out.println("Alterar Verdade");
        }
        if (x == false) {
            salvaralterar.setVisible(false);
            salvar.setVisible(true);
            System.out.println("Alterar Falso+++");
        }

        setLocation((tela.width - this.getSize().width) / 2, (tela.height - this.getSize().height) / 2);
        this.pe = pe;
        this.cad = cad;
        this.nomepessoa.setText(pe.getLogin());

    }

    private boolean alterar = false;
    private CapturaDigital me=null;
    private CadPessoa pe=null;
    private CadDigital cad=null;

    public boolean isAlterar() {
        return alterar;
    }

    public void setAlterar(boolean alterar) {
        this.alterar = alterar;
    }

    public CadDigital getCad() {
        return cad;
    }

    public void setCad(CadDigital cad) {
        this.cad = cad;
    }

    public CapturaDigital getMe() {
        return me;
    }

    public void setMe(CapturaDigital me) {
        this.me = me;
    }

    public CadPessoa getPe() {
        return pe;
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

        salvar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        nomepessoa = new javax.swing.JLabel();
        salvaralterar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        salvar.setText("Gravar Digitais");
        salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarActionPerformed(evt);
            }
        });

        jLabel3.setText("Nome:");

        nomepessoa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nomepessoa.setText("Nome:");

        salvaralterar.setText("Alterar Digitais");
        salvaralterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvaralterarActionPerformed(evt);
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
                        .addGap(28, 28, 28)
                        .addComponent(nomepessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(232, 232, 232)
                        .addComponent(salvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(salvaralterar)))
                .addContainerGap(172, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nomepessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salvar)
                    .addComponent(salvaralterar))
                .addGap(44, 44, 44))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarActionPerformed
        if (getMe() == null) {
            JOptionPane.showMessageDialog(rootPane, "Voce ainda não Cadastrou Ditais");

        } else {

            DigitalPersistencia di = new DigitalPersistencia();
            di.setCapturadigital(getMe());
            pe.setLogin(nomepessoa.getText());
            CadDigital cad = new CadDigital(pe);
            di.setCaddigital(cad);
            di.setLogin(pe);
            String c = di.CadastraLoginTeste();
            di.ExtrairDigitais();
            String b = di.CadastraDigitalTeste();
            JOptionPane.showConfirmDialog(rootPane, b + "\n" + c);
            NovaTela nova = new NovaTela();
            nova.setMe(me);
            nova.setVisible(true);
            this.dispose();

        }
    }//GEN-LAST:event_salvarActionPerformed

    private void salvaralterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvaralterarActionPerformed
        // TODO add your handling code here:
        if (getMe() == null) {
            JOptionPane.showMessageDialog(rootPane, "Voce ainda não Cadastrou Ditais");

        } else {

            DigitalPersistencia di = new DigitalPersistencia();
            di.setCapturadigital(getMe());
            cad.setIdpessoa(pe);
            di.setCaddigital(cad);
            //di.setLogin(pe);
            di.ExtrairDigitais();
            String b = di.AlteraDigitalTeste();
            JOptionPane.showConfirmDialog(rootPane, b + "\n");
            NovaTela nova = new NovaTela();
            nova.setMe(me);
            nova.setVisible(true);
            this.dispose();

        }
    }//GEN-LAST:event_salvaralterarActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCadPessoaBanco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadPessoaBanco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadPessoaBanco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadPessoaBanco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadPessoaBanco().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel nomepessoa;
    private javax.swing.JButton salvar;
    private javax.swing.JButton salvaralterar;
    // End of variables declaration//GEN-END:variables
}