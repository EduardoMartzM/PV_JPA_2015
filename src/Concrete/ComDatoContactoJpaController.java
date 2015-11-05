/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concrete;

import Abstract.IComDatoContacto;
import Concrete.exceptions.NonexistentEntityException;
import Model.ComDatoContacto;
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
public class ComDatoContactoJpaController implements Serializable, IComDatoContacto {

    public ComDatoContactoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public ComDatoContactoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("PuntoVentaJPA_2015PU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(ComDatoContacto comDatoContacto) {
        if (comDatoContacto.getProProveedorList() == null) {
            comDatoContacto.setProProveedorList(new ArrayList<ProProveedor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<ProProveedor> attachedProProveedorList = new ArrayList<ProProveedor>();
            for (ProProveedor proProveedorListProProveedorToAttach : comDatoContacto.getProProveedorList()) {
                proProveedorListProProveedorToAttach = em.getReference(proProveedorListProProveedorToAttach.getClass(), proProveedorListProProveedorToAttach.getId());
                attachedProProveedorList.add(proProveedorListProProveedorToAttach);
            }
            comDatoContacto.setProProveedorList(attachedProProveedorList);
            em.persist(comDatoContacto);
            for (ProProveedor proProveedorListProProveedor : comDatoContacto.getProProveedorList()) {
                ComDatoContacto oldIdComDatoContactoOfProProveedorListProProveedor = proProveedorListProProveedor.getIdComDatoContacto();
                proProveedorListProProveedor.setIdComDatoContacto(comDatoContacto);
                proProveedorListProProveedor = em.merge(proProveedorListProProveedor);
                if (oldIdComDatoContactoOfProProveedorListProProveedor != null) {
                    oldIdComDatoContactoOfProProveedorListProProveedor.getProProveedorList().remove(proProveedorListProProveedor);
                    oldIdComDatoContactoOfProProveedorListProProveedor = em.merge(oldIdComDatoContactoOfProProveedorListProProveedor);
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
    public void edit(ComDatoContacto comDatoContacto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ComDatoContacto persistentComDatoContacto = em.find(ComDatoContacto.class, comDatoContacto.getId());
            List<ProProveedor> proProveedorListOld = persistentComDatoContacto.getProProveedorList();
            List<ProProveedor> proProveedorListNew = comDatoContacto.getProProveedorList();
            List<ProProveedor> attachedProProveedorListNew = new ArrayList<ProProveedor>();
            for (ProProveedor proProveedorListNewProProveedorToAttach : proProveedorListNew) {
                proProveedorListNewProProveedorToAttach = em.getReference(proProveedorListNewProProveedorToAttach.getClass(), proProveedorListNewProProveedorToAttach.getId());
                attachedProProveedorListNew.add(proProveedorListNewProProveedorToAttach);
            }
            proProveedorListNew = attachedProProveedorListNew;
            comDatoContacto.setProProveedorList(proProveedorListNew);
            comDatoContacto = em.merge(comDatoContacto);
            for (ProProveedor proProveedorListOldProProveedor : proProveedorListOld) {
                if (!proProveedorListNew.contains(proProveedorListOldProProveedor)) {
                    proProveedorListOldProProveedor.setIdComDatoContacto(null);
                    proProveedorListOldProProveedor = em.merge(proProveedorListOldProProveedor);
                }
            }
            for (ProProveedor proProveedorListNewProProveedor : proProveedorListNew) {
                if (!proProveedorListOld.contains(proProveedorListNewProProveedor)) {
                    ComDatoContacto oldIdComDatoContactoOfProProveedorListNewProProveedor = proProveedorListNewProProveedor.getIdComDatoContacto();
                    proProveedorListNewProProveedor.setIdComDatoContacto(comDatoContacto);
                    proProveedorListNewProProveedor = em.merge(proProveedorListNewProProveedor);
                    if (oldIdComDatoContactoOfProProveedorListNewProProveedor != null && !oldIdComDatoContactoOfProProveedorListNewProProveedor.equals(comDatoContacto)) {
                        oldIdComDatoContactoOfProProveedorListNewProProveedor.getProProveedorList().remove(proProveedorListNewProProveedor);
                        oldIdComDatoContactoOfProProveedorListNewProProveedor = em.merge(oldIdComDatoContactoOfProProveedorListNewProProveedor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = comDatoContacto.getId();
                if (findComDatoContacto(id) == null) {
                    throw new NonexistentEntityException("The comDatoContacto with id " + id + " no longer exists.");
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
            ComDatoContacto comDatoContacto;
            try {
                comDatoContacto = em.getReference(ComDatoContacto.class, id);
                comDatoContacto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The comDatoContacto with id " + id + " no longer exists.", enfe);
            }
            List<ProProveedor> proProveedorList = comDatoContacto.getProProveedorList();
            for (ProProveedor proProveedorListProProveedor : proProveedorList) {
                proProveedorListProProveedor.setIdComDatoContacto(null);
                proProveedorListProProveedor = em.merge(proProveedorListProProveedor);
            }
            em.remove(comDatoContacto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<ComDatoContacto> findComDatoContactoEntities() {
        return findComDatoContactoEntities(true, -1, -1);
    }

    @Override
    public List<ComDatoContacto> findComDatoContactoEntities(int maxResults, int firstResult) {
        return findComDatoContactoEntities(false, maxResults, firstResult);
    }

    private List<ComDatoContacto> findComDatoContactoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ComDatoContacto.class));
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
    public ComDatoContacto findComDatoContacto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ComDatoContacto.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getComDatoContactoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ComDatoContacto> rt = cq.from(ComDatoContacto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
