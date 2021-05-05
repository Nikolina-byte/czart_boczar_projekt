package pwr.edu.czart_boczar_projekt.entity;

import java.time.LocalDate;

public class EmployeeInformationView {
    private int id;
    private String name;
    private LocalDate birth;
    private String email;
    private String phone;
    private String department;
    private String projects;
    private String manager;

    public EmployeeInformationView() {
    }

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

    public EmployeeInformationView(EmployeeInformationView view) {
        this.id = view.id;
        this.name = view.name;
        this.birth = view.birth;
        this.email = view.email;
        this.phone = view.phone;
        this.department = view.department;
        this.projects = view.projects;
        this.manager = view.manager;
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

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "EmployeeInformationView: " +
                "\t" + name + '\t' + birth + '\t' + email + '\t' + phone + '\t' + department + '\t' + projects
                + '\t' + manager;
    }
}
