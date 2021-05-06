package pwr.edu.czart_boczar_projekt.servlet;

import pwr.edu.czart_boczar_projekt.database.DBUtilEmployee;
import pwr.edu.czart_boczar_projekt.entity.*;

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

        List<ApplicationInformationView> applicationsZlozone = null;
        try {
            String status = "złożony";
            Employee employee = dbUtil.getEmployeeByID(Integer.parseInt(employeeID));
            applicationsZlozone = dbUtil.getApplicationByStatusAndManager(status, employee.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("ZLOZONE_APPLICATIONS_LIST", applicationsZlozone);

        List<ApplicationInformationView> applicationsZaaceptowane = null;
        try {
            String status = "zaakceptowany";
            Employee employee = dbUtil.getEmployeeByID(Integer.parseInt(employeeID));
            applicationsZaaceptowane = dbUtil.getApplicationByStatusAndManager(status, employee.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("APPLICATIONS_LIST", applicationsZaaceptowane);
        request.setAttribute("EMPLOYEE", employeeID);

        try {
            String command = request.getParameter("command");

            if (command == null)
                command = "LIST";

            switch (command) {
                case "LIST":
                    listApplication(request, response);
                    break;

                case "ACCEPT":
                    changeApplication(request, response);
                    break;

                case "REJECTED":
                    deleteApplication(request, response);
                    break;

                default:
                    listApplication(request, response);
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listApplication(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employeeID = request.getParameter("employeeID");

        List<ApplicationInformationView> applicationsZlozone = null;
        try {
            String status = "złożony";
            Employee employee = dbUtil.getEmployeeByID(Integer.parseInt(employeeID));
            applicationsZlozone = dbUtil.getApplicationByStatusAndManager(status, employee.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("ZLOZONE_APPLICATIONS_LIST", applicationsZlozone);

        List<ApplicationInformationView> applicationsZaaceptowane = null;
        try {
            String status = "zaakceptowany";
            Employee employee = dbUtil.getEmployeeByID(Integer.parseInt(employeeID));
            applicationsZaaceptowane = dbUtil.getApplicationByStatusAndManager(status, employee.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("APPLICATIONS_LIST", applicationsZaaceptowane);
        request.setAttribute("EMPLOYEE", employeeID);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/employee_application_view.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteApplication(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("applicationID");
        Application application = dbUtil.getApplicationByID(id);
        Employee employee = application.getEmployee();
        VacationData vacation = dbUtil.getVacationDataByEmploye(employee.getId());

        if(application.getLeaveType().equals("wypoczynkowy")) {
            int days = calcWeekDays(application.getStartDate(), application.getEndDate()) + 1;
            int freeDay = vacation.getFreeDay();
            int useDay = vacation.getUsedDay();

            vacation.setFreeDay(freeDay + days);
            vacation.setUsedDay(useDay - days);
            dbUtil.updateVacationData(vacation);

            dbUtil.deleteApplication(Integer.parseInt(id));
        }
        listApplication(request, response);

    }

    private void changeApplication(HttpServletRequest request, HttpServletResponse response) {
        String managerID = request.getParameter("employeeID");
    }

    private static int calcWeekDays(final LocalDate start, final LocalDate end) {
        final DayOfWeek startW = start.getDayOfWeek();
        final DayOfWeek endW = end.getDayOfWeek();

        final long days = ChronoUnit.DAYS.between(start, end);
        final long daysWithoutWeekends = days - 2 * ((days + startW.getValue())/7);

        //adjust for starting and ending on a Sunday:
        return (int) daysWithoutWeekends + (startW == DayOfWeek.SUNDAY ? 1 : 0) + (endW == DayOfWeek.SUNDAY ? 1 : 0);
    }
}
