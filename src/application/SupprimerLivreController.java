package application;

import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SupprimerLivreController {
	@FXML
	private TextField codeField;
	@FXML
	private Button precButton;
	@FXML
	private Button suppbd;

    private static final String BDD = "biblio_bd";
    private static final String URL = "jdbc:mysql://localhost:3306/" + BDD;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
@FXML
public void supprimerLivreClicked() {
    try {
        // Récupérer les données des champs
        String codeText = codeField.getText();

        // Vérifier si le champ code est vide
        if (codeText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Veuillez entrer un code.");
            alert.show();
            return; // Sortir de la méthode si le champ est vide
        }

        long code = Long.parseLong(codeText);

        // Appeler la méthode pour supprimer le livre de la base de données
        supprimer_livre(code);

        // Charger la nouvelle scène à partir du fichier FXML SuppLivre.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SuppLivre.fxml"));
        Parent root = loader.load();

        // Créer une nouvelle scène
        Scene scene = new Scene(root);

        // Récupérer la référence à la scène actuelle à partir d'un des champs
        Stage stage = (Stage) codeField.getScene().getWindow();

        // Changer la scène principale
        stage.setScene(scene);
        stage.show();

    } catch (IOException | NumberFormatException e) {
        e.printStackTrace();
    }
}


public void supprimer_livre(long code) {
    String requete = "DELETE FROM livre WHERE code = ?";

    try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
        System.out.println("Connecté");

        try (PreparedStatement preparedStatement = con.prepareStatement(requete)) {
            // Remplacement du paramètre de la requête par la valeur fournie
            preparedStatement.setLong(1, code);

            // Exécution de la requête
            int result = preparedStatement.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            if (result > 0) {
                alert.setContentText("Livre supprimé avec succès !");
            } else {
                alert.setAlertType(Alert.AlertType.WARNING);
                alert.setContentText("Aucun livre correspondant au code trouvé.");
            }

            // Afficher l'alerte
            alert.show();
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Erreur");
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
