/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concrete;

import Abstract.ICatTipoPago;
import Concrete.exceptions.IllegalOrphanException;
import Concrete.exceptions.NonexistentEntityException;
import Concrete.exceptions.PreexistingEntityException;
import Model.CatTipoPago;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Model.PedPagosPedidoProveedor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author chirr
 */
public class CatTipoPagoJpaController implements Serializable, ICatTipoPago {

    public CatTipoPagoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public CatTipoPagoJpaController() {
         this.emf = Persistence.createEntityManagerFactory("PuntoVentaJPA_2015PU");
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(CatTipoPago catTipoPago) throws PreexistingEntityException, Exception {
        if (catTipoPago.getPedPagosPedidoProveedorList() == null) {
            catTipoPago.setPedPagosPedidoProveedorList(new ArrayList<PedPagosPedidoProveedor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<PedPagosPedidoProveedor> attachedPedPagosPedidoProveedorList = new ArrayList<PedPagosPedidoProveedor>();
            for (PedPagosPedidoProveedor pedPagosPedidoProveedorListPedPagosPedidoProveedorToAttach : catTipoPago.getPedPagosPedidoProveedorList()) {
                pedPagosPedidoProveedorListPedPagosPedidoProveedorToAttach = em.getReference(pedPagosPedidoProveedorListPedPagosPedidoProveedorToAttach.getClass(), pedPagosPedidoProveedorListPedPagosPedidoProveedorToAttach.getId());
                attachedPedPagosPedidoProveedorList.add(pedPagosPedidoProveedorListPedPagosPedidoProveedorToAttach);
            }
            catTipoPago.setPedPagosPedidoProveedorList(attachedPedPagosPedidoProveedorList);
            em.persist(catTipoPago);
            for (PedPagosPedidoProveedor pedPagosPedidoProveedorListPedPagosPedidoProveedor : catTipoPago.getPedPagosPedidoProveedorList()) {
                CatTipoPago oldIdCatTipoPagoOfPedPagosPedidoProveedorListPedPagosPedidoProveedor = pedPagosPedidoProveedorListPedPagosPedidoProveedor.getIdCatTipoPago();
                pedPagosPedidoProveedorListPedPagosPedidoProveedor.setIdCatTipoPago(catTipoPago);
                pedPagosPedidoProveedorListPedPagosPedidoProveedor = em.merge(pedPagosPedidoProveedorListPedPagosPedidoProveedor);
                if (oldIdCatTipoPagoOfPedPagosPedidoProveedorListPedPagosPedidoProveedor != null) {
                    oldIdCatTipoPagoOfPedPagosPedidoProveedorListPedPagosPedidoProveedor.getPedPagosPedidoProveedorList().remove(pedPagosPedidoProveedorListPedPagosPedidoProveedor);
                    oldIdCatTipoPagoOfPedPagosPedidoProveedorListPedPagosPedidoProveedor = em.merge(oldIdCatTipoPagoOfPedPagosPedidoProveedorListPedPagosPedidoProveedor);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCatTipoPago(catTipoPago.getId()) != null) {
                throw new PreexistingEntityException("CatTipoPago " + catTipoPago + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(CatTipoPago catTipoPago) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CatTipoPago persistentCatTipoPago = em.find(CatTipoPago.class, catTipoPago.getId());
            List<PedPagosPedidoProveedor> pedPagosPedidoProveedorListOld = persistentCatTipoPago.getPedPagosPedidoProveedorList();
            List<PedPagosPedidoProveedor> pedPagosPedidoProveedorListNew = catTipoPago.getPedPagosPedidoProveedorList();
            List<String> illegalOrphanMessages = null;
            for (PedPagosPedidoProveedor pedPagosPedidoProveedorListOldPedPagosPedidoProveedor : pedPagosPedidoProveedorListOld) {
                if (!pedPagosPedidoProveedorListNew.contains(pedPagosPedidoProveedorListOldPedPagosPedidoProveedor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PedPagosPedidoProveedor " + pedPagosPedidoProveedorListOldPedPagosPedidoProveedor + " since its idCatTipoPago field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<PedPagosPedidoProveedor> attachedPedPagosPedidoProveedorListNew = new ArrayList<PedPagosPedidoProveedor>();
            for (PedPagosPedidoProveedor pedPagosPedidoProveedorListNewPedPagosPedidoProveedorToAttach : pedPagosPedidoProveedorListNew) {
                pedPagosPedidoProveedorListNewPedPagosPedidoProveedorToAttach = em.getReference(pedPagosPedidoProveedorListNewPedPagosPedidoProveedorToAttach.getClass(), pedPagosPedidoProveedorListNewPedPagosPedidoProveedorToAttach.getId());
                attachedPedPagosPedidoProveedorListNew.add(pedPagosPedidoProveedorListNewPedPagosPedidoProveedorToAttach);
            }
            pedPagosPedidoProveedorListNew = attachedPedPagosPedidoProveedorListNew;
            catTipoPago.setPedPagosPedidoProveedorList(pedPagosPedidoProveedorListNew);
            catTipoPago = em.merge(catTipoPago);
            for (PedPagosPedidoProveedor pedPagosPedidoProveedorListNewPedPagosPedidoProveedor : pedPagosPedidoProveedorListNew) {
                if (!pedPagosPedidoProveedorListOld.contains(pedPagosPedidoProveedorListNewPedPagosPedidoProveedor)) {
                    CatTipoPago oldIdCatTipoPagoOfPedPagosPedidoProveedorListNewPedPagosPedidoProveedor = pedPagosPedidoProveedorListNewPedPagosPedidoProveedor.getIdCatTipoPago();
                    pedPagosPedidoProveedorListNewPedPagosPedidoProveedor.setIdCatTipoPago(catTipoPago);
                    pedPagosPedidoProveedorListNewPedPagosPedidoProveedor = em.merge(pedPagosPedidoProveedorListNewPedPagosPedidoProveedor);
                    if (oldIdCatTipoPagoOfPedPagosPedidoProveedorListNewPedPagosPedidoProveedor != null && !oldIdCatTipoPagoOfPedPagosPedidoProveedorListNewPedPagosPedidoProveedor.equals(catTipoPago)) {
                        oldIdCatTipoPagoOfPedPagosPedidoProveedorListNewPedPagosPedidoProveedor.getPedPagosPedidoProveedorList().remove(pedPagosPedidoProveedorListNewPedPagosPedidoProveedor);
                        oldIdCatTipoPagoOfPedPagosPedidoProveedorListNewPedPagosPedidoProveedor = em.merge(oldIdCatTipoPagoOfPedPagosPedidoProveedorListNewPedPagosPedidoProveedor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = catTipoPago.getId();
                if (findCatTipoPago(id) == null) {
                    throw new NonexistentEntityException("The catTipoPago with id " + id + " no longer exists.");
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
    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CatTipoPago catTipoPago;
            try {
                catTipoPago = em.getReference(CatTipoPago.class, id);
                catTipoPago.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The catTipoPago with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<PedPagosPedidoProveedor> pedPagosPedidoProveedorListOrphanCheck = catTipoPago.getPedPagosPedidoProveedorList();
            for (PedPagosPedidoProveedor pedPagosPedidoProveedorListOrphanCheckPedPagosPedidoProveedor : pedPagosPedidoProveedorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CatTipoPago (" + catTipoPago + ") cannot be destroyed since the PedPagosPedidoProveedor " + pedPagosPedidoProveedorListOrphanCheckPedPagosPedidoProveedor + " in its pedPagosPedidoProveedorList field has a non-nullable idCatTipoPago field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(catTipoPago);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<CatTipoPago> findCatTipoPagoEntities() {
        return findCatTipoPagoEntities(true, -1, -1);
    }

    @Override
    public List<CatTipoPago> findCatTipoPagoEntities(int maxResults, int firstResult) {
        return findCatTipoPagoEntities(false, maxResults, firstResult);
    }

    private List<CatTipoPago> findCatTipoPagoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CatTipoPago.class));
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
    public CatTipoPago findCatTipoPago(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CatTipoPago.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getCatTipoPagoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CatTipoPago> rt = cq.from(CatTipoPago.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
