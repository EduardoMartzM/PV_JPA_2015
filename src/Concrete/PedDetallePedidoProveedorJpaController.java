/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concrete;

import Abstract.IPedDetallePedidoProveedor;
import Concrete.exceptions.NonexistentEntityException;
import Model.PedDetallePedidoProveedor;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Model.PedPedidoProveedor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author chirr
 */
public class PedDetallePedidoProveedorJpaController implements Serializable, IPedDetallePedidoProveedor {

    public PedDetallePedidoProveedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public PedDetallePedidoProveedorJpaController() {
        this.emf = Persistence.createEntityManagerFactory("PuntoVentaJPA_2015PU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(PedDetallePedidoProveedor pedDetallePedidoProveedor) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PedPedidoProveedor idPedPedidoProveedor = pedDetallePedidoProveedor.getIdPedPedidoProveedor();
            if (idPedPedidoProveedor != null) {
                idPedPedidoProveedor = em.getReference(idPedPedidoProveedor.getClass(), idPedPedidoProveedor.getId());
                pedDetallePedidoProveedor.setIdPedPedidoProveedor(idPedPedidoProveedor);
            }
            em.persist(pedDetallePedidoProveedor);
            if (idPedPedidoProveedor != null) {
                idPedPedidoProveedor.getPedDetallePedidoProveedorList().add(pedDetallePedidoProveedor);
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
    public void edit(PedDetallePedidoProveedor pedDetallePedidoProveedor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PedDetallePedidoProveedor persistentPedDetallePedidoProveedor = em.find(PedDetallePedidoProveedor.class, pedDetallePedidoProveedor.getId());
            PedPedidoProveedor idPedPedidoProveedorOld = persistentPedDetallePedidoProveedor.getIdPedPedidoProveedor();
            PedPedidoProveedor idPedPedidoProveedorNew = pedDetallePedidoProveedor.getIdPedPedidoProveedor();
            if (idPedPedidoProveedorNew != null) {
                idPedPedidoProveedorNew = em.getReference(idPedPedidoProveedorNew.getClass(), idPedPedidoProveedorNew.getId());
                pedDetallePedidoProveedor.setIdPedPedidoProveedor(idPedPedidoProveedorNew);
            }
            pedDetallePedidoProveedor = em.merge(pedDetallePedidoProveedor);
            if (idPedPedidoProveedorOld != null && !idPedPedidoProveedorOld.equals(idPedPedidoProveedorNew)) {
                idPedPedidoProveedorOld.getPedDetallePedidoProveedorList().remove(pedDetallePedidoProveedor);
                idPedPedidoProveedorOld = em.merge(idPedPedidoProveedorOld);
            }
            if (idPedPedidoProveedorNew != null && !idPedPedidoProveedorNew.equals(idPedPedidoProveedorOld)) {
                idPedPedidoProveedorNew.getPedDetallePedidoProveedorList().add(pedDetallePedidoProveedor);
                idPedPedidoProveedorNew = em.merge(idPedPedidoProveedorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pedDetallePedidoProveedor.getId();
                if (findPedDetallePedidoProveedor(id) == null) {
                    throw new NonexistentEntityException("The pedDetallePedidoProveedor with id " + id + " no longer exists.");
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
            PedDetallePedidoProveedor pedDetallePedidoProveedor;
            try {
                pedDetallePedidoProveedor = em.getReference(PedDetallePedidoProveedor.class, id);
                pedDetallePedidoProveedor.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pedDetallePedidoProveedor with id " + id + " no longer exists.", enfe);
            }
            PedPedidoProveedor idPedPedidoProveedor = pedDetallePedidoProveedor.getIdPedPedidoProveedor();
            if (idPedPedidoProveedor != null) {
                idPedPedidoProveedor.getPedDetallePedidoProveedorList().remove(pedDetallePedidoProveedor);
                idPedPedidoProveedor = em.merge(idPedPedidoProveedor);
            }
            em.remove(pedDetallePedidoProveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<PedDetallePedidoProveedor> findPedDetallePedidoProveedorEntities() {
        return findPedDetallePedidoProveedorEntities(true, -1, -1);
    }

    @Override
    public List<PedDetallePedidoProveedor> findPedDetallePedidoProveedorEntities(int maxResults, int firstResult) {
        return findPedDetallePedidoProveedorEntities(false, maxResults, firstResult);
    }

    private List<PedDetallePedidoProveedor> findPedDetallePedidoProveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PedDetallePedidoProveedor.class));
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
    public PedDetallePedidoProveedor findPedDetallePedidoProveedor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PedDetallePedidoProveedor.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getPedDetallePedidoProveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PedDetallePedidoProveedor> rt = cq.from(PedDetallePedidoProveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
