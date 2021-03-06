package com.clayton.telas.validar;

import com.digitalpersona.onetouch.*;
import com.digitalpersona.onetouch.verification.*;
import java.awt.*;

public class ValidarDigital extends CapituraDigital
{
	private final DPFPVerification verificator = DPFPGlobal.getVerificationFactory().createVerification();
	
	ValidarDigital(Frame owner) {
		super(owner);
	}
	
	@Override protected void init()
	{
		super.init();
		this.setTitle("Validar Identidade");
		updateStatus(0);
	}

	@Override protected void process(DPFPSample sample) {
		super.process(sample);

		// Process the sample and create a feature set for the enrollment purpose.
		DPFPFeatureSet features = extractFeatures(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);

		// Check quality of the sample and start verification if it's good
		if (features != null)
		{
			// Compare the feature set with our template
			DPFPVerificationResult result = 
				verificator.verify(features, ((Painel)getOwner()).getTemplate());
			updateStatus(result.getFalseAcceptRate());
			if (result.isVerified())
				makeReport("As Digitais ===== CONFEREM =====.");
			else
				makeReport("As Digitais ===== NÃO CONFEREM =====.");
		}
	}
	
	private void updateStatus(int FAR)
	{
		// Show "False accept rate" value
		setStatus(String.format("False Accept Rate (FAR) = %1$s", FAR));
	}

}
