/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewConsole;

import Business.ClienteBusiness;
import Business.FuncionarioBusiness;
import Business.Ped_ProdBusiness;
import Business.PedidoBusiness;
import Business.ProdutoBusiness;
import Model.FormaPagamento;
import Model.Ped_Prod;
import Model.Pedido;
import Model.Produto;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author Aluno
 */
public class PedidoView {

    private static Scanner in = new Scanner(System.in);

    public static void showAll() {
        if (PedidoBusiness.findAll().isEmpty()) {
            System.out.println("\nNão há pedidos registrados");
            Main.sleep();
            return;
        }
        for (Pedido pedido : PedidoBusiness.findAll()) {
            System.out.println("\n=========================");
            System.out.println("\nID: " + pedido.getId());
            System.out.println("Cliente: " + pedido.getCliente().getNome());
            System.out.println("Funcionario expedidor: " + pedido.getFuncionario().getNome());
            System.out.println("Data de expedição: " + pedido.getDataPedido());
            System.out.println("Forma de pagamento: " + pedido.getFormaPagamento());
            System.out.println("Produtos comprados:");
            for (Ped_Prod produto : pedido.getProdutos()) {
                System.out.println("\n >ID: " + produto.getProduto().getId());
                System.out.println("  Nome: " + produto.getProduto().getNome());
                System.out.println("  Quantidade comprada: " + produto.getQuantidade());
                System.out.println("  Preço unitário:" + produto.getProduto().getPreco());
                System.out.println("  Subtotal: " + produto.getPreco());
            }
            System.out.println("\nPreço Total: " + pedido.getPrecoTotal());
        }
    }

    public static void showPedido() {
        Main.clear();
        if (PedidoBusiness.findAll().isEmpty()) {
            System.out.println("\nNão há pedidos cadastrados!");
            return;
        }
        System.out.println("\nDigite o ID do pedido que deseja visualizar!");
        try {
            int id = Integer.parseInt(in.nextLine());
            Pedido pedido = PedidoBusiness.getPedido(id);
            if (pedido == null) {
                System.out.println("\nID de pedido digitada não está cadastrada!");
                return;
            }
            System.out.println("\nID: " + pedido.getId());
            System.out.println("Cliente: " + pedido.getCliente().getNome());
            System.out.println("Funcionario expedidor: " + pedido.getFuncionario().getNome());
            System.out.println("Data de expedição: " + pedido.getDataPedido());
            System.out.println("Forma de pagamento: " + pedido.getFormaPagamento());
            System.out.println("Produtos comprados:");
            for (Ped_Prod produto : pedido.getProdutos()) {
                System.out.println("\n  >ID: " + produto.getProduto().getId());
                System.out.println("  Nome: " + produto.getProduto().getNome());
                System.out.println("  Quantidade comprada: " + produto.getQuantidade());
                System.out.println("  Preço unitário:" + produto.getProduto().getPreco());
                System.out.println("  Subtotal: " + produto.getPreco());
            }
            System.out.println("\nPreço Total: " + pedido.getPrecoTotal());
        } catch (Exception e) {
        }
    }

    public static void create() {
        if (ProdutoBusiness.findByEstado(true).isEmpty()) {
            System.out.println("\nNão há produtos ativos no sistema! Para criar um pedido, cadastre produtos antes!");
            Main.sleep();
            return;
        }
        if (FuncionarioBusiness.findByEstado(true).isEmpty()) {
            System.out.println("\nNão há funcionários ativos cadastrados no sistema!");
            Main.sleep();
            return;
        }
        try {
            if (!ClienteView.showAtivado()) {
                return;
            }
            System.out.println("\nDigite o ID do cliente que realizou o pedido");
            int idCliente = Integer.parseInt(in.nextLine());
            if (ClienteBusiness.verifyClienteAtivo(idCliente)) {
                if (!FuncionarioView.showAtivado()) {
                    return;
                }
                System.out.println("\nDigite o ID do funcionario expedidor do pedido");
                int idFuncionario = Integer.parseInt(in.nextLine());
                if (FuncionarioBusiness.verifyFuncionarioAtivo(idFuncionario)) {
                    Main.clear();
                    System.out.println("\nDigite a forma de pagamento! ('dinheiro' ou 'cartao')");
                    FormaPagamento pagamento = FormaPagamento.valueOf(in.nextLine().toUpperCase());
                    Date data = Date.valueOf(LocalDate.now());
                    Pedido pedido = PedidoBusiness.create(data, idCliente, idFuncionario, pagamento);
                    carrinho(pedido);
                } else {
                    System.out.println("\nNão há funcionários ativos no sistema com esse ID!");
                }
            } else {
                System.out.println("\nNão há clientes ativos no sistema com esse ID!");
            }

        } catch (Exception e) {
            System.out.println("\nDigite somente o que é  pedido!");
        }
        Main.sleep();
    }

    public static void carrinho(Pedido pedido) {

        stop:
        while (true) {
            Main.clear();
            showCarrinho(pedido);
            System.out.println("\n==Opções==");
            System.out.println("(1)Adicionar um produto ao carrinho");
            System.out.println("(2)Remover um produto do carrinho");
            System.out.println("(3)Remover todos os produtos do carrinho");
            System.out.println("(4)Cancelar pedido");
            System.out.println("(5)Finalizar pedido");

            System.out.println("\nDigite o numero da opção que deseja acessar!");
            String opcao = in.nextLine();

            switch (opcao) {
                case "1":
                    createPedProd(pedido.getId());
                    break;
                case "2":
                    removerProduto(pedido);
                    break;
                case "3":
                    removerTodosProdutos(pedido);
                    break;
                case "4":
                    cancelarPedido(pedido);
                    PedidoBusiness.delete(pedido);
                    Main.sleep();
                    break stop;
                case "5":
                    Pedido pedidoF = PedidoBusiness.getPedido(pedido.getId());
                    if (pedidoF.getProdutos().isEmpty()) {
                        System.out.println("\nNão foi possível completar o pedido: Não há produtos no carrinho!");
                        PedidoBusiness.delete(pedido);
                    } else {
                        System.out.println("\nPedido completado!");
                    }
                    Main.sleep();
                    break stop;
                default:
                    System.out.println("\nOpção inválida!");
                    Main.sleep();
            }
        }
    }

