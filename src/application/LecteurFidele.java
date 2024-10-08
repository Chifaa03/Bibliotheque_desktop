package application;

public class LecteurFidele extends Lecteur{
	
	private String mail;
	private String preferance;
//constructor
	public LecteurFidele(int cIN, String nom, String prenom, Abonnement abonnement, String mail, String preferance) {
		super(cIN, nom, prenom, abonnement);
		this.mail = mail;
		this.preferance = preferance;
	}
//getter and setters 
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPreferance() {
		return preferance;
	}
	public void setPreferance(String preferance) {
		this.preferance = preferance;
	}
	
//affichage

	@Override
	public String toString() {
		return super.toString() + " [mail=" + mail + ", preferance=" + preferance +  "]";
	}

	
	
	

}
