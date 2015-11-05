/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abstract;

import Concrete.exceptions.NonexistentEntityException;
import Concrete.exceptions.PreexistingEntityException;
import Model.CatTipoProducto;
import java.util.List;

/**
 *
 * @author chirr
 */
public interface ICatTipoProducto {

    void create(CatTipoProducto catTipoProducto) throws PreexistingEntityException, Exception;

    void destroy(Integer id) throws NonexistentEntityException;

    void edit(CatTipoProducto catTipoProducto) throws NonexistentEntityException, Exception;

    CatTipoProducto findCatTipoProducto(Integer id);

    List<CatTipoProducto> findCatTipoProductoEntities();

    List<CatTipoProducto> findCatTipoProductoEntities(int maxResults, int firstResult);

    int getCatTipoProductoCount();
    
}
