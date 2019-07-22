<%-- 
    Document   : show_user
    Created on : 2019/7/18, 上午 11:32:13
    Author     : Administrator
--%>

<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <h1><%= new Date()%></h1>
        <h1>${method}</h1>
    </body>
</html>
