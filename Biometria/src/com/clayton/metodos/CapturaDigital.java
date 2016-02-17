/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clayton.metodos;

import com.clayton.model.CadDigital;
import com.digitalpersona.onetouch.DPFPFingerIndex;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPTemplate;
import java.util.EnumMap;

public class CapturaDigital {

    private DPFPTemplate template = null;

    private EnumMap<DPFPFingerIndex, DPFPTemplate> templates = null;

    public CapturaDigital(EnumMap<DPFPFingerIndex, DPFPTemplate> templates, DPFPTemplate template) {
        this.templates = templates;
        this.template = template;

    }

    public CapturaDigital() {
    }

    public EnumMap<DPFPFingerIndex, DPFPTemplate> getTemplates() {
        return templates;
    }

    public void setTemplates(EnumMap<DPFPFingerIndex, DPFPTemplate> templates) {
        this.templates = templates;
    }

    public DPFPTemplate getTemplate() {
        return template;
    }

    public void setTemplate(DPFPTemplate template) {
        DPFPTemplate old = this.template;
        this.template = template;

    }
    
    

    /**
     * @param pe
     * <b> Recebe uma pessoa com as diditais serializada do banco </b>
     * @return EnumMap com todos as digitais no map inserido
     */
    public EnumMap<DPFPFingerIndex, DPFPTemplate> Injetar_Digitais_Pessoa(CadDigital pe) {

        // AQUI CRIA AS 10 TEMPLATES, UMA PARA CADA DEDO DA PESSOA
        // MÃO DIREITA
        DPFPTemplate poldir = DPFPGlobal.getTemplateFactory().createTemplate();
        DPFPTemplate inddir = DPFPGlobal.getTemplateFactory().createTemplate();
        DPFPTemplate meddir = DPFPGlobal.getTemplateFactory().createTemplate();
        DPFPTemplate anedir = DPFPGlobal.getTemplateFactory().createTemplate();
        DPFPTemplate mindir = DPFPGlobal.getTemplateFactory().createTemplate();

        // MÃO ESQUERDA
        DPFPTemplate polesq = DPFPGlobal.getTemplateFactory().createTemplate();
        DPFPTemplate indesq = DPFPGlobal.getTemplateFactory().createTemplate();
        DPFPTemplate medesq = DPFPGlobal.getTemplateFactory().createTemplate();
        DPFPTemplate aneesq = DPFPGlobal.getTemplateFactory().createTemplate();
        DPFPTemplate minesq = DPFPGlobal.getTemplateFactory().createTemplate();

        // CRIA UM  NOVO ENUMMAP, PARA RECEBER EM CADA DEDO 
        EnumMap<DPFPFingerIndex, DPFPTemplate> digitalpessoa = new EnumMap<>(DPFPFingerIndex.class);

        // AQUI VERIFICA SE HÁ DIGITAL PARA ESSE DEDO, SE HOUVER INSERI NO TEMPLANTE
        // MÃO DIREITA
        if (pe.getPolegardir() != null) {
            poldir.deserialize(pe.getPolegardir());
            digitalpessoa.put(DPFPFingerIndex.RIGHT_THUMB, poldir);
        }
        if (pe.getIndicadordir() != null) {
            inddir.deserialize(pe.getIndicadordir());
            digitalpessoa.put(DPFPFingerIndex.RIGHT_INDEX, inddir);
        }
        if (pe.getMediodir() != null) {
            meddir.deserialize(pe.getMediodir());
            digitalpessoa.put(DPFPFingerIndex.RIGHT_MIDDLE, meddir);
        }
        if (pe.getAnelardir() != null) {
            anedir.deserialize(pe.getAnelardir());
            digitalpessoa.put(DPFPFingerIndex.RIGHT_RING, anedir);
        }
        if (pe.getMinidir() != null) {
            mindir.deserialize(pe.getMinidir());
            digitalpessoa.put(DPFPFingerIndex.RIGHT_PINKY, mindir);
        }

        //MÃO ESQUERDA
        if (pe.getPolegaresq() != null) {
            polesq.deserialize(pe.getPolegaresq());
            digitalpessoa.put(DPFPFingerIndex.LEFT_THUMB, polesq);
        }
        if (pe.getIndicadoresq() != null) {
            indesq.deserialize(pe.getIndicadoresq());
            digitalpessoa.put(DPFPFingerIndex.LEFT_INDEX, indesq);
        }
        if (pe.getMedioesq() != null) {
            medesq.deserialize(pe.getMedioesq());
            digitalpessoa.put(DPFPFingerIndex.LEFT_MIDDLE, medesq);
        }
        if (pe.getAnelaresq() != null) {
            aneesq.deserialize(pe.getAnelaresq());
            digitalpessoa.put(DPFPFingerIndex.LEFT_RING, aneesq);
        }
        if (pe.getMiniesq() != null) {
            minesq.deserialize(pe.getMiniesq());
            digitalpessoa.put(DPFPFingerIndex.LEFT_PINKY, minesq);
        }

        try {
            setTemplates(digitalpessoa);
            return getTemplates();

        } catch (Exception e) {
            return getTemplates();
        }

    }

