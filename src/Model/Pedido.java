/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Anacleto Sotfwares Ltda.® All Rigths Reserved If you want to copy, I
 * see you in court
 */
public class Pedido {

    private int id;
    private Date dataPedido;
    private Cliente cliente;
    private Funcionario funcionario;
    private List<Ped_Prod> produtos = new ArrayList<>();
    private FormaPagamento formaPagamento;

    public Pedido() {
    }

    public Pedido(Date dataPedido, Cliente cliente, Funcionario funcionario, FormaPagamento formaPagamento) {
        this.dataPedido = dataPedido;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.formaPagamento = formaPagamento;
    }

    public Pedido(int id, Date dataPedido, Cliente cliente, Funcionario funcionario, FormaPagamento formaPagamento) {
        this.id = id;
        this.dataPedido = dataPedido;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.formaPagamento = formaPagamento;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Ped_Prod> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Ped_Prod> produtos) {
        this.produtos = produtos;
    }

    
    public double getPrecoTotal(){
        double preco = 0;
        for (int i = 0; i < produtos.size(); i++) {
            preco += produtos.get(i).getPreco();
        }
        if (this.formaPagamento == FormaPagamento.DINHEIRO) {
            preco -= preco*0.05;
        }
        return preco;
    }
     
}
