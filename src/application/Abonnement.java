package application;

import java.time.LocalDate;
import java.util.*;

public class Abonnement {
	private LocalDate creationDate;
	private double frais;
	private ArrayList<DetailEmprunt> listeEmprunt;
	private int id_abo;
	
	
	public int getId_abo() {
		return id_abo;
	}


	public void setId_abo(int id_abo) {
		this.id_abo = id_abo;
	}


	public Abonnement(LocalDate creationDate, double frais, ArrayList<DetailEmprunt> listeEmprunt) {
		super();
		this.creationDate = creationDate;
		this.frais = frais;
		this.listeEmprunt = listeEmprunt;
	}
	public Abonnement(LocalDate creationDate, double frais, int id_abo) {
		super();
		this.creationDate = creationDate;
		this.frais = frais;
		this.id_abo=id_abo;
	}

	public Abonnement(LocalDate creationDate, ArrayList<DetailEmprunt> listeEmprunt) {
		super();
		this.creationDate = creationDate;
		this.listeEmprunt = listeEmprunt;
	}
	public Abonnement(LocalDate creationDate,int id_abo, ArrayList<DetailEmprunt> listeEmprunt) {
		super();
		this.id_abo=id_abo;
		this.creationDate = creationDate;
		this.listeEmprunt = listeEmprunt;
	}
	public Abonnement(LocalDate creationDate,int id_abo,double frais, ArrayList<DetailEmprunt> listeEmprunt) {
		super();
		this.id_abo=id_abo;
		this.frais=frais;
		this.creationDate = creationDate;
		this.listeEmprunt = listeEmprunt;
	}
	
	
//getter and setter

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public double getFrais() {
		return frais;
	}

	public void setFrais(double frais) {
		this.frais = frais;
	}

	public ArrayList<DetailEmprunt> getListeEmprunt() {
		return listeEmprunt;
	}

	public void setListeEmprunt(ArrayList<DetailEmprunt> listeEmprunt) {
		this.listeEmprunt = listeEmprunt;
	}

	

}
