/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concrete;

import Abstract.IPedPagosPedidoProveedor;
import Concrete.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Model.CatTipoPago;
import Model.PedPagosPedidoProveedor;
import Model.PedPedidoProveedor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author chirr
 */
public class PedPagosPedidoProveedorJpaController implements Serializable, IPedPagosPedidoProveedor {

    public PedPagosPedidoProveedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public PedPagosPedidoProveedorJpaController() {
        this.emf = Persistence.createEntityManagerFactory("PuntoVentaJPA_2015PU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(PedPagosPedidoProveedor pedPagosPedidoProveedor) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CatTipoPago idCatTipoPago = pedPagosPedidoProveedor.getIdCatTipoPago();
            if (idCatTipoPago != null) {
                idCatTipoPago = em.getReference(idCatTipoPago.getClass(), idCatTipoPago.getId());
                pedPagosPedidoProveedor.setIdCatTipoPago(idCatTipoPago);
            }
            PedPedidoProveedor idPedPedidoProveedor = pedPagosPedidoProveedor.getIdPedPedidoProveedor();
            if (idPedPedidoProveedor != null) {
                idPedPedidoProveedor = em.getReference(idPedPedidoProveedor.getClass(), idPedPedidoProveedor.getId());
                pedPagosPedidoProveedor.setIdPedPedidoProveedor(idPedPedidoProveedor);
            }
            em.persist(pedPagosPedidoProveedor);
            if (idCatTipoPago != null) {
                idCatTipoPago.getPedPagosPedidoProveedorList().add(pedPagosPedidoProveedor);
                idCatTipoPago = em.merge(idCatTipoPago);
            }
            if (idPedPedidoProveedor != null) {
                idPedPedidoProveedor.getPedPagosPedidoProveedorList().add(pedPagosPedidoProveedor);
                idPedPedidoProveedor = em.merge(idPedPedidoProveedor);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(PedPagosPedidoProveedor pedPagosPedidoProveedor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PedPagosPedidoProveedor persistentPedPagosPedidoProveedor = em.find(PedPagosPedidoProveedor.class, pedPagosPedidoProveedor.getId());
            CatTipoPago idCatTipoPagoOld = persistentPedPagosPedidoProveedor.getIdCatTipoPago();
            CatTipoPago idCatTipoPagoNew = pedPagosPedidoProveedor.getIdCatTipoPago();
            PedPedidoProveedor idPedPedidoProveedorOld = persistentPedPagosPedidoProveedor.getIdPedPedidoProveedor();
            PedPedidoProveedor idPedPedidoProveedorNew = pedPagosPedidoProveedor.getIdPedPedidoProveedor();
            if (idCatTipoPagoNew != null) {
                idCatTipoPagoNew = em.getReference(idCatTipoPagoNew.getClass(), idCatTipoPagoNew.getId());
                pedPagosPedidoProveedor.setIdCatTipoPago(idCatTipoPagoNew);
            }
            if (idPedPedidoProveedorNew != null) {
                idPedPedidoProveedorNew = em.getReference(idPedPedidoProveedorNew.getClass(), idPedPedidoProveedorNew.getId());
                pedPagosPedidoProveedor.setIdPedPedidoProveedor(idPedPedidoProveedorNew);
            }
            pedPagosPedidoProveedor = em.merge(pedPagosPedidoProveedor);
            if (idCatTipoPagoOld != null && !idCatTipoPagoOld.equals(idCatTipoPagoNew)) {
                idCatTipoPagoOld.getPedPagosPedidoProveedorList().remove(pedPagosPedidoProveedor);
                idCatTipoPagoOld = em.merge(idCatTipoPagoOld);
            }
            if (idCatTipoPagoNew != null && !idCatTipoPagoNew.equals(idCatTipoPagoOld)) {
                idCatTipoPagoNew.getPedPagosPedidoProveedorList().add(pedPagosPedidoProveedor);
                idCatTipoPagoNew = em.merge(idCatTipoPagoNew);
            }
            if (idPedPedidoProveedorOld != null && !idPedPedidoProveedorOld.equals(idPedPedidoProveedorNew)) {
                idPedPedidoProveedorOld.getPedPagosPedidoProveedorList().remove(pedPagosPedidoProveedor);
                idPedPedidoProveedorOld = em.merge(idPedPedidoProveedorOld);
            }
            if (idPedPedidoProveedorNew != null && !idPedPedidoProveedorNew.equals(idPedPedidoProveedorOld)) {
                idPedPedidoProveedorNew.getPedPagosPedidoProveedorList().add(pedPagosPedidoProveedor);
                idPedPedidoProveedorNew = em.merge(idPedPedidoProveedorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pedPagosPedidoProveedor.getId();
                if (findPedPagosPedidoProveedor(id) == null) {
                    throw new NonexistentEntityException("The pedPagosPedidoProveedor with id " + id + " no longer exists.");
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
            PedPagosPedidoProveedor pedPagosPedidoProveedor;
            try {
                pedPagosPedidoProveedor = em.getReference(PedPagosPedidoProveedor.class, id);
                pedPagosPedidoProveedor.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pedPagosPedidoProveedor with id " + id + " no longer exists.", enfe);
            }
            CatTipoPago idCatTipoPago = pedPagosPedidoProveedor.getIdCatTipoPago();
            if (idCatTipoPago != null) {
                idCatTipoPago.getPedPagosPedidoProveedorList().remove(pedPagosPedidoProveedor);
                idCatTipoPago = em.merge(idCatTipoPago);
            }
            PedPedidoProveedor idPedPedidoProveedor = pedPagosPedidoProveedor.getIdPedPedidoProveedor();
            if (idPedPedidoProveedor != null) {
                idPedPedidoProveedor.getPedPagosPedidoProveedorList().remove(pedPagosPedidoProveedor);
                idPedPedidoProveedor = em.merge(idPedPedidoProveedor);
            }
            em.remove(pedPagosPedidoProveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<PedPagosPedidoProveedor> findPedPagosPedidoProveedorEntities() {
        return findPedPagosPedidoProveedorEntities(true, -1, -1);
    }

    @Override
    public List<PedPagosPedidoProveedor> findPedPagosPedidoProveedorEntities(int maxResults, int firstResult) {
        return findPedPagosPedidoProveedorEntities(false, maxResults, firstResult);
    }

    private List<PedPagosPedidoProveedor> findPedPagosPedidoProveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PedPagosPedidoProveedor.class));
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
    public PedPagosPedidoProveedor findPedPagosPedidoProveedor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PedPagosPedidoProveedor.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getPedPagosPedidoProveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PedPagosPedidoProveedor> rt = cq.from(PedPagosPedidoProveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
