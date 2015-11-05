/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concrete;

import Abstract.IComDireccion;
import Concrete.exceptions.NonexistentEntityException;
import Model.ComDireccion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Model.ComEstadoDireccion;
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
public class ComDireccionJpaController implements Serializable, IComDireccion {

    public ComDireccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public ComDireccionJpaController() {
        this.emf = Persistence.createEntityManagerFactory("PuntoVentaJPA_2015PU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(ComDireccion comDireccion) {
        if (comDireccion.getProProveedorList() == null) {
            comDireccion.setProProveedorList(new ArrayList<ProProveedor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ComEstadoDireccion idEstado = comDireccion.getIdEstado();
            if (idEstado != null) {
                idEstado = em.getReference(idEstado.getClass(), idEstado.getId());
                comDireccion.setIdEstado(idEstado);
            }
            List<ProProveedor> attachedProProveedorList = new ArrayList<ProProveedor>();
            for (ProProveedor proProveedorListProProveedorToAttach : comDireccion.getProProveedorList()) {
                proProveedorListProProveedorToAttach = em.getReference(proProveedorListProProveedorToAttach.getClass(), proProveedorListProProveedorToAttach.getId());
                attachedProProveedorList.add(proProveedorListProProveedorToAttach);
            }
            comDireccion.setProProveedorList(attachedProProveedorList);
            em.persist(comDireccion);
            if (idEstado != null) {
                idEstado.getComDireccionList().add(comDireccion);
                idEstado = em.merge(idEstado);
            }
            for (ProProveedor proProveedorListProProveedor : comDireccion.getProProveedorList()) {
                ComDireccion oldIdComDireccionOfProProveedorListProProveedor = proProveedorListProProveedor.getIdComDireccion();
                proProveedorListProProveedor.setIdComDireccion(comDireccion);
                proProveedorListProProveedor = em.merge(proProveedorListProProveedor);
                if (oldIdComDireccionOfProProveedorListProProveedor != null) {
                    oldIdComDireccionOfProProveedorListProProveedor.getProProveedorList().remove(proProveedorListProProveedor);
                    oldIdComDireccionOfProProveedorListProProveedor = em.merge(oldIdComDireccionOfProProveedorListProProveedor);
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
    public void edit(ComDireccion comDireccion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ComDireccion persistentComDireccion = em.find(ComDireccion.class, comDireccion.getId());
            ComEstadoDireccion idEstadoOld = persistentComDireccion.getIdEstado();
            ComEstadoDireccion idEstadoNew = comDireccion.getIdEstado();
            List<ProProveedor> proProveedorListOld = persistentComDireccion.getProProveedorList();
            List<ProProveedor> proProveedorListNew = comDireccion.getProProveedorList();
            if (idEstadoNew != null) {
                idEstadoNew = em.getReference(idEstadoNew.getClass(), idEstadoNew.getId());
                comDireccion.setIdEstado(idEstadoNew);
            }
            List<ProProveedor> attachedProProveedorListNew = new ArrayList<ProProveedor>();
            for (ProProveedor proProveedorListNewProProveedorToAttach : proProveedorListNew) {
                proProveedorListNewProProveedorToAttach = em.getReference(proProveedorListNewProProveedorToAttach.getClass(), proProveedorListNewProProveedorToAttach.getId());
                attachedProProveedorListNew.add(proProveedorListNewProProveedorToAttach);
            }
            proProveedorListNew = attachedProProveedorListNew;
            comDireccion.setProProveedorList(proProveedorListNew);
            comDireccion = em.merge(comDireccion);
            if (idEstadoOld != null && !idEstadoOld.equals(idEstadoNew)) {
                idEstadoOld.getComDireccionList().remove(comDireccion);
                idEstadoOld = em.merge(idEstadoOld);
            }
            if (idEstadoNew != null && !idEstadoNew.equals(idEstadoOld)) {
                idEstadoNew.getComDireccionList().add(comDireccion);
                idEstadoNew = em.merge(idEstadoNew);
            }
            for (ProProveedor proProveedorListOldProProveedor : proProveedorListOld) {
                if (!proProveedorListNew.contains(proProveedorListOldProProveedor)) {
                    proProveedorListOldProProveedor.setIdComDireccion(null);
                    proProveedorListOldProProveedor = em.merge(proProveedorListOldProProveedor);
                }
            }
            for (ProProveedor proProveedorListNewProProveedor : proProveedorListNew) {
                if (!proProveedorListOld.contains(proProveedorListNewProProveedor)) {
                    ComDireccion oldIdComDireccionOfProProveedorListNewProProveedor = proProveedorListNewProProveedor.getIdComDireccion();
                    proProveedorListNewProProveedor.setIdComDireccion(comDireccion);
                    proProveedorListNewProProveedor = em.merge(proProveedorListNewProProveedor);
                    if (oldIdComDireccionOfProProveedorListNewProProveedor != null && !oldIdComDireccionOfProProveedorListNewProProveedor.equals(comDireccion)) {
                        oldIdComDireccionOfProProveedorListNewProProveedor.getProProveedorList().remove(proProveedorListNewProProveedor);
                        oldIdComDireccionOfProProveedorListNewProProveedor = em.merge(oldIdComDireccionOfProProveedorListNewProProveedor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = comDireccion.getId();
                if (findComDireccion(id) == null) {
                    throw new NonexistentEntityException("The comDireccion with id " + id + " no longer exists.");
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
            ComDireccion comDireccion;
            try {
                comDireccion = em.getReference(ComDireccion.class, id);
                comDireccion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The comDireccion with id " + id + " no longer exists.", enfe);
            }
            ComEstadoDireccion idEstado = comDireccion.getIdEstado();
            if (idEstado != null) {
                idEstado.getComDireccionList().remove(comDireccion);
                idEstado = em.merge(idEstado);
            }
            List<ProProveedor> proProveedorList = comDireccion.getProProveedorList();
            for (ProProveedor proProveedorListProProveedor : proProveedorList) {
                proProveedorListProProveedor.setIdComDireccion(null);
                proProveedorListProProveedor = em.merge(proProveedorListProProveedor);
            }
            em.remove(comDireccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<ComDireccion> findComDireccionEntities() {
        return findComDireccionEntities(true, -1, -1);
    }

    @Override
    public List<ComDireccion> findComDireccionEntities(int maxResults, int firstResult) {
        return findComDireccionEntities(false, maxResults, firstResult);
    }

    private List<ComDireccion> findComDireccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ComDireccion.class));
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
    public ComDireccion findComDireccion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ComDireccion.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getComDireccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ComDireccion> rt = cq.from(ComDireccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
