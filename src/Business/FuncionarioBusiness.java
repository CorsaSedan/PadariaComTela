/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import DAO.CargoDAO;
import DAO.FuncionarioDAO;
import Model.Cargo;
import Model.Funcionario;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class FuncionarioBusiness {

    public static boolean create(String nome, String cpf, Cargo cargo, double Salario) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setCargo(cargo);
        funcionario.setSalario(Salario);
        if (verifyCpf(funcionario)) {
            FuncionarioDAO.create(funcionario);
            return true;
        }
        return false;
    }

    public static boolean delete(int id) {
        if (FuncionarioDAO.findById(id) != null) {
            FuncionarioDAO.delete(id);
            return true;
        } else {
            return false;
        }
    }
    
    public static void turnActive(int id) {
        FuncionarioDAO.turnActive(id);
    }

    public static boolean updateNome(int id, String nome) {
        for (Funcionario funcionarios : FuncionarioDAO.findAll()) {
            if (funcionarios.getId() == id) {
                funcionarios.setNome(nome);
                FuncionarioDAO.update(funcionarios);
                return true;
            }
        }

        return false;
    }

    public static boolean updateCargo(int id, int idCargo) {
        Funcionario funcionario = FuncionarioDAO.findById(id);
        Cargo cargo = CargoDAO.findById(idCargo);
        if (cargo == null) {
            return false;
        } else {
            funcionario.setCargo(cargo);
            FuncionarioDAO.update(funcionario);
            return true;
        }
    }

    public static boolean updateSalario(int id, double salario) {
        for (Funcionario funcionarios : FuncionarioDAO.findAll()) {
            if (funcionarios.getId() == id) {
                funcionarios.setSalario(salario);
                FuncionarioDAO.update(funcionarios);
                return true;
            }
        }

        return false;
    }

    public static boolean verifyCpf(Funcionario funcionario) {
        if (findByCpf(funcionario.getCpf()) == null) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean verifyCpf(String cpf) {
        if (findByCpf(cpf) == null) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean verifyFuncionario(int id) {
        if (FuncionarioDAO.findById(id) == null) {
            return false;
        } else {
            return true;
        }
    }
    
    public static boolean verifyFuncionarioAtivo(int id) {
        if (findById(id).isEstado()) {
            return true;
        } else {
            return false;
        }
    }
    
    public static Funcionario findById(int id){
        return FuncionarioDAO.findById(id);
    }
    
    public static Funcionario findByCpf(String cpf) {
        return FuncionarioDAO.findByCpf(cpf);
    }
    
    public static List<Funcionario> findAll(){
        return FuncionarioDAO.findAll();
    }
    
    public static List<Funcionario> findByEstado(boolean estado) {
        return FuncionarioDAO.findByEstado(estado);
    }

}
