<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
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

<div>
    <ul class="nav nav-pills flex-column">
        <li class="nav-item">
            <a class="nav-link active" href="#">Informacje o departamencie</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" href="AdminApplicationServlet">Informacje o departamencie</a>
        </li>
    </ul>
</div>

<div class="main">
    <div class="container">

        <h1><%= "Działy" %>
        </h1>
        <table border>
            <thead>
            <tr>
                <th scope="col">id</th>
                <th scope="col">name</th>
                <th scope="col">employees</th>
                <th scope="col">manager</th>
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