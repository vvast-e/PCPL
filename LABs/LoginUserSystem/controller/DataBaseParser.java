package controller;

import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseParser {
    private static final Model model = Model.getInstance();

    public static List<Integer> getBlackList() {
        List<Integer> ReturnList = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(model.getPathDataBase());
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id FROM Blacklist");
            while (rs.next()) {
                ReturnList.add(rs.getInt("id"));
            }
            stmt.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error");
        }
        return ReturnList;
    }

    public static Applicants getApplicants(List<Integer> blackList) {
        Applicants applicants = new Applicants();
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(model.getPathDataBase());
            Statement stmt = conn.createStatement();
            String sql = "SELECT id, userName, mail, age, edu, yearExp, other FROM Applicants";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("userName");
                String mail = rs.getString("mail");
                Integer age = rs.getInt("age");
                String edu = rs.getString("edu");
                String yearExp = rs.getString("yearExp");
                String other = rs.getString("other");

                boolean flag = false;
                for (Integer element : blackList) {
                    if (element.equals(id)) {
                        flag = true;
                    }
                }
                Observer nextApplicant;
                if (flag) {
                    nextApplicant = new AdaptedBlockedApplicant(name, mail, age, edu, yearExp, other);
                } else {
                    nextApplicant = new Applicant(name, mail, age, edu, yearExp, other);
                }
                applicants.addApplicat(nextApplicant);
            }
            stmt.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error");
        }
        return applicants;
    }

    public static List<Vacancy> getVacancies() {
        List<Vacancy> vacancies = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(model.getPathDataBase());
            Statement stmt = conn.createStatement();
            String sql = "SELECT id, mainInfo, specialization, otherInfo, edu, yearExp, money, company FROM Vacancies";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String mainInfo = rs.getString("mainInfo");
                String specialization = rs.getString("specialization");
                String otherInfo = rs.getString("otherInfo");
                String edu = rs.getString("edu");
                String yearExp = rs.getString("yearExp");
                Integer money = rs.getInt("money");
                String company = rs.getString("company");
                Vacancy vacancy = new VacancyBuilderImplemented()
                        .setVacancyMainInfo(mainInfo)
                        .setVacancySpecialization(specialization)
                        .setVacancyOtherInfo(otherInfo)
                        .setVacancyEdu(edu)
                        .setVacancyYearExp(yearExp)
                        .setVacancyMoney(money)
                        .setVacancyCompany(company)
                        .build();
                vacancies.add(vacancy);
            }
            stmt.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("SQLError");
        }
        return vacancies;
    }

    public static void addAplicant(Observer applicant, char[] password) {
        addAplicant(applicant);
        Thread identificationSQLThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("org.sqlite.JDBC");
                    Connection conn = DriverManager.getConnection(model.getPathDataBase());
                    String sql = "INSERT INTO IdentificationInfo(mail, hashPassword) VALUES (?,?)";
                    PreparedStatement st = conn.prepareStatement(sql);

                    String hashedPassword = Guarder.hashPassword(password);
                    st.setString(1, applicant.getMail());
                    st.setString(2, hashedPassword);

                    st.executeUpdate();
                    st.close();
                } catch (SQLException | ClassNotFoundException e) {
                    System.out.println("SQLError");
                }
            }
        });
        identificationSQLThread.start();
    }

    public static void addAplicant(Observer applicant) {
        Thread SQLThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("org.sqlite.JDBC");
                    Connection conn = DriverManager.getConnection(model.getPathDataBase());
                    String sql = "INSERT INTO Applicants(userName, mail, age, edu, yearExp, other) VALUES (?,?,?,?,?,?)";
                    PreparedStatement st = conn.prepareStatement(sql);

                    st.setString(1, applicant.getName());
                    st.setString(2, applicant.getMail());
                    st.setInt(3, 0);
                    st.setString(4, applicant.getEdu());
                    st.setString(5, applicant.getYearExp());
                    st.setString(6, applicant.getOther());

                    st.executeUpdate();
                    st.close();
                } catch (SQLException | ClassNotFoundException e) {
                    System.out.println("SQLError");
                }
            }
        });
        SQLThread.start();
    }
}
