package Business;

import DAO.Ped_ProdDAO;
import DAO.ProdutoDAO;
import Model.Ped_Prod;
import Model.Pedido;
import Model.Produto;

public class Ped_ProdBusiness {

    private static void create(Ped_Prod pedProd) {
        Ped_ProdDAO.create(pedProd);
    }

    private static void filterPed_Prod(Ped_Prod pedProd) {
        Pedido pedido = PedidoBusiness.getPedido(pedProd.getPedido().getId());
        for (Ped_Prod produtos : pedido.getProdutos()) {
            if (produtos.getProduto().getId() == pedProd.getProduto().getId()) {
                produtos.setQuantidade(produtos.getQuantidade() + pedProd.getQuantidade());
                Ped_ProdDAO.update(produtos);
                return;
            }
        }
        create(pedProd);
    }
    
    public static boolean setPed_Prod(Ped_Prod pedProd){
        Pedido pedido = PedidoBusiness.getPedido(pedProd.getPedido().getId());
        if (pedido == null) {
            return false;
        }
        
        Produto produto = null;
        if (ProdutoBusiness.verifyProdutoAtivo(pedProd.getProduto().getId())) {
        produto = ProdutoBusiness.findById(pedProd.getProduto().getId());
        if (produto == null) {
            return false;
        }
        }
        filterPed_Prod(pedProd);
        diminuirQtdProduto(pedProd);
        return true;
    }
    
    private static void diminuirQtdProduto(Ped_Prod pedProd) {
        Produto produto = ProdutoBusiness.findById(pedProd.getProduto().getId());
        int qtdProduto = produto.getQuantidade();
        produto.setQuantidade(qtdProduto - pedProd.getQuantidade());
        ProdutoDAO.update(produto);
    }
    
    public static void delete(Ped_Prod pedProd) {
        Produto produto = pedProd.getProduto();
        produto.setQuantidade(produto.getQuantidade() + pedProd.getQuantidade());
        ProdutoDAO.update(produto);
        Ped_ProdDAO.delete(pedProd);
    }
    
    public static void deleteByPedido(int id) {
        Pedido pedido = PedidoBusiness.getPedido(id);
        for (Ped_Prod pedProd : pedido.getProdutos()) {
            Produto produto = ProdutoBusiness.findById(pedProd.getProduto().getId());
            produto.setQuantidade(produto.getQuantidade() + pedProd.getQuantidade());
            ProdutoDAO.update(produto);
            Ped_ProdDAO.delete(pedProd);
        }
    }
    
    public static void updateQuantidade(Ped_Prod pedProd, Produto produto) {
        Ped_ProdDAO.update(pedProd);
        ProdutoDAO.update(produto);
    }
    
}
