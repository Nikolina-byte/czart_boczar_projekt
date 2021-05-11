package pwr.edu.czart_boczar_projekt.entity;

import java.time.LocalDate;

/**
 * Class Implementation Application
 */
public class Application {
    private int id;
    private String leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private Employee employee;


    /***
     * Start Constructor of Application
     * @param id id of the application being entered
     * @param leaveType type of leave chosen by the employee
     * @param startDate start date of leave
     * @param endDate leave end date
     * @param status the status of the application at the moment
     * @param employee the employee who issued the request
     */
    public Application(int id, String leaveType, LocalDate startDate, LocalDate endDate, String status, Employee employee) {
        this.id = id;
        this.leaveType = leaveType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.employee = employee;
    }

    /***
     * Constructor of Application without id
     * @param leaveType type of leave chosen by the employee
     * @param startDate start date of leave
     * @param endDate leave end date
     * @param status the status of the application at the moment
     * @param employee the employee who issued the request
     */
    public Application(String leaveType, LocalDate startDate, LocalDate endDate, String status, Employee employee) {
        this.leaveType = leaveType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.employee = employee;
    }

    /***
     * Constructor of Application to update application table
     * @param application an application that is updated
     * @param status the status of the application at the moment
     */
    public Application(Application application, String status) {
        this.id = application.id;
        this.leaveType = application.leaveType;
        this.startDate = application.startDate;
        this.endDate = application.endDate;
        this.status = status;
        this.employee = application.employee;
    }

    /**
     * return method Id application
     * @return id of the application being entered
     */
    public int getId() {
        return id;
    }

    /**
     * setting method id application
     * @param id id of the application being entered
     */
    public void setId(int id) {
        this.id = id;
    }

    /***
     * return method type of leve
     * @return type of leave chosen by the employee
     */
    public String getLeaveType() {
        return leaveType;
    }

    /***
     * return method start date
     * @return start date of leave
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /***
     * return method end date
     * @return leave end date
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /***
     * return method status application
     * @return the status of the application at the moment
     */
    public String getStatus() {
        return status;
    }

    /***
     * setting method status application
     * @param status the status of the application at the moment
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /***
     * return method pracownika
     * @return the employee who issued the request
     */
    public Employee getEmployee() {
        return employee;
    }

    /***
     * setting method the employe who issued the request
     * @param employee the employee who issued the request
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
