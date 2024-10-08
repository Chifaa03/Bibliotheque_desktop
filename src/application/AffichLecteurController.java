package application;
import java.sql.Statement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AffichLecteurController {
@FXML
private Button precButton;
	@FXML
    private TableView<Lecteur> lecteurTable; // Assurez-vous que le type correspond à votre classe Lecteur
	 @FXML
	    private TableColumn<Lecteur, Long> cinColumn;
  

    @FXML
    private TableColumn<Lecteur, String> nomColumn; // Assurez-vous que le type correspond à votre classe Lecteur

    @FXML
    private TableColumn<Lecteur, String> prenomColumn;
    
    @FXML
    private TableColumn<Lecteur, Long> id_aboColumn;
    @FXML
    void precClicked(ActionEvent event) {
        try {
            // Charger la nouvelle scène à partir du fichier FXML LivreScene.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Lecteur.fxml"));
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
	    private void initialize() {
			cinColumn.setCellValueFactory(new PropertyValueFactory<>("cin"));
		 	nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
		 	prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		 	id_aboColumn.setCellValueFactory(new PropertyValueFactory<>("id_abo"));
		
	        // Appelez une méthode pour charger les données de la base de données, par exemple.
			lecteurTable.getItems().addAll(Lecteur.chargerDonneeLecteur());
	    }
}
