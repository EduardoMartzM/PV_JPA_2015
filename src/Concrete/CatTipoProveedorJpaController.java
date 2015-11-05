/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concrete;

import Abstract.ICatTipoProveedor;
import Concrete.exceptions.NonexistentEntityException;
import Concrete.exceptions.PreexistingEntityException;
import Model.CatTipoProveedor;
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
public class CatTipoProveedorJpaController implements Serializable, ICatTipoProveedor {

    public CatTipoProveedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public CatTipoProveedorJpaController() {
        this.emf = Persistence.createEntityManagerFactory("PuntoVentaJPA_2015PU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(CatTipoProveedor catTipoProveedor) throws PreexistingEntityException, Exception {
        if (catTipoProveedor.getProProveedorList() == null) {
            catTipoProveedor.setProProveedorList(new ArrayList<ProProveedor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<ProProveedor> attachedProProveedorList = new ArrayList<ProProveedor>();
            for (ProProveedor proProveedorListProProveedorToAttach : catTipoProveedor.getProProveedorList()) {
                proProveedorListProProveedorToAttach = em.getReference(proProveedorListProProveedorToAttach.getClass(), proProveedorListProProveedorToAttach.getId());
                attachedProProveedorList.add(proProveedorListProProveedorToAttach);
            }
            catTipoProveedor.setProProveedorList(attachedProProveedorList);
            em.persist(catTipoProveedor);
            for (ProProveedor proProveedorListProProveedor : catTipoProveedor.getProProveedorList()) {
                CatTipoProveedor oldIdCatTipoProveedorOfProProveedorListProProveedor = proProveedorListProProveedor.getIdCatTipoProveedor();
                proProveedorListProProveedor.setIdCatTipoProveedor(catTipoProveedor);
                proProveedorListProProveedor = em.merge(proProveedorListProProveedor);
                if (oldIdCatTipoProveedorOfProProveedorListProProveedor != null) {
                    oldIdCatTipoProveedorOfProProveedorListProProveedor.getProProveedorList().remove(proProveedorListProProveedor);
                    oldIdCatTipoProveedorOfProProveedorListProProveedor = em.merge(oldIdCatTipoProveedorOfProProveedorListProProveedor);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCatTipoProveedor(catTipoProveedor.getId()) != null) {
                throw new PreexistingEntityException("CatTipoProveedor " + catTipoProveedor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(CatTipoProveedor catTipoProveedor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CatTipoProveedor persistentCatTipoProveedor = em.find(CatTipoProveedor.class, catTipoProveedor.getId());
            List<ProProveedor> proProveedorListOld = persistentCatTipoProveedor.getProProveedorList();
            List<ProProveedor> proProveedorListNew = catTipoProveedor.getProProveedorList();
            List<ProProveedor> attachedProProveedorListNew = new ArrayList<ProProveedor>();
            for (ProProveedor proProveedorListNewProProveedorToAttach : proProveedorListNew) {
                proProveedorListNewProProveedorToAttach = em.getReference(proProveedorListNewProProveedorToAttach.getClass(), proProveedorListNewProProveedorToAttach.getId());
                attachedProProveedorListNew.add(proProveedorListNewProProveedorToAttach);
            }
            proProveedorListNew = attachedProProveedorListNew;
            catTipoProveedor.setProProveedorList(proProveedorListNew);
            catTipoProveedor = em.merge(catTipoProveedor);
            for (ProProveedor proProveedorListOldProProveedor : proProveedorListOld) {
                if (!proProveedorListNew.contains(proProveedorListOldProProveedor)) {
                    proProveedorListOldProProveedor.setIdCatTipoProveedor(null);
                    proProveedorListOldProProveedor = em.merge(proProveedorListOldProProveedor);
                }
            }
            for (ProProveedor proProveedorListNewProProveedor : proProveedorListNew) {
                if (!proProveedorListOld.contains(proProveedorListNewProProveedor)) {
                    CatTipoProveedor oldIdCatTipoProveedorOfProProveedorListNewProProveedor = proProveedorListNewProProveedor.getIdCatTipoProveedor();
                    proProveedorListNewProProveedor.setIdCatTipoProveedor(catTipoProveedor);
                    proProveedorListNewProProveedor = em.merge(proProveedorListNewProProveedor);
                    if (oldIdCatTipoProveedorOfProProveedorListNewProProveedor != null && !oldIdCatTipoProveedorOfProProveedorListNewProProveedor.equals(catTipoProveedor)) {
                        oldIdCatTipoProveedorOfProProveedorListNewProProveedor.getProProveedorList().remove(proProveedorListNewProProveedor);
                        oldIdCatTipoProveedorOfProProveedorListNewProProveedor = em.merge(oldIdCatTipoProveedorOfProProveedorListNewProProveedor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = catTipoProveedor.getId();
                if (findCatTipoProveedor(id) == null) {
                    throw new NonexistentEntityException("The catTipoProveedor with id " + id + " no longer exists.");
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
            CatTipoProveedor catTipoProveedor;
            try {
                catTipoProveedor = em.getReference(CatTipoProveedor.class, id);
                catTipoProveedor.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The catTipoProveedor with id " + id + " no longer exists.", enfe);
            }
            List<ProProveedor> proProveedorList = catTipoProveedor.getProProveedorList();
            for (ProProveedor proProveedorListProProveedor : proProveedorList) {
                proProveedorListProProveedor.setIdCatTipoProveedor(null);
                proProveedorListProProveedor = em.merge(proProveedorListProProveedor);
            }
            em.remove(catTipoProveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<CatTipoProveedor> findCatTipoProveedorEntities() {
        return findCatTipoProveedorEntities(true, -1, -1);
    }

    @Override
    public List<CatTipoProveedor> findCatTipoProveedorEntities(int maxResults, int firstResult) {
        return findCatTipoProveedorEntities(false, maxResults, firstResult);
    }

    private List<CatTipoProveedor> findCatTipoProveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CatTipoProveedor.class));
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
    public CatTipoProveedor findCatTipoProveedor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CatTipoProveedor.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getCatTipoProveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CatTipoProveedor> rt = cq.from(CatTipoProveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
