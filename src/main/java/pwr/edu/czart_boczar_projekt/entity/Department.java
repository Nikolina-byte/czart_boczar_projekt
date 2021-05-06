package pwr.edu.czart_boczar_projekt.entity;

import java.util.List;

public class Department {
    private int id;
    private String nameDepartment;
    private Employee employee;

    public Department(int id, String nameDepartment, Employee employee) {
        this.id = id;
        this.nameDepartment = nameDepartment;
        this.employee = employee;
    }

    public Department(String nameDepartment, Employee employee) {
        this.nameDepartment = nameDepartment;
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
