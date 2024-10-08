package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Charger le fichier FXML
            Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));

            // Créer la scène
            Scene scene = new Scene(root, 800, 600);

            // Configurer le stage
            primaryStage.setTitle("Application JavaFX");
            primaryStage.setScene(scene);

            // Afficher le stage
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

