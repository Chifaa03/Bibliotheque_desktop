package application;

import java.time.*;
import java.util.*;

public class Biblio {
    ArrayList<Livre> liste_livres = new ArrayList<>();
    ArrayList<Lecteur> liste_lecteurs = new ArrayList<>();
    Map<Long, DetailEmprunt> mapEmprunts = new HashMap<Long, DetailEmprunt>();
    Map<Long, Integer> mapLivres = new HashMap<Long, Integer>();

 // Ajouter un livre
    public void ajouter_livre(Livre l) {
        ArrayList<Livre> tempList = new ArrayList<>(liste_livres);       
        tempList.add(l);
        liste_livres = tempList;       
        if (mapLivres.containsKey(l.getISBN())) {
            Integer val = mapLivres.get(l.getISBN());
            mapLivres.put(l.getISBN(), val + 1);
        } else {
            mapLivres.put(l.getISBN(), 1);
        }
    }


   /* // Emprunter un livre
    public void emprunter_livre(long cin, Livre l) throws EmpruntInterdit {
    	for(Lecteur l1 : this.Abonnements_epuises()) {
    		if (l1.getcin() == cin) throw new EmpruntInterdit();
    	}
        if (mapLivres.containsKey(l.getISBN())) {
            if (mapLivres.get(l.getISBN()) > 0) {
                if (!mapEmprunts.containsKey(cin)) {
                    DetailEmprunt detail = new DetailEmprunt(l);
                    mapEmprunts.put(cin, detail);
                    mapLivres.put(l.getISBN(), mapLivres.get(l.getISBN()) - 1);
                } else {
                    System.out.println("Le lecteur a déjà emprunté un livre.");
                }
            } else {
                System.out.println("Le livre n'est pas disponible.");
            }
        }
    }*/

    // Retourner un livre
   /* public void retourner_livre(Lecteur l, Livre livre) {
        if (mapEmprunts.containsKey(l.getcin())) {
            mapEmprunts.remove(l.getcin());
            mapLivres.put(livre.getISBN(), mapLivres.get(livre.getISBN()) + 1);
        }
    }*/

    // Le nombre d'emprunts
    public int nombre_livres_empruntes() {
        return mapEmprunts.size();
    }

    // Retourne le nombre de livres de retour dans les 7 jours suivants
    public int nombre_livres_retour() {
        int nbrLivre = 0;
        LocalDate today = LocalDate.now();

        for (DetailEmprunt detail : mapEmprunts.values()) {
            if (detail.getDateRetour().isBefore(today.plusDays(7))) {
                nbrLivre++;
            }
        }
        return nbrLivre;
    }
//tp5 manip2
    public ArrayList<Lecteur> lecteurs_fideles() {
        ArrayList<Lecteur> lecteurs = new ArrayList<>(); 
        for (Lecteur l : this.liste_lecteurs) {
            Period p = Period.between(l.getAbonnement().getCreationDate(), LocalDate.now());
            int pTotal = p.getMonths() + p.getYears() * 12;
            if(l instanceof LecteurFidele) {
            	lecteurs.add(l);
            }
            else if (l.getAbonnement().getListeEmprunt().size() / pTotal >= 2) {
                lecteurs.add(l);
            }
        }
        return lecteurs;
    }

