package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RechercheLecteurController {

    @FXML
    private TextField cinField;
    @FXML
    private Label resultLabel;
    @FXML
    private Button rechercheLecteurButton;
    @FXML
    private Button precButton;
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

    private static final String BDD = "biblio_bd";
    private static final String URL = "jdbc:mysql://localhost:3306/" + BDD;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    @FXML
    void rechercheLecteurClicked(ActionEvent event) {
        try {
            long cin = Long.parseLong(cinField.getText());

            // Assuming you have a database connection established
            try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
                // Search for Lecteur by CIN
                String selectLecteurQuery = "SELECT * FROM lecteur WHERE CIN = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(selectLecteurQuery)) {
                    preparedStatement.setLong(1, cin);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        // Lecteur found, you can retrieve data from the resultSet
                        String nom = resultSet.getString("nom");
                        String prenom = resultSet.getString("prenom");
                        Long id_abo = resultSet.getLong("id_abo");
                        resultLabel.setText("Lecteur trouvé est de :\n Nom: " + nom + " , Prénom: " + prenom +" , ID abonnement : "+ id_abo);
                    } else {
                        // No Lecteur found with this CIN
                        showAlert(Alert.AlertType.WARNING, "Avertissement", "Aucun lecteur trouvé avec ce CIN.");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Erreur", "Échec de la recherche du lecteur. Veuillez réessayer.");
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur", "Veuillez entrer un CIN.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }}

