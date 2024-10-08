package application;

public class LivrePolicier extends Livre{
	private String descriptif;
	private String nomDetective;
	private String nomVectime;
	
//constructor
	public LivrePolicier(String titre, String auteur, String descriptif, String nomDetective, String nomVectime) {
		super(titre, auteur);
		this.descriptif = descriptif;
		this.nomDetective = nomDetective;
		this.nomVectime = nomVectime;
	}

	public LivrePolicier(String descriptif, String nomDetective, String nomVectime) {
		super();
		this.descriptif = descriptif;
		this.nomDetective = nomDetective;
		this.nomVectime = nomVectime;
	}

	public LivrePolicier(String titre, String auteur, String descriptif, String nomDetective,
			String nomVectime, long iSBN) {
		super(titre, auteur, iSBN);
		this.descriptif = descriptif;
		this.nomDetective = nomDetective;
		this.nomVectime = nomVectime;
	}

	@Override
	public String toString() {
		return super.toString() + "LivrePolicier [descriptif=" + descriptif + ", nomDetective=" + nomDetective + ", nomVectime="
				+ nomVectime + "]";
	}
	
	

}
