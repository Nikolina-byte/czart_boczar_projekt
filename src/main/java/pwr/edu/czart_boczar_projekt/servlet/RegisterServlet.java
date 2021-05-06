package pwr.edu.czart_boczar_projekt.servlet;

import pwr.edu.czart_boczar_projekt.database.DBUtilAdmin;
import pwr.edu.czart_boczar_projekt.entity.Department;
import pwr.edu.czart_boczar_projekt.entity.Employee;
import pwr.edu.czart_boczar_projekt.entity.Login;
import pwr.edu.czart_boczar_projekt.entity.VacationData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    private DBUtilAdmin dbUtil;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            dbUtil = new DBUtilAdmin();
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        response.setContentType("text/html");

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

        Employee employee = new Employee(name, surname, birthDate, email, phone, position, status);

        Login loginByEmployee = null;
        Employee insertEmployee = null;

        try {
            loginByEmployee = dbUtil.getLoginByLogin(username);

            if(loginByEmployee!= null){
                System.out.println("Login pracownika jest zajety");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/register_form.html");
                dispatcher.forward(request, response);
            }

            insertEmployee = dbUtil.addEmployee(employee);
            Login login = new Login(username, password, insertEmployee);
            dbUtil.addLoginData(login);

            VacationData vacationData = new VacationData(LocalDate.now(), worked_years, education, insertEmployee);
            dbUtil.addVacationData(vacationData);

            Department departmentByName = dbUtil.getDepartmentByName(department);
            dbUtil.addDepartmentHasEmployee(departmentByName.getId(), insertEmployee.getId());

            dbUtil.addManagerHasEmployee(departmentByName.getEmployee().getId(), insertEmployee.getId());

        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/register_form_anserw.jsp");

        dispatcher.forward(request, response);
    }
}