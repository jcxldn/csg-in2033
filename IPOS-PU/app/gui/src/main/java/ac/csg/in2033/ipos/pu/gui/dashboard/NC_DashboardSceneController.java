package ac.csg.in2033.ipos.pu.gui.dashboard;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class NC_DashboardSceneController {

    @FXML private TextField searchField;
    @FXML private ListView<String> promotionsList;
    @FXML private GridPane productGrid;

    @FXML
    public void initialize() {

    }

    @FXML
    private void onSearchClick() {
        String query = searchField.getText();
    }

    @FXML
    private void onProfileClick() {
    }
}