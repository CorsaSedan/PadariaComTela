package Business;

import DAO.TelefoneDAO;
import Model.Pessoa;
import Model.Telefone;

public class TelefoneBusiness {

    public static void createTelef(String numero, Pessoa dono) {
        Telefone telefone = new Telefone();
        telefone.setNumero(numero);
        telefone.setDono(dono);
        TelefoneDAO.create(telefone);
    }

    public static boolean deleteTelef(int id) {
        if (TelefoneDAO.findById(id) != null) {
            TelefoneDAO.delete(id);
            return true;
        }
        return false;
    }

    public static boolean verifyDonoTelefone(int idTelef, Pessoa dono) {
        try {
            if (TelefoneDAO.findById(idTelef).getDono().getId() == dono.getId()) {
                Class<? extends Pessoa> classe1 = TelefoneDAO.findById(idTelef).getDono().getClass();
                Class<? extends Pessoa> classe2 = dono.getClass();
                if (classe1 == classe2) {
                    return true;
                }
            }
            return false;
        } catch (NullPointerException e) {
            return false;
        }
    }
}
