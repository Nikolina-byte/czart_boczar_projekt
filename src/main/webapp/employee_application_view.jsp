<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<head>
    <title>Employee Application Panel</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<!--pasek główny-->
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


<c:url var="applyApplication" value="EmployeeApplicationServlet">

    <c:param name="employeeID" value="${EMPLOYEE}"></c:param>
</c:url>
<c:url var="vacation_form" value="EmployeeAddApplicationServlet">
    <c:param name="employeeID" value="${EMPLOYEE}"></c:param>
</c:url>

<c:url var="application_view" value="EmployeeApplicationServlet">
    <c:param name="employeeID" value="${EMPLOYEE}"></c:param>
</c:url>

<c:url var="application" value="EmployeeActualApplicationServlet">
    <c:param name="employeeID" value="${EMPLOYEE}"></c:param>
</c:url>

<c:url var="history" value="EmployeeApplicationHistoryServlet">
    <c:param name="employeeID" value="${EMPLOYEE}"></c:param>
</c:url>


<!-- Sidebar -->

<div class="w3-sidebar w3-light-grey w3-bar-block" style="width:25%">
    <h3 class="w3-bar-item">Menu Pracownika</h3>
    <%--    <a href="${department}" class="w3-bar-item w3-button">Informacje o dziale</a>--%>
    <div class="w3-dropdown-hover">
        <button class="w3-button">Twoje wnioski urlopowe<i class="fa fa-caret-down"></i></button>
        <div class="w3-dropdown-content w3-bar-block">
            <a href="${vacation_form}" class="w3-bar-item w3-button">Złóż wniosek</a>
            <a href="${application}" class="w3-bar-item w3-button">Aktualne wnioski</a>
            <a href="${history}" class="w3-bar-item w3-button">Archiwum</a>
        </div>
    </div>
</div>


<!-- Page Content -->
<div style="margin-left:25%">
    <div class="w3-container">
        <h1><%= "Wnioski złożone: " %>
        </h1>
        <div class="row form-group"></div>
        <div class="row form-group"></div>
        <div class="row form-group"></div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">id</th>
                <th scope="col">Rodzaj urlopu</th>
                <th scope="col">Data rozpoczęcia urlopu</th>
                <th scope="col">Data końca urlopu</th>
                <th scope="col">Liczba dni</th>
                <th scope="col">Status wniosku</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="tmpCereal1" items="${ZLOZONE_APPLICATIONS_LIST}">

            <c:url var="modifyLink" value="EmployeeActualApplicationServlet">
                <c:param name="employeeID" value="${EMPLOYEE}"></c:param>
                <c:param name="command" value="MODIFY"></c:param>
                <c:param name="applicationID" value="${tmpCereal1.id}"></c:param>
            </c:url>

            <c:url var="rejctedLink" value="EmployeeActualApplicationServlet">
                <c:param name="employeeID" value="${EMPLOYEE}"></c:param>
                <c:param name="command" value="REJECTED"></c:param>
                <c:param name="applicationID" value="${tmpCereal1.id}"></c:param>
            </c:url>

            <tr>
                <td>${tmpCereal1.getId()}</td>
                <td>${tmpCereal1.getLeaveType()}</td>
                <td>${tmpCereal1.getStartDay()}</td>
                <td>${tmpCereal1.getEndDay()}</td>
                <td>${tmpCereal1.getNumberDay()}</td>
                <td>${tmpCereal1.getStatusApplicationType()}</td>
                <td><a href="${modifyLink}"
                       onclick="if(!(confirm('Czy na pewno chcesz modyfikować ten wniosek?'))) return false">
                    <button type="button" class="btn btn-success">Modyfikuj</button>
                </a>
                    <a href="${rejctedLink}"
                       onclick="if(!(confirm('Czy na pewno chcesz usunąć ten wniosek?'))) return false">
                        <button type="button" class="btn danger">Anuluj</button>
                    </a></td>
            </tr>

            </c:forEach>
        </table>
        <div class="row form-group"></div>
        <div class="row form-group"></div>
        <div class="row form-group"></div>

        <h1><%= "Wnioski zaakceptowane: " %>
        </h1>
        <div class="row form-group"></div>
        <div class="row form-group"></div>
        <div class="row form-group"></div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">id</th>
                <th scope="col">Rodzaj urlopu</th>
                <th scope="col">Data rozpoczęcia urlopu</th>
                <th scope="col">Data końca urlopu</th>
                <th scope="col">Liczba dni</th>
                <th scope="col">Status wniosku</th>
            </tr>
            </thead>
            <tbody>


            <c:forEach var="tmpCereal1" items="${APPLICATIONS_LIST}">
            <c:url var="acceptLink" value="EmployeeActualApplicationServlet">
                <c:param name="employeeID" value="${EMPLOYEE}"></c:param>
                <c:param name="command" value="ACCEPT"></c:param>
                <c:param name="applicationID" value="${tmpCereal1.id}"></c:param>
            </c:url>

            <c:url var="rejctedLink" value="EmployeeActualApplicationServlet">
                <c:param name="employeeID" value="${EMPLOYEE}"></c:param>
                <c:param name="command" value="REJECTED"></c:param>
                <c:param name="applicationID" value="${tmpCereal1.id}"></c:param>
            </c:url>

            <c:url var="modifyLink" value="EmployeeActualApplicationServlet">
                <c:param name="employeeID" value="${EMPLOYEE}"></c:param>
                <c:param name="command" value="MODIFY"></c:param>
                <c:param name="applicationID" value="${tmpCereal1.id}"></c:param>
            </c:url>

            <tr>
                <td>${tmpCereal1.getId()}</td>
                <td>${tmpCereal1.getLeaveType()}</td>
                <td>${tmpCereal1.getStartDay()}</td>
                <td>${tmpCereal1.getEndDay()}</td>
                <td>${tmpCereal1.getNumberDay()}</td>
                <td>${tmpCereal1.getStatusApplicationType()}</td>
                <td><a href="${modifyLink}"
                       onclick="if(!(confirm('Czy na pewno chcesz modyfikować ten wniosek?'))) return false">
                    <button type="button" class="btn btn-success">Modyfikuj</button>
                </a>
                    <a href="${rejctedLink}"
                       onclick="if(!(confirm('Czy na pewno chcesz usunąć ten wniosek?'))) return false">
                        <button type="button" class="btn btn-danger">Anuluj</button>
                    </a></td>
            </tr>

            </c:forEach>
        </table>
        <div class="row form-group"></div>
        <div class="row form-group"></div>
        <div class="row form-group"></div>
    </div>

    <%--    koniec container--%>
</div>
<%--koniec stylu--%>


</body>
</html>