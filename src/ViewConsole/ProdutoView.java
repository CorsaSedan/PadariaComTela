/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewConsole;

import Business.FornecedorBusiness;
import Business.ProdutoBusiness;
import Model.Produto;
import java.util.Scanner;

/**
 *
 * @author cristhian_anacleto
 */
public class ProdutoView {

    private static Scanner in = new Scanner(System.in);

    public static int verifyProduto() {
        System.out.println("\nDigite o id do produto que deseja alterar");
        try {
            int id = Integer.parseInt(in.nextLine());
            if (ProdutoBusiness.verifyProduto(id)) {
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
        try {
            System.out.println("\nDigite o nome do novo produto");
            String nome = in.nextLine();
            System.out.println("\nDigite a descrição do novo produto");
            String descricao = in.nextLine();
            double preco;
            while (true) {
                System.out.println("\nDigite o preco do novo produto");
                preco = Double.parseDouble(in.nextLine());
                if (preco <= 0) {
                    System.out.println("\nDigite valores acima de zero");
                } else {
                    break;
                }
            }
            
            int quantidade;
            while (true) {
                System.out.println("\nDigite a quantidade em estoque do novo produto");
                quantidade = Integer.parseInt(in.nextLine());
                if (quantidade < 0) {
                    System.out.println("\nNão digite valores negativos!");
                } else {
                    break;
                }
            }

            ProdutoBusiness.create(nome, descricao, preco, quantidade);
            System.out.println("\nProduto cadastrado com sucesso!");
            Main.sleep();
        } catch (Exception e) {
            System.out.println("\nDigite somente o que é pedido!");
            Main.sleep();
        }
    }
    
    public static boolean showAll(){
        if (ProdutoBusiness.findAll().isEmpty()) {
            System.out.println("\nNão há produtos cadastrados!");
            Main.sleep();
            return false;
        }
        Main.clear();
        for (Produto produto : ProdutoBusiness.findAll()) {
            System.out.println("\nID: " + produto.getId());
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Descrição: " + produto.getDescricao());
            System.out.println("Quantidade em estoque: " + produto.getQuantidade());
            System.out.println((produto.getFornecedor() == null ? "Não possui fornecedor" : "Fornecedor: " + produto.getFornecedor().getNome()));
            System.out.println("Preço: " + produto.getPreco());
            System.out.println("Estado: " + (produto.isEstado() == true ? "Ativado" : "Desativado" + "!"));
        }
        return true;
    }
    
    public static boolean showAtivado(){
        if (ProdutoBusiness.findByEstado(true).isEmpty()) {
            System.out.println("\nNão há produtos ativos cadastrados!");
            Main.sleep();
            return false;
        }
        Main.clear();
        for (Produto produto : ProdutoBusiness.findByEstado(true)) {
            System.out.println("\nID: " + produto.getId());
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Descrição: " + produto.getDescricao());
            System.out.println("Quantidade em estoque: " + produto.getQuantidade());
            System.out.println((produto.getFornecedor() == null ? "Não possui fornecedor" : "Fornecedor: " + produto.getFornecedor().getNome()));
            System.out.println("Preço: " + produto.getPreco());
        }
        return true;
    }

    public static void showProduto(int id) {
        Produto produto = ProdutoBusiness.findById(id);

        Main.clear();
        if (produto != null) {
            System.out.println("ID: " + produto.getId());
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Descrição: " + produto.getDescricao());
            System.out.println("Quantidade em estoque: " + produto.getQuantidade());
            System.out.println((produto.getFornecedor() == null ? "Não possui fornecedor" : "Fornecedor: " + produto.getFornecedor().getNome()));
            System.out.println("Preço: " + produto.getPreco());
            System.out.println("Estado: " + (produto.isEstado() == true ? "Ativado" : "Desativado" + "!"));
            System.out.println("\nPrecione Enter para continuar...");
            in.nextLine();
        } else {
            System.out.println("\nErro: ID não está cadastrada no sistema!");
            Main.sleep();
        }
    }

    public static void updateNome(int id) {
        System.out.println("\nDigite o novo nome do produto!");
        String nome = in.nextLine();
        ProdutoBusiness.updateNome(id, nome);
        System.out.println("\nNome alterado com sucesso!");
        Main.sleep();
    }

    public static void updateDescricao(int id) {
        System.out.println("\nDigite a nova descrição do produto!");
        String descricao = in.nextLine();
        ProdutoBusiness.updateDescricao(id, descricao);
        System.out.println("\nDescrição alterada com sucesso!");
        Main.sleep();
    }

    public static void updatePreco(int id) {
        try {
            System.out.println("\nDigite um novo preço para o produto!");
            double preco = Double.parseDouble(in.nextLine());
            if (preco > 0) {
            ProdutoBusiness.updatePreco(id, preco);
            System.out.println("\nPreço alterado com sucesso!");
            Main.sleep();
            } else {
                System.out.println("\nO preço deve ser acima de 0!");
                Main.sleep();
            }
        } catch (Exception e) {
            System.out.println("\nDigite somente o que é pedido!");
        }
    }

    public static void updateEstoque(int id) {
        System.out.println("\nEstoque atual: " + ProdutoBusiness.findById(id).getQuantidade() + " unidade/unidades");
        System.out.println("\nDigite o numero de unidades que deseja acresentar ao estoque");
        try {
            int quantidade = Integer.parseInt(in.nextLine());
            if (quantidade >= 0) {
                quantidade += ProdutoBusiness.findById(id).getQuantidade();
                ProdutoBusiness.updateQuantidade(id, quantidade);
                System.out.println("\nQuantidade em estoque aumentada para " + ProdutoBusiness.findById(id).getQuantidade() + " unidade/unidades");
                Main.sleep();
            } else {
                System.out.println("\nErro: Não digite números negativos!");
            }
        } catch (Exception e) {
            System.out.println("\nDigite somente o que é pedido!");
        }
    }

    public static void updateFornecedor(int id) {
        if(!FornecedorView.showAtivado()){
            return;
        }
        System.out.println("\nDigite o ID do novo fornecedor");
        try {
            int idForn = Integer.parseInt(in.nextLine());
            if (FornecedorBusiness.verifyFornecedorAtivo(idForn)) {
                ProdutoBusiness.updateFornecedor(id, idForn);
                System.out.println("\nFornecedor alterado com sucesso!");
            } else {
                System.out.println("\nNão há fornecedores ativos com esse ID!");
            }
            Main.sleep();
        } catch (Exception e) {
            System.out.println("\nDigite somente o que é pedido!");
        }
    }
    
    public static boolean verifEstado(int id){
        return ProdutoBusiness.findById(id).isEstado();
    }
    
    public static void ativar(int id){
        ProdutoBusiness.turnOn(id);
        System.out.println("\nO produto foi ativado!");
        Main.sleep();
    }

    public static void desativar(int id) {
        ProdutoBusiness.turnOff(id);
        System.out.println("\nO produto foi desativado!");
        Main.sleep();
    }
    
}
