<%-- 
    Document   : BlogDetalle
    Created on : 20-dic-2017, 15:22:37
    Author     : Aida
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detalle post</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <c:out value="${post.getPostTitle()}"/>
    </body>
</html>
