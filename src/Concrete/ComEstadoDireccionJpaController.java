/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concrete;

import Abstract.IComEstadoDireccion;
import Concrete.exceptions.NonexistentEntityException;
import Concrete.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Model.ComDireccion;
import Model.ComEstadoDireccion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author chirr
 */
public class ComEstadoDireccionJpaController implements Serializable, IComEstadoDireccion {

    public ComEstadoDireccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public ComEstadoDireccionJpaController() {
        this.emf = Persistence.createEntityManagerFactory("PuntoVentaJPA_2015PU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(ComEstadoDireccion comEstadoDireccion) throws PreexistingEntityException, Exception {
        if (comEstadoDireccion.getComDireccionList() == null) {
            comEstadoDireccion.setComDireccionList(new ArrayList<ComDireccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<ComDireccion> attachedComDireccionList = new ArrayList<ComDireccion>();
            for (ComDireccion comDireccionListComDireccionToAttach : comEstadoDireccion.getComDireccionList()) {
                comDireccionListComDireccionToAttach = em.getReference(comDireccionListComDireccionToAttach.getClass(), comDireccionListComDireccionToAttach.getId());
                attachedComDireccionList.add(comDireccionListComDireccionToAttach);
            }
            comEstadoDireccion.setComDireccionList(attachedComDireccionList);
            em.persist(comEstadoDireccion);
            for (ComDireccion comDireccionListComDireccion : comEstadoDireccion.getComDireccionList()) {
                ComEstadoDireccion oldIdEstadoOfComDireccionListComDireccion = comDireccionListComDireccion.getIdEstado();
                comDireccionListComDireccion.setIdEstado(comEstadoDireccion);
                comDireccionListComDireccion = em.merge(comDireccionListComDireccion);
                if (oldIdEstadoOfComDireccionListComDireccion != null) {
                    oldIdEstadoOfComDireccionListComDireccion.getComDireccionList().remove(comDireccionListComDireccion);
                    oldIdEstadoOfComDireccionListComDireccion = em.merge(oldIdEstadoOfComDireccionListComDireccion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findComEstadoDireccion(comEstadoDireccion.getId()) != null) {
                throw new PreexistingEntityException("ComEstadoDireccion " + comEstadoDireccion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(ComEstadoDireccion comEstadoDireccion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ComEstadoDireccion persistentComEstadoDireccion = em.find(ComEstadoDireccion.class, comEstadoDireccion.getId());
            List<ComDireccion> comDireccionListOld = persistentComEstadoDireccion.getComDireccionList();
            List<ComDireccion> comDireccionListNew = comEstadoDireccion.getComDireccionList();
            List<ComDireccion> attachedComDireccionListNew = new ArrayList<ComDireccion>();
            for (ComDireccion comDireccionListNewComDireccionToAttach : comDireccionListNew) {
                comDireccionListNewComDireccionToAttach = em.getReference(comDireccionListNewComDireccionToAttach.getClass(), comDireccionListNewComDireccionToAttach.getId());
                attachedComDireccionListNew.add(comDireccionListNewComDireccionToAttach);
            }
            comDireccionListNew = attachedComDireccionListNew;
            comEstadoDireccion.setComDireccionList(comDireccionListNew);
            comEstadoDireccion = em.merge(comEstadoDireccion);
            for (ComDireccion comDireccionListOldComDireccion : comDireccionListOld) {
                if (!comDireccionListNew.contains(comDireccionListOldComDireccion)) {
                    comDireccionListOldComDireccion.setIdEstado(null);
                    comDireccionListOldComDireccion = em.merge(comDireccionListOldComDireccion);
                }
            }
            for (ComDireccion comDireccionListNewComDireccion : comDireccionListNew) {
                if (!comDireccionListOld.contains(comDireccionListNewComDireccion)) {
                    ComEstadoDireccion oldIdEstadoOfComDireccionListNewComDireccion = comDireccionListNewComDireccion.getIdEstado();
                    comDireccionListNewComDireccion.setIdEstado(comEstadoDireccion);
                    comDireccionListNewComDireccion = em.merge(comDireccionListNewComDireccion);
                    if (oldIdEstadoOfComDireccionListNewComDireccion != null && !oldIdEstadoOfComDireccionListNewComDireccion.equals(comEstadoDireccion)) {
                        oldIdEstadoOfComDireccionListNewComDireccion.getComDireccionList().remove(comDireccionListNewComDireccion);
                        oldIdEstadoOfComDireccionListNewComDireccion = em.merge(oldIdEstadoOfComDireccionListNewComDireccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = comEstadoDireccion.getId();
                if (findComEstadoDireccion(id) == null) {
                    throw new NonexistentEntityException("The comEstadoDireccion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ComEstadoDireccion comEstadoDireccion;
            try {
                comEstadoDireccion = em.getReference(ComEstadoDireccion.class, id);
                comEstadoDireccion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The comEstadoDireccion with id " + id + " no longer exists.", enfe);
            }
            List<ComDireccion> comDireccionList = comEstadoDireccion.getComDireccionList();
            for (ComDireccion comDireccionListComDireccion : comDireccionList) {
                comDireccionListComDireccion.setIdEstado(null);
                comDireccionListComDireccion = em.merge(comDireccionListComDireccion);
            }
            em.remove(comEstadoDireccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<ComEstadoDireccion> findComEstadoDireccionEntities() {
        return findComEstadoDireccionEntities(true, -1, -1);
    }

    @Override
    public List<ComEstadoDireccion> findComEstadoDireccionEntities(int maxResults, int firstResult) {
        return findComEstadoDireccionEntities(false, maxResults, firstResult);
    }

    private List<ComEstadoDireccion> findComEstadoDireccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ComEstadoDireccion.class));
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

    @Override
    public ComEstadoDireccion findComEstadoDireccion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ComEstadoDireccion.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getComEstadoDireccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ComEstadoDireccion> rt = cq.from(ComEstadoDireccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
