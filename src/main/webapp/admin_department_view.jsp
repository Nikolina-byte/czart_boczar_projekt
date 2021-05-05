<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cereals</title>
</head>
<body>
<div>
    <ul class="nav nav-pills flex-column">
        <li class="nav-item">
            <a class="nav-link active" href="#">Informacje o departamencie</a>
        </li>
    </ul>
</div>

<h1><%= "Depatamenty" %></h1>
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

        <tr >
            <td>${tmpCereal2.getId()}</td>
            <td>${tmpCereal2.getName()}</td>
            <td>${tmpCereal2.getEmployees()}</td>
            <td>${tmpCereal2.getManager()}</td>
        </tr>

    </c:forEach>
    </tbody>
</table>

</body>
</html>