
package ViewConsole;

import Business.CargoBusiness;
import Model.Cargo;
import java.util.Scanner;


public class CargoView {
    
    private static Scanner in = new Scanner(System.in);
    
    public static void create() {
        System.out.println("\nDigite o nome do novo cargo");
        String nome = in.nextLine();
        CargoBusiness.create(nome);
        System.out.println("\nNovo cargo criado com sucesso!");
    }
    
    public static boolean showAll() {
        if (CargoBusiness.findAll().isEmpty()) {
            System.out.println("\nNão há cargos cadastrados!");
            return false;
        }
        for (Cargo cargo : CargoBusiness.findAll()) {
            System.out.println("\nID: " + cargo.getId());
            System.out.println("Cargo: " + cargo.getNome());
        }
        return true;
    }
    
    public static void updateNome() {
        if(!showAll()) {
            return;
        }
        System.out.println("\nDigite o ID do cargo que deseja alterar!");
        try {
            int id = Integer.parseInt(in.nextLine());
            System.out.println("\nDigite o novo nome do cargo!");
            String nome = in.nextLine();
            if (CargoBusiness.updateNome(id, nome)) {
                System.out.println("\nNome do cargo alterado com sucesso!");
            } else {
                System.out.println("\nErro ao alterar nome: ID de cargo digitado não está cadastrado!");
            }
        } catch (Exception e) {
            System.out.println("\nDigite somente o que é pedido!");
        }
    }
}
