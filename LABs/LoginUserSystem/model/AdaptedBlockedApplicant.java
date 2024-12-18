package model;

import java.util.List;

public class AdaptedBlockedApplicant extends BlockedApplicant implements Observer {

    public AdaptedBlockedApplicant(String name, String mail, Integer age, String edu, String yearExp, String other) {
        super(name, mail, age, edu, yearExp, other);
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getMail() {
        return mail;
    }

    @Override
    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public Integer getAge() {
        return age;
    }

    @Override
    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String getEdu() {
        return edu;
    }

    @Override
    public void setEdu(String edu) {
        this.edu = edu;
    }

    @Override
    public String getYearExp() {
        return yearExp;
    }

    @Override
    public void setYearExp(String yearExp) {
        this.yearExp = yearExp;
    }

    @Override
    public String getOther() {
        return other;
    }

    @Override
    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public void handleEvent(List<Vacancy> vacancies) {
    }
}
