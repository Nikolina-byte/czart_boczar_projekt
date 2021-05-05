package pwr.edu.czart_boczar_projekt.entity;



public class VacationData {
    private int id;
    private int workedYears;
    private String  educationType;
    private int freeDay;
    private int usedDay;
    private Employee employee;

    public VacationData(int id, int workedYears, String educationType, int freeDay, int usedDay, Employee employee) {
        this.id = id;
        this.workedYears = workedYears;
        this.educationType = educationType;
        this.freeDay = freeDay;
        this.usedDay = usedDay;
        this.employee = employee;
    }

    public VacationData(int workedYears, String educationType, int freeDay, int usedDay, Employee employee) {
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
}

