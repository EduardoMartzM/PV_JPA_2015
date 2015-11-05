/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abstract;

import Concrete.exceptions.NonexistentEntityException;
import Model.VenDetalleVenta;
import java.util.List;

/**
 *
 * @author chirr
 */
public interface IVenDetalleVenta {

    void create(VenDetalleVenta venDetalleVenta);

    void destroy(Integer id) throws NonexistentEntityException;

    void edit(VenDetalleVenta venDetalleVenta) throws NonexistentEntityException, Exception;

    VenDetalleVenta findVenDetalleVenta(Integer id);

    List<VenDetalleVenta> findVenDetalleVentaEntities();

    List<VenDetalleVenta> findVenDetalleVentaEntities(int maxResults, int firstResult);

    int getVenDetalleVentaCount();
    
}
