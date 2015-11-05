/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concrete;

import Abstract.IVenVenta;
import Concrete.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Model.VenDetalleVenta;
import Model.VenVenta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author chirr
 */
public class VenVentaJpaController implements Serializable, IVenVenta {

    public VenVentaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public VenVentaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("PuntoVentaJPA_2015PU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(VenVenta venVenta) {
        if (venVenta.getVenDetalleVentaList() == null) {
            venVenta.setVenDetalleVentaList(new ArrayList<VenDetalleVenta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<VenDetalleVenta> attachedVenDetalleVentaList = new ArrayList<VenDetalleVenta>();
            for (VenDetalleVenta venDetalleVentaListVenDetalleVentaToAttach : venVenta.getVenDetalleVentaList()) {
                venDetalleVentaListVenDetalleVentaToAttach = em.getReference(venDetalleVentaListVenDetalleVentaToAttach.getClass(), venDetalleVentaListVenDetalleVentaToAttach.getId());
                attachedVenDetalleVentaList.add(venDetalleVentaListVenDetalleVentaToAttach);
            }
            venVenta.setVenDetalleVentaList(attachedVenDetalleVentaList);
            em.persist(venVenta);
            for (VenDetalleVenta venDetalleVentaListVenDetalleVenta : venVenta.getVenDetalleVentaList()) {
                VenVenta oldIdVenVentaOfVenDetalleVentaListVenDetalleVenta = venDetalleVentaListVenDetalleVenta.getIdVenVenta();
                venDetalleVentaListVenDetalleVenta.setIdVenVenta(venVenta);
                venDetalleVentaListVenDetalleVenta = em.merge(venDetalleVentaListVenDetalleVenta);
                if (oldIdVenVentaOfVenDetalleVentaListVenDetalleVenta != null) {
                    oldIdVenVentaOfVenDetalleVentaListVenDetalleVenta.getVenDetalleVentaList().remove(venDetalleVentaListVenDetalleVenta);
                    oldIdVenVentaOfVenDetalleVentaListVenDetalleVenta = em.merge(oldIdVenVentaOfVenDetalleVentaListVenDetalleVenta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(VenVenta venVenta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VenVenta persistentVenVenta = em.find(VenVenta.class, venVenta.getId());
            List<VenDetalleVenta> venDetalleVentaListOld = persistentVenVenta.getVenDetalleVentaList();
            List<VenDetalleVenta> venDetalleVentaListNew = venVenta.getVenDetalleVentaList();
            List<VenDetalleVenta> attachedVenDetalleVentaListNew = new ArrayList<VenDetalleVenta>();
            for (VenDetalleVenta venDetalleVentaListNewVenDetalleVentaToAttach : venDetalleVentaListNew) {
                venDetalleVentaListNewVenDetalleVentaToAttach = em.getReference(venDetalleVentaListNewVenDetalleVentaToAttach.getClass(), venDetalleVentaListNewVenDetalleVentaToAttach.getId());
                attachedVenDetalleVentaListNew.add(venDetalleVentaListNewVenDetalleVentaToAttach);
            }
            venDetalleVentaListNew = attachedVenDetalleVentaListNew;
            venVenta.setVenDetalleVentaList(venDetalleVentaListNew);
            venVenta = em.merge(venVenta);
            for (VenDetalleVenta venDetalleVentaListOldVenDetalleVenta : venDetalleVentaListOld) {
                if (!venDetalleVentaListNew.contains(venDetalleVentaListOldVenDetalleVenta)) {
                    venDetalleVentaListOldVenDetalleVenta.setIdVenVenta(null);
                    venDetalleVentaListOldVenDetalleVenta = em.merge(venDetalleVentaListOldVenDetalleVenta);
                }
            }
            for (VenDetalleVenta venDetalleVentaListNewVenDetalleVenta : venDetalleVentaListNew) {
                if (!venDetalleVentaListOld.contains(venDetalleVentaListNewVenDetalleVenta)) {
                    VenVenta oldIdVenVentaOfVenDetalleVentaListNewVenDetalleVenta = venDetalleVentaListNewVenDetalleVenta.getIdVenVenta();
                    venDetalleVentaListNewVenDetalleVenta.setIdVenVenta(venVenta);
                    venDetalleVentaListNewVenDetalleVenta = em.merge(venDetalleVentaListNewVenDetalleVenta);
                    if (oldIdVenVentaOfVenDetalleVentaListNewVenDetalleVenta != null && !oldIdVenVentaOfVenDetalleVentaListNewVenDetalleVenta.equals(venVenta)) {
                        oldIdVenVentaOfVenDetalleVentaListNewVenDetalleVenta.getVenDetalleVentaList().remove(venDetalleVentaListNewVenDetalleVenta);
                        oldIdVenVentaOfVenDetalleVentaListNewVenDetalleVenta = em.merge(oldIdVenVentaOfVenDetalleVentaListNewVenDetalleVenta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = venVenta.getId();
                if (findVenVenta(id) == null) {
                    throw new NonexistentEntityException("The venVenta with id " + id + " no longer exists.");
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
            VenVenta venVenta;
            try {
                venVenta = em.getReference(VenVenta.class, id);
                venVenta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The venVenta with id " + id + " no longer exists.", enfe);
            }
            List<VenDetalleVenta> venDetalleVentaList = venVenta.getVenDetalleVentaList();
            for (VenDetalleVenta venDetalleVentaListVenDetalleVenta : venDetalleVentaList) {
                venDetalleVentaListVenDetalleVenta.setIdVenVenta(null);
                venDetalleVentaListVenDetalleVenta = em.merge(venDetalleVentaListVenDetalleVenta);
            }
            em.remove(venVenta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<VenVenta> findVenVentaEntities() {
        return findVenVentaEntities(true, -1, -1);
    }

    @Override
    public List<VenVenta> findVenVentaEntities(int maxResults, int firstResult) {
        return findVenVentaEntities(false, maxResults, firstResult);
    }

    private List<VenVenta> findVenVentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VenVenta.class));
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
    public VenVenta findVenVenta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VenVenta.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getVenVentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VenVenta> rt = cq.from(VenVenta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
