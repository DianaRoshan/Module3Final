<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Game</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <meta charset = "UTF-8">

</head>
<body>
<div id="container">
    <%String question = (String) request.getAttribute("question");
     List<String> answers = (List<String>) request.getAttribute("answers");
    %>

    <div><h3><%=session.getAttribute("userName")%></h3></div>
    <div><h3><%=question%></h3></div>

    <div>
        <form method="post" action="/game">
                <%
                    for (String answer: answers) {
                %>
                <input type="radio" name="answer" value="<%=answer%>">
                <label><%=answer%></label>
                <br>
                <%}%>

            <br>
            <input type="submit" value="Дальше">
        </form>
    </div>
</div>
</body>
</html>
