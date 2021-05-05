package pwr.edu.czart_boczar_projekt.entity;

import java.time.LocalDate;

public class ApplicationInformationView {
    private int id;
    private String leaveType;
    private LocalDate startDay;
    private LocalDate endDay;
    private int numberDay;
    private String statusApplicationType;
    private String employeeName;
    private String departmentName;
    private String projects;

    public ApplicationInformationView(int id, String leaveType, LocalDate startDay, LocalDate endDay, int numberDay,
                                      String statusApplicationType, String employeeName, String departmentName, String projects) {
        this.id = id;
        this.leaveType = leaveType;
        this.startDay = startDay;
        this.endDay = endDay;
        this.numberDay = numberDay;
        this.statusApplicationType = statusApplicationType;
        this.employeeName = employeeName;
        this.departmentName = departmentName;
        this.projects = projects;
    }

    public ApplicationInformationView(String leaveType, LocalDate startDay, LocalDate endDay, int numberDay,
                                      String statusApplicationType, String employeeName, String departmentName, String projects) {
        this.leaveType = leaveType;
        this.startDay = startDay;
        this.endDay = endDay;
        this.numberDay = numberDay;
        this.statusApplicationType = statusApplicationType;
        this.employeeName = employeeName;
        this.departmentName = departmentName;
        this.projects = projects;
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

    public LocalDate getStartDay() {
        return startDay;
    }

    public void setStartDay(LocalDate startDay) {
        this.startDay = startDay;
    }

    public LocalDate getEndDay() {
        return endDay;
    }

    public void setEndDay(LocalDate endDay) {
        this.endDay = endDay;
    }

    public int getNumberDay() {
        return numberDay;
    }

    public void setNumberDay(int numberDay) {
        this.numberDay = numberDay;
    }

    public String getStatusApplicationType() {
        return statusApplicationType;
    }

    public void setStatusApplicationType(String statusApplicationType) {
        this.statusApplicationType = statusApplicationType;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }
}
