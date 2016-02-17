package com.clayton.telas.cadastrardigital;

import com.digitalpersona.onetouch.*;
import com.digitalpersona.onetouch.verification.*;
import com.digitalpersona.onetouch.ui.swing.DPFPVerificationControl;
import com.digitalpersona.onetouch.ui.swing.DPFPVerificationEvent;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * Enrollment control test
 */
public final class ValidarDigital
	extends JDialog
{
    private EnumMap<DPFPFingerIndex, DPFPTemplate> templates;
    private int farRequested;
    private int farAchieved;
    private final DPFPVerificationControl verificationControl;
    private boolean matched;
    
    static final String FAR_PROPERTY = "FAR";
    static final String MATCHED_PROPERTY = "Matched";

    public ValidarDigital(Frame owner, EnumMap<DPFPFingerIndex, DPFPTemplate> templates, int farRequested) {
		super(owner, true);
		this.templates = templates;
		this.farRequested = farRequested;

		setTitle("Validar Impressão Digital");
    	setResizable(false);    	

		verificationControl = new DPFPVerificationControl();
		verificationControl.addVerificationListener((DPFPVerificationEvent e) -> {
                    final DPFPVerification verification =
                            DPFPGlobal.getVerificationFactory().createVerification(ValidarDigital.this.farRequested);
                    e.setStopCapture(false);	// we want to continue capture until the dialog is closed
                    int bestFAR = DPFPVerification.PROBABILITY_ONE;
                    boolean hasMatch = false;
                    for (DPFPTemplate template : ValidarDigital.this.templates.values()) {
                        final DPFPVerificationResult result = verification.verify(e.getFeatureSet(), template);
                        e.setMatched(result.isVerified());		// report matching status
                        bestFAR = Math.min(bestFAR, result.getFalseAcceptRate());
                        if (e.getMatched()) {
                            hasMatch = true;
                            break;
                        }
                    }
                    setMatched(hasMatch);
                    setFAR(bestFAR);
                });

		getContentPane().setLayout(new BorderLayout());

		JButton closeButton = new JButton("Fechar");
		closeButton.addActionListener((ActionEvent e) -> {
                    setVisible(false); 		//End Dialog
                });

		JPanel center = new JPanel();
		center.add(verificationControl);
		center.add(new JLabel("Para Confirmar sua Identidade, coloque o dedo sobre o leitor, e aguarde confirmação."));

		JPanel bottom = new JPanel();
		bottom.add(closeButton);

		add(center, BorderLayout.CENTER);
		add(bottom, BorderLayout.PAGE_END);

		pack();
        setLocationRelativeTo(null);         
    }

    public int getFAR() {
    	return farAchieved;
    }
    
    protected void setFAR(int far) {
		final int old = getFAR();
		farAchieved = far;
		firePropertyChange(FAR_PROPERTY, old, getFAR());
    }

    public boolean getMatched() {
    	return matched;
    }
    
    protected void setMatched(boolean matched) {
		final boolean old = getMatched();
		this.matched = matched;
   		firePropertyChange(MATCHED_PROPERTY, old, getMatched());
     }
    
    /**
     * Shows or hides this component depending on the value of parameter
     * <code>b</code>.
     *
     * @param b if <code>true</code>, shows this component;
     *          otherwise, hides this component
     * @see #isVisible
     * @since JDK1.1
     */
    @Override
    public void setVisible(boolean b) {
        if (b) {
            matched = false;
            verificationControl.start();
        } else {
            if (!matched)
                verificationControl.stop();
        }
        super.setVisible(b);
    }
}