package com.clayton.telas.cadastrardigital;

import com.clayton.metodos.CapturaDigital;
import com.clayton.model.CadDigital;
import com.clayton.model.CadPessoa;
import com.clayton.telas.Inicial.NovaTela;
import com.clayton.telas.Inicial.cadastropessoa.TelaCadPessoaBanco;
import com.digitalpersona.onetouch.*;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.ui.swing.DPFPEnrollmentEvent;

import javax.swing.*;
import javax.swing.text.DefaultFormatter;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.util.*;

/**
 * Enrollment control test console
 */
public class CadastrodeDigital extends JFrame {

    @Override
    public void setDefaultCloseOperation(int operation) {
        super.setDefaultCloseOperation(operation);

    }

    private boolean cadastro = false;
    private boolean alterar;
    private EnumMap<DPFPFingerIndex, DPFPTemplate> templates;
    private final EnumMap<DPFPFingerIndex, JCheckBox> checkBoxes = new EnumMap<>(DPFPFingerIndex.class);
    private DPFPGlobal digital;
    private DPFPTemplate template = null;
    private DPFPEnrollmentEvent e;
    private static final DPFPTemplate fakeTemplate;
    private CadPessoa pe;
    private CadDigital cadpe;

    //private CapturaDigital me ;
    private SpinnerNumberModel maxCount = new SpinnerNumberModel(DPFPFingerIndex.values().length, 0, DPFPFingerIndex.values().length, 1);
    private final JSpinner maxCountSpinner;

    private final JButton enrollButton = new JButton("Capturar Impressão Digital");
    private final JButton verifyButton = new JButton(" Testar Leitura de Digitais");
    private final JButton salva = new JButton("Gravar Captura");
    private final JButton fechar = new JButton("Cancelar Captura");
    private SpinnerNumberModel farRequested
            = new SpinnerNumberModel(DPFPVerification.MEDIUM_SECURITY_FAR, 1, DPFPVerification.PROBABILITY_ONE, 100);
    private JSpinner farRequestedSpinner;
    private JTextField farAchieved;
    JCheckBox fingerMatched;

    public boolean isAlterar() {
        return alterar;
    }

    public void setAlterar(boolean alterar) {
        this.alterar = alterar;
    }

    public boolean isCadastro() {
        return cadastro;
    }

    public void setCadastro(boolean cadastro) {
        this.cadastro = cadastro;
    }

    public void setTemplates(EnumMap<DPFPFingerIndex, DPFPTemplate> templates) {
        this.templates = templates;
    }

    public DPFPGlobal getDigital() {
        return digital;
    }

    public void setDigital(DPFPGlobal digital) {
        this.digital = digital;
    }

    public DPFPEnrollmentEvent getE() {
        return e;
    }

    public void setE(DPFPEnrollmentEvent e) {
        this.e = e;
    }

    public SpinnerNumberModel getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(SpinnerNumberModel maxCount) {
        this.maxCount = maxCount;
    }

    public JSpinner getFarRequestedSpinner() {
        return farRequestedSpinner;
    }

    public void setFarRequestedSpinner(JSpinner farRequestedSpinner) {
        this.farRequestedSpinner = farRequestedSpinner;
    }

    public JCheckBox getFingerMatched() {
        return fingerMatched;
    }

    public void setFingerMatched(JCheckBox fingerMatched) {
        this.fingerMatched = fingerMatched;
    }

    public EnumMap<DPFPFingerIndex, DPFPTemplate> getTemplates() {
        return templates;
    }

    public CadPessoa getPe() {
        return pe;
    }

    public void setPe(CadPessoa pe) {
        this.pe = pe;
    }

    public CadDigital getCadpe() {
        return cadpe;
    }

    public void setCadpe(CadDigital cadpe) {
        this.cadpe = cadpe;
    }

    public static String TEMPLATE_PROPERTY = "template";

    static {
        fakeTemplate = DPFPGlobal.getTemplateFactory().createTemplate();
        try {
            fakeTemplate.deserialize(new byte[0]);
        } catch (IllegalArgumentException e) {
        }
    }

