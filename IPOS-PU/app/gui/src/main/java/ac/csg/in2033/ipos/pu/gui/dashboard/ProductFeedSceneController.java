package ac.csg.in2033.ipos.pu.gui.dashboard;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import ac.csg.in2033.ipos.pu.sales.Product;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.util.List;

public class ProductFeedSceneController {

    @FXML
    private ListView<Product> productListView;

    @FXML
    public void initialize() {
        productListView.setItems(FXCollections.observableArrayList(
                new Product(0, "Product 0", "This is product 0.", 1.0, "product/image/img-0.png"),
                new Product(1, "Product 1", "This is product 1.", 1.50, "product/image/img-1.png")
        ));
    }

    public void onAddToCart(ActionEvent actionEvent) {
    }
}
