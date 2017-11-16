package ViewConsole;

import Business.ClienteBusiness;
import Business.FornecedorBusiness;
import Business.FuncionarioBusiness;
import java.util.Scanner;

public class Main {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("\nInitializing Systems...");
        sleep();
        
        while (true) {
            clear();
            System.out.println("=====Bakery Management Systems=====");
            System.out.println("Business edition Released version 1.02                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  ");
            System.out.println("\n====Menu Principal====");
            System.out.println("(1)Gerenciamento de clientes");
            System.out.println("(2)Gerenciamento de funcionarios");
            System.out.println("(3)Gerenciamento de produtos");
            System.out.println("(4)Gerenciamento de fornecedores");
            System.out.println("(5)Gerenciamento de pedidos");
            System.out.println("(6)Gerenciamento de cargos");
            System.out.println("(0)Desligar sistemas");

            System.out.println("\nDigite o numero do menu que deseja acessar!");
            String menu = in.nextLine();

            switch (menu) {
                case "1":
                    menuCliente();
                    break;
                case "2":
                    menuFuncionario();
                    break;
                case "3":
                    menuProduto();
                    break;
                case "4":
                    menuFornecedor();
                    break;
                case "5":
                    menuPedido();
                    break;
                case "6":
                    menuCargo();
                    break;
                case "0":
                    clear();
                    System.out.println("\nShutting down Anacleto Bakery Management Systems ... ");
                    sleep();
                    System.exit(0);
                default:
                    System.out.println("\nOpção inválida!");
                    sleep();
            }
        }
    }

    public static void menuCliente() {
        while (true) {
            clear();
            System.out.println("\n===Gerencia de clientes===");
            System.out.println("(1)Cadastrar um cliente");
            System.out.println("(2)Alterar um cliente");
            System.out.println("(3)Visualizar todos os clientes ativos");
            System.out.println("(0)Voltar");
 
                System.out.println("\nDigite o numero do menu em que deseja entrar!");
                String menu = in.nextLine();

                switch (menu) {
                    case "1":
                        ClienteView.create();
                        return;
                    case "2":
                        menuAlterarCliente();
                        break;
                    case "3":
                        ClienteView.showAtivado();
                        System.out.println("\nPressione Enter para continuar...");
                        in.nextLine();
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("\nOpção inválida!");
                        sleep();
                }
        }
    }

    public static void menuFuncionario() {
        while (true) {
            clear();
            System.out.println("\n===Gerencia de funcionario===");
            System.out.println("(1)Cadastrar um funcionario");
            System.out.println("(2)Alterar um funcionario");
            System.out.println("(3)Visualizar todos os funcionarios ativos");
            System.out.println("(0)Voltar");

                System.out.println("\nDigite o numero do menu em que deseja entrar!");
                String menu = in.nextLine();

                switch (menu) {
                    case "1":
                        FuncionarioView.create();
                        return;
                    case "2":
                        menuAlterarFuncionario();
                        break;
                    case "3":
                        FuncionarioView.showAtivado();
                        System.out.println("\nPrecione Enter para continuar...");
                        in.nextLine();
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("\nOpção inválida!");
                        sleep();
                }
        }
    }

    public static void menuFornecedor() {
        while (true) {
            clear();
            System.out.println("\n===Gerencia de fornecedor===");
            System.out.println("(1)Criar um fornecedor");
            System.out.println("(2)Alterar um fornecedor");
            System.out.println("(3)Mostrar todos os fornecedores ativos");
            System.out.println("(0)Voltar");


                System.out.println("\nDigite o numero do menu que deseja acessar!");
                String menu = in.nextLine();

                switch (menu) {
                    case "1":
                        FornecedorView.create();
                        return;
                    case "2":
                        menuAlterarFornecedor();
                        break;
                    case "3":
                        FornecedorView.showAtivado();
                        System.out.println("\nPressione Enter para continuar...");
                        in.nextLine();
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("\nOpção inválida!");
                        sleep();
                }
        }
    }

    public static void menuProduto() {
        while (true) {
            clear();
            System.out.println("\n===Gerencia de produto===");
            System.out.println("(1)Criar um produto");
            System.out.println("(2)Alterar um produto");
            System.out.println("(3)Mostrar todos os produtos ativos");
            System.out.println("(0)Voltar");


                System.out.println("\nDigite o numero do menu que deseja acessar!");
                String menu = in.nextLine();

                switch (menu) {
                    case "1":
                        ProdutoView.create();
                        return;
                    case "2":
                        menuAlterarProduto();
                        break;
                    case "3":
                        ProdutoView.showAtivado();
                        System.out.println("\nPressione Enter para continuar...");
                        in.nextLine();
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("\nOpção inválida!");
                        sleep();
                }
            }
    }

    public static void menuPedido() {
        while (true) {
            clear();
            System.out.println("\n===Gerencia de pedidos===");
            System.out.println("(1)Criar um novo pedido");
            System.out.println("(2)Visualizar todos os pedidos");
            System.out.println("(3)Visualizar um pedido");
            System.out.println("(0)Voltar");

            System.out.println("\nDigite o numero do menu que deseja acessar!");
            String menu = in.nextLine();

            switch (menu) {
                case "1":
                    PedidoView.create();
                    break;
                case "2":
                    clear();
                    PedidoView.showAll();
                    System.out.println("\nPressione Enter para continuar...");
                    in.nextLine();
                    break;
                case "3":
                    clear();
                    PedidoView.showPedido();
                    System.out.println("\nPressione Enter para continuar...");
                    in.nextLine();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("\nOpção inválida");
                    sleep();
            }
        }
    }
    
    public static void menuCargo() {
        while (true) { 
            clear();
            System.out.println("\n===Gerencia de cargos===");
            System.out.println("(1)Criar um novo cargo");
            System.out.println("(2)Alterar nome de um cargo");
            System.out.println("(3)Listar todos os cargos");
            System.out.println("(0)Voltar");
            
            System.out.println("\nDigite o numero do menu que deseja acessar!");
            String menu = in.nextLine();
            
            switch (menu) {
                case "1":
                    clear();
                    CargoView.create();
                    sleep();
                    break;
                case "2":
                    clear();
                    CargoView.updateNome();
                    sleep();
                    break;
                case "3":
                    clear();
                    CargoView.showAll();
                    System.out.println("\nPressione Enter para continuar...");
                    in.nextLine();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("\nOpção inválida!");
                    sleep();
            }
        }
    }

    /*==============================================================================================================================================================
     ===============================================Menus de alteração===============================================================================================
     ================================================================================================================================================================
     */
    public static void menuAlterarCliente() {
        clear();
        if (!ClienteView.showAll()) {
            return;
        }
        int id = ClienteView.verifyCliente();
        if (id == 0) {
            return;
        }

        while (true) {
            clear();
            System.out.println("\n==Alteração de cliente==");
            System.out.println("(1)Visualizar cliente");
            System.out.println("(2)Alterar nome");
            System.out.println("(3)Adicionar um telefone");
            System.out.println("(4)Remover um telefone");
            System.out.println((ClienteView.verifyEstado(id) ? "(5)Desativar cliente" : "(5)Ativar cliente"));
            System.out.println("(0)Voltar");

            System.out.println("\nDigite o numero do menu que deseja acessar!");
            String menu = in.nextLine();

            switch (menu) {
                case "1":
                    clear();
                    ClienteView.showCliente(id);
                    break;
                case "2":
                    clear();
                    ClienteView.updateName(id);
                    break;
                case "3":
                    clear();
                    ClienteView.createTelefone(id);
                    break;
                case "4":
                    clear();
                    ClienteView.deleteTelefone(id);
                    break;
                case "5":
                    clear();
                    if (ClienteView.verifyEstado(id)) {
                        ClienteView.delete(id);
                    } else {
                        ClienteBusiness.turnActive(id);
                        System.out.println("\nCliente reativado!");
                        sleep();
                    }
                    return;
                case "0":
                    return;
                default:
                    System.out.println("\nOpção inválida!");
                    sleep();
            }
        }
    }

    public static void menuAlterarFuncionario() {
        clear();
        if (!FuncionarioView.showAll()) {
            return;
        }
        int id = FuncionarioView.verifyFuncionario();
        if (id == 0) {
            return;
        }
        while (true) {
            clear();
            System.out.println("\n==Alteração de funcionário==");
            System.out.println("(1)Visualizar funcionário");
            System.out.println("(2)Alterar nome");
            System.out.println("(3)Alterar cargo");
            System.out.println("(4)Alterar salário");
            System.out.println("(5)Adicionar um telefone");
            System.out.println("(6)Remover um telefone");
            System.out.println((FuncionarioView.verifyEstado(id) ? "(7)Desativar funcionario" : "(7)Ativar funcionario"));
            System.out.println("(0)Voltar");

            System.out.println("\nDigite o numero do menu que deseja acessar!");
            String menu = in.nextLine();

            switch (menu) {
                case "1":
                    clear();
                    FuncionarioView.showFuncionario(id);
                    break;
                case "2":
                    clear();
                    FuncionarioView.updateNome(id);
                    break;
                case "3":
                    clear();
                    FuncionarioView.updateCargo(id);
                    break;
                case "4":
                    clear();
                    FuncionarioView.updateSalario(id);
                    break;
                case "5":
                    FuncionarioView.createTelefone(id);
                    break;
                case "6":
                    FuncionarioView.deleteTelefone(id);
                    break;
                case "7":
                    clear();
                    if (FuncionarioView.verifyEstado(id)) {
                        FuncionarioView.delete(id);
                    } else {
                        FuncionarioBusiness.turnActive(id);
                        System.out.println("\nFuncionario reativado!");
                        sleep();
                    }
                    return;
                case "0":
                    return;
                default:
                    clear();
                    System.out.println("\nOpção inválida!");
                    sleep();
            }
        }
    }

    public static void menuAlterarFornecedor() {
        clear();
        if (!FornecedorView.showAll()) {
            return;
        }
        int id = FornecedorView.verifyFornecedor();
        if (id == 0) {
            return;
        }
        while (true) {
            clear();
            System.out.println("\n==Alteração de fornecedor==");
            System.out.println("(1)Visualizar fornecedor");
            System.out.println("(2)Alterar nome");
            System.out.println("(3)Adicionar um telefone");
            System.out.println("(4)Remover um telefone");
            System.out.println((FornecedorView.verifyEstado(id) ? "(5)Desativar fornecedor" : "(5)Ativar fornecedor"));
            System.out.println("(0)Voltar");

            System.out.println("\nDigite o numero do menu que deseja acessar!");
            String menu = in.nextLine();

            switch (menu) {
                case "1":
                    clear();
                    FornecedorView.showFornecedor(id);
                    break;
                case "2":
                    clear();
                    FornecedorView.updateNome(id);
                    break;
                case "3":
                    clear();
                    FornecedorView.createTelefone(id);
                    break;
                case "4":
                    clear();
                    FornecedorView.deleteTelefone(id);
                    break;
                case "5":
                    clear();
                    if (FornecedorView.verifyEstado(id)) {
                        FornecedorView.delete(id);
                    } else {
                        FornecedorBusiness.turnActive(id);
                        System.out.println("\nFornecedor reativado!");
                        sleep();
                    }
                    return;
                case "0":
                    return;
                default:
                    clear();
                    System.out.println("\nOpção inválida!");
                    sleep();
            }
        }
    }

    public static void menuAlterarProduto() {
        clear();
        if (!ProdutoView.showAll()) {
            return;
        }
        int id = ProdutoView.verifyProduto();
        if (id == 0) {
            return;
        }
        while (true) {
            clear();
            System.out.println("\n==Alteração de produto==");
            System.out.println("(1)Visualizar produto");
            System.out.println("(2)Alterar nome");
            System.out.println("(3)Alterar descrição");
            System.out.println("(4)Alterar preço");
            System.out.println("(5)Adicionar estoque");
            System.out.println("(6)Alterar fornecedor");
            System.out.println((ProdutoView.verifEstado(id) ? "(7)Desativar Produto" : "(7)Ativar Produto"));
            System.out.println("(0)Voltar");

            System.out.println("\nDigite o numero do menu que deseja acessar!");
            String menu = in.nextLine();

            switch (menu) {
                case "1":
                    clear();
                    ProdutoView.showProduto(id);
                    break;
                case "2":
                    clear();
                    ProdutoView.updateNome(id);
                    break;
                case "3":
                    clear();
                    ProdutoView.updateDescricao(id);
                    break;
                case "4":
                    clear();
                    ProdutoView.updatePreco(id);
                    break;
                case "5":
                    clear();
                    ProdutoView.updateEstoque(id);
                    break;
                case "6":
                    clear();
                    ProdutoView.updateFornecedor(id);
                    break;
                case "7":
                    if (ProdutoView.verifEstado(id)) {
                        ProdutoView.desativar(id);
                    } else {
                        ProdutoView.ativar(id);
                    }
                    break;
                case "0":
                    return;
                default:
                    clear();
                    System.out.println("\nOpção inválida!");
                    sleep();
            }
        }
    }

    /*===================================================================================================================================
    =====================================Métodos para melhorar a visualização dos menus==================================================
    =====================================================================================================================================
     */
    public static void clear() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public static void sleep() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
