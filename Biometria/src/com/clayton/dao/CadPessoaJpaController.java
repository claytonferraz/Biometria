/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clayton.dao;

import com.clayton.dao.exceptions.NonexistentEntityException;
import com.clayton.model.CadPessoa;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Clayton Ferraz
 */
public class CadPessoaJpaController implements Serializable {

    public CadPessoaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CadPessoa cadPessoa) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cadPessoa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CadPessoa cadPessoa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cadPessoa = em.merge(cadPessoa);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cadPessoa.getIduser();
                if (findCadPessoa(id) == null) {
                    throw new NonexistentEntityException("The cadPessoa with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CadPessoa cadPessoa;
            try {
                cadPessoa = em.getReference(CadPessoa.class, id);
                cadPessoa.getIduser();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cadPessoa with id " + id + " no longer exists.", enfe);
            }
            em.remove(cadPessoa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CadPessoa> findCadPessoaEntities() {
        return findCadPessoaEntities(true, -1, -1);
    }

    public List<CadPessoa> findCadPessoaEntities(int maxResults, int firstResult) {
        return findCadPessoaEntities(false, maxResults, firstResult);
    }

    private List<CadPessoa> findCadPessoaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CadPessoa.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public CadPessoa findCadPessoa(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CadPessoa.class, id);
        } finally {
            em.close();
        }
    }

    public int getCadPessoaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CadPessoa> rt = cq.from(CadPessoa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
