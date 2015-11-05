/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abstract;

import Concrete.exceptions.IllegalOrphanException;
import Concrete.exceptions.NonexistentEntityException;
import Concrete.exceptions.PreexistingEntityException;
import Model.CatTipoPago;
import java.util.List;

/**
 *
 * @author chirr
 */
public interface ICatTipoPago {

    void create(CatTipoPago catTipoPago) throws PreexistingEntityException, Exception;

    void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException;

    void edit(CatTipoPago catTipoPago) throws IllegalOrphanException, NonexistentEntityException, Exception;

    CatTipoPago findCatTipoPago(Integer id);

    List<CatTipoPago> findCatTipoPagoEntities();

    List<CatTipoPago> findCatTipoPagoEntities(int maxResults, int firstResult);

    int getCatTipoPagoCount();
    
}
