<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
    </head>

    <body>

        <c:forEach var="post" items="${posts}">
            <c:out value="${post.getPostTitle()}"></c:out> - 
            <c:out value="${post.getPostImage()}"></c:out> 
            <a href="borrar/<c:out value="${post.getPostId()}"></c:out>"><button>Borrar</button></a><hr>
        </c:forEach>




    </body>
</html>
