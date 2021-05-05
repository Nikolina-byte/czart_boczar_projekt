package pwr.edu.czart_boczar_projekt.entity;

public class DepartamentInformationView {
    private int id;
    private String name;
    private String employees;
    private String manager;

    public DepartamentInformationView(int id, String name, String employees, String manager) {
        this.id = id;
        this.name = name;
        this.employees = employees;
        this.manager = manager;
    }

    public DepartamentInformationView(String name, String employees, String manager) {
        this.name = name;
        this.employees = employees;
        this.manager = manager;
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

    public String getEmployees() {
        return employees;
    }

    public void setEmployees(String employees) {
        this.employees = employees;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
}
