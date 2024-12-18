package model;

public class Vacancy {
    private String mainInfo;
    private String specialization;
    private String otherInfo;
    private String edu;
    private String yearExp;
    private Integer money;
    private String company;

    public Vacancy() {}

    public String getMainInfo() {
        return mainInfo;
    }
    public void setMainInfo(String mainInfo) {
        this.mainInfo = mainInfo;
    }

    public String getSpecialization() {
        return specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getOtherInfo() {
        return otherInfo;
    }
    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public String getEdu() {
        return edu;
    }
    public void setEdu(String edu) {
        this.edu = edu;
    }

    public String getYearExp() {
        return yearExp;
    }
    public void setYearExp(String yearExp) {
        this.yearExp = yearExp;
    }

    public Integer getMoney() {
        return money;
    }
    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }

    public Vacancy(String mainInfo, String specialization, String otherInfo, String edu, String yearExp, Integer money, String company) {
        this.mainInfo = mainInfo;
        this.specialization = specialization;
        this.otherInfo = otherInfo;
        this.edu = edu;
        this.yearExp = yearExp;
        this.money = money;
        this.company = company;
    }
}
