package pwr.edu.czart_boczar_projekt.database;

import pwr.edu.czart_boczar_projekt.entity.Employee;
import pwr.edu.czart_boczar_projekt.entity.Login;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DBUtilAdmin extends DBUtil{
    private static final String URL = "jdbc:mysql://localhost:3306/holidaydb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=CET";
    private static final String NAME = "root";
    private static final String PASSWORD = "herbatka1";
    private Connection connection = null;

    public DBUtilAdmin() {
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

    // Zwraca wszystkich pracowników
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

}
