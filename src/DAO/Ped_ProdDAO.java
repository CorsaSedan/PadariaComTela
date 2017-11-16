package DAO;

import Model.Ped_Prod;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Ped_ProdDAO {

    private static ConnectionManager mngr = ConnectionManager.getInstance();

    public static Ped_Prod findById(int id) {
        String sql = "SELECT * FROM ped_prod WHERE id = ?";
        Ped_Prod pedProd = null;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();
            while (rs.next()) {
                Ped_Prod ped_Prod = new Ped_Prod();
                ped_Prod.setId(rs.getInt("id"));
                ped_Prod.setPedido(PedidoDAO.findById(rs.getInt("id_pedido")));
                ped_Prod.setProduto(ProdutoDAO.findById(rs.getInt("id_produto")));
                ped_Prod.setQuantidade(rs.getInt("qtdProduto"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return pedProd;
    }

    public static List<Ped_Prod> findByPedido(int id) {
        String sql = "SELECT * FROM ped_prod WHERE id_pedido=?";
        List<Ped_Prod> produtos = new ArrayList();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();
            while (rs.next()) {
                Ped_Prod ped_Prod = new Ped_Prod();
                ped_Prod.setId(rs.getInt("id"));
                ped_Prod.setPedido(PedidoDAO.findById(rs.getInt("id_pedido")));
                ped_Prod.setProduto(ProdutoDAO.findById(rs.getInt("id_produto")));
                ped_Prod.setQuantidade(rs.getInt("qtdProduto"));
                produtos.add(ped_Prod);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return produtos;
    }

    public static List<Ped_Prod> findByProduto(int id) {
        String sql = "SELECT * FROM ped_prod WHERE id_produto=?";
        List<Ped_Prod> produtos = new ArrayList();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();
            while (rs.next()) {
                Ped_Prod ped_Prod = new Ped_Prod();
                ped_Prod.setId(rs.getInt("id"));
                ped_Prod.setPedido(PedidoDAO.findById(rs.getInt("id_pedido")));
                ped_Prod.setProduto(ProdutoDAO.findById(rs.getInt("id_produto")));
                ped_Prod.setQuantidade(rs.getInt("qtdProduto"));
                produtos.add(ped_Prod);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return produtos;
    }

    public static List<Ped_Prod> findAll() {
        String sql = "SELECT * FROM ped_Prod";
        List<Ped_Prod> ped_Prods = new ArrayList();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();
            while (rs.next()) {
                Ped_Prod ped_Prod = new Ped_Prod();
                ped_Prod.setId(rs.getInt("id"));
                ped_Prod.setPedido(PedidoDAO.findById(rs.getInt("id_pedido")));
                ped_Prod.setProduto(ProdutoDAO.findById(rs.getInt("id_produto")));
                ped_Prod.setQuantidade(rs.getInt("qtdProduto"));
                ped_Prods.add(ped_Prod);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return ped_Prods;
    }

    public static void update(Ped_Prod ped_Prod) {
        String sql = "UPDATE ped_Prod SET qtdProduto=? WHERE id=?";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, ped_Prod.getQuantidade());
            stmt.setInt(2, ped_Prod.getId());

            stmt.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt);
            JDBCUtil.close(conn);
        }
    }

    public static void create(Ped_Prod ped_Prod) {
        String sql = "INSERT INTO ped_Prod (qtdProduto, id_pedido, id_produto) VALUES (?, ?, ?)";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setDouble(1, ped_Prod.getQuantidade());
            stmt.setInt(2, ped_Prod.getPedido().getId());
            stmt.setInt(3, ped_Prod.getProduto().getId());

            stmt.executeUpdate();
            conn.commit();
            rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                ped_Prod.setId(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
    }

    public static void delete(Ped_Prod ped_Prod) {
        delete(ped_Prod.getId());
    }

    public static void delete(int id) {
        String sql = "DELETE FROM ped_Prod WHERE id = ?";

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

    public static void deleteByPedido(int id) {
        String sql = "DELETE FROM ped_prod WHERE id_pedido = ?";

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
