package pwr.edu.czart_boczar_projekt.servlet;

import pwr.edu.czart_boczar_projekt.database.DBUtilEmployee;
import pwr.edu.czart_boczar_projekt.entity.Application;
import pwr.edu.czart_boczar_projekt.entity.Employee;
import pwr.edu.czart_boczar_projekt.entity.VacationData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Servlet implementation class EmployeeAddApplicationServlet
 */
@WebServlet("/EmployeeAddApplicationServlet")
public class EmployeeAddApplicationServlet extends HttpServlet {
    private DBUtilEmployee dbUtil;

    /**
     * init method of EmployeeAddApplicationServlet
     * @param config
     * @throws ServletException exception if servlet does not work
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            dbUtil = new DBUtilEmployee();
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        response.setContentType("text/html");

        String employeeID = request.getParameter("employeeID");
        request.setAttribute("EMPLOYEE", employeeID);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vacation_form.jsp");
        dispatcher.forward(request, response);
    }
}