    public CadastrodeDigital() {
        super("Cadastro de Impressão Digital");

        this.cadastro = true;
        this.templates = new EnumMap<>(DPFPFingerIndex.class);

        setState(Frame.NORMAL);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        //// Enrollment Panel
        JPanel enrollmentConfigPanel = new JPanel();
        enrollmentConfigPanel.setBorder(BorderFactory.createTitledBorder(""));
        enrollmentConfigPanel.setLayout(new BoxLayout(enrollmentConfigPanel, BoxLayout.Y_AXIS));

        ///// Count
        maxCountSpinner = new JSpinner(maxCount);
        JSpinner.DefaultEditor maxEditor = (JSpinner.DefaultEditor) maxCountSpinner.getEditor();
        DefaultFormatter maxFormatter = (DefaultFormatter) (maxEditor.getTextField().getFormatter());
        maxFormatter.setAllowsInvalid(false);

        JPanel maxcountPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        maxcountPanel.add(new JLabel("Max. de registros de digitais"));
        maxcountPanel.add(maxCountSpinner);

        ///// Fingers
        JPanel fingersPanel = new JPanel(new GridBagLayout());
        fingersPanel.setBorder(BorderFactory.createTitledBorder("Você já registrou as digitais do : "));
        for (DPFPFingerIndex finger : DPFPFingerIndex.values()) {
            JCheckBox jCheckBox = new JCheckBox(Utilidades.fingerName(finger));
            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            final int rows = DPFPFingerIndex.values().length / 2;
            gridBagConstraints.gridx = finger.ordinal() / rows;
            gridBagConstraints.gridy = rows - 1 - Math.abs(rows - 1 - finger.ordinal()) + gridBagConstraints.gridx;
            gridBagConstraints.anchor = GridBagConstraints.WEST;
            fingersPanel.add(jCheckBox, gridBagConstraints);
            checkBoxes.put(finger, jCheckBox);

            final DPFPFingerIndex dummyFinger = finger;
            jCheckBox.addActionListener(new ActionListener() {
                DPFPFingerIndex index;

                {
                    index = dummyFinger;
                }

                @Override
                public void actionPerformed(ActionEvent e) {
                    JCheckBox cb = (JCheckBox) e.getSource();
                    if (cb.isSelected()) {
                        JOptionPane.showMessageDialog(CadastrodeDigital.this,
                                "Para deletar uma impressão digital, primeiro cadastre-a, clique no botão Registrar.", "Registro de Digitais",
                                JOptionPane.INFORMATION_MESSAGE);
                        cb.setSelected(false);
                        //templates.put(index, fakeTemplate);
                    } else {
                        if (JOptionPane.showConfirmDialog(CadastrodeDigital.this,
                                "Tem certeza que deseja deletar a digital do dedo  " + Utilidades.fingerprintName(index) + "?", " Registro de Digitais",
                                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                            templates.remove(index);
                        } else {
                            cb.setSelected(true);
                        }
                    }
                    UpdateUI();
                }
            });
        }

        final JCheckBox enforceFailure = new JCheckBox("Forçar cadastro quando ocorrer falhas na leitura");
        enforceFailure.setAlignmentX(CENTER_ALIGNMENT);
        enforceFailure.setHorizontalTextPosition(SwingConstants.LEADING);

        ///// Button de Adicionar
        enrollButton.addActionListener((ActionEvent e) -> {
            // aqui vou buscar do banco as digital e inserir-las
            new CapturarDigital(CadastrodeDigital.this,
                    maxCount.getNumber().intValue(),
                    enforceFailure.isSelected() ? "Just because I'm not in a mood." : null,
                    templates
            ).setVisible(true);

            UpdateUI();
        });
        enrollButton.setAlignmentX(CENTER_ALIGNMENT);

        //enrollmentConfigPanel.add(maxcountPanel);
        //enrollmentConfigPanel.add(enforceFailure);
        enrollmentConfigPanel.add(Box.createVerticalStrut(4));
        enrollmentConfigPanel.add(enrollButton);
        enrollmentConfigPanel.add(Box.createVerticalStrut(4));
        enrollmentConfigPanel.add(fingersPanel);
        //// Verification Panel
        JPanel verificationConfigPanel = new JPanel();
        verificationConfigPanel.setBorder(BorderFactory.createTitledBorder("Testar Captura de Impressões"));
        verificationConfigPanel.setLayout(new BoxLayout(verificationConfigPanel, BoxLayout.Y_AXIS));

        ///// False Accept Rate
        JPanel farPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBagConstraints;

        // FAR requested
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 4, 0, 0);
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        farPanel.add(new JLabel("False Accept Rate (FAR) requested: "), gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 0, 0, 4);
        gridBagConstraints.anchor = GridBagConstraints.WEST;

        farRequestedSpinner = new JSpinner(farRequested);
        farRequestedSpinner.setPreferredSize(new Dimension(100, 20));
        JSpinner.DefaultEditor farEditor = (JSpinner.DefaultEditor) farRequestedSpinner.getEditor();
        DefaultFormatter farFormatter = (DefaultFormatter) (farEditor.getTextField().getFormatter());
        farFormatter.setAllowsInvalid(false);

        farPanel.add(farRequestedSpinner, gridBagConstraints);

        // FAR achieved
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(0, 4, 0, 0);
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        farPanel.add(new JLabel("False Accept Rate (FAR) achieved: "), gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(0, 0, 0, 4);
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        farAchieved = new JTextField();
        farAchieved.setEditable(false);
        farAchieved.setPreferredSize(new Dimension(100, 20));
        farPanel.add(farAchieved, gridBagConstraints);

        fingerMatched = new JCheckBox("Fingerprint matched");
        fingerMatched.setEnabled(false);
        fingerMatched.setAlignmentX(CENTER_ALIGNMENT);
        fingerMatched.setHorizontalTextPosition(SwingConstants.LEADING);

        ///// Button
        verifyButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ValidarDigital dlg = new ValidarDigital(CadastrodeDigital.this, templates, farRequested.getNumber().intValue());
                    dlg.addPropertyChangeListener((final PropertyChangeEvent e1) -> {
                        String name1 = e1.getPropertyName();
                        if (null != name1) {
                            switch (name1) {
                                case ValidarDigital.FAR_PROPERTY:
                                    farAchieved.setText("" + (Integer) e1.getNewValue());
                                    break;
                                case ValidarDigital.MATCHED_PROPERTY:
                                    fingerMatched.setSelected((Boolean) e1.getNewValue());
                                    break;
                            }
                        }
                    });
                    dlg.setVisible(true);
                    dlg.setDefaultCloseOperation(HIDE_ON_CLOSE);
                } catch (Exception ex) {
                    farRequestedSpinner.requestFocusInWindow();
                }
            }
        });
        verifyButton.setAlignmentX(CENTER_ALIGNMENT);

        verificationConfigPanel.add(farPanel);
        verificationConfigPanel.add(fingerMatched);
        verificationConfigPanel.add(Box.createVerticalStrut(4));
        verificationConfigPanel.add(verifyButton);
        verificationConfigPanel.add(Box.createVerticalStrut(4));
        //verificationConfigPanel.add(salva);

        JPanel salvarpainel = new JPanel();
        salvarpainel.setBorder(BorderFactory.createTitledBorder("Salvar Impressões Digitais"));
        //verificationConfigPanel.setLayout(new BoxLayout(verificationConfigPanel, BoxLayout.Y_AXIS));
        salva.setAlignmentX(CENTER_ALIGNMENT);
        fechar.setBackground(Color.red);
        fechar.setFont(Font.getFont(TEMPLATE_PROPERTY));
        salvarpainel.add(salva);

        salvarpainel.add(fechar);

        fechar.addActionListener((ActionEvent e) -> {
            onClose();
        });
        salva.addActionListener((ActionEvent e) -> {
            onSave();
        });

        //// Main frame
        JPanel dummy = new JPanel();
        dummy.setLayout(new BoxLayout(dummy, BoxLayout.Y_AXIS));
        dummy.add(Box.createVerticalStrut(4));
        dummy.add(enrollmentConfigPanel);
        dummy.add(verificationConfigPanel);
        dummy.add(Box.createVerticalStrut(4));
        dummy.add(salvarpainel);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        add(Box.createHorizontalStrut(4));
        add(dummy);
        add(Box.createHorizontalStrut(4));

        pack();
        setLocationRelativeTo(null);
        UpdateUI();
        setVisible(true);

        enrollButton.requestFocusInWindow();
    }

    private void UpdateUI() {
        // update enrolled fingers checkboxes
        setCadastro(true);
        for (DPFPFingerIndex finger : DPFPFingerIndex.values()) {
            checkBoxes.get(finger).setSelected(templates.containsKey(finger));
        }

        // update verification button
        verifyButton.setEnabled(!templates.isEmpty());
        salva.setEnabled(!templates.isEmpty());

    }

    public void UpdateUIP() {

        for (DPFPFingerIndex finger : DPFPFingerIndex.values()) {
            checkBoxes.get(finger).setSelected(templates.containsKey(finger));
        }

        // update verification button
        verifyButton.setEnabled(!templates.isEmpty());
        salva.setEnabled(!templates.isEmpty());
        setCadastro(true);
    }

    private void onSave() {
        TelaCadPessoaBanco no = new TelaCadPessoaBanco(pe, isAlterar(), cadpe);
        System.out.println(isAlterar());
        CapturaDigital me = new CapturaDigital(templates, template);
        if (isAlterar() == true) {
            no.setAlterar(true);
            //System.out.println(isAlterar() + "++ Captura");
        } else {
            no.setAlterar(false);
            //System.out.println(isAlterar() + "-- Captura");
        }
        no.setMe(me);
        no.setVisible(true);
        this.dispose();
    }

    private void onClose() {
        NovaTela no = new NovaTela();
        //CapturaDigital me = new CapturaDigital(templates, template, e);
        no.setVisible(true);

        this.dispose();
    }

    public DPFPTemplate getTemplate() {
        return template;
    }

    public void setTemplate(DPFPTemplate template) {
        DPFPTemplate old = this.template;
        this.template = template;
        firePropertyChange(TEMPLATE_PROPERTY, old, template);
    }

    public static void main(String... args) {

        SwingUtilities.invokeLater(() -> {
            //setDefaultCloseOperation( int.classoperation);
            new CadastrodeDigital();
        });
    }

}
