package Business;

import DAO.FornecedorDAO;
import DAO.ProdutoDAO;
import Model.Fornecedor;
import Model.Produto;
import java.util.List;

public class ProdutoBusiness {

    public static void create(String nome, String descricao, double preco, int quantidade) {
        Produto produto = new Produto(nome, preco, descricao, quantidade, true);
        ProdutoDAO.create(produto);
    }

    public static int updateFornecedor(int idProd, int idForn) {
        //Método que atualiza o fornecedor do produto
        Fornecedor fornecedor = FornecedorDAO.findById(idForn);
        if (fornecedor == null) {
            //Caso o fornecedor digitado não esteja cadastrado, o método retornará 1
            return 1;
        }
        Produto produto = ProdutoDAO.findById(idProd);
        if (produto == null) {
            //Caso o produto digitado não esteja cadastrado, o método retornará 2
            return 2;
        }
        produto.setFornecedor(fornecedor);
        ProdutoDAO.updateFornecedor(produto);
        //Caso tudo ocorra com sucesso, o método retornará 0
        return 0;
    }

    public static boolean updatePreco(int id, double preco) {
        Produto produto = ProdutoDAO.findById(id);
        if (produto == null) {
            //Caso o produto digitado não esteja cadastrado, o método retorna false
            return false;
        }
        produto.setPreco(preco);
        ProdutoDAO.update(produto);
        //Caso tudo ocorra com sucesso, o método retornará true
        return true;
    }

    public static boolean updateQuantidade(int id, int quantidade) {
        Produto produto = ProdutoDAO.findById(id);
        if (produto == null) {
            //Caso o produto digitado não esteja cadastrado, o método retorna false
            return false;
        }
        produto.setQuantidade(quantidade);
        ProdutoDAO.update(produto);
        //Caso tudo ocorra com sucesso, o método retornará true
        return true;
    }

    public static boolean updateDescricao(int id, String descricao) {
        Produto produto = ProdutoDAO.findById(id);
        if (produto == null) {
            //Caso o produto digitado não esteja cadastrado, o método retorna false
            return false;
        }
        produto.setDescricao(descricao);
        ProdutoDAO.update(produto);
        //Caso tudo ocorra com sucesso, o método retornará true
        return true;
    }

    public static boolean updateNome(int id, String nome) {
        Produto produto = ProdutoDAO.findById(id);
        if (produto == null) {
            //Caso o produto digitado não esteja cadastrado, o método retorna false
            return false;
        }
        produto.setNome(nome);
        ProdutoDAO.update(produto);
        //Caso tudo ocorra com sucesso, o método retornará true
        return true;
    }
    
    public static boolean turnOn(int id) {
        Produto produto = ProdutoDAO.findById(id);
        if (produto == null) {
            //Caso o id digitado não esteja registrado, o método retorna false
            return false;
        } else {
            produto.setEstado(true);
            ProdutoDAO.update(produto);
            //Caso tudo ocorra com sucesso, o método retorna true
            return true;
        }
    }

    public static boolean turnOff(int id) {
        Produto produto = ProdutoDAO.findById(id);
        if (produto == null) {
            //Caso o id digitado não esteja registrado, o método retorna false
            return false;
        } else {
            produto.setEstado(false);
            ProdutoDAO.update(produto);
            //Caso tudo ocorra com sucesso, o método retorna true
            return true;
        }
    }
    
    public static boolean verifyProduto(int id){
        if (ProdutoDAO.findById(id) == null) {
            return false;
        } else {
            return true;
        }
    }
    
    public static boolean verifyProdutoAtivo(int id) {
        for (Produto produto : ProdutoDAO.findByEstado(true)) {
            if (produto.getId() == id) {
                return true;
            }
        }
        return false;
    }
    
    public static Produto findById(int id){
        return ProdutoDAO.findById(id);
    }
    
    public static List<Produto> findAll(){
        return ProdutoDAO.findAll();
    }
    
    public static List<Produto> findByEstado(boolean estado){
        return ProdutoDAO.findByEstado(estado);
    }

}
