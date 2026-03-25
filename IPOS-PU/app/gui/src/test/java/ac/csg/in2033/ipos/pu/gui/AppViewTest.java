package ac.csg.in2033.ipos.pu.gui;

import static org.junit.jupiter.api.Assertions.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

class AppViewTest {
    @BeforeAll
    static void initJavaFX() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.startup(latch::countDown);

        latch.await(); // wait until JavaFX is ready
    }

    @Test
    void testLoginFxmlLoads() {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("login/fxml/login-root-tab.fxml")
        );

        assertNotNull(loader.getLocation(), "FXML file not found");

        try {
            Parent root = loader.load();
            assertNotNull(root, "Loaded root should not be null");
        } catch (Exception e) {
            fail("Failed to load FXML: " + e.getMessage(), e);
        }
    }
}