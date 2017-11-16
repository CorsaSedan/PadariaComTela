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
public class Fornecedor extends Pessoa implements iJuridica{
    private String cnpj;

    public Fornecedor(String cnpj, int id, String nome) {
        super(id, nome);
        this.cnpj = cnpj;
    }

    public Fornecedor() {
    }

    @Override
    public String getCnpj() {
        return cnpj;
    }

    @Override
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    
}