    public void categories_livres() {
        int nbrPolicier = 0;
        int nbrRomantiques = 0;
        int nbrScience = 0;
        int nbrAutre = 0;

        for (int i = 0; i < this.liste_livres.size(); i++) {
            Livre l = this.liste_livres.get(i);

            if (l instanceof LivrePolicier) {
                nbrPolicier++;
            } else if (l instanceof LivreRomentique) {
                nbrRomantiques++;
            } else if (l instanceof LivreScienceFiction) {
                nbrScience++;
            } else {
                nbrAutre++;
            }
        }

        System.out.println("nbr des livres science fiction est : " + nbrScience);
        System.out.println("nbr des livres romantiques est : " + nbrRomantiques);
        System.out.println("nbr des livres policiers est : " + nbrPolicier);
        System.out.println("nbr des autres livres est : " + nbrAutre);
    }

    
    public ArrayList<Lecteur> Abonnements_epuises() {
        ArrayList<Lecteur> abonnementEpuise = new ArrayList<>(); 
    	for(Lecteur l : this.liste_lecteurs) {
    		if(l.getAbonnement().getCreationDate().plusYears(1).isBefore(LocalDate.now())) {
    			abonnementEpuise.add(l);
    		}
    	}
    	return abonnementEpuise;
    }

    
 
    
    
    
public static void main(String[] args) {
		//try {
		Biblio bib=new Biblio();
		
		//Constructeur:  public Livre(String titre, String auteur, long ISBN) 
		
        	Livre l1=new Livre("Stupeur et tremblement", "Nathalie Nothomb",12578945677L);
        	bib.liste_livres.add(l1);
		    Livre l2=new Livre("Les misérables", "Victor Hugo",1236547896541L);
		    bib.liste_livres.add(l2);
	        Livre l3=new Livre("Le Mur", "Jean Paul Sartre",7412589637418L);
	        bib.liste_livres.add(l3);
	        Livre l4=new Livre("Notre dame de Paris", "Victor Hugo",7894561236547L);
	        bib.liste_livres.add(l4);
	        Livre l5=new Livre("Crime et Chatiment", "Fyodor Dostoevsky",1234567891230L);
	        bib.liste_livres.add(l5);
	        Livre l6=new Livre("Orgueuil et Préjugés", "Jane Austen",3216549871230L);
	        bib.liste_livres.add(l6);
	        Livre l7=new Livre("Emma", "Jane Austen",1487956412347L);
	        bib.liste_livres.add(l7);
	        Livre l8=new Livre("Orgueuil et Préjugés", "Jane Austen",3216549871230L);
	        bib.liste_livres.add(l8);
	        Livre l9=new Livre("Orgueuil et Préjugés", "Jane Austen",3216549871230L);
	        bib.liste_livres.add(l9);
	        Livre l10=new Livre("que serais je sans toi", "Guillaume Musso",2589631478520L);
	        bib.liste_livres.add(l10);
	        Livre l11=new Livre("Stupeur et tremblement", "Nathalie Nothomb",12578945677L);
	        bib.liste_livres.add(l11);
	        Livre l12=new Livre("Le Mur", "Jean Paul Sartre",7412589637418L);
	        bib.liste_livres.add(l12);
	        
	   /*****  remplir maplivres en appelant it rativement 
	    * la m thode void ajouter_MapLivres( Livre L)   impl menter *********/
//	        System.out.println(bib.liste_livres.stream()
//	        .filter(l->l.getAuteur().equalsIgnoreCase("Victor Hugo")) //peek intermediare affichage en cours de traitement mais foreach est une fonction terminal doit etre a la fin du traitement de stream
//	        .filter(l->l.getTitre().startsWith("L"))
//	        .peek(System.out::println)
//	        .count());
////	        
////	        bib.liste_lecteurs.stream()
////	        .sorted(Comparator.comparing(liste_lecteurs::getNom)
////	        		.thenComparing(liste_lecteurs::getPrenom))
////	        .toList();
	        
	        
	        
	        
	        
	        for (Livre livre: bib.liste_livres) {
	        	bib.ajouter_livre( livre);
	        	System.out.println(livre);
	        }
	        

       // Afficher Maplivres
	        
	        System.out.println(bib.mapLivres);
	   // vous devez avoir ce résultat {7412589637418=2, 2589631478520=1, 7894561236547=1, 1234567891230=1, 12578945677=2, 1487956412347=1, 1236547896541=1, 3216549871230=3}     
       
//	   /******************Liste de lecteurs***********************/
//	        
	        bib.liste_lecteurs.add(new Lecteur(782456789,"Ines","Slim"));
	        bib.liste_lecteurs.add(new Lecteur(254567899,"Aymen","Ben Salah"));
	        bib.liste_lecteurs.add(new Lecteur(254566899,"Imen","Massoudi"));
	        bib.liste_lecteurs.add(new Lecteur(264567899,"Selim","Ben Aissa"));
	        bib.liste_lecteurs.add(new Lecteur(884567899,"Amine","Ben youssef"));
	        
	   /****************** Ajouter un emprunt à la map d'emprunts**************************/
	        /******implémenter la méthode void emprunter_livre(long cin, Livre livre) ***/
	        /** la méthode ajoute l'emprunt si le livre existe dans la liste des livres de la bibliothèque
	         *  et si le lecteur n'existe pas déjà dans la map d'emprunts(veut dire qu'il n'a pas déjà un livre emprunt )
	         *  n'oublier pas de décrémenter de 1 le nombre de copies de livres dans maplivres*****/ 
	         
	       //boolean ajouterEmprunt(long cin, long ISBN, LocalDate date)   impl menter
	     /* 
	        bib.emprunter_livre(782456789L, l3);// Ines Slim emprunte Le Mur
	        bib.emprunter_livre(884567899L, l6);// Amine Ben Youssef emprunte Orgueuil et pr jug s
	        bib.emprunter_livre(264567899L, l8);// Selim Ben Aissa emprunte Orgueil et pr jug s
	        bib.emprunter_livre(254566899L, l9);// Imen Massoudi emprunte Orgueil et pr jug s
	       
	        bib.emprunter_livre(254567899L,l9 );// Aymen Ben Saleh emprunte Orgueil et pr jug s
	       //  dans ce cas, vous devrez avoir cet affichage: Le livre n'est pas disponible
	        bib.emprunter_livre(782456789L, l5);// Ines Slim emprunte Les Mis rables
	        // dans ce cas, vous devrez avoir cet affichage: Le lecteur a d j  emprunt  un livre

//		   /********************************Affichage du  nombre total de livres*******************/
//	        
//	      ///  System.out.println("Nombre toatal de livres: "+bib.nombre_total_livres()); */
//	       // Nombre toatal de livres: 12
//	       
//	        /********************************Affichage du  nombre de livres emprunt s*******************/
//	        
              System.out.println("Nombre toatal de livres emprunt s: "+bib.nombre_livres_empruntes());
//	        //Nombre toatal de livres emprunt s: 4
//	       
//	        /********************************Affichage du  nombre total de livres de retour dans les sept jours qui suivent j*******************/
//	       
	        System.out.println("Nombre  de livres de retour dans les 7 jours suivants: "+bib.nombre_livres_retour());
//               // Nombre  de livres de retour dans les 7 jours suivants: 0
	             // faites les modifications nécessaires pour afficher 4
//	        
	/*	}catch(EmpruntInterdit e) {
			System.out.println(e.getMessage());
		}
		}*/
}}
