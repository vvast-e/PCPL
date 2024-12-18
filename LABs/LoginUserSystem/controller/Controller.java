package controller;

import model.*;

import java.util.List;

public class Controller implements Observed {
    private final Model model;

    private static Controller INSTANCE;

    public static Controller getInstance(Model model) {
        if (INSTANCE == null) {
            INSTANCE = new Controller(model);
        }
        return INSTANCE;
    }

    public Controller(Model model) {
        this.model = model;
    }

    public void setData(List<Integer> blackList, Applicants applicants, List<Vacancy> vacancies) {
        model.blackList = blackList;
        model.applicants = applicants;
        model.vacancies = vacancies;
    }

    public void addVacancy(Vacancy vacancy) {
        model.vacancies.add(vacancy);
        notifyObservers();
    }

    public void removeVacancy(Vacancy vacancy) {
        model.vacancies.remove(vacancy);
        notifyObservers();
    }

    @Override
    public boolean addObserver(Observer observer) {
        model.applicants.add(observer);
        DataBaseParser.addAplicant(new Applicant(
                observer.getName(),
                observer.getMail(),
                observer.getAge(),
                observer.getEdu(),
                observer.getYearExp(),
                observer.getOther())
        );
        return true;
    }

    public boolean registerObserver(Observer observer, char[] password) {

        for (Observer applicant : model.applicants) {
            try {
                if (applicant.getMail().equals(observer.getMail())) {
                    return false;
                }
            } catch (Exception ignored) {
            }
        }

        DataBaseParser.addAplicant(new Applicant(
                        observer.getName(),
                        observer.getMail(),
                        observer.getAge(),
                        observer.getEdu(),
                        observer.getYearExp(),
                        observer.getOther()),
                password
        );
        return true;
    }

    @Override
    public boolean removeObserver(Observer observer) {
        model.applicants.remove(observer);
        return true;
    }

    @Override
    public boolean notifyObservers() {
        for (Observer observer : model.applicants) {
            observer.handleEvent(model.vacancies);
        }
        return true;
    }
}
