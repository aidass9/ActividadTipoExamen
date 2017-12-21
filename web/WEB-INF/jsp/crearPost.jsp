<%-- 
    Document   : crearPost
    Created on : 20-dic-2017, 15:47:03
    Author     : Aida
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear post</title>
    </head>
    <body>
        <%

            if (request.getSession().getAttribute("error") != null) {
                out.println("<div class='error'>" + request.getSession().getAttribute("error").toString() + "</div>");
                request.getSession().removeAttribute("error");
            }
        %>
        
        <form method="POST" action="<c:url value='/crear'/>">
            Título: <input type="text" name="postTitle" value="<c:out value="${postTitle}"/>"><br>
            Slug: <input type="text" name="postSlug" value="<c:out value="${postSlug}"/>"><br>
            Body: <textarea name="postBody">${postBody}</textarea> <br>
            <input type="submit" value="Crear">
              
            
        </form>
    </body>
</html>
