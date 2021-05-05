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

<h1><%= "Wnioski zaakceptowane: " %></h1>
<table border>
    <thead>
    <tr>
        <th scope="col">id</th>
        <th scope="col">leaveType</th>
        <th scope="col">startDay</th>
        <th scope="col">endDay</th>
        <th scope="col">numberday</th>
        <th scope="col">status</th>
        <th scope="col">name</th>
        <th scope="col">department</th>
        <th scope="col">projects</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="tmpCereal1" items="${APPLICATIONS_LIST}">

    <tr >
        <td>${tmpCereal1.getId()}</td>
        <td>${tmpCereal1.getLeaveType()}</td>
        <td>${tmpCereal1.getStartDay()}</td>
        <td>${tmpCereal1.getEndDay()}</td>
        <td>${tmpCereal1.getNumberDay()}</td>
        <td>${tmpCereal1.getStatusApplicationType()}</td>
        <td>${tmpCereal1.getEmployeeName() }</td>
        <td>${tmpCereal1.getDepartmentName()}</td>
        <td>${tmpCereal1.getProjects()}</td>
    </tr>

    </c:forEach>
</table>

<h1><%= "Wnioski zrealizowane: " %></h1>
<table border>
    <thead>
    <tr>
        <th scope="col">id</th>
        <th scope="col">leaveType</th>
        <th scope="col">startDay</th>
        <th scope="col">endDay</th>
        <th scope="col">numberday</th>
        <th scope="col">status</th>
        <th scope="col">name</th>
        <th scope="col">department</th>
        <th scope="col">projects</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="tmpCereal1" items="${ZREALIZOWANE_APPLICATIONS_LIST}">

    <tr >
        <td>${tmpCereal1.getId()}</td>
        <td>${tmpCereal1.getLeaveType()}</td>
        <td>${tmpCereal1.getStartDay()}</td>
        <td>${tmpCereal1.getEndDay()}</td>
        <td>${tmpCereal1.getNumberDay()}</td>
        <td>${tmpCereal1.getStatusApplicationType()}</td>
        <td>${tmpCereal1.getEmployeeName() }</td>
        <td>${tmpCereal1.getDepartmentName()}</td>
        <td>${tmpCereal1.getProjects()}</td>
    </tr>

    </c:forEach>
</table>

<h1><%= "Wnioski odrzucone: " %></h1>
<table border>
    <thead>
    <tr>
        <th scope="col">id</th>
        <th scope="col">leaveType</th>
        <th scope="col">startDay</th>
        <th scope="col">endDay</th>
        <th scope="col">numberday</th>
        <th scope="col">status</th>
        <th scope="col">name</th>
        <th scope="col">department</th>
        <th scope="col">projects</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="tmpCereal1" items="${ODRZUCONE_APPLICATIONS_LIST}">

    <tr >
        <td>${tmpCereal1.getId()}</td>
        <td>${tmpCereal1.getLeaveType()}</td>
        <td>${tmpCereal1.getStartDay()}</td>
        <td>${tmpCereal1.getEndDay()}</td>
        <td>${tmpCereal1.getNumberDay()}</td>
        <td>${tmpCereal1.getStatusApplicationType()}</td>
        <td>${tmpCereal1.getEmployeeName() }</td>
        <td>${tmpCereal1.getDepartmentName()}</td>
        <td>${tmpCereal1.getProjects()}</td>
    </tr>

    </c:forEach>
</table>

</body>
</html>