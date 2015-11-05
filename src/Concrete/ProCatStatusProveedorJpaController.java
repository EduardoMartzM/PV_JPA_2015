/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concrete;

import Abstract.IProCatStatusProveedor;
import Concrete.exceptions.NonexistentEntityException;
import Concrete.exceptions.PreexistingEntityException;
import Model.ProCatStatusProveedor;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Model.ProProveedor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author chirr
 */
public class ProCatStatusProveedorJpaController implements Serializable, IProCatStatusProveedor {

    public ProCatStatusProveedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public ProCatStatusProveedorJpaController() {
        this.emf = Persistence.createEntityManagerFactory("PuntoVentaJPA_2015PU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(ProCatStatusProveedor proCatStatusProveedor) throws PreexistingEntityException, Exception {
        if (proCatStatusProveedor.getProProveedorList() == null) {
            proCatStatusProveedor.setProProveedorList(new ArrayList<ProProveedor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<ProProveedor> attachedProProveedorList = new ArrayList<ProProveedor>();
            for (ProProveedor proProveedorListProProveedorToAttach : proCatStatusProveedor.getProProveedorList()) {
                proProveedorListProProveedorToAttach = em.getReference(proProveedorListProProveedorToAttach.getClass(), proProveedorListProProveedorToAttach.getId());
                attachedProProveedorList.add(proProveedorListProProveedorToAttach);
            }
            proCatStatusProveedor.setProProveedorList(attachedProProveedorList);
            em.persist(proCatStatusProveedor);
            for (ProProveedor proProveedorListProProveedor : proCatStatusProveedor.getProProveedorList()) {
                ProCatStatusProveedor oldIdProCatStatusProveedorOfProProveedorListProProveedor = proProveedorListProProveedor.getIdProCatStatusProveedor();
                proProveedorListProProveedor.setIdProCatStatusProveedor(proCatStatusProveedor);
                proProveedorListProProveedor = em.merge(proProveedorListProProveedor);
                if (oldIdProCatStatusProveedorOfProProveedorListProProveedor != null) {
                    oldIdProCatStatusProveedorOfProProveedorListProProveedor.getProProveedorList().remove(proProveedorListProProveedor);
                    oldIdProCatStatusProveedorOfProProveedorListProProveedor = em.merge(oldIdProCatStatusProveedorOfProProveedorListProProveedor);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProCatStatusProveedor(proCatStatusProveedor.getId()) != null) {
                throw new PreexistingEntityException("ProCatStatusProveedor " + proCatStatusProveedor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(ProCatStatusProveedor proCatStatusProveedor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProCatStatusProveedor persistentProCatStatusProveedor = em.find(ProCatStatusProveedor.class, proCatStatusProveedor.getId());
            List<ProProveedor> proProveedorListOld = persistentProCatStatusProveedor.getProProveedorList();
            List<ProProveedor> proProveedorListNew = proCatStatusProveedor.getProProveedorList();
            List<ProProveedor> attachedProProveedorListNew = new ArrayList<ProProveedor>();
            for (ProProveedor proProveedorListNewProProveedorToAttach : proProveedorListNew) {
                proProveedorListNewProProveedorToAttach = em.getReference(proProveedorListNewProProveedorToAttach.getClass(), proProveedorListNewProProveedorToAttach.getId());
                attachedProProveedorListNew.add(proProveedorListNewProProveedorToAttach);
            }
            proProveedorListNew = attachedProProveedorListNew;
            proCatStatusProveedor.setProProveedorList(proProveedorListNew);
            proCatStatusProveedor = em.merge(proCatStatusProveedor);
            for (ProProveedor proProveedorListOldProProveedor : proProveedorListOld) {
                if (!proProveedorListNew.contains(proProveedorListOldProProveedor)) {
                    proProveedorListOldProProveedor.setIdProCatStatusProveedor(null);
                    proProveedorListOldProProveedor = em.merge(proProveedorListOldProProveedor);
                }
            }
            for (ProProveedor proProveedorListNewProProveedor : proProveedorListNew) {
                if (!proProveedorListOld.contains(proProveedorListNewProProveedor)) {
                    ProCatStatusProveedor oldIdProCatStatusProveedorOfProProveedorListNewProProveedor = proProveedorListNewProProveedor.getIdProCatStatusProveedor();
                    proProveedorListNewProProveedor.setIdProCatStatusProveedor(proCatStatusProveedor);
                    proProveedorListNewProProveedor = em.merge(proProveedorListNewProProveedor);
                    if (oldIdProCatStatusProveedorOfProProveedorListNewProProveedor != null && !oldIdProCatStatusProveedorOfProProveedorListNewProProveedor.equals(proCatStatusProveedor)) {
                        oldIdProCatStatusProveedorOfProProveedorListNewProProveedor.getProProveedorList().remove(proProveedorListNewProProveedor);
                        oldIdProCatStatusProveedorOfProProveedorListNewProProveedor = em.merge(oldIdProCatStatusProveedorOfProProveedorListNewProProveedor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = proCatStatusProveedor.getId();
                if (findProCatStatusProveedor(id) == null) {
                    throw new NonexistentEntityException("The proCatStatusProveedor with id " + id + " no longer exists.");
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
            ProCatStatusProveedor proCatStatusProveedor;
            try {
                proCatStatusProveedor = em.getReference(ProCatStatusProveedor.class, id);
                proCatStatusProveedor.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proCatStatusProveedor with id " + id + " no longer exists.", enfe);
            }
            List<ProProveedor> proProveedorList = proCatStatusProveedor.getProProveedorList();
            for (ProProveedor proProveedorListProProveedor : proProveedorList) {
                proProveedorListProProveedor.setIdProCatStatusProveedor(null);
                proProveedorListProProveedor = em.merge(proProveedorListProProveedor);
            }
            em.remove(proCatStatusProveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<ProCatStatusProveedor> findProCatStatusProveedorEntities() {
        return findProCatStatusProveedorEntities(true, -1, -1);
    }

    @Override
    public List<ProCatStatusProveedor> findProCatStatusProveedorEntities(int maxResults, int firstResult) {
        return findProCatStatusProveedorEntities(false, maxResults, firstResult);
    }

    private List<ProCatStatusProveedor> findProCatStatusProveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProCatStatusProveedor.class));
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
    public ProCatStatusProveedor findProCatStatusProveedor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProCatStatusProveedor.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getProCatStatusProveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProCatStatusProveedor> rt = cq.from(ProCatStatusProveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
