<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>JSP-Servlet MVC MySQL Connection Example</title>
  </head>
  <body>

  <hr>
  <ol>
  <%
    List<String> list = (List<String>) request.getAttribute("list");
    for(String i : list){
        out.print("<li>"+i+"</li> <br/>");
    }

  %>
  </ol>

  <hr>

  </body>
</html>
