package pwr.edu.czart_boczar_projekt.servlet;

import pwr.edu.czart_boczar_projekt.database.DBUtilEmployee;
import pwr.edu.czart_boczar_projekt.database.DBUtilManager;
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
import java.util.List;

@WebServlet("/EmployeeApplicationHistoryServlet")
public class EmployeeApplicationHistoryServlet extends HttpServlet {


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
        String managerID = request.getParameter("employeeID");
        List<ApplicationInformationView> applicationsZaaceptowane = null;
        try {
            String status = "zaakceptowany";
            EmployeeInformationView employee = dbUtil.getEmployeeByID(Integer.parseInt(managerID));
            applicationsZaaceptowane = dbUtil.getApplicationsByStatusAndEmployeeID(status, employee.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("APPLICATIONS_LIST", applicationsZaaceptowane);

        // Wnioski zrealizowane
        List<ApplicationInformationView> applicationsZrealizowany = null;
        try {
            String status = "zrealizowany";
            EmployeeInformationView employee = dbUtil.getEmployeeByID(Integer.parseInt(managerID));
            applicationsZrealizowany = dbUtil.getApplicationsByStatusAndEmployeeID(status, employee.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("ZREALIZOWANE_APPLICATIONS_LIST", applicationsZrealizowany);

        // Wnioski zrealizowane
        List<ApplicationInformationView> applicationsOdrzucone = null;
        try {
            String status = "odrzucony";
            EmployeeInformationView employee = dbUtil.getEmployeeByID(Integer.parseInt(managerID));
            applicationsOdrzucone = dbUtil.getApplicationsByStatusAndEmployeeID(status, employee.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("ODRZUCONE_APPLICATIONS_LIST", applicationsOdrzucone);
        request.setAttribute("EMPLOYEE", managerID);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/employee_application_view.jsp");
        dispatcher.forward(request, response);
    }

}
