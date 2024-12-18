package controller;

import model.Model;

import java.sql.*;

public class IdentificationController {
    private static final Model model = Model.getInstance();

    public static boolean checkData(String login, String password) {
        String path = model.getPathDataBase();
        String table = model.getIndentifTable();

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(path);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT mail, hashPassword FROM " + table);
            while (rs.next()) {
                String nextLogin = rs.getString("mail");
                String nextPassword = rs.getString("hashPassword");
                if (nextLogin.equals(login) && nextPassword.equals(password)) {
                    return true;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("IDError");
        }
        return false;
    }
}
