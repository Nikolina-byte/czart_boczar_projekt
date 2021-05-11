package pwr.edu.czart_boczar_projekt.entity;

/***
 * Class Implementation DepartmentInformaionView
 */
public class DepartamentInformationView {
    private int id;
    private String name;
    private String employees;
    private String manager;

    /***
     * Start Constructor of DepartmentInformaionView
     * @param id department id
     * @param name the name of the department concerned
     * @param employees list of employees working in the department
     * @param manager head of the department concerned
     */
    public DepartamentInformationView(int id, String name, String employees, String manager) {
        this.id = id;
        this.name = name;
        this.employees = employees;
        this.manager = manager;
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
     * return method department name
     * @return the name of the department concerned
     */
    public String getName() {
        return name;
    }

    /***
     * setting method department name
     * @param name the name of the department concerned
     */
    public void setName(String name) {
        this.name = name;
    }

    /***
     * departmental manager return method
     * @return head of the department concerned
     */
    public String getManager() {
        return manager;
    }

    /***
     * departmental manager setting method
     * @param manager head of the department concerned
     */
    public void setManager(String manager) {
        this.manager = manager;
    }
}
