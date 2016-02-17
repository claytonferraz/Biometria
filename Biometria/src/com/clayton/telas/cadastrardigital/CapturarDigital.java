package com.clayton.telas.cadastrardigital;

import com.digitalpersona.onetouch.*;
import com.digitalpersona.onetouch.ui.swing.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * Enrollment control test
 */
public class CapturarDigital extends JDialog {

    private EnumMap<DPFPFingerIndex, DPFPTemplate> templates;

    public CapturarDigital(Frame owner, int maxCount, final String reasonToFail, EnumMap<DPFPFingerIndex, DPFPTemplate> templates) {
        super(owner, true);
        this.templates = templates;

        setTitle("Registro de Impressão Digital");

        DPFPEnrollmentControl enrollmentControl = new DPFPEnrollmentControl();

        EnumSet<DPFPFingerIndex> fingers = EnumSet.noneOf(DPFPFingerIndex.class);
        fingers.addAll(templates.keySet());
        enrollmentControl.setEnrolledFingers(fingers);
        enrollmentControl.setMaxEnrollFingerCount(maxCount);

        enrollmentControl.addEnrollmentListener(new DPFPEnrollmentListener() {
            @Override
            public void fingerDeleted(DPFPEnrollmentEvent e) throws DPFPEnrollmentVetoException {
                if (reasonToFail != null) {
                    throw new DPFPEnrollmentVetoException(reasonToFail);
                } else {
                    CapturarDigital.this.templates.remove(e.getFingerIndex());
                }
            }

            @Override
            public void fingerEnrolled(DPFPEnrollmentEvent e) throws DPFPEnrollmentVetoException {
                if (reasonToFail != null) {               
                    throw new DPFPEnrollmentVetoException(reasonToFail);
                } else {
                    CapturarDigital.this.templates.put(e.getFingerIndex(), e.getTemplate());
                }
            }
        });

        getContentPane().setLayout(new BorderLayout());

        JButton closeButton = new JButton("Fechar");
        closeButton.addActionListener((ActionEvent e) -> {
            //testar aqui se salva no banco
            
            setVisible(false);               
        });

        JPanel bottom = new JPanel();
        bottom.add(closeButton);
        add(enrollmentControl, BorderLayout.CENTER);
        add(bottom, BorderLayout.PAGE_END);

        pack();
        setLocationRelativeTo(null);
    }
}
