/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewConsole;

import Business.FornecedorBusiness;
import Business.TelefoneBusiness;
import Model.Fornecedor;
import Model.Telefone;
import java.util.Scanner;

/**
 *
 * @author cristhian_anacleto
 */
public class FornecedorView {

    private static Scanner in = new Scanner(System.in);
    
    public static boolean verifyEstado(int id) {
        return FornecedorBusiness.findById(id).isEstado();
    }

    public static int verifyFornecedor() {
        System.out.println("\nDigite o id do fornecedor que deseja alterar!");
        try {
            int id = Integer.parseInt(in.nextLine());
            if (FornecedorBusiness.verifyFornecedor(id)) {
                return id;
            } else {
                System.out.println("\nID digitado não está cadastrado no sistema!");
                Main.sleep();
                return 0;
            }
        } catch (Exception e) {
            System.out.println("\nDigite somente o que é pedido!");
            Main.sleep();
            return 0;
        }
    }

    public static void create() {
        System.out.println("\nDigite o nome do fornecedor");
        String nome = in.nextLine();
        System.out.println("\nDigite o cnpj do fornecedor");
        String cnpj = in.nextLine();
        if (FornecedorBusiness.create(nome, cnpj)) {
            System.out.println("\nFornecedor cadastrado com sucesso!");
            Main.sleep();
        } else {
            System.out.println("\nErro ao cadastrar fornecedor: Já existe um fornecedor com o mesmo cnpj");
            Main.sleep();
        }
    }

    public static void delete(int id) {
        FornecedorBusiness.turnOff(id);
        System.out.println("\nFornecedor desativado!");
        Main.sleep();
    }

    public static void updateNome(int id) {
        System.out.println("\nDigite o novo nome do fornecedor");
        String nome = in.nextLine();
        FornecedorBusiness.update(id, nome);
        System.out.println("\nNome alterado com sucesso!");
        Main.sleep();
    }

    public static void showFornecedor(int id) {
        Fornecedor fornecedor = FornecedorBusiness.findById(id);

        System.out.println("\nID: " + fornecedor.getId());
        System.out.println("Nome: " + fornecedor.getNome());
        System.out.println("CNPJ: " + fornecedor.getCnpj());
        System.out.println("Estado: " + (fornecedor.isEstado() == true ? "Ativado" : "Desativado" + "!"));
        if (fornecedor.getTelefones().isEmpty()) {

        } else {
            System.out.println("Telefones: ");
            for (Telefone telefone : fornecedor.getTelefones()) {
                System.out.println("\nID: " + telefone.getId());
                System.out.println("Numero: " + telefone.getNumero());
            }
        }
        System.out.println("\nPressione Enter para continuar...");
        in.nextLine();
    }

    public static boolean showAll() {
        if (FornecedorBusiness.findAll().isEmpty()) {
            System.out.println("\nNão há fornecedores cadastrados!");
            Main.sleep();
            return false;
        }

        Main.clear();
        for (Fornecedor fornecedor : FornecedorBusiness.findAll()) {
            System.out.println("\nID: " + fornecedor.getId());
            System.out.println("Nome: " + fornecedor.getNome());
            System.out.println("CNPJ: " + fornecedor.getCnpj());
            System.out.println("Estado: " + (fornecedor.isEstado() == true ? "Ativado" : "Desativado" + "!"));
        }
        return true;

    }
    
    public static boolean showAtivado(){
        if (FornecedorBusiness.findByEstado(true).isEmpty()) {
            System.out.println("\nNão há fornecedores ativos cadastrados!");
            Main.sleep();
            return false;
        }
        Main.clear();
        for (Fornecedor fornecedor : FornecedorBusiness.findByEstado(true)) {
            System.out.println("\nID: " + fornecedor.getId());
            System.out.println("Nome: " + fornecedor.getNome());
            System.out.println("CNPJ: " + fornecedor.getCnpj());
        }
        return true;
    }

    public static void createTelefone(int id) {
        System.out.println("\nDigite o numero do novo telefone!");
        String numero = in.nextLine();
        TelefoneBusiness.createTelef(numero, FornecedorBusiness.findById(id));
        System.out.println("\nTelefone cadastrado com sucesso!");
        Main.sleep();
    }

    public static void deleteTelefone(int idDono) {
        System.out.println("\nDigite o id do telefone que deseja remover");

        try {
            int idTelef = Integer.parseInt(in.nextLine());
            Fornecedor fornecedor = FornecedorBusiness.findById(idDono);
            if (TelefoneBusiness.verifyDonoTelefone(idTelef, fornecedor)) {
                TelefoneBusiness.deleteTelef(idTelef);
                System.out.println("\nTelefone removido com sucesso!");
                Main.sleep();
            } else {
                System.out.println("\nEsse fornecedor não possui um telefone registrado com esse ID!");
                Main.sleep();
            }
        } catch (Exception e) {
            System.out.println("\nDigite somente o que é pedido!");
            Main.sleep();
        }
    }

}
