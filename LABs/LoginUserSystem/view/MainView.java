package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private final Controller controller;

    private MainView(Controller controller) {
        this.controller = controller;
    }

    private static MainView INSTANCE;

    public static MainView getInstance(Controller controller) {
        if (INSTANCE == null) {
            INSTANCE = new MainView(controller);
        }
        return INSTANCE;
    }

    private JLabel label;

    public void init() {
        this.setSize(500, 500);
        setResizable(false);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);

        label = new JLabel();

        this.add(label, BorderLayout.CENTER);
        label.setText("You sucess sign in! It this page must be app of job search, but it is in development");

        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
