/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abstract;

import Concrete.exceptions.NonexistentEntityException;
import Concrete.exceptions.PreexistingEntityException;
import Model.CatTipoProveedor;
import java.util.List;

/**
 *
 * @author chirr
 */
public interface ICatTipoProveedor {

    void create(CatTipoProveedor catTipoProveedor) throws PreexistingEntityException, Exception;

    void destroy(Integer id) throws NonexistentEntityException;

    void edit(CatTipoProveedor catTipoProveedor) throws NonexistentEntityException, Exception;

    CatTipoProveedor findCatTipoProveedor(Integer id);

    List<CatTipoProveedor> findCatTipoProveedorEntities();

    List<CatTipoProveedor> findCatTipoProveedorEntities(int maxResults, int firstResult);

    int getCatTipoProveedorCount();
    
}
