package pwr.edu.czart_boczar_projekt.servlet;

import pwr.edu.czart_boczar_projekt.HashCalculator;
import pwr.edu.czart_boczar_projekt.database.DBUtilAdmin;
import pwr.edu.czart_boczar_projekt.entity.Employee;
import pwr.edu.czart_boczar_projekt.entity.Login;

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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private DBUtilAdmin dbUtil;

    /**
     * init method of LoginServlet
     * @param config
     * @throws ServletException exception if servlet does not work
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            dbUtil = new DBUtilAdmin();
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    /***
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        response.setContentType("text/html");
        try {
            update();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String login = request.getParameter("loginInput");
        String password = request.getParameter("passwordInput");

        try {
            Login loginData = dbUtil.getLoginData(login);

            if (!(loginData == null)) {
                if(HashCalculator.validatePassword(password, loginData.getPassword())){
                    if (loginData.getEmployee().getPosition().equals("manager")) {
                        request.setAttribute("EMPLOYEE", loginData.getEmployee().getId());
                        System.out.println(loginData.getEmployee().getId());
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_view.jsp");
                        dispatcher.forward(request, response);
                    }else {
                        request.setAttribute("EMPLOYEE", loginData.getEmployee().getId());
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/employee_view.jsp");
                        dispatcher.forward(request, response);
                    }
                }
            }
            else{
                request.setAttribute("message", "Invalid");
                request.getRequestDispatcher("login_alert.jsp").forward(request,response);

            }
        } catch (Exception e) {
            System.out.println("NIE MA TAKIEGO LOGINU");
            e.printStackTrace();
            request.setAttribute("message", "Invalid");
            response.sendRedirect("/login_alert.jsp");

        }
    }

    private void update() throws Exception {
        List<Employee> employees = dbUtil.getEmployees();
        for (Employee employee : employees){
            dbUtil.updateBase(employee.getId());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        try {
            String command = request.getParameter("EMPLOYEEID");
            System.out.println();
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}