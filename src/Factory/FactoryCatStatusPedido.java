/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Abstract.ICatStatusPedido;
import Concrete.CatStatusPedidoJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chirr
 */
public class FactoryCatStatusPedido {
    
     private static FactoryCatStatusPedido factory;

    static {
        try {
            factory = FactoryCatStatusPedido.class.newInstance();
        } catch (IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(FactoryCatStatusPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public static FactoryCatStatusPedido getInstance() {
        return factory;
    }

    public ICatStatusPedido getInstanceAbstract() {
        try {
            CatStatusPedidoJpaController ctrlCatStatusPedido = new CatStatusPedidoJpaController();
            return (ICatStatusPedido) ctrlCatStatusPedido;

        } catch (Exception _e) {
            return null;
        }
    }
    
//    public List<CatStatusPedido> getAllAlumno()
//    {
//        try {
//            AlumnoJPAExtends alumno= new AlumnoJPAExtends();
//            List<Alumno> listAlumno= alumno.getAllAlumnos();
//            return  listAlumno;
//        } catch (Exception _e) {
//            System.out.println(_e.getMessage());
//            return null;
//        }
//    }
    
}
