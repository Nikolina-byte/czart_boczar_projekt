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
                    "VALUES(?,?,?,?,?,?)";

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
    public void updateApplication(Application application) throws Exception {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = dbConnect();
            DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-M-dd");

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



    // Modyfikuje vacation data
    public void updateVacationData(VacationData vacationData) throws Exception {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = dbConnect();
            DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-M-dd");

            // zapytanie UPDATE
            String sql = "UPDATE vacation_data SET worked_years=?, education=?," +
                    "free_days=?, used_days=?, employee_id=? " +
                    "WHERE id =?";

            statement = conn.prepareStatement(sql);
            statement.setInt(1, vacationData.getWorkedYears());
            statement.setString(2, String.valueOf(vacationData.getEducationType()));
            statement.setInt(3, vacationData.getFreeDay());
            statement.setInt(4, vacationData.getUsedDay());
            statement.setInt(5, vacationData.getEmployee().getId());
            statement.setInt(6, vacationData.getId());

            statement.execute();
        } finally {
            close(conn, statement, null);
        }
    }

    // Zwraca pracownika po ID
    public EmployeeInformationView getEmployeeByID(int employeeID) throws Exception {
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
                String phone = resultSet.getString("Phone");
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

    public Employee getEmployee(int employeeID) throws Exception {

        Employee e = null;
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
                String name = resultSet.getString("first_name");
                String surname = resultSet.getString("last_name");
                String birth = resultSet.getString("birth_date");
                String email = resultSet.getString("e-mail");
                String phone = resultSet.getString("phone_number");
                String position = resultSet.getString("position");
                String status = resultSet.getString("status");

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-M-dd");
                LocalDate birthDate = LocalDate.parse(birth, dtf);

                e = new Employee(name, surname, birthDate, email, phone, position, status);

            }
        } finally {
            close(conn, statement, resultSet);
        }

        return e;
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

                Employee employee = getEmployee(employeeId);
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


}
