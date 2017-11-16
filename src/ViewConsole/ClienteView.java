/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewConsole;

import Business.ClienteBusiness;
import Business.TelefoneBusiness;
import Model.Cliente;
import Model.Telefone;
import java.util.Scanner;

/**
 *
 * @author Aluno
 */
public class ClienteView {

    private static Scanner in = new Scanner(System.in);
    
    public static boolean verifyEstado(int id) {
        return ClienteBusiness.findById(id).isEstado();
    }

    public static void create() {
        System.out.println("\nDigite o nome do novo cliente");
        String nome = in.nextLine();
        System.out.println("\nDigite o cpf do novo cliente");
        String cpf = in.nextLine();

        if (ClienteBusiness.create(nome, cpf)) {
            System.out.println("\nCliente cadastrado com sucesso!");
            Main.sleep();
        } else {
            System.out.println("\nErro ao cadastrar: Outro cliente possui esse CPF!");
            Main.sleep();
        }
    }

    public static void updateName(int id) {
        System.out.println("\nDigite o novo nome do cliente cadastrado");
        String nome = in.nextLine();
        ClienteBusiness.update(id, nome);
        System.out.println("\nNome alterado com sucesso!");
        Main.sleep();
    }

    public static void showCliente(int id) {
        Cliente cliente = ClienteBusiness.findById(id);

        Main.clear();
        if (cliente != null) {
            System.out.println("ID: " + cliente.getId());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("Estado: " + (cliente.isEstado() == true ? "Ativado" : "Desativado" + "!"));
            if (cliente.getTelefones().isEmpty()) {
            } else {
                System.out.println("Telefones: ");
                for (Telefone telefone : cliente.getTelefones()) {
                    System.out.println("\nID: " + telefone.getId());
                    System.out.println("Numero: " + telefone.getNumero());
                }
            }
            System.out.println("\nPrecione Enter para continuar...");
            in.nextLine();
        } else {
            System.out.println("\nErro: ID não está cadastrada no sistema!");
            Main.sleep();
        }
    }

    public static boolean showAll() {
        if (ClienteBusiness.findAll().isEmpty()) {
            System.out.println("\nNão há clientes cadastrados!");
            Main.sleep();
            return false;
        }

        Main.clear();
        for (Cliente cliente : ClienteBusiness.findAll()) {
            System.out.println("\nID: " + cliente.getId());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("Estado: " + (cliente.isEstado() == true ? "Ativado" : "Desativado" + "!"));
        }
        return true;
    }
    
    public static boolean showAtivado(){
        if (ClienteBusiness.findByEstado(true).isEmpty()) {
            System.out.println("\nNão há clientes ativos cadastrados!");
            Main.sleep();
            return false;
        }
        Main.clear();
        for (Cliente cliente : ClienteBusiness.findByEstado(true)) {
            System.out.println("\nID: " + cliente.getId());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("CPF: " + cliente.getCpf());
        }
        return true;
    }

    public static void delete(int id) {
        ClienteBusiness.delete(id);
        System.out.println("\nCliente desativado!");
        Main.sleep();
    }

    public static void createTelefone(int id) {
        System.out.println("\nDigite o numero do novo telefone!");
        String numero = in.nextLine();
        TelefoneBusiness.createTelef(numero, ClienteBusiness.findById(id));
        System.out.println("\nTelefone cadastrado com sucesso!");
        Main.sleep();
    }

    public static void deleteTelefone(int idDono) {
        System.out.println("\nDigite o id do telefone que deseja remover");

        try {
            int idTelef = Integer.parseInt(in.nextLine());
            Cliente cliente = ClienteBusiness.findById(idDono);
            if (TelefoneBusiness.verifyDonoTelefone(idTelef, cliente)) {
                TelefoneBusiness.deleteTelef(idTelef);
                System.out.println("\nTelefone removido com sucesso!");
                Main.sleep();
            } else {
                System.out.println("\nEsse cliente não possui um telefone registrado com esse ID!");
                Main.sleep();
            }
        } catch (Exception e) {
            System.out.println("\nDigite somente o que é pedido!");
            Main.sleep();
        }
    }

    public static int verifyCliente() {
        System.out.println("\nDigite o id do Cliente que deseja alterar");
        try {
            int id = Integer.parseInt(in.nextLine());
            if (ClienteBusiness.verifyCliente(id)) {
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

}
