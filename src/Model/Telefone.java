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
public class Telefone {
    private int id;
    private String numero;
    private Pessoa dono;

    public Telefone(int id, String numero, Pessoa dono) {
        this.id = id;
        this.numero = numero;
        this.dono = dono;
    }

    public Telefone() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Pessoa getDono() {
        return dono;
    }

    public void setDono(Pessoa dono) {
        this.dono = dono;
    }
}
