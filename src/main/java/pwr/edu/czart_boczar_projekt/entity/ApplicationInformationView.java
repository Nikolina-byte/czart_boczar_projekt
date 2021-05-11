package pwr.edu.czart_boczar_projekt.entity;

import java.time.LocalDate;

/***
 * Class Implementation ApplicationInformaionView
 */
public class ApplicationInformationView {
    private int id;
    private int idEmployee;
    private String leaveType;
    private LocalDate startDay;
    private LocalDate endDay;
    private int numberDay;
    private String statusApplicationType;
    private String employeeName;
    private String departmentName;
    private String projects;


    /***
     * Start Constructor of ApplicationInformaionView
     * @param id id of the application being entered
     * @param leaveType type of leave chosen by the employee
     * @param startDay vstart date of leave
     * @param endDay leave end date
     * @param numberDay the number of vacation days
     * @param statusApplicationType the status of the application at the moment
     * @param employeeName the name and surname of the employee who submitted the application
     * @param departmentName name of the department to which the employee belongs
     * @param projects projects in which the applicant participates
     */
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


    /***
     * Constructor of ApplicationInformationView with idEmployee
     * @param id id of the application being entered
     * @param idEmployee id of the employee who submitted the application
     * @param leaveType type of leave chosen by the employee
     * @param startDay start date of leave
     * @param endDay leave end date
     * @param numberDay the number of vacation days
     * @param statusApplicationType the status of the application at the moment
     * @param employeeName the name and surname of the employee who submitted the application
     * @param departmentName name of the department to which the employee belongs
     * @param projects projects in which the applicant participates
     */
    public ApplicationInformationView(int id, int idEmployee, String leaveType, LocalDate startDay, LocalDate endDay, int numberDay, String statusApplicationType, String employeeName, String departmentName, String projects) {
        this.id = id;
        this.idEmployee = idEmployee;
        this.leaveType = leaveType;
        this.startDay = startDay;
        this.endDay = endDay;
        this.numberDay = numberDay;
        this.statusApplicationType = statusApplicationType;
        this.employeeName = employeeName;
        this.departmentName = departmentName;
        this.projects = projects;
    }

    /***
     * return method Id application
     * @return id of the application being entered
     */
    public int getId() {
        return id;
    }

    /***
     * setting method id application
     * @param id id of the application being entered
     */
    public void setId(int id) {
        this.id = id;
    }

    /***
     * return method type of leave
     * @return type of leave chosen by the employee
     */
    public String getLeaveType() {
        return leaveType;
    }
}
