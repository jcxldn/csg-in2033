package ac.csg.in2033.ipos.pu.gui.dashboard;

import ac.csg.in2033.ipos.pu.prm.Promotion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import ac.csg.in2033.ipos.pu.sales.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ProductFeedSceneController {

    @FXML
    private ListView<Product> productListView;

    ObservableList<Product> productList = FXCollections.observableArrayList(); // Filtered

    private ObservableList<Product> allProducts = FXCollections.observableArrayList(); // Unfiltered
    private Promotion currentPromotion;

    @FXML
    public void initialize() {
        // Insert dummy data
        allProducts.add(new Product(0, "Product 0", "This is product 0.", 1.0, "product/image/img-0.png"));
        allProducts.add(new Product(1, "Product 1", "This is product 1.", 1.50, "product/image/img-1.png"));
        allProducts.add(new Product(2, "Product 2", "This is product 2.", 1.99, "product/image/img-0.png"));
        allProducts.add(new Product(3, "Product 3", "This is product 3.", 25.00, "product/image/img-1.png"));

        // Save all products
        productList.setAll(allProducts);

        // Move products to viewing list
        productListView.setItems(productList);

        // Set the custom cell factory
        productListView.setCellFactory(param -> new ListCell<Product>() {
            private final VBox productCell;
            private final ProductItemSceneController controller;

            // Load FXML once per cell instance
            {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/product-item.fxml"));
                    productCell = loader.load();
                    controller = loader.getController();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            protected void updateItem(Product product, boolean empty) {
                super.updateItem(product, empty);

                if (empty || product == null) {
                    setGraphic(null);
                } else {
                    controller.setProduct(product);
                    setGraphic(productCell);
                }
            }
        });

        // Binding filters products when search query changes
        SearchBarSceneController.searchQueryProperty().addListener((observable, oldValue, newValue) -> {
            filterProducts(newValue);
        });
    }


    // Set current promotion and update prices
    public void setPromotion(Promotion promotion) {
        this.currentPromotion = promotion;
        updateProductPrices();
    }

    // Update product prices based on the selected promotion
    private void updateProductPrices() {
        for (Product product : allProducts) {
            if (currentPromotion != null) {
                double discount = currentPromotion.getDiscount(String.valueOf(product.getId()));
                if (discount > 0) {
                    product.setDiscountedPrice(product.getPrice() * (1 - discount / 100));
                }
            }
        }
        productList.setAll(allProducts);  // Refresh the product list view
    }


    // Filter products based on search query
    public void filterProducts(String query) {
        ObservableList<Product> filteredList = FXCollections.observableArrayList();

        for (Product product : allProducts) {
            if (product.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(product);
            }
        }

        // Update the list with filtered products
        productListView.setItems(filteredList);
    }

    public void onAddToCart(ActionEvent actionEvent) {
    }
}
