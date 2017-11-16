
package Business;

import DAO.ClienteDAO;
import DAO.FuncionarioDAO;
import DAO.Ped_ProdDAO;
import DAO.PedidoDAO;
import Model.Cliente;
import Model.FormaPagamento;
import Model.Funcionario;
import Model.Ped_Prod;
import Model.Pedido;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PedidoBusiness {

    private static Pedido create(Date dataPedido, Cliente cliente, Funcionario funcionario, FormaPagamento formaPagamento){
        Pedido pedido = new Pedido(dataPedido, cliente, funcionario, formaPagamento);
        pedido = PedidoDAO.create(pedido);
        return pedido;
    }
    
    public static Pedido create(Date dataPedido, int idCliente, int idFuncionario, FormaPagamento formaPagamento) {
        Cliente cliente = ClienteDAO.findById(idCliente);
        if (cliente == null) {
            return null;
        }
        Funcionario funcionario = FuncionarioDAO.findById(idFuncionario);
        if (funcionario == null) {
            return null;
        }
        Pedido pedido = create(dataPedido, cliente, funcionario, formaPagamento);
        
        return pedido;
    }
    
    public static Pedido getPedido(int id){
        //Método que retorna um pedido com sua lista de produtos
        Pedido pedido = PedidoDAO.findById(id);
        if (pedido == null) {
            return null;
        }
        
        List<Ped_Prod> produtos = Ped_ProdDAO.findByPedido(id);
        pedido.setProdutos(produtos);
        return pedido;
    } 
    
    public static Pedido findById(int id) {
        return PedidoDAO.findById(id);
    }
    
    public static List<Pedido> findAll() {
        List<Pedido> pedidos = new ArrayList();
        for (Pedido pedido : PedidoDAO.findAll()) {
            pedido = getPedido(pedido.getId());
            pedidos.add(pedido);
        }
        return pedidos;
    }
    
    public static void delete(Pedido pedido) {
        PedidoDAO.delete(pedido);
    }
    
}