    // METODO CRIADO QUE OBTEM O TEMPLANTE DE CADA DEDO
    public byte[] Extrair_polegarDireito() {
        byte[] b = null;
        try {
            b = getTemplates().put(DPFPFingerIndex.RIGHT_THUMB, template).serialize();

            return b;
        } catch (Exception e) {
            return b;
        }

    }

    public byte[] Extrair_indicadorDireito() {
        byte[] b = null;
        try {
            b = getTemplates().put(DPFPFingerIndex.RIGHT_INDEX, template).serialize();

            return b;
        } catch (Exception e) {
            return b;
        }
    }

    public byte[] Extrair_medioDireito() {
        byte[] b = null;
        try {
            b = getTemplates().put(DPFPFingerIndex.RIGHT_MIDDLE, template).serialize();

            return b;
        } catch (Exception e) {
            return b;
        }
    }

    public byte[] Extrair_anelarDireito() {
        byte[] b = null;
        try {
            b = getTemplates().put(DPFPFingerIndex.RIGHT_RING, template).serialize();

            return b;
        } catch (Exception e) {
            return b;
        }
    }

    public byte[] Extrair_minimoDireito() {
        byte[] b = null;
        try {
            b = getTemplates().put(DPFPFingerIndex.RIGHT_PINKY, template).serialize();
            return b;
        } catch (Exception e) {
            return b;
        }
    }

    public byte[] Extrair_polegarEsquerdo() {
        byte[] b = null;
        try {
            b = getTemplates().put(DPFPFingerIndex.LEFT_THUMB, template).serialize();
            return b;
        } catch (Exception e) {
            return b;
        }
    }

    public byte[] Extrair_indicadorEsquerdo() {
        byte[] b = null;
        try {
            b = getTemplates().put(DPFPFingerIndex.LEFT_INDEX, template).serialize();
            return b;
        } catch (Exception e) {
            return b;
        }
    }

    public byte[] Extrair_medioEsquerdo() {
        byte[] b = null;
        try {
            b = getTemplates().put(DPFPFingerIndex.LEFT_MIDDLE, template).serialize();
            return b;
        } catch (Exception e) {
            return b;
        }
    }

    public byte[] Extrair_anelarEsquerdo() {
        byte[] b = null;
        try {
            b = getTemplates().put(DPFPFingerIndex.LEFT_RING, template).serialize();
            return b;
        } catch (Exception e) {
            return b;
        }

    }

    public byte[] Extrair_minimoEsquerdo() {
        byte[] b = null;
        try {
            b = getTemplates().put(DPFPFingerIndex.LEFT_PINKY, template).serialize();
            return b;
        } catch (Exception e) {
            return b;
        }
    }

}
