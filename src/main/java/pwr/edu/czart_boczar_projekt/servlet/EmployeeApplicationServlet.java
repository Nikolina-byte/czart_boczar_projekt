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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        response.setContentType("text/html");

        String employeeID = request.getParameter("employee");


        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-M-dd");
        LocalDate startDate = LocalDate.parse(request.getParameter("start_date"), dtf);
        LocalDate endDate = LocalDate.parse(request.getParameter("end_date"), dtf);
        String leaveType = request.getParameter("vacation_type");
        String status = "złożony";

        List<ApplicationInformationView> applicationsZlozone = null;

        Employee employee = null;
        try {
            employee = dbUtil.getEmployeeByID(Integer.parseInt(employeeID));

            Application application = new Application(leaveType, startDate, endDate, status, employee);
            dbUtil.addApplication(application);
            VacationData vacationData = dbUtil.getVacationDataByEmploye(Integer.parseInt(employeeID));


            if(application.getLeaveType().equals("wypoczynkowy")){
                int days = calcWeekDays(startDate, endDate)+1;
                int freeDay = vacationData.getFreeDay();
                if(freeDay-days>=0){
                    vacationData.setFreeDay(freeDay-days);
                    vacationData.setUsedDay(days);
                    dbUtil.updateVacationData(vacationData);


                }else {
                    System.out.println("Nie ma dni");
//                    request.setAttribute("EMPLOYEE", employeeID);
//                    RequestDispatcher dispatcher = request.getRequestDispatcher("/vacation_form_anserw.jsp");
//                    dispatcher.forward(request, response);
                }
            }

            applicationsZlozone = dbUtil.getApplicationsByStatusAndEmployeeID(status, employee.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }


        request.setAttribute("ZLOZONE_APPLICATIONS_LIST", applicationsZlozone);
        request.setAttribute("EMPLOYEE", employeeID);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/vacation_form_anserw.jsp");
        dispatcher.forward(request, response);
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
