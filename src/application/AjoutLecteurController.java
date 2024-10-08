package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class AjoutLecteurController {

    @FXML
    private TextField cinField;

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private DatePicker creationDateField;

    @FXML
    private TextField fraisField;

    @FXML
    private TextField idAboField;
    @FXML
    private Button precButton;

    @FXML
    private Button ajoutLecteur;
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
    void ajouterLecteurClicked(ActionEvent event) {
        try {
            long cin = Long.parseLong(cinField.getText());
            String nom = nomField.getText();
            String prenom = prenomField.getText();
            LocalDate creationDate = LocalDate.now(); // Set creationDate to the current date
            double frais = Double.parseDouble(fraisField.getText());
            int idAbo = Integer.parseInt(idAboField.getText());

            // Assuming you have a database connection established
            try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
                // Insert into Lecteur table
                String insertLecteurQuery = "INSERT INTO lecteur (CIN, nom, prenom, id_abo) VALUES (?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertLecteurQuery, Statement.RETURN_GENERATED_KEYS)) {
                    preparedStatement.setLong(1, cin);
                    preparedStatement.setString(2, nom);
                    preparedStatement.setString(3, prenom);
                    preparedStatement.setInt(4, idAbo);
                    preparedStatement.executeUpdate();
                }

                // Insert into Abonnement table
                String insertAbonnementQuery = "INSERT INTO abonnement (creationDate, frais, id_abo) VALUES (?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertAbonnementQuery)) {
                    preparedStatement.setDate(1, java.sql.Date.valueOf(creationDate));
                    preparedStatement.setDouble(2, frais);
                    preparedStatement.setInt(3, idAbo);
                    preparedStatement.executeUpdate();
                }

                showAlert(Alert.AlertType.INFORMATION, "Success", "Lecteur et Abonnement ajouté avec succés!");

            } catch (SQLException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error", "Probleme d'ajout. essayez une autre fois.");
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid data format. Please enter valid numerical values.");
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
