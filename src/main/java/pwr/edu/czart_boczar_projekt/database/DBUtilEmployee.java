package pwr.edu.czart_boczar_projekt.database;

import pwr.edu.czart_boczar_projekt.entity.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/***
 * The class responsible for connecting Employee to the database
 */
public class DBUtilEmployee extends DBUtil {
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
     * the method responsible for adding the application to the database
     * @param application a mapped object of class application
     * @throws Exception
     */
    public void addApplication(Application application) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-M-dd");

            conn = dbConnect();

            String sql = "INSERT INTO application(type_leave, start_date, end_date, status, employee_id) " +
                    "VALUES(?,?,?,?,?)";

            statement = conn.prepareStatement(sql);
            statement.setString(1, String.valueOf(application.getLeaveType()));
            statement.setString(2, dtf1.format(application.getStartDate()));
            statement.setString(3, dtf1.format(application.getEndDate()));
            statement.setString(4, String.valueOf(application.getStatus()));
            statement.setInt(5, application.getEmployee().getId());
            statement.execute();

        } finally {
            close(conn, statement, null);
        }
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

            String sql = "UPDATE `application` SET " +
                    "type_leave=" + application.getLeaveType() +
                    ", start_date=" + application.getStartDate() +
                    ", end_date=" + application.getEndDate() +
                    ", status=" + application.getStatus() +
                    ", employee_id =" + application.getEmployee().getId() +
                    " WHERE id=" + application.getId();

            statement = conn.prepareStatement(sql);
            statement.execute();

        } finally {
            close(conn, statement, null);
        }

    }

    /***
     * method responsible for updating holiday data
     * @param vacationData a mapped class object with holiday data
     * @throws Exception
     */
    public void updateVacationData(VacationData vacationData) throws Exception {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = dbConnect();
            DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-M-dd");

            // zapytanie UPDATE
            String sql = "UPDATE `vacation_data` SET " +
                    "`free_days`=" + vacationData.getFreeDay() +
                    ", `used_days`=" + vacationData.getUsedDay() +
                    " WHERE `employee_id`="+vacationData.getEmployee().getId() +";";
            statement = conn.prepareStatement(sql);

            statement.execute();
        } finally {
            close(conn, statement, null);
        }
    }

    /***
     * method responsible for receiving the application based on the status and manager
     * @param statusApplication the status of the application
     * @param employee a mapped employee class object, which defines the manager
     * @return List of matched applications
     * @throws Exception
     */
    public List<ApplicationInformationView> getApplicationByStatusAndManager(String statusApplication, int employee) throws Exception {
        List<ApplicationInformationView> applications = new ArrayList<>();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conn = dbConnect();

            String sql = "SELECT * FROM application_information WHERE Status = '" + statusApplication + "'" +
                    " AND Id_employee = " + employee;
            statement = conn.createStatement();

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                // pobranie danych z rzedu
                int id = resultSet.getInt("Id");
                int employeeID = resultSet.getInt("Id_employee");
                String leaveType = resultSet.getString("LeaveType");
                String startDay = resultSet.getString("StartDay");
                String endDay = resultSet.getString("EndDay");
                int numberDay = resultSet.getInt("NumberDay");
                String status = resultSet.getString("Status");
                String name = resultSet.getString("Name");
                String department = resultSet.getString("Department");
                String projects = resultSet.getString("Projects");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-dd");
                // dodanie do listy nowego obiektu
                applications.add(new ApplicationInformationView(id, employeeID, leaveType, LocalDate.parse(startDay, formatter),
                        LocalDate.parse(endDay, formatter), numberDay, status, name, department, projects));
            }
        } finally {
            close(conn, statement, resultSet);
        }
        return applications;
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
                // dodanie do listy nowego obiektu
                employee = new Employee(id, firstName, lastName, LocalDate.parse(birth, formatter), email, phone, position, status);
            }
        } finally {
            close(conn, statement, resultSet);
        }
        return employee;
    }


    /***
     * method responsible for receiving data on holidays on the basis of employee id
     * @param employeeID the id of the selected employee
     * @return vacation data object mapped
     * @throws Exception
     */
    public VacationData getVacationDataByEmploye(int employeeID) throws Exception {
        VacationData vacationData = null;
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conn = dbConnect();

            String sql = "SELECT * FROM vacation_data WHERE employee_id = " + employeeID;
            statement = conn.createStatement();

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int workedYears = resultSet.getInt("worked_years");
                String educationType = resultSet.getString("education");
                int freeDays = resultSet.getInt("free_days");
                int usedDays = resultSet.getInt("used_days");
                int employeeId = resultSet.getInt("employee_id");
                String startCompany = resultSet.getString("start_company");
                String dateMaxVacation = resultSet.getString("date_max_vacation");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-dd");
                // dodanie do listy nowego obiektu

                Employee employeeByID = getEmployeeByID(employeeId);
                if(dateMaxVacation==null){
                    dateMaxVacation = "2010-01-01";
                }

                vacationData = new VacationData(workedYears, educationType, freeDays, usedDays, employeeByID,
                        LocalDate.parse(startCompany, formatter), LocalDate.parse(dateMaxVacation, formatter));
            }
        } finally {
            close(conn, statement, resultSet);
        }
        return vacationData;
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
     * the method responsible for returning the conclusion based on the status and id empolyer
     * @param statusApplication the status of the selected application
     * @param employeeID the id of the selected employee
     * @return a list of matching application objects
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<ApplicationInformationView> getApplicationsByStatusAndEmployeeID(String statusApplication, int employeeID) throws SQLException, ClassNotFoundException {
        List<ApplicationInformationView> applications = new ArrayList<>();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;


        try {
            conn = dbConnect();

            String sql = "SELECT * FROM application_information WHERE Id_employee = " + employeeID+" AND Status = " + statusApplication;

            statement = conn.createStatement();

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                // pobranie danych z rzedu
                int id = resultSet.getInt("id");
                int id_employee = resultSet.getInt("Id_employee");
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
                applications.add(new ApplicationInformationView(id, id_employee, leaveType, LocalDate.parse(startDay, formatter),
                        LocalDate.parse(endDay, formatter), numberDay, status, employee, department, projects));
            }
        } finally {
            close(conn, statement, resultSet);
        }
        return applications;
    }

    /***
     * method responsible for removing the application from the database
     * @param id id of the selected application
     * @throws Exception
     */
    public void deleteApplication(int id) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = dbConnect();

            String sql = "DELETE FROM application WHERE id =?";

            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            statement.execute();

        } finally {
            close(conn, statement, null);
        }
    }
}
