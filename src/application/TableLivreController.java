package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TableLivreController {
	 // Assurez-vous que le type correspond à votre classe Lecteur
@FXML
private Button precButton;
@FXML
private TableView<Livre> livreTable;
	 @FXML
	    private TableColumn<Livre, Long> codeColumn;
	@FXML

	private TableColumn<Livre, String> titreColumn;

    @FXML
    private TableColumn<Livre, String> auteurColumn; // Assurez-vous que le type correspond à votre classe Lecteur

    @FXML
    private TableColumn<Livre, Long> ISBNColumn;
	
	 @FXML
	    private void initialize() {
		 	codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
		 	titreColumn.setCellValueFactory(new PropertyValueFactory<>("titre")); 
		 	auteurColumn.setCellValueFactory(new PropertyValueFactory<>("auteur"));
		 	ISBNColumn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));

	        // Appelez une méthode pour charger les données de la base de données, par exemple.
		 	livreTable.getItems().addAll(Livre.chargerDonneesLivres());
	    }
	    @FXML
	    void precClicked(ActionEvent event) {
	        try {
	            // Charger la nouvelle scène à partir du fichier FXML LivreScene.fxml
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("LivreScene.fxml"));
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
