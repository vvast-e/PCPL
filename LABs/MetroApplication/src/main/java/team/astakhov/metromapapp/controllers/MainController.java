package team.spirit.metromapapp.controllers;

import javafx.util.Pair;
import team.spirit.metromapapp.model.*;
import team.spirit.metromapapp.utils.algo.MyEdge;
import team.spirit.metromapapp.utils.algo.MyVertex;
import team.spirit.metromapapp.utils.algo.Vertex;

import java.sql.*;
import java.util.*;

public class MainController {
    private static Model model;
    private static MainController INSTANCE;

    private MainController(Model model) {
        MainController.model = model;
    }

    public static MainController getInstance(Model model) {
        if (INSTANCE == null) {
            INSTANCE = new MainController(model);
        }
        return INSTANCE;
    }

    public static List<MyVertex> getStantions() {
        return getNameColorStations("");
    }


    public static List<MyEdge> getEdges() {
        List<MyEdge> data = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(model.getPathDataBase());
            Statement stmt = conn.createStatement();
            String sql = "SELECT lt2.id, Time, id1, name1, vetka1, id2, name as name2, vetka as vetka2 FROM " +
                    "(SELECT lt1.id, Time, id1, name as name1, vetka as vetka1, id2 FROM Edges as lt1 " +
                    "LEFT JOIN Stantions as rt1 ON lt1.id1 = rt1.id) as lt2 " +
                    "LEFT JOIN Stantions as rt2 ON lt2.id2 = rt2.id";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                data.add(new MyEdge(rs.getInt("Time"),
                        rs.getInt("id1"), rs.getInt("id2"),
                        rs.getString("name1"), rs.getString("name2"),
                        rs.getString("vetka1"), rs.getString("vetka2")
                ));
            }
            stmt.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("DataBase Error");
        }
        return data;
    }

    public static Vertex getVertexByIdStation(int id) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(model.getPathDataBase());
            Statement stmt = conn.createStatement();
            String sql = "SELECT name, vetka, x, y FROM Stantions WHERE id = " + id;

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                return new Vertex(rs.getString("name"), rs.getString("vetka"), rs.getInt("x"), rs.getInt("y"));
            }
            stmt.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("DataBase Error");
        }
        return null;
    }

    public static List<Integer> getAllId() {
        List<Integer> data = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(model.getPathDataBase());
            Statement stmt = conn.createStatement();
            String sql = "SELECT id FROM Stantions";

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                data.add(rs.getInt("id"));
            }
            stmt.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("DataBase Error");
        }
        Collections.sort(data);
        return data;
    }

    public static List<MyVertex> getNameColorStations(String startNameStantion) {
        List<MyVertex> data = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(model.getPathDataBase());
            String sql;
            Statement stmt = conn.createStatement();
            if (startNameStantion.isEmpty()) {
                sql = "SELECT name, vetka, x, y FROM Stantions";
            } else {
                String[] st = new String[2];
                st[0] = startNameStantion;
                st[1] = "";
                if (startNameStantion.contains(" ")) {
                    st = startNameStantion.split(" ");
                }
                sql = "SELECT name, vetka, x, y FROM Stantions WHERE name LIKE '" + st[0] + "%' and vetka LIKE '" + st[1] + "%' ";
            }
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                data.add(new MyVertex(rs.getString("name"), rs.getString("vetka"), rs.getInt("x"), rs.getInt("y")));
            }
            stmt.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("DataBase Error");
        }
        Collections.sort(data, (o1, o2) -> {
            int c1 = (o1.getName()).compareTo(o2.getName());
            int c2 = (o1.getColor()).compareTo(o2.getColor());
            if (c1 != 0) {
                return c1;
            } else {
                return c2;
            }
        });
        return data;
    }

}

