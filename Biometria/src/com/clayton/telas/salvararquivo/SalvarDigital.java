package com.clayton.telas.salvararquivo;

import java.io.*;
import java.beans.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.digitalpersona.onetouch.*;
import java.util.EnumMap;
import com.clayton.metodos.CapturaDigital;
import com.clayton.telas.Inicial.NovaTela;
import java.util.Arrays;

public class SalvarDigital extends JFrame {
    
    public CapturaDigital me; 

    private EnumMap<DPFPFingerIndex, DPFPTemplate> templates;
    public static String TEMPLATE_PROPERTY = "template";
    private DPFPTemplate template;

    public CapturaDigital getMe() {
        return me;
    }

    public void setMe(CapturaDigital me) {
        this.me = me;
    }
    
    

    public EnumMap<DPFPFingerIndex, DPFPTemplate> getTemplates() {
        return templates;
    }

    public void setTemplates(EnumMap<DPFPFingerIndex, DPFPTemplate> templates) {
        this.templates = templates;
    }
    
    
    

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

    public SalvarDigital() {
        
        //setTemplates(templates);
        
        setState(Frame.NORMAL);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Captura e Validação de Impressões Digitais");
        setResizable(false);

        final JButton enroll = new JButton("Registrar Impressão Digital");
        enroll.addActionListener((ActionEvent e) -> {
            onEnroll();
        });

        final JButton verify = new JButton("Validar Impressão Digital");
        verify.addActionListener((ActionEvent e) -> {
            onVerify();
        });

        final JButton save = new JButton("Salvar Captura de Impressão Digital");
        save.addActionListener((ActionEvent e) -> {
            onSave();
        });

        final JButton load = new JButton("Carregar Impressão Digital");
        load.addActionListener((ActionEvent e) -> {
            //onLoad();
        });

        final JButton quit = new JButton("Fechar");
        quit.addActionListener((ActionEvent e) -> {
            NovaTela no = new NovaTela();
            no.setVisible(true);
            this.dispose();
        });

        this.addPropertyChangeListener(TEMPLATE_PROPERTY, (PropertyChangeEvent evt) -> {
            //verify.setEnabled(template != null);
            //save.setEnabled(templates != null);
            if (evt.getNewValue() == evt.getOldValue()) {
                return;
            }
            if (template != null) {
                JOptionPane.showMessageDialog(SalvarDigital.this, "Modelo lido com sucesso.", "Fingerprint Enrollment", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JPanel center = new JPanel();
        center.setLayout(new GridLayout(4, 1, 0, 5));
        center.setBorder(BorderFactory.createEmptyBorder(20, 20, 5, 20));
		//center.add(enroll);
        //center.add(verify);
        center.add(save);
		//center.add(load);

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
        EnrollmentForm form = new EnrollmentForm(this);
        form.setVisible(true);
    }

    private void onVerify() {
        VerificationForm form = new VerificationForm(this);
        form.setVisible(true);
    }

    private void onSave() {
        JFileChooser chooser = new JFileChooser();
        chooser.addChoosableFileFilter(new com.clayton.telas.cadastrardigital.TemplateFileFilter());
        while (true) {
            if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = chooser.getSelectedFile();
                    if (!file.toString().toLowerCase().endsWith(".fpt")) {
                        file = new File(file.toString() + "indicador.fpt");
                    }
                    if (file.exists()) {
                        int choice = JOptionPane.showConfirmDialog(this,
                                String.format("Arquivo \"%1$s\" já existe.\nDeseja gravar mesmo assim?", file.toString()),
                                "Digital Salva",
                                JOptionPane.YES_NO_CANCEL_OPTION);
                        if (choice == JOptionPane.NO_OPTION) {
                            continue;
                        } else if (choice == JOptionPane.CANCEL_OPTION) {
                            break;
                        }
                    }
                    try (FileOutputStream stream = new FileOutputStream(file)) {
                        setTemplate(me.getTemplates().put(DPFPFingerIndex.RIGHT_INDEX, template));
                        //setTemplate(me.getTemplates().put(DPFPFingerIndex.RIGHT_MIDDLE, template));
                        System.out.println(Arrays.toString(getTemplate().serialize()));
                        stream.write(getTemplate().serialize());
                        JOptionPane.showMessageDialog(rootPane, "Arquivo criado com sucesso");
                    }
                } catch (HeadlessException | IOException ex) {
                    JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "Impressão Digital salva com SUCESSO", JOptionPane.ERROR_MESSAGE);
                }
            }
            break;
        }
    }

    
    public DPFPTemplate getTemplate() {
        return template;
    }

    public void setTemplate(DPFPTemplate template) {
        //DPFPTemplate old = this.template;
        this.template = template;
        //firePropertyChange(TEMPLATE_PROPERTY, old, template);
    }

    /**
     * @param args the command line arguments
     */
    

}
