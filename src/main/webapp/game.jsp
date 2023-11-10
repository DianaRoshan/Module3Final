<%@ page import="GameServlet" %>
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
    <div><h3>${question}</h3></div>

    <div>
        <form method="post" action="${pageContext.request.contextPath}/game">
            <c:forEach var="answer" items="${answers}">
                <input type="radio" name="answer" value="${answer}">
                <label>${answer}</label>
                <br>
            </c:forEach>
            <br>
            <input type="submit" value="Дальше">
        </form>
    </div>
</div>
</body>
</html>
