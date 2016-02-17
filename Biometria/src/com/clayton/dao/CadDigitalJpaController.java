/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clayton.dao;

import com.clayton.dao.exceptions.NonexistentEntityException;
import com.clayton.model.CadDigital;
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
public class CadDigitalJpaController implements Serializable {

    public CadDigitalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CadDigital cadDigital) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cadDigital);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CadDigital cadDigital) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cadDigital = em.merge(cadDigital);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cadDigital.getId();
                if (findCadDigital(id) == null) {
                    throw new NonexistentEntityException("The cadDigital with id " + id + " no longer exists.");
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
            CadDigital cadDigital;
            try {
                cadDigital = em.getReference(CadDigital.class, id);
                cadDigital.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cadDigital with id " + id + " no longer exists.", enfe);
            }
            em.remove(cadDigital);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CadDigital> findCadDigitalEntities() {
        return findCadDigitalEntities(true, -1, -1);
    }

    public List<CadDigital> findCadDigitalEntities(int maxResults, int firstResult) {
        return findCadDigitalEntities(false, maxResults, firstResult);
    }

    private List<CadDigital> findCadDigitalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CadDigital.class));
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

    public CadDigital findCadDigital(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CadDigital.class, id);
        } finally {
            em.close();
        }
    }

    public int getCadDigitalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CadDigital> rt = cq.from(CadDigital.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
