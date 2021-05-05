package pwr.edu.czart_boczar_projekt.database;

import pwr.edu.czart_boczar_projekt.entity.Application;
import pwr.edu.czart_boczar_projekt.entity.Employee;
import pwr.edu.czart_boczar_projekt.entity.EmployeeInformationView;
import pwr.edu.czart_boczar_projekt.entity.VacationData;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DBUtilEmployee extends DBUtil{
    private static final String URL = "jdbc:mysql://localhost:3306/holidaydb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=CET";;
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

    // Dodaje użytkownika
    public void addEmployee(Employee employee) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-M-dd");

            conn = dbConnect();

            String sql = "INSERT INTO employee(first_name, last_name, birth_date, `e-mail`, phone_number, `position`) " +
                    "VALUES(?,?,?,?,?,?)";

            statement = conn.prepareStatement(sql);
            statement.setString(1, employee.getFirst_name());
            statement.setString(2, employee.getLast_name());
            statement.setString(3, dtf1.format(employee.getBirth()));
            statement.setString(4, employee.getEmail());
            statement.setString(5, employee.getPhone());
            statement.setString(6, employee.getPosition());
            statement.execute();
        } finally {
            close(conn, statement, null);
        }
    }

    // Dodaje vacation_data
    public void addVacationData(VacationData vacationData) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-M-dd");

            conn = dbConnect();

            String sql = "INSERT INTO vacation_data (worked_years, education, free_days, used_days, employee_id) " +
                    "VALUES(?,?,?,?,?)";

            statement = conn.prepareStatement(sql);
            statement.setInt(1, vacationData.getWorkedYears());
            statement.setString(2, String.valueOf(vacationData.getEducationType()));
            statement.setInt(3, vacationData.getFreeDay());
            statement.setInt(4, vacationData.getUsedDay());
            statement.setInt(5, vacationData.getEmployee().getId());
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


    // Anuluje wniosek - ? -> potem do zatwierdzenia
}
