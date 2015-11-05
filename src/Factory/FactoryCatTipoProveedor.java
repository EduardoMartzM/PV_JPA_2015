/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Abstract.ICatTipoProveedor;
import Concrete.CatTipoProveedorJpaController;
import ConcreteExtends.CatTipoProveedorJPAExtends;
import Model.CatTipoProveedor;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chirr
 */
public class FactoryCatTipoProveedor {
    
    private static FactoryCatTipoProveedor factory;

    static {
        try {
            factory = FactoryCatTipoProveedor.class.newInstance();
        } catch (IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(FactoryCatTipoProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static FactoryCatTipoProveedor getInstance() {
        return factory;
    }

    public ICatTipoProveedor getInstanceAbstract() {
        try {
            CatTipoProveedorJpaController ctrlCatTipoProveedor = new CatTipoProveedorJpaController();
            return (ICatTipoProveedor) ctrlCatTipoProveedor;

        } catch (Exception _e) {
            return null;
        }
    }
    
       public List<CatTipoProveedor> getAllTipoProveedor()
    {
        try
        {
            CatTipoProveedorJPAExtends ctrlProveedorExtends = new CatTipoProveedorJPAExtends();
            return ctrlProveedorExtends.findAllTipoProveedor();
        }
        catch(Exception ex)
        {
            System.out.println("Error FactoryCatTipoProveedor(getAllTipoProveedor): "+ex.getMessage());
            return null;
        }
    }
    
}
