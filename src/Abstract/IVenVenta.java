/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abstract;

import Concrete.exceptions.NonexistentEntityException;
import Model.VenVenta;
import java.util.List;

/**
 *
 * @author chirr
 */
public interface IVenVenta {

    void create(VenVenta venVenta);

    void destroy(Integer id) throws NonexistentEntityException;

    void edit(VenVenta venVenta) throws NonexistentEntityException, Exception;

    VenVenta findVenVenta(Integer id);

    List<VenVenta> findVenVentaEntities();

    List<VenVenta> findVenVentaEntities(int maxResults, int firstResult);

    int getVenVentaCount();
    
}
