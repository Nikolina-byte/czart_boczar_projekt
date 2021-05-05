package pwr.edu.czart_boczar_projekt.entity;

import java.time.LocalDate;

public class Project {
    private int id;
    private String Name;
    private String description;
    private LocalDate deadline;

    public Project(int id, String name, String description, LocalDate deadline) {
        this.id = id;
        Name = name;
        this.description = description;
        this.deadline = deadline;
    }

    public Project(String name, String description, LocalDate deadline) {
        Name = name;
        this.description = description;
        this.deadline = deadline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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
}
