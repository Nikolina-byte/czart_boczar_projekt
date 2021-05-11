package pwr.edu.czart_boczar_projekt.entity;

import java.time.LocalDate;

/**
 * Class Implementation Project
 */
public class Project {
    private int id;
    private String Name;
    private String description;
    private LocalDate deadline;

    /**
     * Constructor of Project
     * @param id number of project
     * @param name name of project
     * @param description description of project
     * @param deadline end date of project
     */
    public Project(int id, String name, String description, LocalDate deadline) {
        this.id = id;
        Name = name;
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * Constructor of Project
     * @param name name of project
     * @param description description of project
     * @param deadline end date of project
     */
    public Project(String name, String description, LocalDate deadline) {
        Name = name;
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * Method gets id
     * @return id of Project
     */
    public int getId() {
        return id;
    }

    /**
     * Method sets  the id
     * @param id set the id if project
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Method returns name of project
     * @return name of project
     */
    public String getName() {
        return Name;
    }

    /**
     * Method sets name of project
     * @param name
     */
    public void setName(String name) {
        Name = name;
    }


}
