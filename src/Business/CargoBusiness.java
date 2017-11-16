/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import DAO.CargoDAO;
import Model.Cargo;
import java.util.List;

/**
 *
 * @author cristhian_anacleto
 */
public class CargoBusiness {
    
    public static void create(String nome) {
        Cargo cargo = new Cargo();
        cargo.setNome(nome);
        CargoDAO.create(cargo);
    }
    
    public static boolean updateNome(int id, String nome) {
        Cargo cargo = findById(id);
        if (cargo == null) {
            return false;
        }
        cargo.setNome(nome);
        CargoDAO.update(cargo);
        return true;
    }
    
    public static boolean verifyCargo(int id) {
        return findById(id) != null;
    }
    
    public static Cargo findById(int id) {
        return CargoDAO.findById(id);
    }
    
    public static List<Cargo> findAll(){
        return CargoDAO.findAll();
    }
}
