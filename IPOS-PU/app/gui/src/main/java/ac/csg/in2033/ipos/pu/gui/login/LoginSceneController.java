package ac.csg.in2033.ipos.pu.gui.login;

import ac.csg.in2033.ipos.pu.gui.SceneController;
import ac.csg.in2033.ipos.pu.gui.StageController;
import ac.csg.in2033.ipos.pu.gui.dashboard.NC_DashboardSceneController;
import ac.csg.in2033.ipos.pu.members.UserDatabase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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

         // these will be passed somewhere else
        String email = emailTextField.getText();
        String password = passwordField.getText();

        boolean success = UserDatabase.login(email, password);

        // check first login

        // this will be commented out until database is implemented in
        // order to test further features without issue using dummy data
        /*
        if (success) {
            if (UserDatabase.isFirstLogin(email)) {
                notifLabel.setText("Please change your password");
                emailLabel.setVisible(false);
                emailTextField.setVisible(false);
                return;
            }
            String userType = UserDatabase.getUserType(email);
            notifLabel.setText("Login successful: " + userType);
        } else {
            notifLabel.setText("Login failed");
        }
         */

        // send user/password text to other system
        // check if user/password are correctly formatted
        // check if user exists
        // check permissions of user
        // if authenticated, show the main view fxml
        // pass along the level of access of the user

        // if new user, prompt them to create a new password

        // assuming success:

        try {
            loadNonCommercialDashboard();
        } catch (IOException e) {
            logger.debug("Non-commercial user dashboard could not be loaded: ", e);
        }

    }

    private void loadNonCommercialDashboard() throws IOException {
        StageController.setScene(NC_DashboardSceneController.class.getResource("/ac/csg/in2033/ipos/pu/gui/dashboard/fxml/non-commercial-dashboard.fxml"));
        StageController.setTitle("Dashboard");
    }
}
