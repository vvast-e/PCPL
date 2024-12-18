package view;

import controller.Controller;
import model.Applicant;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class RegisterView extends JFrame {
    private final Controller controller;

    private RegisterView(Controller controller) {
        this.controller = controller;
    }

    private static RegisterView INSTANCE;

    public static RegisterView getInstance(Controller controller) {
        if (INSTANCE == null) {
            INSTANCE = new RegisterView(controller);
        }
        return INSTANCE;
    }

    private JPanel grid;
    private GridLayout layout;
    private JTextField loginTextArea;
    private JPasswordField passwordTextArea;
    private JButton registerButton;
    private JButton backButton;

    public void init() {
        this.setSize(300, 400);
        setResizable(false);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);

        loginTextArea = new JTextField();
        passwordTextArea = new JPasswordField();
        registerButton = new JButton("sign up");
        backButton = new JButton("back");
        grid = new JPanel();
        layout = new GridLayout(4, 1, 10, 15);
        grid.setLayout(layout);

        grid.add(loginTextArea);
        grid.add(passwordTextArea);
        grid.add(registerButton);
        grid.add(backButton);
        getContentPane().add(grid);

        registerButton.addActionListener(eventRegister -> {
            if (!controller.registerObserver(
                    new Applicant("", loginTextArea.getText(), null, "", "", ""),
                    passwordTextArea.getPassword())) {
                loginTextArea.setText("User with this mail exists. Try another mail");
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
            } else {
                this.setVisible(false);
                RegisterView.INSTANCE = null;

                UserView userView = UserView.getInstance(controller);
                SwingUtilities.invokeLater(() -> {
                    userView.init();
                    userView.signUp();
                });
            }
        });
        backButton.addActionListener(eventBack -> {
            this.setVisible(false);
            RegisterView.INSTANCE = null;

            UserView userView = UserView.getInstance(controller);
            SwingUtilities.invokeLater(() -> {
                userView.init();
            });
        });

        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
