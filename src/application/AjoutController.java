package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class AjoutController {

    @FXML
    private Button ajoutButton;
    @FXML
    private Button suppButton;
    @FXML
    private Button affButton;
    @FXML
    private Button titreButton;
    @FXML
    private Button auteurButton;
    @FXML
    private Button lettreButton;
    @FXML
    private Button precButton;
 
    
    private static final String BDD = "biblio_bd";
    private static final String URL = "jdbc:mysql://localhost:3306/" + BDD;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    @FXML
    private TextArea livresTextArea;

 
  @FXML
    // Méthode appelée au besoin pour charger les données
    public void chargerDonnees() {
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT * FROM livre";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    StringBuilder livresText = new StringBuilder();
                    while (resultSet.next()) {
                        Livre livre = new Livre(
                                resultSet.getInt("code"),
                                resultSet.getString("titre"),
                                resultSet.getString("auteur"),
                                resultSet.getLong("isbn")
                        );
                        livresText.append(livre.getTitre()).append(" - ")
                                .append(livre.getAuteur()).append("\n");
                    }

                    if (livresText.length() > 0) {
                        // Si des livres sont trouvés, les afficher dans la TextArea
                        livresTextArea.setText(livresText.toString());
                    } else {
                        // Si aucun livre n'est trouvé
                        livresTextArea.setText("Aucun livre trouvé.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

@FXML
    public void ajoutClicked(ActionEvent event) {
        try {
            // Charger la nouvelle scène à partir du fichier FXML SuppLivre.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Ajout_livre.fxml"));
            Parent root = loader.load();

            // Changer la scène principale
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            this.chargerDonnees();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
@FXML
    public void affichClicked(ActionEvent event) {
        try {
            // Charger la nouvelle scène à partir du fichier FXML SuppLivre.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Affich_livre.fxml"));
            Parent root = loader.load();

            // Changer la scène principale
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            this.chargerDonnees();
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
         } 
@FXML
    public void suppClicked(ActionEvent event) {
        try {
            // Charger la nouvelle scène à partir du fichier FXML SuppLivre.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SuppLivre.fxml"));
            Parent root = loader.load();

            // Changer la scène principale
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            this.chargerDonnees();  
        } catch (IOException e) {
            e.printStackTrace();
        }
         } 
@FXML
    public void recherchtitreClicked(ActionEvent event) {
        try {
            // Charger la nouvelle scène à partir du fichier FXML SuppLivre.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RechLivre.fxml"));
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
    public void recherchauteurClicked(ActionEvent event) {
             try {
                 // Charger la nouvelle scène à partir du fichier FXML SuppLivre.fxml
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("RechAuteurLivre.fxml"));
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
    public void rechercheTitreClicked(ActionEvent event) {
        try {
            // Charger la nouvelle scène à partir du fichier FXML SuppLivre.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RechLettreLivre.fxml"));
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
    void precClicked(ActionEvent event) {
        try {
            // Charger la nouvelle scène à partir du fichier FXML LivreScene.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScene.fxml"));
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
    
    
