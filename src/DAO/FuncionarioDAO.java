/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cristhian_anacleto
 */
public class FuncionarioDAO {
    
    private static ConnectionManager mngr = ConnectionManager.getInstance();

    public static Funcionario findById(int id) {
        String sql = "SELECT * FROM funcionario WHERE id = ?";
        Funcionario funcionario = null;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();
            while (rs.next()) {
                funcionario = new Funcionario();
                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setSalario(rs.getDouble("salario"));
                funcionario.setCargo(CargoDAO.findById(rs.getInt("id_cargo")));
                funcionario.setEstado(rs.getBoolean("estado"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return funcionario;
    }
    
    public static Funcionario findByCpf(String cpf) {
        String sql = "SELECT * FROM funcionario WHERE cpf = ?";
        Funcionario funcionario = null;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf);

            rs = stmt.executeQuery();
            while (rs.next()) {
                funcionario = new Funcionario();
                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setSalario(rs.getDouble("salario"));
                funcionario.setCargo(CargoDAO.findById(rs.getInt("id_cargo")));
                funcionario.setEstado(rs.getBoolean("estado"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return funcionario;
    }

    public static List<Funcionario> findAll() {
        String sql = "SELECT * FROM funcionario";
        List<Funcionario> funcionarios = new ArrayList();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setSalario(rs.getDouble("salario"));
                funcionario.setCargo(CargoDAO.findById(rs.getInt("id_cargo")));
                funcionario.setEstado(rs.getBoolean("estado"));
                funcionarios.add(funcionario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return funcionarios;
    }
    
    public static List<Funcionario> findByEstado(boolean estado) {
        String sql = "SELECT * FROM funcionario WHERE estado = ?";
        List<Funcionario> funcionarios = new ArrayList();
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setBoolean(1, estado);

            rs = stmt.executeQuery();
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setSalario(rs.getDouble("salario"));
                funcionario.setCargo(CargoDAO.findById(rs.getInt("id_cargo")));
                funcionario.setEstado(rs.getBoolean("estado"));
                funcionarios.add(funcionario);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
        
        return funcionarios;
    }
    
    public static void update(Funcionario funcionario){
        String sql = "UPDATE funcionario SET nome=?, salario=?, id_cargo=? WHERE id=?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, funcionario.getNome());
            stmt.setDouble(2, funcionario.getSalario());
            stmt.setInt(3, funcionario.getCargo().getId());
            stmt.setInt(4, funcionario.getId());
            
            stmt.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt);
            JDBCUtil.close(conn);
        }
    }
    
    public static void create (Funcionario funcionario){
        String sql = "INSERT INTO funcionario (nome, cpf, id_cargo, salario, estado) VALUES (?, ?, ?, ?, true)";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setInt(3, funcionario.getCargo().getId());
            stmt.setDouble(4, funcionario.getSalario());

            stmt.executeUpdate();
            conn.commit();
            rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                funcionario.setId(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
    }
    
    public static void delete(Funcionario funcionario) {
        delete(funcionario.getId());
    }

    public static void delete(int id) {
        String sql = "UPDATE funcionario SET estado=false WHERE id=?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            
            stmt.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt);
            JDBCUtil.close(conn);
        }
    }
    
    public static void turnActive(int id) {
        String sql = "UPDATE funcionario SET estado=true WHERE id=?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            
            stmt.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt);
            JDBCUtil.close(conn);
        }
    }
    
}
