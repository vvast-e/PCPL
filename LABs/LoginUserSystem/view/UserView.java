package view;

import controller.Controller;
import controller.DataBaseParser;
import controller.Guarder;
import controller.IdentificationController;
import model.Applicants;
import model.Vacancy;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class UserView extends JFrame {
    private final Controller controller;

    private UserView(Controller controller) {
        this.controller = controller;
    }

    private static UserView INSTANCE;

    public static UserView getInstance(Controller controller) {
        if (INSTANCE == null) {
            INSTANCE = new UserView(controller);
        }
        return INSTANCE;
    }

    private JPanel grid;
    private GridLayout layout;
    private JButton loginButton;
    private JTextField loginTextArea;
    private JPasswordField passwordTextArea;
    private JButton registerButton;

    public void init() {
        Thread dataThread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Integer> blackList = new ArrayList<>();
                blackList = DataBaseParser.getBlackList();

                Applicants applicants = new Applicants();
                applicants = DataBaseParser.getApplicants(blackList);

                List<Vacancy> vacancies = new ArrayList<>();
                vacancies = DataBaseParser.getVacancies();

                controller.setData(blackList, applicants, vacancies);
            }
        });
        dataThread.start();

        setSize(300, 400);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
        setResizable(false);

        loginButton = new JButton("sign in");
        loginTextArea = new JTextField();
        passwordTextArea = new JPasswordField();
        registerButton = new JButton("sign up");
        grid = new JPanel();
        layout = new GridLayout(4, 1, 10, 15);
        grid.setLayout(layout);

        grid.add(loginTextArea);
        grid.add(passwordTextArea);
        grid.add(loginButton);
        grid.add(registerButton);
        getContentPane().add(grid);

        loginButton.addActionListener(eventLogin -> {
            String checkLogin = loginTextArea.getText();
            String checkPassword = Guarder.hashPassword(passwordTextArea.getPassword());
            if (IdentificationController.checkData(checkLogin, checkPassword)) {
                this.setVisible(false);

                MainView mainWindow = MainView.getInstance(controller);
                SwingUtilities.invokeLater(() -> {
                    mainWindow.init();
                });
            } else {
                loginTextArea.setText("Invalid login or password. Try again");
                passwordTextArea.setText("");
                Thread sleepThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            TimeUnit.SECONDS.sleep(2);
                            loginTextArea.setText("");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                sleepThread.start();
            }
        });

        registerButton.addActionListener(eventRegister -> {
            this.setVisible(false);
            UserView.INSTANCE = null;
            RegisterView registerWindow = RegisterView.getInstance(controller);

            SwingUtilities.invokeLater(() -> {
                registerWindow.init();
            });
        });

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void signUp() {
        loginTextArea.setText("You sucess sign up!");
        passwordTextArea.setText("");
        Thread registerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    loginTextArea.setText("");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        registerThread.start();
    }
}
