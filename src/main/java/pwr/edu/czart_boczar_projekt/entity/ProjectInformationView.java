package pwr.edu.czart_boczar_projekt.entity;

import java.time.LocalDate;

public class ProjectInformationView {
    private int id;
    private String name;
    private String description;
    private LocalDate deadline;
    private String employees;

    public ProjectInformationView(int id, String name, String description, LocalDate deadline, String employees) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.employees = employees;
    }

    public ProjectInformationView(String name, String description, LocalDate deadline, String employees) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.employees = employees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getEmployees() {
        return employees;
    }

    public void setEmployees(String employees) {
        this.employees = employees;
    }
}
