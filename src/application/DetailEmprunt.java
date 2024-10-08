package application;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class DetailEmprunt {
	Livre livre;
	LocalDate dateEmprunt;
	LocalDate dateRetour;
	long code;
	long id_abo;
	static long idEmprunt;
//constructor
	public DetailEmprunt(Livre livre) {
		this.livre = livre;
		dateEmprunt = LocalDate.now();
		dateRetour = dateEmprunt.plusDays(7);
	}
	public DetailEmprunt(Livre livre,LocalDate d) {
		this.livre = livre;
		dateEmprunt = d;
		dateRetour = dateEmprunt.plusDays(7);
	}
	
	
public DetailEmprunt(long idEmprunt, LocalDate dateEmprunt, LocalDate dateRetour, long id_abo, long code) {
		super();
		this.idEmprunt = idEmprunt;
		this.dateEmprunt = dateEmprunt;
		this.dateRetour = dateRetour;
		this.id_abo = id_abo;
		this.code = code;
	}
//getters and setters

	public LocalDate getDateEmprunt() {
		return dateEmprunt;
	}

	public void setDateEmprunt(LocalDate dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}

	public LocalDate getDateRetour() {
		return dateRetour;
	}

	public void setDateRetour(LocalDate dateRetour) {
		this.dateRetour = dateRetour;
	}
	public Livre getLivre() {
		return livre;
	}
	public void setLivre(Livre livre) {
		this.livre = livre;
	}
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	public long getId_abo() {
		return id_abo;
	}
	public void setId_abo(long id_abo) {
		this.id_abo = id_abo;
	}
	public long getIdEmprunt() {
		return idEmprunt;
	}
	public void setIdEmprunt(long idEmprunt) {
		this.idEmprunt = idEmprunt;


}
}