package ac.csg.in2033.ipos.pu.gui.login;

import ac.csg.in2033.ipos.pu.gui.SceneController;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginRootSceneController extends SceneController {
    private final static Logger logger = LoggerFactory.getLogger(LoginRootSceneController.class);

    public TabPane tabPane;

    public Tab loginTab;
    public Tab registerTab;

    public VBox loginTabController;
    public VBox registerTabController;
}
