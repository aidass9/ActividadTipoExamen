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
        
        <a href="<c:url value="/formularioCrear"/>"><button>CREAR</button></a>
        <%

            if (request.getSession().getAttribute("mensaje") != null) {
                out.println("<div class='mensaje'>" + request.getSession().getAttribute("mensaje").toString() + "</div>");
                request.getSession().removeAttribute("mensaje");
            }

            if (request.getSession().getAttribute("error") != null) {
                out.println("<div class='error'>" + request.getSession().getAttribute("error").toString() + "</div>");
                request.getSession().removeAttribute("error");
            }
        %>

        <c:forEach var="post" items="${posts}">
            <a href="<c:url value="/${post.getPostSlug()}"/>">VER MÁS</a>
            <c:out value="${post.getPostTitle()}"></c:out> - 
            <img src="<c:url value="/imagenes/${post.getPostImage()}"/>">
            <a href="<c:url value="/borrar/${post.getPostId()}"/>"><button>Borrar</button></a><hr>
        </c:forEach>




    </body>
</html>
