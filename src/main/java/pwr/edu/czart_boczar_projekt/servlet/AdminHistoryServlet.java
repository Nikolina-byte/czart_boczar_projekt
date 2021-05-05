package pwr.edu.czart_boczar_projekt.servlet;

import pwr.edu.czart_boczar_projekt.database.DBUtilManager;
import pwr.edu.czart_boczar_projekt.entity.*;

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

@WebServlet("/AdminHistoryServlet")
public class AdminHistoryServlet extends HttpServlet {

    private DBUtilManager dbUtil;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            dbUtil = new DBUtilManager();
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        response.setContentType("text/html");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        String managerID = request.getParameter("employeeID");
        List<ApplicationInformationView> applicationsZaaceptowane = null;
        try {
            String status = "zaakceptowany";
            EmployeeInformationView employee = dbUtil.getEmployeeViewByID(Integer.parseInt(managerID));
            applicationsZaaceptowane = dbUtil.getApplicationByStatusAndManager(status, employee.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("APPLICATIONS_LIST", applicationsZaaceptowane);

        // Wnioski zrealizowane
        List<ApplicationInformationView> applicationsZrealizowany = null;
        try {
            String status = "zrealizowany";
            EmployeeInformationView employee = dbUtil.getEmployeeViewByID(Integer.parseInt(managerID));
            applicationsZrealizowany = dbUtil.getApplicationByStatusAndManager(status, employee.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("ZREALIZOWANE_APPLICATIONS_LIST", applicationsZrealizowany);

        // Wnioski zrealizowane
        List<ApplicationInformationView> applicationsOdrzucone = null;
        try {
            String status = "odrzucony";
            EmployeeInformationView employee = dbUtil.getEmployeeViewByID(Integer.parseInt(managerID));
            applicationsOdrzucone = dbUtil.getApplicationByStatusAndManager(status, employee.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("ODRZUCONE_APPLICATIONS_LIST", applicationsOdrzucone);
        request.setAttribute("EMPLOYEE", managerID);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_history_view.jsp");
        dispatcher.forward(request, response);
    }
}