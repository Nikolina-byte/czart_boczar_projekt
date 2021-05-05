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
    <c:url var="department" value="AdminDepartmentServlet">
        <c:param name="employeeID" value="${EMPLOYEE}"></c:param>
    </c:url>
    <c:url var="application" value="AdminApplicationServlet">
        <c:param name="employeeID" value="${EMPLOYEE}"></c:param>
    </c:url>
    <c:url var="employee" value="AdminEmployeeServlet">
        <c:param name="employeeID" value="${EMPLOYEE}"></c:param>
    </c:url>
    <c:url var="history" value="AdminHistoryServlet">
        <c:param name="employeeID" value="${EMPLOYEE}"></c:param>
    </c:url>

    <ul class="nav nav-pills flex-column">
        <li class="nav-item">
            <a class="nav-link" href="${department}">Informacje o departamencie</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${application}">Wnioski do rozpatrzenia</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${employee}">Pracownicy</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${history}">Historia zwolnień</a>
        </li>
    </ul>
</div>

<h1><%= "Pracownicy projektu 1:" %></h1>
<table border>
    <thead>
    <tr>
        <th scope="col">id</th>
        <th scope="col">name</th>
        <th scope="col">birth</th>
        <th scope="col">email</th>
        <th scope="col">phone</th>
        <th scope="col">department</th>
        <th scope="col">manager</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="tmpCereal" items="${EMPLOYEE_LIST}">

    <c:url var="deleteLink" value="AdminEmployeeServlet">
        <c:param name="command" value="DELETE"></c:param>
        <c:param name="employeeID" value="${tmpCereal.id}"></c:param>
    </c:url>

    <tr >
        <td>${tmpCereal.getId()}</td>
        <td>${tmpCereal.getName()}</td>
        <td>${tmpCereal.getBirth()}</td>
        <td>${tmpCereal.getEmail()}</td>
        <td>${tmpCereal.getPhone()}</td>
        <td>${tmpCereal.getDepartment()}</td>
        <td>${tmpCereal.getProjects()}</td>
        <td>${tmpCereal.getManager()}</td>
        <td><a href="${deleteLink}"
               onclick="if(!(confirm('Czy na pewno chcesz usunąć tego pracownika?'))) return false">
            <button type="button" class="btn btn-danger">Usuń</button>
        </a></td>
    </tr>

    </c:forEach>
</table>

</body>
</html>