package pwr.edu.czart_boczar_projekt.database;

import pwr.edu.czart_boczar_projekt.HashCalculator;
import pwr.edu.czart_boczar_projekt.entity.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/***
 * The class responsible for connecting Admin to the database
 */
public class DBUtilAdmin extends DBUtil{
    private static final String URL = "jdbc:mysql://localhost:3306/holidaydb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=CET";
    private static final String NAME = "root";
//    private static final String PASSWORD = "qwerty";
    private static final String PASSWORD = "herbatka1";
    private Connection connection = null;

    /**
     * The parameterless constructor
     */
    public DBUtilAdmin() {
    }

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

    /**
     * Method that returns login data by user id
     * @param userLogin user's ID
     * @return a mapped array object storing logins and passwords
     * @throws Exception
     */
    public Login getLoginData(String userLogin) throws Exception {
        Login login = null;

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conn = dbConnect();
            String sql = "SELECT * FROM login_data WHERE login = '" + userLogin + "'";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String loginData = resultSet.getString("login");
                String passwordData = resultSet.getString("password");
                int employeeID = resultSet.getInt("employee_id");

                Employee employee = getEmployeeByID(employeeID);
                login = new Login(loginData, passwordData, employee);
            }
        } finally {
            close(conn, statement, resultSet);
        }
        return login;
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
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String birth = resultSet.getString("birth_date");
                String email = resultSet.getString("e-mail");
                String phone= resultSet.getString("phone_number");
                String position = resultSet.getString("position");
                String status = resultSet.getString("status");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-dd");
                employee = new Employee(id, firstName, lastName, LocalDate.parse(birth, formatter), email, phone, position, status);
            }
        } finally {
            close(conn, statement, resultSet);
        }
        return employee;
    }

    /***
     * method responsible for adding a user
     * @param employee a mapped employee class object
     * @return employe class object with id set
     * @throws Exception
     */
    public Employee addEmployee(Employee employee) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-M-dd");

            conn = dbConnect();

            String sql = "INSERT INTO employee(first_name, last_name, birth_date, `e-mail`, phone_number, `position`, status) " +
                    "VALUES(?,?,?,?,?,?,?)";

            statement = conn.prepareStatement(sql);
            statement.setString(1, employee.getFirst_name());
            statement.setString(2, employee.getLast_name());
            statement.setString(3, dtf1.format(employee.getBirth()));
            statement.setString(4, employee.getEmail());
            statement.setString(5, employee.getPhone());
            statement.setString(6, employee.getPosition());
            statement.setString(7, employee.getStatus());
            statement.execute();

            Employee byEverything = getEmployeeByEverything(employee);
            employee.setId(byEverything.getId());
        } finally {
            close(conn, statement, null);
        }

        return employee;
    }

    /***
     * The employee return method after all
     * @param employee a mapped employee class object
     * @return a mapped employee class object with id
     * @throws Exception
     */
    public Employee getEmployeeByEverything(Employee employee) throws Exception {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conn = dbConnect();

            String sql = "SELECT * FROM employee WHERE " +
                    "first_name ='" + employee.getFirst_name() +
                    "' AND last_name='" + employee.getLast_name() +
                    "' AND birth_date='"+employee.getBirth() +
                    "' AND `e-mail`='"+employee.getEmail() +
                    "' AND phone_number='"+employee.getPhone() + "';";
            statement = conn.createStatement();

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String birth = resultSet.getString("birth_date");
                String email = resultSet.getString("e-mail");
                String phone= resultSet.getString("phone_number");
                String position = resultSet.getString("position");
                String status = resultSet.getString("status");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-dd");

                employee = new Employee(id, firstName, lastName, LocalDate.parse(birth, formatter), email, phone, position, status);
            }
        } finally {
            close(conn, statement, resultSet);
        }
        return employee;
    }

    /***
     * method responsible for adding information about user's vacation days
     * @param vacationData a mapped vacation data class object with id
     * @throws Exception
     */
    public void addVacationData(VacationData vacationData) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-M-dd");

            conn = dbConnect();

            String sql = "INSERT INTO vacation_data (worked_years, education, free_days, used_days, employee_id, " +
                    "start_company, date_max_vacation) " +
                    "VALUES(?,?,?,?,?,?,?)";

            statement = conn.prepareStatement(sql);
            statement.setInt(1, vacationData.getWorkedYears());
            statement.setString(2, String.valueOf(vacationData.getEducationType()));
            statement.setInt(3, vacationData.getFreeDay());
            statement.setInt(4, vacationData.getUsedDay());
            statement.setInt(5, vacationData.getEmployee().getId());
            statement.setString(6,  dtf1.format(vacationData.getStartCompany()));
            statement.setString(7,  dtf1.format(vacationData.getDateMaxVacation()));
            statement.execute();
        } finally {
            close(conn, statement, null);
        }
    }

    /***
     * method responsible for adding login data to the database
     * @param login a mapped login class object with id
     * @throws Exception
     */
    public void addLoginData(Login login) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = dbConnect();

            String sql = "INSERT INTO login_data(login, password, employee_id) " +
                    "VALUES(?,?,?)";

            statement = conn.prepareStatement(sql);
            statement.setString(1, login.getLogin());
            statement.setString(2, HashCalculator.hashSHA256(login.getPassword()));
            statement.setInt(3, login.getEmployee().getId());
            statement.execute();
            //dropnij baze i dodaj iddo
        } finally {
            close(conn, statement, null);
        }
    }

    /***
     * method providing login by login data
     * @param loginInput entered login when logging in
     * @return a mapped login class object
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Login getLoginByLogin(String loginInput) throws SQLException, ClassNotFoundException {
        Login login = null;
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conn = dbConnect();

            String sql = "SELECT * FROM login_data WHERE login = '" + loginInput + "'";
            statement = conn.createStatement();

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                // pobranie danych z rzedu
                String loginEmployee = resultSet.getString("login");
                String passwordEmployee = resultSet.getString("password");
                int employeeId = resultSet.getInt("employee_id");

                Employee employee = getEmployeeByID(employeeId);

                login = new Login(loginEmployee, passwordEmployee,employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, statement, resultSet);
        }
        return login;
    }

    /***
     * method responsible for retrieving departments by name
     * @param nameDepartment the name of the department concerned
     * @return a mapped department object
     * @throws Exception
     */
    public Department getDepartmentByName(String nameDepartment) throws Exception {
        Department departament = null;

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conn = dbConnect();

            String sql = "SELECT * FROM department WHERE name_deparment = '" + nameDepartment +"'";
            statement = conn.createStatement();

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = Integer.parseInt(resultSet.getString("id"));
                String name = resultSet.getString("name_deparment");
                String employee = resultSet.getString("employee_id");

                Employee employeeByID = getEmployeeByID(Integer.parseInt(employee));
                departament = new Department(id, name, employeeByID);
            }
        } finally {
            close(conn, statement, resultSet);
        }
        return departament;
    }

    /***
     * method responsible for adding employees to a given department
     * @param departmentID the id of the selected department
     * @param employeeID the id of the selected employee
     * @throws Exception
     */
    public void addDepartmentHasEmployee(int departmentID, int employeeID) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = dbConnect();

            String sql = "INSERT INTO department_has_employees(department_id, employee_id) " +
                    "VALUES(?,?)";

            statement = conn.prepareStatement(sql);
            statement.setInt(1, departmentID);
            statement.setInt(2, employeeID);

            statement.execute();

        } finally {
            close(conn, statement, null);
        }
    }

    /***
     * the method responsible for assigning employees to the manager
     * @param managerID the id of the selected manager
     * @param employeeID the id of the selected employee
     * @throws Exception
     */
    public void addManagerHasEmployee(int managerID, int employeeID) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = dbConnect();

            String sql = "INSERT INTO manager_has_employee(manager_id, employee_id) " +
                    "VALUES(?,?)";

            statement = conn.prepareStatement(sql);
            statement.setInt(1, managerID);
            statement.setInt(2, employeeID);

            statement.execute();

        } finally {
            close(conn, statement, null);
        }
    }

    /***
     * a method responsible for updating applications and employee vacation data
     * @param employeeID the id of the selected employee
     * @throws Exception
     */
    public void updateBase(int employeeID) throws Exception {
        updateApplication(employeeID);
        updateVacationData(employeeID);
    }

    /***
     * a method that returns a list of all employers
     * @return a list of employee class objects
     * @throws Exception
     */
    public List<Employee> getEmployees() throws Exception {
        List<Employee> employees = new ArrayList<>();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conn = dbConnect();
            String sql = "SELECT * FROM employee";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String birth = resultSet.getString("birth_date");
                String email = resultSet.getString("e-mail");
                String phone= resultSet.getString("phone_number");
                String position = resultSet.getString("position");
                String status = resultSet.getString("status");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-dd");

                employees.add(new Employee(id, firstName, lastName, LocalDate.parse(birth, formatter), email, phone, position, status));
            }
        } finally {
            close(conn, statement, resultSet);
        }
        return employees;
    }


    private void updateApplication(int empoyeeID) throws Exception {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = dbConnect();

            String sql = "UPDATE `application` SET `status`='zrealizowany' WHERE `status`='zaakceptowany' AND " +
                    "`start_date`=curdate() AND employee_id=" + empoyeeID;

            statement = conn.prepareStatement(sql);
            statement.execute();
        } finally {
            close(conn, statement, null);
        }
    }

    private void updateVacationData(int empoyeeID) throws Exception {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = dbConnect();

            String sql = " UPDATE `vacation_data` SET `free_days`=26 WHERE `date_max_vacation`=curdate() AND employee_id="+ empoyeeID;

            statement = conn.prepareStatement(sql);
            statement.execute();
        } finally {
            close(conn, statement, null);
        }
    }
}
