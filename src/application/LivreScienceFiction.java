package application;

public class LivreScienceFiction extends Livre{
	private double annee;
	private String espace;
	
//constructor	
	public LivreScienceFiction(String titre, String auteur, double annee, String espace) {
		super(titre, auteur);
		this.annee = annee;
		this.espace = espace;
	}

	public LivreScienceFiction(double annee, String espace) {
		super();
		this.annee = annee;
		this.espace = espace;
	}
	
	public LivreScienceFiction(String titre, String auteur, long iSBN, double annee, String espace) {
		super(titre, auteur, iSBN);
		this.annee = annee;
		this.espace = espace;
	}

//affichage
	@Override
	public String toString() {
		return super.toString() + " LivreScienceFiction [annee=" + annee + ", espace=" + espace +  "]";
	}
	
		
	

}
