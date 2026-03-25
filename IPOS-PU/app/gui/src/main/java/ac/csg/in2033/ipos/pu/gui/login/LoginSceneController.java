package ac.csg.in2033.ipos.pu.gui.login;

import ac.csg.in2033.ipos.pu.gui.SceneController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class LoginSceneController extends SceneController {
    private final static Logger logger = LoggerFactory.getLogger(LoginSceneController.class);
    public Label descriptionLabel;
    public Label emailLabel;
    public Label passwordLabel;

    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Hyperlink registerLink;

    @FXML
    public Label notifLabel;

    @FXML
    protected void OnLoginButtonClick() {
        // log button press
        if (logger.isDebugEnabled()) {
            logger.debug("Login button pressed.");
        }

        // these will be passed somewhere else
        String username = emailTextField.getText();
        String password = passwordField.getText();

        // send user/password text to other system
        // check if user/password are correctly formatted
        // check if user exists
        // check permissions of user
        // if authenticated, show the main view fxml
        // pass along the level of access of the user

        // if new user, prompt them to create a new password
    }
}
