package pwr.edu.czart_boczar_projekt.entity;

import java.time.LocalDate;

/**
 * Class Implementation EmployeeInformationView
 */
public class EmployeeInformationView {
    private int id;
    private String name;
    private LocalDate birth;
    private String email;
    private String phone;
    private String department;
    private String projects;
    private String manager;

    /**
     *
     * @param id number of employeeInformationView
     * @param name name of employee
     * @param birth date of birth
     * @param email email
     * @param phone phone
     * @param department department
     * @param projects projects
     * @param manager manager
     */
    public EmployeeInformationView(int id, String name, LocalDate birth, String email, String phone, String department, String projects,
                                   String manager) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.email = email;
        this.phone = phone;
        this.department = department;
        this.projects = projects;
        this.manager = manager;
    }


    /**
     * Method returns the id of EmployeeInformationView
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Method sets the id of EmployeeInformationView
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Methid gets the name of Employee
     * @return name of Employee
     */
    public String getName() {
        return name;
    }

    /**
     * Method sets name of Employee
     * @param name of Employee
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Method returns the name the department
     * @return name of department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Method sets the name of department
     * @param department name of department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Method gets the Manager
     * @return name of manager
     */
    public String getManager() {
        return manager;
    }

    /**
     * Method sets the manager
     * @param manager of manager
     */
    public void setManager(String manager) {
        this.manager = manager;
    }

    /**
     * Method toString of EmployeeInformationView
     * @return string with information of EmployeeInformationView
     */
    @Override
    public String toString() {
        return "EmployeeInformationView: " +
                "\t" + name + '\t' + birth + '\t' + email + '\t' + phone + '\t' + department + '\t' + projects
                + '\t' + manager;
    }
}
