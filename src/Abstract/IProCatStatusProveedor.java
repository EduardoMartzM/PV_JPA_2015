/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abstract;

import Concrete.exceptions.NonexistentEntityException;
import Concrete.exceptions.PreexistingEntityException;
import Model.ProCatStatusProveedor;
import java.util.List;

/**
 *
 * @author chirr
 */
public interface IProCatStatusProveedor {

    void create(ProCatStatusProveedor proCatStatusProveedor) throws PreexistingEntityException, Exception;

    void destroy(Integer id) throws NonexistentEntityException;

    void edit(ProCatStatusProveedor proCatStatusProveedor) throws NonexistentEntityException, Exception;

    ProCatStatusProveedor findProCatStatusProveedor(Integer id);

    List<ProCatStatusProveedor> findProCatStatusProveedorEntities();

    List<ProCatStatusProveedor> findProCatStatusProveedorEntities(int maxResults, int firstResult);

    int getProCatStatusProveedorCount();
    
}
