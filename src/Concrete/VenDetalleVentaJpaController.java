/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concrete;

import Abstract.IVenDetalleVenta;
import Concrete.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Model.ProProducto;
import Model.VenDetalleVenta;
import Model.VenVenta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author chirr
 */
public class VenDetalleVentaJpaController implements Serializable, IVenDetalleVenta {

    public VenDetalleVentaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public VenDetalleVentaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("PuntoVentaJPA_2015PU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(VenDetalleVenta venDetalleVenta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProProducto idProducto = venDetalleVenta.getIdProducto();
            if (idProducto != null) {
                idProducto = em.getReference(idProducto.getClass(), idProducto.getId());
                venDetalleVenta.setIdProducto(idProducto);
            }
            VenVenta idVenVenta = venDetalleVenta.getIdVenVenta();
            if (idVenVenta != null) {
                idVenVenta = em.getReference(idVenVenta.getClass(), idVenVenta.getId());
                venDetalleVenta.setIdVenVenta(idVenVenta);
            }
            em.persist(venDetalleVenta);
            if (idProducto != null) {
                idProducto.getVenDetalleVentaList().add(venDetalleVenta);
                idProducto = em.merge(idProducto);
            }
            if (idVenVenta != null) {
                idVenVenta.getVenDetalleVentaList().add(venDetalleVenta);
                idVenVenta = em.merge(idVenVenta);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(VenDetalleVenta venDetalleVenta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VenDetalleVenta persistentVenDetalleVenta = em.find(VenDetalleVenta.class, venDetalleVenta.getId());
            ProProducto idProductoOld = persistentVenDetalleVenta.getIdProducto();
            ProProducto idProductoNew = venDetalleVenta.getIdProducto();
            VenVenta idVenVentaOld = persistentVenDetalleVenta.getIdVenVenta();
            VenVenta idVenVentaNew = venDetalleVenta.getIdVenVenta();
            if (idProductoNew != null) {
                idProductoNew = em.getReference(idProductoNew.getClass(), idProductoNew.getId());
                venDetalleVenta.setIdProducto(idProductoNew);
            }
            if (idVenVentaNew != null) {
                idVenVentaNew = em.getReference(idVenVentaNew.getClass(), idVenVentaNew.getId());
                venDetalleVenta.setIdVenVenta(idVenVentaNew);
            }
            venDetalleVenta = em.merge(venDetalleVenta);
            if (idProductoOld != null && !idProductoOld.equals(idProductoNew)) {
                idProductoOld.getVenDetalleVentaList().remove(venDetalleVenta);
                idProductoOld = em.merge(idProductoOld);
            }
            if (idProductoNew != null && !idProductoNew.equals(idProductoOld)) {
                idProductoNew.getVenDetalleVentaList().add(venDetalleVenta);
                idProductoNew = em.merge(idProductoNew);
            }
            if (idVenVentaOld != null && !idVenVentaOld.equals(idVenVentaNew)) {
                idVenVentaOld.getVenDetalleVentaList().remove(venDetalleVenta);
                idVenVentaOld = em.merge(idVenVentaOld);
            }
            if (idVenVentaNew != null && !idVenVentaNew.equals(idVenVentaOld)) {
                idVenVentaNew.getVenDetalleVentaList().add(venDetalleVenta);
                idVenVentaNew = em.merge(idVenVentaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = venDetalleVenta.getId();
                if (findVenDetalleVenta(id) == null) {
                    throw new NonexistentEntityException("The venDetalleVenta with id " + id + " no longer exists.");
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
            VenDetalleVenta venDetalleVenta;
            try {
                venDetalleVenta = em.getReference(VenDetalleVenta.class, id);
                venDetalleVenta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The venDetalleVenta with id " + id + " no longer exists.", enfe);
            }
            ProProducto idProducto = venDetalleVenta.getIdProducto();
            if (idProducto != null) {
                idProducto.getVenDetalleVentaList().remove(venDetalleVenta);
                idProducto = em.merge(idProducto);
            }
            VenVenta idVenVenta = venDetalleVenta.getIdVenVenta();
            if (idVenVenta != null) {
                idVenVenta.getVenDetalleVentaList().remove(venDetalleVenta);
                idVenVenta = em.merge(idVenVenta);
            }
            em.remove(venDetalleVenta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<VenDetalleVenta> findVenDetalleVentaEntities() {
        return findVenDetalleVentaEntities(true, -1, -1);
    }

    @Override
    public List<VenDetalleVenta> findVenDetalleVentaEntities(int maxResults, int firstResult) {
        return findVenDetalleVentaEntities(false, maxResults, firstResult);
    }

    private List<VenDetalleVenta> findVenDetalleVentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VenDetalleVenta.class));
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
    public VenDetalleVenta findVenDetalleVenta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VenDetalleVenta.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getVenDetalleVentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VenDetalleVenta> rt = cq.from(VenDetalleVenta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
