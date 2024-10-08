package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class RechercheAuteurController {

    @FXML
    private TextField auteurField;

    @FXML
    private TextArea livresTextArea;
    @FXML 
    private Button rechercherButton;
    @FXML
    private Button precButton;

    @FXML
    private Label resultLabel;
    private static final String BDD = "biblio_bd";
    private static final String URL = "jdbc:mysql://localhost:3306/" + BDD;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    // Méthode appelée lorsqu'on clique sur le bouton Rechercher
    public void rechercherLivreauteur() {
        String auteur = auteurField.getText();

        if (auteur.isEmpty()) {
            afficherAlerte("Champ Vide", "Veuillez entrer l'auteur.");
            return;
        }

        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String requete = "SELECT * FROM livre WHERE auteur = ?";

            try (PreparedStatement preparedStatement = con.prepareStatement(requete)) {
                preparedStatement.setString(1, auteur);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    List<Livre> livresByAuteur = new ArrayList<>();

                    while (resultSet.next()) {
                        Livre livre = new Livre();
                        livre.setCode(resultSet.getLong("code"));
                        livre.setTitre(resultSet.getString("titre"));
                        livre.setAuteur(resultSet.getString("auteur"));
                        livresByAuteur.add(livre);
                    }

                    if (!livresByAuteur.isEmpty()) {
                        // Si des livres sont trouvés, les concaténer dans une chaîne
                        StringBuilder livresTrouves = new StringBuilder("Les livres d'auteur '").append(auteur).append("' sont :\n");
                        livresByAuteur.forEach(livre -> {
                            livresTrouves.append("Code: ").append(livre.getCode())
                                    .append(", Titre: ").append(livre.getTitre())
                                    .append(", Auteur: ").append(livre.getAuteur())
                                    .append("\n");
                        });

                        resultLabel.setText(livresTrouves.toString());
                    } else {
                        // Si aucun livre n'est trouvé
                        afficherAlerte("Aucun Résultat", "Aucun livre trouvé avec auteur : " + auteur);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            afficherAlerte("Erreur", "Erreur lors de la recherche du livre.");
        }
    }

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
