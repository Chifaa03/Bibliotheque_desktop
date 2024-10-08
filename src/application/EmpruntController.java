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

public class EmpruntController {

    @FXML
    private TextField idLecteurField;

    @FXML
    private TextField codeLivreField;
@FXML
private Button precButton;
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
    private Button emprunterButton;
    private static final String BDD = "biblio_bd";
    private static final String URL = "jdbc:mysql://localhost:3306/" + BDD;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    @FXML
    private void emprunterButtonClicked() {
        String idLecteur = idLecteurField.getText();
        String codeLivre = codeLivreField.getText();

        if (idLecteur.isEmpty() || codeLivre.isEmpty()) {
            showAlert("Veuillez remplir tous les champs.");
            return;
        }
       
        try {
        	Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            // Vérifier si le livre est déjà emprunté
            if (livreDejaEmprunte(connection, codeLivre)) {
                showAlert("Le livre est déjà emprunté.");
                return;
            }

            // Vérifier si le lecteur a des livres non rendus
            if (lecteurALivreNonRendu(connection, idLecteur)) {
                showAlert("Le lecteur a des livres non rendus et en retard .");
                return;
            }

            // Ajouter l'emprunt à la base de données
            ajouterEmprunt(connection, idLecteur, codeLivre);

            showAlert("Emprunt ajouté avec succès.");

            // Fermer la connexion
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Erreur lors de la connexion à la base de données.");
        }
    }

    private boolean livreDejaEmprunte(Connection connection, String code) throws SQLException {
        String query = "SELECT * FROM detailemprunt WHERE code = ? ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
    }

    private boolean lecteurALivreNonRendu(Connection connection, String id_abo) throws SQLException {
        String query = "SELECT * FROM detailemprunt WHERE id_abo = ? AND dateretour < CURRENT_DATE";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id_abo);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
    }


    private void ajouterEmprunt(Connection connection, String id_abo, String code) throws SQLException {
        String query = "INSERT INTO detailemprunt (id_abo, code, dateemprunt, dateretour) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id_abo);
            preparedStatement.setString(2, code);
            preparedStatement.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
            preparedStatement.setDate(4, java.sql.Date.valueOf(LocalDate.now().plusDays(7)));
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