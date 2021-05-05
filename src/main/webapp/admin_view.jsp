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

</body>
</html>