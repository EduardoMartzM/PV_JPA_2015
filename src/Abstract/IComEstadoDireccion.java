/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abstract;

import Concrete.exceptions.NonexistentEntityException;
import Concrete.exceptions.PreexistingEntityException;
import Model.ComEstadoDireccion;
import java.util.List;

/**
 *
 * @author chirr
 */
public interface IComEstadoDireccion {

    void create(ComEstadoDireccion comEstadoDireccion) throws PreexistingEntityException, Exception;

    void destroy(Integer id) throws NonexistentEntityException;

    void edit(ComEstadoDireccion comEstadoDireccion) throws NonexistentEntityException, Exception;

    ComEstadoDireccion findComEstadoDireccion(Integer id);

    List<ComEstadoDireccion> findComEstadoDireccionEntities();

    List<ComEstadoDireccion> findComEstadoDireccionEntities(int maxResults, int firstResult);

    int getComEstadoDireccionCount();
    
}
