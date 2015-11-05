/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concrete;

import Abstract.IProProducto;
import Concrete.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Model.CatTipoProducto;
import Model.ProProducto;
import Model.ProProveedor;
import Model.VenDetalleVenta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author chirr
 */
public class ProProductoJpaController implements Serializable, IProProducto {

    public ProProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public ProProductoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("PuntoVentaJPA_2015PU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(ProProducto proProducto) {
        if (proProducto.getVenDetalleVentaList() == null) {
            proProducto.setVenDetalleVentaList(new ArrayList<VenDetalleVenta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CatTipoProducto idCategoria = proProducto.getIdCategoria();
            if (idCategoria != null) {
                idCategoria = em.getReference(idCategoria.getClass(), idCategoria.getId());
                proProducto.setIdCategoria(idCategoria);
            }
            ProProveedor idProveedor = proProducto.getIdProveedor();
            if (idProveedor != null) {
                idProveedor = em.getReference(idProveedor.getClass(), idProveedor.getId());
                proProducto.setIdProveedor(idProveedor);
            }
            List<VenDetalleVenta> attachedVenDetalleVentaList = new ArrayList<VenDetalleVenta>();
            for (VenDetalleVenta venDetalleVentaListVenDetalleVentaToAttach : proProducto.getVenDetalleVentaList()) {
                venDetalleVentaListVenDetalleVentaToAttach = em.getReference(venDetalleVentaListVenDetalleVentaToAttach.getClass(), venDetalleVentaListVenDetalleVentaToAttach.getId());
                attachedVenDetalleVentaList.add(venDetalleVentaListVenDetalleVentaToAttach);
            }
            proProducto.setVenDetalleVentaList(attachedVenDetalleVentaList);
            em.persist(proProducto);
            if (idCategoria != null) {
                idCategoria.getProProductoList().add(proProducto);
                idCategoria = em.merge(idCategoria);
            }
            if (idProveedor != null) {
                idProveedor.getProProductoList().add(proProducto);
                idProveedor = em.merge(idProveedor);
            }
            for (VenDetalleVenta venDetalleVentaListVenDetalleVenta : proProducto.getVenDetalleVentaList()) {
                ProProducto oldIdProductoOfVenDetalleVentaListVenDetalleVenta = venDetalleVentaListVenDetalleVenta.getIdProducto();
                venDetalleVentaListVenDetalleVenta.setIdProducto(proProducto);
                venDetalleVentaListVenDetalleVenta = em.merge(venDetalleVentaListVenDetalleVenta);
                if (oldIdProductoOfVenDetalleVentaListVenDetalleVenta != null) {
                    oldIdProductoOfVenDetalleVentaListVenDetalleVenta.getVenDetalleVentaList().remove(venDetalleVentaListVenDetalleVenta);
                    oldIdProductoOfVenDetalleVentaListVenDetalleVenta = em.merge(oldIdProductoOfVenDetalleVentaListVenDetalleVenta);
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
    public void edit(ProProducto proProducto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProProducto persistentProProducto = em.find(ProProducto.class, proProducto.getId());
            CatTipoProducto idCategoriaOld = persistentProProducto.getIdCategoria();
            CatTipoProducto idCategoriaNew = proProducto.getIdCategoria();
            ProProveedor idProveedorOld = persistentProProducto.getIdProveedor();
            ProProveedor idProveedorNew = proProducto.getIdProveedor();
            List<VenDetalleVenta> venDetalleVentaListOld = persistentProProducto.getVenDetalleVentaList();
            List<VenDetalleVenta> venDetalleVentaListNew = proProducto.getVenDetalleVentaList();
            if (idCategoriaNew != null) {
                idCategoriaNew = em.getReference(idCategoriaNew.getClass(), idCategoriaNew.getId());
                proProducto.setIdCategoria(idCategoriaNew);
            }
            if (idProveedorNew != null) {
                idProveedorNew = em.getReference(idProveedorNew.getClass(), idProveedorNew.getId());
                proProducto.setIdProveedor(idProveedorNew);
            }
            List<VenDetalleVenta> attachedVenDetalleVentaListNew = new ArrayList<VenDetalleVenta>();
            for (VenDetalleVenta venDetalleVentaListNewVenDetalleVentaToAttach : venDetalleVentaListNew) {
                venDetalleVentaListNewVenDetalleVentaToAttach = em.getReference(venDetalleVentaListNewVenDetalleVentaToAttach.getClass(), venDetalleVentaListNewVenDetalleVentaToAttach.getId());
                attachedVenDetalleVentaListNew.add(venDetalleVentaListNewVenDetalleVentaToAttach);
            }
            venDetalleVentaListNew = attachedVenDetalleVentaListNew;
            proProducto.setVenDetalleVentaList(venDetalleVentaListNew);
            proProducto = em.merge(proProducto);
            if (idCategoriaOld != null && !idCategoriaOld.equals(idCategoriaNew)) {
                idCategoriaOld.getProProductoList().remove(proProducto);
                idCategoriaOld = em.merge(idCategoriaOld);
            }
            if (idCategoriaNew != null && !idCategoriaNew.equals(idCategoriaOld)) {
                idCategoriaNew.getProProductoList().add(proProducto);
                idCategoriaNew = em.merge(idCategoriaNew);
            }
            if (idProveedorOld != null && !idProveedorOld.equals(idProveedorNew)) {
                idProveedorOld.getProProductoList().remove(proProducto);
                idProveedorOld = em.merge(idProveedorOld);
            }
            if (idProveedorNew != null && !idProveedorNew.equals(idProveedorOld)) {
                idProveedorNew.getProProductoList().add(proProducto);
                idProveedorNew = em.merge(idProveedorNew);
            }
            for (VenDetalleVenta venDetalleVentaListOldVenDetalleVenta : venDetalleVentaListOld) {
                if (!venDetalleVentaListNew.contains(venDetalleVentaListOldVenDetalleVenta)) {
                    venDetalleVentaListOldVenDetalleVenta.setIdProducto(null);
                    venDetalleVentaListOldVenDetalleVenta = em.merge(venDetalleVentaListOldVenDetalleVenta);
                }
            }
            for (VenDetalleVenta venDetalleVentaListNewVenDetalleVenta : venDetalleVentaListNew) {
                if (!venDetalleVentaListOld.contains(venDetalleVentaListNewVenDetalleVenta)) {
                    ProProducto oldIdProductoOfVenDetalleVentaListNewVenDetalleVenta = venDetalleVentaListNewVenDetalleVenta.getIdProducto();
                    venDetalleVentaListNewVenDetalleVenta.setIdProducto(proProducto);
                    venDetalleVentaListNewVenDetalleVenta = em.merge(venDetalleVentaListNewVenDetalleVenta);
                    if (oldIdProductoOfVenDetalleVentaListNewVenDetalleVenta != null && !oldIdProductoOfVenDetalleVentaListNewVenDetalleVenta.equals(proProducto)) {
                        oldIdProductoOfVenDetalleVentaListNewVenDetalleVenta.getVenDetalleVentaList().remove(venDetalleVentaListNewVenDetalleVenta);
                        oldIdProductoOfVenDetalleVentaListNewVenDetalleVenta = em.merge(oldIdProductoOfVenDetalleVentaListNewVenDetalleVenta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = proProducto.getId();
                if (findProProducto(id) == null) {
                    throw new NonexistentEntityException("The proProducto with id " + id + " no longer exists.");
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
            ProProducto proProducto;
            try {
                proProducto = em.getReference(ProProducto.class, id);
                proProducto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proProducto with id " + id + " no longer exists.", enfe);
            }
            CatTipoProducto idCategoria = proProducto.getIdCategoria();
            if (idCategoria != null) {
                idCategoria.getProProductoList().remove(proProducto);
                idCategoria = em.merge(idCategoria);
            }
            ProProveedor idProveedor = proProducto.getIdProveedor();
            if (idProveedor != null) {
                idProveedor.getProProductoList().remove(proProducto);
                idProveedor = em.merge(idProveedor);
            }
            List<VenDetalleVenta> venDetalleVentaList = proProducto.getVenDetalleVentaList();
            for (VenDetalleVenta venDetalleVentaListVenDetalleVenta : venDetalleVentaList) {
                venDetalleVentaListVenDetalleVenta.setIdProducto(null);
                venDetalleVentaListVenDetalleVenta = em.merge(venDetalleVentaListVenDetalleVenta);
            }
            em.remove(proProducto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<ProProducto> findProProductoEntities() {
        return findProProductoEntities(true, -1, -1);
    }

    @Override
    public List<ProProducto> findProProductoEntities(int maxResults, int firstResult) {
        return findProProductoEntities(false, maxResults, firstResult);
    }

    private List<ProProducto> findProProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProProducto.class));
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
    public ProProducto findProProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProProducto.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getProProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProProducto> rt = cq.from(ProProducto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
