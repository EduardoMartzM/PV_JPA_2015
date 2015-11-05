/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concrete;

import Abstract.IPedPedidoProveedor;
import Concrete.exceptions.IllegalOrphanException;
import Concrete.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Model.CatStatusPedido;
import Model.PedPagosPedidoProveedor;
import java.util.ArrayList;
import java.util.List;
import Model.PedDetallePedidoProveedor;
import Model.PedPedidoProveedor;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author chirr
 */
public class PedPedidoProveedorJpaController implements Serializable, IPedPedidoProveedor {

    public PedPedidoProveedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public PedPedidoProveedorJpaController() {
        this.emf = Persistence.createEntityManagerFactory("PuntoVentaJPA_2015PU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(PedPedidoProveedor pedPedidoProveedor) {
        if (pedPedidoProveedor.getPedPagosPedidoProveedorList() == null) {
            pedPedidoProveedor.setPedPagosPedidoProveedorList(new ArrayList<PedPagosPedidoProveedor>());
        }
        if (pedPedidoProveedor.getPedDetallePedidoProveedorList() == null) {
            pedPedidoProveedor.setPedDetallePedidoProveedorList(new ArrayList<PedDetallePedidoProveedor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CatStatusPedido idCatStatusPedido = pedPedidoProveedor.getIdCatStatusPedido();
            if (idCatStatusPedido != null) {
                idCatStatusPedido = em.getReference(idCatStatusPedido.getClass(), idCatStatusPedido.getId());
                pedPedidoProveedor.setIdCatStatusPedido(idCatStatusPedido);
            }
            List<PedPagosPedidoProveedor> attachedPedPagosPedidoProveedorList = new ArrayList<PedPagosPedidoProveedor>();
            for (PedPagosPedidoProveedor pedPagosPedidoProveedorListPedPagosPedidoProveedorToAttach : pedPedidoProveedor.getPedPagosPedidoProveedorList()) {
                pedPagosPedidoProveedorListPedPagosPedidoProveedorToAttach = em.getReference(pedPagosPedidoProveedorListPedPagosPedidoProveedorToAttach.getClass(), pedPagosPedidoProveedorListPedPagosPedidoProveedorToAttach.getId());
                attachedPedPagosPedidoProveedorList.add(pedPagosPedidoProveedorListPedPagosPedidoProveedorToAttach);
            }
            pedPedidoProveedor.setPedPagosPedidoProveedorList(attachedPedPagosPedidoProveedorList);
            List<PedDetallePedidoProveedor> attachedPedDetallePedidoProveedorList = new ArrayList<PedDetallePedidoProveedor>();
            for (PedDetallePedidoProveedor pedDetallePedidoProveedorListPedDetallePedidoProveedorToAttach : pedPedidoProveedor.getPedDetallePedidoProveedorList()) {
                pedDetallePedidoProveedorListPedDetallePedidoProveedorToAttach = em.getReference(pedDetallePedidoProveedorListPedDetallePedidoProveedorToAttach.getClass(), pedDetallePedidoProveedorListPedDetallePedidoProveedorToAttach.getId());
                attachedPedDetallePedidoProveedorList.add(pedDetallePedidoProveedorListPedDetallePedidoProveedorToAttach);
            }
            pedPedidoProveedor.setPedDetallePedidoProveedorList(attachedPedDetallePedidoProveedorList);
            em.persist(pedPedidoProveedor);
            if (idCatStatusPedido != null) {
                idCatStatusPedido.getPedPedidoProveedorList().add(pedPedidoProveedor);
                idCatStatusPedido = em.merge(idCatStatusPedido);
            }
            for (PedPagosPedidoProveedor pedPagosPedidoProveedorListPedPagosPedidoProveedor : pedPedidoProveedor.getPedPagosPedidoProveedorList()) {
                PedPedidoProveedor oldIdPedPedidoProveedorOfPedPagosPedidoProveedorListPedPagosPedidoProveedor = pedPagosPedidoProveedorListPedPagosPedidoProveedor.getIdPedPedidoProveedor();
                pedPagosPedidoProveedorListPedPagosPedidoProveedor.setIdPedPedidoProveedor(pedPedidoProveedor);
                pedPagosPedidoProveedorListPedPagosPedidoProveedor = em.merge(pedPagosPedidoProveedorListPedPagosPedidoProveedor);
                if (oldIdPedPedidoProveedorOfPedPagosPedidoProveedorListPedPagosPedidoProveedor != null) {
                    oldIdPedPedidoProveedorOfPedPagosPedidoProveedorListPedPagosPedidoProveedor.getPedPagosPedidoProveedorList().remove(pedPagosPedidoProveedorListPedPagosPedidoProveedor);
                    oldIdPedPedidoProveedorOfPedPagosPedidoProveedorListPedPagosPedidoProveedor = em.merge(oldIdPedPedidoProveedorOfPedPagosPedidoProveedorListPedPagosPedidoProveedor);
                }
            }
            for (PedDetallePedidoProveedor pedDetallePedidoProveedorListPedDetallePedidoProveedor : pedPedidoProveedor.getPedDetallePedidoProveedorList()) {
                PedPedidoProveedor oldIdPedPedidoProveedorOfPedDetallePedidoProveedorListPedDetallePedidoProveedor = pedDetallePedidoProveedorListPedDetallePedidoProveedor.getIdPedPedidoProveedor();
                pedDetallePedidoProveedorListPedDetallePedidoProveedor.setIdPedPedidoProveedor(pedPedidoProveedor);
                pedDetallePedidoProveedorListPedDetallePedidoProveedor = em.merge(pedDetallePedidoProveedorListPedDetallePedidoProveedor);
                if (oldIdPedPedidoProveedorOfPedDetallePedidoProveedorListPedDetallePedidoProveedor != null) {
                    oldIdPedPedidoProveedorOfPedDetallePedidoProveedorListPedDetallePedidoProveedor.getPedDetallePedidoProveedorList().remove(pedDetallePedidoProveedorListPedDetallePedidoProveedor);
                    oldIdPedPedidoProveedorOfPedDetallePedidoProveedorListPedDetallePedidoProveedor = em.merge(oldIdPedPedidoProveedorOfPedDetallePedidoProveedorListPedDetallePedidoProveedor);
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
    public void edit(PedPedidoProveedor pedPedidoProveedor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PedPedidoProveedor persistentPedPedidoProveedor = em.find(PedPedidoProveedor.class, pedPedidoProveedor.getId());
            CatStatusPedido idCatStatusPedidoOld = persistentPedPedidoProveedor.getIdCatStatusPedido();
            CatStatusPedido idCatStatusPedidoNew = pedPedidoProveedor.getIdCatStatusPedido();
            List<PedPagosPedidoProveedor> pedPagosPedidoProveedorListOld = persistentPedPedidoProveedor.getPedPagosPedidoProveedorList();
            List<PedPagosPedidoProveedor> pedPagosPedidoProveedorListNew = pedPedidoProveedor.getPedPagosPedidoProveedorList();
            List<PedDetallePedidoProveedor> pedDetallePedidoProveedorListOld = persistentPedPedidoProveedor.getPedDetallePedidoProveedorList();
            List<PedDetallePedidoProveedor> pedDetallePedidoProveedorListNew = pedPedidoProveedor.getPedDetallePedidoProveedorList();
            List<String> illegalOrphanMessages = null;
            for (PedPagosPedidoProveedor pedPagosPedidoProveedorListOldPedPagosPedidoProveedor : pedPagosPedidoProveedorListOld) {
                if (!pedPagosPedidoProveedorListNew.contains(pedPagosPedidoProveedorListOldPedPagosPedidoProveedor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PedPagosPedidoProveedor " + pedPagosPedidoProveedorListOldPedPagosPedidoProveedor + " since its idPedPedidoProveedor field is not nullable.");
                }
            }
            for (PedDetallePedidoProveedor pedDetallePedidoProveedorListOldPedDetallePedidoProveedor : pedDetallePedidoProveedorListOld) {
                if (!pedDetallePedidoProveedorListNew.contains(pedDetallePedidoProveedorListOldPedDetallePedidoProveedor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PedDetallePedidoProveedor " + pedDetallePedidoProveedorListOldPedDetallePedidoProveedor + " since its idPedPedidoProveedor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idCatStatusPedidoNew != null) {
                idCatStatusPedidoNew = em.getReference(idCatStatusPedidoNew.getClass(), idCatStatusPedidoNew.getId());
                pedPedidoProveedor.setIdCatStatusPedido(idCatStatusPedidoNew);
            }
            List<PedPagosPedidoProveedor> attachedPedPagosPedidoProveedorListNew = new ArrayList<PedPagosPedidoProveedor>();
            for (PedPagosPedidoProveedor pedPagosPedidoProveedorListNewPedPagosPedidoProveedorToAttach : pedPagosPedidoProveedorListNew) {
                pedPagosPedidoProveedorListNewPedPagosPedidoProveedorToAttach = em.getReference(pedPagosPedidoProveedorListNewPedPagosPedidoProveedorToAttach.getClass(), pedPagosPedidoProveedorListNewPedPagosPedidoProveedorToAttach.getId());
                attachedPedPagosPedidoProveedorListNew.add(pedPagosPedidoProveedorListNewPedPagosPedidoProveedorToAttach);
            }
            pedPagosPedidoProveedorListNew = attachedPedPagosPedidoProveedorListNew;
            pedPedidoProveedor.setPedPagosPedidoProveedorList(pedPagosPedidoProveedorListNew);
            List<PedDetallePedidoProveedor> attachedPedDetallePedidoProveedorListNew = new ArrayList<PedDetallePedidoProveedor>();
            for (PedDetallePedidoProveedor pedDetallePedidoProveedorListNewPedDetallePedidoProveedorToAttach : pedDetallePedidoProveedorListNew) {
                pedDetallePedidoProveedorListNewPedDetallePedidoProveedorToAttach = em.getReference(pedDetallePedidoProveedorListNewPedDetallePedidoProveedorToAttach.getClass(), pedDetallePedidoProveedorListNewPedDetallePedidoProveedorToAttach.getId());
                attachedPedDetallePedidoProveedorListNew.add(pedDetallePedidoProveedorListNewPedDetallePedidoProveedorToAttach);
            }
            pedDetallePedidoProveedorListNew = attachedPedDetallePedidoProveedorListNew;
            pedPedidoProveedor.setPedDetallePedidoProveedorList(pedDetallePedidoProveedorListNew);
            pedPedidoProveedor = em.merge(pedPedidoProveedor);
            if (idCatStatusPedidoOld != null && !idCatStatusPedidoOld.equals(idCatStatusPedidoNew)) {
                idCatStatusPedidoOld.getPedPedidoProveedorList().remove(pedPedidoProveedor);
                idCatStatusPedidoOld = em.merge(idCatStatusPedidoOld);
            }
            if (idCatStatusPedidoNew != null && !idCatStatusPedidoNew.equals(idCatStatusPedidoOld)) {
                idCatStatusPedidoNew.getPedPedidoProveedorList().add(pedPedidoProveedor);
                idCatStatusPedidoNew = em.merge(idCatStatusPedidoNew);
            }
            for (PedPagosPedidoProveedor pedPagosPedidoProveedorListNewPedPagosPedidoProveedor : pedPagosPedidoProveedorListNew) {
                if (!pedPagosPedidoProveedorListOld.contains(pedPagosPedidoProveedorListNewPedPagosPedidoProveedor)) {
                    PedPedidoProveedor oldIdPedPedidoProveedorOfPedPagosPedidoProveedorListNewPedPagosPedidoProveedor = pedPagosPedidoProveedorListNewPedPagosPedidoProveedor.getIdPedPedidoProveedor();
                    pedPagosPedidoProveedorListNewPedPagosPedidoProveedor.setIdPedPedidoProveedor(pedPedidoProveedor);
                    pedPagosPedidoProveedorListNewPedPagosPedidoProveedor = em.merge(pedPagosPedidoProveedorListNewPedPagosPedidoProveedor);
                    if (oldIdPedPedidoProveedorOfPedPagosPedidoProveedorListNewPedPagosPedidoProveedor != null && !oldIdPedPedidoProveedorOfPedPagosPedidoProveedorListNewPedPagosPedidoProveedor.equals(pedPedidoProveedor)) {
                        oldIdPedPedidoProveedorOfPedPagosPedidoProveedorListNewPedPagosPedidoProveedor.getPedPagosPedidoProveedorList().remove(pedPagosPedidoProveedorListNewPedPagosPedidoProveedor);
                        oldIdPedPedidoProveedorOfPedPagosPedidoProveedorListNewPedPagosPedidoProveedor = em.merge(oldIdPedPedidoProveedorOfPedPagosPedidoProveedorListNewPedPagosPedidoProveedor);
                    }
                }
            }
            for (PedDetallePedidoProveedor pedDetallePedidoProveedorListNewPedDetallePedidoProveedor : pedDetallePedidoProveedorListNew) {
                if (!pedDetallePedidoProveedorListOld.contains(pedDetallePedidoProveedorListNewPedDetallePedidoProveedor)) {
                    PedPedidoProveedor oldIdPedPedidoProveedorOfPedDetallePedidoProveedorListNewPedDetallePedidoProveedor = pedDetallePedidoProveedorListNewPedDetallePedidoProveedor.getIdPedPedidoProveedor();
                    pedDetallePedidoProveedorListNewPedDetallePedidoProveedor.setIdPedPedidoProveedor(pedPedidoProveedor);
                    pedDetallePedidoProveedorListNewPedDetallePedidoProveedor = em.merge(pedDetallePedidoProveedorListNewPedDetallePedidoProveedor);
                    if (oldIdPedPedidoProveedorOfPedDetallePedidoProveedorListNewPedDetallePedidoProveedor != null && !oldIdPedPedidoProveedorOfPedDetallePedidoProveedorListNewPedDetallePedidoProveedor.equals(pedPedidoProveedor)) {
                        oldIdPedPedidoProveedorOfPedDetallePedidoProveedorListNewPedDetallePedidoProveedor.getPedDetallePedidoProveedorList().remove(pedDetallePedidoProveedorListNewPedDetallePedidoProveedor);
                        oldIdPedPedidoProveedorOfPedDetallePedidoProveedorListNewPedDetallePedidoProveedor = em.merge(oldIdPedPedidoProveedorOfPedDetallePedidoProveedorListNewPedDetallePedidoProveedor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pedPedidoProveedor.getId();
                if (findPedPedidoProveedor(id) == null) {
                    throw new NonexistentEntityException("The pedPedidoProveedor with id " + id + " no longer exists.");
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
            PedPedidoProveedor pedPedidoProveedor;
            try {
                pedPedidoProveedor = em.getReference(PedPedidoProveedor.class, id);
                pedPedidoProveedor.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pedPedidoProveedor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<PedPagosPedidoProveedor> pedPagosPedidoProveedorListOrphanCheck = pedPedidoProveedor.getPedPagosPedidoProveedorList();
            for (PedPagosPedidoProveedor pedPagosPedidoProveedorListOrphanCheckPedPagosPedidoProveedor : pedPagosPedidoProveedorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PedPedidoProveedor (" + pedPedidoProveedor + ") cannot be destroyed since the PedPagosPedidoProveedor " + pedPagosPedidoProveedorListOrphanCheckPedPagosPedidoProveedor + " in its pedPagosPedidoProveedorList field has a non-nullable idPedPedidoProveedor field.");
            }
            List<PedDetallePedidoProveedor> pedDetallePedidoProveedorListOrphanCheck = pedPedidoProveedor.getPedDetallePedidoProveedorList();
            for (PedDetallePedidoProveedor pedDetallePedidoProveedorListOrphanCheckPedDetallePedidoProveedor : pedDetallePedidoProveedorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PedPedidoProveedor (" + pedPedidoProveedor + ") cannot be destroyed since the PedDetallePedidoProveedor " + pedDetallePedidoProveedorListOrphanCheckPedDetallePedidoProveedor + " in its pedDetallePedidoProveedorList field has a non-nullable idPedPedidoProveedor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CatStatusPedido idCatStatusPedido = pedPedidoProveedor.getIdCatStatusPedido();
            if (idCatStatusPedido != null) {
                idCatStatusPedido.getPedPedidoProveedorList().remove(pedPedidoProveedor);
                idCatStatusPedido = em.merge(idCatStatusPedido);
            }
            em.remove(pedPedidoProveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<PedPedidoProveedor> findPedPedidoProveedorEntities() {
        return findPedPedidoProveedorEntities(true, -1, -1);
    }

    @Override
    public List<PedPedidoProveedor> findPedPedidoProveedorEntities(int maxResults, int firstResult) {
        return findPedPedidoProveedorEntities(false, maxResults, firstResult);
    }

    private List<PedPedidoProveedor> findPedPedidoProveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PedPedidoProveedor.class));
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
    public PedPedidoProveedor findPedPedidoProveedor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PedPedidoProveedor.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getPedPedidoProveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PedPedidoProveedor> rt = cq.from(PedPedidoProveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
