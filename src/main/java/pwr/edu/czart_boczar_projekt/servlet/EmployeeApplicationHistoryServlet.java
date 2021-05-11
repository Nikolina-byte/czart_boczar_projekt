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

/**
 * Servlet implementation class EmployeeApplicationHistoryServlet
 */
@WebServlet("/EmployeeApplicationHistoryServlet")
public class EmployeeApplicationHistoryServlet extends HttpServlet {


    private DBUtilEmployee dbUtil;


    /***
     * init method of EmployeeApplicationHistoryServlet
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employeeID = request.getParameter("employeeID");

        List<ApplicationInformationView> applicationsZrealizowany = null;
        try {
            String status = "zrealizowany";
            Employee employee = dbUtil.getEmployeeByID(Integer.parseInt(employeeID));
            applicationsZrealizowany = dbUtil.getApplicationByStatusAndManager(status, employee.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("ZREALIZOWANE_APPLICATIONS_LIST", applicationsZrealizowany);

        List<ApplicationInformationView> applicationsOdrzucone = null;
        try {
            String status = "odrzucony";
            Employee employee = dbUtil.getEmployeeByID(Integer.parseInt(employeeID));
            applicationsOdrzucone = dbUtil.getApplicationByStatusAndManager(status, employee.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("ODRZUCONE_APPLICATIONS_LIST", applicationsOdrzucone);
        request.setAttribute("EMPLOYEE", employeeID);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/employee_history_application.jsp");
        dispatcher.forward(request, response);
    }
}
