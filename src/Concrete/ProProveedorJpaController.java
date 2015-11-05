/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concrete;

import Abstract.IProProveedor;
import Concrete.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Model.CatTipoProveedor;
import Model.ComDatoContacto;
import Model.ComDireccion;
import Model.ProCatStatusProveedor;
import Model.ProProducto;
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
public class ProProveedorJpaController implements Serializable, IProProveedor {

    public ProProveedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public ProProveedorJpaController() {
        this.emf = Persistence.createEntityManagerFactory("PuntoVentaJPA_2015PU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(ProProveedor proProveedor) {
        if (proProveedor.getProProductoList() == null) {
            proProveedor.setProProductoList(new ArrayList<ProProducto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CatTipoProveedor idCatTipoProveedor = proProveedor.getIdCatTipoProveedor();
            if (idCatTipoProveedor != null) {
                idCatTipoProveedor = em.getReference(idCatTipoProveedor.getClass(), idCatTipoProveedor.getId());
                proProveedor.setIdCatTipoProveedor(idCatTipoProveedor);
            }
            ComDatoContacto idComDatoContacto = proProveedor.getIdComDatoContacto();
            if (idComDatoContacto != null) {
                idComDatoContacto = em.getReference(idComDatoContacto.getClass(), idComDatoContacto.getId());
                proProveedor.setIdComDatoContacto(idComDatoContacto);
            }
            ComDireccion idComDireccion = proProveedor.getIdComDireccion();
            if (idComDireccion != null) {
                idComDireccion = em.getReference(idComDireccion.getClass(), idComDireccion.getId());
                proProveedor.setIdComDireccion(idComDireccion);
            }
            ProCatStatusProveedor idProCatStatusProveedor = proProveedor.getIdProCatStatusProveedor();
            if (idProCatStatusProveedor != null) {
                idProCatStatusProveedor = em.getReference(idProCatStatusProveedor.getClass(), idProCatStatusProveedor.getId());
                proProveedor.setIdProCatStatusProveedor(idProCatStatusProveedor);
            }
            List<ProProducto> attachedProProductoList = new ArrayList<ProProducto>();
            for (ProProducto proProductoListProProductoToAttach : proProveedor.getProProductoList()) {
                proProductoListProProductoToAttach = em.getReference(proProductoListProProductoToAttach.getClass(), proProductoListProProductoToAttach.getId());
                attachedProProductoList.add(proProductoListProProductoToAttach);
            }
            proProveedor.setProProductoList(attachedProProductoList);
            em.persist(proProveedor);
            if (idCatTipoProveedor != null) {
                idCatTipoProveedor.getProProveedorList().add(proProveedor);
                idCatTipoProveedor = em.merge(idCatTipoProveedor);
            }
            if (idComDatoContacto != null) {
                idComDatoContacto.getProProveedorList().add(proProveedor);
                idComDatoContacto = em.merge(idComDatoContacto);
            }
            if (idComDireccion != null) {
                idComDireccion.getProProveedorList().add(proProveedor);
                idComDireccion = em.merge(idComDireccion);
            }
            if (idProCatStatusProveedor != null) {
                idProCatStatusProveedor.getProProveedorList().add(proProveedor);
                idProCatStatusProveedor = em.merge(idProCatStatusProveedor);
            }
            for (ProProducto proProductoListProProducto : proProveedor.getProProductoList()) {
                ProProveedor oldIdProveedorOfProProductoListProProducto = proProductoListProProducto.getIdProveedor();
                proProductoListProProducto.setIdProveedor(proProveedor);
                proProductoListProProducto = em.merge(proProductoListProProducto);
                if (oldIdProveedorOfProProductoListProProducto != null) {
                    oldIdProveedorOfProProductoListProProducto.getProProductoList().remove(proProductoListProProducto);
                    oldIdProveedorOfProProductoListProProducto = em.merge(oldIdProveedorOfProProductoListProProducto);
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
    public void edit(ProProveedor proProveedor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProProveedor persistentProProveedor = em.find(ProProveedor.class, proProveedor.getId());
            CatTipoProveedor idCatTipoProveedorOld = persistentProProveedor.getIdCatTipoProveedor();
            CatTipoProveedor idCatTipoProveedorNew = proProveedor.getIdCatTipoProveedor();
            ComDatoContacto idComDatoContactoOld = persistentProProveedor.getIdComDatoContacto();
            ComDatoContacto idComDatoContactoNew = proProveedor.getIdComDatoContacto();
            ComDireccion idComDireccionOld = persistentProProveedor.getIdComDireccion();
            ComDireccion idComDireccionNew = proProveedor.getIdComDireccion();
            ProCatStatusProveedor idProCatStatusProveedorOld = persistentProProveedor.getIdProCatStatusProveedor();
            ProCatStatusProveedor idProCatStatusProveedorNew = proProveedor.getIdProCatStatusProveedor();
            List<ProProducto> proProductoListOld = persistentProProveedor.getProProductoList();
            List<ProProducto> proProductoListNew = proProveedor.getProProductoList();
            if (idCatTipoProveedorNew != null) {
                idCatTipoProveedorNew = em.getReference(idCatTipoProveedorNew.getClass(), idCatTipoProveedorNew.getId());
                proProveedor.setIdCatTipoProveedor(idCatTipoProveedorNew);
            }
            if (idComDatoContactoNew != null) {
                idComDatoContactoNew = em.getReference(idComDatoContactoNew.getClass(), idComDatoContactoNew.getId());
                proProveedor.setIdComDatoContacto(idComDatoContactoNew);
            }
            if (idComDireccionNew != null) {
                idComDireccionNew = em.getReference(idComDireccionNew.getClass(), idComDireccionNew.getId());
                proProveedor.setIdComDireccion(idComDireccionNew);
            }
            if (idProCatStatusProveedorNew != null) {
                idProCatStatusProveedorNew = em.getReference(idProCatStatusProveedorNew.getClass(), idProCatStatusProveedorNew.getId());
                proProveedor.setIdProCatStatusProveedor(idProCatStatusProveedorNew);
            }
            List<ProProducto> attachedProProductoListNew = new ArrayList<ProProducto>();
            for (ProProducto proProductoListNewProProductoToAttach : proProductoListNew) {
                proProductoListNewProProductoToAttach = em.getReference(proProductoListNewProProductoToAttach.getClass(), proProductoListNewProProductoToAttach.getId());
                attachedProProductoListNew.add(proProductoListNewProProductoToAttach);
            }
            proProductoListNew = attachedProProductoListNew;
            proProveedor.setProProductoList(proProductoListNew);
            proProveedor = em.merge(proProveedor);
            if (idCatTipoProveedorOld != null && !idCatTipoProveedorOld.equals(idCatTipoProveedorNew)) {
                idCatTipoProveedorOld.getProProveedorList().remove(proProveedor);
                idCatTipoProveedorOld = em.merge(idCatTipoProveedorOld);
            }
            if (idCatTipoProveedorNew != null && !idCatTipoProveedorNew.equals(idCatTipoProveedorOld)) {
                idCatTipoProveedorNew.getProProveedorList().add(proProveedor);
                idCatTipoProveedorNew = em.merge(idCatTipoProveedorNew);
            }
            if (idComDatoContactoOld != null && !idComDatoContactoOld.equals(idComDatoContactoNew)) {
                idComDatoContactoOld.getProProveedorList().remove(proProveedor);
                idComDatoContactoOld = em.merge(idComDatoContactoOld);
            }
            if (idComDatoContactoNew != null && !idComDatoContactoNew.equals(idComDatoContactoOld)) {
                idComDatoContactoNew.getProProveedorList().add(proProveedor);
                idComDatoContactoNew = em.merge(idComDatoContactoNew);
            }
            if (idComDireccionOld != null && !idComDireccionOld.equals(idComDireccionNew)) {
                idComDireccionOld.getProProveedorList().remove(proProveedor);
                idComDireccionOld = em.merge(idComDireccionOld);
            }
            if (idComDireccionNew != null && !idComDireccionNew.equals(idComDireccionOld)) {
                idComDireccionNew.getProProveedorList().add(proProveedor);
                idComDireccionNew = em.merge(idComDireccionNew);
            }
            if (idProCatStatusProveedorOld != null && !idProCatStatusProveedorOld.equals(idProCatStatusProveedorNew)) {
                idProCatStatusProveedorOld.getProProveedorList().remove(proProveedor);
                idProCatStatusProveedorOld = em.merge(idProCatStatusProveedorOld);
            }
            if (idProCatStatusProveedorNew != null && !idProCatStatusProveedorNew.equals(idProCatStatusProveedorOld)) {
                idProCatStatusProveedorNew.getProProveedorList().add(proProveedor);
                idProCatStatusProveedorNew = em.merge(idProCatStatusProveedorNew);
            }
            for (ProProducto proProductoListOldProProducto : proProductoListOld) {
                if (!proProductoListNew.contains(proProductoListOldProProducto)) {
                    proProductoListOldProProducto.setIdProveedor(null);
                    proProductoListOldProProducto = em.merge(proProductoListOldProProducto);
                }
            }
            for (ProProducto proProductoListNewProProducto : proProductoListNew) {
                if (!proProductoListOld.contains(proProductoListNewProProducto)) {
                    ProProveedor oldIdProveedorOfProProductoListNewProProducto = proProductoListNewProProducto.getIdProveedor();
                    proProductoListNewProProducto.setIdProveedor(proProveedor);
                    proProductoListNewProProducto = em.merge(proProductoListNewProProducto);
                    if (oldIdProveedorOfProProductoListNewProProducto != null && !oldIdProveedorOfProProductoListNewProProducto.equals(proProveedor)) {
                        oldIdProveedorOfProProductoListNewProProducto.getProProductoList().remove(proProductoListNewProProducto);
                        oldIdProveedorOfProProductoListNewProProducto = em.merge(oldIdProveedorOfProProductoListNewProProducto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = proProveedor.getId();
                if (findProProveedor(id) == null) {
                    throw new NonexistentEntityException("The proProveedor with id " + id + " no longer exists.");
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
            ProProveedor proProveedor;
            try {
                proProveedor = em.getReference(ProProveedor.class, id);
                proProveedor.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proProveedor with id " + id + " no longer exists.", enfe);
            }
            CatTipoProveedor idCatTipoProveedor = proProveedor.getIdCatTipoProveedor();
            if (idCatTipoProveedor != null) {
                idCatTipoProveedor.getProProveedorList().remove(proProveedor);
                idCatTipoProveedor = em.merge(idCatTipoProveedor);
            }
            ComDatoContacto idComDatoContacto = proProveedor.getIdComDatoContacto();
            if (idComDatoContacto != null) {
                idComDatoContacto.getProProveedorList().remove(proProveedor);
                idComDatoContacto = em.merge(idComDatoContacto);
            }
            ComDireccion idComDireccion = proProveedor.getIdComDireccion();
            if (idComDireccion != null) {
                idComDireccion.getProProveedorList().remove(proProveedor);
                idComDireccion = em.merge(idComDireccion);
            }
            ProCatStatusProveedor idProCatStatusProveedor = proProveedor.getIdProCatStatusProveedor();
            if (idProCatStatusProveedor != null) {
                idProCatStatusProveedor.getProProveedorList().remove(proProveedor);
                idProCatStatusProveedor = em.merge(idProCatStatusProveedor);
            }
            List<ProProducto> proProductoList = proProveedor.getProProductoList();
            for (ProProducto proProductoListProProducto : proProductoList) {
                proProductoListProProducto.setIdProveedor(null);
                proProductoListProProducto = em.merge(proProductoListProProducto);
            }
            em.remove(proProveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<ProProveedor> findProProveedorEntities() {
        return findProProveedorEntities(true, -1, -1);
    }

    @Override
    public List<ProProveedor> findProProveedorEntities(int maxResults, int firstResult) {
        return findProProveedorEntities(false, maxResults, firstResult);
    }

    private List<ProProveedor> findProProveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProProveedor.class));
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
    public ProProveedor findProProveedor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProProveedor.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getProProveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProProveedor> rt = cq.from(ProProveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
