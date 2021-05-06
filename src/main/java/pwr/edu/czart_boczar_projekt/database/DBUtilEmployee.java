package pwr.edu.czart_boczar_projekt.database;

import pwr.edu.czart_boczar_projekt.entity.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class DBUtilEmployee extends DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/holidaydb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=CET";
    ;
    private static final String NAME = "admin";
    private static final String PASSWORD = "Adminadmin1";
    private Connection connection = null;

    public DBUtilEmployee() {
    }

    // Połączenie z bazą
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

    // Dodaje wniosek
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

    // Modyfikuje wniosek -> automatycznie do zatwierdzenia
    public void updateApplication(String empoyeeID) throws Exception {
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

    // Modyfikuje vacation data
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
            System.out.println(vacationData.getFreeDay());
            System.out.println(vacationData.getUsedDay());
            System.out.println(vacationData.getEmployee().getId());
            statement = conn.prepareStatement(sql);

            statement.execute();
        } finally {
            close(conn, statement, null);
        }
    }

    //Zwraca listę wnisków na podstawie stanu
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

    // Zwraca pracownika po ID
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


    // Zwraca pracownika po ID
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

    // Zwraca pracownika po ID
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


    // Anuluje wniosek - ? -> potem do zatwierdzenia



    //Zwraca wniosek na podstawie ID wniosku
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

    //zwraca listę wniosków dla danego pracownika
    public List<ApplicationInformationView> getApplicationListByIDemployee(int employeeID) throws SQLException, ClassNotFoundException {
        List<ApplicationInformationView> applications = new ArrayList<>();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;


        try {
            conn = dbConnect();

            String sql = "SELECT * FROM application_information WHERE Id_employee = " + employeeID;
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
