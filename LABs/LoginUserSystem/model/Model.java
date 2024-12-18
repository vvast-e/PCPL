package model;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private static Model INSTANCE;

    private Model() {}

    public static Model getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Model();
        }
        return INSTANCE;
    }

    private String pathDataBase = "jdbc:sqlite:src/model/data.db";
    private String indentifTable = "IdentificationInfo";

    public List<Integer> blackList = new ArrayList<>();
    public Applicants applicants = new Applicants();
    public List<Vacancy> vacancies = new ArrayList<>();


    public String getPathDataBase() {
        return pathDataBase;
    }

    public String getIndentifTable() {
        return indentifTable;
    }
}
