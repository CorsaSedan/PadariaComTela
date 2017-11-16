/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Anacleto Sotfwares Ltda.®
 *         All Rigths Reserved
 *         If you want to copy, I see you in court
 */
public class Funcionario extends Pessoa implements iFisica {

    private String cpf;
    private Cargo cargo;
    private double salario;

    public Funcionario(String cpf, Cargo cargo, double salario, int id, String nome) {
        super(id, nome);
        this.cpf = cpf;
        this.cargo = cargo;
        this.salario = salario;
    }

    public Funcionario() {
    }

    public Funcionario(String cpf, Cargo cargo, double salario) {
        this.cpf = cpf;
        this.cargo = cargo;
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String getCpf() {
        return cpf;
    }

    @Override
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}
