/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.ProdutoDAO;
import Model.Produto;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @author www.codejava.net
 */
public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
 
    
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
 
        try {
            switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertProduto(request, response);
                break;
            case "/delete":
                deleteProduto(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateProduto(request, response);
                break;
            default:
                listarProdutos(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    private void listarProdutos(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException, ClassNotFoundException {
        List<Produto> listaProdutos = ProdutoDAO.listarProdutos();
        request.setAttribute("listaProdutos", listaProdutos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ProdutoForm.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("ProdutoForm.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        Produto existingProduto = ProdutoDAO.getProduto(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ProdutoForm.jsp");
        request.setAttribute("produto", existingProduto);
        dispatcher.forward(request, response);
 
    }
 
    private void insertProduto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ClassNotFoundException {
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        double valorCompra = Double.parseDouble(request.getParameter("valorCompra"));
        double valorVenda = Double.parseDouble(request.getParameter("valorVenda"));
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        String categoria = request.getParameter("categoria");
 
        Produto newProduto = new Produto(nome, descricao, valorCompra, valorVenda, quantidade, categoria);
        ProdutoDAO.insertProduto(newProduto);
        response.sendRedirect("listaProdutos");
    }
 
    private void updateProduto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        double valorCompra = Double.parseDouble(request.getParameter("valorCompra"));
        double valorVenda = Double.parseDouble(request.getParameter("valorVenda"));
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        String categoria = request.getParameter("categoria");
 
        Produto produto = new Produto(id, nome, descricao, valorCompra, valorVenda, quantidade, categoria);
        ProdutoDAO.updateProduto(produto);
        response.sendRedirect("listaProdutos");
    }
 
    private void deleteProduto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        Produto produto = new Produto(id);
        ProdutoDAO.deleteProduto(produto);
        response.sendRedirect("listaProdutos");
 
    }
}
