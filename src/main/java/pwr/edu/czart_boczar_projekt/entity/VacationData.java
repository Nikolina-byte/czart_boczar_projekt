package pwr.edu.czart_boczar_projekt.entity;

import java.time.Duration;
import java.time.LocalDate;

public class VacationData {
    private int id;
    private LocalDate startCompany;
    private LocalDate dateMaxVacation;
    private int workedYears;
    private String  educationType;
    private int freeDay;
    private int usedDay;
    private Employee employee;

    //Start Constructor
    public VacationData(int id, LocalDate startCompany, int workedYears, String educationType, Employee employee) {
        this.id = id;
        this.startCompany = startCompany;
        this.dateMaxVacation = startCompany;
        this.workedYears = workedYears;
        this.educationType = educationType;
        this.freeDay = calculateVacationDay();
        this.usedDay = 0;
        this.employee = employee;
    }

    public VacationData(int id, LocalDate startCompany, LocalDate dateMaxVacation, int workedYears, String educationType,
                        int freeDay, int usedDay, Employee employee) {
        this.id = id;
        this.startCompany = startCompany;
        this.dateMaxVacation = dateMaxVacation;
        this.workedYears = workedYears;
        this.educationType = educationType;
        this.freeDay = freeDay;
        this.usedDay = usedDay;
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getStartCompany() {
        return startCompany;
    }

    public void setStartCompany(LocalDate startCompany) {
        this.startCompany = startCompany;
    }

    public void setDateMaxVacation(LocalDate dateMaxVacation) {
        this.dateMaxVacation = dateMaxVacation;
    }

    public int getWorkedYears() {
        return workedYears;
    }

    public void setWorkedYears(int workedYears) {
        this.workedYears = workedYears;
    }

    public String getEducationType() {
        return educationType;
    }

    public void setEducationType(String educationType) {
        this.educationType = educationType;
    }

    public int getFreeDay() {
        return freeDay;
    }

    public void setFreeDay(int freeDay) {
        this.freeDay = freeDay;
    }

    public int getUsedDay() {
        return usedDay;
    }

    public void setUsedDay(int usedDay) {
        this.usedDay = usedDay;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    private boolean isHaveEighteen(){
        boolean isHaveEighteen = false;
        int currentYear = LocalDate.now().getYear();
        int employeeYear = getEmployee().getBirth().getYear();
        if(currentYear - employeeYear < 18){
            isHaveEighteen = true;
        }
        return isHaveEighteen();
    }

    private boolean isFirstYear(){
        boolean isFirstYear = false;
        int currentYear = LocalDate.now().getYear();
        int employeeYear = getEmployee().getBirth().getYear();
        if(currentYear == employeeYear){
            isFirstYear = true;
        }
        return isFirstYear;
    }

    public int calculateVacationDay(){
        int newVacationDays = 0;
        if(isHaveEighteen()){
            newVacationDays = 26;
        }else{
            if(getWorkedYears() + switchEducationToYears() + getCurrentWorkYear() < 10){
                newVacationDays = 20;
                LocalDate date = getDateMaxVacation();
                setDateMaxVacation(date);
            }else{
                newVacationDays = 26;
            }
        }
        if(isFirstYear()){
            int month = getStartCompany().getMonth().getValue();
            newVacationDays = Math.round((newVacationDays / 12) * (12-month));
        }
        return newVacationDays;
    }

    private LocalDate getDateMaxVacation(){
        int years = getWorkedYears() + switchEducationToYears() + getWorkedYears();
        LocalDate today = LocalDate.now();
        Duration difference = Duration.between(getStartCompany().atStartOfDay(), today.atStartOfDay());
        long days = difference.toDays();
        int months = (int) (days/30);
        int numberYears = months/12;
        int numberMonth = months - numberYears*12;
        int numberDay = ((int) days - months*30);
        int missingDay = (((10 - years) * 12)-numberMonth)*30 - numberDay;

        return today.plusDays(missingDay);
    }

    private int getCurrentWorkYear(){
        LocalDate today = LocalDate.now();
        LocalDate startWork = getStartCompany();

        Duration difference = Duration.between(startWork.atStartOfDay(), today.atStartOfDay());

        long days = difference.toDays();
        int months = (int) (days/30);
        int numberYears = months/12;

        return numberYears;
    }

    private int switchEducationToYears(){
        int response;
        switch (this.educationType) {
            case "Podstawowe":
                response = 0;
                break;

            case "Zasadnicze":
                response = 3;
                break;

            case "Zawodowe":
                response = 5;
                break;

            case "Ogólnokształcące":
                response = 4;
                break;

            case "Policealne":
                response = 6;
                break;

            case "Wyższe":
                response = 8;
                break;

            default:
                response = 0;;
        }
        return response;
    }
}

