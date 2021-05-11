package pwr.edu.czart_boczar_projekt.database;

import pwr.edu.czart_boczar_projekt.database.DBUtil;
import pwr.edu.czart_boczar_projekt.entity.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/***
 * The class responsible for connecting Manager to the database
 */
public class DBUtilManager extends DBUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/holidaydb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=CET";
    private static final String NAME = "admin";
    private static final String PASSWORD = "Adminadmin1";
    private Connection connection = null;

    /**
     * The method responsible for creating a connection with the database for the admin
     * @return A connection (session) with a specific database
     * @throws SQLException An exception that provides information on a database access error or other errors.
     * @throws ClassNotFoundException Thrown when an application tries to load in a class through its string name using
     */
    public Connection dbConnect() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("No MySQL JDBC Driver found." + "\n");
            e.printStackTrace();
            throw e;
        }
        try {
            connection = DriverManager.getConnection(URL, NAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Connection Failed! Try again." + "\n");
            e.printStackTrace();
            throw e;
        }
        return connection;
    }

    /***
     * the method responsible for returning all employees
     * @return list of all employees entered into the database
     * @throws Exception
     */
    public List<EmployeeInformationView> getEmployee() throws Exception {
        List<EmployeeInformationView> employee = new ArrayList<>();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conn = dbConnect();

            String sql = "SELECT * FROM user_information";
            statement = conn.createStatement();

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                // pobranie danych z rzedu
                int id = resultSet.getInt("id");
                String name = resultSet.getString("Name");
                String birth = resultSet.getString("Birth");
                String email = resultSet.getString("E-mail");
                String phone= resultSet.getString("Phone");
                String department = resultSet.getString("Department");
                String projects = resultSet.getString("Projects");
                String manager = resultSet.getString("Manager");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-dd");
                // dodanie do listy nowego obiektu
                employee.add(new EmployeeInformationView(id, name, LocalDate.parse(birth, formatter), email, phone, department, projects, manager));
            }
        } finally {
            close(conn, statement, resultSet);
        }
        return employee;
    }

    /***
     * method responsible for obtaining data about the department on the basis of view by manager
     * @param employeeName the name of the selected manager
     * @return a mapped department information view object
     * @throws Exception
     */
    public DepartamentInformationView getDepartmentByManager(String employeeName) throws Exception {
        DepartamentInformationView departament = null;

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conn = dbConnect();

            String sql = "SELECT * FROM department_information WHERE manager='" + employeeName +"'";
            statement = conn.createStatement();

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = Integer.parseInt(resultSet.getString("id"));
                String name = resultSet.getString("Name");
                String employees = resultSet.getString("Employees");
                String manager = resultSet.getString("Manager");
                departament = new DepartamentInformationView(id, name, employees, manager);
            }
        } finally {
            close(conn, statement, resultSet);
        }
        return departament;
    }

    /***
     * method responsible for receiving user information with a view by its id
     * @param employeeID the id of the selected employee
     * @return A mapped employee information view object
     * @throws Exception
     */
    public EmployeeInformationView getEmployeeViewByID(int employeeID) throws Exception {
        EmployeeInformationView employee = null;
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conn = dbConnect();

            String sql = "SELECT * FROM user_information WHERE id = " + employeeID;
            statement = conn.createStatement();

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                // pobranie danych z rzedu
                int id = resultSet.getInt("id");
                String name = resultSet.getString("Name");
                String birth = resultSet.getString("Birth");
                String email = resultSet.getString("E-mail");
                String phone= resultSet.getString("Phone");
                String department = resultSet.getString("Department");
                String projects = resultSet.getString("Projects");
                String manager = resultSet.getString("Manager");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-dd");
                // dodanie do listy nowego obiektu
                employee = new EmployeeInformationView(id, name, LocalDate.parse(birth, formatter), email, phone, department, projects, manager);
            }
        } finally {
            close(conn, statement, resultSet);
        }
        return employee;
    }

    /***
     * method that returns the employee object after the id
     * @param employeeID emplyee's id
     * @return a mapped employee class object
     * @throws Exception
     */
    public Employee getEmployeeByID(int employeeID) throws Exception {
        Employee employee = null;
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conn = dbConnect();

            String sql = "SELECT * FROM employee WHERE id = " + employeeID;
            statement = conn.createStatement();

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                // pobranie danych z rzedu
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String birth = resultSet.getString("birth_date");
                String email = resultSet.getString("e-mail");
                String phone= resultSet.getString("phone_number");
                String position = resultSet.getString("position");
                String status = resultSet.getString("status");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-dd");
                // dodanie do listy nowego obiektu
                employee = new Employee(id, firstName, lastName, LocalDate.parse(birth, formatter), email, phone, position, status);
            }
        } finally {
            close(conn, statement, resultSet);
        }
        return employee;
    }

    /***
     * the method responsible for receiving information about the employee by the manager
     * @param employeeManager manager's name and surname
     * @return a mapped employee class object
     * @throws Exception
     */
    public List<EmployeeInformationView> getEmployeeByManager(String employeeManager) throws Exception {
        List<EmployeeInformationView> employee = new ArrayList<>();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conn = dbConnect();

            String sql = "SELECT * FROM user_information WHERE Manager = '" + employeeManager + "'";
            statement = conn.createStatement();

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                // pobranie danych z rzedu
                int id = resultSet.getInt("id");
                String name = resultSet.getString("Name");
                String birth = resultSet.getString("Birth");
                String email = resultSet.getString("E-mail");
                String phone= resultSet.getString("Phone");
                String department = resultSet.getString("Department");
                String projects = resultSet.getString("Projects");
                String manager = resultSet.getString("Manager");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-dd");
                // dodanie do listy nowego obiektu
                employee.add(new EmployeeInformationView(id, name, LocalDate.parse(birth, formatter), email, phone, department, projects, manager));
            }
        } finally {
            close(conn, statement, resultSet);
        }
        return employee;
    }

    /***
     * method responsible for receiving the application based on the status and manager
     * @param statusApplication the status of the application
     * @param manager id of the selected manager
     * @return List of matched applications (from view)
     * @throws Exception
     */
    public List<ApplicationInformationView> getApplicationByStatusAndManager(String statusApplication, int manager) throws Exception {
        List<ApplicationInformationView> applications = new ArrayList<>();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conn = dbConnect();

            String sql = "SELECT * FROM application_information WHERE Status = '" + statusApplication + "'" +
                    " AND MANAGER = " + manager;
            statement = conn.createStatement();

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                // pobranie danych z rzedu
                int id = resultSet.getInt("id");
                String leaveType = resultSet.getString("LeaveType");
                String startDay = resultSet.getString("StartDay");
                String endDay = resultSet.getString("EndDay");
                int numberDay = resultSet.getInt("NumberDay");
                String status = resultSet.getString("Status");
                String employee = resultSet.getString("Name");
                String department = resultSet.getString("Department");
                String projects = resultSet.getString("Projects");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-dd");
                // dodanie do listy nowego obiektu
                applications.add(new ApplicationInformationView(id, leaveType, LocalDate.parse(startDay, formatter),
                        LocalDate.parse(endDay, formatter), numberDay, status, employee, department, projects));
            }
        } finally {
            close(conn, statement, resultSet);
        }
        return applications;
    }

    /***
     * the method responsible for receiving the request based on the ID
     * @param applicationID the id of the composite application
     * @return a mapped object of class application
     * @throws Exception
     */
    public Application getApplicationByID(String applicationID) throws Exception {
        Application application = null;

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conn = dbConnect();

            String sql = "SELECT * FROM application WHERE id = " + applicationID;
            statement = conn.createStatement();

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                // pobranie danych z rzedu
                int id = resultSet.getInt("id");
                String leaveType = resultSet.getString("type_leave");
                String startDay = resultSet.getString("start_date");
                String endDay = resultSet.getString("end_date");
                String status = resultSet.getString("status");
                int employeeId = resultSet.getInt("employee_id");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-dd");

                Employee employee = getEmployeeByID(employeeId);

                application = new Application(id, leaveType, LocalDate.parse(startDay, formatter),
                        LocalDate.parse(endDay, formatter), status, employee);
            }
        } finally {
            close(conn, statement, resultSet);
        }
        return application;
    }

    /***
     * the method responsible for updating the application
     * @param application a mapped object of class application
     * @throws Exception
     */
    public void updateApplication(Application application) throws Exception {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = dbConnect();
            DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-M-dd");

            // zapytanie UPDATE
            String sql = "UPDATE application SET type_leave=?, start_date=?," +
                    "end_date=?, status=?, employee_id=? " +
                    "WHERE id =?";

            statement = conn.prepareStatement(sql);
            statement.setString(1, String.valueOf(application.getLeaveType()));
            statement.setString(2, dtf1.format(application.getStartDate()));
            statement.setString(3, dtf1.format(application.getEndDate()));
            statement.setString(4, String.valueOf(application.getStatus()));
            statement.setInt(5, application.getEmployee().getId());
            statement.setInt(6, application.getId());

            statement.execute();
        } finally {
            close(conn, statement, null);
        }
    }

    /***
     * the method responsible for the employees' adaptation
     * @param employee a mapped employee class object
     * @throws Exception
     */
    public void updateEmployee(Employee employee) throws Exception {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = dbConnect();
            DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-M-dd");

            // zapytanie UPDATE
            String sql = "UPDATE employee SET first_name=?, last_name=?," +
                    "birth_date=?, `e-mail`=?, phone_number=? " +
                    "WHERE id =?";

            statement = conn.prepareStatement(sql);
            statement.setString(1, employee.getFirst_name());
            statement.setString(2, employee.getLast_name());
            statement.setString(3, dtf1.format(employee.getBirth()));
            statement.setString(4, employee.getEmail());
            statement.setString(4, employee.getPhone());
            statement.setInt(5, employee.getId());

            statement.execute();
        } finally {
            close(conn, statement, null);
        }
    }

    /***
     * method responsible for removing the employee from the database
     * @param id the id of the selected employee
     * @throws Exception
     */
    public void deleteEmployee(String id) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            int employeeID = Integer.parseInt(id);

            conn = dbConnect();

            deleteApplication(employeeID);
            deleteVacationData(employeeID);
            deleteManagerHasEmployee(employeeID);
            deleteEmployeeHasProject(employeeID);
            deleteDepartmentHasEmployees(employeeID);

            String sql = "DELETE FROM employee WHERE id =?";

            statement = conn.prepareStatement(sql);
            statement.setInt(1, employeeID);
            statement.execute();

        } finally {
            close(conn, statement, null);
        }
    }

    private void deleteApplication(int id) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = dbConnect();

            String sql = "DELETE FROM application WHERE employee_id =?";

            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();

        } finally {
            close(conn, statement, null);
        }
    }

    private void deleteVacationData(int id) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = dbConnect();

            String sql = "DELETE FROM vacation_data WHERE employee_id =?";

            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();

        } finally {
            close(conn, statement, null);
        }
    }


    private void deleteManagerHasEmployee(int id) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = dbConnect();

            String sql = "DELETE FROM manager_has_employee WHERE employee_id =?";

            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();

        } finally {
            close(conn, statement, null);
        }
    }

    private void deleteEmployeeHasProject(int id) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = dbConnect();

            String sql = "DELETE FROM employee_has_project WHERE employee_id =?";

            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();

        } finally {
            close(conn, statement, null);
        }
    }

    private void deleteDepartmentHasEmployees(int id) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = dbConnect();

            String sql = "DELETE FROM department_has_employees WHERE employee_id =?";

            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();

        } finally {
            close(conn, statement, null);
        }
    }
}