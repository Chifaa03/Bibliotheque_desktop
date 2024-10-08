package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SuppressionLecteurController {

    @FXML
    private TextField idAboField;

    @FXML
    private Button suppressionLecteurButton;
    @FXML
    private Button precButton;

    private static final String BDD = "biblio_bd";
    private static final String URL = "jdbc:mysql://localhost:3306/" + BDD;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
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
    void suppressionLecteurClicked(ActionEvent event) {
        try {
            int idAbo = Integer.parseInt(idAboField.getText());

            // Assuming you have a database connection established
            try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
                // Delete from Lecteur and Abonnement tables using a JOIN
                String deleteLecteurAndAbonnementQuery = "DELETE lecteur, abonnement FROM lecteur " +
                        "LEFT JOIN abonnement ON lecteur.id_abo = abonnement.id_abo " +
                        "WHERE lecteur.id_abo = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(deleteLecteurAndAbonnementQuery)) {
                    preparedStatement.setInt(1, idAbo);
                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        showAlert(Alert.AlertType.INFORMATION, "Succès", "Lecteur et abonnement supprimés avec succès !");
                    } else {
                        showAlert(Alert.AlertType.WARNING, "Avertissement", "Aucun lecteur trouvé avec cet id_abo.");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Erreur", "Échec de la suppression du lecteur et de l'abonnement. Veuillez réessayer.");
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur", "Format de données invalide. Veuillez entrer des valeurs numériques valides.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
