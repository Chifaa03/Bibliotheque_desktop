package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

public class TitreController {

    @FXML
    private TextField titreField;

    @FXML
    private Button rechercherButton;

    @FXML
    private Label resultLabel;
    @FXML
    private Button precButton;

    private static final String BDD = "biblio_bd";
    private static final String URL = "jdbc:mysql://localhost:3306/" + BDD;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    @FXML
    void rechercherLivre() {
        String titre = titreField.getText();

        if (titre.isEmpty()) {
            afficherAlerte("Champ Vide", "Veuillez entrer un titre.");
            return;
        }

        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String requete = "SELECT * FROM livre WHERE titre = ?";

            try (PreparedStatement preparedStatement = con.prepareStatement(requete)) {
                preparedStatement.setString(1, titre);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Au moins un livre avec le titre spécifié a été trouvé
                        StringBuilder livresTrouves = new StringBuilder("Les livres de titre '").append(titre).append("' sont :\n");

                        do {
                            // Ajouter chaque livre trouvé à la chaîne de résultats
                            String livreInfo =
                                    "Code: " + resultSet.getLong("code") +
                                            ", Titre: " + resultSet.getString("titre") +
                                            ", Auteur: " + resultSet.getString("auteur") +
                                            ", ISBN :" + resultSet.getLong("ISBN") + "\n";

                            livresTrouves.append(livreInfo);
                        } while (resultSet.next());

                        resultLabel.setText(livresTrouves.toString());
                    } else {
                        // Aucun livre trouvé avec le titre spécifié
                        afficherAlerte("Aucun Résultat", "Aucun livre trouvé avec le titre : " + titre);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            afficherAlerte("Erreur", "Erreur lors de la recherche du livre.");
        }
    }

    // Méthode pour afficher des alertes
    private void afficherAlerte(String titre, String contenu) {
        Alert alerte = new Alert(Alert.AlertType.WARNING);
        alerte.setTitle(titre);
        alerte.setHeaderText(null);
        alerte.setContentText(contenu);
        alerte.showAndWait();
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
