/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abstract;

import Concrete.exceptions.NonexistentEntityException;
import Model.ProProveedor;
import java.util.List;

/**
 *
 * @author chirr
 */
public interface IProProveedor {

    void create(ProProveedor proProveedor);

    void destroy(Integer id) throws NonexistentEntityException;

    void edit(ProProveedor proProveedor) throws NonexistentEntityException, Exception;

    ProProveedor findProProveedor(Integer id);

    List<ProProveedor> findProProveedorEntities();

    List<ProProveedor> findProProveedorEntities(int maxResults, int firstResult);

    int getProProveedorCount();
    
}
