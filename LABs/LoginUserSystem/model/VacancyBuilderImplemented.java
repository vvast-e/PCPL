package model;

public class VacancyBuilderImplemented implements VacancyBuilder {
    Vacancy vacancy = new Vacancy();

    @Override
    public VacancyBuilder setVacancyMainInfo(String mainInfo) {
        vacancy.setMainInfo(mainInfo);
        return this;
    }

    @Override
    public VacancyBuilder setVacancySpecialization(String specialization) {
        vacancy.setSpecialization(specialization);
        return this;
    }

    @Override
    public VacancyBuilder setVacancyOtherInfo(String otherInfo) {
        vacancy.setOtherInfo(otherInfo);
        return this;
    }

    @Override
    public VacancyBuilder setVacancyEdu(String edu) {
        vacancy.setEdu(edu);
        return this;
    }

    @Override
    public VacancyBuilder setVacancyYearExp(String yearExp) {
        vacancy.setYearExp(yearExp);
        return this;
    }

    @Override
    public VacancyBuilder setVacancyMoney(Integer money) {
        vacancy.setMoney(money);
        return this;
    }

    @Override
    public VacancyBuilder setVacancyCompany(String company) {
        vacancy.setCompany(company);
        return this;
    }

    @Override
    public Vacancy build() {
        return vacancy;
    }
}
