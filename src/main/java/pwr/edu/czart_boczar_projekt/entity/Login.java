package pwr.edu.czart_boczar_projekt.entity;

public class Login {
    private int id;
    private String login;
    private String password;
    private Employee employee;

    public Login(int id, String login, String password, Employee employee) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.employee = employee;
    }

    public Login(String login, String password, Employee employee) {
        this.login = login;
        this.password = password;
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
