package ac.csg.in2033.ipos.pu.gui;

import org.junit.jupiter.api.Test;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class AppViewTest {

    @Test
    void start() {
        URL url = getClass().getResource("fxml/app-view.fxml");
        assertNotNull(url);
    }
}