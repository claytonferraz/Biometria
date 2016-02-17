/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clayton.metodos;

import com.clayton.controle.Sistema;
import com.clayton.model.CadDigital;
import com.clayton.model.CadPessoa;
import java.util.List;

/**
 * @author clayton
 */
public class DigitalPersistencia {

    private CapturaDigital capturadigital = null;
    private CadDigital caddigital = null;
    private CadPessoa login = null;
    private List<CadDigital> lista;

    public DigitalPersistencia() {
    }

    public DigitalPersistencia(CapturaDigital digital, CadPessoa login) {
        this.capturadigital = digital;
        this.login = login;
    }

    public DigitalPersistencia(CapturaDigital digital, CadDigital caddigital, CadPessoa login) {
        this.capturadigital = digital;
        this.caddigital = caddigital;
        this.login = login;
    }

    public DigitalPersistencia(CapturaDigital digital, CadDigital caddigital) {
        this.capturadigital = digital;
        this.caddigital = caddigital;
    }

    public List<CadDigital> getLista() {
        return lista;
    }

    public void setLista(List<CadDigital> lista) {
        this.lista = lista;
    }

    public CapturaDigital getCapturadigital() {
        return capturadigital;
    }

    public void setCapturadigital(CapturaDigital capturadigital) {
        this.capturadigital = capturadigital;
    }

    public CadDigital getCaddigital() {
        return caddigital;
    }

    public void setCaddigital(CadDigital caddigital) {
        this.caddigital = caddigital;
    }

    public CadPessoa getLogin() {
        return login;
    }

    public void setLogin(CadPessoa login) {
        this.login = login;
    }

    // teste se obtem do banco
    public CadPessoa BuscaPessoa(Integer id) {

        Sistema s = new Sistema();

        CadPessoa lo;
        lo = (CadPessoa) s.ProcurarLogin(id);
        return lo;

    }

    public void BuscaDigitalPessoa(Integer id) {

        Sistema s = new Sistema();
        CadDigital po = new CadDigital();
        po = (CadDigital) s.ProcurarDigitalPessoa(id);

    }

    public CadDigital BuscaDigitalALL(Integer id) {

        Sistema s = new Sistema();
        CadDigital po = new CadDigital();
        po = (CadDigital) s.ProcurarDigitais(id);

        return po;

    }

    // OK, grava no banco.
    public void ExtrairDigitais() {

        caddigital.setAnelaresq(capturadigital.Extrair_anelarEsquerdo());
        caddigital.setAnelardir(capturadigital.Extrair_anelarDireito());
        caddigital.setIndicadordir(capturadigital.Extrair_indicadorDireito());
        caddigital.setIndicadoresq(capturadigital.Extrair_indicadorEsquerdo());
        caddigital.setMediodir(capturadigital.Extrair_medioDireito());
        caddigital.setMedioesq(capturadigital.Extrair_medioEsquerdo());
        caddigital.setMinidir(capturadigital.Extrair_minimoDireito());
        caddigital.setMiniesq(capturadigital.Extrair_minimoEsquerdo());
        caddigital.setPolegardir(capturadigital.Extrair_polegarDireito());
        caddigital.setPolegaresq(capturadigital.Extrair_polegarEsquerdo());
        caddigital.setAtivo(true);

    }

    public void InjetarDigitais(CadDigital pe) {

        try {
            capturadigital.Injetar_Digitais_Pessoa(caddigital);
        } catch (Exception e) {
            System.out.println("Erro");
        }

    }

    public String CadastraLoginTeste() {

        try {
            Sistema si = new Sistema();
            si.cadastraLogin(getLogin());
            return "A pessoa com nome = " + getLogin().getLogin() + " foi cadastrada";
        } catch (Exception e) {
            return "A pessoa com nome = " + getLogin().getLogin() + " NÃO foi cadastrada";
        }

    }

    public String CadastraDigitalTeste() {

        try {
            Sistema si = new Sistema();
            //caddigital.setIdpessoa(getLogin());
            si.cadastraDigitais(getCaddigital());
            return "As Digitais de " + getLogin().getLogin() + " foram cadastradas";
        } catch (Exception e) {
            return "As Digitais de " + getLogin().getLogin() + " NÃO foram cadastradas";
        }

    }

    public String AlteraDigitalTeste() {

        try {
            Sistema si = new Sistema();
            si.alterarDigitais(getCaddigital());
            return "As Digitais de " + getLogin().getLogin() + " foram Alteradas";
        } catch (Exception e) {
            return "As Digitais de " + getLogin().getLogin() + " NÃO foram Alteradas";
        }

    }

    public String AlteraLoginTeste() {

        try {
            Sistema si = new Sistema();
            ExtrairDigitais();
            si.alterarDigitais(caddigital);
            si.alterarLogin(login);
            return "A pessoa com nome = " + getLogin() + " foi alterada";
        } catch (Exception e) {
            return "A pessoa com nome = " + getLogin() + " NÃO foi Alterada";
        }

    }

}
