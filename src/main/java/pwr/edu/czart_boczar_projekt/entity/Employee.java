package pwr.edu.czart_boczar_projekt.entity;

import java.time.LocalDate;

public class Employee {
    private int id;
    private String first_name;
    private String last_name;
    private LocalDate birth;
    private String email;
    private String phone;
    private String position;
    private String status;

    public Employee(int id, String first_name, String last_name, LocalDate birth, String email, String phone, String position, String status) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth = birth;
        this.email = email;
        this.phone = phone;
        this.position = position;
        this.status = status;
    }

    public Employee(String first_name, String last_name, LocalDate birth, String email, String phone, String position, String status) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth = birth;
        this.email = email;
        this.phone = phone;
        this.position = position;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
