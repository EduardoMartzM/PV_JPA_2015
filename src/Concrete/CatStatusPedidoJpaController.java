/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concrete;

import Abstract.ICatStatusPedido;
import Concrete.exceptions.IllegalOrphanException;
import Concrete.exceptions.NonexistentEntityException;
import Concrete.exceptions.PreexistingEntityException;
import Model.CatStatusPedido;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Model.PedPedidoProveedor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author chirr
 */
public class CatStatusPedidoJpaController implements Serializable, ICatStatusPedido {

    public CatStatusPedidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public CatStatusPedidoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("PuntoVentaJPA_2015PU");
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(CatStatusPedido catStatusPedido) throws PreexistingEntityException, Exception {
        if (catStatusPedido.getPedPedidoProveedorList() == null) {
            catStatusPedido.setPedPedidoProveedorList(new ArrayList<PedPedidoProveedor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<PedPedidoProveedor> attachedPedPedidoProveedorList = new ArrayList<PedPedidoProveedor>();
            for (PedPedidoProveedor pedPedidoProveedorListPedPedidoProveedorToAttach : catStatusPedido.getPedPedidoProveedorList()) {
                pedPedidoProveedorListPedPedidoProveedorToAttach = em.getReference(pedPedidoProveedorListPedPedidoProveedorToAttach.getClass(), pedPedidoProveedorListPedPedidoProveedorToAttach.getId());
                attachedPedPedidoProveedorList.add(pedPedidoProveedorListPedPedidoProveedorToAttach);
            }
            catStatusPedido.setPedPedidoProveedorList(attachedPedPedidoProveedorList);
            em.persist(catStatusPedido);
            for (PedPedidoProveedor pedPedidoProveedorListPedPedidoProveedor : catStatusPedido.getPedPedidoProveedorList()) {
                CatStatusPedido oldIdCatStatusPedidoOfPedPedidoProveedorListPedPedidoProveedor = pedPedidoProveedorListPedPedidoProveedor.getIdCatStatusPedido();
                pedPedidoProveedorListPedPedidoProveedor.setIdCatStatusPedido(catStatusPedido);
                pedPedidoProveedorListPedPedidoProveedor = em.merge(pedPedidoProveedorListPedPedidoProveedor);
                if (oldIdCatStatusPedidoOfPedPedidoProveedorListPedPedidoProveedor != null) {
                    oldIdCatStatusPedidoOfPedPedidoProveedorListPedPedidoProveedor.getPedPedidoProveedorList().remove(pedPedidoProveedorListPedPedidoProveedor);
                    oldIdCatStatusPedidoOfPedPedidoProveedorListPedPedidoProveedor = em.merge(oldIdCatStatusPedidoOfPedPedidoProveedorListPedPedidoProveedor);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCatStatusPedido(catStatusPedido.getId()) != null) {
                throw new PreexistingEntityException("CatStatusPedido " + catStatusPedido + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(CatStatusPedido catStatusPedido) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CatStatusPedido persistentCatStatusPedido = em.find(CatStatusPedido.class, catStatusPedido.getId());
            List<PedPedidoProveedor> pedPedidoProveedorListOld = persistentCatStatusPedido.getPedPedidoProveedorList();
            List<PedPedidoProveedor> pedPedidoProveedorListNew = catStatusPedido.getPedPedidoProveedorList();
            List<String> illegalOrphanMessages = null;
            for (PedPedidoProveedor pedPedidoProveedorListOldPedPedidoProveedor : pedPedidoProveedorListOld) {
                if (!pedPedidoProveedorListNew.contains(pedPedidoProveedorListOldPedPedidoProveedor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PedPedidoProveedor " + pedPedidoProveedorListOldPedPedidoProveedor + " since its idCatStatusPedido field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<PedPedidoProveedor> attachedPedPedidoProveedorListNew = new ArrayList<PedPedidoProveedor>();
            for (PedPedidoProveedor pedPedidoProveedorListNewPedPedidoProveedorToAttach : pedPedidoProveedorListNew) {
                pedPedidoProveedorListNewPedPedidoProveedorToAttach = em.getReference(pedPedidoProveedorListNewPedPedidoProveedorToAttach.getClass(), pedPedidoProveedorListNewPedPedidoProveedorToAttach.getId());
                attachedPedPedidoProveedorListNew.add(pedPedidoProveedorListNewPedPedidoProveedorToAttach);
            }
            pedPedidoProveedorListNew = attachedPedPedidoProveedorListNew;
            catStatusPedido.setPedPedidoProveedorList(pedPedidoProveedorListNew);
            catStatusPedido = em.merge(catStatusPedido);
            for (PedPedidoProveedor pedPedidoProveedorListNewPedPedidoProveedor : pedPedidoProveedorListNew) {
                if (!pedPedidoProveedorListOld.contains(pedPedidoProveedorListNewPedPedidoProveedor)) {
                    CatStatusPedido oldIdCatStatusPedidoOfPedPedidoProveedorListNewPedPedidoProveedor = pedPedidoProveedorListNewPedPedidoProveedor.getIdCatStatusPedido();
                    pedPedidoProveedorListNewPedPedidoProveedor.setIdCatStatusPedido(catStatusPedido);
                    pedPedidoProveedorListNewPedPedidoProveedor = em.merge(pedPedidoProveedorListNewPedPedidoProveedor);
                    if (oldIdCatStatusPedidoOfPedPedidoProveedorListNewPedPedidoProveedor != null && !oldIdCatStatusPedidoOfPedPedidoProveedorListNewPedPedidoProveedor.equals(catStatusPedido)) {
                        oldIdCatStatusPedidoOfPedPedidoProveedorListNewPedPedidoProveedor.getPedPedidoProveedorList().remove(pedPedidoProveedorListNewPedPedidoProveedor);
                        oldIdCatStatusPedidoOfPedPedidoProveedorListNewPedPedidoProveedor = em.merge(oldIdCatStatusPedidoOfPedPedidoProveedorListNewPedPedidoProveedor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = catStatusPedido.getId();
                if (findCatStatusPedido(id) == null) {
                    throw new NonexistentEntityException("The catStatusPedido with id " + id + " no longer exists.");
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
            CatStatusPedido catStatusPedido;
            try {
                catStatusPedido = em.getReference(CatStatusPedido.class, id);
                catStatusPedido.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The catStatusPedido with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<PedPedidoProveedor> pedPedidoProveedorListOrphanCheck = catStatusPedido.getPedPedidoProveedorList();
            for (PedPedidoProveedor pedPedidoProveedorListOrphanCheckPedPedidoProveedor : pedPedidoProveedorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CatStatusPedido (" + catStatusPedido + ") cannot be destroyed since the PedPedidoProveedor " + pedPedidoProveedorListOrphanCheckPedPedidoProveedor + " in its pedPedidoProveedorList field has a non-nullable idCatStatusPedido field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(catStatusPedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<CatStatusPedido> findCatStatusPedidoEntities() {
        return findCatStatusPedidoEntities(true, -1, -1);
    }

    @Override
    public List<CatStatusPedido> findCatStatusPedidoEntities(int maxResults, int firstResult) {
        return findCatStatusPedidoEntities(false, maxResults, firstResult);
    }

    private List<CatStatusPedido> findCatStatusPedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CatStatusPedido.class));
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
    public CatStatusPedido findCatStatusPedido(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CatStatusPedido.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getCatStatusPedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CatStatusPedido> rt = cq.from(CatStatusPedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
