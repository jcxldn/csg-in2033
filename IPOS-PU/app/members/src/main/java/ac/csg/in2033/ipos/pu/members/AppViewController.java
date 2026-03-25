package ac.csg.in2033.ipos.pu.members;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppViewController {
    String javafxVersion = System.getProperty("javafx.version");
    String javaVersion = System.getProperty("java.version");
    private final static Logger logger = LoggerFactory.getLogger(AppViewController.class);

    @FXML
    private Label javafxVersionText;

    @FXML
    private Label javaVersionText;

    @FXML
    protected void onJavafxButtonClick() {
        javafxVersionText.setText("Welcome to JavaFX version " + javafxVersion + "!");
        if (logger.isDebugEnabled()) {
            logger.debug("JavaFX button pressed.");
        }
    }

    @FXML
    protected void onJavaButtonClick() {
        javaVersionText.setText("Welcome to Java version " + javaVersion + "!");
        if (logger.isDebugEnabled()) {
            logger.debug("Java button pressed.");
        }
    }

    public void handleLogin(ActionEvent actionEvent) {
    }
}

