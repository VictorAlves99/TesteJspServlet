package DAO;

import Model.Produto;
import static Utils.ConnectionUtils.obterConexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
/**
 * AbstractDAO.java
 * This DAO class provides CRUD database operations for the table book
 * in the database.
 * @author www.codejava.net
 *
 */
public class ProdutoDAO {
     
    public static boolean insertProduto(Produto produto) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement statement = null;
        
        String sql = "INSERT INTO produtos (nome, descricao, valorCompra, valorVenda, quantidade, categoria) VALUES (?, ?, ?, ?, ?, ?)";
        
        conn = obterConexao();
        statement = conn.prepareStatement(sql);
        statement.setString(1, produto.getNomeProd());
        statement.setString(2, produto.getDescProd());
        statement.setDouble(3, produto.getValorCompra());
        statement.setDouble(1, produto.getValorVenda());
        statement.setInt(2, produto.getQuantidade());
        statement.setString(3, produto.getCategoria());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        conn.close();
        return rowInserted;
    }
     
    public static List<Produto> listarProdutos() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        
        List<Produto> listProdutos = new ArrayList<>();
         
        String sql = "SELECT * FROM produtos";
         
        conn = obterConexao();
        stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nome = resultSet.getString("nome");
            float valorCompra = resultSet.getFloat("valorCompra");
            float valorVenda = resultSet.getFloat("valorVenda");
            int quantidade = resultSet.getInt("quantidade");
            String categoria = resultSet.getString("categoria");
            
            Produto produto = new Produto(id, nome, valorCompra, valorVenda,quantidade,categoria);
            listProdutos.add(produto);
        }
         
        resultSet.close();
        stmt.close();
        conn.close();
         
        return listProdutos;
    }
     
    public static boolean deleteProduto(Produto produto) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement statement = null;
        
        String sql = "DELETE FROM produtos where id = ?";
         
        conn = obterConexao();
        statement = conn.prepareStatement(sql);
        statement.setInt(1, produto.getCodProduto());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        conn.close();
        return rowDeleted;     
    }
     
    public static boolean updateProduto(Produto produto) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement statement = null;
        
        String sql = "UPDATE produtos SET nome = ?, descricao = ?, valorCompra = ?, valorVenda = ?, quantidade = ?, categoria = ?";
        sql += " WHERE id = ?";
        
        conn = obterConexao();
        statement = conn.prepareStatement(sql);
        statement.setString(1, produto.getNomeProd());
        statement.setString(2, produto.getDescProd());
        statement.setDouble(3, produto.getValorCompra());
        statement.setDouble(4, produto.getValorVenda());
        statement.setInt(5, produto.getQuantidade());
        statement.setString(6, produto.getCategoria());
        statement.setInt(7, produto.getCodProduto());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        conn.close();
        return rowUpdated;     
    }
     
    public static Produto getProduto(int id) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement statement = null;
        
        Produto produto = null;
        String sql = "SELECT * FROM produtos WHERE id = ?";
         
        conn = obterConexao();
        statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String nome = resultSet.getString("nome");
            String descricao = resultSet.getString("descricao");
            double valorCompra = resultSet.getDouble("valorCompra");
            double valorVenda = resultSet.getDouble("valorVenda");
            int quantidade = resultSet.getInt("descricao");
            String categoria = resultSet.getString("categoria");
             
            produto = new Produto(id, nome, descricao, valorCompra, valorVenda,quantidade,categoria);
        }
         
        resultSet.close();
        statement.close();
         
        return produto;
    }
}
