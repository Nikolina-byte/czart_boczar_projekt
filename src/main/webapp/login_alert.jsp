<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<head>
    <meta charset="utf-8">
    <title>Employee Vacation Form</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">


</head>
<body onload="start();">

<!--pasek główny-->
<nav class="nav" role="navigation">
    <div class="container">
        <ul>
            <li><a href="index.html">Strona główna</a></li>
        </ul>
    </div>
</nav>



<div class="header">
</div>


<div class="row form-group"></div>
<div class="row form-group"></div>
<div class="row form-group"></div>

<div class="jumbotron">
    <div class="container">
        <h1>Zaloguj się</h1>
        <h2>Niepoprawne dane. Spróbuj jeszcze raz</h2>

        <form action="LoginServlet" method="post">
            <div class="form-group">
                <label for="loginInput">Login</label>
                <label>
                    <input type="text" class="form-control" name="loginInput" id = "loginInput" placeholder="Wprowadź login" required>
                </label>
                <small id="emailHelp" class="form-text text-muted">Login powinien mieć długość 4 znaków.</small>
            </div>
            <div class="form-group">
                <label for="passwordInput">Hasło</label>
                <label>
                    <input type="password" class="form-control" id = "passwordInput" name="passwordInput" placeholder="Hasło" required>
                </label>
            </div>
            <button type="submit" class="btn btn-primary">Dalej</button>
            <br>
            <small class="form-text text-muted">Nie masz jeszcze konta? Założysz je <a link href="register_form.html">tutaj</a></small>

        </form>


    </div>
</div>






</body>
<div class="footer">
    <p>
        <a href="https://pbs.twimg.com/media/DXoBwnAVQAAvx0N.jpg">&copy; Adrianna Boczar 250319</a>
    </p>
</div>
</html>
