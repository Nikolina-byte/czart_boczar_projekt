<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<head>
    <meta charset="utf-8">
    <title>Manager Panel</title>
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

<!-- Sidebar -->
<div class="w3-sidebar w3-light-grey w3-bar-block" style="width:25%">
    <h3 class="w3-bar-item">Menu Pracownika</h3>
    <a href="${department}" class="w3-bar-item w3-button">Informacje o dziale</a>
    <div class="w3-dropdown-hover">
        <button class="w3-button">Twoje wnioski urlopowe<i class="fa fa-caret-down"></i></button>
        <div class="w3-dropdown-content w3-bar-block">
            <a href="vacation_form.jsp" class="w3-bar-item w3-button">Złóż wniosek</a>
            <a href=# class="w3-bar-item w3-button">Moje wnioski</a>
            <a href=# class="w3-bar-item w3-button">Archiwum</a>
        </div>
    </div>
    <a href="${employee}" class="w3-bar-item w3-button">Pracownicy</a>
</div>
<!-- Page Content -->
<div style="margin-left:25%">


    <div class="w3-container">


        <h1>Załóż konto</h1>
        <form action="vacation_form_anserw.jsp">
            <fieldset>

                <!--                String leaveType, LocalDate startDate, LocalDate endDate, String status, Employee employee-->

                <div class="form-group">
                    <label for="start_date">Data rozpoczęcia urlopu</label>
                    <input type="date" class="form-control" name="start_date" id="start_date"  required>
                </div>

                <div class="form-group">
                    <label for="end_date">Data rozpoczęcia urlopu</label>
                    <input type="date" class="form-control" name="end_date" id="end_date"  required>
                </div>


                <div class="form-group">
                    <label for="vacation_type">Rodzaj urlopu</label>
                    <select id="vacation_type" name="vacation_type">
                        <option value="wypoczynkowy">Wypoczynkowy</option>
                        <option value="krwiodastwo">Krwiodastwo</option>
                        <option value="wolontariat">Wolontariar</option>
                        <option value="narodzenie dziecka">Narodziny dziecka</option>
                        <option value="ślub własny">Ślub własny</option>
                        <option value="pogrzeb (bardzo bliska rodzina)">Pogrzeb (bardzo bliska rodzina)"</option>
                        <option value="pogrzeb (dalsza rodzina)">Pogrzeb (dalszarodzina)"</option>
                        <option value="ślub dziecka">Ślub dziecka</option>
                    </select>
                </div>

                <button type="submit" class="btn btn-primary">Wyślij wniosek</button>

            </fieldset>
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
