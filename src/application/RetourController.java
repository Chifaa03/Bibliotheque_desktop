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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class RetourController {

    @FXML
    private TextField idLecteurField;

    @FXML
    private TextField codeLivreField;

    @FXML
    private Button retournerButton;
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailEmprunt.fxml"));
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
    private void retournerButtonClicked() {
        String idLecteur = idLecteurField.getText();
        String codeLivre = codeLivreField.getText();

        if (idLecteur.isEmpty() || codeLivre.isEmpty()) {
            showAlert("Veuillez remplir tous les champs.");
            return;
        }

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            // Vérifier si le livre est déjà emprunté
            if (!livreDejaEmprunte(connection, codeLivre)) {
                showAlert("Le livre n'est pas emprunté.");
                return;
            }

            // Vérifier si le lecteur a des livres non rendus
            if (lecteurALivreNonRendu(connection, idLecteur, codeLivre)) {
                showAlert("Le lecteur a un retard de retour.");
                supprimerEmprunt(connection, idLecteur, codeLivre);
                showAlert("Retour avec succès.");
            } else {
          
                supprimerEmprunt(connection, idLecteur, codeLivre);
                showAlert("Retour avec succès.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Erreur lors de la connexion à la base de données.");
        }
    }

    private boolean livreDejaEmprunte(Connection connection, String code) throws SQLException {
        String query = "SELECT * FROM detailemprunt WHERE code = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
    }

    private boolean lecteurALivreNonRendu(Connection connection, String id_abo, String code) throws SQLException {
        String query = "SELECT * FROM detailemprunt WHERE id_abo = ? AND code = ? AND dateretour < CURRENT_DATE";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id_abo);
            preparedStatement.setString(2, code);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
    }

    private void supprimerEmprunt(Connection connection, String id_abo, String code) throws SQLException {
        String query = "DELETE FROM detailemprunt WHERE id_abo = ? AND code = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id_abo);
            preparedStatement.setString(2, code);
            preparedStatement.executeUpdate();
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
