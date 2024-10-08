package application;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Lecteur {

	private int cin;
	private String nom;
	private String prenom;
	private int id_abo;
	private Abonnement abonnement;
	private double sommeCumulu = 0;
	
	
	
	
//constructor 

	public Lecteur(int cIN, String nom, String prenom) {
		super();
		this.cin = cIN;
		this.nom = nom;
		this.prenom = prenom;
		
	}
	public Lecteur(int cin, String nom, String prenom , int id_abo) {
		super();
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.id_abo=id_abo;
		
	}

	public Lecteur (String nom, String p , int id_ab) {
		
		this.nom = nom;
		this.prenom = p;
		this.id_abo=id_ab;
		
	}
	public Lecteur(int cIN, String nom, String prenom,Abonnement abonnement) {
		super();
		this.cin = cIN;
		this.nom = nom;
		
		
		this.prenom = prenom;
		this.abonnement=abonnement;
	}
	//constructor tp7

	
//getter and setter 

	public int getCin() {
		return cin;
	}

	public void setCin(int cIN) {
		cin = cIN;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	


public int getId_abo() {
		return id_abo;
	}

	public void setId_abo(int id_abo) {
		this.id_abo = id_abo;
	}

public Abonnement getAbonnement() {
		return abonnement;
	}

	public void setAbonnement(Abonnement abonnement) {
		this.abonnement = abonnement;
	}

	//affichage
	@Override
	public String toString() {
		return "Lecteur [CIN=" + cin + ", nom=" + nom + ", prenom=" + prenom + "]";
	}
	
//methode frais abonnement (tp5 manip1)
	
	public double frais_Abonnement() 
	{
		return this.abonnement.getFrais();
	}
	
	

	
    private static final String BDD = "biblio_bd";
    private static final String URL = "jdbc:mysql://localhost:3306/" + BDD;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    public static List<Lecteur> chargerDonneeLecteur() {
        List<Lecteur> listeDeLecteurs = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM lecteur";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                    	int cin = resultSet.getInt("cin");
                        String nom = resultSet.getString("nom");
                        String prenom = resultSet.getString("prenom");
                        int id_abo = resultSet.getInt("id_abo");

                        
                        Lecteur lecteur = new Lecteur(cin,nom,prenom,id_abo);
                        listeDeLecteurs.add(lecteur);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in a real-world scenario
        }

        return listeDeLecteurs;
    } 
	
	
	
	
	
	public static void main(String[] args) {
	}
	
	
}
