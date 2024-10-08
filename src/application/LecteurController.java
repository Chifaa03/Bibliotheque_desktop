package application;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
public class LecteurController {
	@FXML
	private Button ajoutButton;
	@FXML
	private Button suppButton;
	@FXML 
	private Button rechButton;
	@FXML
	private Button affichButton;
	@FXML
	private Button precButton;
	 @FXML
	    void precClicked(ActionEvent event) {
	        try {
	            // Charger la nouvelle scène à partir du fichier FXML LivreScene.fxml
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScene.fxml"));
	            Parent root = loader.load();

	            // Changer la scène principale
	            Scene scene = new Scene(root);
	            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	            stage.setScene(scene);
	            stage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	@FXML
	 void   ajoutlecteurClicked(ActionEvent event) {
	        try {
	            // Charger la nouvelle scène à partir du fichier FXML LivreScene.fxml
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutLecteur.fxml"));
	            Parent root = loader.load();

	            // Changer la scène principale
	            Scene scene = new Scene(root);
	            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	            stage.setScene(scene);
	            stage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	@FXML
	
	void supplecteurClicked(ActionEvent event) {
        try {
            // Charger la nouvelle scène à partir du fichier FXML LivreScene.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SuppLecteur.fxml"));
            Parent root = loader.load();

            // Changer la scène principale
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	@FXML
	
	void rechlecteurClicked(ActionEvent event) {
        try {
            // Charger la nouvelle scène à partir du fichier FXML LivreScene.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RechLecteur.fxml"));
            Parent root = loader.load();

            // Changer la scène principale
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	@FXML
	
	void affichlecteurClicked(ActionEvent event) {
        try {
            // Charger la nouvelle scène à partir du fichier FXML LivreScene.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichLecteur.fxml"));
            Parent root = loader.load();

            // Changer la scène principale
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
