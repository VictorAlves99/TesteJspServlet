<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>NOOBIES</title>
</head>
<body>
    <center>
        <h1>Gerenciamento de Produtos</h1>
        <h2>
            <a href="/new">Adicionar Produto</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/list">Listar Todos</a>
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Lista de Produtos</h2></caption>
            <tr>
                <th>ID</th>
                <th>NOME</th>
                <th>V.VENDA</th>
                <th>V.COMPRA</th>
                <th>QUANTIDADE</th>
                <th>CATEGORIA</th>
                <th>ACTIONS</th>
            </tr>
            <c:forEach var="produto" items="${listaProdutos}">
                <tr>
                    <td><c:out value="${produto.id}" /></td>
                    <td><c:out value="${produto.nome}" /></td>
                    <td><c:out value="${produto.valorCompra}" /></td>
                    <td><c:out value="${produto.valorVenda}" /></td>
                    <td><c:out value="${produto.quantidade}" /></td>
                    <td><c:out value="${produto.categoria}" /></td>
                    <td>
                        <a href="/edit?id=<c:out value='${produto.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/delete?id=<c:out value='${produto.id}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>