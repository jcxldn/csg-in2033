package ac.csg.in2033.ipos.pu.prm;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Pos;

// WARNING:     THIS CLASS HAS NOW BEEN DEPRECATED

public class HelloFX extends Application {

    @Override
    public void start(Stage stage) {
        String resourceText;
        try {

            InputStream resourceFile = getClass().getClassLoader().getResourceAsStream("resource.txt");
            resourceText = new String(resourceFile.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            resourceText = "(could not read)";
        }

        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        Label rl = new Label("Read from resource.txt: '" + resourceText + "'");

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.add(l, 0, 0);
        pane.add(rl, 0, 1);
        Scene scene = new Scene(pane, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}