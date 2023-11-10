<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset = "UTF-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
</head>
<script>
    $(document).ready(function() {
        $("#startBtn").click(function (){
            var name = $("#user-name").val();
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/hello",
                data: jQuery.param({userName : name}),
                success : function () {
                    location.replace("http://localhost:8080/game")
                }
            });
        });
    });
</script>

<body>
<div>
<h1>Добро пожаловать на борт!</h1>
<h2>Введите свое имя:</h2>
</div>
<div>
<input type="text" id="user-name">
<button id = "startBtn" type="button">Продолжить</button>
</div>
</body>
</html>
