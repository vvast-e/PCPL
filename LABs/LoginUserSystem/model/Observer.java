package model;

import java.util.List;

public interface Observer {
    public String getName();
    public void setName(String name);

    public String getMail();
    public void setMail(String mail);

    public Integer getAge();
    public void setAge(Integer age);

    public String getEdu();
    public void setEdu(String edu);

    public String getYearExp();
    public void setYearExp(String yearExp);

    public String getOther();
    public void setOther(String other);

    public void handleEvent(List<Vacancy> vacancies);
}
