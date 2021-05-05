package pwr.edu.czart_boczar_projekt.servlet;

import pwr.edu.czart_boczar_projekt.database.DBUtilManager;
import pwr.edu.czart_boczar_projekt.entity.DepartamentInformationView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AdminDepartmentServlet")
public class AdminDepartmentServlet extends HttpServlet {

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
        String command = request.getParameter("EMPLOYEE");
        System.out.println(command);
        // Wszystkie Departamenty
        List<DepartamentInformationView> departaments = null;
        try {
            departaments = dbUtil.getDepartments();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("DEPARTMENT_LIST", departaments);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_department_view.jsp");
        dispatcher.forward(request, response);
    }
}
