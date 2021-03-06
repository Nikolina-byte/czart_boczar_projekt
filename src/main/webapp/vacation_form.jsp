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


    <script type="text/javascript">
        function SetDate() {
            var date = new Date();

            var day = date.getDate();
            var month = date.getMonth() + 1;
            var year = date.getFullYear();

            if (month < 10) month = "0" + month;
            if (day < 10) day = "0" + day;

            var today = year + "-" + month + "-" + day;


            document.getElementById('start_date').min = today;
            document.getElementById('start_date').value = today;

        }

        function SetEndDate() {
            var date = new Date();

            var day = date.getDate() + 1;
            var month = date.getMonth() + 1;
            var year = date.getFullYear();

            if (month < 10) month = "0" + month;
            if (day < 10) day = "0" + day;

            var today = year + "-" + month + "-" + day;


            document.getElementById('end_date').min = today;
            document.getElementById('end_date').value = today;
        }

        function checkDates() {

            var endDate = document.getElementById("end_date").value;
            var startDate = document.getElementById("start_date").value;
            if ((Date.parse(startDate) >= Date.parse(endDate))) {
                alert("End date should be greater than Start date");
                document.getElmentById("EndDate").value = "";
                return false;
            }
        }


        function start() {
            SetDate();
            SetEndDate();
        }


    </script>


</head>
<body onload="start();">

<!--pasek g????wny-->
<nav class="nav" role="navigation">
    <div id="navbar" class="navbar-collapse collapse navbar-right">
        <a class="btn" href="login.html" role="button">Zaloguj si??</a>
    </div>
    <div class="container">
        <ul>
            <li><a href="index.html">Strona g????wna</a></li>
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
            <a href="${vacation_form}" class="w3-bar-item w3-button">Z?????? wniosek</a>
            <a href="${application}" class="w3-bar-item w3-button">Aktualne wnioski</a>
            <a href="${history}" class="w3-bar-item w3-button">Archiwum</a>
        </div>
    </div>
</div>

<%--Page content--%>

<c:url var="applyApplication" value="EmployeeApplicationServlet">
    <c:param name="employeeID" value="${EMPLOYEE}"></c:param>
</c:url>

<div style="margin-left:25%">

    <div class="w3-container w3-teal">
        <h1>Witaj ${EMPLOYEE}!</h1>
    </div>


    <div class="w3-container">


        <h1>Z?????? wniosek o urlop</h1>
        <form name="vacationForm" action="${applyApplication}" method="get" onsubmit="return checkDates();">

            <fieldset>
                <div class="form-group">
                    <label for="employee">ID wnioskodawcy:</label>
                    <input type="text" class="form-control" name="employee" id="employee" value=${EMPLOYEE} readonly>
                </div>

                <div class="form-group">
                    <label for="start_date">Data rozpocz??cia urlopu</label>
                    <input type="date" class="form-control" name="start_date" id="start_date" required>
                </div>


                <div class="form-group">
                    <label for="end_date">Data zako??czenia urlopu</label>
                    <input type="date" class="form-control" name="end_date" id="end_date" required>
                </div>

                <%--                <div class="form-group">--%>
                <%--                    <label for="numberOfDays">Liczba dni</label>--%>
                <%--                    <input type="number" class="form-control" name="numberOfDays" id="numberOfDays" readonly="">--%>
                <%--                </div>--%>


                <div class="form-group">
                    <label for="vacation_type">Rodzaj urlopu</label>
                    <select id="vacation_type" name="vacation_type">
                        <option value="wypoczynkowy">Wypoczynkowy</option>
                        <option value="krwiodastwo">Krwiodawstwo</option>
                        <option value="wolontariat">Wolontariat</option>
                        <option value="narodzenie dziecka">Narodziny dziecka</option>
                        <option value="??lub w??asny">??lub w??asny</option>
                        <option value="pogrzeb (bardzo bliska rodzina)">Pogrzeb (bardzo bliska rodzina)</option>
                        <option value="pogrzeb (dalsza rodzina)">Pogrzeb (dalszarodzina)</option>
                        <option value="??lub dziecka">??lub dziecka</option>
                    </select>
                </div>

                <button type="submit" class="btn btn-primary" onsubmit="return checkDates();">Wy??lij wniosek</button>

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
