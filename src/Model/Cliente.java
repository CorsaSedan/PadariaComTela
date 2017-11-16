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
public class Cliente extends Pessoa implements iFisica{
    private String cpf;

    public Cliente(String cpf, int id, String nome) {
        super(id, nome);
        this.cpf = cpf;
    }

    public Cliente() {
    }

    @Override
    public String getCpf() {
        return cpf;
    }

    @Override
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
}
