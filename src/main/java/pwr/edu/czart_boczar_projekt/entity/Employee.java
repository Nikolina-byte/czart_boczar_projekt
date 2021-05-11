package pwr.edu.czart_boczar_projekt.entity;

import java.time.LocalDate;

/**
 * Class Implementation Employee
 */
public class Employee {
    private int id;
    private String first_name;
    private String last_name;
    private LocalDate birth;
    private String email;
    private String phone;
    private String position;
    private String status;

    /**
     * Constructor of Employee
     *
     * @param id         number of employee
     * @param first_name name of employee
     * @param last_name  surname of employee
     * @param birth      date of birth of employee
     * @param email      email of employee
     * @param phone      phone of employee
     * @param position   position of employee (Manger/Employee)
     * @param status     status of employee
     */
    public Employee(int id, String first_name, String last_name, LocalDate birth, String email, String phone, String position, String status) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth = birth;
        this.email = email;
        this.phone = phone;
        this.position = position;
        this.status = status;
    }

    /**
     * Consturctor of Employee
     * @param first_name name of employee
     * @param last_name surname of employee
     * @param birth      date of birth of employee
     * @param email      email of employee
     * @param phone      phone of employee
     * @param position   @param position position
     * @param status     status of employee
     */
    public Employee(String first_name, String last_name, LocalDate birth, String email, String phone, String position, String status) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth = birth;
        this.email = email;
        this.phone = phone;
        this.position = position;
        this.status = status;
    }

    /**
     * Method returns the  id(number) of employee
     * @return number of emploee
     */
    public int getId() {
        return id;
    }

    /**
     *
     * Method sets the  id(number) of employee
     * @param id number of emploee
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *  Method returns first name
     * @return name of Employee
     */
    public String getFirst_name() {
        return first_name;
    }


    /**
     * Method returns last name
     * @return surname of Employee
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * Method returns the date of birth
     * @return date of birth of employee
     */
    public LocalDate getBirth() {
        return birth;
    }

    /**
     * Method returns the email of employee
     * @return mail of employee
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method returns the phone of employee
     * @return phone of employee
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Method returns the position of employee
     * @return position of employee (Manager/Employee)
     */
    public String getPosition() {
        return position;
    }

    /**
     * Method returns the status  of employee (at work/on vacation)
     * @return status at work of employee
     */
    public String getStatus() {
        return status;
    }

    /**
     * Method sets the status  of employee (at work/on vacation)
     * @param status status at work of employee
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
