package backend.nyc.com.titan.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameUI extends Application {

    Stage window;

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Titan");
        window.setMinWidth(650);
        window.setMinHeight(650);

        GridPane gridpane = new GridPane();

        Label label = new Label();
        label.setText("Label");
        gridpane.add(label, 2, 0);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                gridpane.add(new Button("(" + i + "," + j + ")"), j, i);
            }
        }

        Scene scene = new Scene(gridpane);
        window.setScene(scene);
        window.show();
    }

    private static void add(GridPane gridPane) {

    }

}
