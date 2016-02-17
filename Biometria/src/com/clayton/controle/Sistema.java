/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clayton.controle;

import com.clayton.dao.DAO;
import com.clayton.model.CadDigital;
import com.clayton.model.CadPessoa;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author clayton
 */
public class Sistema {

    private final DAO dao;

    public Sistema() {
        dao = new DAO();
    }

   
    
    public void cadastraDigitais (CadDigital digitais){
        dao.inserir(digitais);
    }
    
   public void alterarDigitais (CadDigital digitais){
        dao.alterar(digitais);
    }
   
    public Object ProcurarDigitais(Integer id){
        return dao.procurar(CadDigital.class, id);
    }
    public List<CadDigital>  ProcurarDigitalPessoa(Integer id){
        return (List<CadDigital>) dao.listardigitaisPessoa(CadDigital.class, id);
    }
    
    public List<CadDigital> getDigital() {
        return (List<CadDigital>) dao.listar(CadDigital.class);
    }

    public Vector getIdDigital() {
        Vector vetor = new Vector();
        List<CadDigital> lista = this.getListaDigital();
        for (CadDigital f : lista) {
            vetor.add(f.getId());
        }
        return vetor;
    }

    public Vector getListaDigital() {
        Vector vetor = new Vector();
        List<CadDigital> lista = this.getDigital();
        this.getDigital().stream().forEach((digital) -> {
            vetor.add(digital);
        });
        return vetor;
    }

    public void excluirDigital(Integer id) {
        dao.excluir(CadDigital.class, id);
    }
    
    // Login
    
    public void cadastraLogin (CadPessoa login){
        dao.inserir(login);
    }
    
   public void alterarLogin (CadPessoa login){
        dao.alterar(login);
    }
   
    public Object ProcurarLogin(Integer id){
        return dao.procurar(CadPessoa.class, id);
    }
    
    public List<CadPessoa> getLogin() {
        return (List<CadPessoa>) dao.listar(CadPessoa.class);
    }
    
    
    
    public Vector getIDLogin() {
        Vector vetor = new Vector();
        List<CadPessoa> lista = this.getListaLogin();
        for (CadPessoa f : lista) {
            vetor.add(f.getIduser());
        }
        return vetor;
    }

    public Vector getListaLogin() {
        Vector vetor = new Vector();
        List<CadPessoa> lista = this.getLogin();
        this.getLogin().stream().forEach((login) -> {
            vetor.add(login);
        });
        return vetor;
    }

    public void excluirLogin(Integer id) {
        dao.excluir(CadPessoa.class, id);
    }

}
