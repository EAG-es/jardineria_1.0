<%-- 
    Document   : consulta
    Created on : 23 nov 2021, 9:52:23
    Author     : informatica
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean class="inser.web.jardineria.Producto" scope="request" id="producto" />
<% producto.iniciar_javabean(request, response, out, session);%>
<a href="${pageContext.request.contextPath}/index/producto/${producto.getCreacion()}">Crear nuevo</a> <br>
<table>
    <h2>${producto.mensaje}</h2>
    <thead>
        <tr>
            <th>
                Código producto
            </th>
            <th>
                Nombre
            </th>
            <th>
                Gama
            </th>
            <th>
                Dimensiones
            </th>
            <th>
                Proveedor
            </th>
            <th>
                Descripción
            </th>
            <th>
                Cantidad en stock
            </th>
            <th>
                Precio de venta
            </th>
            <th>
                Precio de proveedor
            </th>
        </tr>
    </thead>
    <tbody>
        <% while (producto.presentar_siguiente()) { %>
        <tr>
            <td>
                ${producto.presentar("codigo_producto")}
            </td>
            <td>
                ${producto.presentar("nombre")}
            </td>
            <td>
                ${producto.presentar("gama")}
            </td>
            <td>
                ${producto.presentar("dimensiones")}
            </td>
            <td>
                ${producto.presentar("proveedor")}
            </td>
            <td>
                ${producto.presentar("descripcion")}
            </td>
            <td>
                ${producto.presentar("cantidad_en_stock")}
            </td>
            <td>
                ${producto.presentar("precio_venta")}
            </td>
            <td>
                ${producto.presentar("precio_proveedor")}
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/index/producto/${producto.getModificacion()}/${producto.presentar("codigo_producto")}"">Editar</a> 
                <a href="${pageContext.request.contextPath}/index/producto/${producto.getBorrado()}/${producto.presentar("codigo_producto")}"">Borrar</a>
            </td>
        </tr>
        <% } %>       
    </tbody>
</table>
