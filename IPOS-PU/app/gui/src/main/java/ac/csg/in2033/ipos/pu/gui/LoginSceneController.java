package ac.csg.in2033.ipos.pu.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class LoginSceneController extends SceneController {
    private final static Logger logger = LoggerFactory.getLogger(LoginSceneController.class);

    @FXML
    private TextField email;

    @FXML
    private TextField pass;

    @FXML
    private Button loginButton;

    @FXML
    private Hyperlink registerLink;

    @FXML
    protected void OnLoginButtonClick() {
        // log button press
        if (logger.isDebugEnabled()) {
            logger.debug("Login button pressed.");
        }

        // these will be passed somewhere else
        String username = email.getText();
        String password = pass.getText();

        // send user/password text to other system
        // check if user/password are correctly formatted
        // check if user exists
        // check permissions of user
        // if authenticated, show the main view fxml
        // pass along the level of access of the user
    }

    @FXML
    protected void OnNonCommercialRegisterButtonClick() {
        // log button press
        if (logger.isDebugEnabled()) {
            logger.debug("Register button pressed.");
        }

        // these will be passed somewhere else
        String username = email.getText();
        String password = pass.getText();

        //

        // send user/password text to other system
        // check if user/password are correctly formatted
        // check if user exists
        // if user details don't exist, pass them along
        // if commercial, send to IPOS-SA
    }

    @FXML
    protected void OnCommercialRegisterLinkClick() throws IOException {
        // create a FXMLLoader object
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/commercial-register-menu.fxml"));

        // Create a Scene to contain the new GUI
        Scene inputScene = new Scene(loader.load());

        // Get a reference to the Controller for the new GUI
        C_RegisterSceneController controller = loader.getController();

        // Tell the controller who its parent window is
        controller.setParent(LoginSceneController.this);

        // Put the scene into a Stage and show it
        Stage inputStage = new Stage();
        inputStage.setTitle("Registration Form");
        inputStage.setScene(inputScene);
        inputStage.show();
    }
}
