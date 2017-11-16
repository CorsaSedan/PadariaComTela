package ViewConsole;

import Business.CargoBusiness;
import Business.FuncionarioBusiness;
import Business.TelefoneBusiness;
import Model.Cargo;
import Model.Funcionario;
import Model.Telefone;
import java.util.Scanner;

public class FuncionarioView {

    private static Scanner in = new Scanner(System.in);

    public static boolean verifyEstado(int id) {
        return FuncionarioBusiness.findById(id).isEstado();
    }

    public static int verifyFuncionario() {
        System.out.println("\nDigite o ID do funcionario que deseja alterar!");
        try {
            int id = Integer.parseInt(in.nextLine());
            if (!FuncionarioBusiness.verifyFuncionario(id)) {
                System.out.println("\nID digitado não está cadastrado no sistema!");
                Main.sleep();
                return 0;
            } else {
                return id;
            }
        } catch (Exception e) {
            System.out.println("\nDigite somente o que é pedido!");
            Main.sleep();
            return 0;
        }

    }

    public static void create() {
        if (CargoBusiness.findAll().isEmpty()) {
            System.out.println("\nNão há cargos registrados no sistema! Por Favor, cadastre um Cargo!");
            Main.sleep();
            return;
        }
        System.out.println("\nDigite o nome do novo funcionario");
        String nome = in.nextLine();
        System.out.println("\nDigite o cpf do novo funcionario");
        String cpf = in.nextLine();
        System.out.println("\nDigite o salário do novo funcionário");
        double salario;
        try {
            salario = Double.parseDouble(in.nextLine());

            System.out.println("\n====LISTA DE CARGOS====");
            CargoView.showAll();
            System.out.println("\nDigite o ID do cargo do novo funcionário");
            int idCargo = Integer.parseInt(in.nextLine());

            if (CargoBusiness.verifyCargo(idCargo)) {
                if (FuncionarioBusiness.create(nome, cpf, CargoBusiness.findById(idCargo), salario)) {
                    System.out.println("\nFuncionário cadastrado com sucesso!");
                } else {
                    System.out.println("\nErro ao cadastrar funcionário: já existem um funcionário cadastrado com esse CPF!");
                }
            } else {
                System.out.println("\nErro: ID de cargo digitado inválido!");
            }

        } catch (Exception e) {
            System.out.println("\nDigite somente o que é pedido!");
        }
        Main.sleep();
    }

    public static void updateNome(int id) {
        System.out.println("\nDigite o novo nome do funcionário");
        String nome = in.nextLine();
        FuncionarioBusiness.updateNome(id, nome);
        System.out.println("\nNome alterado com sucesso!");
        Main.sleep();
    }

    public static void updateSalario(int id) {
        System.out.println("\nDigite o novo salário do funcionário");
        try {
            double salario = Double.parseDouble(in.nextLine());
            FuncionarioBusiness.updateSalario(id, salario);
            System.out.println("\nSalário alterado com sucesso!");
            Main.sleep();
        } catch (Exception e) {
            System.out.println("\nDigite somente o que é pedido!");
            Main.sleep();
        }
    }

    public static void updateCargo(int id) {
        CargoView.showAll();
        if (CargoBusiness.findAll().isEmpty()) {
            System.out.println("\nNão há cargos cadastrados no sistema!");
            Main.sleep();
            return;
        }
        System.out.println("\nDigite o ID do novo cargo do funcionário!");
        try {
            int idCargo = Integer.parseInt(in.nextLine());
            if (FuncionarioBusiness.updateCargo(id, idCargo)) {
                System.out.println("\nCargo alterado com sucesso!");
            } else {
                System.out.println("\nErro ao alterar cargo: ID digitado não está cadastrado!");
            }

        } catch (NumberFormatException e) {
            System.out.println("\nDigite somente o que é pedido!");
        }
        Main.sleep();
    }

    public static void createTelefone(int id) {
        System.out.println("\nDigite o numero do novo telefone!");
        String numero = in.nextLine();
        Funcionario funcionario = FuncionarioBusiness.findById(id);
        TelefoneBusiness.createTelef(numero, funcionario);
        System.out.println("\nTelefone criado com sucesso!");
        Main.sleep();
    }

    public static void deleteTelefone(int idDono) {
        System.out.println("\nDigite o id do telefone que deseja remover");

        try {
            int idTelef = Integer.parseInt(in.nextLine());
            Funcionario funcionario = FuncionarioBusiness.findById(idDono);
            if (TelefoneBusiness.verifyDonoTelefone(idTelef, funcionario)) {
                TelefoneBusiness.deleteTelef(idTelef);
                System.out.println("\nTelefone removido com sucesso!");
                Main.sleep();
            } else {
                System.out.println("\nEsse funcionario não possui telefones registrados com esse ID!");
                Main.sleep();
            }
        } catch (Exception e) {
            System.out.println("\nDigite somente o que é pedido!");
            Main.sleep();
        }
    }

    public static void delete(int id) {
        FuncionarioBusiness.delete(id);
        System.out.println("\nFuncionario desativado!");
        Main.sleep();
    }

    public static boolean showAll() {
        if (FuncionarioBusiness.findAll().isEmpty()) {
            System.out.println("\nNão há funcionários cadastrados!");
            Main.sleep();
            return false;
        }

        Main.clear();
        for (Funcionario funcionario : FuncionarioBusiness.findAll()) {
            System.out.println("\nID: " + funcionario.getId());
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("CPF: " + funcionario.getCpf());
            System.out.println("Salário: " + funcionario.getSalario());
            System.out.println("Cargo: " + funcionario.getCargo().getNome());
            System.out.println("Estado: " + (funcionario.isEstado() == true ? "Ativado" : "Desativado" + "!"));
        }
        return true;
    }

    public static void showFuncionario(int id) {
        Funcionario funcionario = FuncionarioBusiness.findById(id);

        System.out.println("\nID: " + funcionario.getId());
        System.out.println("Nome: " + funcionario.getNome());
        System.out.println("CPF: " + funcionario.getCpf());
        System.out.println("Salário: " + funcionario.getSalario());
        System.out.println("Cargo: " + funcionario.getCargo().getNome());
        System.out.println("Estado: " + (funcionario.isEstado() == true ? "Ativado" : "Desativado" + "!"));
        if (funcionario.getTelefones().isEmpty()) {

        } else {
            System.out.println("Telefones: ");
            for (Telefone telefone : funcionario.getTelefones()) {
                System.out.println("\nID: " + telefone.getId());
                System.out.println("Numero: " + telefone.getNumero());
            }
        }
        System.out.println("\nPressione Enter para continuar...");
        in.nextLine();
    }

    public static boolean showAtivado() {
        if (FuncionarioBusiness.findByEstado(true).isEmpty()) {
            System.out.println("\nNão há funcionarios ativos cadastrados!");
            Main.sleep();
            return false;
        }
        Main.clear();
        for (Funcionario funcionario : FuncionarioBusiness.findByEstado(true)) {
            System.out.println("\nID: " + funcionario.getId());
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("CPF: " + funcionario.getCpf());
            System.out.println("Salário: " + funcionario.getSalario());
            System.out.println("Cargo: " + funcionario.getCargo().getNome());
        }
        return true;
    }
}
