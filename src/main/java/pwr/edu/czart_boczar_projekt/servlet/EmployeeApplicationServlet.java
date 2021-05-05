package pwr.edu.czart_boczar_projekt.servlet;


import pwr.edu.czart_boczar_projekt.database.DBUtilEmployee;
import pwr.edu.czart_boczar_projekt.database.DBUtilManager;
import pwr.edu.czart_boczar_projekt.entity.Application;
import pwr.edu.czart_boczar_projekt.entity.ApplicationInformationView;
import pwr.edu.czart_boczar_projekt.entity.Employee;
import pwr.edu.czart_boczar_projekt.entity.EmployeeInformationView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/EmployeeApplicationServlet")
public class EmployeeApplicationServlet extends HttpServlet {
    private DBUtilEmployee dbUtil;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            dbUtil = new DBUtilEmployee();
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        // odczytanie danych z formularza

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-M-dd");
        LocalDate startDate = LocalDate.parse(request.getParameter("start_date"), dtf);
        LocalDate endDate = LocalDate.parse(request.getParameter("end_date"), dtf);
        String leaveType = request.getParameter("vacation_type");
        String managerID = request.getParameter("employeeID");
        String status = "złożono";
        List<ApplicationInformationView> applicationsZlozone = null;
        try {
//            EmployeeInformationView employee = dbUtil.getEmployeeByID(Integer.parseInt(managerID));
            Employee employee = dbUtil.getEmployee(Integer.parseInt(managerID));
//            int id, String leaveType, LocalDate startDate, LocalDate endDate, String status, Employee employee
            Application application = new Application(1, leaveType, startDate, endDate, status, employee);
            applicationsZlozone = dbUtil.getApplicationsByStatus(employee.getId(), status);
            dbUtil.addApplication(application);

        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("ZLOZONE_APPLICATIONS_LIST", applicationsZlozone);
        request.setAttribute("EMPLOYEE", managerID);


    }
}
