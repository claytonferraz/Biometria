package com.clayton.telas.salvararquivo;

import com.digitalpersona.onetouch.*;
import com.digitalpersona.onetouch.processing.*;
import java.awt.*;
import javax.swing.JOptionPane;

public class EnrollmentForm extends CaptureForm
{
	private DPFPEnrollment enroller = DPFPGlobal.getEnrollmentFactory().createEnrollment();
	
	EnrollmentForm(Frame owner) {
		super(owner);
	}
	
	@Override protected void init()
	{
		super.init();
		this.setTitle("Registro de Impressões Digitais");
		updateStatus();
	}

	@Override protected void process(DPFPSample sample) {
		super.process(sample);
		// Process the sample and create a feature set for the enrollment purpose.
		DPFPFeatureSet features = extractFeatures(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);

		// Check quality of the sample and add to enroller if it's good
		if (features != null) try
		{
			makeReport("Registro de Impressão digital criado.");
			enroller.addFeatures(features);		// Add feature set to template.
		}
		catch (DPFPImageQualityException ex) { }
		finally {
			updateStatus();

			// Check if template has been created.
			switch(enroller.getTemplateStatus())
			{
				case TEMPLATE_STATUS_READY:	// report success and stop capturing
					stop();
					((SalvarDigital)getOwner()).setTemplate(enroller.getTemplate());
					setPrompt("Clique em Fechar, e clique em Verificar Impressão Digital.");
					break;

				case TEMPLATE_STATUS_FAILED:	// report failure and restart capturing
					enroller.clear();
					stop();
					updateStatus();
					((SalvarDigital)getOwner()).setTemplate(null);
					JOptionPane.showMessageDialog(EnrollmentForm.this, " O modelo de Impressão Digital não é valido. Registre novamente a impressão digital.", "Registro de Impressão Digital", JOptionPane.ERROR_MESSAGE);
					start();
					break;
			}
		}
	}
	
	private void updateStatus()
	{
		// Show number of samples needed.
		setStatus(String.format("E necessário um modelo de Impressão Digital: %1$s", enroller.getFeaturesNeeded()));
	}
	
}
