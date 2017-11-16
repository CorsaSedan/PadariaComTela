package Business;

import DAO.FornecedorDAO;
import DAO.ProdutoDAO;
import Model.Fornecedor;
import Model.Produto;
import java.util.List;

public class FornecedorBusiness {

    public static boolean create(String nome, String cnpj) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(nome);
        fornecedor.setCnpj(cnpj);
        if (verifyCnpj(fornecedor)) {
            FornecedorDAO.create(fornecedor);
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean turnOff(int id){
        if (FornecedorDAO.findById(id) != null) {
            FornecedorDAO.delete(id);
            for (Produto produto : ProdutoDAO.findByFornecedor(id)) {
                produto.setFornecedor(null);
                ProdutoDAO.deleteFornecedor(produto);
            }
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean update(int id, String nome){
        for (Fornecedor fornecedor : FornecedorDAO.findAll()) {
            if (fornecedor.getId() == id) {
                fornecedor.setNome(nome);
                FornecedorDAO.update(fornecedor);
                return true;
            }
        }
        return false;
    }
    
    public static void turnActive(int id) {
        FornecedorDAO.turnActive(id);
    }

    public static boolean verifyCnpj(Fornecedor fornecedor) {
        for (Fornecedor fornecedores : FornecedorDAO.findAll()) {
            if (fornecedores.getCnpj().equals(fornecedor.getCnpj())) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean verifyFornecedor(int id){
        if (FornecedorDAO.findById(id) == null) {
            return false;
        }
        return true;
    }
    
    public static boolean verifyFornecedorAtivo(int id) {
        for (Fornecedor fornecedor : FornecedorDAO.findByEstado(true)) {
            if (fornecedor.getId() == id) {
                return true;
            }
        }
        return false;
    }
    
    public static Fornecedor findById(int id) {
        return FornecedorDAO.findById(id);
    }
    
    public static List<Fornecedor> findAll() {
        return FornecedorDAO.findAll();
    }
    
    public static List<Fornecedor> findByEstado(boolean estado) {
        return FornecedorDAO.findByEstado(estado);
    }

}
