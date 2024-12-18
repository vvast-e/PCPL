package model;

public interface VacancyBuilder {
    VacancyBuilder setVacancyMainInfo(String mainInfo);

    VacancyBuilder setVacancySpecialization(String specialization);

    VacancyBuilder setVacancyOtherInfo(String otherInfo);

    VacancyBuilder setVacancyEdu(String edu);

    VacancyBuilder setVacancyYearExp(String yearExp);

    VacancyBuilder setVacancyMoney(Integer money);

    VacancyBuilder setVacancyCompany(String company);

    Vacancy build();
}
