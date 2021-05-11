package pwr.edu.czart_boczar_projekt.servlet;

import pwr.edu.czart_boczar_projekt.database.DBUtilManager;
import pwr.edu.czart_boczar_projekt.entity.Application;
import pwr.edu.czart_boczar_projekt.entity.ApplicationInformationView;
import pwr.edu.czart_boczar_projekt.entity.DepartamentInformationView;
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
 * Servlet implementation class AdminApplicationServlet
 */
@WebServlet("/AdminApplicationServlet")
public class AdminApplicationServlet extends HttpServlet {

    private DBUtilManager dbUtil;

    /**
     * init method of AdminApplicationServlet
     * @param config
     * @throws ServletException exception if servlet does not work
     */
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
        String managerID = request.getParameter("employeeID");

        // Wnioski złożone
        List<ApplicationInformationView> applicationsZlozone = null;
        try {
            String status = "złożony";
            EmployeeInformationView employee = dbUtil.getEmployeeViewByID(Integer.parseInt(managerID));
            applicationsZlozone = dbUtil.getApplicationByStatusAndManager(status, employee.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("ZLOZONE_APPLICATIONS_LIST", applicationsZlozone);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_application_view.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        String managerID = request.getParameter("employeeID");
        // Wnioski złożone
        List<ApplicationInformationView> applicationsZlozone = null;
        try {
            String status = "złożony";
            EmployeeInformationView employee = dbUtil.getEmployeeViewByID(Integer.parseInt(managerID));
            applicationsZlozone = dbUtil.getApplicationByStatusAndManager(status, employee.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("ZLOZONE_APPLICATIONS_LIST", applicationsZlozone);
        request.setAttribute("EMPLOYEE", managerID);

        try {
            String command = request.getParameter("command");

            if (command == null)
                command = "LIST";

            switch (command) {
                case "LIST":
                    listApplication(request, response);
                    break;

                case "ACCEPT":
                    changeStatusAccept(request, response);
                    break;

                case "REJECTED":
                    changeStatusRejected(request, response);
                    break;

                default:
                    listApplication(request, response);
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void changeStatusAccept(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("applicationID");
        // pobranie  danych telefonu z BD
        Application application = dbUtil.getApplicationByID(id);
        application.setStatus("zaakceptowany");
        dbUtil.updateApplication(application);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_application_view.jsp");

        dispatcher.forward(request, response);
    }

    private void changeStatusRejected(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("applicationID");
        Application application = dbUtil.getApplicationByID(id);
        Application applicationMap = new Application(application, "odrzucony");
        dbUtil.updateApplication(applicationMap);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_application_view.jsp");

        dispatcher.forward(request, response);
    }

    private void listApplication(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String managerID = request.getParameter("employeeID");
        List<ApplicationInformationView> applicationsZlozone = null;
        try {
            String status = "złożony";
            EmployeeInformationView employee = dbUtil.getEmployeeViewByID(Integer.parseInt(managerID));
            applicationsZlozone = dbUtil.getApplicationByStatusAndManager(status, employee.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("ZLOZONE_APPLICATIONS_LIST", applicationsZlozone);
        request.setAttribute("EMPLOYEE", managerID);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_application_view.jsp");
        dispatcher.forward(request, response);
    }
}