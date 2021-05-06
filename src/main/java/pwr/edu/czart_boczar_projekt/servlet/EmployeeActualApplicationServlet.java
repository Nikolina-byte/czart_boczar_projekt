package pwr.edu.czart_boczar_projekt.servlet;

import pwr.edu.czart_boczar_projekt.database.DBUtilEmployee;
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

@WebServlet("/EmployeeActualApplicationServlet")
public class EmployeeActualApplicationServlet extends HttpServlet {


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


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        response.setContentType("text/html");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String employeeID = request.getParameter("employeeID");

        List<ApplicationInformationView> applicationsZaaceptowane = null;
        try {
            String status = "zaakceptowany";
            Employee employee = dbUtil.getEmployeeByID(Integer.parseInt(employeeID));
            applicationsZaaceptowane = dbUtil.getApplicationByStatusAndManager(status, employee.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("APPLICATIONS_LIST", applicationsZaaceptowane);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/employee_application_view.jsp");
        dispatcher.forward(request, response);
    }
//    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-M-dd");
//
//        // odczytanie danych z formularza
//        int id = Integer.parseInt(request.getParameter("employeeID"));
//        String firstName = request.getParameter("employeeFirstName");
//        String lastName = request.getParameter("employeeLastName");
//        String birth = request.getParameter("employeeBirth");
//        String email = request.getParameter("employeeEmail");
//        String phone = request.getParameter("employeePhone");
//        String position = request.getParameter("employeePosition");
//        String status = request.getParameter("employeeStatus");
//
//        Employee employee = new Employee(id, firstName, lastName, LocalDate.parse(birth, dtf1), email, phone, position, status);
//
////        dbUtil.updateEmployee(employee);
////
////        listEmployee(request, response);
//    }

//    private void listEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        String managerID = request.getParameter("employeeID");
//
//        List<EmployeeInformationView> managerEmployees = null;
//        try {
//            EmployeeInformationView employee = dbUtil.getEmployeeViewByID(Integer.parseInt(managerID));
//            managerEmployees = dbUtil.getEmployeeByManager(employee.getName());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        request.setAttribute("EMPLOYEE", managerID);
//        request.setAttribute("EMPLOYEE_LIST", managerEmployees);
//
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_employee_view.jsp");
//
//        dispatcher.forward(request, response);
//    }
//
//
//    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        String id = request.getParameter("employeeID");
//        dbUtil.deleteEmployee(id);
//        listEmployee(request, response);
//    }
}
