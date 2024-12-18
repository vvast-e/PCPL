package model;

public class BlockedApplicant {
    protected String name;
    protected String mail;
    protected Integer age;
    protected String edu;
    protected String yearExp;
    protected String other;

    public BlockedApplicant(String name, String mail, Integer age, String edu, String yearExp, String other) {
        this.name = name;
        this.mail = mail;
        this.age = age;
        this.edu = edu;
        this.yearExp = yearExp;
        this.other = other;
    }
}
