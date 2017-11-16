/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.TelefoneDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Anacleto Sotfwares Ltda.® 
 *         All Rigths Reserved 
 *         If you want to copy, I see you in court
 */
public abstract class Pessoa {

    private int id;
    private String nome;
    private boolean estado;

    public Pessoa(int id, String nome, boolean estado) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
    }

    public Pessoa(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Pessoa() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public List<Telefone> getTelefones(){
        List<Telefone> telefones = new ArrayList();
        if (this instanceof Cliente) {
            telefones = TelefoneDAO.findByCliente(id);
        } else if (this instanceof Funcionario){
            telefones =  TelefoneDAO.findByFuncionario(id);
        } else if (this instanceof  Fornecedor){
            telefones = TelefoneDAO.findByFornecedor(id);
        }
        return telefones;
    }

}
