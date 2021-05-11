package pwr.edu.czart_boczar_projekt.entity;

import java.util.List;

/***
 * Class Implementation Department
 */
public class Department {
    private int id;
    private String nameDepartment;
    private Employee employee;

    /***
     * Constructor of Department
     * @param id department id
     * @param nameDepartment the name of the department concerned
     * @param employee head of the department concerned
     */
    public Department(int id, String nameDepartment, Employee employee) {
        this.id = id;
        this.nameDepartment = nameDepartment;
        this.employee = employee;
    }

    /***
     * Constructor of Department
     * @param nameDepartment the name of the department concerned
     * @param employee head of the department concerned
     */
    public Department(String nameDepartment, Employee employee) {
        this.nameDepartment = nameDepartment;
        this.employee = employee;
    }

    /***
     * return method Id department
     * @return department id
     */
    public int getId() {
        return id;
    }

    /***
     * setting method id department
     * @param id department id
     */
    public void setId(int id) {
        this.id = id;
    }

    /***
     * departmental manager return method
     * @return head of the department concerned
     */
    public Employee getEmployee() {
        return employee;
    }

    /***
     * departmental manager setting method
     * @param employee head of the department concerned
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
