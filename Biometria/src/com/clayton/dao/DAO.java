/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clayton.dao;

/**
 *
 * @author clayton
 */
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class DAO {

    private EntityManagerFactory emf = null;
    private EntityManager em = null;
    private EntityTransaction tx = null;
    private String UP = null;

    public DAO(){
        this.UP=("LeitorBiometricoEleicoesPU");
        emf= Persistence.createEntityManagerFactory(this.UP);

    }
    // METODO QUE INSERI NO BANCO DE DADOS
    public void inserir (Object obj){
        // AQUI em RECEBE A CONEXAO
        em = emf.createEntityManager();
        // RECEBE A TRANSACAO
        tx = em.getTransaction();
        // AQUI INICIA
        tx.begin();
        // AQUI INSERI
        em.persist(obj);
        // AQUI GRAVA NO BANCO PRA SEMPRE, OU ATE EXCLUIR
        tx.commit();
        // AQUI ENCERRA A CONEXAO
        em.close();

    }
    // METODO PARA ALTERAR O OBJETO NO BANCO DE DADOS
    public void alterar(Object obj){
        em = emf.createEntityManager();
        tx= em.getTransaction();
        tx.begin();
        em.merge(obj);
        tx.commit();
        em.close();
   }// METODO PARA EXCLUIR UM OBJETO NO BANCO DE DADOS
   public void excluir(Class objeto,Integer cod){
       Object object = null;
       this.em = emf.createEntityManager();
       this.tx = em.getTransaction();
       this.tx.begin();
       object = em.getReference(objeto, cod);
       this.em.remove(object);
       this.tx.commit();
       this.em.close();
   }
   // METODO PARA PROCURAR E RETONAR UM OBJETO
    public Object procurar (Class objeto, Integer x){
        Object object = null;
        em = emf.createEntityManager();
        tx = em.getTransaction();
        tx.begin();
        object = em.getReference(objeto, x);
        
        tx.commit();
        return object;
    }
// METODO PARA LISTAR TODOS OS DADOS DE UM TABELA DO BANCO
    public List listar (Class obj){
        em= emf.createEntityManager();
        Query query = em.createNativeQuery("select * from "+obj.getSimpleName());
        List r = query.getResultList();
        em.close();
        return r;

    }
    
    
    // METODO PARA LISTAR TODOS OS DADOS DE UM TABELA DO BANCO
    public List listardigitaisPessoa (Class obj,Integer id){
        em= emf.createEntityManager();
        Query query = em.createNativeQuery("select * from "+obj.getSimpleName()+" where idpessoa_iduser ="+ id);
        List r = query.getResultList();
        em.close();
        
        System.out.println(r.size());
        return r;

    }

}


