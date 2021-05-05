package pwr.edu.czart_boczar_projekt.entity;

import java.time.LocalDate;

public class Application {
    private int id;
    private String leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private Employee employee;

    public Application(int id, String leaveType, LocalDate startDate, LocalDate endDate, String status, Employee employee) {
        this.id = id;
        this.leaveType = leaveType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.employee = employee;
    }

    public Application(Application application, String status) {
        this.id = application.id;
        this.leaveType = application.leaveType;
        this.startDate = application.startDate;
        this.endDate = application.endDate;
        this.status = status;
        this.employee = application.employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
