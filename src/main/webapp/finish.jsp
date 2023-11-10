<%@ page import="GameServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%if (GameServlet.getWinValue()) { %>
    <title>WINNER!</title>
    <% } %>
    <%if (!GameServlet.getWinValue()) { %>
    <title>LOSER!</title>
    <% } %>
</head>
<body>
<%if (GameServlet.getWinValue()) { %>
<h1><br><br><br>ПОБЕДА!</h1>
<form action="/hello">
    <button>Заново</button>
</form>
<% } %>
<%if (!GameServlet.getWinValue()) { %>
<h1><br><br><br>ТЫ ПРОИГРАЛ!</h1>
<form action="/hello">
    <button>Заново</button>
</form>
<% } %>
</body>
</html>