package ac.csg.in2033.ipos.pu.gui.dashboard;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;

public class SearchBarSceneController {
    private static final StringProperty searchQuery = new SimpleStringProperty("");

    @FXML
    private TextField searchField;

    @FXML
    private ListView<String> searchResultsList;

    private ProductFeedSceneController productFeedController;

    @FXML
    private void initialize() {
        searchField.setOnAction(event -> onSearchClick());
    }

    // Handle search input
    @FXML
    private void onSearchClick() {
        searchQuery.set(searchField.getText());  // Update search query
    }

    // Expose the search query property for binding
    public static StringProperty searchQueryProperty() {
        return searchQuery;
    }
}