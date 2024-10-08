package application;

import java.io.IOException;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AjoutLivreController {
	@FXML
	public Button ajoutbd;
	@FXML
	public Button precButton;
    @FXML
    private TextField titreField;

    @FXML
    private TextField auteurField;

    @FXML
    private TextField isbnField;

    private static final String BDD = "biblio_bd";
    private static final String URL = "jdbc:mysql://localhost:3306/" + BDD;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    @FXML
    public void ajouterLivreClicked() {
        try {
            // Récupérer les données des champs
            String titre = titreField.getText();
            String auteur = auteurField.getText();
            String isbnText = isbnField.getText();

            // Vérifier si les champs obligatoires sont vides
            if (titre.isEmpty() || auteur.isEmpty() || isbnText.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Veuillez remplir tous les champs obligatoires.");
                alert.show();
                return;
            }

            long isbn = 0; // Valeur par défaut, vous pouvez ajuster selon vos besoins

            try {
                // Convertir la chaîne en long
                isbn = Long.parseLong(isbnText);
            } catch (NumberFormatException e) {
                e.printStackTrace(); // Gérer l'exception, par exemple, en affichant un message à l'utilisateur
                return; // Sortir de la méthode si la conversion échoue
            }

            // Créer un objet Livre avec les données saisies
            Livre livre = new Livre();
            livre.setTitre(titre);
            livre.setAuteur(auteur);
            livre.setISBN(isbn);

            // Appeler la méthode pour ajouter le livre à la base de données
            ajouterLivre(livre);

            // Charger la nouvelle scène à partir du fichier FXML Ajout_livre.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Ajout_livre.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène
            Scene scene = new Scene(root);

            // Récupérer la référence à la scène actuelle à partir d'un des champs
            Stage stage = (Stage) titreField.getScene().getWindow();

            // Changer la scène principale
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ajouterLivre(Livre livre) {
        // Utiliser les valeurs de l'objet Livre pour créer la requête SQL
        String requete = "INSERT INTO livre (titre, auteur, ISBN) VALUES ('" +
                livre.getTitre() + "', '" +
                livre.getAuteur() + "', '" +
                livre.getISBN() + "')";

        try {
            // Connexion à la base de données et exécution de la requête
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stmt = con.createStatement();
            int result = stmt.executeUpdate(requete);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            if (result > 0) {
                alert.setContentText("Livre ajouté avec succès !");
                alert.show();
            } else {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("Échec de l'ajout du livre.");
                alert.show();
            }

            // Fermer la connexion
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'accès à la base de données : " + e.getMessage());
        }
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
