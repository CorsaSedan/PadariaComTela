/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.FormaPagamento;
import Model.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cristhian_anacleto
 */
public class PedidoDAO {

    private static ConnectionManager mngr = ConnectionManager.getInstance();

    public static Pedido findById(int id) {
        String sql = "SELECT * FROM pedido WHERE id = ?";
        Pedido pedido = null;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();
            while (rs.next()) {
                pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                pedido.setCliente(ClienteDAO.findById(rs.getInt("id_cliente")));
                pedido.setFuncionario(FuncionarioDAO.findById(rs.getInt("id_funcionario")));
                pedido.setDataPedido(rs.getDate("dataPedido"));
                pedido.setFormaPagamento(FormaPagamento.valueOf(rs.getString("formaPagamento")));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return pedido;
    }

    public static List<Pedido> findAll() {
        String sql = "SELECT * FROM pedido";
        List<Pedido> pedidos = new ArrayList();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                pedido.setCliente(ClienteDAO.findById(rs.getInt("id_cliente")));
                pedido.setFuncionario(FuncionarioDAO.findById(rs.getInt("id_funcionario")));
                pedido.setDataPedido(rs.getDate("dataPedido"));
                pedido.setFormaPagamento(FormaPagamento.valueOf(rs.getString("formaPagamento")));
                pedidos.add(pedido);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return pedidos;
    }

    public static Pedido create(Pedido pedido) {
        String sql = "INSERT INTO pedido (dataPedido, formaPagamento, id_cliente, id_funcionario) VALUES (?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setDate(1, pedido.getDataPedido());
            stmt.setString(2, pedido.getFormaPagamento().name());
            stmt.setInt(3, pedido.getCliente().getId());
            stmt.setInt(4, pedido.getFuncionario().getId());

            stmt.executeUpdate();
            conn.commit();
            rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                pedido.setId(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
        
        return pedido;
    }
    
    public static void delete(Pedido pedido) {
        delete(pedido.getId());
    }
    
    public static void delete(int id){
        String sql = "DELETE FROM pedido WHERE id = ?";

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

}
