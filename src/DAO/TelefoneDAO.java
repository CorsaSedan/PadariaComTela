/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Cliente;
import Model.Fornecedor;
import Model.Funcionario;
import Model.Telefone;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cristhian_anacleto
 */
public class TelefoneDAO {
    
    private static ConnectionManager mngr = ConnectionManager.getInstance();

    public static Telefone findById(int id) {
        String sql = "SELECT * FROM telefone WHERE id = ?";
        Telefone telefone = null;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();
            while (rs.next()) {
                telefone = new Telefone();
                telefone.setId(rs.getInt("id"));
                telefone.setNumero(rs.getString("numero"));
                if (rs.getInt("id_cliente") != 0) {
                    telefone.setDono(ClienteDAO.findById(rs.getInt("id_cliente")));
                } else if (rs.getInt("id_funcionario") != 0){
                    telefone.setDono(FuncionarioDAO.findById(rs.getInt("id_funcionario")));
                } else if (rs.getInt("id_fornecedor") != 0) {
                    telefone.setDono(FornecedorDAO.findById(rs.getInt("id_fornecedor")));
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return telefone;
    }

    public static List<Telefone> findAll() {
        String sql = "SELECT * FROM telefone";
        List<Telefone> telefones = new ArrayList();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();
            while (rs.next()) {
                Telefone telefone = new Telefone();
                telefone.setId(rs.getInt("id"));
                telefone.setNumero(rs.getString("numero"));
                if (rs.getInt("id_cliente") != 0) {
                    telefone.setDono(ClienteDAO.findById(rs.getInt("id_cliente")));
                } else if (rs.getInt("id_funcionario") != 0){
                    telefone.setDono(FuncionarioDAO.findById(rs.getInt("id_funcionario")));
                } else if (rs.getInt("id_fornecedor") != 0) {
                    telefone.setDono(FornecedorDAO.findById(rs.getInt("id_fornecedor")));
                }
                telefones.add(telefone);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return telefones;
    }
    
    public static void update(Telefone telefone){
        String sql = "UPDATE telefone SET numero=? WHERE id=?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, telefone.getNumero());
            stmt.setInt(2, telefone.getId());
            
            stmt.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt);
            JDBCUtil.close(conn);
        }
    }
    
    public static void create (Telefone telefone){
        String sql = null;
        if (telefone.getDono() instanceof  Cliente) {
            sql = "INSERT INTO telefone (numero, id_cliente) VALUES (?, ?)";
        } else if (telefone.getDono() instanceof  Funcionario) {
            sql = "INSERT INTO telefone (numero, id_funcionario) VALUES (?, ?)";
        } else if (telefone.getDono() instanceof  Fornecedor) {
            sql = "INSERT INTO telefone (numero, id_fornecedor) VALUES (?, ?)";
        }
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, telefone.getNumero());
            stmt.setInt(2, telefone.getDono().getId());

            stmt.executeUpdate();
            conn.commit();
            rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                telefone.setId(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
    }
    
    public static void delete(Telefone telefone) {
        delete(telefone.getId());
    }

    public static void delete(int id) {
        String sql = "DELETE FROM telefone WHERE id = ?";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            stmt.executeUpdate();
            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
    }
    
    public static List<Telefone> findByCliente(int id) {
        String sql = "SELECT * FROM telefone WHERE id_cliente = ?";
        List<Telefone> telefones = new ArrayList();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while (rs.next()) {
                Telefone telefone = new Telefone();
                telefone.setId(rs.getInt("id"));
                telefone.setNumero(rs.getString("numero"));
                telefone.setDono(ClienteDAO.findById(rs.getInt("id_cliente")));

                telefones.add(telefone);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
        return telefones;
    }
    
    public static List<Telefone> findByFuncionario(int id) {
        String sql = "SELECT * FROM telefone WHERE id_funcionario = ?";
        List<Telefone> telefones = new ArrayList();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while (rs.next()) {
                Telefone telefone = new Telefone();
                telefone.setId(rs.getInt("id"));
                telefone.setNumero(rs.getString("numero"));
                telefone.setDono(FuncionarioDAO.findById(rs.getInt("id_funcionario")));

                telefones.add(telefone);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
        return telefones;
    }
    
    public static List<Telefone> findByFornecedor(int id) {
        String sql = "SELECT * FROM telefone WHERE id_fornecedor = ?";
        List<Telefone> telefones = new ArrayList();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while (rs.next()) {
                Telefone telefone = new Telefone();
                telefone.setId(rs.getInt("id"));
                telefone.setNumero(rs.getString("numero"));
                telefone.setDono(FornecedorDAO.findById(rs.getInt("id_fornecedor")));

                telefones.add(telefone);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
        return telefones;
    }
    
}
