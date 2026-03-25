package ac.csg.in2033.ipos.pu.gui.login;

import ac.csg.in2033.ipos.pu.gui.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class RegisterSceneController extends SceneController {
    private final static Logger logger = LoggerFactory.getLogger(RegisterSceneController.class);
    public Label notifLabel;
    public TextField emailTextField;

    @FXML
    protected void OnNonCommercialRegisterButtonClick() {
        // log button press
        if (logger.isDebugEnabled()) {
            logger.debug("Register button pressed.");
        }

        // these will be passed somewhere else
        String username = emailTextField.getText();
        //String password = passwordField.getText();

        // the user is notified if the username is unique to complete the
        // verification process using their email
        notifLabel.setText("Please verify your email to complete registration.");

        // send user/password text to other system
        // check if user/password are correctly formatted
        // check if user exists
        // if user details don't exist, pass them along
        // if commercial, send to IPOS-SA
    }

    @FXML
    protected void OnCommercialRegisterLinkClick() throws IOException {
        // create a FXMLLoader object
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/commercial-register-popup.fxml"));

        // Create a Scene to contain the new GUI
        Scene inputScene = new Scene(loader.load());

        // Get a reference to the Controller for the new GUI
        C_RegisterSceneController controller = loader.getController();

        // Tell the controller who its parent window is
        controller.setParent(RegisterSceneController.this);

        // Put the scene into a Stage and show it
        Stage inputStage = new Stage();
        inputStage.setTitle("Registration Form");
        inputStage.setScene(inputScene);
        inputStage.show();
    }
}