    public static void createPedProd(int idPedido) {
        ProdutoView.showAtivado();
        System.out.println("\nDigite o ID do produto que deseja adicionar ao carrinho!");
        try {
            int idProd = Integer.parseInt(in.nextLine());

            if (ProdutoBusiness.verifyProdutoAtivo(idProd)) {
                Produto produto = ProdutoBusiness.findById(idProd);
                Main.clear();
                System.out.println("\n >Quantidade em estoque: " + produto.getQuantidade());
                System.out.println(" >Preço unitário: " + produto.getPreco());

                System.out.println("\nDigite a quantidade que deseja comprar!");
                int qtd = Integer.parseInt(in.nextLine());

                if (qtd < 0 || qtd > produto.getQuantidade()) {
                    System.out.println("\nQuantidade digitada invalida!");
                } else {

                    Ped_Prod pedProd = new Ped_Prod(PedidoBusiness.getPedido(idPedido), produto, qtd);
                    Ped_ProdBusiness.setPed_Prod(pedProd);

                    System.out.println("\nProduto acrescentado ao carrinho!");
                }

            } else {
                System.out.println("\nNão há um produto ativo com esse ID!");
            }

        } catch (Exception e) {
            System.out.println("\nDigite somente o que é pedido!");
        }
        Main.sleep();
    }

    public static void removerProduto(Pedido pedido) {
        Main.clear();
        pedido = PedidoBusiness.getPedido(pedido.getId());
        if (pedido.getProdutos().isEmpty()) {
            System.out.println("\nNão há produtos no carrinho!");
            Main.sleep();
            return;
        }
        try {
            for (int i = 0; i < pedido.getProdutos().size(); i++) {
                System.out.println("\nÍndice: " + (i + 1));
                System.out.println("Produto: " + pedido.getProdutos().get(i).getProduto().getNome());
                System.out.println("Preço unitário: " + pedido.getProdutos().get(i).getProduto().getPreco());
                System.out.println("Unidades compradas: " + pedido.getProdutos().get(i).getQuantidade());
                System.out.println("Total do produto: " + pedido.getProdutos().get(i).getPreco());
            }
            System.out.println("\nDigite o índice do produto que deseja remover!");
            int index = Integer.parseInt(in.nextLine()) - 1;
            Ped_Prod pedProd = pedido.getProdutos().get(index);

            System.out.println("\nDigite a quantidade de produtos que deseja remover!");
            int qtd = Integer.parseInt(in.nextLine());

            if (qtd == pedProd.getQuantidade()) {
                Ped_ProdBusiness.delete(pedProd);
                System.out.println("\nAlteração aplicada com sucesso!");
            } else if (qtd < 0 || qtd > pedProd.getQuantidade()) {
                System.out.println("\nDigite quantidades válidas!");
            } else {
                pedProd.setQuantidade(pedProd.getQuantidade() - qtd);
                Produto produto = pedProd.getProduto();
                produto.setQuantidade(qtd + produto.getQuantidade());
                Ped_ProdBusiness.updateQuantidade(pedProd, produto);
                System.out.println("\nAlteração aplicada com sucesso!");
            }

        } catch (Exception e) {
            System.out.println("\nErro: Digite o que é requerido!");
        }
        Main.sleep();
    }

    public static void removerTodosProdutos(Pedido pedido) {
        System.out.println("\nTem certeza de que deseja realizar esta operação!(Digite 's' ou 'n')");
        String opcao = in.nextLine();
        if (opcao.equalsIgnoreCase("s")) {
            Ped_ProdBusiness.deleteByPedido(pedido.getId());
            System.out.println("\nOperação realiada com sucesso!");
        } else if (opcao.equalsIgnoreCase("n")) {
            System.out.println("\nAinda bem!");
        } else {
            System.out.println("\nOpção inválida!");
        }

        Main.sleep();
    }

    public static void cancelarPedido(Pedido pedido) {
        Ped_ProdBusiness.deleteByPedido(pedido.getId());
        System.out.println("\nPedido cancelado!");
    }

    public static void showCarrinho(Pedido pedido) {
        System.out.println("\n===CARRINHO=====================");
        pedido = PedidoBusiness.getPedido(pedido.getId());
        if (pedido.getProdutos().isEmpty()) {
            System.out.println("\nVAZIO");
        } else {
            for (Ped_Prod pedProd : pedido.getProdutos()) {
                System.out.println("\nNome: " + pedProd.getProduto().getNome());
                System.out.println("Preço unitário: " + pedProd.getProduto().getPreco());
                System.out.println("Quantidade comprada: " + pedProd.getQuantidade());
                System.out.println("Subtotal: " + pedProd.getPreco());
            }
            
            System.out.println(" ");
            if (pedido.getFormaPagamento() == FormaPagamento.DINHEIRO) {
                System.out.println("Desconto por pagar em dinheiro: 5%");
            }
            System.out.println("Total a pagar: " + pedido.getPrecoTotal());
        }
        System.out.println("\n================================");
    }

}
