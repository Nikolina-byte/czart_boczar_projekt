package pwr.edu.czart_boczar_projekt.entity;

import java.time.Duration;
import java.time.LocalDate;

/**
 * Class Implementation VacationData
 */
public class VacationData {
    private int id;
    private int workedYears;
    private String  educationType;
    private int freeDay;
    private int usedDay;
    private Employee employee;
    private LocalDate startCompany;
    private LocalDate dateMaxVacation;

    //Start Constructor

    /**
     *
     * Start Constructor of VacationData
     * @param startCompany date of start day at company
     * @param workedYears number of worked years at lifetime
     * @param educationType type of earned education
     * @param employee employee
     */
    public VacationData(LocalDate startCompany, int workedYears, String educationType, Employee employee) {
        this.startCompany = startCompany;
        this.employee = employee;
        this.dateMaxVacation = LocalDate.of(2010, 1,1);
        this.workedYears = workedYears;
        this.educationType = educationType;
        this.freeDay = calculateVacationDay();
        this.usedDay = 0;
    }

    /**
     *
     * @param workedYears number of worked years at lifetime
     * @param educationType type of earned education
     * @param freeDay number of free days
     * @param usedDay number of used days
     * @param employee employee
     * @param startCompany date of start day at company
     * @param dateMaxVacation
     */
    public VacationData(int workedYears, String educationType, int freeDay, int usedDay, Employee employee,
                        LocalDate startCompany, LocalDate dateMaxVacation) {
        this.workedYears = workedYears;
        this.educationType = educationType;
        this.freeDay = freeDay;
        this.usedDay = usedDay;
        this.employee = employee;
        this.startCompany = startCompany;
        this.dateMaxVacation = dateMaxVacation;
    }

    /**
     *Method gets Id of vacationData object
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Method sets Id of vacationData object
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method returns date of start day at company
     * @return date of start day at company
     */
    public LocalDate getStartCompany() {
        return startCompany;
    }


    /**
     * Method sets the maximum end date of vacation
     * @param dateMaxVacation date of maximum end date of vacation
     */
    public void setDateMaxVacation(LocalDate dateMaxVacation) {
        this.dateMaxVacation = dateMaxVacation;
    }

    /**
     * Method returns the maximum end date of vacation
     * @return date of maximum end date of vacation
     */
    public LocalDate getDateMaxVacation() {
        return dateMaxVacation;
    }

    /**
     * Method gets the number of Worked years by employee
     * @return number of worked years
     */
    public int getWorkedYears() {
        return workedYears;
    }


    /**
     * Method gets the earned education type by Employee
     * @return earned education type by Employee
     */
    public String getEducationType() {
        return educationType;
    }


    /**
     * Method gets number of days to use by employee
     * @return number of days to use by employee
     */
    public int getFreeDay() {
        return freeDay;
    }

    /**
     * Method sets the number of days to use by employee
     * @param freeDay number of days to use by employee
     */
    public void setFreeDay(int freeDay) {
        this.freeDay = freeDay;
    }

    /**
     * Method returns number of used days by employee
     * @return number of used days by employee
     */
    public int getUsedDay() {
        return usedDay;
    }

    /**
     * Method sets the number of Used days
     * @param usedDay number of used days by employee
     */
    public void setUsedDay(int usedDay) {
        this.usedDay = usedDay;
    }

    /**
     * Mehod gets the employee from instance of VacationData
     * @return employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Method sets the employee to Vacation Data
     * @param employee employee
     */
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
        return isHaveEighteen;
    }

    private boolean isFirstYear(){
        boolean isFirstYear = false;
        int currentYear = LocalDate.now().getYear();
        int employeeYear = getStartCompany().getYear();
        if(currentYear == employeeYear){
            isFirstYear = true;
        }
        return isFirstYear;
    }

    /**
     * Method caluclated the free days to use by employee
     * @return number of vacation days to use
     */
    public int calculateVacationDay(){
        int newVacationDays = 0;
        if(isHaveEighteen()){
            newVacationDays = 26;
        }else{
            if(getWorkedYears() + switchEducationToYears() + getCurrentWorkYear() < 10){
                newVacationDays = 20;
                LocalDate date = createDateMaxVacation();
                setDateMaxVacation(date);
            }else{
                newVacationDays = 26;
            }
        }
        if(isFirstYear()){
            int month = getStartCompany().getMonth().getValue();
            newVacationDays = (int) Math.round((newVacationDays / 12.0) * (12-month));
        }
        return newVacationDays;
    }

    private LocalDate createDateMaxVacation(){
        int years = getWorkedYears() + switchEducationToYears();
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

