<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<%@ page import="java.math.BigDecimal" %>

<%@ page import="java.time.LocalDate" %>

<%@ page import="pwr.edu.czart_boczar_projekt.entity.Employee" %>
<%@ page import="pwr.edu.czart_boczar_projekt.entity.Login" %>
<%@ page import="java.time.format.DateTimeFormatter" %>


<html>
<head>
    <title>Confirmation</title>

    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <style> .header {
        background: url(./img/img.png) no-repeat center center;
        background-size: cover;
        height: 500px;
        text-align: center;
    }
    </style>
</head>

<body>

<nav class="nav" role="navigation">
    <div id="navbar" class="navbar-collapse collapse navbar-right">
        <a class="btn" href="login.html" role="button">Zaloguj się</a></p>
    </div>

    <div class="container">
        <ul>
            <li><a href="index.html">Strona główna</a></li>
        </ul>
    </div>
</nav>

<div class="header">
</div>


<div class="main">
    <div class="container">
        <c:set var="today" value="<%= new java.util.Date() %>"/>
        <c:set var="nameLen"/>

        Time on server is: ${today}


        <%

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-M-dd");
            LocalDate birthDate = LocalDate.parse(request.getParameter("birth_date"), dtf);

            // po prawej odpowiedzi z formularza - nazwy pól z input name="password"
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String password = request.getParameter("password");
            String position = String.valueOf(request.getParameter("position"));// manager/employee
            String status = "w pracy";

            String education = String.valueOf(request.getParameter("education")); // wyższe zawodowe
            int worked_years = Integer.valueOf(request.getParameter("worked_years"));
            String department= String.valueOf(request.getParameter("working_department")); // dział pracy



            Employee u = new Employee(name, surname, birthDate, email, phone, position, status);
            Login user = new Login(username, password, u);
        %>
        Przyjęto dane
        <%--        dane z nazw pól (lewa strona - Java)--%>
        <h2>Rejestracja przebiegła pomyślnie</h2>
        Imię: <% out.println(u.getFirst_name());%>
        <br>
        Nazwisko: <% out.println(u.getLast_name());%>
        <br>
        Email: <% out.println(u.getEmail());%>
       <br>
        Phone: <% out.println(u.getPhone());%>
        <br>
        Date: <% out.println(u.getBirth());%>
        <br>
        Rola: <% out.println(u.getPosition());%>
        <br>
        <br>
        <h2>Dane logowania</h2>
        <br>
        Nazwa użytkownika: <% out.println(user.getLogin());%>
        <br>
        Password: <% out.println(user.getPassword());%>
        <br>

    </div>
</div>


<div class="main">
    <div class="container">
        <p>Wróć do <a href="index.html"> strony głównej</a></p>
    </div>
</div>


<div class="footer">
    <p>Last updated: <%=new java.util.Date()%>
    </p>
    <p>
        <a href="https://i.imgflip.com/49j42o.jpg">&copy; Adrianna Boczar 250319</a>
    </p>
</div>
</body>
</html>

