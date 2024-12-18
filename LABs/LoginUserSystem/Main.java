import controller.Controller;
import model.Model;
import view.UserView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        Model model = Model.getInstance();
        Controller controller = Controller.getInstance(model);
        UserView userView = UserView.getInstance(controller);
        SwingUtilities.invokeLater(() -> {
            userView.init();
        });
    }
}
