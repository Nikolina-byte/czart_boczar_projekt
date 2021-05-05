package pwr.edu.czart_boczar_projekt.entity;

import java.util.List;

public class Department {
    private int id;
    private String nameDepartment;
    private Employee employee;
    private List<Employee> employees;

    public Department(int id, String nameDepartment, Employee employee, List<Employee> employees) {
        this.id = id;
        this.nameDepartment = nameDepartment;
        this.employee = employee;
        this.employees = employees;
    }

    public Department(String nameDepartment, Employee employee, List<Employee> employees) {
        this.nameDepartment = nameDepartment;
        this.employee = employee;
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
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
