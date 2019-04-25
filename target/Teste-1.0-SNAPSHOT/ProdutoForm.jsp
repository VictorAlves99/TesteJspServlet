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
            <a href="/list">Listar Produtos</a>
             
        </h2>
    </center>
    <div align="center">
        <c:if test="${produto != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${produto == null}">
            <form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${produto != null}">
                        Edit Prod
                    </c:if>
                    <c:if test="${produto == null}">
                        Add New Prod
                    </c:if>
                </h2>
            </caption>
                <c:if test="${produto != null}">
                    <input type="hidden" name="id" value="<c:out value='${produto.id}' />" />
                </c:if>           
            <tr>
                <th>Nome: </th>
                <td>
                    <input type="text" name="nome" size="45"
                            value="<c:out value='${produto.nome}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Descrição: </th>
                <td>
                    <input type="text" name="descricao" size="45"
                            value="<c:out value='${produto.descricao}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Valor de Compra: </th>
                <td>
                    <input type="text" name="valorCompra" size="5"
                            value="<c:out value='${produto.valorCompra}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Valor de Venda: </th>
                <td>
                    <input type="text" name="valorVenda" size="45"
                            value="<c:out value='${produto.valorVenda}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Quantidade: </th>
                <td>
                    <input type="text" name="quantidade" size="45"
                            value="<c:out value='${produto.quantidade}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Categoria: </th>
                <td>
                    <input type="text" name="categoria" size="5"
                            value="<c:out value='${produto.categoria}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>