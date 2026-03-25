package ac.csg.in2033.ipos.pu.gui;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class AppView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        StageController.setStage(stage);
        StageController.setTitle("Login");
        StageController.setScene(getClass().getResource("login/fxml/login-tab.fxml"));
    }

    public static void main(String[] args) {
        launch();
    }
}

