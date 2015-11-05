/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concrete;

import Abstract.ICatTipoProducto;
import Concrete.exceptions.NonexistentEntityException;
import Concrete.exceptions.PreexistingEntityException;
import Model.CatTipoProducto;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Model.ProProducto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author chirr
 */
public class CatTipoProductoJpaController implements Serializable, ICatTipoProducto {

    public CatTipoProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public CatTipoProductoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("PuntoVentaJPA_2015PU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(CatTipoProducto catTipoProducto) throws PreexistingEntityException, Exception {
        if (catTipoProducto.getProProductoList() == null) {
            catTipoProducto.setProProductoList(new ArrayList<ProProducto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<ProProducto> attachedProProductoList = new ArrayList<ProProducto>();
            for (ProProducto proProductoListProProductoToAttach : catTipoProducto.getProProductoList()) {
                proProductoListProProductoToAttach = em.getReference(proProductoListProProductoToAttach.getClass(), proProductoListProProductoToAttach.getId());
                attachedProProductoList.add(proProductoListProProductoToAttach);
            }
            catTipoProducto.setProProductoList(attachedProProductoList);
            em.persist(catTipoProducto);
            for (ProProducto proProductoListProProducto : catTipoProducto.getProProductoList()) {
                CatTipoProducto oldIdCategoriaOfProProductoListProProducto = proProductoListProProducto.getIdCategoria();
                proProductoListProProducto.setIdCategoria(catTipoProducto);
                proProductoListProProducto = em.merge(proProductoListProProducto);
                if (oldIdCategoriaOfProProductoListProProducto != null) {
                    oldIdCategoriaOfProProductoListProProducto.getProProductoList().remove(proProductoListProProducto);
                    oldIdCategoriaOfProProductoListProProducto = em.merge(oldIdCategoriaOfProProductoListProProducto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCatTipoProducto(catTipoProducto.getId()) != null) {
                throw new PreexistingEntityException("CatTipoProducto " + catTipoProducto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(CatTipoProducto catTipoProducto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CatTipoProducto persistentCatTipoProducto = em.find(CatTipoProducto.class, catTipoProducto.getId());
            List<ProProducto> proProductoListOld = persistentCatTipoProducto.getProProductoList();
            List<ProProducto> proProductoListNew = catTipoProducto.getProProductoList();
            List<ProProducto> attachedProProductoListNew = new ArrayList<ProProducto>();
            for (ProProducto proProductoListNewProProductoToAttach : proProductoListNew) {
                proProductoListNewProProductoToAttach = em.getReference(proProductoListNewProProductoToAttach.getClass(), proProductoListNewProProductoToAttach.getId());
                attachedProProductoListNew.add(proProductoListNewProProductoToAttach);
            }
            proProductoListNew = attachedProProductoListNew;
            catTipoProducto.setProProductoList(proProductoListNew);
            catTipoProducto = em.merge(catTipoProducto);
            for (ProProducto proProductoListOldProProducto : proProductoListOld) {
                if (!proProductoListNew.contains(proProductoListOldProProducto)) {
                    proProductoListOldProProducto.setIdCategoria(null);
                    proProductoListOldProProducto = em.merge(proProductoListOldProProducto);
                }
            }
            for (ProProducto proProductoListNewProProducto : proProductoListNew) {
                if (!proProductoListOld.contains(proProductoListNewProProducto)) {
                    CatTipoProducto oldIdCategoriaOfProProductoListNewProProducto = proProductoListNewProProducto.getIdCategoria();
                    proProductoListNewProProducto.setIdCategoria(catTipoProducto);
                    proProductoListNewProProducto = em.merge(proProductoListNewProProducto);
                    if (oldIdCategoriaOfProProductoListNewProProducto != null && !oldIdCategoriaOfProProductoListNewProProducto.equals(catTipoProducto)) {
                        oldIdCategoriaOfProProductoListNewProProducto.getProProductoList().remove(proProductoListNewProProducto);
                        oldIdCategoriaOfProProductoListNewProProducto = em.merge(oldIdCategoriaOfProProductoListNewProProducto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = catTipoProducto.getId();
                if (findCatTipoProducto(id) == null) {
                    throw new NonexistentEntityException("The catTipoProducto with id " + id + " no longer exists.");
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
            CatTipoProducto catTipoProducto;
            try {
                catTipoProducto = em.getReference(CatTipoProducto.class, id);
                catTipoProducto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The catTipoProducto with id " + id + " no longer exists.", enfe);
            }
            List<ProProducto> proProductoList = catTipoProducto.getProProductoList();
            for (ProProducto proProductoListProProducto : proProductoList) {
                proProductoListProProducto.setIdCategoria(null);
                proProductoListProProducto = em.merge(proProductoListProProducto);
            }
            em.remove(catTipoProducto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<CatTipoProducto> findCatTipoProductoEntities() {
        return findCatTipoProductoEntities(true, -1, -1);
    }

    @Override
    public List<CatTipoProducto> findCatTipoProductoEntities(int maxResults, int firstResult) {
        return findCatTipoProductoEntities(false, maxResults, firstResult);
    }

    private List<CatTipoProducto> findCatTipoProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CatTipoProducto.class));
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
    public CatTipoProducto findCatTipoProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CatTipoProducto.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getCatTipoProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CatTipoProducto> rt = cq.from(CatTipoProducto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
