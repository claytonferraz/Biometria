package com.clayton.telas.validar;

import java.io.*;
import java.beans.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.digitalpersona.onetouch.*;
import com.clayton.telas.Inicial.NovaTela;

public final class Painel extends JFrame {

    public static String TEMPLATE_PROPERTY = "template";
    private DPFPTemplate template;

    public class TemplateFileFilter extends javax.swing.filechooser.FileFilter {

        @Override
        public boolean accept(File f) {
            return f.getName().endsWith(".fpt");
        }

        @Override
        public String getDescription() {
            return "Arquivo de Impressão Digital (*.fpt)";
        }
    }

    public Painel() {
        setState(Frame.NORMAL);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Captura e Validação de Impressões Digitais");
        setResizable(false);

        final JButton load = new JButton("Carregar Impressão Digital");
        load.addActionListener((ActionEvent e) -> {
            onLoad();
        });

        final JButton verify = new JButton("Validar Impressão Digital");
        verify.addActionListener((ActionEvent e) -> {
            onVerify();
        });

        final JButton quit = new JButton("Fechar");
        quit.addActionListener((ActionEvent e) -> {
            
            NovaTela no = new NovaTela();
            no.setVisible(true);
            this.dispose();
        });

        this.addPropertyChangeListener(TEMPLATE_PROPERTY, (PropertyChangeEvent evt) -> {
            verify.setEnabled(template != null);

            if (evt.getNewValue() == evt.getOldValue()) {
                return;
            }
            if (template != null) {
                JOptionPane.showMessageDialog(Painel.this, "O modelo foi lido com sucesso.", "Digital ", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JPanel center = new JPanel();
        center.setLayout(new GridLayout(4, 1, 0, 5));
        center.setBorder(BorderFactory.createEmptyBorder(20, 20, 5, 20));

        center.add(load);
        center.add(verify);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        bottom.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        bottom.add(quit);

        setLayout(new BorderLayout());
        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.PAGE_END);

        pack();
        setSize((int) (getSize().width * 1.6), getSize().height);
        setLocationRelativeTo(null);
        setTemplate(null);
        setVisible(true);
    }

    private void onEnroll() {
        RegistrarDigital form = new RegistrarDigital(this);
        form.setVisible(true);
    }

    private void onVerify() {
        ValidarDigital form = new ValidarDigital(this);
        form.setVisible(true);
    }

    private void onLoad() {
        JFileChooser chooser = new JFileChooser();
        chooser.addChoosableFileFilter(new TemplateFileFilter());
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                byte[] data;
                try (FileInputStream stream = new FileInputStream(chooser.getSelectedFile())) {
                    data = new byte[stream.available()];
                    stream.read(data);
                }
                DPFPTemplate t = DPFPGlobal.getTemplateFactory().createTemplate();
                t.deserialize(data);
                setTemplate(t);
            } catch (IOException | IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "Digital Carregada", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public DPFPTemplate getTemplate() {
        return template;
    }

    public void setTemplate(DPFPTemplate template) {
        DPFPTemplate old = this.template;
        this.template = template;
        firePropertyChange(TEMPLATE_PROPERTY, old, template);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Painel();
        });
    }

}
