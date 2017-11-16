/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import DAO.ClienteDAO;
import Model.Cliente;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class ClienteBusiness {
    
    public static boolean create(String nome, String cpf) {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        if (verifyCpf(cliente)) {
            ClienteDAO.create(cliente);
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean delete(int id){
        if (ClienteDAO.findById(id) != null) {
            ClienteDAO.delete(id);
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean update(int id, String nome){
        for (Cliente clientes : ClienteDAO.findAll()) {
            if (clientes.getId() == id) {
                clientes.setNome(nome);
                ClienteDAO.update(clientes);
                return true;
            }
        }
        return false;
    }
    
    public static void turnActive(int id) {
        ClienteDAO.turnActive(id);
    }
    
    public static boolean verifyCpf(Cliente cliente){
        for (Cliente clientes : ClienteDAO.findAll()) {
            if (clientes.getCpf().equals(cliente.getCpf())) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean verifyCliente(int id){
        if (ClienteDAO.findById(id) == null) {
            return false;
        } else {
            return true;
        }
    }
    
    public static boolean verifyClienteAtivo(int id) {
        for (Cliente cliente : ClienteDAO.findByEstado(true)) {
            if (cliente.getId() == id) {
                return true;
            }
        }
        return false;
    }
    
    public static Cliente findById(int id){
        return ClienteDAO.findById(id);
    }
    
    public static List<Cliente> findAll(){
        return ClienteDAO.findAll();
    }
    
    public static List<Cliente> findByEstado(boolean estado) {
        return ClienteDAO.findByEstado(estado);
    }
    
    
}
