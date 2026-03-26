package ac.csg.in2033.ipos.pu.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public final class StageController {
    private final static Logger logger = LoggerFactory.getLogger(StageController.class);

    private StageController() {
    }

    private static Stage currentStage;
    private static Scene currentScene;

    public static void setStage(Stage stage) {
        currentStage = stage;
    }

    public static void setTitle(String title) {
        currentStage.setTitle(title);
    }

    public static void setScene(URL resourcePath) throws RuntimeException {
        if (currentStage == null) {
            logger.debug("Stage has not been initialised.");
            return;
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(resourcePath);
            Scene scene = new Scene(fxmlLoader.load());
            currentScene = scene;
            currentStage.setScene(scene);
            currentStage.show();
        } catch (IOException ex) {
            logger.debug("Attempted to set scene to {}.", resourcePath.getPath());
            throw new RuntimeException("Failed to load FXML", ex);
            //logger.debug("Scene switch attempted from {} to {}", currentScene.toString(), resourcePath.getPath());
        }
    }

    public static Scene getCurrentScene() {
        return currentScene;
    }
}
