 package application;
 import java.sql.Statement;
import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Livre {
	private long code;
	private String titre;
	private String auteur;
	private long ISBN;
	private static long nbliv;

//constructor
	public Livre(String titre, String auteur) {
		this.titre = titre;
		this.auteur = auteur;
	nbliv++;
	}
	
	public Livre(long code ,String titre, String auteur, long iSBN) {
		this.code=code;
		this.titre = titre;
		this.auteur = auteur;
		ISBN = iSBN;
		nbliv++;
		
}
	
	public Livre(String titre, String auteur, long iSBN) {
			this.titre = titre;
			this.auteur = auteur;
			ISBN = iSBN;
			nbliv++;
		
	}
	public Livre() {
		nbliv++;
	
}
//getters and setters
	
	
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	public long getISBN() {
		return ISBN;
	}
	public void setISBN(long iSBN) {
		ISBN = iSBN;
	}
	
	

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	//Affichage
	@Override
	public String toString() {
		return "Livre [code=" + code + ", titre=" + titre + ", auteur=" + auteur + "]";
	}
//Compare
	public int compare(Livre l1) {
		return ( l1.titre.compareToIgnoreCase(titre));
	}
	public static int compare(Livre l1, Livre l2) {
		return ( l1.titre.compareToIgnoreCase(l2.titre));
	}
//la methode equals
	@Override
	public int hashCode() {
		return Objects.hash(auteur);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livre other = (Livre) obj;
		return Objects.equals(auteur, other.auteur);
	}
	private static final String BDD = "biblio_bd";
    private static final String URL = "jdbc:mysql://localhost:3306/" + BDD;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public void ajouter_livre(String titre, String auteur, String isbn) {
        String requete = "INSERT INTO livre (titre, auteur, ISBN) VALUES ('" + titre + "', '" + auteur + "', '" + isbn + "')";

        try {
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connecté");

            Statement stmt = con.createStatement();
            int result = stmt.executeUpdate(requete);

            if (result > 0) {
                System.out.println("Livre ajouté avec succès !");
            } else {
                System.out.println("Échec de l'ajout du livre.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur");
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

                if (result > 0) {
                    System.out.println("Livre supprimé avec succès !");
                } else {
                    System.out.println("Aucun livre correspondant au code trouvé.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur");
        }}
        
        public static List<Livre> chargerDonneesLivres() {
            List<Livre> listeDeLivres = new ArrayList<>();
            try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
                String query = "SELECT * FROM Livre";
                try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                        	long code = resultSet.getLong("code");
                            String titre = resultSet.getString("titre");
                            String auteur = resultSet.getString("auteur");
                            long ISBN = resultSet.getLong("ISBN");
                            
                            Livre livre = new Livre(code,titre,auteur,ISBN);
                            listeDeLivres.add(livre);
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception properly in a real-world scenario
            }

            return listeDeLivres;
        }
        
    


    public static void main(String[] args) {
   
	
}
}