package pwr.edu.czart_boczar_projekt.servlet;

import pwr.edu.czart_boczar_projekt.database.DBUtilAdmin;
import pwr.edu.czart_boczar_projekt.entity.Login;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        response.setContentType("text/html");

        String login = request.getParameter("loginInput");
        String password = request.getParameter("passwordInput");

        try {
            Login loginData = dbUtil.getLoginData(login);

            if (!(loginData == null)) {
                if (password.equals(loginData.getPassword())) {
                    if (loginData.getEmployee().getPosition().equals("manager")) {
                        request.setAttribute("EMPLOYEE", String.valueOf(loginData.getEmployee().getId()));
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
        } catch (Exception e) {
            System.out.println("NIE MA TAKIEGO LOGINU");
            e.printStackTrace();
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