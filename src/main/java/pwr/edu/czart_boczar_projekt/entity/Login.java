package pwr.edu.czart_boczar_projekt.entity;

/**
 * Class Implementation Login
 */
public class Login {
    private int id;
    private String login;
    private String password;
    private Employee employee;

    /**
     * Constructor of Login Class
     * @param id number id of Login (User)
     * @param login name of Login object
     * @param password password of Login object
     * @param employee employee
     */
    public Login(int id, String login, String password, Employee employee) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.employee = employee;
    }


    /**
     *
     * @param login name of Login object
     * @param password password of Login object
     * @param employee employee
     */
    public Login(String login, String password, Employee employee) {
        this.login = login;
        this.password = password;
        this.employee = employee;
    }

    /**
     * Method returns id of Login
     * @return id number of Login
     */
    public int getId() {
        return id;
    }

    /**
     * Method sets id of Login
     * @param id number of id of Login object
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method returns name to login  of Login
     * @return login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Method sets name to login  of Login
     * @param login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Method gets the password of Login object
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method gets the password of Login object
     * @param password password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method returns the employee
     * @return employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Method sets the employee
     * @param employee employee
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
