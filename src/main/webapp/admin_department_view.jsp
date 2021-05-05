<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<head>
    <title>Manager - Department</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">

</head>
<body>

<!--pasek-->
<nav class="nav" role="navigation">
    <div id="navbar" class="navbar-collapse collapse navbar-right">
        <a class="btn" href="login.html" role="button">Zaloguj się</a>
    </div>
    <div class="container">
        <ul>
            <li><a href="index.html">Strona główna</a></li>
        </ul>
    </div>
</nav>

<%--side bar--%>
<div class="w3-sidebar w3-light-grey w3-bar-block" style="width:25%">
    <h3 class="w3-bar-item">Menu</h3>
    <a href="${department}" class="w3-bar-item w3-button">Informacje o dziale</a>
    <a href="#" class="w3-bar-item w3-button">Link 2</a>
    <a href="#" class="w3-bar-item w3-button">Link 3</a>
</div>

<%

%>

<%--page content--%>
<div style="margin-left:25%">
    <div class="w3-container">
        <h1>Informacje o Twoim dziale</h1>
        <h2>Pracownicy</h2>
        <ol>
            <li>
            </li>
        </ol>

        <h1><%= "Działy" %>
        </h1>
        <table border>
            <thead>
            <tr>
                <th scope="col">id</th>
                <th scope="col">Nazwa działu</th>
                <th scope="col">Pracownicy</th>
                <th scope="col">Kierownik</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="tmpCereal2" items="${DEPARTMENT_LIST}">

                <tr>
                    <td>${tmpCereal2.getId()}</td>
                    <td>${tmpCereal2.getName()}</td>
                    <td>${tmpCereal2.getEmployees()}</td>
                    <td>${tmpCereal2.getManager()}</td>
                </tr>

            </c:forEach>
            </tbody>
        </table>
    </div>

</div>
</body>
</html>