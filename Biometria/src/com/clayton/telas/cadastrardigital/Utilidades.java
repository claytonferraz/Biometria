package com.clayton.telas.cadastrardigital;

import java.util.EnumMap;

import com.digitalpersona.onetouch.DPFPFingerIndex;

class Utilidades {

    private static final EnumMap<DPFPFingerIndex, String> fingerNames;
    static {
    	fingerNames = new EnumMap<>(DPFPFingerIndex.class);
    	fingerNames.put(DPFPFingerIndex.LEFT_PINKY,   "Minimo Esquerdo");
    	fingerNames.put(DPFPFingerIndex.LEFT_RING,    "Anelar Esquerdo");
    	fingerNames.put(DPFPFingerIndex.LEFT_MIDDLE,  "Medio Esquerdo");
    	fingerNames.put(DPFPFingerIndex.LEFT_INDEX,   "Indicador Esquerdo");
    	fingerNames.put(DPFPFingerIndex.LEFT_THUMB,   "Polegar Esquerdo");
    	
    	fingerNames.put(DPFPFingerIndex.RIGHT_PINKY,  "Minimo Direito");
    	fingerNames.put(DPFPFingerIndex.RIGHT_RING,   "Anelar Direito");
    	fingerNames.put(DPFPFingerIndex.RIGHT_MIDDLE, "Medio Direito");
    	fingerNames.put(DPFPFingerIndex.RIGHT_INDEX,  "Indicador Direito");
    	fingerNames.put(DPFPFingerIndex.RIGHT_THUMB,  "Polegar Direito");
    }

    public static String fingerName(DPFPFingerIndex finger) {
    	return fingerNames.get(finger); 
    }
    public static String fingerprintName(DPFPFingerIndex finger) {
    	return fingerNames.get(finger) + " "; 
    }
    
}
