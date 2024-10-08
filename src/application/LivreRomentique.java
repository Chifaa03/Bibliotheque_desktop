package application;

public class LivreRomentique extends Livre{
	private String description;
	private String nomPersonnage;
//constructor
	public LivreRomentique(String titre, String auteur, long iSBN, String description, String nomPersonnage) {
		super(titre, auteur, iSBN);
		this.description = description;
		this.nomPersonnage = nomPersonnage;
	}
	public LivreRomentique(String description, String nomPersonnage) {
		super();
		this.description = description;
		this.nomPersonnage = nomPersonnage;
	}
	public LivreRomentique(String titre, String auteur, String description, String nomPersonnage) {
		super(titre, auteur);
		this.description = description;
		this.nomPersonnage = nomPersonnage;
	}
	

//affichage
	@Override
	public String toString() {
		return super.toString() +  "LivreRomentique [description=" + description + ", nomPersonnage=" + nomPersonnage +  "]";
	}
	


}
