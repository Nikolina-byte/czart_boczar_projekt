package pwr.edu.czart_boczar_projekt.entity;

import java.time.LocalDate;

/**
 * Class Implementation ProjectInformationView
 */
public class ProjectInformationView {
    private int id;
    private String name;
    private String description;
    private LocalDate deadline;
    private String employees;

    /**
     * Constructor of ProjectInformationView
     * @param id id of object
     * @param name name
     * @param description description
     * @param deadline end date of Project
     * @param employees employee objects assigned to ProjectInformationView
     */
    public ProjectInformationView(int id, String name, String description, LocalDate deadline, String employees) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.employees = employees;
    }


    /**
     * Method gets the id of ProjectInformationView object
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Method sets the id of ProjectInformationView object
     * @param id number of ProjectInformationView
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method gets the name of Project
     * @return name of Project
     */
    public String getName() {
        return name;
    }

    /**
     * Method sets the name of Project
     * @param name name of project
     */
    public void setName(String name) {
        this.name = name;
    }


}
